// 1. import package
import java.sql.*;
class DbDemo1
{
	public static void main(String[] args) 
	{
		try
		{
			// 2. load and register the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 3. opening database connection to mysql server
			String db_url = "jdbc:mysql://localhost:3306/manandb?useSSL=false";
			String db_uname = "manan";
			String db_upass = "Manan+31";
			
			Connection con =  DriverManager.getConnection(db_url,db_uname,db_upass);

			// 4. getting statement object to exectue query
			Statement st = con.createStatement();

			// 5. Executing query
			String q = "insert into login(UNAME, UPASS) values('github1', '001')";
			st.executeUpdate(q);
			System.out.println("data inserted...");

			// 6. close connection
			con.close();

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}