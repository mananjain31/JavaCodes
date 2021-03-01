import java.sql.*;
import java.util.*;

class insertUsingScanner
{
	public static void main(String[] args) 
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Name : ");
			String s1 = sc.next();
			System.out.println("Enter Password :");
			String s2 = sc.next();
			//load and regioster the driver
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			String db_url = "jdbc:mysql://localhost:3306/manandb?allowPublicKeyRetrieval=true&useSSL=false";
			String db_uname = "manan";
			String db_upass = "Manan+31";
			Connection con = DriverManager.getConnection(db_url,db_uname,db_upass);

			Statement st = con.createStatement();

			String q = "insert into login values('"+s1+"', '"+s2+"')";
			st.execute(q);
			System.out.println("data inserted...");

			con.close();
		}	

		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}