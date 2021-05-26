import java.net.*;
// import java.util.*;
import java.io.*;
class MyServer
{
public static void main(String...args)
{
try
{
ServerSocket ss = new ServerSocket(5050);
System.out.println("Server is ready to accept requests at port 5050");
Socket socket = ss.accept();
System.out.println("Client from : "+socket.getInetAddress()+" connected");
//Scanner sc = new Scanner(socket.getInputStream());
BufferedReader sc = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//Scanner cmd = new Scanner(System.in);
BufferedReader cmd = new BufferedReader(new InputStreamReader(System.in));
PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
String s = null;
out.println("You are connected to the Server");
while(true)
{
//s = sc.nextLine();
s = sc.readLine();
if(s.trim().equals("BYE")) break;
System.out.println(s);
//s = cmd.nextLine();
s = cmd.readLine();
out.println(s);
if(s.trim().equals("BYE")) break;
out.flush();
}
System.out.println("Chat Ended");
ss.close();
}
catch(Exception e)
{	
e.printStackTrace();
}
}
}


//BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));