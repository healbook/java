package LTM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class Search {
	Connection con;
	Statement stmt;
	Vector list;
	public Search(Socket s) throws Exception {
	if(s.isConnected()) {
		Vector data1 = new Vector();
		Vector data2 = new Vector();
		Vector data3 = new Vector();
		Vector columnsName1 = new Vector();
		Vector columnsName2 = new Vector();
		Vector columnsName3 = new Vector();
		JFrame f = new JFrame("Search");
		f = new JFrame();
		f.setVisible(true);	
		f.setSize(800, 700);
		f.getContentPane().setLayout(null);
		
		JTabbedPane tp = new JTabbedPane();
		JPanel pn1 = new JPanel();
		pn1.setLayout(null);
		JPanel pn2 = new JPanel();
		pn2.setLayout(null);
		JPanel pn3 = new JPanel();
		pn3.setLayout(null);
		tp.addTab("Clothes", null, pn1, "Click to show search result of clothes");
		tp.addTab("Customer", null, pn2, "Click to show search result of customer");
		tp.addTab("Bill", null, pn3, "Click to show search result of bill");
		tp.setBounds(0, 200, 800, 400);
		tp.setTabPlacement(JTabbedPane.TOP);
		f.getContentPane().add(tp);
		
		ImageIcon ic1 = new ImageIcon("Image/search.png");
		JLabel lb1 = new JLabel();
		lb1.setBounds(60, 80, 80, 40);
		lb1.setIcon(ic1);
		f.getContentPane().add(lb1);
		
		
		
		
		JTextField tf1 = new JTextField("Enter thing to search");
		tf1.setBounds(100, 80, 400, 40);
		tf1.setForeground(Color.LIGHT_GRAY);
		tf1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tf1.addFocusListener(new FocusListener() {
			
			@Override	
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(tf1.getText().equals("")) {
					tf1.setText("Enter thing to search");
					tf1.setForeground(new Color(153,153,153));
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(tf1.getText().equals("Enter thing to search")) {
					tf1.setText("");
					tf1.setForeground(new Color(0,0,0));
				}
			}
		});
		columnsName1.addElement("Clothes ID");
		columnsName1.addElement("Clothes Name");
		columnsName1.addElement("Buy Price");
		columnsName1.addElement("Sell Price");
		columnsName1.addElement("Buy Quantity");
		columnsName1.addElement("Remain Quantity");
		
		DefaultTableModel model1 = new DefaultTableModel(null,columnsName1);
		JTable tb1 = new JTable(model1);
		JScrollPane sp1 = new JScrollPane(tb1);
		sp1.setBounds(0, 0, 785, 400);
		pn1.add(sp1);
		
		columnsName2.addElement("Customer ID");
		columnsName2.addElement("Customer Name");
		columnsName2.addElement("Contact Number");
		columnsName2.addElement("Email");
		columnsName2.addElement("Address");		
		
		DefaultTableModel model2 = new DefaultTableModel(null,columnsName2);;
		JTable tb2 = new JTable(model2);
		JScrollPane sp2 = new JScrollPane(tb2);
		sp2.setBounds(0, 0, 785, 400);
		pn2.add(sp2);
		
		columnsName3.addElement("Bill ID");
		columnsName3.addElement("Date");
		columnsName3.addElement("Time");
		columnsName3.addElement("Customer ID");
		columnsName3.addElement("Customer Name");
		columnsName3.addElement("BillTotal");
		
		DefaultTableModel model3 = new DefaultTableModel(null,columnsName3);
		JTable tb3 = new JTable(model3);
		JScrollPane sp3 = new JScrollPane(tb3);
		sp3.setBounds(0, 0, 785, 400);
		pn3.add(sp3);
		
		
		
		JButton search = new JButton("Search");
		search.setBounds(550, 80, 100, 40);
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					DataOutputStream dos = new DataOutputStream(s.getOutputStream());
					dos.writeUTF("8");
					String rs1 = "EXEC SClothes '"+tf1.getText()+"'";
					dos.writeUTF(rs1);
					dos.flush();
					String rs2 = "EXEC SCustomer '"+tf1.getText()+"'";
					dos.writeUTF(rs2);
					dos.flush();
					String rs3 = "EXEC SBill '"+tf1.getText()+"'";
					dos.writeUTF(rs3);
					dos.flush();
					DataInputStream dis = new DataInputStream(s.getInputStream());
					ObjectInputStream ois = new ObjectInputStream(dis);
					list = (Vector) ois.readObject();
					Vector sdata1 = (Vector) list.get(0);			
					Vector sdata2 = (Vector) list.get(1);
					Vector sdata3 = (Vector) list.get(2);
					if(sdata1.size()==0&&sdata2.size()==0&&sdata3.size()==0) {
						JOptionPane.showMessageDialog(null, "Nothing was found");
					}
					else {
						tb1.setModel(new DefaultTableModel(sdata1,columnsName1));
						tb2.setModel(new DefaultTableModel(sdata2,columnsName2));
						tb3.setModel(new DefaultTableModel(sdata3,columnsName3));
					}
				} catch (IOException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		f.getContentPane().add(search);
		
		f.getContentPane().add(tf1);
		f.requestFocusInWindow();
		f.setVisible(true);
		tb1.setDefaultEditor(Object.class, null);
		tb2.setDefaultEditor(Object.class, null);
		tb3.setDefaultEditor(Object.class, null);
	}
	}
}
