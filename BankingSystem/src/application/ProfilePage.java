package application ;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ProfilePage {
	
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
		QPUsername.setText("HuzefaAziz");
		QPPhoneNumber.setText("9562228078");
		QPEmail.setText("huzefa@gmail.com");
		QPCustomerID.setText("1234567");
		QPAddress.setText("Yewaliwadi, Pune");
	} // QP_Info(). Quick Pane Personal Information. 
	
	public void Personal_Info() {
		
		fullName_txtfield.setText("Hozefa Hatim Aziz");
		fatherName_txtfield.setText("Hatim");
		motherName_txtfield.setText("Sakina");
		Nationality_txtfield.setText("Indian");
		Occupation_txtfield.setText("Student");
		DOB_txtfield.setText("11/07/2003");
		Gender_txtfield.setText("Male");
		MaritalStatus_txtfield.setText("Not Married") ;
	} // Personal_Info().
	
	@FXML
    void OpenDashboard(MouseEvent event) {     	    	  // ERROR....!!!!!!
		MainPageController.DashboardPane.toFront();
		MainPageController.DashboardPane.setVisible(true);
    } // OpenDashboard().
	
} // class.