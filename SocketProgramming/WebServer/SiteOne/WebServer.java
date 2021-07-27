import java.net.*;
import java.util.*;
import java.io.*;
class RequestHandler implements Runnable
{
	StringBuilder sb;
	String request, response, path, mimeType;
	long responseLength;
	OutputStream os;
	InputStream is;

	Socket socket;
	RequestHandler(Socket socket) throws Exception
	{
		this.socket = socket;
		is = socket.getInputStream();
		os = socket.getOutputStream();
		// this.start();
	}
	void writeResponse() throws Exception
	{
		byte arr[] = new byte[1024];
		FileInputStream fis = new FileInputStream(path);
		int recieved = 0;
		int size = 1024;
		while(true)
		{
			recieved = fis.read(arr, 0, size);
			if(recieved == -1) break;
			else if (recieved < size)
			{
				size = recieved;
			}
			os.write(arr, 0, size);
			// os.flush();
		}
	}
	String getMimeType(){
		String mime = URLConnection.getFileNameMap().getContentTypeFor(path);
		if(mime==null){
			if(path.endsWith(".css")) mime = "text/css";
			if(path.endsWith(".js")) mime = "application/javascript";
		}
		return mime;
	}
	long getFileSize(){
		return new File(path).length();
	}
	String readRequest() throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		return br.readLine();
	}
	public void run()
	{
		try{	
			is = socket.getInputStream();
			os = socket.getOutputStream();
			request = readRequest();
			System.out.println("Request Arrived : "+request);
			path = (request.split(" "))[1]; 
			if(path.equals("/")){
				path = "index.html";
			}
			else{
				path = path.substring(1);
			}
			mimeType = getMimeType();
			responseLength = getFileSize();
			sb = new StringBuilder();
			sb.append("HTTP/1.1 200 OK\n");
			sb.append("Content-Type: "+mimeType+"\n");
			sb.append("Content-Length: "+responseLength+"\n");
			sb.append("\n");
			os.write(sb.toString().getBytes());
			System.out.println("Header : "+sb);
			writeResponse();
			os.flush();
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
				Thread.sleep(1000);
				i++;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}