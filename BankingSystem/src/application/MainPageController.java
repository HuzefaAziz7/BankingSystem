package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import networking.ClientServer;
import networking.TransactionsController;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class MainPageController {
	
    static CallableStatement MyCallStmt = null;
    static Connection MyCon = null;
    static Statement MyStmt = null;
    static ResultSet MyRS = null;
    static PreparedStatement PSUpdate = null ;

    @FXML
    private ChoiceBox<?> CBChooseAcc;
    @FXML
    private ChoiceBox<?> CBChooseAcc1;
    @FXML
    private AnchorPane CardsPane;
    @FXML
    public AnchorPane DashboardPane;
    @FXML
    private AnchorPane DepositPane;
    @FXML
    private AnchorPane EnterPINPane;
    @FXML
    private AnchorPane HelpPane;
    @FXML
    private AnchorPane HistoryPane;
    @FXML
    private ImageView ImgBank;
    @FXML
    private ImageView ImgFailed;
    @FXML
    private ImageView ImgSuccessful;
    @FXML
    private ImageView ToProfile ;
    @FXML
    private Pane LatestTransPane;
    @FXML
    private Label LblAccountType;
    @FXML
    private Label LblBalanceText;
    @FXML
    private Label LblHello;
    @FXML
    private static String Name = "CrisRonaldo"; // LoginPageController.DashboardUsername ;
    
    public Label LblName;
    @FXML
    public Label LblDateTime;
    @FXML
    private Label LblTotalCredit;
    @FXML
    private Label LblTotalDebit;

    @FXML
    private Label LblPay;
    @FXML
    private Label LblRequest;
    @FXML
    private Label LblTransResult;
    @FXML
    private Label LblYourBalance;
    @FXML
    private Label LblYourBalance1;
    @FXML
    private SplitPane Main;
    @FXML
    private StackPane MainDisplayPane;
    @FXML
    private AnchorPane OffersPane;
    @FXML
    private StackPane PINPane;
    @FXML
    private Pane PayReqPane;
    @FXML
    private AnchorPane SettingsPane;
    @FXML
    private AnchorPane SideBarPane;
    @FXML
    private ProgressIndicator TransProgree;
    @FXML
    private AnchorPane TransResultPane;
    @FXML
    private AnchorPane TransactionsPane;
    public Button BtnSendAmount;
    @FXML
    private AnchorPane UpperDashboardPane;
    @FXML
    private AnchorPane WithdrawPane;
    private ImageView ToDashboard;

    @FXML
    public Label LblBalanceAmount = new Label();
    @FXML
    private TextField DPtxtFieldAccNumber;
    @FXML
    private TextField DPtxtFieldPayeeName;
    @FXML
    private TextField DPtxtFieldRemarks;
    public ChoiceBox<String> CBAccountType;
    @FXML
    private TextField DPtxtFieldSendAmount;
    @FXML
    private TextField WPAmountField;
    @FXML
    private TextField WPPayerField;
    @FXML
    private TextField WPRemarksField;
    
    
    public MainPageController() {
    	
    } // Contructor.
    
    @FXML
    void initialize() {
        BasicPriorities();
    }

    @FXML
    void OpenDashboard(MouseEvent event) { 
        DashboardPane.toFront();
        DashboardPane.setVisible(true);
        TransactionsPane.setVisible(false);
    }

    @FXML
    void OpenTransactions(MouseEvent event) {
        TransactionsPane.toFront();
        TransactionsPane.setVisible(true);
        DashboardPane.setVisible(false);
    }
    
    

    void BasicPriorities() {
        LblName.setText("Hello, " + Name);
        CBAccountType.getItems().addAll("Current Account : •••• 7610", "Savings Account : •••• 8243");
        CBAccountType.setOnAction(this::SelectedAccount);
        String balance = String.valueOf(bankBalance());
        LblBalanceAmount.setText(balance);
        DateTime();
        LblTotalCredit.setText(String.valueOf(ImpMethods.totalCredit(Name)));
        LblTotalDebit.setText(String.valueOf(ImpMethods.totalDebit(Name)));
    }

    void DateTime() {
        LocalDate Date = LocalDate.now();
        LblDateTime.setText(Date.toString());
    }

    void SelectedAccount(ActionEvent event) {
        String MyAccChoice = CBAccountType.getValue();
        LblAccountType.setText(MyAccChoice);
    }

    public static int bankBalance() {
    	int result = 0 ;
    	
        ImpMethods.ClientDBConnection();

        try {
            if (Name == null || Name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name parameter is null or empty");
            }

            ImpMethods.MyCallStmt = ImpMethods.MyCon.prepareCall("{call getBankBalance(?)}");
            ImpMethods.MyCallStmt.setString(1, Name);

            MyRS = ImpMethods.MyCallStmt.executeQuery();

            if (MyRS.next()) {
                result = MyRS.getInt("BankBalance");
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } catch (Exception exc) {
            exc.printStackTrace();
        }


        
        return result;
    }

    void Cards() {
        // Cards logic here
    }

    void LatestTransactions() {
        // Latest transactions logic here
    }

    void Offers() {
        // Offers logic here
    }

    public void sendMoney(ActionEvent event) {
        String Payee = DPtxtFieldPayeeName.getText();
        String AccNumber = DPtxtFieldAccNumber.getText();
        String xAmount = DPtxtFieldSendAmount.getText();
        String Remarks = DPtxtFieldRemarks.getText();
        if (Payee != null || xAmount != null || AccNumber != null) {
            int Amount = Integer.parseInt(xAmount);
            ClientServer.sendMoney(Payee,Amount,Remarks); // AccNumber.
        } else {
            System.out.println("Please fill all the fields properly.");
        }
    }

    public void requestMoney(ActionEvent event) {
        String Payer = WPPayerField.getText();
        String xAmount = WPAmountField.getText();
        String Remarks = WPRemarksField.getText();
        if (Payer != null || xAmount != null) {
            int Amount = Integer.parseInt(xAmount);
            ClientServer.requestMoney(Payer,Amount,Remarks);
        } else {
            System.out.println("Please fill all the fields properly.");
        }
    }

    public void viaLinkMoney(ActionEvent event) {
        // viaLinkMoney logic here
    }
    
    @FXML
    void refreshDashboard(MouseEvent event) {

    }
    
    public void ToProfilePage() {
		try {
            Parent newUserPage = FXMLLoader.load(getClass().getResource("/Fxml_Files/ProfilePage.fxml"));
            Scene scene = new Scene(newUserPage, 850, 630);
            Stage NewUserStage = new Stage();
            NewUserStage.setTitle("Capital Bank");
            NewUserStage.setScene(scene);
            NewUserStage.show();
            BankingSystemMain.primaryStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	} // ToProfilePage().
    
} // CLASS.
