package XML;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateXMLFile {
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Employee e1 = new Employee("1","Le Viet","0123","Da Nang",100000);
            Employee e2 = new Employee("2","Quoc Viet","01224","Da Nang",100000);
            Employee e3 = new Employee("3","Van vo","012342224","Quang Nam",20402);
            
            Element company = doc.createElement("Company");
            addE(doc,company,e1);
            addE(doc,company,e2);
            addE(doc,company,e3);
            doc.appendChild(company);
            
          
            try {
            	TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(doc);
                StreamResult streamResult = new StreamResult(new File("V:\\Java\\employee.xml"));
               
     
                // If you use
                // StreamResult result = new StreamResult(System.out);
                // the output will be pushed to the standard output ...
                // You can use that for debugging 
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformer.transform(domSource, streamResult);
     
                System.out.println("Done creating XML File");
                
            } catch (TransformerConfigurationException ex) {
                Logger.getLogger(CreateXMLFile.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(CreateXMLFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CreateXMLFile.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	static private void addE(Document doc, Element company, Employee employee) {
		Element nodeemployee = doc.createElement("employee");
		 Element id = doc.createElement("id");
		 id.setTextContent(employee.getId());
        Element name = doc.createElement("name");
        name.setTextContent(employee.getName());
        Element phone = doc.createElement("phone");
        phone.setTextContent(employee.getPhone());
        Element loc = doc.createElement("location");
        loc.setTextContent(employee.getLocation());
        Element sal = doc.createElement("salary");
        sal.setTextContent(String.valueOf(employee.getSalary()));
        
        nodeemployee.appendChild(id);
        nodeemployee.appendChild(name);
        nodeemployee.appendChild(phone);
        nodeemployee.appendChild(loc);
        nodeemployee.appendChild(sal);
        
        company.appendChild(nodeemployee);
	}
}
