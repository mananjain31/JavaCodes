import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class JFDemo extends JFrame implements ActionListener
{
	CardLayout card;
	Container cn = getContentPane();
	HomeDemo home;
	LoginDemo login;
	MenuDemo menu;
	JFDemo()
	{
		card = new CardLayout();
		cn.setLayout(card);

		home = new HomeDemo(this);
		cn.add("home",home);
		
		login = new LoginDemo(this);
		cn.add("login",login);

		menu = new MenuDemo(this);
		cn.add("menu",menu);

	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==home.b1)
			card.next(cn);
		else if(e.getSource()==login.b1)
		{
			String s1 = login.tx1.getText();
			String s2 = login.tx2.getText();
			if(s1.equals("abc") && s2.equals("123"))		
			{
				card.next(cn);
			}
			else 
				JOptionPane.showMessageDialog(null,
					"invalid username and password");
		}
	}
}

class cardLayout2
{
	public static void main(String[] args) 
	{
		JFDemo f = new JFDemo();
		f.setVisible(true);
		f.setBounds(100,100,800,600);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
	}
}