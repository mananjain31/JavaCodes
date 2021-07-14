import java.sql.*;
class FetchAllData
{
	public static void main(String[] args) 
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");

			String db_url = "jdbc:mysql://localhost:3306/manandb?useSSL=false&allowPublicKeyRetrieval=true";
			String db_uname = "manan";
			String db_upass = "Manan+31";
			Connection con = DriverManager.getConnection(db_url, db_uname, db_upass);

			Statement st = con.createStatement();

			String q = "select * from login";
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