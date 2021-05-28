import java.net.*;
import java.util.*;
import java.io.*;
class myserver 
{
	private class myserverthread implements Runnable
	{
		Socket skt;
		String name;
		String s;
		myserverthread(Socket skt) throws Exception
		{
			this.skt = skt;
		}
		public void run() 
		{
			try
			{
				BufferedReader br = new BufferedReader(new InputStreamReader(skt.getInputStream()));
				OutputStreamWriter osw = new OutputStreamWriter(skt.getOutputStream());
				s = "Enter Your Name : ";
				osw.write(s);
				osw.write("#");
				osw.flush();
				int x=0;
				StringBuffer sb = new StringBuffer();
				while(true)
				{
					x = br.read();
					if(x == '#') break;
					sb.append((char)x);
				}
				name = sb.toString();
				System.out.println(name+" connected");
				
				///messanging
				osw.write("nice to meet you " + name);
				osw.write("#");
				osw.flush();

				sb = new StringBuffer();
				while(true)
				{
					x = br.read();
					if(x == '#') break;
					sb.append((char)x);
				}
				s = sb.toString();
				System.out.println("Message from "+name+ " : "+s);

			}catch(Exception e){e.printStackTrace();}
		}
	}
	private ServerSocket ss;
	public myserver()
	{
		try 
		{
			ss = new ServerSocket(5050);
			boolean tru = true;
			while(tru == true)
			{
				System.out.println("Server is ready to listen at port number 5050");
				Socket skt = ss.accept();
				System.out.println("new user");
				new Thread(new myserverthread(skt)).start();
			}
			ss.close();
		}catch(Exception e){e.printStackTrace();}
	}
	public static void main(String[] args) {
		new myserver();
	}
}
class myclient
{
	public myclient()
	{
		try
		{
			Socket skt = new Socket("localhost",5050);
			BufferedReader br = new BufferedReader(new InputStreamReader(skt.getInputStream()));
			OutputStreamWriter osw = new OutputStreamWriter(skt.getOutputStream());
			StringBuilder sb = new StringBuilder();
			int x=0;
			while(true)
			{
				x = (int)br.read();
				// System.out.println(x);
				if(x == '#') break;
				sb.append((char)x);
			}
			String s = sb.toString();
			System.out.print(s);
			Scanner sc = new Scanner(System.in);
			s = sc.nextLine();
			osw.write(s);
			osw.write("#");
			osw.flush();

			///messanging
			sb = new StringBuilder();
			x=0;
			while(true)
			{
				x = (int)br.read();
				// System.out.println(x);
				if(x == '#') break;
				sb.append((char)x);
			}
			s = sb.toString();
			System.out.println(s);
			s = sc.nextLine();
			osw.write(s);
			osw.write("#");
			osw.flush();
		}catch(Exception e){e.printStackTrace();}
	}
	public static void main(String[] args) {
		new myclient();
	}
}