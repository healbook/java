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
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;





public class addnode {
	private static JTextField tf1;
	private static JTextField tf2;
	private static JTextField tf3;
	private static JTextField tf4;
	private static JTextField tf5;
	public addnode() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		String Url = "jdbc:sqlserver://MSI\\VJT:1433;databaseName=XML";
		  String user = "sa";
		  String pass ="sasasa"; 

		Connection con = DriverManager.getConnection(Url, user, pass);
		Statement stmt = con.createStatement();
		JFrame f;
		f = new JFrame();
		f.setSize(650,730);
		f.getContentPane().setLayout(null);
		
		JLabel lb1 = new JLabel("ID:");
		lb1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb1.setBounds(44, 34, 111, 47);
		f.getContentPane().add(lb1);
		
		JLabel lb2 = new JLabel("Name:");
		lb2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb2.setBounds(44, 141, 111, 47);
		f.getContentPane().add(lb2);
		
		JLabel lb3 = new JLabel("Phone Number");
		lb3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb3.setBounds(44, 244, 159, 47);
		f.getContentPane().add(lb3);
		
		JLabel lb4 = new JLabel("Location:");
		lb4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb4.setBounds(44, 361, 111, 47);
		f.getContentPane().add(lb4);
		
		JLabel lb5 = new JLabel("Salary:");
		lb5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb5.setBounds(44, 494, 111, 47);
		f.getContentPane().add(lb5);
		
		tf1 = new JTextField();
		tf1.setBounds(213, 43, 361, 36);
		f.getContentPane().add(tf1);
		tf1.setColumns(10);
		
		tf2 = new JTextField();
		tf2.setColumns(10);
		tf2.setBounds(213, 150, 361, 36);
		f.getContentPane().add(tf2);
		
		tf3 = new JTextField();
		tf3.setColumns(10);
		tf3.setBounds(213, 253, 361, 36);
		f.getContentPane().add(tf3);
		
		tf4 = new JTextField();
		tf4.setColumns(10);
		tf4.setBounds(213, 372, 361, 36);
		f.getContentPane().add(tf4);
		
		tf5 = new JTextField();
		tf5.setColumns(10);
		tf5.setBounds(213, 503, 361, 36);
		f.getContentPane().add(tf5);
		
		JButton b1 = new JButton("Add");
		b1.setBounds(63, 617, 204, 47);
		f.getContentPane().add(b1);
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ResultSet rs = stmt.executeQuery("select * from xml1 where id = "+tf1.getText());
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "ID already exist!!!!");
					}
					else {
						try {
							File xmlFile = new File("V:\\Java\\employee.xml");
							DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
							DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
							Document document = documentBuilder.parse(xmlFile);
							Element documentElement = document.getDocumentElement();
							Element cnode1 = document.createElement("id");
							cnode1.setTextContent(tf1.getText());
							Element cnode2 = document.createElement("name");
							cnode2.setTextContent(tf2.getText());
							Element cnode3 = document.createElement("phone");
							cnode3.setTextContent(tf3.getText());
							Element cnode4 = document.createElement("location");
							cnode4.setTextContent(tf4.getText());
							Element cnode5 = document.createElement("salary");
							cnode5.setTextContent(tf5.getText());
							Element nodeElement = document.createElement("employee");
							nodeElement.appendChild(cnode1);
							nodeElement.appendChild(cnode2);
							nodeElement.appendChild(cnode3);
							nodeElement.appendChild(cnode4);
							nodeElement.appendChild(cnode5);
							documentElement.appendChild(nodeElement);
							document.replaceChild(documentElement, documentElement);

					        TransformerFactory transformerFactory = TransformerFactory.newInstance();
					        Transformer transformer = transformerFactory.newTransformer(
					                new StreamSource(new File("V:\\Java\\xslt.xslt")));
					        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
					        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
		
							
							Source source = new DOMSource(document);
							Result result = new StreamResult(xmlFile);
							transformer.transform(source, result);
							JOptionPane.showMessageDialog(null, "added");
							f.dispose();
							new SQLput();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ParserConfigurationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SAXException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (TransformerConfigurationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (TransformerFactoryConfigurationError e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (TransformerException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton b2 = new JButton("Cancel");
		b2.setBounds(370, 617, 204, 47);
		f.getContentPane().add(b2);
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f.dispose();
			}
		});
		f.setVisible(true);
	}
}
