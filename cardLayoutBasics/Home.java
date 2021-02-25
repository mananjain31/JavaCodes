import javax.swing.*;
import java.awt.*;

class Home extends JPanel
{
	JButton aboutB, loginB;
	JLabel u1;
	Home(JFDemo f)
	{
		setLayout(null);
		setBackground(Color.blue);
		u1 = new JLabel("HOME PAGE");
		u1.setSize(200,100);
		u1.setLocation(300,50);
		add(u1);

		aboutB = new JButton("About");
		aboutB.setSize(100,50);
		aboutB.setLocation(0,400);
		add(aboutB);

		loginB = new JButton("Login");
		loginB.setSize(100,50);
		loginB.setLocation(680,400);
		add(loginB);

		aboutB.addActionListener(f);
		loginB.addActionListener(f);
	}

}