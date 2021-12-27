import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class ManualSalePanel extends JPanel implements ActionListener {
	JTextField t;
	JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bdiv,bmul,bsub,badd,bdec,beq,bdel,bclr;
	 
	static double a=0,b=0,result=0;
	static int operator=0;
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs= null;
	String sql="";
	public static String title = "Manual Sale";
	/**
	 * Create the panel.
	 */
	public ManualSalePanel() {
		setLayout(null);
		setBounds(300, 0, 900, 700);
		setBackground(Colors.white);
		
		
		JLabel lblLoggedInAs = new JLabel("Logged in as");
		lblLoggedInAs.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblLoggedInAs.setBounds(52, 12, 102, 21);
		lblLoggedInAs.setForeground(Colors.darkPurple);
		add(lblLoggedInAs);
		JLabel lblLoggedAsName = new JLabel("");
		lblLoggedAsName.setForeground(new Color(59, 42, 94));
		lblLoggedAsName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblLoggedAsName.setBounds(142, 12, 79, 21);
		add(lblLoggedAsName);
		
		try {
			con = ConnectionManager.getConnection();
			stmt = con.createStatement();
			sql = "SELECT first_name FROM `user` WHERE username = '" + Login.username + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String nameUserInputed = rs.getString(1);
				String firstLatterToUpper = nameUserInputed.substring(0, 1).toUpperCase()
						+ nameUserInputed.substring(1).toLowerCase();
				lblLoggedAsName.setText(firstLatterToUpper);

			} else {

				System.out.println("Could not find user");
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel lblUserIcon = new JLabel("");
		lblUserIcon.setIcon(new ImageIcon("images/userIcon.png"));
		lblUserIcon.setBounds(24, 16, 16, 16);
		add(lblUserIcon);
		
		t=new JTextField();
		b1=new JButton("1");
		b2=new JButton("2");
		b3=new JButton("3");
		b4=new JButton("4");
		b5=new JButton("5");
		b6=new JButton("6");
		b7=new JButton("7");
		b8=new JButton("8");
		b9=new JButton("9");
		b0=new JButton("0");
		bdiv=new JButton("/");
		bmul=new JButton("*");
		bsub=new JButton("-");
		badd=new JButton("+");
		bdec=new JButton(".");
		beq=new JButton("=");
		bdel=new JButton("Delete");
		bclr=new JButton("Clear");
		//1stRow
		t.setBounds(300,200,280,30);
		b7.setBounds(310,250,50,40);
		b8.setBounds(380,250,50,40);
		b9.setBounds(450,250,50,40);
		bdiv.setBounds(520,250,50,40);
		//2ndRow
		b4.setBounds(310,320,50,40);
		b5.setBounds(380,320,50,40);
		b6.setBounds(450,320,50,40);
		bmul.setBounds(520,320,50,40);
		//3rd row
		b1.setBounds(310,390,50,40);
		b2.setBounds(380,390,50,40);
		b3.setBounds(450,390,50,40);
		bsub.setBounds(520,390,50,40);
		//4th row
		bdec.setBounds(310,460,50,40);
		b0.setBounds(380,460,50,40);
		beq.setBounds(450,460,50,40);
		badd.setBounds(520,460,50,40);
		//5th
		bdel.setBounds(310,530,100,40);
		bclr.setBounds(470,530,100,40);
		add(t);
		add(b7);
		add(b8);
		add(b9);
		add(bdiv);
		add(b4);
		add(b5);
		add(b6);
		add(bmul);
		add(b1);
		add(b2);
		add(b3);
		add(bsub);
	    add(bdec);
		add(b0);
		add(beq);
		add(badd);
		add(bdel);
		add(bclr);
		setLayout(null);
		setVisible(true);
		
	
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		b0.addActionListener(this);
		badd.addActionListener(this);
		bdiv.addActionListener(this);
		bmul.addActionListener(this);
		bsub.addActionListener(this);
		bdec.addActionListener(this);
		beq.addActionListener(this);
		bdel.addActionListener(this);
		bclr.addActionListener(this);

		
		
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
	if(e.getSource()==b1)
	t.setText(t.getText().concat("1"));
	if(e.getSource()==b2)
	t.setText(t.getText().concat("2"));
	if(e.getSource()==b3)
	t.setText(t.getText().concat("3"));
	if(e.getSource()==b4)
	t.setText(t.getText().concat("4"));
	if(e.getSource()==b5)
	t.setText(t.getText().concat("5"));
	if(e.getSource()==b6)
	t.setText(t.getText().concat("6"));
	if(e.getSource()==b7)
	t.setText(t.getText().concat("7"));
	if(e.getSource()==b8)
	t.setText(t.getText().concat("8"));
	if(e.getSource()==b9)
	t.setText(t.getText().concat("9"));
	if(e.getSource()==b0)
	t.setText(t.getText().concat("0"));
	if(e.getSource()==bdec)
	t.setText(t.getText().concat("."));
	if(e.getSource()==badd)
	{
	a=Double.parseDouble(t.getText());
	operator=1;
	t.setText("");
	}
	if(e.getSource()==bsub)
	{
	a=Double.parseDouble(t.getText());
	operator=2;
	t.setText("");
	}
	if(e.getSource()==bmul)
	{
	a=Double.parseDouble(t.getText());
	operator=3;
	t.setText("");
	}
	if(e.getSource()==bdiv)
	{
	a=Double.parseDouble(t.getText());
	operator=4;
	t.setText("");
	}
	if(e.getSource()==beq)
	{
	b=Double.parseDouble(t.getText());
	switch(operator)
	{
	case 1: result=a+b;
	break;
	case 2: result=a-b;
	break;
	case 3: result=a*b;
	break;
	case 4: result=a/b;
	break;
	default: result=0;
	}
	t.setText(""+result);
	}
	if(e.getSource()==bclr)
	t.setText("");
	if(e.getSource()==bdel)
	{
	String s=t.getText();
	t.setText("");
	for(int i=0;i<s.length()-1;i++)
	t.setText(t.getText()+s.charAt(i));
	}
	}
	

}
