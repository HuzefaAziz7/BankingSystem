package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.ImpMethods;

public class DB_Controller {
	
	static ImpMethods ImpMethods = new ImpMethods() ;
	 static CallableStatement MyCallStmt = null;	
	 static Connection MyCon = null;
	 static Statement MyStmt = null;	
	 static ResultSet MyRS = null;
	 static PreparedStatement PSUpdate = null;
	
	public static void Basic() {
		ImpMethods.ClientDBConnection();
		MyCallStmt = ImpMethods.MyCallStmt ;
		MyCon = ImpMethods.MyCon ;
		MyStmt = ImpMethods.MyStmt ;
		MyRS = ImpMethods.MyRS ;
		PSUpdate = ImpMethods.PSUpdate ;	
	} // COntructor.
	
	public static void DB_sendMoney(String Payer, String Payee, String AccNumber, int Amount,String Remarks) {
		Basic() ;
		DB_deductMoney(Payer,Amount,Payee) ;
		DB_addMoney(Payee,Amount,Payer);
	} // DB_sendMoney().
	
	public void DB_reqMoney(String Payer, int Amount, String Remarks) {
		
	} // DB_reqMoney().
	
	public static void DB_deductMoney(String Payer, int Amount, String Payee) {
		try {
	        PSUpdate = MyCon.prepareStatement("INSERT INTO " + Payer + " (Payee, Amount, Payer, TransStatus, TransType) VALUES (?,?,?,?,?)");
	        PSUpdate.setString(1, Payee);
	        PSUpdate.setInt(2, Amount);
	        PSUpdate.setString(3, Payer);
	        PSUpdate.setString(4, "Completed");
	        PSUpdate.setString(5, "Debit");
	        PSUpdate.executeUpdate(); // Execute the statement
//	        System.out.println("Money deducted from " + Payer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // DB_deductMoney().
	
	public static void DB_addMoney(String Payee, int Amount, String Payer) {
		try {
			PSUpdate = MyCon.prepareStatement("INSERT INTO " + Payee + " (Payee, Amount, Payer, TransStatus,TransType) VALUES (?,?,?,?,?)") ;
			PSUpdate.setString(1, Payee);
			PSUpdate.setInt(2, Amount);
			PSUpdate.setString(3, Payer);
			PSUpdate.setString(4, "Completed");
			PSUpdate.setString(5, "Credit");
			PSUpdate.executeUpdate();
//			System.out.println("Money added to " + Payee);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // DB_addMoney(). 
	
} // Class.