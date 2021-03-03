import javax.swing.*;
import java.awt.*;
class LoginDemo extends JPanel
{
	JLabel u1, u2;
	JTextField tx1;
	JPasswordField tx2;
	JButton b1,b2;
	LoginDemo(JFDemo f)
	{
		setLayout(null);
		u1 = new JLabel("Enter Username");
		u1.setBounds(100, 50, 150, 50);
	
		
		add(u1);

		u2 = new JLabel("Enter Password");
		
		
		u2.setBounds(100, 150, 150, 50);
		add(u2);

		tx1 = new JTextField();
		tx1.setBounds(250, 60, 300, 30);
		add(tx1);

		tx2 = new JPasswordField();
		tx2.setBounds(250, 160, 300, 30);
		add(tx2);

		b1 = new JButton("LogIn");
		add(b1);
		b1.setBounds(200, 250, 100, 50);

		b2 = new JButton("Register");
		b2.setBounds(50, 500, 100, 50);
		add(b2);

		b2.addActionListener(f);
		b1.addActionListener(f);
	}

	public void paintComponent(Graphics g)
	{
		 g.drawImage(new ImageIcon("yellow.png").getImage(), 0, 0, null);
	}
}