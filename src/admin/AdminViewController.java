package admin;

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

public class AdminViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnMilkman;
    
    @FXML
    private Button btnClose;

    @FXML
    void doAdmin(ActionEvent event) 
    {
    		try{
    			Scene scene1=(Scene)btnAdmin.getScene();
    			scene1.getWindow().hide();
    			
    			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("adminLogin/AdminLoginView.fxml")); 
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
    void doMilkman(ActionEvent event) 
    {
    	try{
    		
			Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("milkmanDashboard/MilkmanDashboardView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			
			Scene scene1=(Scene)btnMilkman.getScene();
			scene1.getWindow().hide();
			
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
    			System.exit(1);
    }

    @FXML
    void initialize() {
        

    }
}
