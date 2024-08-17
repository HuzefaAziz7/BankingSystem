// FXML Files connected to this controller : FrontLoginPage, NewUserPage, ForgotPwdPage.
package application;


import javafx.scene.control.TextArea;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginPageController {
	
	public TextField TxtFieldNULPUsername;
	public  TextField TxtFMobileNum;
	public  TextField TxtFieldNULPEmail;
	public  TextField TxtFieldUsername;
	
	public  PasswordField PwdFieldNULPConfirmPwd;
	public  PasswordField PwdFieldNULPPassword;
	public  PasswordField PwdFieldPassword;

	public  TextArea TxtAreaAdress;

	public static Stage NewUserStage;
	
	public  ImageView BankImage;
	
	public ChoiceBox<String> CBAccountType;
	public ArrayList<String> AccountTypes = new ArrayList<>(Arrays.asList("Current Account", "Savings Account", "Youth Account"));
	
	public ChoiceBox<String> CBBranchType;
	public ArrayList<String> BranchTypes = new ArrayList<>(Arrays.asList("Pune", "Mumbai", "Jaipur","Partapur"));

	public  Label LblAddress;
	public  Label LblBranch;
	public  Label LblChooseAccount;
	public  Label LblMobileNum;
	public  Label LblNULP1;
	public  Label LblNULPConfirmPwd;
	public  Label LblNULPEmail;
	public  Label LblNULPPassword;
	public  Label LblNULPUsername;
	public  Label LblForgotPwd;
	public  Label LblPassword;
	public  Label LblSignIn;
	public  Label LblUsername;
	public  Label LblResult;
	
	public static String DashboardUsername;

	public Button MyButton;
	public  Button BtnNULP;

//  FXML Methods begin from here.
    
	@FXML 
	void initialize() {
		BasicPriorities();
	}
	
	void BasicPriorities() {
		AccountTypes();
		BankBranch();
	}
    @FXML
    void ForgotPwd(MouseEvent event) {
    	System.out.println("Forgot Pwd Button Working");
    } 
     
    @FXML
    void NewUserLogin(MouseEvent event) {

    	try {
    		Parent NewUserPage = FXMLLoader.load(getClass().getResource("/Fxml_Files/NewUserPage.fxml"));
    		Scene scene = new Scene(NewUserPage,850, 630);
    		Stage NewUserStage = new Stage();
    		LoginPageController.NewUserStage = NewUserStage ;
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
    	
    	String Fullname = new String(TxtFieldNULPUsername.getText()) ;
		String NewPassword = new String(PwdFieldNULPPassword.getText()) ;
		String ConfirmPassword = new String(PwdFieldNULPConfirmPwd.getText()) ;
		String Email = new String(TxtFieldNULPEmail.getText());
		String SelectedAcc = CBAccountType.getValue();
		int MobileNum = Integer.parseInt(TxtFMobileNum.getText());
		String Address = new String(TxtAreaAdress.getText());
		String SelectedBranch = CBBranchType.getValue();
		
		
		/*
		if (NewPassword.equals(ConfirmPassword)) { 
			ImpMethods.NewUserLogin(Fullname, NewPassword, Email);
			LblNULP1.setText("New Login Successful.. Please Wait");
		} */
		
    }   
    
    @FXML
    void handleButtonAction(ActionEvent event) { // On Main Page.
    
    	String Username = new String (TxtFieldUsername.getText());
    	DashboardUsername = Username;
    	String Password = new String(PwdFieldPassword.getText());
    	ImpMethods.ExistingUserLogin(Username,Password);
    	if (ImpMethods.VerificationResult.equals("Pass")) {
        	LblResult.setText("Login Successful. Please Wait");
    	}    
    	else if (ImpMethods.VerificationResult.equals("Fail")){
    		LblResult.setText("Login Failed. Please Try Again");
    	} 
    }    
    
	void AccountTypes() {		
		CBAccountType.getItems().addAll(AccountTypes);
	}
	
	void BankBranch() {
		CBBranchType.getItems().addAll(BranchTypes);
	}
    
}



