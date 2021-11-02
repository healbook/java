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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AddButton  {
	
	private static JTextField idtf;
	private static JTextField nametf;
	private static JTextField buytf;
	private static JTextField selltf;
	private static JTextField q1tf;
	private static JTextField q2tf;
	Connection con;
	Statement stmt;
	public AddButton() throws SQLException, ClassNotFoundException{
		JFrame f;
		f = new JFrame();
		f.setTitle("Add Clothes");
		f.getContentPane().setLayout(null);
		f.setSize(500, 550);
		JLabel titlelb = new JLabel("Enter the information of new Clothes:");
		titlelb.setFont(new Font("Candara Light", Font.BOLD, 21));
		titlelb.setBounds(52, 10, 400, 40);
		f.getContentPane().add(titlelb);
		
		JLabel idlb = new JLabel("Clothes ID:");
		idlb.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idlb.setBounds(10, 90, 105, 40);
		f.getContentPane().add(idlb);
		
		idtf = new JTextField();
		idtf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		idtf.setBounds(189, 89, 230, 40);
		f.getContentPane().add(idtf);
		idtf.setColumns(10);
		
		JLabel namelb = new JLabel("Clothes Name:");
		namelb.setFont(new Font("Tahoma", Font.PLAIN, 15));
		namelb.setBounds(10, 140, 105, 40);
		f.getContentPane().add(namelb);
		
		nametf = new JTextField();
		nametf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nametf.setColumns(10);
		nametf.setBounds(189, 140, 230, 40);
		f.getContentPane().add(nametf);
		
		JLabel buylb = new JLabel("Buy Price:");
		buylb.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buylb.setBounds(10, 190, 105, 40);
		f.getContentPane().add(buylb);
		
		buytf = new JTextField();
		buytf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buytf.setColumns(10);
		buytf.setBounds(189, 190, 230, 40);
		f.getContentPane().add(buytf);
		
		JLabel selllb = new JLabel("Sell Price:");
		selllb.setFont(new Font("Tahoma", Font.PLAIN, 15));
		selllb.setBounds(10, 240, 105, 40);
		f.getContentPane().add(selllb);
		
		selltf = new JTextField();
		selltf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		selltf.setColumns(10);
		selltf.setBounds(189, 240, 230, 40);
		f.getContentPane().add(selltf);
		
		JLabel quantity1 = new JLabel("Buy Quantity:");
		quantity1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		quantity1.setBounds(10, 290, 105, 40);
		f.getContentPane().add(quantity1);
		
		JLabel quantity2 = new JLabel("Remain Quantity:");
		quantity2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		quantity2.setBounds(10, 340, 125, 40);
		f.getContentPane().add(quantity2);
		
		q1tf = new JTextField();
		q1tf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		q1tf.setColumns(10);
		q1tf.setBounds(189, 290, 230, 40);
		f.getContentPane().add(q1tf);
		
		q2tf = new JTextField();
		q2tf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		q2tf.setColumns(10);
		q2tf.setBounds(189, 340, 230, 40);
		f.getContentPane().add(q2tf);
		
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
			
				String addquery = "INSERT INTO Clothes Values('"+id+"',"+"N'"+name+"',"+buy+","+sell+","+q1+","+q2+")";
				try {
					
					PreparedStatement prstmt = con.prepareStatement(addquery);
					prstmt.execute();
					JOptionPane.showMessageDialog(null, "Add Successfully, Press refresh to apply change");
					f.dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		f.getContentPane().add(addb);
		
		JButton cancelb = new JButton("Cancel");
		cancelb.setBackground(Color.PINK);
		cancelb.setBounds(290, 443, 105, 40);
		cancelb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				
			}
		});
		f.getContentPane().add(cancelb);
		
		f.setVisible(true);
		f.setAlwaysOnTop(true);
	}
}
