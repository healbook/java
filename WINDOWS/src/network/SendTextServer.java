package network;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SendTextServer {

    public static void main(String[] args) throws Exception {

        ServerSocket server = new ServerSocket(1202);
        System.out.println("Server is started...");
        Socket socket = server.accept();
        DataInputStream din = new DataInputStream(socket.getInputStream());
        DataOutputStream dos
                = new DataOutputStream(socket.getOutputStream());
        String str = din.readUTF();
        System.out.println(str);
        Scanner sc = new Scanner(System.in);
        String msgSv = sc.nextLine();
        dos.writeUTF("\n\n\nFrom server: " + msgSv);
        socket.close();

    }
}