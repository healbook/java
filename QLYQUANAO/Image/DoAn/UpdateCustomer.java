package DoAn;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class UpdateCustomer {
	private static JTextField tf1;
	private static JTextField tf2;
	private static JTextField tf3;
	private static JTextField tf4;
	private static JTextField tf5;
	private static JTextField tf6;
	Connection con;
	Statement stmt;
	public UpdateCustomer() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		String Url = "jdbc:sqlserver://MSI\\VJT:1433;databaseName=Qlyshop";
		String user = "sa";
		String pass ="sasasa"; 

		con = DriverManager.getConnection(Url, user, pass);
		stmt = con.createStatement();
		JFrame f;
		f = new JFrame();
		f.setTitle("Update Customer");
		f.getContentPane().setLayout(null);
		
		JLabel ulb = new JLabel("Update Customer");
		ulb.setFont(new Font("Candara Light", Font.BOLD, 38));
		ulb.setBounds(100, 10, 350, 46);
		f.getContentPane().add(ulb);
		
		JLabel lb1 = new JLabel("ID");
		lb1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lb1.setBounds(10, 90, 140, 40);
		f.getContentPane().add(lb1);
		
		tf1 = new JTextField();
		tf1.setBounds(180, 90, 215, 40);
		f.getContentPane().add(tf1);
		tf1.setColumns(10);
		
		JButton search = new JButton("Search");
		search.setFont(new Font("Tahoma", Font.PLAIN, 15));
		search.setBounds(420, 90, 85, 40);
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int cid =Integer.parseInt(tf1.getText());
				String cid1 = tf1.getText();
				String query = "SELECT * FROM Customer WHERE CustomerID = '"+cid+"'";
					try {
						ResultSet rs = stmt.executeQuery(query);
						if(rs.next()) {
							tf2.setEditable(false);
							tf2.setText(rs.getString(1));
							tf3.setText(rs.getString(2));
							tf4.setText(rs.getString(3));
							tf5.setText(rs.getString(4));
							tf6.setText(rs.getString(5));
						}
						else 
							JOptionPane.showMessageDialog(null, "ID does not exist");
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				
			}
		});
		f.getContentPane().add(search);
		
		JLabel lb2 = new JLabel("ID");
		lb2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lb2.setBounds(10, 165, 100, 50);
		f.getContentPane().add(lb2);
		
		JSeparator se1 = new JSeparator();
		se1.setBounds(0, 153, 546, 2);
		f.getContentPane().add(se1);
		
		tf2 = new JTextField();
		tf2.setBounds(180, 165, 215, 40);
		f.getContentPane().add(tf2);
		
		JLabel lb3 = new JLabel("Name");
		lb3.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lb3.setBounds(10, 225, 100, 50);
		f.getContentPane().add(lb3);
		
		JLabel lb4 = new JLabel("Contact Number");
		lb4.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lb4.setBounds(10, 285, 140, 50);
		f.getContentPane().add(lb4);
		
		JLabel lb5 = new JLabel("Email");
		lb5.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lb5.setBounds(10, 345, 100, 50);
		f.getContentPane().add(lb5);
		
		JLabel lb6 = new JLabel("Address");
		lb6.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lb6.setBounds(10, 405, 100, 50);
		f.getContentPane().add(lb6);
		
		tf3 = new JTextField();
		tf3.setBounds(180, 225, 215, 40);
		f.getContentPane().add(tf3);
		
		tf4 = new JTextField();
		tf4.setBounds(180, 285, 215, 40);
		f.getContentPane().add(tf4);
		
		tf5 = new JTextField();
		tf5.setBounds(180, 345, 215, 40);
		f.getContentPane().add(tf5);
		
		tf6 = new JTextField();
		tf6.setBounds(180, 405, 215, 40);
		f.getContentPane().add(tf6);
		
		JButton updateb = new JButton("Update");
		updateb.setBounds(70, 495, 100, 50);
		updateb.setBackground(Color.pink);
		updateb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int cid = Integer.parseInt(tf2.getText());
				String name = tf3.getText();
				String contactno = tf4.getText();
				String email = tf5.getText();
				String address = tf6.getText();
				String upquery = "EXEC CUSUPDATE "+cid+",'"+name+"','"+contactno+"','"+email+"','"+address+"'";
				try {
					PreparedStatement prstmt = con.prepareStatement(upquery);
					prstmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Update Successfully,Press refresh to apply change");
					f.dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		f.getContentPane().add(updateb);
		
		JButton cancelb = new JButton("Cancel");
		cancelb.setBounds(340, 495, 100, 50);
		cancelb.setBackground(Color.pink);
		cancelb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				
			}
		});
		f.getContentPane().add(cancelb);
		f.setSize(550, 610);
		f.setVisible(true);
	}
}
