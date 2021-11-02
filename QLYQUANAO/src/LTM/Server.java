package LTM;

import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;














public class Server
{	
	public static JFrame f;
	public static JTextField totalc;
	public static JTextArea statusta;
	private int port;
	public static ArrayList<Socket> listsk;
	ServerSocket server;
	public Server(int port) throws IOException {
		this.port=port;
	}
	public void execute() throws IOException {
		ServerSocket server = new ServerSocket(port);
		SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		DateTimeFormatter dtf =DateTimeFormatter.ofPattern("hh:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		f = new JFrame();
		f.setBounds(100,100,500,600);
		f.getContentPane().setLayout(null);
		statusta = new JTextArea();
		statusta.setBackground(Color.BLACK);
		statusta.setFont(new Font("Monospaced", Font.PLAIN, 20));
		statusta.setEditable(false);
		JScrollPane pn = new JScrollPane(statusta);
		pn.setBounds(10, 100, 450, 450);
		statusta.setForeground(Color.white);
		f.getContentPane().add(pn);
		f.setVisible(true);

		
		statusta.append("["+dtf.format(now)+"]"+"Server is working...\n");
		JLabel lbport = new JLabel("Port:");
		lbport.setBounds(20,20,70,40);
		lbport.setFont(new Font("Monospaced", Font.PLAIN, 20));
		f.getContentPane().add(lbport);
		
		JLabel lbport2 = new JLabel();
		lbport2.setText(String.valueOf(port));
		lbport2.setBounds(80, 20, 50, 40);
		lbport2.setFont(new Font("Monospaced", Font.PLAIN, 20));
		f.getContentPane().add(lbport2);
		
		
		JLabel total = new JLabel("Total Client in server:");
		total.setBounds(180, 5, 300, 40);
		total.setFont(new Font("Monospaced", Font.PLAIN, 20));
		f.getContentPane().add(total);
		
		totalc = new JTextField();
		totalc.setBounds(220, 50, 100, 40);
		totalc.setEditable(false);
		f.getContentPane().add(totalc);
		while(true) {
			Socket socket = server.accept();
			Server.listsk.add(socket);
			statusta.append("-------------------------------------------\n");
			statusta.append("["+dtf.format(now)+"]"+"New client connected" +"("+socket.getPort()+")\n");
			statusta.append("-------------------------------------------\n");
			ThreadHandler th = new ThreadHandler(socket);
			th.start();
			totalc.setText(String.valueOf(listsk.size()));
	    }		
	
		
	}	
	public static void main(String[] args)
	{
		int port = Integer.parseInt(JOptionPane.showInputDialog("Enter port to start server:"));
		Server.listsk = new ArrayList<Socket>();
		Server sv;
		try {
			sv = new Server(port);
			sv.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
			Server.f.dispose();
		}
		
    }
	

}

class ThreadHandler extends Thread
{
	private PrintWriter out;
    private BufferedReader bf;
	private Socket server;
	Connection con;
	boolean cp;
public ThreadHandler(Socket server)
	{
	this.server=server;
	}

public void run()
	{
	
	while(true) {
		int svport = server.getPort();
			DateTimeFormatter dtf =DateTimeFormatter.ofPattern("hh:mm:ss");
			LocalDateTime now = LocalDateTime.now();
		DataOutputStream dos = null;
		DataInputStream dis = null;
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
			try {
				dis = new DataInputStream(server.getInputStream());
				dos = new DataOutputStream(server.getOutputStream());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			while(true) {
				try {
					int option = Integer.parseInt(dis.readUTF());	
					
					if(option==1) {
						Vector data = new Vector();
						Vector cdata = new Vector();
						Vector pkg = new Vector();
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
							   	pkg.add(data);
							    ResultSet crs = stmt.executeQuery("SELECT * FROM Customer");
							    ResultSetMetaData cmetaData = crs.getMetaData();
							    int ccolumns = cmetaData.getColumnCount();
							    while (crs.next()) {
							       Vector crow = new Vector(ccolumns);
							       for (int i = 1; i <= ccolumns; i++) {
							        crow.addElement(crs.getObject(i));
							       }
							       cdata.addElement(crow);
							    }
							    pkg.add(cdata);
							    ObjectOutputStream oos = new ObjectOutputStream(dos);
							oos.writeObject(pkg);		
							oos.flush();
							dos.writeUTF("Table data updated!\n");
							dos.flush();
							Server.statusta.append("\n["+dtf.format(now)+"]"+"Client["+svport+"] has sent updatetable request");
			
					}
					else if(option==2) {
						dos = new DataOutputStream(server.getOutputStream());
						dis = new DataInputStream(server.getInputStream());
						String addquery=dis.readUTF();
						PreparedStatement prstmt = con.prepareStatement(addquery);
						prstmt.execute();
						dos.writeUTF("Request done!Inserted new Cloth");
						Server.statusta.append("\n["+dtf.format(now)+"]"+"Client["+svport+"] has sent insert request");
						
					}
					else if(option==3) {
						dos = new DataOutputStream(server.getOutputStream());
						dis = new DataInputStream(server.getInputStream());
						String query=dis.readUTF();
						PreparedStatement prstmt = con.prepareStatement(query);
						prstmt.execute();
						dos.writeUTF("Request done!Inserted new Customer");
						Server.statusta.append("\n["+dtf.format(now)+"]"+"Client["+svport+"] has sent insert request");
					}
					else if(option==4) {
						dos = new DataOutputStream(server.getOutputStream());
						dis = new DataInputStream(server.getInputStream());
						String query=dis.readUTF();
						PreparedStatement prstmt = con.prepareStatement(query);
						prstmt.execute();
						dos.writeUTF("Request done! Deleted");
						Server.statusta.append("\n["+dtf.format(now)+"]"+"Client["+svport+"] has sent delete request");
					}
					else if(option==5) {
						dos = new DataOutputStream(server.getOutputStream());
						dis = new DataInputStream(server.getInputStream());
						String query=dis.readUTF();
						Statement upstmt = con.createStatement();
						upstmt.executeUpdate(query);
						dos.writeUTF("Request done! Updated");
						Server.statusta.append("\n["+dtf.format(now)+"]"+"Client["+svport+"] has sent update request");
				
					}
					else if(option==6) {
						dos = new DataOutputStream(server.getOutputStream());
						dis=new DataInputStream(server.getInputStream());
						String query = dis.readUTF();
						PreparedStatement prstmt = con.prepareStatement(query);
						prstmt.execute();
						dos.writeUTF("Request done! New Bill Generated!");
						Server.statusta.append("\n["+dtf.format(now)+"]"+"Client["+svport+"] has sent insert request");
						
					}
					else if(option==7) {
						dos = new DataOutputStream(server.getOutputStream());
						dis=new DataInputStream(server.getInputStream());
						String query = dis.readUTF();
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query);
						ResultSetMetaData mtrs = rs.getMetaData();
						Vector billdata = new Vector();
						int col = mtrs.getColumnCount();
						while(rs.next()) {
							Vector row = new Vector();
							for(int i = 1;i<=col;i++) {
								row.add(rs.getObject(i));
							}
							billdata.addElement(row);
							
						}
						ObjectOutputStream oos = new ObjectOutputStream(dos);
						oos.writeObject(billdata);
						oos.flush();
						Server.statusta.append("\n["+dtf.format(now)+"]"+"Client["+svport+"] has sent view request");
						
					}
					else if(option==8) {
						dos = new DataOutputStream(server.getOutputStream());
						dis = new DataInputStream(server.getInputStream());
						String query1 = dis.readUTF();
						String query2 = dis.readUTF();
						String query3 = dis.readUTF();
						Statement stmt = con.createStatement();
						//Clothes search result
						Vector sclothes = new Vector();
						ResultSet rs1=stmt.executeQuery(query1);
						ResultSetMetaData rsmt1 = rs1.getMetaData();
						int col1 = rsmt1.getColumnCount();
						while(rs1.next()) {
							Vector row1=new Vector();
							for(int i=1;i<=col1;i++) {
								row1.add(rs1.getObject(i));
							}
							sclothes.addElement(row1);
						}
						//Customer search result
						Vector scustomer = new Vector();
						ResultSet rs2=stmt.executeQuery(query2);
						ResultSetMetaData rsmt2 = rs2.getMetaData();
						int col2 = rsmt2.getColumnCount();
						while(rs2.next()) {
							Vector row2=new Vector();
							for(int i=1;i<=col2;i++) {
								row2.add(rs2.getObject(i));
							}
							scustomer.addElement(row2);
						}
						//Bill search result
						Vector sbill = new Vector();
						ResultSet rs3=stmt.executeQuery(query3);
						ResultSetMetaData rsmt3 = rs3.getMetaData();
						int col3 = rsmt3.getColumnCount();
						while(rs3.next()) {
							Vector row3=new Vector();
							for(int i=1;i<=col3;i++) {
								row3.add(rs3.getObject(i));
							}
							sbill.addElement(row3);
						}
						Vector pkg = new Vector();
						pkg.addElement(sclothes);
						pkg.addElement(scustomer);
						pkg.addElement(sbill);
						ObjectOutputStream oos = new ObjectOutputStream(dos);
						oos.writeObject(pkg);
						Server.statusta.append("\n["+dtf.format(now)+"]"+"Client["+svport+"] has sent search request");
					}
					else if(option==10) {
						Server.listsk.remove(server);
						Server.statusta.append("\n-------------------------------------------");
						Server.statusta.append("\n"+"["+dtf.format(now)+"]"+"Client["+svport+"] Disconnected\n");					
						Server.statusta.append("\n-------------------------------------------\n");
						Server.totalc.setText(String.valueOf(Server.listsk.size()));
						dos.close();
						dis.close();
						server.close();
						cp=false;
								
					}
					else if(option==11) {
						dis = new DataInputStream(server.getInputStream());
						String query = dis.readUTF();
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query);
						Vector cloinf = new Vector();
						if(rs.next()) {
							cloinf.addElement(rs.getObject(2));
							cloinf.addElement(rs.getObject(3));
							cloinf.addElement(rs.getObject(4));
							cloinf.addElement(rs.getObject(5));
						}
						dos = new DataOutputStream(server.getOutputStream());
						ObjectOutputStream oos = new ObjectOutputStream(dos);
						oos.writeObject(cloinf);
					}
					else if(option==12) {
						dis = new DataInputStream(server.getInputStream());
						String query = dis.readUTF();
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query);
						Vector cusinf = new Vector();
						if(rs.next()) {
							cusinf.addElement(rs.getObject(2));
							cusinf.addElement(rs.getObject(4));
							cusinf.addElement(rs.getObject(6));
						}
						dos = new DataOutputStream(server.getOutputStream());
						ObjectOutputStream oos = new ObjectOutputStream(dos);
						oos.writeObject(cusinf);
				
					}
					else if(option==13) {
						dis = new DataInputStream(server.getInputStream());
						String query =dis.readUTF();
						PreparedStatement prmt = con.prepareStatement(query);
						prmt.execute();
		
					}
			} catch(IOException e) {
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
		
	}
	
}

}

