package allMilkmen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import customerRegistration.data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

public class AllMilkmenViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<MilkmanBean> tableview;
    ObservableList<MilkmanBean> list;

    @FXML
    private Label labelMessage;
    
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
    void doExport(ActionEvent event) {
    	try {
			writeExcel();
			labelMessage.setText("Exported to excel..");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    }
    
    public void writeExcel() throws Exception {
        Writer writer = null;
        try {
        	FileChooser chooser=new FileChooser();
	    	
        	chooser.setTitle("Select Path:");
        	
        	chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Files", "*.*")
                    
                );
        	 File file=chooser.showSaveDialog(null);
        	String filePath=file.getAbsolutePath();
        	if(!(filePath.endsWith(".csv")||filePath.endsWith(".CSV")))
        	{
        		labelMessage.setText("file name should have .csv extension");
        		return;
        	}
        	 file = new File(filePath);
        	 
        	 
        	 
            writer = new BufferedWriter(new FileWriter(file));
            String text="MilkmanID,MilkmanName,Mobile,Address,Salary\n";
            writer.write(text);
            for (MilkmanBean p : list)
            {
				text = p.getMilkmanID()+ "," + p.getMilkmanName()+ "," + p.getMobile()+ "," + p.getAddress()+ "," + p.getSalary()+"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            writer.flush();
             writer.close();
        }
    }

    @SuppressWarnings("unchecked")
	@FXML
    void initialize() {
       doConnection();
       
       TableColumn<MilkmanBean, String> MilkmanIDCol = new TableColumn<MilkmanBean, String>("MilkmanID");
       MilkmanIDCol.setCellValueFactory(new PropertyValueFactory<>("MilkmanID"));
       
       TableColumn<MilkmanBean, String> MilkmanNameCol = new TableColumn<MilkmanBean, String>("MilkmanName");
       MilkmanNameCol.setCellValueFactory(new PropertyValueFactory<>("MilkmanName"));
       
       TableColumn<MilkmanBean, String> MobileCol = new TableColumn<MilkmanBean, String>("Mobile");
       MobileCol.setCellValueFactory(new PropertyValueFactory<>("Mobile"));
       
       TableColumn<MilkmanBean, String> AddressCol = new TableColumn<MilkmanBean, String>("Address");
       AddressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
       
       TableColumn<MilkmanBean, String> SalaryCol = new TableColumn<MilkmanBean, String>("Salary");
       SalaryCol.setCellValueFactory(new PropertyValueFactory<>("Salary"));
       
       tableview.getColumns().clear();
   	   tableview.getColumns().addAll(MilkmanIDCol,MilkmanNameCol,MobileCol,AddressCol,SalaryCol);
   	
   	
   	   list = FXCollections.observableArrayList();
  		
  		try {
  			  pst=con.prepareStatement("select * from milkmanregistration");
  			 
  			ResultSet rs=  pst.executeQuery();
  			while(rs.next())
  			{
  				String MilkmanID = rs.getString("MilkmanID");
  				String MilkmanName = rs.getString("MilkmanName");
  				String Mobile = rs.getString("Mobile");
  				String Address = rs.getString("Address");
  				String Salary = rs.getString("Salary");
  				
  				MilkmanBean bean = new MilkmanBean(MilkmanID, MilkmanName, Mobile, Address, Salary);
  				list.add(bean);
  			}
  			
  			} 
  		catch (SQLException e) 
  		{
  			
  			e.printStackTrace();
  		}
  		
  		tableview.setItems(list);
    }
}
