// Important Methods.
package application;

import java.util.Random;
import java.util.Scanner ;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Year;

public class ImpMethods {
	 
	 static CallableStatement MyCallStmt = null ;
	 static Connection MyCon = null ;
	 static Statement MyStmt = null ; 
	 static ResultSet MyRS = null ;
	 static PreparedStatement PSUpdate = null ; 
	 static String VerificationResult = null; // Used in ExistingUserLogin().
	 
	static void Display() {
		System.out.println("Connection Successful");
	}
	
	public static void AdminDBConnection() {

	  	{ try {
			
	  		String dbUrl = "jdbc:mysql://localhost:3306/Admin" ;
	  		String usernamedb = "BankingSystem" ;
	  		String passworddb = "bank" ;
			
//	  		Get Connection to Database 
	  		MyCon = DriverManager.getConnection(dbUrl, usernamedb , passworddb); 
			
//	  		Create A Statement 
	  		MyStmt = MyCon.createStatement();
	  		
//	  		System.out.println("Connection to Admin Established");
	  	} // Try.
		
	  	catch (Exception exc) {
	  		exc.printStackTrace(); 
	  	}  } // Catch, JDBC Connection.

	  } // DBConnection()
	
	public static void ClientDBConnection() {

	  	{ try {
			
	  		String dbUrl = "jdbc:mysql://localhost:3306/Client" ;
	  		String usernamedb = "BankingSystem" ;
	  		String passworddb = "bank" ;
			
//	  		Get Connection to Database 
	  		MyCon = DriverManager.getConnection(dbUrl, usernamedb , passworddb); 
			
//	  		Create A Statement 
	  		MyStmt = MyCon.createStatement();
	  		
	  		System.out.println("Connection to Client Established");
	  	} // Try.
		
	  	catch (Exception exc) {
	  		exc.printStackTrace(); 
	  	}  } // Catch, JDBC Connection.

	  } // DBConnection()
	
	static public void NewUserLogin(String CustomerID, String CustomerName, String Password, String AccountType, String AccountNumber, String DebitCardNum, String PIN, String Email, String MobileNum, String Address, String Branch) {
    	AdminDBConnection();
        try {
            // Prepared Statement for Inserting the Username and HashedPassword into DB.
        	String[] Info = { CustomerID,  CustomerName,  Password,  AccountType,  AccountNumber,  DebitCardNum,  PIN = null,  Email,  MobileNum,  Address,  Branch};
        	
        	PSUpdate = MyCon.prepareStatement("INSERT INTO ClientInfo(CustomerID,CustomerName,Password,AccountType,AccountNumber,DebitCardNum,PIN,Email,MobileNumber,Address,Branch) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            
            for (int i = 0; i < Info.length; i++) {
//                System.out.println((i + 1) + ", " + Info[i]);
            	if (i == 0 || i == 8) {
            	    PSUpdate.setInt((i + 1), Integer.parseInt(Info[i]));
            	}
            	else {
            		PSUpdate.setString((i + 1) , Info[i]);
            	} 	
            }
            int rowsAffected = PSUpdate.executeUpdate();
            PSUpdate.close();
            System.out.print("Done");
        } catch (Exception exc) {
            exc.printStackTrace();
        } // Catch.
    } // NewUserLogin Method.
	
	static public void ExistingUserLogin(String Username, String Password) {
        String hashedpassword = null;

        try {
            PSUpdate = MyCon.prepareStatement("SELECT Username,UserPassword FROM IdInfo WHERE Username = ?");
            PSUpdate.setString(1, Username);
            MyRS = PSUpdate.executeQuery();
            if (MyRS.next()) {
                hashedpassword = MyRS.getString("UserPassword");
            }
            boolean matched = BCrypt.checkpw(Password, hashedpassword);
            if (matched) {
            	VerificationResult = "Pass";
            } else {
                VerificationResult = "Fail";
            }
            PSUpdate.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        } // Catch.
    } // ExistingUserLogin()
	

	static public void generateCustomerID(String Branch) {
		Random rand = new Random();
		int FPart ;
		
		if (Branch == "Pune") {
			FPart = 11 ; 
		}
		else if (Branch == "Mumbai") {
			FPart = 22 ; 
		}
		else if (Branch == "Jaipur") {
			FPart = 33 ; 
		}
		else if (Branch == "Partapur") {
			FPart = 44 ; 
		}
		else {
			FPart = 55 ; 
		}
		
		int SPart = rand.nextInt(10000,99999) ;
		
		LoginPageController.CustomerID  = String.valueOf(FPart) + String.valueOf(SPart);
		  
	} // generateCustomerID().
	
	static public void generateAccountNumber(String AccType) {
		Random rand = new Random();	
		
		int FPart = rand.nextInt(1000,9999);
		int SPart = rand.nextInt(1000,9999);
		int TPart = rand.nextInt(1000,9999);
		int FrPart = rand.nextInt(1000,9999);
				
		LoginPageController.AccountNumber = String.valueOf(FPart) +","+ String.valueOf(SPart) +","+ String.valueOf(TPart) +","+ String.valueOf(FrPart);
	} // generateAccountNumber().
	
	static public void generateCardNum(String AccType) { // Number, CVV, ExpiryDate.
		Random rand = new Random();
		
		// Card Number.
		int FPart;

		if (AccType == "Current") {
			FPart = 5110 ;
		}
		else if (AccType == "Savings") {
			FPart = 5220 ;
		}
		else if (AccType == "Youth") {
			FPart = 5330 ;
		}
		else {
			FPart = rand.nextInt(1000,9999);
		}
		
		int SPart = rand.nextInt(1000,9999);
		int TPart = rand.nextInt(1000,9999);
		int FrPart = rand.nextInt(1000,9999);
				
		String ConCardNumber = String.valueOf(FPart) +","+ String.valueOf(SPart) +","+ String.valueOf(TPart) +","+ String.valueOf(FrPart);
		
		// CVV.
		int CVV = rand.nextInt(100,999);
		String CVVNumber = String.valueOf(CVV); 
		
		// ExpiryDate.
		int ExpMonth = rand.nextInt(1, 13);

        Year thisYear = Year.now();
        int ExpYear = thisYear.getValue() + 3;
         
        String formattedMonth = String.format("%02d", ExpMonth);
        String formattedYear = String.format("%02d", ExpYear % 100);
         
        String ExpiryDate = formattedMonth + "/" + formattedYear;
        
        LoginPageController.DebitCardNum = ConCardNumber ;

	} // generateCardNum(). For Debit and Credit.
	
	
	
	
	
}