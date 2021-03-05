import javax.swing.*;
import java.awt.*;
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
	public void paintComponent(Graphics g)
	{
		 g.drawImage(new ImageIcon("yellow.png").getImage(), 0, 0, null);
	}
}