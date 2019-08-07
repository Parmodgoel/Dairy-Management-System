package adminDashboard;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import adminDashboard.data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AdminDashboardViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab tabMilkman;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextArea txtAddress;

    @FXML
    private TextField txtSalary;

    @FXML
    private ListView<String> listMilkman;
    
    @FXML
    private TextField txtSearch;
    
    @FXML
    private Button btnBack;

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
    void doDetails(ActionEvent event) {
    	try{
    		
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("milkmanDetails/MilkmanDetailsView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void doNew(ActionEvent event) {
    	txtMobile.setText("");
    	txtName.setText("");
    	txtAddress.setText("");
    	txtSalary.setText("");
    	txtID.setText("");
    	txtSearch.setText("");
    	
    	listMilkman.getItems().clear();
    	
    	try {
			pst = con.prepareStatement("select MilkmanName from milkmanregistration");
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				listMilkman.getItems().add(rs.getString("MilkmanName"));
			}
			
		} 
    	
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}

    }

    @FXML
    void doSave(ActionEvent event) {
    	try {
			pst = con.prepareStatement("insert into milkmanregistration values(?,?,?,?,?)");
			pst.setString(1,txtID.getText());
			pst.setString(2,txtName.getText());
			pst.setString(3,txtMobile.getText());
			pst.setString(4,txtAddress.getText());
			pst.setString(5,txtSalary.getText());
	
			pst.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    	listMilkman.getItems().clear();
    	
    	try {
			pst = con.prepareStatement("select MilkmanName from milkmanregistration");
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				listMilkman.getItems().add(rs.getString("MilkmanName"));
			}
			
		} 
    	
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}

    }

    @FXML
    void doSearch(ActionEvent event) {
    	
    	try {
    		pst=con.prepareStatement("select * from milkmanregistration where MilkmanName LIKE ?");
 			pst.setString(1, txtSearch.getText() + "%");
	
 			listMilkman.getItems().clear();
 			
 			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				
				listMilkman.getItems().add(rs.getString("MilkmanName"));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

    }
    
    @FXML
    void doListName(MouseEvent event) {
    	if(event.getClickCount() == 2)
    	{
    		try {
    			pst = con.prepareStatement("select * from milkmanregistration where MilkmanName = ?");
    			pst.setString(1, listMilkman.getSelectionModel().getSelectedItem());
    			
    			ResultSet rs = pst.executeQuery();
    			
    			rs.next();

    				txtID.setText(rs.getString("MilkmanID"));
    				txtName.setText(rs.getString("MilkmanName"));
    				txtMobile.setText(rs.getString("Mobile"));
    				txtAddress.setText(rs.getString("Address"));
    				txtSalary.setText(rs.getString("Salary"));
    }
        	
        	catch (SQLException e) {
    			
    			e.printStackTrace();
    		}
    	}
    	
    }

    @FXML
    void doUpdate(ActionEvent event) {
    	try {
			pst = con.prepareStatement("update milkmanregistration set MilkmanName = ?, Address = ?, Salary = ?, Mobile = ? where MilkmanID = ?");
			pst.setString(5,txtID.getText());
			pst.setString(1,txtName.getText());
			pst.setString(2,txtAddress.getText());
			pst.setString(3,txtSalary.getText());
			pst.setString(4,txtMobile.getText());
			
			pst.executeUpdate();
			
		} 
    	
    	catch (SQLException e) {
			e.printStackTrace();
		}

    }
    
    @FXML
    void doBack(ActionEvent event) {
    	try{
    		
    		Scene scene1=(Scene)btnBack.getScene();
			scene1.getWindow().hide();
    		
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("admin/AdminView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
    	doConnection();
        
    	try {
			pst = con.prepareStatement("select MilkmanName from milkmanregistration");
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				listMilkman.getItems().add(rs.getString("MilkmanName"));
			}
			
		} 
    	
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    }
}
