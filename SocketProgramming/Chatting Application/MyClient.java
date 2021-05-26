import java.net.*;
// import java.util.*;
import java.io.*;
class MyClient
{
public static void main(String...args)
{
try
{
Socket socket = new Socket("localhost",5050);
//Scanner sc = new Scanner(socket.getInputStream());
BufferedReader sc = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//Scanner cmd = new Scanner(System.in);
BufferedReader cmd = new BufferedReader(new InputStreamReader(System.in));
PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
String s = null;
while(true)
{
//System.out.println(sc.nextLine());
System.out.println(sc.readLine());
//s = cmd.nextLine();
s = cmd.readLine();
out.println(s);
out.flush();
if(s.trim().equals("BYE")) 
{
System.out.println("Chat Ended");
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