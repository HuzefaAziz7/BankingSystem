package application;


import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class MainController {

	public static Stage NewUserStage;

	@FXML
    public ImageView BankImage;
    public Label LblForgotPwd;
    public Label LblPassword;
    public Label LblSignIn;
    public Label LblUsername;
    public Label LblResult;
    private Button BtnNULP;
    public Button MyButton;
    private Label LblNULP1;
    private Label LblNULPConfirmPwd;
    private Label LblNULPEmail;
    private Label LblNULPPassword;
    private Label LblNULPUsername;
    private PasswordField PwdFieldNULPConfirmPwd;
    private PasswordField PwdFieldNULPPassword;
    public PasswordField PwdFieldPassword;
    public TextField TxtFieldUsername;
    private TextField TxtFieldNULPEmail;
    private TextField TxtFieldNULPUsername;

    
//  FXML Methods begin from here.
    
    @FXML
    void ForgotPwd(MouseEvent event) {
    	System.out.println("Forgot Pwd Button Working");
    } 
     
    @FXML
    void NewUserLogin(MouseEvent event) {

    	try {
    		Parent NewUserPage = FXMLLoader.load(getClass().getResource("/Fxml_Files/NewUserPage.fxml"));
    		Scene scene = new Scene(NewUserPage,700,620);
    		Stage NewUserStage = new Stage();
    		MainController.NewUserStage = NewUserStage ;
    		NewUserStage.setTitle("New User Page");
    		NewUserStage.setScene(scene);
    		NewUserStage.show();
    		BankingSystemMain.primaryStage.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void BtnNULPaction(ActionEvent event) {
    	
    	String NewUsername = new String(TxtFieldNULPUsername.getText()) ;
		String NewPassword = new String(PwdFieldNULPPassword.getText()) ;
		String ConfirmPassword = new String(PwdFieldNULPConfirmPwd.getText()) ;
		String Email = new String(TxtFieldNULPEmail.getText());
		
		if (NewPassword.equals(ConfirmPassword)) { 
			ImpMethods.NewUserLogin(NewUsername, NewPassword, Email);
			LblNULP1.setText("New Login Successful.. Please Wait");
		}
		
    }   
    
    @FXML
    void handleButtonAction(ActionEvent event) { // On Main Page.
    
    	String Username = new String (TxtFieldUsername.getText());
    	String Password = new String(PwdFieldPassword.getText());
    	ImpMethods.ExistingUserLogin(Username,Password);
    	if (ImpMethods.VerificationResult.equals("Pass")) {
        	LblResult.setText("Login Successful. Please Wait");
    	}    
    	else if (ImpMethods.VerificationResult.equals("Fail")){
    		LblResult.setText("Login Failed. Please Try Again");
    	} 

    }    
    
}



