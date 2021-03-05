import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Component.*;
import java.sql.*;


class JFDemo extends JFrame implements ActionListener
{
	CardLayout card;
	Container cn = getContentPane();
	HomeDemo home;
	LoginDemo login;
	MenuDemo menu;
	RegisterDemo regis;
	JFDemo()
	{
 		
		card = new CardLayout();
		cn.setLayout(card);

		home = new HomeDemo(this);
		cn.add("home",home);
		
		login = new LoginDemo(this);
		cn.add("login",login);
		
		regis = new RegisterDemo(this);
		cn.add("regis",regis);
		

		menu = new MenuDemo(this);
		cn.add("menu",menu);

	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==home.b1)
			card.next(cn);
		else if(e.getSource()==home.b2 || e.getSource()==login.b2)
			card.show(cn,"regis");
		else if(e.getSource()==regis.b2)
			card.show(cn,"login");
		else if(e.getSource()==login.b1)
		{
			String s1 = login.tx1.getText();
			String s2 = login.tx2.getText();
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");

				String db_url = "jdbc:mysql://localhost:3306/manandb?useSSL=false";
				String db_uname = "manan";
				String db_upass = "Manan+31";
				Connection con = DriverManager.getConnection(db_url, db_uname, db_upass);
				Statement st = con.createStatement();

				String q = "select * from login where UNAME='"+s1+"' AND UPASS='"+s2+"'";
				ResultSet rs = st.executeQuery(q);

				if(rs.next())
					card.show(cn,"menu");
				else
				JOptionPane.showMessageDialog(login.b1,
					"invalid username and password", "Message from Manan Jain",
					 JOptionPane.ERROR_MESSAGE);
				con.close();
			}
			catch(Exception e1)
			{
				System.out.println(e1);
			}	
			// PLAIN_MESSAGE
			// WARNING_MESSAGE
			// QUESTION_MESSAGE
			// INFORMATION_MESSAGE
		}
	}
}

class swing2
{
	public static void main(String[] args) 
	{ 
		JFDemo f = new JFDemo();
		f.setVisible(true);
		f.setBounds(100,100,800,600);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
	}
}