package application;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Controller {

	@FXML
    public ImageView BankImage;

    @FXML
    public Label LblForgotPwd;

    @FXML
    public Label LblPassword;

    @FXML
    public Label LblSignIn;
    public Label LblUsername;

    @FXML
    public Button MyButton;

    @FXML
    public PasswordField PwdFieldPassword;

    @FXML
    public TextField TxtFieldUsername;
    
    
    @FXML
    void ForgotPwd(MouseEvent event) {
    	System.out.println("Forgot Pwd Button Working");
    } 
     
    @FXML
    void NewUserLogin(MouseEvent event) {
    	System.out.println("Sign In Button Working");
    }
 
    @FXML
    void handleButtonAction(ActionEvent event) {
    	System.out.println("Working");    	
    } 
}



