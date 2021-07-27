package utils;
import java.net.*;
public class MimeTypes
{
	public static String getMimeType(String resource)
	{
		String[] parts = resource.split("/");
		System.out.println(resource);
		String resourceFileName = parts[parts.length-1];
		System.out.println("resourceFileName : "+resourceFileName);
		String mimeType = URLConnection.getFileNameMap().getContentTypeFor(resourceFileName);
		if (mimeType == null)
		{
			if(resourceFileName.endsWith(".js")) return "application/javascript";
			if(resourceFileName.endsWith(".css")) return "text/css";
		} 
		return mimeType;
	}
}
	