package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MainPageController {

    @FXML // Dashboard Pane.
    private AnchorPane DashboardPane;
    private Button BtnDashboard;
    private Label LblDB1;
    
    @FXML // Transaction Pane.
    private AnchorPane TransactionsPane;
    private Button BtnTransactions;
    private Label LblTrans1; 
    
    @FXML
    void initialize() {
		
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

}
