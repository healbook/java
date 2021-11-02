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

import com.sun.net.httpserver.Authenticator.Result;

public class UpdateClothes {
	private static JTextField tf1;
	private static JTextField tf2;
	private static JTextField tf3;
	private static JTextField tf4;
	private static JTextField tf5;
	private static JTextField tf6;
	private static JTextField tf7;
	Connection con;
	Statement stmt;
	public UpdateClothes() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		String Url = "jdbc:sqlserver://MSI\\VJT:1433;databaseName=Qlyshop";
		String user = "sa";
		String pass ="sasasa";
		con = DriverManager.getConnection(Url, user, pass);
		stmt = con.createStatement();
		
		JFrame f;
		f = new JFrame();
		f.setTitle("Update Clothes");
		f.getContentPane().setLayout(null);
		
		JLabel ulb = new JLabel("Update Clothes");
		ulb.setFont(new Font("Candara Light", Font.BOLD, 38));
		ulb.setBounds(120, 10, 300, 46);
		f.getContentPane().add(ulb);
		
		JLabel lb1 = new JLabel("Clothes ID");
		lb1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lb1.setBounds(10, 90, 140, 40);
		f.getContentPane().add(lb1);
		
		tf1 = new JTextField();
		tf1.setBounds(180, 90, 215, 40);
		f.getContentPane().add(tf1);
		
		JButton search = new JButton("Search");
		search.setFont(new Font("Tahoma", Font.PLAIN, 15));
		search.setBounds(420, 90, 85, 40);
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String cloid = tf1.getText();
				String query = "SELECT * FROM Clothes WHERE ClothesID = '"+cloid+"'";
				try {
					ResultSet rs = stmt.executeQuery(query);
					if(rs.next()) {
						tf2.setText(rs.getString(1));
						tf3.setText(rs.getString(2));
						tf4.setText(String.valueOf(rs.getFloat(3)));
						tf5.setText(String.valueOf(rs.getFloat(4)));
						tf6.setText(String.valueOf(rs.getInt(5)));
						tf7.setText(String.valueOf(rs.getInt(6)));
						
					}
					else
						JOptionPane.showMessageDialog(null, "Clothes ID does not exist!!!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		f.getContentPane().add(search);
		
		JLabel lb2 = new JLabel("Clothes ID");
		lb2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lb2.setBounds(10, 165, 100, 50);
		f.getContentPane().add(lb2);
		
		JSeparator se1 = new JSeparator();
		se1.setBounds(0, 150, 540, 2);
		f.getContentPane().add(se1);
		
		tf2 = new JTextField();
		tf2.setBounds(220, 165, 215, 40);
		f.getContentPane().add(tf2);
		
		JLabel lb7 = new JLabel("Clothes Name");
		lb7.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lb7.setBounds(10, 220, 150, 50);
		f.getContentPane().add(lb7);
		
		tf3 = new JTextField();
		tf3.setBounds(220, 220, 215, 40);
		f.getContentPane().add(tf3);
		
		JLabel lb3 = new JLabel("Buy Price");
		lb3.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lb3.setBounds(10, 283, 140, 50);
		f.getContentPane().add(lb3);
		
		JLabel lb4 = new JLabel("Sell Price");
		lb4.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lb4.setBounds(10, 343, 140, 50);
		f.getContentPane().add(lb4);
		
		JLabel lb5 = new JLabel("Buy Quantity");
		lb5.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lb5.setBounds(10, 403, 140, 50);
		f.getContentPane().add(lb5);
		
		JLabel lb6 = new JLabel("Remain Quantity");
		lb6.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lb6.setBounds(10, 463, 163, 50);
		f.getContentPane().add(lb6);
		
		tf4 = new JTextField();
		tf4.setBounds(220, 280, 215, 40);
		f.getContentPane().add(tf4);
		
		tf5 = new JTextField();
		tf5.setColumns(10);
		tf5.setBounds(220, 340, 215, 40);
		f.getContentPane().add(tf5);
		
		tf6 = new JTextField();
		tf6.setBounds(220, 400, 215, 40);
		f.getContentPane().add(tf6);
		
		tf7 = new JTextField();
		tf7.setBounds(220, 460, 215, 40);
		f.getContentPane().add(tf7);
		
		JButton updateb = new JButton("Update");
		updateb.setBounds(75, 540, 100, 50);
		updateb.setBackground(Color.PINK);
		updateb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String sid = tf1.getText();
				String cloid = tf2.getText();
				String cloname = tf3.getText();
				float buyp = Float.parseFloat(tf4.getText());
				float sellp = Float.parseFloat(tf5.getText());
				int buyq = Integer.parseInt(tf6.getText());
				int remainq = Integer.parseInt(tf7.getText());
				
				String upquery = "EXEC CLOUPDATE '"+sid+"','"+cloid+"','"+cloname+"',"+buyp+","+sellp+","+buyq+","+remainq;
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
		cancelb.setBounds(340, 540, 100, 50);
		cancelb.setBackground(Color.pink);
		cancelb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				
			}
		});
		f.getContentPane().add(cancelb);
		f.setVisible(true);
		f.setSize(550, 650);
		
	}
}
