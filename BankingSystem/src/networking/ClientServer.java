package networking;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientServer {

    static Scanner scan = new Scanner(System.in);
    static Socket socket = null;
    static DataInputStream dis = null;
    static DataOutputStream dos = null;

    public static void main(String[] args) throws IOException {
    } // main.
    
    // Constructor.
    public ClientServer(Socket socket, DataInputStream dis, DataOutputStream dos) throws IOException {
    	
    	this.socket = new Socket("localhost", 5253);
        this.dis = new DataInputStream(this.socket.getInputStream());
        this.dos = new DataOutputStream(this.socket.getOutputStream());
        
    } // ClientServer().
    
    static public void sendMoney(String Payee, String AccNumber, int Amount,String Remarks) {

        try {
        	
            while (true) {
            	
            	ClientServer ClientServer = new ClientServer(socket, dis, dos);
                System.out.println(dis.readUTF());
                String toSend = Payee + " has sent " + Amount + " to your account..!!" + " Remarks : " + Remarks ;
                dos.writeUTF(toSend);

                String received = dis.readUTF();
                System.out.println(received);
                break;
            }

        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Server has closed the connection.");
        }

    } // sendMoney().

    static public void requestMoney(String Payee, String AccNumber, int Amount,String Remarks) {

        try {
            while (true) {
            	ClientServer ClientServer = new ClientServer(socket, dis, dos);
                System.out.println(dis.readUTF());
                String toSend = Payee + " has requested " + Amount + " to your account..!!" + " Remarks : " + Remarks ;
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
