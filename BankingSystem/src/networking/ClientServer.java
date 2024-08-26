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

        socket = new Socket("localhost", 5253);
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
        
        System.out.print("Enter Amount : ");
        int Amount = scan.nextInt();
        requestMoney(Amount);

    } // main.

    static public void sendMoney(int Amount) {

        try {
            while (true) {
                System.out.println(dis.readUTF());
                String toSend = "Huzefa has sent " + Amount + " to your account..!!";
                dos.writeUTF(toSend);

                String received = dis.readUTF();
                System.out.println(received);
            }

        } catch (Exception e) {
            System.out.println("Server has closed the connection.");
        }

    } // sendMoney().

    static public void requestMoney(int Amount) {

        try {
            while (true) {
                System.out.println(dis.readUTF());
                String toSend = "Huzefa has requested " + Amount + " from your account..!!";
                dos.writeUTF(toSend);

                String received = dis.readUTF();
                System.out.println(received);
            }

        } catch (Exception e) {
            System.out.println("Server has closed the connection.");
        }

    } // requestMoney().

} // TestEchoClient.
