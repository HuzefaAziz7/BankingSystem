// Important Methods.
package application;

import java.util.Scanner ;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
	
	public static void DBConnection() {

	  	{ try {
			
	  		String dbUrl = "jdbc:mysql://localhost:3306/AccManager" ;
	  		String usernamedb = "root" ;
	  		String passworddb = "root1203503" ;
			
//	  		Get Connection to Database 
	  		MyCon = DriverManager.getConnection(dbUrl, usernamedb , passworddb); 
			
//	  		Create A Statement 
	  		MyStmt = MyCon.createStatement();
	  		
	  		System.out.println("Connection Established");
	  	} // Try.
		
	  	catch (Exception exc) {
	  		exc.printStackTrace(); 
	  	}  } // Catch, JDBC Connection.

	  } // DBConnection()
	
	static public void NewUserLogin(String NewUsername, String NewPassword, String Email) {
        String HashedPassword = BCrypt.hashpw(NewPassword, BCrypt.gensalt());
        
        try {
            // Prepared Statement for Inserting the Username and HashedPassword into DB.
            PSUpdate = MyCon.prepareStatement("INSERT INTO IdInfo(Username,UserPassword,Email) VALUES (?,?,?)");
            PSUpdate.setString(1, NewUsername);
            PSUpdate.setString(2, HashedPassword);
            PSUpdate.setString(3, Email);
            int rowsAffected = PSUpdate.executeUpdate();
            PSUpdate.close();
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
}