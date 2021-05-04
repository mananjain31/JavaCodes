import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class JFDemo extends JFrame implements ActionListener
{
	JTextField tx1, tx2;
	JButton b1;
	JFDemo()
	{
		setLayout(new FlowLayout());
		tx1 = new JTextField(20);
		tx2 = new JTextField(20);
		b1 = new JButton("Insert");

		add(tx1);
		add(tx2);
		add(b1);
		b1.addActionListener(this);
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
			tx1.setText("");
			tx2.setText("");
			System.out.println("Data inserted..");	
			con.close();
		}
		catch(Exception e1)
		{
			System.out.println(e1);	
		}
	}
}


class insertUsingSwingForm
{
	public static void main(String[] args) {
		JFDemo f = new JFDemo();
		f.setVisible(true);
		f.setBounds(400,100,250,300);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
	}
}