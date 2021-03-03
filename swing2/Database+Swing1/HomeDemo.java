import javax.swing.*;
import java.awt.event.*;
class HomeDemo extends JPanel
{
	JButton b1;
	JButton b2;
	HomeDemo(JFDemo f)
	{
		b1 = new JButton("Login");
		add(b1);
		b1.addActionListener(f);

		b2 = new JButton("Register");
		add(b2);
		b2.addActionListener(f);

	}
}