package adminLogin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class AdminLoginViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField pass;

    @FXML
    private Button btnProceed;

    @FXML
    void doProceed(ActionEvent event) {
    	if(pass.getText().equals("admin"))
    	{
    		try{
        		Scene scene1=(Scene)btnProceed.getScene();
    			scene1.getWindow().hide();
    			
    			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("adminDashboard/AdminDashboardView.fxml")); 
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
    }

    @FXML
    void initialize() {
        

    }
}
