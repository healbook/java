package network;
import java.io.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class SendTextClient {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 1202);
        System.out.println("da ket noi den server thanh cong");
        DataOutputStream dos
                = new DataOutputStream(socket.getOutputStream());
        DataInputStream din = new DataInputStream(socket.getInputStream());
        Scanner sc = new Scanner(System.in);
        String msgClient = sc.nextLine();
        dos.writeUTF("\n\nFrom client:" + msgClient);
        String str = din.readUTF();
        System.out.println("\n\n\n" + msgClient);
        socket.close();
    }
}