package application;

import java.sql.SQLException;
import java.util.Random;

import org.mindrot.jbcrypt.BCrypt;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class PasswordController { 
	@FXML
	private TextField TxtFieldFPCustomerID;
	private ImageView BankImage;
	private Pane FPOPEmailPane, FPOPMobileNumPane;
	private AnchorPane FPOptionPane, ForgotPwdPane;
	private Label LblEmailAddressFP, LblFPCustomerID, LblMainFP, LblMobileNumberFP, LblReqEmail, LblReqNumber;
	
	public static int Passkey; 
	public static String VerifyEmail;
	public int CustomerID;
	
    @FXML
    void initialize() {
    	ImpMethods.AdminDBConnection();
    } // initialize().
    
    @FXML
    void ReqEmail(MouseEvent event) {
//    	String x = new String(TxtFieldFPCustomerID.getText());
    	CustomerID = Integer.parseInt(TxtFieldFPCustomerID.getText());  
    	ForgotPassword(CustomerID);
//    	System.out.println("Email Sent");
    }

    @FXML
    void ReqMobileNumber(MouseEvent event) {
//    	CustomerID = Integer.parseInt(TxtFieldFPCustomerID.getText());  
    	ImpMethods.AdminDBConnection();
    	System.out.println("OTP Sent");
    }
    
    public void ForgotPassword(int CustomerID) {
    	String DBEmail = null;
    	try {
			ImpMethods.PSUpdate = ImpMethods.MyCon.prepareStatement("SELECT Email FROM ClientInfo WHERE CustomerID = ?");
			ImpMethods.PSUpdate.setInt(1, CustomerID);
	        ImpMethods.MyRS = ImpMethods.PSUpdate.executeQuery();
	        if (ImpMethods.MyRS.next()) {
	            DBEmail = ImpMethods.MyRS.getString("Email");
	            generatePasskey();
	            EmailController.ResetPasswordEmail(DBEmail, Passkey);
	        }
	        ImpMethods.PSUpdate.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Email not sent. Please try again.");
		}
    	
        
//            GUI.lblConfirmation.setText("Email/Username Incorrect.");
        
    } // Forgot Password.
    
    private void generatePasskey() {
        Random random = new Random();
        int min = 100000, max = 1000000;
        Passkey = random.nextInt(max - min + 1) + min;
    } // generatePasskey()
    
    public void ResetPassword(int CustomerID, int PasskeyRP, String NewPassword) {
        if (Passkey == PasskeyRP) {
            try {
                String HashedPassword = BCrypt.hashpw(NewPassword, BCrypt.gensalt());
                ImpMethods.PSUpdate = ImpMethods.MyCon.prepareStatement("UPDATE ClientInfo SET Password = ? WHERE CustomerID = ?");
                ImpMethods.PSUpdate.setString(1, HashedPassword);
                ImpMethods.PSUpdate.setInt(2, CustomerID);
                int rowsAffected = ImpMethods.PSUpdate.executeUpdate();
//                GUI.lblConfirmationRP.setText("Password Changed Successfully.");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("104");
            }
        } else {
            System.out.println("Passkey Incorrect.!");
        }
    } // ResetPassword()
    
} // Main Class.
