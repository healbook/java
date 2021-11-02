package DoAn;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Server extends Thread{
	public Connection con;
	private int port;
	Socket s;
	OutputStream os;
	InputStream is;
	PrintWriter pw;
	ServerSocket ss;
	public static ArrayList<Socket> listsk;
	public Server(int port) {
		this.port=port;
	}
	private void execute() throws IOException {
		ServerSocket server = new ServerSocket(port);
		System.out.println("Server is working...");
		while(true) {
			Socket socket = server.accept();
			Server.listsk.add(socket);
			System.out.println("New client connected ("+socket+")");
			readfromclient read = new readfromclient(socket);
			read.start();
		
	}
	
	}
	
	
	public static void main(String[] args) {
		Server.listsk = new ArrayList<Socket>();
		Server server = new Server(1234);
		try {
			server.execute();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		}
	class readfromclient extends Thread {
		Connection con;
		private Socket server;
		public readfromclient(Socket server) {
			this.server=server;
		}
		@Override 
		public void run() {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
			String Url = "jdbc:sqlserver://MSI\\VJT:1433;databaseName=Qlyshop";
			String user = "sa";
			String pass ="sasasa"; 
			try {
				con = DriverManager.getConnection(Url, user, pass);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Success!!");
			DataInputStream dis = null;
			DataOutputStream dos = null;
			try {
				dis = new DataInputStream(server.getInputStream());
				dos = new DataOutputStream(server.getOutputStream());
				while(true) {		
					for(Socket item : Server.listsk) {
						try {	
							//sent table data to client //
							int option = Integer.parseInt(dis.readUTF());
							switch(option) {
							case 1:{
								Vector data = new Vector();
								try {
								    Statement stmt = con.createStatement();
								    ResultSet rs = stmt.executeQuery("SELECT * FROM Clothes");
								    ResultSetMetaData metaData = rs.getMetaData();
								    int columns = metaData.getColumnCount();
								    while (rs.next()) {
								       Vector row = new Vector(columns);
								       for (int i = 1; i <= columns; i++) {
								        row.addElement(rs.getObject(i));
								       }
								       data.addElement(row);
								    }
								} catch (SQLException e) {
								    e.printStackTrace();
								}
								
								//
								
									dos = new DataOutputStream(item.getOutputStream());
									ObjectOutputStream oos = new ObjectOutputStream(dos);
									oos.writeObject(data);
									oos.flush();
									dos.writeUTF("Table data updated!!");
								
							
							}
							case 2:{
									DataInputStream dis2 = new DataInputStream(item.getInputStream());
									ObjectInputStream ois = new ObjectInputStream(dis);
									Vector addquery = (Vector) ois.readObject();
									Enumeration enu = addquery.elements();
									while(enu.hasMoreElements()) {
										System.out.println(enu.nextElement().toString());
									}
									//String query = "INSERT INTO Clothes Values('"+addquery.get(0)+"',"+"N'"+addquery.get(1)+"',"+addquery.get(2)+","+addquery.get(3)+","+addquery.get(4)+","+addquery.get(5)+")";
									//PreparedStatement prmt = con.prepareStatement(query);
								
								
							}
							}
					} catch (Exception e) {
						// TODO: handle exception
					}
					}
				}
			} catch (Exception e) {
				try {
					dis.close();
					server.close();
				} catch( IOException ex) {
					JOptionPane.showMessageDialog(null, "Server disconnected");
				}
			}
		}
	}
	
	}


