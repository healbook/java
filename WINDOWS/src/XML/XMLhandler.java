package XML;

import java.awt.Font;
import java.awt.event.ActionEvent;	
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;






public class XMLhandler {
	public JTable tb;
	public DefaultTableModel model;
	public String tbname;
	public Connection con;
	public Statement stmt;
	public static void main(String[] args) {
		
		try {
			new XMLhandler();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	
		
	}
	public XMLhandler() throws ClassNotFoundException, SQLException {
		Vector data = new Vector();
		Vector<String> columnNames = new Vector<String>();
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		String Url = "jdbc:sqlserver://MSI\\VJT:1433;databaseName=XML";
		  String user = "sa";
		  String pass ="sasasa"; 

		Connection con = DriverManager.getConnection(Url, user, pass);
		Statement stmt = con.createStatement();
		if (con != null)  
			System.out.println("successfuly!");
		else
			System.out.println("Fail");
		
        new SQLput();
		
	//
		JFrame f;
		f = new JFrame();
		f.setSize(1080,812);
		f.getContentPane().setLayout(null);
		
		
		
			try {
			    stmt = con.createStatement();
			    ResultSet rs = stmt.executeQuery("SELECT * FROM xml1");
			    ResultSetMetaData metaData = rs.getMetaData();
			    int columns = metaData.getColumnCount();
			    while (rs.next()) {
			       Vector row = new Vector(columns);
			       for (int i = 1; i <= columns; i++) {
			        row.addElement(rs.getObject(i));
			       }
			       data.addElement(row);
			    }
			  
			} catch (SQLException e) {
			    e.printStackTrace();
			}
			try {
				stmt = con.createStatement();
			    ResultSet rs = stmt.executeQuery("SELECT * FROM xml1");
			    ResultSetMetaData metaData = rs.getMetaData();
			    int columns = metaData.getColumnCount();
			    for (int i = 1; i <= columns; i++) {
			    	columnNames.addElement(metaData.getColumnName(i));
			    }
			} catch(SQLException e) {
				e.printStackTrace();
			}
		
			
			model = new DefaultTableModel(data, columnNames);
		JTable tb = new JTable(model);
		tb.setDefaultEditor(Object.class, null);
		
		//
		JScrollPane sctb = new JScrollPane(tb);
		sctb.setViewportView(tb);
		sctb.setBounds(10, 70, 1050, 500);
		f.getContentPane().add(sctb);
		
		JButton b2 = new JButton("Add Node");
		b2.setBounds(113, 599, 159, 48);
		f.getContentPane().add(b2);
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					new addnode();
					model.fireTableDataChanged();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		
		JButton b3 = new JButton("Delete Node");
		b3.setBounds(467, 599, 159, 48);
		b3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = tb.getModel().getValueAt(tb.getSelectedRow(), 0).toString();
				delete(id);
				try {
					PreparedStatement prmt = con.prepareStatement("delete from xml1 where id='"+id+"'");
					prmt.execute();
					JOptionPane.showMessageDialog(null, "deleted");
				}
				catch(Exception d) {
					d.printStackTrace();
				}
			}
		});
		f.getContentPane().add(b3);
		
		JButton b4 = new JButton("Edit Node");
		b4.setBounds(809, 599, 159, 48);
		b4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int srow = tb.getSelectedRow();
				if(srow==-1) {
					JOptionPane.showMessageDialog(null, "Select node to edit in the table!!");
				}
				else {
					String sid = tb.getModel().getValueAt(srow, 0).toString();
					try {
						new modifynode(sid);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
					
				
			}
		});
		f.getContentPane().add(b4);
		
		JButton b5 = new JButton("Refresh table");
		b5.setBounds(113, 10, 302, 48);
		b5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				data.clear();
		
				try {
				   Statement stmt = con.createStatement();
				    ResultSet rs = stmt.executeQuery("SELECT * FROM xml1");
				    ResultSetMetaData metaData = rs.getMetaData();
				    int columns = metaData.getColumnCount();
				    while (rs.next()) {
				       Vector row = new Vector(columns);
				       for (int i = 1; i <= columns; i++) {
				        row.addElement(rs.getObject(i));
				       }
				       data.addElement(row);
				    }
				  
				} catch (SQLException f) {
				    f.printStackTrace();
				}
				tb.setModel(new DefaultTableModel(data,columnNames));
				
			}
		});
		f.getContentPane().add(b5);
		f.setVisible(true);
		
	}
	public static void delete(String id) {
		String xmlFile = "V:\\Java\\employee.xml";
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(xmlFile);
			NodeList employees = document.getElementsByTagName("employee");
			for (int i = 0; i < employees.getLength(); i++) {
				Element employee = (Element) employees.item(i);
				Element idTag = (Element) employee.getElementsByTagName("id").item(0);
				if (idTag.getTextContent().equalsIgnoreCase(id)) {
					idTag.getParentNode().getParentNode().removeChild(employees.item(i));
					break;
				}
			}
			saveXMLContent(document, xmlFile);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public static void saveXMLContent(Document document, String xmlFile) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(xmlFile);
			transformer.transform(domSource, streamResult);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	public static void modify(String id) {
		
	}
	
}
