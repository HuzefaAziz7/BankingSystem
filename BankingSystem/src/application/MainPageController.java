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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MainPageController {

	@FXML
	private Button BtnCards;
	public ChoiceBox<String> CBAccountType;
	@FXML
	private Button BtnDashboard;
	@FXML
	private Button BtnHelp;
	@FXML
	private Button BtnHistory;
	@FXML
	private Button BtnNotifications;
	@FXML
	private Button BtnProfile;
	@FXML
	private Button BtnSettings;
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
	private Label LblBalanceAmount;
	@FXML
	private Label LblBalanceText;
	@FXML
	private Label LblHello;
	@FXML
	private String Name = "HuzefaAziz"; //MainController.DashboardUsername
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
	@FXML
	private AnchorPane UpperDashboardPane;
	@FXML
	private AnchorPane WithdrawPane;
	

    
    @FXML
    void initialize() {
    	BasicPriorities();
    	CBAccountType.getItems().addAll("Current Account : •••• 7610", "Savings Account : •••• 8243");
    	CBAccountType.setOnAction(this::SelectedAccount);
    }
    
    @FXML
    void OpenDashboard(ActionEvent event) {
		 DashboardPane.toFront();
		 DashboardPane.setVisible(true);
	     TransactionsPane.setVisible(false);
    }

    @FXML
    void OpenTransactions(ActionEvent event) {
		 TransactionsPane.toFront();
		 TransactionsPane.setVisible(true);
	     DashboardPane.setVisible(false);
    }
    
    void BasicPriorities() {
    	LblName.setText("Hello, " + Name);
    	DateTime(); 	
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
}
