import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class JFDemo extends JFrame implements ActionListener
{
	Container cn =getContentPane();
	CardLayout card;
	Home home;
	About about;
	Login login;
	JFDemo()
	{
		card = new CardLayout ();
		cn.setLayout(card);
		home = new Home(this);
		cn.add("HomePage",home);
		about = new About(this);
		cn.add("AboutPage",about);
		login = new Login(this);
		cn.add("LoginPage",login);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==home.aboutB)
		{
			card.show(cn,"AboutPage");
		}		
		if(e.getSource()==home.loginB)
		{
			card.show(cn,"LoginPage");
		}

		if(e.getSource()==about.backB)
		{
			card.show(cn,"HomePage");	
	
		}
		if(e.getSource()==login.backB)
		{
			card.show(cn,"HomePage");	
		}
	}
}

class cardLayout
{
	public static void main(String...args)
	{
		JFDemo f = new JFDemo();
		f.setVisible(true);
		f.setBounds(100,100,800,600);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
	}
}