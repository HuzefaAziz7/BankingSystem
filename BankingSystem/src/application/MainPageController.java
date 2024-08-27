package application;

import javafx.event.ActionEvent;

import java.time.LocalDate;
import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter; 
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import networking.ClientServer;

public class MainPageController {

	@FXML
	private Button BtnCards;	
	@FXML

	private Label BtnSettings;
	@FXML
	private Button BtnHelp;
	@FXML
	private Button BtnHistory;
	@FXML
	private Button BtnNotifications;
	@FXML
	private Button BtnProfile;
	
//	private Button BtnSettings;
	@FXML
	private Button BtnTransactions;
	@FXML
	private ChoiceBox<?> CBChooseAcc;
	@FXML
	private ChoiceBox<?> CBChooseAcc1;
	@FXML
	private AnchorPane CardsPane;
	@FXML
	private AnchorPane DashboardPane;
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
	private Pane LatestTransPane;
	@FXML
	private Label LblAccountType;	
	@FXML
	private Label LblBalanceText;
	@FXML
	private Label LblHello;
	@FXML
	private String Name = "HuzefaAziz"; //MainController.DashboardUsername // ERROR
	public Label LblName;
	@FXML
	public Label LblDateTime;
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
	public ChoiceBox<String> CBAccountType;
	public static Label LblBalanceAmount;
	@FXML
    private TextField DPtxtFieldAccNumber;

    @FXML
    private TextField DPtxtFieldPayeeName;

    @FXML
    private TextField DPtxtFieldRemarks;

    @FXML
    private TextField DPtxtFieldSendAmount;

	
	

    
    @FXML
    void initialize() {
    	BasicPriorities();
    	CBAccountType.getItems().addAll("Current Account : •••• 7610", "Savings Account : •••• 8243");
    	CBAccountType.setOnAction(this::SelectedAccount);
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
    	DateTime(); 
    	// LblBalanceAmount.setText("1000");
    	// ImpMethods.BankBalance();
    	
    } // BasicPriorities(). For Things which must be done before anything.
   
    void DateTime() {
    	LocalDate Date = LocalDate.now(); 
    	LblDateTime.setText(Date.toString());      
    } // DateTime().
    
    void SelectedAccount(ActionEvent event) {
    	String MyAccChoice = CBAccountType.getValue();
    	LblAccountType.setText(MyAccChoice);
//    	System.out.println(MyAccChoice);
    } // SelectedAccount().
    
    void BankBalance() {
    
    } // BankBalance().
    
    void Cards() {
    	
    } // Cards().
    
    void LatestTransactions() {
    	
    } // LatestTransactions().
    
    void Offers() {
    	
    } // Offers().
    
    public void SendMoney(ActionEvent event) {
    	
    	String Payee = new String(DPtxtFieldPayeeName.getText());
    	String AccNumber = new String(DPtxtFieldAccNumber.getText());
    	int Amount = Integer.parseInt(new String(DPtxtFieldSendAmount.getText()));
    	String Remarks = new String(DPtxtFieldRemarks.getText());
    	TransactionsController.sendMoney(Payee,AccNumber,Amount,Remarks);
    	
    } //SendMoney().
    
    public void requestMoney() {
    	
    } // requestMoney().
    
    public void viaLinkMoney() {
    	
    } // viaLinkMoney().
    
    
    
    
    
    
}
