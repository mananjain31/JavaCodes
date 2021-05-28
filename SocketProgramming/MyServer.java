import java.net.*;
import java.util.*;
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
Scanner sc = new Scanner(socket.getInputStream());
Scanner cmd = new Scanner(System.in);
PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
String s = null;
out.println("Welcome to MyServer");
out.flush();
while(true)
{
s = sc.nextLine();
if(s.trim().equals("BYE")) break;
System.out.println(s);
s = cmd.nextLine();
out.println(s);
out.flush();
}
System.out.println("Client Left The Chat");
ss.close();
}
catch(Exception e)
{	
e.printStackTrace();
}
}
}


//BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));