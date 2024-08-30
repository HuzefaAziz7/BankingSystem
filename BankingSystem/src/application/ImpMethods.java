// Important Methods.
package application;

import java.util.Random;
import java.util.Scanner ;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Year;

import org.mindrot.jbcrypt.BCrypt;

public class ImpMethods {
	 
	 public static CallableStatement MyCallStmt = null ;
	 public static Connection MyCon = null ;
	 public static Statement MyStmt = null ; 
	 public static ResultSet MyRS = null ;
	 public static PreparedStatement PSUpdate = null ; 
	 public static String VerificationResult = null; // Used in ExistingUserLogin().
// 	 MainPageController MainPageController = new MainPageController();
	
	public static void AdminDBConnection() {

	  	{ try {
			
	  		String dbUrl = "jdbc:mysql://localhost:3306/Admin" ;
	  		String usernamedb = "BankingSystem" ;
	  		String passworddb = "bank" ;
			
//	  		Get Connection to Database 
	  		MyCon = DriverManager.getConnection(dbUrl, usernamedb , passworddb); 
			
//	  		Create A Statement 
	  		MyStmt = MyCon.createStatement();
	  	
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
	  	
	  	} // Try.
		
	  	catch (Exception exc) {
	  		exc.printStackTrace(); 
	  	}  } // Catch, JDBC Connection.

	  } // DBConnection()
	
	static public void NewUserLogin(String CustomerID, String CustomerName, String Password,String AccountID, String AccountType, String AccountNumber, String DebitCardNum, String CVV, String ExpiryDate, String PIN, String Email, String MobileNum, String Address, String Branch) {
    	AdminDBConnection();
        try {
            // Prepared Statement for Inserting the Username and HashedPassword into DB.
        	String[] Info = { CustomerID,  CustomerName,  Password, AccountID,  AccountType,  AccountNumber,  DebitCardNum, CVV, ExpiryDate,  PIN = null,  Email,  MobileNum,  Address,  Branch};
        	
        	PSUpdate = MyCon.prepareStatement("INSERT INTO ClientInfo(CustomerID,CustomerName,Password,AccountID,AccountType,AccountNumber,DebitCardNum,CVV,ExpiryDate,PIN,Email,MobileNumber,Address,Branch) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
        	for (int i = 0; i < Info.length; i++) {
        	    if (i == 0 || i == 7 || i == 11) {
        	        // Use Long.parseLong to avoid NumberFormatException for large numbers
        	        PSUpdate.setLong((i + 1), Long.parseLong(Info[i]));
        	    } else {
        	        PSUpdate.setString((i + 1), Info[i]);
        	    }
        	}
        	int rowsAffected = PSUpdate.executeUpdate();
            PSUpdate.close();
            System.out.print("Done");
            InsertUsedCustomerID();
            String table = String.valueOf(CustomerID);
            newUserTable(CustomerID);
        } catch (Exception exc) {
            exc.printStackTrace();
        } // Catch.
    } // NewUserLogin Method.
	
	static public void ExistingUserLogin(String Username, String Password) {
        String hashedpassword = null;
        AdminDBConnection();
        try {
            PSUpdate = MyCon.prepareStatement("SELECT CustomerName,Password FROM ClientInfo WHERE CustomerName = ?");
            PSUpdate.setString(1, Username);
            MyRS = PSUpdate.executeQuery();
            if (MyRS.next()) {
                hashedpassword = MyRS.getString("Password");
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

		if (AccType == "Current Account") {
			FPart = 5110 ;
		}
		else if (AccType == "Savings Account") {
			FPart = 5220 ;
		}
		else if (AccType == "Youth Account") {
			FPart = 5330 ;
		}
		else {
			FPart = 5440;
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
        LoginPageController.CVV = CVVNumber ;
        LoginPageController.ExpiryDate = ExpiryDate ;
        
	} // generateCardNum(). For Debit and Credit.
	
	
	
	
	
	
	
	static public void generateReqLink() {
		
	} // generateReqLink(). 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	static void InsertUsedCustomerID() {
		AdminDBConnection();
		try {
            PSUpdate = MyCon.prepareStatement("INSERT INTO UsedCustomerID(UsedCustomerID) VALUES (?) ");
            PSUpdate.setLong(1, Long.parseLong(LoginPageController.CustomerID));
            int rowsAffected = PSUpdate.executeUpdate();
            PSUpdate.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        } // Catch.
	} // InsertUsedCustomerID()
	
	static void newUserTable(String tableName) {
		ClientDBConnection();
		
		try {
			PSUpdate = MyCon.prepareStatement("CREATE TABLE " + tableName + " (\n"
	  				+ "  `SrNo` int NOT NULL AUTO_INCREMENT,\n"
	  				+ "  `DateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,\n"
	  				+ "  `Payee` varchar(45) NOT NULL,\n"
	  				+ "  `Amount` int NOT NULL,\n"
	  				+ "  `Payer` varchar(45) NOT NULL,\n"
	  				+ "  `TransStatus` varchar(45) NOT NULL,\n"
	  				+ "  `TransType` varchar(45) NOT NULL,\n"
	  				+ "  UNIQUE KEY `SrNo_UNIQUE` (`SrNo`)\n"
	  				+ ")");
            int rowsAffected = PSUpdate.executeUpdate();
            PSUpdate.close();

        } catch (Exception exc) {
            exc.printStackTrace();
        } // Catch.
		
	} // New Table(). is made everytime a new user is entered in the database. 
    
	
	
	
	
	
}