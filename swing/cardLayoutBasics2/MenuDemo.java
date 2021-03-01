import javax.swing.*;
class MenuDemo extends JPanel
{
	JButton b1;
	MenuDemo(JFDemo f)
	{
		b1 = new JButton("My Menu Page");
		add(b1);
		b1.addActionListener(f);
	}
}