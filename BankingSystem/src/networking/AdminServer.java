package networking ; 

import java.io.*;
import java.net.*;
import java.util.*;

public class AdminServer {

    public static void main(String[] args) {
    	
    	try {
    		ServerSocket svrsocket = new ServerSocket(5253);
//          System.out.println("Server Started.");
          int count = 0;

          while (true) {
             
              Socket socket = null;

              try {
                  count++;
                  socket = svrsocket.accept();

                  DataInputStream dis = new DataInputStream(socket.getInputStream());
                  DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                  System.out.println("Assigning a new thread for this client : " + count);

                  Thread t = new ClientHandler(socket, dis, dos);

                  t.start();

              } catch (Exception e) {
                  e.printStackTrace();
              }

          }
    	} catch (Exception e) {
            System.out.println("Server Denied.");
            e.printStackTrace();
        }
        

    }

}

class ClientHandler extends Thread {

    final Socket socket;
    final DataInputStream dis;
    final DataOutputStream dos;

    // Constructor
    public ClientHandler(Socket socket, DataInputStream dis, DataOutputStream dos) {
        this.socket = socket;
        this.dis = dis;
        this.dos = dos;
    }

    public void run() {
        String recv;
        String toReturn;
        
        while (true) {

            try {
            	String AccNumber = "12345678" ;
            	String Remarks = "Others" ;
            	
            	String Payer = dis.readUTF();
//            	System.out.println("Payer : "+ Payer);s
                dos.writeUTF("Server connection established to : " + Payer);
                
                String Payee = dis.readUTF();
//                System.out.println("Payee : "+ Payee);
                
                int Amount = dis.readInt();
//                System.out.println("Amount : "+ Amount);
                
                boolean result = TransactionsController.checkTrans(Amount, Payee) ; // 
                
                if (result) {
                	TransactionsController.sendMoneyDB(Payer, Payee,AccNumber, Amount, Remarks);
                    dos.writeUTF("Transaction Completed.");
                }
                
                else {
                	dos.writeUTF("Transaction Failed.");
                }
                
                break;
                
            } catch (Exception e) {

            }
        }

    } // run().

}
