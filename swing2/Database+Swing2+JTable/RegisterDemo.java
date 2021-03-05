import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class RegisterDemo extends JPanel implements ActionListener,KeyListener
{
	JLabel u1, u2;
	JTextField tx1, tx2;
	JButton b1,b2;
	RegisterDemo(JFDemo f)
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
		tx1.setText("Your Name");
		tx1.setForeground(Color.gray);
		add(tx1);

		tx2 = new JTextField();
		tx2.setBounds(250, 160, 300, 30);
		tx2.setText("Your Password");
		tx2.setForeground(Color.gray);
		add(tx2);

		b1 = new JButton("Register");
		add(b1);
		b1.setBounds(200, 250, 100, 50);
		
		b2 = new JButton("Login");
		b2.setBounds(50, 500, 100, 50);
		add(b2);
		

		tx1.addKeyListener(this);
		tx2.addKeyListener(this);
		b2.addActionListener(f);
		b1.addActionListener(this);

	}
	public void keyReleased(KeyEvent e)
	{
		if(e.getSource() == tx1 && tx1.getText().trim().equals(""))
		{
			tx1.setText("Your Name");
			tx1.setForeground(Color.gray);
		}
		else if(e.getSource() == tx2 && tx2.getText().trim().equals(""))
		{
			tx2.setText("Your Password");
			tx2.setForeground(Color.gray);
		}

	}
	public void keyPressed(KeyEvent e)
	{
		if(e.getSource()==tx1 && tx1.getText().equals("Your Name"))
		{
			tx1.setText("");
			tx1.setForeground(Color.black);
		}
		else if(e.getSource()==tx2 && tx2.getText().equals("Your Password"))
		{
			tx2.setText("");
			tx2.setForeground(Color.black);
		}
	}
	public void keyTyped(KeyEvent e)
	{}

	public void paintComponent(Graphics g)
	{
		 g.drawImage(new ImageIcon("yellow.png").getImage(), 0, 0, null);
	}

	public void actionPerformed(ActionEvent e)
	{
		String s1 = tx1.getText();
		String s2 = tx2.getText();

		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String db_url = "jdbc:mysql://localhost:3306/manandb?useSSL=false";
			String db_uname = "manan";
			String db_upass = "Manan+31";
			Connection con = DriverManager.getConnection(db_url, db_uname, db_upass);
			Statement st = con.createStatement();
			String s = "INSERT INTO LOGIN(UNAME, UPASS) VALUES('"+s1+"', '"+s2+"')";
			st.executeUpdate(s);
			JOptionPane.showMessageDialog(b1,"Registered Sucessfully", 
				"Message from Manan Jain",
				JOptionPane.INFORMATION_MESSAGE);
			tx1.setText("");
			tx2.setText("");
			System.out.println("Data inserted..");	
		}
		catch(Exception e1)
		{
			System.out.println(e1);	
		}
	}
}