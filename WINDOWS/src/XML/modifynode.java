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

public class modifynode {
	public modifynode(String inputid) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		String Url = "jdbc:sqlserver://MSI\\VJT:1433;databaseName=XML";
		 String user = "sa";
		 String pass ="sasasa"; 

		Connection con = DriverManager.getConnection(Url, user, pass);
		Statement stmt = con.createStatement();
		JFrame f1;
		f1 = new JFrame();
		f1.setSize(650,730);
		f1.getContentPane().setLayout(null);
		
		JLabel lb1 = new JLabel("ID:");
		lb1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb1.setBounds(44, 34, 111, 47);
		f1.getContentPane().add(lb1);
		
		JLabel lb2 = new JLabel("Name:");
		lb2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb2.setBounds(44, 141, 111, 47);
		f1.getContentPane().add(lb2);
		
		JLabel lb3 = new JLabel("Phone Number");
		lb3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb3.setBounds(44, 244, 159, 47);
		f1.getContentPane().add(lb3);
		
		JLabel lb4 = new JLabel("Location:");
		lb4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb4.setBounds(44, 361, 111, 47);
		f1.getContentPane().add(lb4);
		
		JLabel lb5 = new JLabel("Salary:");
		lb5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb5.setBounds(44, 494, 111, 47);
		f1.getContentPane().add(lb5);
		
		JTextField tf1 = new JTextField();
		tf1.setBounds(213, 43, 361, 36);
		f1.getContentPane().add(tf1);

		tf1.setColumns(10);
		
		JTextField tf2 = new JTextField();
		tf2.setColumns(10);
		tf2.setBounds(213, 150, 361, 36);
		f1.getContentPane().add(tf2);
		
		JTextField tf3 = new JTextField();
		tf3.setColumns(10);
		tf3.setBounds(213, 253, 361, 36);
		f1.getContentPane().add(tf3);
		
		JTextField tf4 = new JTextField();
		tf4.setColumns(10);
		tf4.setBounds(213, 372, 361, 36);
		f1.getContentPane().add(tf4);
		
		JTextField tf5 = new JTextField();
		tf5.setColumns(10);
		tf5.setBounds(213, 503, 361, 36);
		f1.getContentPane().add(tf5);
		
		
		
		JButton cb = new JButton("Cancel");
		cb.setBounds(400, 590, 100, 40);
		cb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f1.dispose();
			}
		});
		f1.getContentPane().add(cb);
		
		f1.setVisible(true);
		try {
			Statement modstmt = con.createStatement();
			ResultSet modrs = modstmt.executeQuery("SELECT * from xml1 where id ="+inputid);
			if(modrs.next()) {
				tf1.setText(modrs.getString(1));
				tf2.setText(modrs.getString(2));
				tf3.setText(modrs.getString(3));
				tf4.setText(modrs.getString(4));
				tf5.setText(modrs.getString(5));
			}
			else {
				JOptionPane.showMessageDialog(null, "unknown error!");
			}
		}
		catch(Exception d) {
			d.printStackTrace();
		}
		JButton saveb = new JButton("Save");
		saveb.setBounds(150, 590, 100, 40);
		saveb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		
		        try {
		        	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
					  // parse XML file
			        DocumentBuilder db = dbf.newDocumentBuilder();

			        Document doc = db.parse(new File("V:\\Java\\employee.xml"));

			       	
			        doc.getDocumentElement().normalize();

			        // get <employee>
			        NodeList employeelist = doc.getElementsByTagName("employee");
			        for(int i =0; i<employeelist.getLength();i++) {
			        	Node employee = employeelist.item(i);
			        	Element element = (Element) employee;
			    
		        		String id = element.getElementsByTagName("id").item(0).getTextContent();
			        	if(id.equalsIgnoreCase(inputid)) {
			        		if(employee.getNodeType() == Node.ELEMENT_NODE) {
			        			element.getElementsByTagName("id").item(0).setTextContent(tf1.getText());
			        			element.getElementsByTagName("name").item(0).setTextContent(tf2.getText());
			        			element.getElementsByTagName("phone").item(0).setTextContent(tf3.getText());
			        			element.getElementsByTagName("location").item(0).setTextContent(tf4.getText());
			        			element.getElementsByTagName("salary").item(0).setTextContent(tf5.getText());
			        			Transformer tFormer =
								TransformerFactory.newInstance().newTransformer();
								Source source = new DOMSource(doc);
								Result result = new StreamResult("V:\\Java\\employee.xml");
								tFormer.transform(source, result);
								PreparedStatement saveprmt = con.prepareStatement("update xml1 set id='"+tf1.getText()+"', name='"+tf2.getText()+"',phone='"+tf3.getText()+"',location='"+tf4.getText()+"',salary ="+tf5.getText() + "where id ="+inputid);
			        			saveprmt.execute();
			        			JOptionPane.showMessageDialog(null, "UPDATED");
			        			f1.dispose();
			        			break;
			        		}
			        	}
			        	else {
			        	}
			        }
			        
				} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
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
				}

		      
		        
			}
		});
		f1.getContentPane().add(saveb);
	}
}
