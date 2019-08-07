package allCustomers;

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
import allCustomers.AllCustomerBean;

public class AllCustomersViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<AllCustomerBean> tableview;
    ObservableList<AllCustomerBean> list;
    
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
            String text="Mobile,Customer Name,Address,Area,City,CQ,CP,BQ,BP,Date Of Start\n";
            writer.write(text);
            for (AllCustomerBean p : list)
            {
				text = p.getMobile()+ "," + p.getCustomerName()+ "," + p.getAddress()+ "," + p.getArea()+ "," + p.getCity()+ "," + p.getCQ()+ "," + p.getCP()+ "," + p.getBQ()+ "," + p.getBP()+ "," + p.getDate()+"\n";
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
    	
    	TableColumn<AllCustomerBean, String> MilkmanIDCol = new TableColumn<AllCustomerBean, String>("MilkmanID");
	MilkmanIDCol.setCellValueFactory(new PropertyValueFactory<>("MilkmanID"));
	
	TableColumn<AllCustomerBean, String> MobileCol = new TableColumn<AllCustomerBean, String>("Mobile");
	MobileCol.setCellValueFactory(new PropertyValueFactory<>("Mobile"));
	
	TableColumn<AllCustomerBean, String> CustomerNameCol = new TableColumn<AllCustomerBean, String>("Customer Name");
	CustomerNameCol.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
	
	TableColumn<AllCustomerBean, String> AddressCol = new TableColumn<AllCustomerBean, String>("Address");
	AddressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));

	TableColumn<AllCustomerBean, String> AreaCol = new TableColumn<AllCustomerBean, String>("Area");
	AreaCol.setCellValueFactory(new PropertyValueFactory<>("Area"));
	
	TableColumn<AllCustomerBean, String> CityCol = new TableColumn<AllCustomerBean, String>("City");
	CityCol.setCellValueFactory(new PropertyValueFactory<>("City"));
	
	TableColumn<AllCustomerBean, String> CQCol = new TableColumn<AllCustomerBean, String>("CQ");
	CQCol.setCellValueFactory(new PropertyValueFactory<>("CQ"));
	
	TableColumn<AllCustomerBean, String> CPCol = new TableColumn<AllCustomerBean, String>("CP");
	CPCol.setCellValueFactory(new PropertyValueFactory<>("CP"));
	
	TableColumn<AllCustomerBean, String> BQCol = new TableColumn<AllCustomerBean, String>("BQ");
	BQCol.setCellValueFactory(new PropertyValueFactory<>("BQ"));
	
	TableColumn<AllCustomerBean, String> BPCol = new TableColumn<AllCustomerBean, String>("BP");
	BPCol.setCellValueFactory(new PropertyValueFactory<>("BP"));
	
	TableColumn<AllCustomerBean, String> DateCol = new TableColumn<AllCustomerBean, String>("Date Of Start");
	DateCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
	
	tableview.getColumns().clear();
	tableview.getColumns().addAll(MilkmanIDCol,MobileCol,CustomerNameCol,AddressCol,AreaCol,CityCol,CQCol,CPCol,BQCol,BPCol,DateCol);
	
	
	list = FXCollections.observableArrayList();
		
		try {
			  pst=con.prepareStatement("select * from registration inner join milkmanregistration on registration.MilkmanID = milkmanregistration.MilkmanID order by milkmanregistration.MilkmanID");
			 
			ResultSet rs=  pst.executeQuery();
			while(rs.next())
			{
				String MilkmanID = rs.getString("milkmanregistration.MilkmanID");
				String Mobile = rs.getString("registration.Mobile");
				String CustomerName = rs.getString("registration.CustomerName");
				String Address = rs.getString("registration.Address");
				String Area = rs.getString("registration.Area");
				String City = rs.getString("registration.City");
				String CQ = rs.getString("registration.CQ");
				String CP = rs.getString("registration.CP");
				String BQ = rs.getString("registration.BQ");
				String BP = rs.getString("registration.BP");
				String Date = rs.getString("registration.Date");
				
				AllCustomerBean bean1 = new AllCustomerBean(MilkmanID,Mobile, CustomerName, Address, Area, City, CQ, CP, BQ, BP, Date);
				list.add(bean1);
			}
			
			} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		tableview.setItems(list);

    }
}
