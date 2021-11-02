import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class sqlbrowser {
	public JFrame frmConnection;
	public JTextField tf1;
	public JTextField tf2;
	public JPasswordField pswf;
	public JTable tableResult;
	public Vector vTitle;
	public Vector vData;
	public DefaultTableModel model;
	public JScrollPane resultPane;
	public Vector name;
	public sqlbrowser() {
	
		new login();
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sqlbrowser login = new sqlbrowser();
		
		
	}
	class login extends JFrame {
		JFrame frmConnection;
		
		public login() {
			frmConnection = new JFrame();
			frmConnection.setTitle("Connection");
			frmConnection.setBounds(100, 100, 350, 350);
			frmConnection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frmConnection.getContentPane().setLayout(null);
			
			JLabel lb1 = new JLabel("Username:");
			lb1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lb1.setBounds(10, 34, 140, 30);
			frmConnection.getContentPane().add(lb1);
			
			tf1 = new JTextField();
			tf1.setBounds(119, 34, 191, 29);
			frmConnection.getContentPane().add(tf1);
			tf1.setColumns(10);
			
			JLabel lb2 = new JLabel("Password:");
			lb2.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lb2.setBounds(10, 87, 140, 30);
			frmConnection.getContentPane().add(lb2);
			
			pswf = new JPasswordField();
			pswf.setBounds(119, 87, 191, 29);
			frmConnection.getContentPane().add(pswf);
			frmConnection.setLocation(500,300);
			frmConnection.setVisible(true);
			
			JLabel lb3 = new JLabel("DB Name:");
			lb3.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lb3.setBounds(10, 140, 140, 30);
			frmConnection.getContentPane().add(lb3);
			
			tf2 = new JTextField();
			tf2.setBounds(119, 140, 191, 29);
			frmConnection.getContentPane().add(tf2);
			tf2.setColumns(10);
			
			
			JButton b1 = new JButton("Connect");
			b1.setBounds(200, 250, 100, 20);
			b1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String pass = "sasasa";
					String userpass=pswf.getText();
					if(pswf.getText().equals(pass)) {
						JOptionPane.showMessageDialog(null, "Connected");
						try {
							frmConnection.dispose();
							new sqlinterface();
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} 
					else {
						JOptionPane.showMessageDialog(null, "Wrong Username or Password!!!");
						
					}
					
				}
			});
			frmConnection.getContentPane().add(b1);
			
			JButton b2 = new JButton("Cancel");
			b2.setBounds(60, 250, 100, 20);
			b2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					frmConnection.dispose();
				}
			});
			frmConnection.getContentPane().add(b2);
		}
	}
	class sqlinterface {
		public String Url;
		public String user;
		public String pass;
		JTextArea query;
		JScrollPane resultPane=new JScrollPane();
		JScrollPane queryPane=new JScrollPane(query);
		Connection con;
		//CustomPane configPane;
		public sqlinterface() throws SQLException, ClassNotFoundException {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			String Url = "jdbc:sqlserver://MSI\\VJT:1433;databaseName="+tf2.getText();
			String user = tf1.getText();
			String pass = pswf.getText(); 

			Connection con = DriverManager.getConnection(Url, user, pass);
			Statement stmt = con.createStatement();
			if (con != null)  
				System.out.println("successfuly!");
			else
				System.out.println("Fail");
			
				
			JFrame frmInterface;
			frmInterface = new JFrame();
			frmInterface.setSize(1100, 550);
			frmInterface.getContentPane().setLayout(null);
			
			query = new JTextArea();
			query.setBounds(10, 10, 716, 84);
			frmInterface.getContentPane().add(query);
			query.setColumns(10);
			
			JButton rf = new JButton("Refresh");
			rf.setBounds(758, 10, 85, 38);
			frmInterface.getContentPane().add(rf);
			rf.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					query.setText(null);
					vData.clear();
					vTitle.clear();
					model = new DefaultTableModel(vData,vTitle);
					tableResult = new JTable(model);
					resultPane.setViewportView(tableResult);
					
				}
			});
			
			JButton eb = new JButton("Execute");
			eb.setBounds(758, 56, 85, 38);
			frmInterface.getContentPane().add(eb);
			frmInterface.setLocation(300, 300);
			frmInterface.setVisible(true);
			eb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try{	 
						Statement stmt = con.createStatement();
						if(stmt.execute(query.getText())){
							ResultSet rs=stmt.getResultSet();
							//lay ten cac truong
							ResultSetMetaData rsmd = rs.getMetaData();
							int numberOfColumns = rsmd.getColumnCount();
							vTitle=new Vector(numberOfColumns,0);
							for(int j=1; j<=numberOfColumns;j++) {
								vTitle.add(rsmd.getColumnLabel(j));
							}
							// dua du lieu vao vector vData
							vData=new Vector(10,10);
							while(rs.next()) {
								Vector row=new Vector(numberOfColumns,0);
								for(int i=1; i<=numberOfColumns;i++){ 
									row.add(rs.getObject(i));
								}
								vData.add(row);
							}
							rs.close();
							stmt.close();
							tableResult=new JTable(vData,vTitle);
							resultPane.setViewportView(tableResult);
							}
						 else {
							int updateCount=stmt.getUpdateCount();
							JOptionPane.showMessageDialog(null,"Da cap nhat "+ updateCount+" ban ghi");
						}
						} catch(Exception exception)
					{
							System.out.println("Error " + e); 
							}
				}
			});
			
			DefaultMutableTreeNode schema =new DefaultMutableTreeNode("MSI/Vjt");
			JTree jtree = new JTree(schema);
			try {
		        DatabaseMetaData dbmt =  con.getMetaData();
		        ResultSet rs = dbmt.getCatalogs();
				while(rs.next()) {
					DefaultMutableTreeNode databasename = new DefaultMutableTreeNode(rs.getString("TABLE_CAT"));
					
					String link = "jdbc:sqlserver://MSI\\\\VJT:1433;databaseName=" + rs.getString("TABLE_CAT");
					Connection conn = DriverManager.getConnection(link,tf1.getText(),pswf.getText());
					DatabaseMetaData md1 = conn.getMetaData();
					String table[] = { "TABLE" };
					ResultSet rstb = md1.getTables(null, null, null, table);
					while(rstb.next()) {
						DefaultMutableTreeNode tablename = new DefaultMutableTreeNode(rstb.getString(3));
						databasename.add(tablename);
					}
					schema.add(databasename);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			jtree.addTreeSelectionListener(new TreeSelectionListener() {
				
				@Override
				public void valueChanged(TreeSelectionEvent e) {
					// TODO Auto-generated method stub
					DefaultMutableTreeNode selected=  (DefaultMutableTreeNode) jtree.getLastSelectedPathComponent();
					int pos = e.getPath().getPathCount();
					String tbname = selected.getUserObject().toString();
					if(pos==3) {
						try {
							String queryx = "select * from " + tbname;
							Connection con = DriverManager.getConnection(Url, user, pass);
							Statement stmt = con.createStatement();
							if(stmt.execute(queryx)) {
								ResultSet rs=stmt.getResultSet();
								//lay ten cac truong
								ResultSetMetaData rsmd = rs.getMetaData();
								int numberOfColumns = rsmd.getColumnCount();
								vTitle=new Vector(numberOfColumns,0);
								for(int j=1; j<=numberOfColumns;j++) {
									vTitle.add(rsmd.getColumnLabel(j));
								}
								// dua du lieu vao vector vData
								vData=new Vector(10,10);
								while(rs.next()) {
									Vector row=new Vector(numberOfColumns,0);
									for(int i=1; i<=numberOfColumns;i++){ 
										row.add(rs.getObject(i));
									}
									vData.add(row);
								}
								rs.close();
								stmt.close();
							}
							model = new DefaultTableModel(vData,vTitle);
							tableResult=new JTable(model);
							resultPane.setViewportView(tableResult);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
				}
			});
			JScrollPane treeview = new JScrollPane(jtree);
			treeview.setBounds(860, 100, 200, 400);
			frmInterface.getContentPane().add(treeview);
			frmInterface.getContentPane().add(resultPane);
			frmInterface.getContentPane().add(queryPane);
			resultPane.setBounds(10, 100, 840, 400);
		}
	}
}
 