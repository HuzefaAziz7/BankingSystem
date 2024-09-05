package application ;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ProfilePage {
	
	MainPageController MainPageController = new MainPageController() ;
	
	@FXML
	private Label QPAddress;
	@FXML
	private Label QPCustomerID;
	@FXML
	private Label QPEmail;
	@FXML
	private Label QPPhoneNumber;
	@FXML
	private Label QPUsername;
	@FXML
	private TextField fatherName_txtfield;
	@FXML
	private TextField fullName_txtfield;
	@FXML
	private TextField motherName_txtfield;
	@FXML
	private TextField MaritalStatus_txtfield;
	@FXML
	private TextField Nationality_txtfield;
	@FXML
	private TextField Occupation_txtfield;
	@FXML
	private TextField DOB_txtfield;
	@FXML
	private TextField Gender_txtfield;


    @FXML
    void initialize() {
    	BasicPriorities();
    }
    
    void BasicPriorities() {
    	QP_Info();
    	Personal_Info() ;
    } //BasicPriorities().
    
	public void QP_Info() {  
		
		QPUsername.setText("CrisRonaldo");
		QPPhoneNumber.setText("9562228078");
		QPEmail.setText("ronaldo@gmail.com");
		QPCustomerID.setText("48502993");
		QPAddress.setText("New York, USA");
		
	} // QP_Info(). Quick Pane Personal Information. 
	
	public void Personal_Info() {
		
		fullName_txtfield.setText("Cris Ronaldo");
		fatherName_txtfield.setText("John");
		motherName_txtfield.setText("Julia");
		Nationality_txtfield.setText("American");
		Occupation_txtfield.setText("Student");
		DOB_txtfield.setText("23/03/2000");
		Gender_txtfield.setText("Male");
		MaritalStatus_txtfield.setText("Not Married") ;
		
	} // Personal_Info(). Personal Info Tab.
	
	@FXML
    void OpenDashboard(MouseEvent event) {     	    	  // ERROR....!!!!!!
//		MainPageController.DashboardPane.toFront();
		MainPageController.DashboardPane.setVisible(true);
    } // OpenDashboard().
	
	@FXML
    public void DeleteAcc(MouseEvent event) {
    	System.out.println("Account Deleted.");
    }
} // class.