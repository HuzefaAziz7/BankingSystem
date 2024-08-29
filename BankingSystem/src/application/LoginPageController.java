package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LoginPageController {

    public TextField TxtFieldNULPUsername;
    public TextField TxtFMobileNum;
    public TextField TxtFieldNULPEmail;
    public TextField TxtFieldUsername;
    
    @FXML
    private ChoiceBox<String> CBBranchType  = new ChoiceBox<String>();
    @FXML
    private ChoiceBox<String> CBAccountType = new ChoiceBox<String>();
    
    
    public PasswordField PwdFieldNULPConfirmPwd;
    public PasswordField PwdFieldNULPPassword;
    public PasswordField PwdFieldPassword;

    public TextArea TxtAreaAdress;
   
    public static Stage NewUserStage;

    public ImageView BankImage;

    public Label LblAddress;
    public Label LblBranch;
    public Label LblChooseAccount;
    public Label LblMobileNum;
    public Label LblNULP1;
    public Label LblNULPConfirmPwd;
    public Label LblNULPEmail;
    public Label LblNULPPassword;
    public Label LblNULPUsername;
    public Label LblForgotPwd;
    public Label LblPassword;
    public Label LblSignIn;
    public Label LblUsername;
    public Label LblResult;

    public static String DashboardUsername;
    public static String CustomerID;
    public static String AccountNumber;
    public static String DebitCardNum;
    public static String CVV;
    public static String ExpiryDate;

    public Button MyButton;
    public Button BtnNULP;

    @FXML
    void initialize() {
//        BasicPriorities();
    	 AccountTypes();
         BankBranch();
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
            Parent newUserPage = FXMLLoader.load(getClass().getResource("/Fxml_Files/NewUserPage.fxml"));
            Scene scene = new Scene(newUserPage, 850, 630);
            NewUserStage = new Stage();
            NewUserStage.setTitle("Capital Bank");
            NewUserStage.setScene(scene);
            NewUserStage.show();
            BankingSystemMain.primaryStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void BtnNULPaction(ActionEvent event) {
        String customerName = TxtFieldNULPUsername.getText();
        String newPassword = PwdFieldNULPPassword.getText();
        String confirmPassword = PwdFieldNULPConfirmPwd.getText();
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        String accountType = CBAccountType.getValue();
        String pin = null;
        String email = TxtFieldNULPEmail.getText();
        String mobileNum = TxtFMobileNum.getText();
        String address = TxtAreaAdress.getText();
        String branch = CBBranchType.getValue();
        ImpMethods.generateCustomerID(branch);
        ImpMethods.generateAccountNumber(accountType);
        ImpMethods.generateCardNum(accountType);
        String accountID = "ACC12345-SAV001";

        if (newPassword.equals(confirmPassword)) {
            ImpMethods.NewUserLogin(CustomerID, customerName, hashedPassword, accountID, accountType, AccountNumber, DebitCardNum, CVV, ExpiryDate, pin, email, mobileNum, address, branch);
            LblNULP1.setText("New Login Successful.. Please Wait");
        }
    }

    @FXML
    void handleButtonAction(ActionEvent event) {
        String username = TxtFieldUsername.getText();
        DashboardUsername = username;
        String password = PwdFieldPassword.getText();
        ImpMethods.ExistingUserLogin(username, password);

        if ("Pass".equals(ImpMethods.VerificationResult)) {
            LblResult.setText("Login Successful. Please Wait");
            try {
                Parent mainPage = FXMLLoader.load(getClass().getResource("/Fxml_Files/MainPage.fxml"));
                Scene scene = new Scene(mainPage, 850, 630);
                NewUserStage = new Stage();
                NewUserStage.setTitle("Capital Bank");
                NewUserStage.setScene(scene);
                NewUserStage.show();
                BankingSystemMain.primaryStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if ("Fail".equals(ImpMethods.VerificationResult)) {
            LblResult.setText("Login Failed. Please Try Again");
        }
    }

    void AccountTypes() {
        ArrayList<String> accountTypes = new ArrayList<>(Arrays.asList("Current Account", "Savings Account", "Youth Account"));
        CBAccountType.getItems().addAll(accountTypes);
    }

    void BankBranch() {
        ArrayList<String> branchTypes = new ArrayList<>(Arrays.asList("Pune", "Mumbai", "Jaipur", "Partapur"));
        CBBranchType.getItems().addAll(branchTypes);
    }
}
