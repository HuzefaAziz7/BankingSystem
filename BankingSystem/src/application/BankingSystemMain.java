package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import networking.AdminServer;


public class BankingSystemMain extends Application {
	public static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BankingSystemMain.primaryStage = primaryStage;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/FrontLoginPage.fxml")); // /Fxml_Files
			Parent root = loader.load(); 
			Scene scene = new Scene(root,850, 630);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Capital Bank");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
	    ImpMethods.AdminDBConnection();
	    
	    Thread adminServerThread = new Thread(() -> {
	        AdminServer.main(args);
	    });
	    adminServerThread.start();
	    
	    
	   
	    launch(args);
	   
	    

        
	}
}

// Scene Dimensions : 850, 630.
