package customerRegistration;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class CustomerRegistrationViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtArea;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCQ;

    @FXML
    private TextField txtBQ;

    @FXML
    private TextField txtCP;

    @FXML
    private TextField txtBP;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;
    
    @FXML
    private Button btnClose;

    @FXML
    private DatePicker datepicker;
    
    @FXML
    private ImageView errMobile;

    @FXML
    private ImageView errName;

    @FXML
    private ImageView errAddress;

    @FXML
    private ImageView errArea;

    @FXML
    private ImageView errCity;

    @FXML
    private ImageView errDate;
    
    @FXML
    private TextField txtID;
    
    @FXML
    private ImageView errID;
    
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
    	try {
			pst = con.prepareStatement("delete from registration where Mobile = ?");
			pst.setString(1, txtMobile.getText());
			pst.executeUpdate();
			
			txtMobile.setText("");
			txtID.setText("");
	    	txtName.setText("");
	    	txtAddress.setText("");
	    	txtArea.setText("");
	    	txtCity.setText("");
	    	txtCQ.setText("0.0");
	    	txtCP.setText("0.0");
	    	txtBQ.setText("0.0");
	    	txtBP.setText("0.0");
	    	errAddress.setVisible(false);
	    	errArea.setVisible(false);
	    	errDate.setVisible(false);
	    	errCity.setVisible(false);
	    	errMobile.setVisible(false);
	    	errName.setVisible(false);
	    	errID.setVisible(false);
	    	datepicker.setValue(null);
	    	btnDelete.setText("Deleted...");
			
    	}
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    }

    @FXML
    void doNew(ActionEvent event) {
    	txtMobile.setText("");
    	txtID.setText("");
    	txtName.setText("");
    	txtAddress.setText("");
    	txtArea.setText("");
    	txtCity.setText("");
    	txtCQ.setText("0.0");
    	txtCP.setText("0.0");
    	txtBQ.setText("0.0");
    	txtBP.setText("0.0");
    	errAddress.setVisible(false);
    	errArea.setVisible(false);
    	errDate.setVisible(false);
    	errCity.setVisible(false);
    	errMobile.setVisible(false);
    	errName.setVisible(false);
    	errID.setVisible(false);
    	datepicker.setValue(null);
    	btnSave.setText("Save");
    	btnUpdate.setText("Update");
    	btnDelete.setText("Delete");
    }

    @FXML
    void doSave(ActionEvent event) {
    	if(txtMobile.getText().equals(""))
			errMobile.setVisible(true);
    	
    	if(txtID.getText().equals(""))
    			errID.setVisible(true);
    	
    	if(txtName.getText().equals(""))
			errName.setVisible(true);
    	
    	if(txtAddress.getText().equals(""))
			errAddress.setVisible(true);
    	
    	if(txtArea.getText().equals(""))
			errArea.setVisible(true);
    	
    	if(txtCity.getText().equals(""))
			errCity.setVisible(true);
    	
    	if(datepicker.getValue() == null)
			errDate.setVisible(true);
    else
    {
    	LocalDate local = datepicker.getValue();
    	
    	try {
			pst = con.prepareStatement("insert into registration values(?,?,?,?,?,?,?,?,?,?,?)");
			pst.setString(1,txtMobile.getText());
			pst.setString(2,txtID.getText());
			pst.setString(3,txtName.getText());
			pst.setString(4,txtAddress.getText());
			pst.setString(5,txtArea.getText());
			pst.setString(6,txtCity.getText());
			pst.setString(7,txtCQ.getText());
			pst.setString(8,txtCP.getText());
			pst.setString(9,txtBQ.getText());
			pst.setString(10,txtBP.getText());
			Date d = Date.valueOf(local);
			pst.setDate(11, d);
			pst.executeUpdate();
			errAddress.setVisible(false);
	    	errArea.setVisible(false);
	    	errDate.setVisible(false);
	    	errCity.setVisible(false);
	    	errMobile.setVisible(false);
	    	errID.setVisible(false);
	    	errName.setVisible(false);
			btnSave.setText("Saved...");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    }
    
    }

    @FXML
    void doSearch(ActionEvent event) {
    	try {
			pst = con.prepareStatement("select * from registration inner join milkmanregistration on registration.MilkmanID = milkmanregistration.MilkmanID where registration.Mobile = ? and registration.MilkmanID = ?");
			pst.setString(1, txtMobile.getText());
			pst.setString(2, txtID.getText());
			
			ResultSet rs = pst.executeQuery();
			boolean x = false;
			
			while(rs.next())
			{
				x = true;
				errMobile.setVisible(false);
				errID.setVisible(false);
				
				txtName.setText(rs.getString("CustomerName"));
				txtAddress.setText(rs.getString("Address"));
				txtArea.setText(rs.getString("Area"));
				txtCity.setText(rs.getString("City"));
				txtCQ.setText(rs.getString("CQ"));
				txtCP.setText(rs.getString("CP"));
				txtBQ.setText(rs.getString("BQ"));
				txtBP.setText(rs.getString("BP"));
				datepicker.setValue(rs.getDate("Date").toLocalDate());
			}
			if(x == false) {
				errMobile.setVisible(true);
				errID.setVisible(true);
			}
    	}
    	
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    }

    @FXML
    void doUpdate(ActionEvent event) {
    	if(txtMobile.getText().equals(""))
			errMobile.setVisible(true);
    	
    	if(txtID.getText().equals(""))
			errID.setVisible(true);
	
    	if(txtName.getText().equals(""))
    		errName.setVisible(true);
	
    	if(txtAddress.getText().equals(""))
    		errAddress.setVisible(true);
    	
    	if(txtArea.getText().equals(""))
    		errArea.setVisible(true);
	
    	if(txtCity.getText().equals(""))
    		errCity.setVisible(true);
	
    	if(datepicker.getValue() == null)
    		errDate.setVisible(true);
    	
    else
    {
    	LocalDate local = datepicker.getValue();
    	
    	try {
			pst = con.prepareStatement("update registration set CustomerName = ?, Address = ?, Area = ?, City = ?, CQ = ?, CP = ?, BQ = ?, BP = ?, Date = ? where Mobile = ?");
			pst.setString(10,txtMobile.getText());
			pst.setString(1,txtName.getText());
			pst.setString(2,txtAddress.getText());
			pst.setString(3,txtArea.getText());
			pst.setString(4,txtCity.getText());
			pst.setString(5,txtCQ.getText());
			pst.setString(6,txtCP.getText());
			pst.setString(7,txtBQ.getText());
			pst.setString(8,txtBP.getText());
			Date d = Date.valueOf(local);
			pst.setDate(9, d);
			pst.executeUpdate();
			btnUpdate.setText("Updated...");
		} 
    	
    	catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
    }

    @FXML
    void initialize() {
    	doConnection();
    	
    	txtCQ.setText("0.0");
    	txtCP.setText("0.0");
    	txtBQ.setText("0.0");
    	txtBP.setText("0.0");

    }
}
