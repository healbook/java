import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.util.*;

public class DBBrowser extends JFrame implements ActionListener,LayoutManager{
	JTextArea queryText=new JTextArea(5,20);
	JScrollPane resultPane=new JScrollPane();
	JScrollPane queryPane=new JScrollPane(queryText);
	JButton cmdConfig=new JButton("Config");
	JButton cmdSub=new JButton("Submit");
	Connection con;
	//CustomPane configPane;
	JDialog configDialog;

	public DBBrowser(){
		setTitle("Database Browser");
		getContentPane().setLayout(this);
		getContentPane().add(queryPane);
		getContentPane().add(resultPane);
		getContentPane().add(cmdConfig);
		getContentPane().add(cmdSub);
		cmdConfig.addActionListener(this);
		cmdSub.addActionListener(this);
		setSize(500,350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createConfigDialog();
	}

	public void actionPerformed(ActionEvent e){

	}
	public void addLayoutComponent(String name,Component comp){}
	public void removeLayoutComponent(Component comp){}
	public Dimension preferredLayoutSize(Container parent){return null;}
	public Dimension minimumLayoutSize(Container parent){return null;}
	public void layoutContainer(Container parent){
		int w=parent.getWidth();
		int	h=parent.getHeight();
		int g=7;
		cmdConfig.setBounds(w-120-g,g,120,25);
		cmdSub.setBounds(w-120-g,25+2*g,120,25);
		queryPane.setBounds(g,g,w-120-3*g,90);
		resultPane.setBounds(g,90+2*g,w-2*g,h-90-3*g);
	}
	public void createConfigDialog(){
	
	}
	public void connect(){
		
	}
	public void executeStatement(String sql){
		try{	 
		Statement stmt = con.createStatement();
		if(stmt.execute(sql)){
			ResultSet rs=stmt.getResultSet();
			//lay ten cac truong
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			Vector vTitle=new Vector(numberOfColumns,0);
			for(int j=1; j<=numberOfColumns;j++) {
				vTitle.add(rsmd.getColumnLabel(j));
			}
			// dua du lieu vao vector vData
			Vector vData=new Vector(10,10);
			while(rs.next()) {
				Vector row=new Vector(numberOfColumns,0);
				for(int i=1; i<=numberOfColumns;i++){ 
					row.add(rs.getObject(i));
				}
				vData.add(row);
			}
			rs.close();
			stmt.close();
			JTable tableResult=new JTable(vData,vTitle);
			resultPane.setViewportView(tableResult);
			}
		 else {
			int updateCount=stmt.getUpdateCount();
			JOptionPane.showMessageDialog(this,"Da cap nhat "+ updateCount+" ban ghi");
		}
		} catch(Exception e){  System.out.println("Error " + e); }
	}

	public static void main(String[] args) 
	{
		new DBBrowser().show();
	}
}
