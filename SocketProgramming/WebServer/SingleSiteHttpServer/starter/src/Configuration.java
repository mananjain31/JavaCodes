package utils;

import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

public class Configuration
{
	public static HashMap<String, String> getPathMappings()
	{
		HashMap<String, String> pathMappings = new HashMap<String, String>();
		String configurationFilePath = "conf.xml";

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try
		{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(configurationFilePath));
			document.getDocumentElement().normalize();

			NodeList pathMappingNodeList = document.getElementsByTagName("path-mapping");
			for(int i=0;i<pathMappingNodeList.getLength();i++)
			{
				Node pathMappingNode = pathMappingNodeList.item(i);
				NodeList pathMappingNodeDetails = pathMappingNode.getChildNodes();
				String path = null;
				String resource = null;
				for(int j=0;j<pathMappingNodeDetails.getLength();j++)
				{
					Node pathMappingNodeDetail = pathMappingNodeDetails.item(j);
					if(pathMappingNodeDetail.getNodeType() == Node.ELEMENT_NODE)
					{
						Element detail = (Element) pathMappingNodeDetail;
						if(detail.getTagName().equals("path")) path = detail.getTextContent();
						if(detail.getTagName().equals("resource")) resource = detail.getTextContent();
					}
				}

				if(path!=null && resource!=null) pathMappings.put(path, resource);
				// Node pathNode = pathMappingNodeDetails.item(0); 
				// Node resourceNode = pathMappingNodeDetails.item(1); 
				// System.out.println(path+"____"+resource);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		
		return pathMappings;
	}
}