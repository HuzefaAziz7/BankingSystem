package networking ; 

import java.io.*;
import java.net.*;
import java.util.*;

public class AdminServer {

    public static void main(String[] args) throws IOException {
    	
        ServerSocket svrsocket = new ServerSocket(5253);
        System.out.println("Server Started.");
        int count = 0;

        while (true) {
//            System.out.println("Number of Clients connected: " + count); 
            Socket socket = null;

            try {
                count++;
                socket = svrsocket.accept();

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                System.out.println("Assigning a new thread for this client: " + count);

                Thread t = new ClientHandler(socket, dis, dos);

                t.start();

            } catch (Exception e) {
                e.printStackTrace();
            }

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
                // Ask user.
                dos.writeUTF("Connection established to Server.");

                recv = dis.readUTF();
//              System.out.println(recv);
                
                dos.writeUTF("Server says : " + recv);

            } catch (Exception e) {

            }
        }

    }

}
