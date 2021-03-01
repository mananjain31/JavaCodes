import java.sql.*;
import java.util.*;
class FetchSpecificData
{
	public static void main(String[] args) 
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter Name : ");
			String s1 = sc.next();
			System.out.print("Enter Password :");
			String s2 = sc.next();
			
			Class.forName("com.mysql.cj.jdbc.Driver");

			String db_url = "jdbc:mysql://localhost:3306/manandb?useSSL=false";
			String db_uname = "manan";
			String db_upass = "Manan+31";
			Connection con = DriverManager.getConnection(db_url, db_uname, db_upass);

			Statement st = con.createStatement();

			String q = "select * from login where UNAME='"+s1+"' AND UPASS='"+s2+"'";
			//creating object of ResultSet to fetch the table "login"
			ResultSet rs = st.executeQuery(q);
			//by default rs points to the top so to make it point to the first row we do rs.next()
			while(rs.next())
			{
				System.out.println(rs.getString(1)+"\t"+rs.getString(2));
			}
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}