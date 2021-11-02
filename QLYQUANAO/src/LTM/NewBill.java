package LTM;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;




public class NewBill extends JFrame{
	public float finaltotal = 0;
	public int quantity;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;
	private JTextField tf4;
	private JTextField tf5;
	private JTextField tf6;
	private JTextField tf7;
	private JTextField tf8;
	private JTextField tf9;
	private JTextField tf10;
	private JTextField tf11;
	public String changeq;
	Vector data;
	Vector columnsName;
	private Socket s;
	Connection con;
	Statement stmt;
	public NewBill(Socket s) throws Exception {
		if(s.isConnected()) {
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			
			this.getContentPane().setLayout(null);
			
			JLabel lb = new JLabel("BILLING");
			lb.setFont(new Font("Candara Light", Font.PLAIN, 60));
			lb.setBounds(10, 10, 310, 80);
			this.getContentPane().add(lb);
			
			JLabel datelb = new JLabel("Date:");
			datelb.setFont(new Font("Tahoma", Font.PLAIN, 18));
			datelb.setBounds(940, 10, 50, 35);
			this.getContentPane().add(datelb);
			
			JLabel lblTime = new JLabel("Time:");
			lblTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblTime.setBounds(940, 55, 55, 35);
			this.getContentPane().add(lblTime);
			
			JLabel d = new JLabel();
			d.setFont(new Font("Tahoma", Font.PLAIN, 18));
			d.setBounds(1050, 10, 120, 35);
			this.getContentPane().add(d);
			
			JLabel t = new JLabel();
			t.setFont(new Font("Tahoma", Font.PLAIN, 18));
			t.setBounds(1050, 55, 90, 35);
			this.getContentPane().add(t);
			//DATE AND TIME
			SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			d.setText(dFormat.format(date));
			DateTimeFormatter dtf =DateTimeFormatter.ofPattern("hh:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			t.setText(dtf.format(now));
			//
			
			JLabel cusdetail = new JLabel("Customer Detail:");
			cusdetail.setFont(new Font("Tahoma", Font.PLAIN, 20));
			cusdetail.setBounds(10, 100, 150, 50);
			this.getContentPane().add(cusdetail);
			
			JLabel cusid = new JLabel("ID:");
			cusid.setFont(new Font("Tahoma", Font.PLAIN, 18));
			cusid.setBounds(10, 160, 30, 35);
			this.getContentPane().add(cusid);
			
			tf1 = new JTextField();
			tf1.setBounds(39, 160, 75, 35);
			tf1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub	
					int id = Integer.parseInt(tf1.getText());
					
					String query = "SELECT * FROM Customer WHERE CustomerID ="+id;
					DataOutputStream dos;
					DataInputStream dis;
					try {
						dos = new DataOutputStream(s.getOutputStream());
						dos.writeUTF("11");
						dos.flush();
						dos.writeUTF(query);
						dos.flush();
						dis = new DataInputStream(s.getInputStream());
						ObjectInputStream ois = new ObjectInputStream(dis);
						
						Vector cinf = (Vector) ois.readObject();
						if(cinf.size()==0) {
							JOptionPane.showMessageDialog(null, "Invalid ID");
						}
						else {
							tf2.setText(cinf.get(0).toString());
							tf3.setText(cinf.get(1).toString());
							tf4.setText(cinf.get(2).toString());
							tf5.setText(cinf.get(3).toString());
						}
						
					} catch (IOException | ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			this.getContentPane().add(tf1);
			
			JLabel cusname = new JLabel("Name:");
			cusname.setFont(new Font("Tahoma", Font.PLAIN, 18));
			cusname.setBounds(120, 160, 65, 35);
			this.getContentPane().add(cusname);
			
			tf2 = new JTextField();
			tf2.setBounds(180, 160, 250, 35);
			this.getContentPane().add(tf2);
			
			JLabel cusnum = new JLabel("ContactNo:");
			cusnum.setFont(new Font("Tahoma", Font.PLAIN, 18));
			cusnum.setBounds(440, 160, 100, 35);
			this.getContentPane().add(cusnum);
			
			tf3 = new JTextField();
			tf3.setBounds(540, 160, 160, 35);
			this.getContentPane().add(tf3);
			
			JLabel cuse = new JLabel("Email:");
			cuse.setFont(new Font("Tahoma", Font.PLAIN, 18));
			cuse.setBounds(710, 160, 65, 35);
			this.getContentPane().add(cuse);
			
			tf4 = new JTextField();
			tf4.setBounds(770, 160, 160, 35);
			this.getContentPane().add(tf4);
			
			JLabel cusadd = new JLabel("Address:");
			cusadd.setFont(new Font("Tahoma", Font.PLAIN, 18));
			cusadd.setBounds(940, 160, 85, 35);
			this.getContentPane().add(cusadd);
			
			tf5 = new JTextField();
			tf5.setBounds(1020, 163, 350, 35);
			this.getContentPane().add(tf5);
			
			JLabel clodetail = new JLabel("Clothes Detail:");
			clodetail.setFont(new Font("Tahoma", Font.PLAIN, 20));
			clodetail.setBounds(10, 205, 130, 35);
			this.getContentPane().add(clodetail);
			
			JLabel lb1 = new JLabel("Clothes ID:");
			lb1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lb1.setBounds(10, 250, 100, 35);
			this.getContentPane().add(lb1);
			
			tf6 = new JTextField();
			tf6.setBounds(110, 250, 75, 35);
			tf6.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String cloid = tf6.getText();
					String sql = "SELECT * FROM Clothes WHERE ClothesID= '"+cloid+"'";
					DataOutputStream dos;
					DataInputStream dis;
					try {
						dos = new DataOutputStream(s.getOutputStream());
						dos.writeUTF("12");
						dos.flush();
						dos.writeUTF(sql);
						dos.flush();
						dis = new DataInputStream(s.getInputStream());
						ObjectInputStream ois = new ObjectInputStream(dis);
						
						Vector cusinf = (Vector) ois.readObject();
						if(cusinf.size()==0) {
							JOptionPane.showMessageDialog(null, "Invalid cloth ID");
						}
						else {
							tf7.setText(cusinf.get(0).toString());
							tf8.setText(cusinf.get(1).toString());
							tf9.setText(cusinf.get(2).toString());
						}

						
					} catch (IOException | ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			this.getContentPane().add(tf6);
			
			JLabel lb2 = new JLabel("Clothes Name:");
			lb2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lb2.setBounds(210, 250, 130, 35);
			this.getContentPane().add(lb2);
			
			tf7 = new JTextField();
			tf7.setBounds(340, 250, 200, 35);
			this.getContentPane().add(tf7);
			
			JLabel lb3 = new JLabel("Price:");
			lb3.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lb3.setBounds(560, 250, 50, 35);
			this.getContentPane().add(lb3);
			
			tf8 = new JTextField();
			tf8.setBounds(610, 250, 160, 35);
			this.getContentPane().add(tf8);
			
			JLabel lb4 = new JLabel("Remain Quantity:");
			lb4.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lb4.setBounds(790, 250, 150, 35);
			this.getContentPane().add(lb4);
			
			tf9 = new JTextField();
			tf9.setBounds(950, 250, 175, 35);
			this.getContentPane().add(tf9);
			
			JLabel lb7 = new JLabel("Sell Quantity:");
			lb7.setBounds(1140, 250, 150, 35);
			lb7.setFont(new Font("Tahoma", Font.PLAIN, 18));
			this.getContentPane().add(lb7);
			
			tf11 = new JTextField();
			tf11.setBounds(1260, 250, 100, 35);
			tf11.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			this.getContentPane().add(tf11);
			
			JSeparator s1 = new JSeparator();
			s1.setBounds(1, 90, 10000, 50);
			this.getContentPane().add(s1);
			JSeparator s2 = new JSeparator();
			s2.setBounds(1, 350, 10000, 50);
			this.getContentPane().add(s2);
			//TABLE
			columnsName = new Vector();
			columnsName.addElement("Clothes ID");
			columnsName.addElement("Clothes Name");
			columnsName.addElement("Price");
			columnsName.addElement("Quantity");
			columnsName.addElement("total");
			DefaultTableModel model = new DefaultTableModel(null,columnsName);
			JTable tb = new JTable(model);
			JScrollPane scrolltb = new JScrollPane(tb);
			scrolltb.setBounds(10, 360, 600, 400);
			scrolltb.setBorder(BorderFactory.createRaisedBevelBorder());
			this.getContentPane().add(scrolltb);
		
			JSeparator s3 = new JSeparator();
			s3.setBounds(620, 350, 470, 450);
			s3.setOrientation(SwingConstants.VERTICAL);
			this.getContentPane().add(s3);
			
			JLabel lb5 = new JLabel("Detail");
			lb5.setBounds(630, 360, 310, 80);
			lb5.setFont(new Font("Candara Light", Font.PLAIN, 60));
			this.getContentPane().add(lb5);
			
			JLabel lb6 = new JLabel("Bill Total:");
			lb6.setBounds(630, 440, 100, 50);
			lb6.setFont(new Font("Tahoma", Font.PLAIN, 18));
			this.getContentPane().add(lb6);
			
			JTextField tf10 = new JTextField();
			tf10.setBounds(750, 440, 200, 50);
			this.getContentPane().add(tf10);
			
			JButton addb = new JButton("Add");
			addb.setBackground(Color.PINK);
			addb.setFont(new Font("Tahoma", Font.PLAIN, 18));
			addb.setBounds(1285, 300, 100, 40);
			addb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int remain = Integer.parseInt(tf9.getText());
					int sell = Integer.parseInt(tf11.getText());
					if(remain==0) {
						JOptionPane.showMessageDialog(null, "Empty Stock!!!");
					}
					else
					if(sell>remain) {
						JOptionPane.showMessageDialog(null, "Invalid sell values!!");
					}
					else {
					float price = Float.parseFloat(tf8.getText());
					int quantity = Integer.parseInt(tf11.getText());
					float total = price*quantity;
					DefaultTableModel model = (DefaultTableModel)tb.getModel();
					model.addRow(new Object[] {tf6.getText(),tf7.getText(),price,quantity,total});	
					finaltotal = finaltotal + total;
					tf10.setText(String.valueOf(finaltotal));
					tf9.setText(String.valueOf(remain-sell));
					String query = "EXEC CHECKSL '"+tf6.getText()+"',"+sell;
					DataOutputStream dos;
					try {
						dos = new DataOutputStream(s.getOutputStream());
						dos.writeUTF("13");
						dos.flush();
						dos.writeUTF(query);
						dos.flush();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					}
				}
			});
			this.getContentPane().add(addb);
			
			JButton saveb = new JButton("Save");
			saveb.setBounds(1285, 460, 100, 40);
			saveb.setFont(new Font("Tahoma", Font.PLAIN, 18));
			saveb.setBackground(Color.pink);
			saveb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					SimpleDateFormat sqlf = new SimpleDateFormat("yyyy-MM-dd");
					Date sqld = new Date();
					String date = sqlf.format(sqld);
					String time = t.getText();
					int cid = Integer.parseInt(tf1.getText());
					String cname = tf2.getText();
					float btotal = Float.parseFloat(tf10.getText());
					String newsql = "INSERT INTO BILL(Date,Time,CustomerID,CustomerName,BillTotal)\r\n"
							+ "VALUES ('"+date+"','"+time+"',"+cid+",N'"+cname+"',"+btotal+")";
					try {
						DataOutputStream dos = new DataOutputStream(s.getOutputStream());
						DataInputStream dis = new DataInputStream(s.getInputStream());
						dos.writeUTF("6");
						dos.flush();
						dos.writeUTF(newsql);
						dos.flush();						
						JOptionPane.showMessageDialog(null, dis.readUTF());
						dispose();
					} catch ( IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			this.getContentPane().add(saveb);
			JButton cancelb = new JButton("Cancel");
			cancelb.setBounds(1285, 600, 100, 40);
			cancelb.setBackground(Color.pink);
			cancelb.setFont(new Font("Tahoma", Font.PLAIN, 18));
			cancelb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();
					
				}
			});
			this.getContentPane().add(cancelb);
			this.setSize(1420, 800);
			this.setVisible(true);
		}
	
		
	}
}
