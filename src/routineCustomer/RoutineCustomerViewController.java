package routineCustomer;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import customerRegistration.data;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class RoutineCustomerViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listName;

    @FXML
    private ListView<String> listAddress;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtDefaultCQ;

    @FXML
    private TextField txtDefaultBQ;

    @FXML
    private TextField txtVariationCQ;

    @FXML
    private TextField txtVariationBQ;

    @FXML
    private CheckBox checkSkipped;

    @FXML
    private Label lblInsert;

    @FXML
    private Button btnClose;

    @FXML
    private TextField txtID;
    
    PreparedStatement pst;
    Connection con;
    
    void doConnection()
    {
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con = DriverManager.getConnection("jdbc:mysql://localhost/dairy_management", data.uid, data.pwd);
    		System.out.println("Connected");
    	}
    	
    	catch (Exception ex){
    		ex.printStackTrace();
    	}
    }

    @FXML
    void doClose(ActionEvent event) {
    	Alert confirm = new Alert(AlertType.CONFIRMATION);
    	confirm.setTitle("Closing..");
    	confirm.setContentText("Are You sure?");
    	Optional<ButtonType> res= confirm.showAndWait();
    	if(res.get()==ButtonType.OK)
    	{
    		Scene scene1=(Scene)btnClose.getScene();
			scene1.getWindow().hide();
    	}
    }

    @FXML
    void doDelete(ActionEvent event) {
    	try
       	{
        	int i = 0, j = 0;
        	ObservableList<String> s = listAddress.getItems();
        	ObservableList<String> s1 = listAddress.getSelectionModel().getSelectedItems();
        	ArrayList<String> all = new ArrayList<String>(s);
        	ArrayList<String> selected = new ArrayList<String>(s1);
        	
        	while(!all.equals(selected) && j != selected.size())
        	{
        		for (String string : all) {
					if (selected.contains(string))
					{
						i++;
						j++;
					}
					else
					{
						listName.getItems().remove(i);
						listAddress.getItems().remove(i);
					}
				}
        	}
        }
        catch(Exception e){
      	e.printStackTrace();
        }
     	
     	listName.getSelectionModel().clearSelection();
     	listAddress.getSelectionModel().clearSelection();
     	txtDefaultCQ.setText("");
    	txtDefaultBQ.setText("");
    	txtVariationCQ.setText("");
    	txtVariationBQ.setText("");
    	txtMobile.setText("");
    	checkSkipped.setSelected(false);
    	lblInsert.setVisible(false);
     	
    }

    @FXML
    void doDelete1(MouseEvent event) {
    	listAddress.getSelectionModel().clearSelection();
    	ObservableList<Integer> indx = listName.getSelectionModel().getSelectedIndices();
    	for (Integer integer : indx) {
			listAddress.getSelectionModel().select(integer);
		}
    	lblInsert.setVisible(false);
    	try {
			pst = con.prepareStatement("select Mobile,CQ,BQ from registration where Address = ?");
			pst.setString(1, listAddress.getSelectionModel().getSelectedItem());
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				txtMobile.setText(rs.getString("Mobile"));
				txtDefaultCQ.setText(rs.getString("CQ"));
				txtDefaultBQ.setText(rs.getString("BQ"));
			}
    	}
    	
    	catch (SQLException e) {
			
			e.printStackTrace();
		}
    }

    @FXML
    void doFetch(ActionEvent event) {
    	listName.getItems().clear();
    	listAddress.getItems().clear();
    	
    	listName.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	listAddress.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	
    	txtVariationCQ.setText("0");
    	txtVariationBQ.setText("0");
    	
    	try {
			pst = con.prepareStatement("select registration.CustomerName,registration.Address from registration inner join milkmanregistration on registration.MilkmanID = milkmanregistration.MilkmanID where registration.MilkmanID = ?");
			pst.setString(1, txtID.getText());
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				listName.getItems().add(rs.getString("CustomerName"));
				listAddress.getItems().add(rs.getString("Address"));
			}
			
		} 
    	
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    }

    @FXML
    void doInsert(ActionEvent event) {
    	float dcq =0, dbq = 0, netcq = 0, netbq = 0;
    	try {
			pst = con.prepareStatement("select CQ,BQ from registration where Address = ?");
			pst.setString(1, listAddress.getSelectionModel().getSelectedItem());
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				dcq = Float.parseFloat(rs.getString("CQ"));
				dbq = Float.parseFloat(rs.getString("BQ"));
			}
    	}
    	
    	catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    	
    	if(checkSkipped.isSelected() == true)
    	{
    		netcq = 0;
    		netbq = 0;
    	}
    	
    	else
    	{
    		float vcq = Float.parseFloat(txtVariationCQ.getText());
    		float vbq = Float.parseFloat(txtVariationBQ.getText());
    		netcq = dcq + vcq;
    		netbq = dbq + vbq;
    	}
    	
    	try
    	{
    		pst=con.prepareStatement("insert into routine values(?,?,?,?,month(current_date),year(current_date),day(current_date))");
    		pst.setString(1, txtID.getText());
    		pst.setString(2, txtMobile.getText());
    		pst.setFloat(3, netcq);
    		pst.setFloat(4, netbq);
    		pst.executeUpdate();
    		lblInsert.setVisible(true);
    	}
    	
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	int indx=listName.getSelectionModel().getSelectedIndex();
		listName.getItems().remove(indx);
		listAddress.getItems().remove(indx);
		
		listName.getSelectionModel().clearSelection();
		listAddress.getSelectionModel().clearSelection();
		txtDefaultCQ.setText("");
    	txtDefaultBQ.setText("");
    	txtVariationCQ.setText("");
    	txtVariationBQ.setText("");
    	txtMobile.setText("");
    	checkSkipped.setSelected(false);
    	txtVariationCQ.setText("0");
    	txtVariationBQ.setText("0");
		
    }

    @FXML
    void doNew(ActionEvent event) {
    	listName.getItems().clear();
    	listAddress.getItems().clear();
    	
    	txtID.setText("");
    	txtDefaultCQ.setText("");
    	txtDefaultBQ.setText("");
    	txtVariationCQ.setText("0");
    	txtVariationBQ.setText("0");
    	txtMobile.setText("");
    	checkSkipped.setSelected(false);
    	lblInsert.setVisible(false);
    }

    @FXML
    void initialize() {
    	doConnection();
    	txtVariationCQ.setText("0");
    	txtVariationBQ.setText("0");
    	
    }
}
