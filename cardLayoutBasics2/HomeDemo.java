import javax.swing.*;
import java.awt.event.*;
class HomeDemo extends JPanel
{
	JButton b1;
	HomeDemo(JFDemo f)
	{
		b1 = new JButton("Login");
		add(b1);
		b1.addActionListener(f);
	}
}