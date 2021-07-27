import model.*;
import utils.*;
import java.util.*;
import java.net.*;
import java.io.*;
public class WebServer implements Runnable
{

	// String request, response, path, mimeType;
	// Request request;
	static HashMap<String, String> pathMappings;
	Socket socket;
	OutputStream os;
	InputStream is;

	WebServer(Socket socket) throws Exception
	{
		this.socket = socket;
		is = socket.getInputStream();
		os = socket.getOutputStream();
		// this.start();
	}

	// String getMimeType(String resource)
	// {
	// 	String[] parts = resource.split("/");
	// 	String resourceFileName = parts[parts.length-1];
	// 	p
	// 	return URLConnection.getFileNameMap().getContentTypeFor(resourceFileName);
	// }

	void serveResource(Request request) throws Exception
	{
		File resourceFile = new File(request.getResource());

		if(resourceFile.exists())
		{
			String mimeType = MimeTypes.getMimeType(request.getResource());
			// System.out.println(mimeType);
			StringBuilder sb = new StringBuilder();
			sb.append("HTTP/1.1 200 OK\n");
			sb.append("Content-Type: "+mimeType+"\n");
			sb.append("Content-Length: "+resourceFile.length()+"\n");
			sb.append("\n");
			String header = sb.toString();
			// System.out.print("Writing header : "+ header);
			os.write(header.getBytes());
			os.flush();

			//now writing content of the requested resource file
			int bufferSize = 1024;
			byte buffer[] = new byte[bufferSize];
			FileInputStream fis = new FileInputStream(resourceFile);
			int recieved = 0;
			while(true)
			{
				recieved = fis.read(buffer, 0, bufferSize);
				if(recieved <= 0) break;
				else if (recieved < bufferSize)
				{
					bufferSize = recieved;
				}
				os.write(buffer, 0, bufferSize);
				// System.out.println(new String(buffer));
				os.flush();
			}
			fis.close();			
			// System.out.println("done");
		}
		else{
			// System.out.println("does not exists");
			Errors.send404(os, request.getResource());
		}
	}
	public void run()
	{
		try{
			// System.out.println("in run");
			Request request = new RequestParser().parseRequest(is, pathMappings);
			// System.out.println(request);
			if(request.getError() != 0)
			{
				if(request.getError() == 404)
				{
					Errors.send404(os, request.getResource());
				}
			}
			else if(request.isClientSideTechnologyResource)
			{
				serveResource(request);
			}
			else
			{
				//will code this later on
				System.out.println(request);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			try{	
				os.close();
				is.close();
			}catch(Exception ee){ee.printStackTrace();}
		}
	}

	public static void main(String[] args) 
	{
		try{
			ServerSocket serverSocket = new ServerSocket(8088);
			pathMappings = Configuration.getPathMappings();
			System.out.println(pathMappings);
			System.out.println("Server is Listening at port 8088");
			Socket socket = null;
			int i=1;
			while(true)
			{
				new Thread(new WebServer(serverSocket.accept())).start();
				// System.out.println("new request : "+i);
				i++;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}