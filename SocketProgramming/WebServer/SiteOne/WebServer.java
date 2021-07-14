import java.net.*;
import java.util.*;
import java.io.*;
class RequestHandler implements Runnable
{
	StringBuilder sb = new StringBuilder();
	String request, response, method, path, protocol;
	int responseLength;
	OutputStreamWriter osw;
	Scanner sc;

	Socket socket;
	public RequestHandler(Socket socket) throws Exception
	{
		this.socket = socket;
		sc = new Scanner(socket.getInputStream());
		osw = new OutputStreamWriter(socket.getOutputStream());
	}
	public String getResponse(String path) throws Exception
	{
		// File f = new File("index.html");
		if(path.equals("/"))
		{
			return new Scanner(new File("index.html")).useDelimiter("\\A").next();
		}
		File folder = new File("./");
		for(File file : folder.listFiles())
		{
			if(file.getName().equals(path.substring(1)))
				return new Scanner(file).useDelimiter("\\A").next();
		}
		return new Scanner(new File("error.html")).useDelimiter("\\A").next();
	}
	public void run()
	{
		try{
			if(sc.hasNextLine())
			request = sc.nextLine();
			System.out.println("Request Arrived : "+request);
			path = (request.split(" "))[1]; 

			// System.out.println(s);
			response = getResponse(path);
			responseLength = response.length();
			sb = new StringBuilder();
			sb.append("HTTP/1.1 200 OK\n");
			sb.append("Content-Type: text/html\n");
			sb.append("Content-Length: "+responseLength+"\n");
			sb.append("\n");
			sb.append(response);
			// System.out.println(sb.toString());
			osw.write(sb.toString());
			osw.flush();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
class WebServer
{
	public static void main(String[] args) 
	{
		try{
			ServerSocket serverSocket = new ServerSocket(8080);
			System.out.println("Server is Listening at port 8080");
			Socket socket = null;
			int i=1;
			while(true)
			{
				socket = serverSocket.accept();
				System.out.println(i);
				new Thread(new RequestHandler(socket)).start();
				i++;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}