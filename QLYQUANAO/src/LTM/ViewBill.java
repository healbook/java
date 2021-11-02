package LTM;

import java.awt.Font;
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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class ViewBill {
		public ViewBill(Socket s) throws ClassNotFoundException, SQLException, IOException {
		if(s.isClosed()) {
			
		}
		else
		if(s.isConnected()) 
		{
			Vector billdata = new Vector();
			Vector columns = new Vector();
			JFrame f = new JFrame("View Bill");
			f = new JFrame();
			f.setVisible(true);	
			f.setSize(800, 700);
			f.getContentPane().setLayout(null);
			ImageIcon ic = new ImageIcon("Image/bill.png");
			JLabel title = new JLabel("All Bill");
			title.setIcon(ic);
			title.setVerticalTextPosition(SwingConstants.CENTER);
			title.setFont(new Font("Candara Light", Font.PLAIN, 70));
			title.setBounds(200, 30, 310, 80);
			f.getContentPane().add(title);
			
			//TAble data
			columns.addElement("Bill_ID");
			columns.addElement("Date");
			columns.addElement("Time");
			columns.addElement("CustomerID");
			columns.addElement("CustomerName");
			columns.addElement("BillTotal");
			
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF("7");
			dos.flush();
			dos.writeUTF("SELECT * From Bill");
			dos.flush();
			DataInputStream dis = new DataInputStream(s.getInputStream());
			ObjectInputStream ois = new ObjectInputStream(dis);
			billdata = (Vector) ois.readObject();
			DefaultTableModel model = new DefaultTableModel(billdata,columns);
			JTable bill = new JTable(model);
			bill.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			JScrollPane scrolltb = new JScrollPane(bill);
			scrolltb.setBounds(100, 155, 600, 300);	
			scrolltb.setBorder(BorderFactory.createRaisedBevelBorder());
			f.getContentPane().add(scrolltb);
			
		}
			
	}
}
