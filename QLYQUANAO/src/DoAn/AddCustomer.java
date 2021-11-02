package DoAn;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddCustomer {
	private static JTextField tf1;
	private static JTextField tf2;
	private static JTextField tf3;
	private static JTextField tf4;
	Connection con;
	Statement stmt;
	public AddCustomer() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		String Url = "jdbc:sqlserver://MSI\\VJT:1433;databaseName=Qlyshop";
		String user = "sa";
		String pass ="sasasa"; 

		con = DriverManager.getConnection(Url, user, pass);
		stmt = con.createStatement();
		JFrame f;
		f = new JFrame();
		f.getContentPane().setLayout(null);
		
		JLabel clb = new JLabel("New Customer");
		clb.setFont(new Font("Candara Light", Font.BOLD, 38));
		clb.setBounds(110, 10, 275, 40);
		f.getContentPane().add(clb);
		
		JLabel lb1 = new JLabel("Name:");
		lb1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lb1.setBounds(49, 98, 105, 40);
		f.getContentPane().add(lb1);
		
		tf1 = new JTextField();
		tf1.setBounds(188, 98, 230, 40);
		f.getContentPane().add(tf1);
		tf1.setColumns(10);
		
		JLabel lb2 = new JLabel("ContactNumber:");
		lb2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lb2.setBounds(49, 148, 118, 40);
		f.getContentPane().add(lb2);
		
		JLabel lb3 = new JLabel("Email:");
		lb3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lb3.setBounds(49, 198, 105, 40);
		f.getContentPane().add(lb3);
		
		JLabel lb4 = new JLabel("Address:");
		lb4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lb4.setBounds(49, 248, 105, 40);
		f.getContentPane().add(lb4);
		
		tf2 = new JTextField();
		tf2.setColumns(10);
		tf2.setBounds(188, 148, 230, 40);
		f.getContentPane().add(tf2);
		
		tf3 = new JTextField();
		tf3.setColumns(10);
		tf3.setBounds(188, 198, 230, 40);
		f.getContentPane().add(tf3);
		
		tf4 = new JTextField();
		tf4.setColumns(10);
		tf4.setBounds(188, 248, 230, 90);
		f.getContentPane().add(tf4);
		
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
					PreparedStatement prstmt = con.prepareStatement(query);
					prstmt.execute();
					JOptionPane.showMessageDialog(null, "Add Successfully,Press refresh to apply change");
					f.dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		f.getContentPane().add(addb);
		
		JButton cb = new JButton("Cancel");
		cb.setBackground(Color.PINK);
		cb.setBounds(312, 380, 105, 40);
		cb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				
			}
		});
		f.getContentPane().add(cb);
		f.setSize(500, 550);
		f.setTitle("New Customer");
		f.setVisible(true);
	}
}
