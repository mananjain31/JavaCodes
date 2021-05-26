import java.net.*;
import java.util.*;
import java.io.*;
class MyClient
{
public static void main(String...args)
{
try
{
Socket socket = new Socket("localhost",5050);
Scanner sc = new Scanner(socket.getInputStream());
Scanner cmd = new Scanner(System.in);
PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
String s = null;
while(true)
{
System.out.println(sc.nextLine());
s = cmd.nextLine();
out.println(s);
out.flush();
if(s.trim().equals("BYE")) 
{
System.out.println("Closing connection");
break;
}
}
}
catch(Exception e)
{
e.printStackTrace();
}
}
}


//BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));