import javax.swing.*;
import java.awt.*;

class About extends JPanel
{
	JButton backB;
	JLabel u1;
	About(JFDemo f)
	{
		setLayout(null);
		//using html in it for multiline Label
		setBackground(Color.green);
		u1 = new JLabel("<html>HELLO....<br> Myself Manan Jain</html>");
		u1.setSize(200,100);
		u1.setLocation(100,50);
		add(u1);
		backB = new JButton("Back");
		backB.setSize(100,50);
		backB.setLocation(0,400);
		add(backB);

		backB.addActionListener(f);
	}

}