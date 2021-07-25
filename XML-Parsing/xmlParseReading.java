import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
public class xmlParseReading
{
    public static void main(String[] args) 
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("./students.xml")); // Getting document
            document.getDocumentElement().normalize(); // normalization of document

            NodeList studentList = document.getElementsByTagName("student");
            for(int i=0;i<studentList.getLength();i++)
            {
                Node studentNode = studentList.item(i);
                if(studentNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element studentElement = (Element) studentNode;
                    System.out.println(studentElement.getTagName() + ": class -> " + studentElement.getAttribute("class"));
                    NodeList studentDetails = studentNode.getChildNodes();
                    for(int j=0;j<studentDetails.getLength();j++)
                    {
                        Node studentDetailNode = studentDetails.item(j);
                        if(studentDetailNode.getNodeType() == Node.ELEMENT_NODE)
                        {
                            Element studentDetailElement = (Element) studentDetailNode;
                            System.out.println("    "+studentDetailElement.getTagName()+" = "+studentDetailElement.getTextContent());
                        }
                    }
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}