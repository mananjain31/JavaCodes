import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
class MenuDemo extends JPanel
{
	JButton b1;
	JTabbedPane tab1;
	InsertDemo ins;
	SearchDemo src;
	DeleteDemo del;
	UpdateDemo upd;
	ShowAll shw;
	MenuDemo(JFDemo f)
	{
		setLayout(new BorderLayout());
		tab1 = new JTabbedPane();

		ins = new InsertDemo(this);
		tab1.add("Insert",ins);

		src = new SearchDemo(this);
		tab1.add("Search",src);

		del = new DeleteDemo(this);
		tab1.add("Delete",del);

		upd = new UpdateDemo(this);
		tab1.add("Update",upd);

		shw = new ShowAll(this);
		tab1.add("Show All",shw);

		add(tab1);
	}

}

class InsertDemo extends JPanel implements ActionListener
{
	JLabel u1, u2, u3, u4, u5;
	JTextField t1, t2, t3, t4, t5;
	JButton b1;
	MenuDemo menu;
	InsertDemo(MenuDemo menu)
	{
		setLayout(null);
		this.menu = menu;
		u1 = new JLabel("Enter Roll No.");
		u1.setBounds(100, 50, 100, 50);
		add(u1);

		u2 = new JLabel("Enter Name");
		u2.setBounds(100, 50+50, 100, 50);
		add(u2);		

		u3 = new JLabel("Physics Marks");
		u3.setBounds(100, 50+100, 100, 50);
		add(u3);

		u4 = new JLabel("Chemistry Marks");
		u4.setBounds(100, 50+150, 100, 50);
		add(u4);

		u5 = new JLabel("Maths Marks");
		u5.setBounds(100, 50+200, 100, 50);
		add(u5);

		t1 = new JTextField();
		t1.setBounds(250, 60, 300, 30);
		add(t1);
		t2 = new JTextField();
		t2.setBounds(250, 60+50, 300, 30);
		add(t2);
		t3 = new JTextField();
		t3.setBounds(250, 60+100, 300, 30);
		add(t3);
		t4 = new JTextField();
		t4.setBounds(250, 60+150, 300, 30);
		add(t4);
		t5 = new JTextField();
		t5.setBounds(250, 60+200, 300, 30);
		add(t5);

		b1 = new JButton("Insert");
		b1.setBounds(200, 60+250, 100, 50);
		add(b1);
		b1.addActionListener(this);
	}
	public void paintComponent(Graphics g)
	{
		g.drawImage(new ImageIcon("yellow.png").getImage(), 0, 0, null);
	}
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			String s1 = t1.getText();
			String s2 = t2.getText();
			if(s1.trim().equals("") || s2.trim().equals(""))
				throw (new NumberFormatException());
			int s3 = Integer.parseInt(t3.getText());
			int s4 = Integer.parseInt(t4.getText());
			int s5 = Integer.parseInt(t5.getText());
			if(s3 > 100 || s4 > 100 || s5 > 100) 
				throw (new ArithmeticException());


			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String db_url = "jdbc:mysql://localhost:3306/manandb?useSSL=false";
			String db_uname = "manan";
			String db_upass = "Manan+31";
			Connection con = DriverManager.getConnection(db_url, db_uname, db_upass);

			Statement st = con.createStatement();
			String s = 
			"INSERT INTO STUDENT VALUES('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"')";
			st.executeUpdate(s);
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t4.setText("");
			t5.setText("");
			System.out.println("Data inserted..");
			con.close();
			menu.shw.showAll(true);	
		}
		catch(NumberFormatException e1)
		{
			JOptionPane.showMessageDialog(
					b1,
					"Fill all the Data Required Correctly", 
					"Message from Manan Jain",
					 JOptionPane.ERROR_MESSAGE);	
			System.out.println("handled " + e1);
		}
		catch (ArithmeticException e1)
		{
			JOptionPane.showMessageDialog(
					b1,
					"the Input Marks exceeds limit of 100", 
					"Message from Manan Jain",
					 JOptionPane.ERROR_MESSAGE);	
			System.out.println("handled "+e1);
		}
		catch(Exception e1)
		{
			System.out.println(e1);
		}
	}
}

class UpdateDemo extends JPanel implements ActionListener
{
	JLabel u1, u2, u3, u4, u5;
	JTextField t1, t2, t3, t4, t5;
	JButton b1, b2;
	String rno, s2;
	MenuDemo menu;
	UpdateDemo(MenuDemo menu)
	{
		this.menu = menu;
		setLayout(null);
		u1 = new JLabel("Enter Roll No.");
		u1.setBounds(100, 50, 100, 50);
		add(u1);

		u2 = new JLabel("Enter Name");
		u2.setBounds(100, 50+50, 100, 50);
		add(u2);		

		u3 = new JLabel("Physics Marks");
		u3.setBounds(100, 50+100, 100, 50);
		add(u3);

		u4 = new JLabel("Chemistry Marks");
		u4.setBounds(100, 50+150, 100, 50);
		add(u4);

		u5 = new JLabel("Maths Marks");
		u5.setBounds(100, 50+200, 100, 50);
		add(u5);

		t1 = new JTextField();
		t1.setBounds(250, 60, 300, 30);
		add(t1);
		t2 = new JTextField();
		t2.setBounds(250, 60+50, 300, 30);
		add(t2);
		t3 = new JTextField();
		t3.setBounds(250, 60+100, 300, 30);
		add(t3);
		t4 = new JTextField();
		t4.setBounds(250, 60+150, 300, 30);
		add(t4);
		t5 = new JTextField();
		t5.setBounds(250, 60+200, 300, 30);
		add(t5);

		b1 = new JButton("Search");
		b1.setBounds(570, 60, 100, 30);
		add(b1);

		b2 = new JButton("Update");
		b2.setBounds(200, 60+250, 100, 50);
		add(b2);

		u2.setVisible(false);
		u3.setVisible(false);
		u4.setVisible(false);
		u5.setVisible(false);
		t2.setVisible(false);
		t3.setVisible(false);
		t4.setVisible(false);
		t5.setVisible(false);
		b2.setVisible(false);

		b1.addActionListener(this);
		b2.addActionListener(this);

	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == b1)
		try
		{
			String s1 = t1.getText();
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String db_url = "jdbc:mysql://localhost:3306/manandb?useSSL=false";
			String db_uname = "manan";
			String db_upass = "Manan+31";
			Connection con = DriverManager.getConnection(db_url, db_uname, db_upass);

			Statement st = con.createStatement();
			String q = 
			"select * from student where RNO='"+s1+"'";
			ResultSet rs = st.executeQuery(q);
			if(rs.next())
			{
				rno = t1.getText();
				t2.setText(rs.getString(2));
				t3.setText(rs.getString(3));
				t4.setText(rs.getString(4));
				t5.setText(rs.getString(5));
				u2.setText("Name");
				u3.setText("Physics Marks");
				u4.setText("Chemistry Marks");
				u5.setText("Maths Marks");
				u2.setVisible(true);
				u3.setVisible(true);
				u4.setVisible(true);
				u5.setVisible(true);
				t2.setVisible(true);
				t3.setVisible(true);
				t4.setVisible(true);
				t5.setVisible(true);
				b2.setVisible(true);
			}	
			else
			{
				JOptionPane.showMessageDialog(b1,
					"Data not Found", "Message from Manan Jain",
					 JOptionPane.ERROR_MESSAGE);
						u2.setVisible(false);
				u3.setVisible(false);
				u4.setVisible(false);
				u5.setVisible(false);
				t2.setVisible(false);
				t3.setVisible(false);
				t4.setVisible(false);
				t5.setVisible(false);
				b2.setVisible(false);
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");
			}
			con.close();
		}catch(Exception e1){System.out.println(e1);}

		else if(e.getSource() == b2)
		try
		{
			s2 = t2.getText();
			if(s2.trim().equals(""))
				throw new NumberFormatException();
			int s3 = Integer.parseInt(t3.getText());
			int s4 = Integer.parseInt(t4.getText());
			int s5 = Integer.parseInt(t5.getText());
			if(s3 > 100 || s4 > 100 || s5 > 100)
				throw new ArithmeticException();

			Class.forName("com.mysql.cj.jdbc.Driver");
			String db_url = "jdbc:mysql://localhost:3306/manandb?useSSL=false";
			String db_uname = "manan";
			String db_upass = "Manan+31";
			Connection con = DriverManager.getConnection(db_url, db_uname, db_upass);
			Statement st = con.createStatement();
			String q = 
			"update student set NAME='"+s2+"', P='"+s3+"', C='"+s4+"', M='"+s5+"' where RNO='"+rno+"'";
			st.executeUpdate(q);

			System.out.println("data updated");
			JOptionPane.showMessageDialog(b1,
				"Data Updated", "Message from Manan Jain",
				 JOptionPane.INFORMATION_MESSAGE);
					u2.setVisible(false);
			menu.shw.showAll(true);
			u3.setVisible(false);
			u4.setVisible(false);
			u5.setVisible(false);
			t2.setVisible(false);
			t3.setVisible(false);
			t4.setVisible(false);
			t5.setVisible(false);
			b2.setVisible(false);
			t2.setText("");
			t3.setText("");
			t4.setText("");
			t5.setText("");
		}
		catch(NumberFormatException e1)
		{
			JOptionPane.showMessageDialog(
					b2,
					"Fill all the Data Required Correctly", 
					"Message from Manan Jain",
					 JOptionPane.ERROR_MESSAGE);	
			System.out.println("handled " + e1);
		}
		catch (ArithmeticException e1)
		{
			JOptionPane.showMessageDialog(
					b2,
					"the Input Marks exceeds limit of 100", 
					"Message from Manan Jain",
					 JOptionPane.ERROR_MESSAGE);	
			System.out.println("handled "+e1);
		}
		catch(Exception e1){System.out.println(e1);}
	}
	public void paintComponent(Graphics g)
	{
		 g.drawImage(new ImageIcon("yellow.png").getImage(), 0, 0, null);
	}
}

class SearchDemo extends JPanel implements ActionListener
{
	JLabel u1, u2, u3, u4, u5, t2, t3, t4, t5;
	JTextField t1;
	JButton b1;
	SearchDemo(MenuDemo menu)
	{
		setLayout(null);
		u1 = new JLabel("Enter Roll No.");
		u1.setBounds(100, 50, 100, 50);
		add(u1);

		u2 = new JLabel();
		u2.setBounds(100, 50+50, 100, 50);
		add(u2);		

		u3 = new JLabel();
		u3.setBounds(100, 50+100, 100, 50);
		add(u3);

		u4 = new JLabel();
		u4.setBounds(100, 50+150, 100, 50);
		add(u4);

		u5 = new JLabel();
		u5.setBounds(100, 50+200, 100, 50);
		add(u5);

		t1 = new JTextField();
		t1.setBounds(250, 60, 300, 30);
		add(t1);
		t2 = new JLabel();
		t2.setBounds(250, 60+50, 300, 30);
		add(t2);
		t3 = new JLabel();
		t3.setBounds(250, 60+100, 300, 30);
		add(t3);
		t4 = new JLabel();
		t4.setBounds(250, 60+150, 300, 30);
		add(t4);
		t5 = new JLabel();
		t5.setBounds(250, 60+200, 300, 30);
		add(t5);

		b1 = new JButton("Search");
		b1.setBounds(570, 60, 100, 30);
		add(b1);
		b1.addActionListener(this);

	}	
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			String s1 = t1.getText();
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String db_url = "jdbc:mysql://localhost:3306/manandb?useSSL=false";
			String db_uname = "manan";
			String db_upass = "Manan+31";
			Connection con = DriverManager.getConnection(db_url, db_uname, db_upass);

			Statement st = con.createStatement();
			String q = 
			"select * from student where RNO='"+s1+"'";
			ResultSet rs = st.executeQuery(q);
			if(rs.next())
			{
				t2.setText(rs.getString(2));
				t3.setText(rs.getString(3));
				t4.setText(rs.getString(4));
				t5.setText(rs.getString(5));
				u2.setText("Name");
				u3.setText("Physics Marks");
				u4.setText("Chemistry Marks");
				u5.setText("Maths Marks");
			}	
			else
			{
				JOptionPane.showMessageDialog(b1,
					"Data not Found", "Message from Manan Jain",
					 JOptionPane.ERROR_MESSAGE);
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");
				u2.setText("");
				u3.setText("");
				u4.setText("");
				u5.setText("");
			}
			con.close();
		}
		catch(Exception e1)
		{
			System.out.println(e1);
		}
	}
	public void paintComponent(Graphics g)
	{

		g.drawImage(new ImageIcon("yellow.png").getImage(), 0, 0, null);
	}
}

class DeleteDemo extends JPanel implements ActionListener
{
	JLabel u1;
	JTextField t1;
	JButton b1;
	MenuDemo menu;
	DeleteDemo(MenuDemo menu)
	{
		this.menu = menu;
		setLayout(null);
		u1 = new JLabel("Enter Roll No.");
		u1.setBounds(100, 50, 100, 50);
		add(u1);

		t1 = new JTextField();
		t1.setBounds(250, 60, 300, 30);
		add(t1);

		b1 = new JButton("Delete");
		b1.setBounds(570, 60, 100, 30);
		add(b1);
		b1.addActionListener(this);
	}	
	public void paintComponent(Graphics g)
	{
		 g.drawImage(new ImageIcon("yellow.png").getImage(), 0, 0, null);
	}
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			String s1 = t1.getText();
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String db_url = "jdbc:mysql://localhost:3306/manandb?useSSL=false";
			String db_uname = "manan";
			String db_upass = "Manan+31";
			Connection con = DriverManager.getConnection(db_url, db_uname, db_upass);

			Statement st = con.createStatement();
			String q = 
			"SELECT * FROM STUDENT WHERE RNO='"+s1+"'";
			ResultSet rs = st.executeQuery(q);
			if(rs.next())
			{
			st.executeUpdate("delete from student where RNO='"+s1+"'");
			JOptionPane.showMessageDialog(b1,
				"Data Deleted", "Message from Manan Jain",
				 JOptionPane.INFORMATION_MESSAGE);
			menu.shw.showAll(true);
			}	
			else
			{
				JOptionPane.showMessageDialog(b1,
					"Data not Found", "Message from Manan Jain",
					 JOptionPane.ERROR_MESSAGE);
			}
			con.close();
		}catch(Exception e1){System.out.println(e1);}
	}
}

class ShowAll extends JPanel
{
	JLabel u1, u2;
	Graphics g;
	JTable j;
	JScrollPane sp;
	DefaultTableModel model;
	ShowAll(MenuDemo menu)
	{
		setLayout(new BorderLayout());
		showAll(false);
		System.out.println("showall");
	}
	public void showAll(boolean i)
	{
		if(i == true)remove(sp); 
		j = new JTable();
		model = (DefaultTableModel) j.getModel();
		model.addColumn("R.No");
	    model.addColumn("Name");
	    model.addColumn("Physics");
	    model.addColumn("Chemistry");
	    model.addColumn("Maths");
	    j.getColumnModel().getColumn(0).setPreferredWidth(5);
	    j.getColumnModel().getColumn(1).setPreferredWidth(50);
	    j.getColumnModel().getColumn(2).setPreferredWidth(50);
	    j.getColumnModel().getColumn(3).setPreferredWidth(50);
      	j.getColumnModel().getColumn(4).setPreferredWidth(50);
      	j.setRowHeight(40);
      	j.getTableHeader().setFont(new Font("", Font.BOLD, 20));
      	j.getTableHeader().setForeground(Color.gray);
      	j.getTableHeader().setBackground(Color.yellow);
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String db_url = "jdbc:mysql://localhost:3306/manandb?useSSL=false";
			String db_uname = "manan";
			String db_upass = "Manan+31";
			Connection con = DriverManager.getConnection(db_url, db_uname, db_upass);
			Statement st = con.createStatement();
			String q = "select * from student";
			ResultSet rs = st.executeQuery(q);
			int y=20;
			while(rs.next())
			{				
				model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
			}
			sp = new JScrollPane(j); 
			j.setFont(new Font("", Font.BOLD, 15));
			j.setForeground(Color.black);
			j.setBackground(Color.yellow);
			add(sp);
			System.out.println("showAll");
			con.close();
		}catch(Exception e){System.out.println(e);}
	}
	public void paintComponent(Graphics g1)
	{
		g1.drawImage(new ImageIcon("yellow.png").getImage(), 0, 0, null);
	}	
}

// class ShowAll extends JPanel
// {
// 	JLabel u1, u2;
// 	Graphics g;
// 	ShowAll(MenuDemo menu)
// 	{
// 		setLayout(null);
// 		showAll();
// 	}
// 	public void showAll()
// 	{
// 		try
// 		{
// 			Class.forName("com.mysql.cj.jdbc.Driver");
// 			String db_url = "jdbc:mysql://localhost:3306/manandb?useSSL=false";
// 			String db_uname = "manan";
// 			String db_upass = "Manan+31";
// 			Connection con = DriverManager.getConnection(db_url, db_uname, db_upass);
// 			Statement st = con.createStatement();
// 			String q = "select * from student";
// 			ResultSet rs = st.executeQuery(q);
// 			int y=20;
// 			while(rs.next())
// 			{				
// 				u1 = new JLabel(rs.getString(1));
// 				u1.setBounds(100, y, 100, 30);
// 				add(u1);
// 				u1 = new JLabel(rs.getString(2));
// 				u1.setBounds(200, y, 100, 30);
// 				add(u1);
// 				u1 = new JLabel(rs.getString(3));
// 				u1.setBounds(300, y, 100, 30);
// 				add(u1);
// 				u1 = new JLabel(rs.getString(4));
// 				u1.setBounds(400, y, 100, 30);					
// 				add(u1);
// 				u1 = new JLabel(rs.getString(5));
// 				u1.setBounds(500, y, 100, 30);		
// 				add(u1);
// 				y+=30;

// 			}
// 			System.out.println("showAll");
// 			con.close();
// 		}catch(Exception e){System.out.println(e);}
// 		add(u1);
// 	}
// 	public void paintComponent(Graphics g1)
// 	{
// 		g1.drawImage(new ImageIcon("yellow.png").getImage(), 0, 0, null);
// 	}	
// }

