package DoAn;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;

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
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import LTM.NewBill;
import LTM.Search;
import LTM.ViewBill;

import java.util.logging.Level;
import java.util.logging.Logger;



public class GiaoDienClient {
	private InetAddress host;
	private int port;
	public Socket s;
	OutputStream os;
	InputStream is;
	public DefaultTableModel model;
	PrintWriter pw;
	JTable ctb;
	JTable supplytb;
	JTextArea statusta;
	public Connection con;
	public Statement stmt;
	Vector data;
	
	public GiaoDienClient() throws ClassNotFoundException, SQLException {
		

	}
	public void execute() throws ClassNotFoundException, SQLException {
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
							
							int port = Integer.parseInt(porttf.getText());
							try {
						        s = new Socket("127.0.0.1",port);
						        if(s.isConnected()) {
						        	 statusta.append("Connected (port "+port+")");
						        	 DataOutputStream dos = new DataOutputStream(s.getOutputStream());
						        	 dos.writeUTF("1");
								     DataInputStream diss = new DataInputStream(s.getInputStream());
								     ObjectInputStream ois = new ObjectInputStream(diss);
								     
									
										try {
											data = (Vector) ois.readObject();
											statusta.append(diss.readUTF());
										} catch (ClassNotFoundException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										
									
										
						        }
						    } catch (IOException ex) {
						        JOptionPane.showMessageDialog(null, "Loi phan socket: " +ex.getMessage());
						        if("Connection refused".equals(ex.getMessage())){
						        statusta.append("Chua bat server!");
						        }
						        try {
						            Thread.sleep(2000);
						        } catch (InterruptedException ex1) {
						            Logger.getLogger(GiaoDien.class.getName()).log(Level.SEVERE, null, ex1);
						        }
						        System.exit(0);
						    }
							
			
						}
					});
					
					panel.add(connect);
					JLabel status = new JLabel("Client Status:");
					status.setFont(new Font("Candara Light", Font.BOLD, 25));
					status.setBounds(1000, 300, 200, 40);
					panel.add(status);
					statusta = new JTextArea();
					statusta.setEditable(false);
					statusta.setBounds(1000, 350, 450, 300);
					statusta.setBorder(BorderFactory.createRaisedSoftBevelBorder());
					panel.add(statusta);
					//
					Vector columnNames = new Vector();
					
					columnNames.addElement("ClothesID");
					columnNames.addElement("ClothesName");
					columnNames.addElement("BuyPrice");
					columnNames.addElement("SellPrice");
					columnNames.addElement("BuyQuantity");
					columnNames.addElement("RemainQuantity");
					model = new DefaultTableModel(data,columnNames);
					JTable supplytb = new JTable(model);
					supplytb.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					JScrollPane scrolltb = new JScrollPane(supplytb);
					scrolltb.setBounds(10, 155, 450, 300);	
					scrolltb.setBorder(BorderFactory.createRaisedBevelBorder());
					panel.add(scrolltb);
					//TABLE
					
					//Customer Table
					Vector cdata = new Vector();
					try {
					    stmt = con.createStatement();
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
					} catch (SQLException e) {
					    e.printStackTrace();
					}
					Vector ccolumnNames = new Vector();
					
					ccolumnNames.addElement("ID");
					ccolumnNames.addElement("Name");
					ccolumnNames.addElement("ContactNumber");
					ccolumnNames.addElement("Email");
					ccolumnNames.addElement("Address");
					
					DefaultTableModel cmodel = new DefaultTableModel(cdata,ccolumnNames);
					JTable ctb = new JTable(cmodel);
					ctb.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					JScrollPane cscrolltb = new JScrollPane(ctb);
					cscrolltb.setBounds(500, 155, 450, 300);	
					cscrolltb.setBorder(BorderFactory.createRaisedBevelBorder());
					
					panel.add(cscrolltb);
					//ADD BUTTON
					JButton b_addclothes = new JButton("New Clothes");
					b_addclothes.setBackground(Color.PINK);
					b_addclothes.setForeground(Color.BLACK);
					b_addclothes.setBounds(30, 490, 130, 62);
					b_addclothes.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
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
									String id = idtf.getText();
									String name =nametf.getText();
									float buy =Float.parseFloat(buytf.getText());
									float sell = Float.parseFloat(selltf.getText());
									int q1 =Integer.parseInt(q1tf.getText());
									int q2 =Integer.parseInt(q2tf.getText());
									Vector addquery = new Vector();
									addquery.add(id);
									addquery.add(name);
									addquery.add(buy);
									addquery.add(sell);
									addquery.add(q1);
									addquery.add(q1);
									
									DataOutputStream dos1;
									try {
										dos1 = new DataOutputStream(s.getOutputStream());
										ObjectOutputStream oos1 = new ObjectOutputStream(dos1);
										dos1.writeUTF("2");
										dos1.flush();
										oos1.writeObject(addquery);
										
										oos1.flush();
										
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
									addf.dispose();
									
								}
							});
							addf.getContentPane().add(cancelb);
							
							addf.setVisible(true);
							addf.setAlwaysOnTop(true);
							
							
					}});
					panel.setLayout(null);
					panel.add(b_addclothes);
					//NEW BILL
					JButton b_cnew = new JButton("New Customer");
					b_cnew.setBackground(Color.PINK);
					b_cnew.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								new AddCustomer();
								
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
					});
					b_cnew.setBounds(30, 584, 130, 62);
					panel.add(b_cnew);
					//DELETE 
					JButton b_delete = new JButton("Delete Clothes");
					b_delete.setBackground(Color.PINK);
					b_delete.setBounds(270, 490, 130, 62);
					b_delete.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							
							if(supplytb.getRowCount()==0) {
								JOptionPane.showMessageDialog(null, "Empty Clothes");
							}
							else	
						if(supplytb.getSelectedRow()==-1) {
							JOptionPane.showMessageDialog(null, "No Clothes Was Selected!!!!");
						}
						else
						if(supplytb.getSelectedRow()>=0) {
							String cell = supplytb.getModel().getValueAt(supplytb.getSelectedRow(), 0).toString();
						if (JOptionPane.showConfirmDialog(null, "Are you sure to delete Clothes with id: " + cell,"Your Choice:", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
						{
							String deletequery = "EXEC DEL "+cell+"";
							PreparedStatement prtm;
							try {
								prtm = con.prepareStatement(deletequery);
								prtm.executeUpdate();
								JOptionPane.showMessageDialog(null, "Delete Succesfully");
								
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						}
						
						}
					});
				
					panel.add(b_delete);
					//UPDATE CUSTOMER
					JButton b_cusupdate = new JButton("Update Customer");
					b_cusupdate.setBackground(Color.PINK);
					b_cusupdate.setBounds(270, 584, 130, 62);
					b_cusupdate.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							JFrame f2;
							f2 = new JFrame();
							f2.setTitle("Update Customer");
							f2.getContentPane().setLayout(null);
							
							JLabel lbc2 = new JLabel("ID");
							lbc2.setFont(new Font("Tahoma", Font.PLAIN, 19));
							lbc2.setBounds(10, 165, 100, 50);
							f2.getContentPane().add(lbc2);
							
							JSeparator sec1 = new JSeparator();
							sec1.setBounds(0, 153, 546, 2);
							f2.getContentPane().add(sec1);
							
							JTextField tfc2 = new JTextField();
							tfc2.setBounds(180, 165, 215, 40);
							f2.getContentPane().add(tfc2);
							
							JLabel lbc3 = new JLabel("Name");
							lbc3.setFont(new Font("Tahoma", Font.PLAIN, 19));
							lbc3.setBounds(10, 225, 100, 50);
							f2.getContentPane().add(lbc3);
							
							JLabel lbc4 = new JLabel("Contact Number");
							lbc4.setFont(new Font("Tahoma", Font.PLAIN, 19));
							lbc4.setBounds(10, 285, 140, 50);
							f2.getContentPane().add(lbc4);
							
							JLabel lbc5 = new JLabel("Email");
							lbc5.setFont(new Font("Tahoma", Font.PLAIN, 19));
							lbc5.setBounds(10, 345, 100, 50);
							f2.getContentPane().add(lbc5);
							
							JLabel lbc6 = new JLabel("Address");
							lbc6.setFont(new Font("Tahoma", Font.PLAIN, 19));
							lbc6.setBounds(10, 405, 100, 50);
							f2.getContentPane().add(lbc6);
							
							 JTextField tfc3 = new JTextField();
							tfc3.setBounds(180, 225, 215, 40);
							f2.getContentPane().add(tfc3);
							
							JTextField tfc4 = new JTextField();
							tfc4.setBounds(180, 285, 215, 40);
							f2.getContentPane().add(tfc4);
							
							JTextField tfc5 = new JTextField();
							tfc5.setBounds(180, 345, 215, 40);
							f2.getContentPane().add(tfc5);
							
							JTextField tfc6 = new JTextField();
							tfc6.setBounds(180, 405, 215, 40);
							f2.getContentPane().add(tfc6);
							
							JLabel ulb = new JLabel("Update Customer");
							ulb.setFont(new Font("Candara Light", Font.BOLD, 38));
							ulb.setBounds(100, 10, 350, 46);
							f2.getContentPane().add(ulb);
				
								int cselectedrow = ctb.getSelectedRow();
								String cdcell = ctb.getModel().getValueAt(cselectedrow, 0).toString();
									String query = "SELECT * FROM Customer WHERE CustomerID = '"+cdcell+"'";
										try {
											Statement stmt1 = con.createStatement();
											ResultSet crs = stmt1.executeQuery(query);
											if(crs.next()) {
												tfc2.setEditable(false);
												tfc2.setText(crs.getString(1));
												tfc3.setText(crs.getString(2));
												tfc4.setText(crs.getString(3));
												tfc5.setText(crs.getString(4));
												tfc6.setText(crs.getString(5));
											}
											else 
												JOptionPane.showMessageDialog(null, "ID does not exist");
											
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									
					
							
							
							
							JButton cupdateb = new JButton("Update");
							cupdateb.setBounds(70, 495, 100, 50);
							cupdateb.setBackground(Color.pink);
							cupdateb.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									int cid = Integer.parseInt(tfc2.getText());
									String name = tfc3.getText();
									String contactno = tfc4.getText();
									String email = tfc5.getText();
									String address = tfc6.getText();
									String upquery = "EXEC CUSUPDATE "+cid+",'"+name+"','"+contactno+"','"+email+"','"+address+"'";
									try {
										PreparedStatement prstmt = con.prepareStatement(upquery);
										prstmt.executeUpdate();
										JOptionPane.showMessageDialog(null, "Update Successfully,Press refresh to apply change");
										f2.dispose();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
								}
							});
							f2.getContentPane().add(cupdateb);
							
							JButton cancelb = new JButton("Cancel");
							cancelb.setBounds(340, 495, 100, 50);
							cancelb.setBackground(Color.pink);
							cancelb.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									f2.dispose();
									
								}
							});
							f2.getContentPane().add(cancelb);
							f2.setSize(550, 610);
							f2.setVisible(true);
							
						}
					});
					panel.add(b_cusupdate);
					//UPDATE CLOTHES
					JButton b_clupdate = new JButton("Update Clothes");
					b_clupdate.setBackground(Color.PINK);
					b_clupdate.setBounds(550, 490, 130, 62);
					b_clupdate.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							JFrame f1;
							f1 = new JFrame();
							f1.setTitle("Update Clothes");
							f1.getContentPane().setLayout(null);
							
							JLabel ulb = new JLabel("Update Clothes");
							ulb.setFont(new Font("Candara Light", Font.BOLD, 38));
							ulb.setBounds(120, 10, 300, 46);
							f1.getContentPane().add(ulb);
							
							JLabel lb1 = new JLabel("Clothes ID");
							lb1.setFont(new Font("Tahoma", Font.BOLD, 17));
							lb1.setBounds(10, 90, 140, 40);
							f1.getContentPane().add(lb1);
							
							JTextField tfs1 = new JTextField();
							tfs1.setBounds(180, 90, 215, 40);
							f1.getContentPane().add(tfs1);
							
							JButton search = new JButton("Search");
							search.setFont(new Font("Tahoma", Font.PLAIN, 15));
							search.setBounds(420, 90, 85, 40);
							
							JLabel lb2 = new JLabel("Clothes ID");
							lb2.setFont(new Font("Tahoma", Font.PLAIN, 19));
							lb2.setBounds(10, 165, 100, 50);
							f1.getContentPane().add(lb2);
							
							JSeparator se1 = new JSeparator();
							se1.setBounds(0, 150, 540, 2);
							f1.getContentPane().add(se1);
							
							JTextField tfs2 = new JTextField();
							tfs2.setBounds(220, 165, 215, 40);
							f1.getContentPane().add(tfs2);
							
							JLabel lb7 = new JLabel("Clothes Name");
							lb7.setFont(new Font("Tahoma", Font.PLAIN, 19));
							lb7.setBounds(10, 220, 150, 50);
							f1.getContentPane().add(lb7);
							
							JTextField tfs3 = new JTextField();
							tfs3.setBounds(220, 220, 215, 40);
							f1.getContentPane().add(tfs3);
							
							JLabel lb3 = new JLabel("Buy Price");
							lb3.setFont(new Font("Tahoma", Font.PLAIN, 19));
							lb3.setBounds(10, 283, 140, 50);
							f1.getContentPane().add(lb3);
							
							JLabel lb4 = new JLabel("Sell Price");
							lb4.setFont(new Font("Tahoma", Font.PLAIN, 19));
							lb4.setBounds(10, 343, 140, 50);
							f1.getContentPane().add(lb4);
							
							JLabel lb5 = new JLabel("Buy Quantity");
							lb5.setFont(new Font("Tahoma", Font.PLAIN, 19));
							lb5.setBounds(10, 403, 140, 50);
							f1.getContentPane().add(lb5);
							
							JLabel lb6 = new JLabel("Remain Quantity");
							lb6.setFont(new Font("Tahoma", Font.PLAIN, 19));
							lb6.setBounds(10, 463, 163, 50);
							f1.getContentPane().add(lb6);
							
							JTextField tfs4 = new JTextField();
							tfs4.setBounds(220, 280, 215, 40);
							f1.getContentPane().add(tfs4);
							
							JTextField tfs5 = new JTextField();
							tfs5.setColumns(10);
							tfs5.setBounds(220, 340, 215, 40);
							f1.getContentPane().add(tfs5);
							
							JTextField tfs6 = new JTextField();
							tfs6.setBounds(220, 400, 215, 40);
							f1.getContentPane().add(tfs6);
							
							JTextField tfs7 = new JTextField();
							tfs7.setBounds(220, 460, 215, 40);
							f1.getContentPane().add(tfs7);
							
			
							
							JButton cancelb = new JButton("Cancel");
							cancelb.setBounds(340, 540, 100, 50);
							cancelb.setBackground(Color.pink);
							cancelb.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									f1.dispose();
									
								}
							});
							f1.getContentPane().add(cancelb);
							f1.setVisible(true);
							f1.setSize(550, 650);
							
							
							int selectedrow = supplytb.getSelectedRow();
							String dcell = supplytb.getModel().getValueAt(selectedrow, 0).toString();
							try {
								Statement stmt1 = con.createStatement();
								ResultSet rs = stmt1.executeQuery("SELECT * FROM Clothes WHERE ClothesID = '"+dcell+"'");
								if(rs.next()) {
									tfs1.setText(rs.getString(1));
									tfs2.setText(rs.getString(1));
									tfs3.setText(rs.getString(2));
									tfs4.setText(String.valueOf(rs.getFloat(3)));
									tfs5.setText(String.valueOf(rs.getFloat(4)));
									tfs6.setText(String.valueOf(rs.getInt(5)));
									tfs7.setText(String.valueOf(rs.getInt(6)));
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
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
										Statement upstmt = con.createStatement();
										upstmt.executeUpdate(upquery);
										JOptionPane.showMessageDialog(null, "Update Successfully,Press refresh to apply change");
										f1.dispose();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
								}
							});
							f1.getContentPane().add(updateb);
							
						}
					});
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
					
					JButton refresh = new JButton("Refresh");
					refresh.setBounds(400, 110, 105, 40);
					refresh.setBackground(Color.PINK);
					refresh.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							cdata.clear();			
							Vector columnNames = new Vector();
							
							columnNames.addElement("ClothesID");
							columnNames.addElement("ClothesName");
							columnNames.addElement("BuyPrice");
							columnNames.addElement("SellPrice");
							columnNames.addElement("BuyQuantity");
							columnNames.addElement("RemainQuantity");
							supplytb.setModel(new DefaultTableModel(data, columnNames));
							Vector cdata = new Vector();
							try {
							    Statement cstmt = con.createStatement();
							    ResultSet crs = cstmt.executeQuery("SELECT * FROM Customer");
							    ResultSetMetaData cmetaData = crs.getMetaData();
							    int ccolumns = cmetaData.getColumnCount();
							    while (crs.next()) {
							       Vector crow = new Vector(ccolumns);
							       for (int i = 1; i <= ccolumns; i++) {
							        crow.addElement(crs.getObject(i));
							       }
							       cdata.addElement(crow);
							    }
							} catch (SQLException r) {
							    r.printStackTrace();
							}
							Vector ccolumnNames = new Vector();
							
							ccolumnNames.addElement("ID");
							ccolumnNames.addElement("Name");
							ccolumnNames.addElement("ContactNumber");
							ccolumnNames.addElement("Email");
							ccolumnNames.addElement("Address");
							ctb.setModel(new DefaultTableModel(cdata, ccolumnNames));
						}
					});
					panel.add(refresh);
					
					JButton billb = new JButton("New Bill");
					billb.setBounds(820, 490, 130, 62);
					billb.setBackground(Color.PINK);
					billb.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							try {
								new NewBill();
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					panel.add(billb);
					
					JButton search = new JButton("Search");
					search.setBounds(550, 584, 130, 62);
					search.setBackground(Color.PINK);
					search.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							try {
								new Search();
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					panel.add(search);
					
					JButton cal = new JButton("Profit Calculator");
					cal.setBounds(820, 584, 130, 62);
					cal.setBackground(Color.PINK);
					cal.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							try {
								new Calculator();
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					panel.add(cal);
					
					JButton bill = new JButton("View All Bill");
					bill.setBounds(420, 680, 130, 62);
					bill.setBackground(Color.pink);
					bill.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							 try {
								new ViewBill();
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					panel.add(bill);
					f.setLocation(20, 20);
					f.setVisible(true);
					ctb.setDefaultEditor(Object.class, null);
	

	
	}

	public static void main(String[] args) throws ClassNotFoundException {
	try {
		GiaoDienClient client = new GiaoDienClient();
		client.execute();
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
