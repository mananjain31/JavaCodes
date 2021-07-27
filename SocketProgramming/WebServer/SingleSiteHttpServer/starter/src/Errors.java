package utils;
import model.Request;
import java.util.*;
import java.io.*;
public class Errors{
	public static void send404(OutputStream os, String resource) throws Exception 
	{
		String body = 	"<!DOCTYPE HTML>\n" +
						"<html lang = 'en'>\n" + 
						"<head>\n"+
						"<title>404 Not Found</title>\n" +
						"<meta charset = 'utf-8'>\n" +
						"</head>\n" +
						"<body>\n" +
						"<h1>Resource Not Found</h1>\n" + 
						"<p>The Requested Resource URL /"+resource+" was not found on this server</p>\n" +
						"</body>\n" + 
						"</html>\n";
		String header = "HTTP/1.1 404 Not Found\n";
		header+=new Date().toGMTString()+"\n";
		header+="Server: MJWebProjector\n";
		header+="Content-Type: text/html\n";
		header+="Content-Length: "+body.length()+"\n";
		header+="\n";

		os.write(header.getBytes());
		os.write(body.getBytes());
		os.flush();
	}
}