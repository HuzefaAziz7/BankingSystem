package networking;

import java.sql.*;

import application.ImpMethods;
import application.MainPageController;
import networking.ClientServer;

public class TransactionsController {
	
	static CallableStatement MyCallStmt = null;
    static Connection MyCon = null;
    static Statement MyStmt = null;
    static ResultSet MyRS = null;
    static PreparedStatement PSUpdate = null;
	
    static boolean checkTrans(int amount, String Payee) {
		boolean result = false ;
		
		if (checkBalance(amount)) {
			System.out.println("Sufficient amount.");
			if (checkPayeeDB(Payee)) {
				System.out.println("Payee found.");
				result = true ;
			} else {
				System.out.println("Payee not found");
				result = false ;
			} 
			
		} else {
			System.out.println("Insufficient amount.");
		}
		return result ; 
	} // trans 
    
	static public void sendMoney(String Payee, String AccNumber, int Amount,String Remarks) {
    	ClientServer.sendMoney(Payee,Amount,Remarks); // AccNumber.
	} // sendMoney().
	
	static public void requestMoney(String Payer, int Amount, String Remarks) {
    	ClientServer.requestMoney(Payer,Amount,Remarks);
	} // sendMoney().
	
	public static boolean checkBalance(int amount) { // Check if Payer has the required amount. 
		boolean result = false ;
		int balance = MainPageController.bankBalance();
		if (balance >= amount) {
			result = true ;
		}
		return result;
	} // checkBalance().
	
	public static boolean checkPayeeDB(String CustomerName) { // Check if Payee exists in the database;
		  ImpMethods.AdminDBConnection();
		  boolean result = false ;
		  try {
			  ImpMethods.PSUpdate = ImpMethods.MyCon.prepareStatement("SELECT CustomerID FROM ClientInfo WHERE CustomerName = ?");
			  ImpMethods.PSUpdate.setString(1, CustomerName);
			  ImpMethods.MyRS = ImpMethods.PSUpdate.executeQuery();
			  	result = ImpMethods.MyRS.next() ;
			  	ImpMethods.PSUpdate.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return result;
	  } // checkPayeeDB().
	 
} // TransactionsController.