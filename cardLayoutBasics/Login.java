import javax.swing.*;
import java.awt.*;

class Login extends JPanel
{
	JLabel u1, u2;
	JTextField uname;
	JPasswordField upass;
	JButton backB, loginB;
	Login(JFDemo f)
	{
		setLayout(null);
		setBackground(Color.yellow);
		u1 = new JLabel("Enter Username");
		u1.setSize(100,50);
		u1.setLocation(100,50);
		add(u1);

		uname = new JTextField();
		uname.setSize(400,50);
		uname.setLocation(250,50);
		add(uname);

		u2 = new JLabel("Enter Password");
		u2.setSize(100,50);
		u2.setLocation(100,150);
		add(u2);

		upass = new JPasswordField();
		upass.setSize(400,50);
		upass.setLocation(250,150);
		add(upass);

		loginB = new JButton("Login");
		loginB.setSize(100,50);
		loginB.setLocation(250,250);
		add(loginB);

		backB = new JButton("Back");
		backB.setSize(100,50);
		backB.setLocation(0,400);
		add(backB);

		backB.addActionListener(f);
	}

}