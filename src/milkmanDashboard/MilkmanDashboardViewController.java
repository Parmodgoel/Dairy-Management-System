package milkmanDashboard;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MilkmanDashboardViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnBack;

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
    void doBill(ActionEvent event) {
    	try{
    		
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("generateBill/GenerateBillView.fxml")); 
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
    void doRegistration(ActionEvent event) {
    	try{
    		
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("customerRegistration/CustomerRegistrationView.fxml")); 
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
    void doRoutine(ActionEvent event) {
    	try{
    		
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("routineCustomer/RoutineCustomerView.fxml")); 
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

    }
}
