package networking;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientServer {

    static Scanner scan = new Scanner(System.in);
    static Socket socket = null;
    static DataInputStream dis = null;
    static DataOutputStream dos = null;

    public static void main(String[] args) throws IOException {} // main.
    
    // Constructor.
    public ClientServer(Socket socket, DataInputStream dis, DataOutputStream dos) throws IOException {
    	
    	this.socket = new Socket("localhost", 5253);
        this.dis = new DataInputStream(this.socket.getInputStream());
        this.dos = new DataOutputStream(this.socket.getOutputStream());
        
    } // ClientServer().
    
    /*
    static public void transaction() {
    	try {
        	System.out.print("Press 1. Send , 2.Request");
            int choice = scan.nextInt();
            
            System.out.println("Enter Payer Name : ");
            String Payer = scan.next() ;
            dos.writeUTF(Payer);
            
            scan.nextLine();
            System.out.println("Enter Payee Name : ");
            String Payee = scan.next();
            dos.writeUTF(Payee);
            
            System.out.println("Enter Amount : ");
            int Amount = scan.nextInt();
            dos.writeInt(Amount);
            
            if (choice == 1) {
            	sendMoney(Payer,Amount,Payee);
            }
            else if (choice == 2) {
            	requestMoney(Payer,Amount,Payee);
            }
            else {
            	System.out.println("Invalid Option");
            
    	}} catch (Exception e) {
    		System.out.println("FAILED IN LINE 28");
    	}
    	
    } // trans
    */
    
    static public void sendMoney(String Payee, int Amount,String Remarks) { // String AccNumber, 
    	System.out.println("CS Line 61");
    	try {
        	
            while (true) {
            	System.out.println("CS Line 65");
            	ClientServer ClientServer = new ClientServer(socket, dis, dos);
            	System.out.println("CS Line 67");
            	
            	String Payer = "Huzefa" ;
                dos.writeUTF(Payer);
                dos.writeUTF(Payee);
                dos.writeInt(Amount);
                
                
                String toSend = Payee + " has sent " + Amount + " to your account." + " Remarks : " + Remarks ;
                dos.writeUTF(toSend);
                System.out.println("CS Line 71");
                String received = dis.readUTF();
                System.out.println(received);
                System.out.println("CS Line 74");
                break;
                
            }

        } catch (Exception e) {
//        	e.printStackTrace();
            System.out.println("Server has closed the connection.");
        }

    } // sendMoney().

    static public void requestMoney(String Payer, int Amount, String Remarks) {

        try {
            while (true) {
            	ClientServer ClientServer = new ClientServer(socket, dis, dos);
                System.out.println(dis.readUTF());
                String toSend = Payer + " has requested " + Amount + " from your account." + " Remarks : " + Remarks ;
                dos.writeUTF(toSend);

                String received = dis.readUTF();
                System.out.println(received);
                break;
            }

        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Server has closed the connection.");
        }

    } // requestMoney().

} // TestEchoClient.
