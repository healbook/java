package DoAn;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class Calculator {
	Connection con;
	Statement stmt;
	public Calculator() throws SQLException, ClassNotFoundException {
		//CONNECTION
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		String Url = "jdbc:sqlserver://MSI\\VJT:1433;databaseName=Qlyshop";
		String user = "sa";
		String pass ="sasasa"; 

		con = DriverManager.getConnection(Url, user, pass);
		stmt = con.createStatement();
		//
		JFrame f = new JFrame("Profit Calculator");
		f.setSize(600, 680);
		f.getContentPane().setLayout(null);
		f.setVisible(true);
		f.setLocation(400,50);
		
		ImageIcon ic1 = new ImageIcon("Image/cal.jpg");
		JLabel lb1 = new JLabel("Profit Calculator");
		lb1.setIcon(ic1);
		lb1.setVerticalTextPosition(SwingConstants.CENTER);
		lb1.setBounds(40, 30, 600, 80);
		lb1.setFont(new Font("Candara Light", Font.PLAIN, 60));
		f.getContentPane().add(lb1);
		
		JLabel lb2 = new JLabel("Capital(Money that used to buy Clothes):");
		lb2.setBounds(130, 130, 600, 40);
		lb2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		f.getContentPane().add(lb2);
		
		JLabel lb3 = new JLabel();
		lb3.setBounds(200, 180, 200, 40);
		lb3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		f.getContentPane().add(lb3);
		
		JLabel lb4 = new JLabel("Refund(Money earned from sold clothes:");
		lb4.setBounds(130, 240, 600, 40);
		lb4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		f.getContentPane().add(lb4);
		
		JLabel lb5 = new JLabel();
		lb5.setBounds(200, 290, 200, 40);
		lb5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		f.getContentPane().add(lb5);
		
		JLabel lb6 = new JLabel("Earning(Profit from selling clothes):");
		lb6.setBounds(130, 350, 600, 40);
		lb6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		f.getContentPane().add(lb6);
		
		JLabel lb7 = new JLabel();
		lb7.setBounds(200, 400, 200, 40);
		lb7.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		f.getContentPane().add(lb7);
		
		JLabel lb9 = new JLabel();
		lb9.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lb9.setBounds(50, 500, 600, 50);
		f.getContentPane().add(lb9);
		
		lb3.setHorizontalAlignment(SwingConstants.CENTER);
		lb5.setHorizontalAlignment(SwingConstants.CENTER);
		lb7.setHorizontalAlignment(SwingConstants.CENTER);
		
		String sql = "EXEC CAL";
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
		lb3.setText(String.valueOf(String.format("%.4f", rs.getDouble(1))));
		lb5.setText(String.valueOf(String.format("%.4f", rs.getDouble(2))));
		lb7.setText(String.valueOf(String.format("%.4f", rs.getDouble(3))));
		}
		float profit = rs.getFloat(3);
		if(profit<0) {
			lb9.setText("Your capital is losing,Continue selling for more profit!!!");
		}
		else
		if(profit==0) {
			lb9.setText("Your capital is breakeven");
		}
		else
		if(profit>0) {
			lb9.setText("Congratulations,Your bussiness is gaining profit!!");
		}
		JButton close = new JButton("Close");
		close.setBounds(240, 570, 100, 40);
		close.setBackground(Color.pink);
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f.dispose();
			}
		});
		f.getContentPane().add(close);
		f.setTitle("Profit Calculator");
	}
}
