package utils;
import model.Request;
import java.util.*;
import java.io.*;
public class RequestParser
{
	public static Request parseRequest(InputStream socketInputStream, HashMap<String,String> pathMappings) throws Exception 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(socketInputStream));
		StringBuilder sb = new StringBuilder();
		String s = "";
		do{
			s = br.readLine();
			sb.append(s);
			sb.append("\n");
		}while(s != null && s.length()>0);
		
		String requestString = sb.toString();
		String[] splits = requestString.split("\n");
		String firstLine = splits[0];
		String[] words = firstLine.split(" ");
		Request request = new Request();
		request.setError(0);
		// request.method = words[0];
		request.setMethod(words[0]);

		if(words[1].equals("/"))
		{
			// request.resource = "index.html";
			request.setResource("index.html");
		}
		else
		{
			// request.resource = words[1].substring(1);
			request.setResource(words[1].substring(1));
		}

		if(words[1].startsWith("/private/"))
		{
			request.setError(404);
			return request;
		}

		if(pathMappings.get(words[1]) != null)
		{
			request.isClientSideTechnologyResource = false;
			request.setResource(pathMappings.get(words[1]));
		}
		else
		{
			request.isClientSideTechnologyResource = true;
		}
		//will be changed later on
		return request;
	}
}