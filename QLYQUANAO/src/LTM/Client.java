package LTM;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import DoAn.AddCustomer;
import DoAn.Calculator;
import DoAn.GiaoDien;

public class Client{
	private InetAddress host;
	private int port;
	public Socket s;
	public JTextField porttf;
	OutputStream os;
	InputStream is;
	public DefaultTableModel model;
	public DefaultTableModel cmodel;
	JScrollPane scrolltb;
	JScrollPane cscrolltb;
	PrintWriter pw;
	public JTable ctb;
	public JTable supplytb;
	JTextArea statusta;
	public Connection con;
	public Statement stmt;
	Vector cdata;
	Vector data;
	Vector columnNames;
	Vector ccolumnNames;
	JLabel sta;
	DataInputStream dis = null;
	DataOutputStream dos = null;
	ObjectInputStream rf = null;
	JButton b_addclothes;
	JButton b_cnew;
	JButton b_delete;
	JButton b_clupdate;
	JButton billb;
	JButton bill;
	JButton search;
	public Client() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		String Url = "jdbc:sqlserver://MSI\\VJT:1433;databaseName=Qlyshop";
		  String user = "sa";
		  String pass ="sasasa"; 

		 con = DriverManager.getConnection(Url, user, pass);
		stmt = con.createStatement();
				//CONNECT TO SERVER 

				//MAINFRAME
				    JFrame f;
					f = new JFrame();
					f.setBounds(100, 100, 1500, 800);
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
					
					JPanel panel = new JPanel();
					f.getContentPane().add(panel);
					//CONNECTION FIELD 
					JLabel portlb = new JLabel("Enter port to connect:");
					portlb.setFont(new Font("Candara Light", Font.BOLD, 18));
					portlb.setBounds(1030, 100, 200, 40);
					panel.add(portlb);
					JTextField porttf=new JTextField();
					porttf.setBounds(1250, 100, 140, 40);
					panel.add(porttf);
					JButton connect  = new JButton("Connect");
					connect.setBounds(1250, 170, 140, 40);
					connect.setBackground(Color.PINK);
					connect.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if(e.getActionCommand().equals("Connect")) {
								try {
									int port = Integer.parseInt(porttf.getText());
									s = new Socket("127.0.0.1",port);
									if(s.isConnected()) {
										statusta.append("Connected\n");
										connect.setText("Disconnect");	
										statusta.append("Refesh to update table data\n");
										sta.setForeground(Color.green);
										sta.setText("Connected");
										updatetable();
										b_addclothes.setEnabled(true);
										b_cnew.setEnabled(true);
										b_delete.setEnabled(true);
										b_clupdate.setEnabled(true);
										billb.setEnabled(true);
										bill.setEnabled(true);
										search.setEnabled(true);
									}															
									
								} catch (NumberFormatException | IOException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(null, "Server not found");
								} 
							}
							if(e.getActionCommand().equals("Disconnect")) {
								statusta.append("Connection Closed\n");
								try {
									dos = new DataOutputStream(s.getOutputStream());
									dos.writeUTF("10");
									dos.flush();
									sta.setForeground(Color.red);
									sta.setText("Inactivated/Disconnected");							
									s.close();
								} catch (IOException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								connect.setText("Connect");	
								try {
									s.close();
									data.clear();
									cdata.clear();
									supplytb.setModel(new DefaultTableModel(data,columnNames));
									ctb.setModel(new DefaultTableModel(cdata,ccolumnNames));
									b_addclothes.setEnabled(false);
									b_cnew.setEnabled(false);
									b_delete.setEnabled(false);
									b_clupdate.setEnabled(false);
									billb.setEnabled(false);
									bill.setEnabled(false);
									search.setEnabled(false);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					});
					
					
					
					panel.add(connect);
					JLabel status = new JLabel("Client Status:");
					status.setFont(new Font("Candara Light", Font.BOLD, 25));
					status.setBounds(1000, 300, 200, 40);
					panel.add(status);
					
					sta = new JLabel("Inactived/disconnected");
					sta.setFont(new Font("Candara Light", Font.BOLD, 25));
					sta.setBounds(1200, 300, 300, 40);
					sta.setForeground(Color.red);
					panel.add(sta);
					
					statusta = new JTextArea();
					statusta.setEditable(false);
					JScrollPane statuspn = new JScrollPane(statusta);
					statuspn.setBounds(1000, 350, 450, 300);
					statusta.setFont(new Font("Tahoma", Font.PLAIN, 20));
					statuspn.setBorder(BorderFactory.createRaisedSoftBevelBorder());
					panel.add(statuspn);
					//
					
					
				
					scrolltb = new JScrollPane(supplytb);
					scrolltb.setBounds(10, 155, 450, 300);	
					scrolltb.setBorder(BorderFactory.createRaisedBevelBorder());
					panel.add(scrolltb);
					//TABLE
					
					//Customer Table					
					
					
			
					cscrolltb = new JScrollPane(ctb);
					cscrolltb.setBounds(500, 155, 450, 300);	
					cscrolltb.setBorder(BorderFactory.createRaisedBevelBorder());
					
					panel.add(cscrolltb);
					//ADD BUTTON
					b_addclothes = new JButton("New Clothes");
					b_addclothes.setBackground(Color.PINK);
					b_addclothes.setForeground(Color.BLACK);
					b_addclothes.setEnabled(false);
					b_addclothes.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							JFrame addf;
							addf = new JFrame();
							addf.setTitle("Add Clothes");
							addf.getContentPane().setLayout(null);
							addf.setSize(500, 550);
							JLabel titlelb = new JLabel("Enter the information of new Clothes:");
							titlelb.setFont(new Font("Candara Light", Font.BOLD, 21));
							titlelb.setBounds(52, 10, 400, 40);
							addf.getContentPane().add(titlelb);
							
							JLabel idlb = new JLabel("Clothes ID:");
							idlb.setFont(new Font("Tahoma", Font.PLAIN, 15));
							idlb.setBounds(10, 90, 105, 40);
							addf.getContentPane().add(idlb);
							
							JTextField idtf = new JTextField();
							idtf.setFont(new Font("Tahoma", Font.PLAIN, 18));
							idtf.setBounds(189, 89, 230, 40);
							addf.getContentPane().add(idtf);
							idtf.setColumns(10);
							
							JLabel namelb = new JLabel("Clothes Name:");
							namelb.setFont(new Font("Tahoma", Font.PLAIN, 15));
							namelb.setBounds(10, 140, 105, 40);
							addf.getContentPane().add(namelb);
							
							JTextField nametf = new JTextField();
							nametf.setFont(new Font("Tahoma", Font.PLAIN, 18));
							nametf.setColumns(10);
							nametf.setBounds(189, 140, 230, 40);
							addf.getContentPane().add(nametf);
							
							JLabel buylb = new JLabel("Buy Price:");
							buylb.setFont(new Font("Tahoma", Font.PLAIN, 15));
							buylb.setBounds(10, 190, 105, 40);
						addf.getContentPane().add(buylb);
							
							JTextField buytf = new JTextField();
							buytf.setFont(new Font("Tahoma", Font.PLAIN, 18));
							buytf.setColumns(10);
							buytf.setBounds(189, 190, 230, 40);
							addf.getContentPane().add(buytf);
							
							JLabel selllb = new JLabel("Sell Price:");
							selllb.setFont(new Font("Tahoma", Font.PLAIN, 15));
							selllb.setBounds(10, 240, 105, 40);
							addf.getContentPane().add(selllb);
							
							JTextField selltf = new JTextField();
							selltf.setFont(new Font("Tahoma", Font.PLAIN, 18));
							selltf.setColumns(10);
							selltf.setBounds(189, 240, 230, 40);
							addf.getContentPane().add(selltf);
							
							JLabel quantity1 = new JLabel("Buy Quantity:");
							quantity1.setFont(new Font("Tahoma", Font.PLAIN, 15));
							quantity1.setBounds(10, 290, 105, 40);
							addf.getContentPane().add(quantity1);
							
							JLabel quantity2 = new JLabel("Remain Quantity:");
							quantity2.setFont(new Font("Tahoma", Font.PLAIN, 15));
							quantity2.setBounds(10, 340, 125, 40);
							addf.getContentPane().add(quantity2);
							
							JTextField q1tf = new JTextField();
							q1tf.setFont(new Font("Tahoma", Font.PLAIN, 18));
							q1tf.setColumns(10);
							q1tf.setBounds(189, 290, 230, 40);
							addf.getContentPane().add(q1tf);
							
							JTextField q2tf = new JTextField();
							q2tf.setFont(new Font("Tahoma", Font.PLAIN, 18));
							q2tf.setColumns(10);
							q2tf.setBounds(189, 340, 230, 40);
							addf.getContentPane().add(q2tf);
							
							JButton addb = new JButton("Add");
							addb.setBackground(Color.PINK);
							addb.setBounds(52, 443, 105, 40);
							addb.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									String id = idtf.getText();
									String name =nametf.getText();
									float buy =Float.parseFloat(buytf.getText());
									float sell = Float.parseFloat(selltf.getText());
									int q1 =Integer.parseInt(q1tf.getText());
									int q2 =Integer.parseInt(q2tf.getText());
									String addquery = "INSERT INTO Clothes Values('"+id+"',"+"N'"+name+"',"+buy+","+sell+","+q1+","+q2+")";
									try {	
										DataInputStream dis = new DataInputStream(s.getInputStream());
										DataOutputStream dos = new DataOutputStream(s.getOutputStream());
										dos.writeUTF("2");
										dos.flush();
										dos.writeUTF(addquery);
										dos.flush();
										JOptionPane.showMessageDialog(addf, dis.readUTF());
										addf.dispose();
										updatetable();
							
									
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});
						
							addf.getContentPane().add(addb);
							
							JButton cancelb = new JButton("Cancel");
							cancelb.setBackground(Color.PINK);
							cancelb.setBounds(290, 443, 105, 40);
							cancelb.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									addf.dispose();
								}
							});
							addf.getContentPane().add(cancelb);
							
							addf.setVisible(true);
							addf.setAlwaysOnTop(true);
							
							
						}
					});
					b_addclothes.setBounds(30, 490, 130, 62);
		
						
					panel.setLayout(null);
					panel.add(b_addclothes);
					//NEW customer
					b_cnew = new JButton("New Customer");
					b_cnew.setBackground(Color.PINK);
					b_cnew.setEnabled(false);
					b_cnew.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JFrame addcf;
							addcf = new JFrame();
							addcf.getContentPane().setLayout(null);
							
							JLabel clb = new JLabel("New Customer");
							clb.setFont(new Font("Candara Light", Font.BOLD, 38));
							clb.setBounds(110, 10, 275, 40);
							addcf.getContentPane().add(clb);
							
							JLabel lb1 = new JLabel("Name:");
							lb1.setFont(new Font("Tahoma", Font.PLAIN, 15));
							lb1.setBounds(49, 98, 105, 40);
							addcf.getContentPane().add(lb1);
							
							JTextField tf1 = new JTextField();
							tf1.setBounds(188, 98, 230, 40);
							addcf.getContentPane().add(tf1);
							tf1.setColumns(10);
							
							JLabel lb2 = new JLabel("ContactNumber:");
							lb2.setFont(new Font("Tahoma", Font.PLAIN, 15));
							lb2.setBounds(49, 148, 118, 40);
							addcf.getContentPane().add(lb2);
							
							JLabel lb3 = new JLabel("Email:");
							lb3.setFont(new Font("Tahoma", Font.PLAIN, 15));
							lb3.setBounds(49, 198, 105, 40);
							addcf.getContentPane().add(lb3);
							
							JLabel lb4 = new JLabel("Address:");
							lb4.setFont(new Font("Tahoma", Font.PLAIN, 15));
							lb4.setBounds(49, 248, 105, 40);
							addcf.getContentPane().add(lb4);
							
							JTextField tf2 = new JTextField();
							tf2.setColumns(10);
							tf2.setBounds(188, 148, 230, 40);
							addcf.getContentPane().add(tf2);
							
							JTextField tf3 = new JTextField();
							tf3.setColumns(10);
							tf3.setBounds(188, 198, 230, 40);
							addcf.getContentPane().add(tf3);
							
							JTextField tf4 = new JTextField();
							tf4.setColumns(10);
							tf4.setBounds(188, 248, 230, 90);
							addcf.getContentPane().add(tf4);
							
							JButton addb = new JButton("Add");
							addb.setBackground(Color.PINK);
							addb.setBounds(80, 380, 105, 40);
							addb.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									String name = tf1.getText();
									String no = tf2.getText();
									String email = tf3.getText();
									String address = tf4.getText();
									String query = "INSERT INTO Customer(CustomerName,ContactNumber,Email,Address) VALUES(N'"+name+"',"+"'"+no+"',"+"'"+email+"',"+"N'"+address+"')";
									try {	
										DataInputStream dis = new DataInputStream(s.getInputStream());
										DataOutputStream dos = new DataOutputStream(s.getOutputStream());
										dos.writeUTF("3");
										dos.flush();
										dos.writeUTF(query);
										dos.flush();
										JOptionPane.showMessageDialog(addcf, dis.readUTF());
										addcf.dispose();
										updatetable();
							
									
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							});
							addcf.getContentPane().add(addb);
							
							JButton cb = new JButton("Cancel");
							cb.setBackground(Color.PINK);
							cb.setBounds(312, 380, 105, 40);
							cb.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									addcf.dispose();
									
								}
							});
							addcf.getContentPane().add(cb);
							addcf.setSize(500, 550);
							addcf.setTitle("New Customer");
							addcf.setVisible(true);
						}
					});
					b_cnew.setBounds(30, 584, 130, 62);
					panel.add(b_cnew);
					//DELETE 
					b_delete = new JButton("Delete Clothes");
					b_delete.setBackground(Color.PINK);
					b_delete.setBounds(270, 490, 130, 62);
					b_delete.setEnabled(false);
					b_delete.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
						if(supplytb.getRowCount()==0) {
							JOptionPane.showMessageDialog(f, "Empty Clothes");
						}
						else if (supplytb.getSelectedRow()==-1) {
							JOptionPane.showMessageDialog(f, "No cloth was select");
						}
								
						else
						if(supplytb.getSelectedRow()>=0) {
							String cell = supplytb.getModel().getValueAt(supplytb.getSelectedRow(), 0).toString();
						if (JOptionPane.showConfirmDialog(null, "Are you sure to delete Clothes with id: " + cell,"Your Choice:", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
						{
							String deletequery = "EXEC DEL "+cell+"";
							try {
								dos = new DataOutputStream(s.getOutputStream());
								dis = new DataInputStream(s.getInputStream());
								dos.writeUTF("4");
								dos.flush();
								dos.writeUTF(deletequery);
								dos.flush();
								JOptionPane.showMessageDialog(f, dis.readUTF());
								updatetable();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
						}
						}
						}
					});
						
					
				
					panel.add(b_delete);
					//UPDATE CUSTOMER
					
					//UPDATE CLOTHES
					b_clupdate = new JButton("Update Clothes");
					b_clupdate.setBackground(Color.PINK);
					b_clupdate.setEnabled(false);
					b_clupdate.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							if(supplytb.getRowCount()==0) {
								JOptionPane.showMessageDialog(f, "Empty Clothes");
							}
							else if (supplytb.getSelectedRow()==-1) {
								JOptionPane.showMessageDialog(f, "No cloth was select");
							}
									
							else
							if(supplytb.getSelectedRow()>=0) {									
							// TODO Auto-generated method stub
							JFrame cluf;
							cluf = new JFrame();
							cluf.setTitle("Update Clothes");
							cluf.getContentPane().setLayout(null);
							
							JLabel ulb = new JLabel("Update Clothes");
							ulb.setFont(new Font("Candara Light", Font.BOLD, 38));
							ulb.setBounds(120, 10, 300, 46);
							cluf.getContentPane().add(ulb);
							
							JLabel lb1 = new JLabel("Clothes ID");
							lb1.setFont(new Font("Tahoma", Font.BOLD, 17));
							lb1.setBounds(10, 90, 140, 40);
							cluf.getContentPane().add(lb1);
							
							JTextField tfs1 = new JTextField();
							tfs1.setBounds(180, 90, 215, 40);
							cluf.getContentPane().add(tfs1);
							
							JButton search = new JButton("Search");
							search.setFont(new Font("Tahoma", Font.PLAIN, 15));
							search.setBounds(420, 90, 85, 40);
							
							JLabel lb2 = new JLabel("Clothes ID");
							lb2.setFont(new Font("Tahoma", Font.PLAIN, 19));
							lb2.setBounds(10, 165, 100, 50);
							cluf.getContentPane().add(lb2);
							
							JSeparator se1 = new JSeparator();
							se1.setBounds(0, 150, 540, 2);
							cluf.getContentPane().add(se1);
							
							JTextField tfs2 = new JTextField();
							tfs2.setBounds(220, 165, 215, 40);
							cluf.getContentPane().add(tfs2);
							
							JLabel lb7 = new JLabel("Clothes Name");
							lb7.setFont(new Font("Tahoma", Font.PLAIN, 19));
							lb7.setBounds(10, 220, 150, 50);
							cluf.getContentPane().add(lb7);
							
							JTextField tfs3 = new JTextField();
							tfs3.setBounds(220, 220, 215, 40);
							cluf.getContentPane().add(tfs3);
							
							JLabel lb3 = new JLabel("Buy Price");
							lb3.setFont(new Font("Tahoma", Font.PLAIN, 19));
							lb3.setBounds(10, 283, 140, 50);
							cluf.getContentPane().add(lb3);
							
							JLabel lb4 = new JLabel("Sell Price");
							lb4.setFont(new Font("Tahoma", Font.PLAIN, 19));
							lb4.setBounds(10, 343, 140, 50);
							cluf.getContentPane().add(lb4);
							
							JLabel lb5 = new JLabel("Buy Quantity");
							lb5.setFont(new Font("Tahoma", Font.PLAIN, 19));
							lb5.setBounds(10, 403, 140, 50);
							cluf.getContentPane().add(lb5);
							
							JLabel lb6 = new JLabel("Remain Quantity");
							lb6.setFont(new Font("Tahoma", Font.PLAIN, 19));
							lb6.setBounds(10, 463, 163, 50);
							cluf.getContentPane().add(lb6);
							
							JTextField tfs4 = new JTextField();
							tfs4.setBounds(220, 280, 215, 40);
							cluf.getContentPane().add(tfs4);
							
							JTextField tfs5 = new JTextField();
							tfs5.setColumns(10);
							tfs5.setBounds(220, 340, 215, 40);
							cluf.getContentPane().add(tfs5);
							
							JTextField tfs6 = new JTextField();
							tfs6.setBounds(220, 400, 215, 40);
							cluf.getContentPane().add(tfs6);
							
							JTextField tfs7 = new JTextField();
							tfs7.setBounds(220, 460, 215, 40);
							cluf.getContentPane().add(tfs7);
							tfs1.setEditable(false);
			
							
							JButton cancelb = new JButton("Cancel");
							cancelb.setBounds(340, 540, 100, 50);
							cancelb.setBackground(Color.pink);
							cancelb.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									cluf.dispose();
									
								}
							});
							cluf.getContentPane().add(cancelb);
							cluf.setVisible(true);
							cluf.setSize(550, 650);
				
							
							
							int selectedrow = supplytb.getSelectedRow();
							String dcell = supplytb.getModel().getValueAt(selectedrow, 0).toString();						
							tfs1.setText(supplytb.getModel().getValueAt(selectedrow, 0).toString());
							tfs2.setText(supplytb.getModel().getValueAt(selectedrow, 0).toString());
							tfs3.setText(supplytb.getModel().getValueAt(selectedrow, 1).toString());
							tfs4.setText(supplytb.getModel().getValueAt(selectedrow, 2).toString());
							tfs5.setText(supplytb.getModel().getValueAt(selectedrow, 3).toString());
							tfs6.setText(supplytb.getModel().getValueAt(selectedrow, 4).toString());
							tfs7.setText(supplytb.getModel().getValueAt(selectedrow, 5).toString());
							JButton updateb = new JButton("Update");
							updateb.setBounds(75, 540, 100, 50);
							updateb.setBackground(Color.PINK);
							updateb.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									String sid = tfs1.getText();
									String cloid = tfs2.getText();
									String cloname = tfs3.getText();
									float buyp = Float.parseFloat(tfs4.getText());
									float sellp = Float.parseFloat(tfs5.getText());
									int buyq = Integer.parseInt(tfs6.getText());
									int remainq = Integer.parseInt(tfs7.getText());
									
									String upquery = "EXEC CLOUPDATE '"+sid+"','"+cloid+"','"+cloname+"',"+buyp+","+sellp+","+buyq+","+remainq;
									try {
										dos = new DataOutputStream(s.getOutputStream());
										dis = new DataInputStream(s.getInputStream());
										dos.writeUTF("5");
										dos.flush();
										dos.writeUTF(upquery);
										dos.flush();
										JOptionPane.showMessageDialog(cluf, dis.readUTF());
										cluf.dispose();
										updatetable();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
									
								}
							});
							cluf.getContentPane().add(updateb);
						}
						}
					});
					b_clupdate.setBounds(550, 490, 130, 62);
					
					
					panel.add(b_clupdate);
					//TITLE
					ImageIcon ic1 = new ImageIcon("image/titleicon.png");
					
					JLabel titlelb = new JLabel("Clothing store management system");
					titlelb.setIcon(ic1);
					titlelb.setIconTextGap(20);
					titlelb.setBackground(Color.DARK_GRAY);
					titlelb.setForeground(Color.BLACK);
					titlelb.setHorizontalAlignment(SwingConstants.CENTER);
					titlelb.setFont(new Font("Tahoma", Font.PLAIN, 35));
					titlelb.setBounds(150, 10, 800, 63);
					panel.add(titlelb);
			
					JLabel tablelb = new JLabel("Current Clothes");
					
					tablelb.setFont(new Font("Tahoma", Font.PLAIN, 20));
					tablelb.setBounds(150, 120, 160, 25);
					tablelb.setBorder(BorderFactory.createLineBorder(Color.pink));
					panel.add(tablelb);
					
					JLabel cuslb = new JLabel("Current Customers");
					
					cuslb.setFont(new Font("Tahoma", Font.PLAIN, 20));
					cuslb.setBounds(700, 120, 160, 25);
					cuslb.setBorder(BorderFactory.createLineBorder(Color.pink));
					panel.add(cuslb);
					
			
					
					billb = new JButton("New Bill");
					billb.setBounds(820, 490, 130, 62);
					billb.setBackground(Color.PINK);
					billb.setEnabled(false);
					billb.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							try {
								NewBill bill = new NewBill(s);			
								bill.addWindowListener(new WindowListener() {
									
									@Override
									public void windowOpened(WindowEvent e) {
										// TODO Auto-generated method stub
										
									}
									
									@Override
									public void windowIconified(WindowEvent e) {
										// TODO Auto-generated method stub
										
									}
									
									@Override
									public void windowDeiconified(WindowEvent e) {
										// TODO Auto-generated method stub
										
									}
									
									@Override
									public void windowDeactivated(WindowEvent e) {
										// TODO Auto-generated method stub
										
									}
									
									@Override
									public void windowClosing(WindowEvent e) {
										// TODO Auto-generated method stub								
									}
									
									@Override
									public void windowClosed(WindowEvent e) {
										// TODO Auto-generated method stub
										updatetable();
									}
									
									@Override
									public void windowActivated(WindowEvent e) {
										// TODO Auto-generated method stub
										
									}
								});
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, e1);;
							}
						}
					});
					panel.add(billb);
					
					bill = new JButton("View All Bill");
					bill.setBounds(270, 584, 130, 62);
					bill.setBackground(Color.pink);
					bill.setEnabled(false);
					bill.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							try {
								new ViewBill(s);
							} catch (ClassNotFoundException | SQLException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					panel.add(bill);
					
					search = new JButton("Search");
					search.setBounds(550, 584, 130, 62);
					search.setBackground(Color.PINK);
					search.setEnabled(false);
					search.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							try {
								new Search(s);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, e1);
							}
						}
					});
					panel.add(search);
					
					
					f.setLocation(20, 20);
					f.setVisible(true);		
	}
	public void updatetable () {
		supplytb = new JTable();
		ctb = new JTable();
		try {
			dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF("1");								
			dis=new DataInputStream(s.getInputStream());
			rf = new ObjectInputStream(dis);
			Vector list =(Vector) rf.readObject();
			data=(Vector) list.get(0);
			cdata=(Vector) list.get(1);
			statusta.append(dis.readUTF());
		} catch (Exception e) {
			// TODO: handle exception
		}
		Vector columnNames = new Vector();
		
		columnNames.addElement("ClothesID");
		columnNames.addElement("ClothesName");
		columnNames.addElement("BuyPrice");
		columnNames.addElement("SellPrice");
		columnNames.addElement("BuyQuantity");
		columnNames.addElement("RemainQuantity");
		model = new DefaultTableModel(data,columnNames)  {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		supplytb.setModel(model);
		scrolltb.setViewportView(supplytb);
		
		
		Vector ccolumnNames = new Vector();
		
		ccolumnNames.addElement("ID");
		ccolumnNames.addElement("Name");
		ccolumnNames.addElement("ContactNumber");
		ccolumnNames.addElement("Email");
		ccolumnNames.addElement("Address");
		cmodel = new DefaultTableModel(cdata,ccolumnNames) {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		ctb.setModel(cmodel);
		cscrolltb.setViewportView(ctb);
	}
	public static void main(String[] args) {
		try {
			
			Client c = new Client();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
