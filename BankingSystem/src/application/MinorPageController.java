package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MinorPageController {

	private ImageView BankImage;
	private Pane FPOPEmailPane, FPOPMobileNumPane;
	private AnchorPane FPOptionPane, ForgotPwdPane;
	private Label LblEmailAddressFP, LblFPCustomerID, LblMainFP, LblMobileNumberFP, LblReqEmail, LblReqNumber;
	private TextField TxtFieldFPCustomerID;


    @FXML
    void ReqEmail(MouseEvent event) {
    	System.out.println("Email Sent");
    }

    @FXML
    void ReqMobileNumber(MouseEvent event) {
    	System.out.println("OTP Sent");
    }

}
