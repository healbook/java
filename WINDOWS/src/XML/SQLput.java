package XML;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SQLput {
	public SQLput() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		String Url = "jdbc:sqlserver://MSI\\VJT:1433;databaseName=XML";
		  String user = "sa";
		  String pass ="sasasa"; 

		Connection con = DriverManager.getConnection(Url, user, pass);
		Statement stmt = con.createStatement();
	
		try {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // process XML securely, avoid attacks like XML External Entities (XXE)
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        // parse XML file
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.parse(new File("V:\\Java\\employee.xml"));

        // optional, but recommended       
        doc.getDocumentElement().normalize();


        // get <employee>
        NodeList list = doc.getElementsByTagName("employee");

        for (int i = 0; i < list.getLength(); i++) {

            Node node = list.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;

                // get employee's element
                String id = element.getElementsByTagName("id").item(0).getTextContent();

                String name = element.getElementsByTagName("name").item(0).getTextContent();
                String phone = element.getElementsByTagName("phone").item(0).getTextContent();
                String loc = element.getElementsByTagName("location").item(0).getTextContent();
                String salary = element.getElementsByTagName("salary").item(0).getTextContent();
                float fsalary = Float.parseFloat(salary);

                //insertinto SQL
               
                ResultSet rs = stmt.executeQuery("select*from xml1 where id = "+id);
                if(rs.next()) {
                	
                }
                else {
                	 try {
                     	PreparedStatement prmt = con.prepareStatement("Insert into xml1 values('"+id+"','"+name+"','"+phone+"','"+loc+"',"+fsalary+")");
                     	prmt.execute();
                     	
                     }
                     catch(Exception d) {
                     	d.printStackTrace();
                     }
                }
                
                
            }
        }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
