import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
//*import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
public class Login implements ActionListener,MouseListener{
	//Initialing DB connection to null
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	//Creating needed object for frame
	JFrame login = new JFrame();
    JButton btnLogin = new JButton("Login");	
    JLabel label = new JLabel();
    JLabel lblusername = new JLabel("Username");
	JTextField textFieldUsername = new JTextField();
	JLabel lblPassword = new JLabel("Password");
	JPasswordField passwordField = new JPasswordField();
	JCheckBox chckbxNewCheckBox = new JCheckBox("Rememmber me");
	JLabel lblHaveNoAccount = new JLabel("Have no account? ");
	JLabel lblNewLabel = new JLabel("Super Market Billing System");
	JLabel lblRegister = new JLabel("Register");

	Login() throws SQLException{
		//////////////////////////
		//Designing frame
		
		//Setting login frame
		login.setResizable(false);
		login.setVisible(true);
		login.setForeground(Color.WHITE);
		login.getContentPane().setBackground(Color.DARK_GRAY);
		login.setLayout(null);		
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setBounds(100, 100, 500, 500);
		//Username label		
		lblusername.setFont(new Font("Ubuntu Condensed", Font.BOLD, 16));
		lblusername.setForeground(new Color(50, 205, 50));
		lblusername.setBounds(158, 169, 138, 15);
		login.add(lblusername);
		//Username field
		textFieldUsername.setBounds(158, 196, 220, 23);
		login.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		//Password label
		lblPassword.setFont(new Font("Ubuntu Condensed", Font.BOLD, 16));
		lblPassword.setForeground(new Color(50, 205, 50));
		lblPassword.setBounds(158, 231, 97, 15);
		login.add(lblPassword);
		//Password field
		passwordField.setBounds(158, 256, 220, 23);
		login.add(passwordField);
		//Rememmber me checkbox
		chckbxNewCheckBox.setForeground(Color.WHITE);
		chckbxNewCheckBox.setBackground(Color.DARK_GRAY);
		chckbxNewCheckBox.setBounds(158, 287, 167, 23);
		login.add(chckbxNewCheckBox);
		//Have no account label
		lblHaveNoAccount.setForeground(Color.WHITE);
		lblHaveNoAccount.setBounds(158, 376, 128, 15);
		login.add(lblHaveNoAccount);
		//Register label and event on that
		lblRegister.addMouseListener((MouseListener) this);
		lblRegister.setForeground(new Color(50, 205, 50));
		lblRegister.setBounds(290, 376, 70, 15);
		login.add(lblRegister);
		//Title label
		lblNewLabel.setForeground(new Color(50, 205, 50));
		lblNewLabel.setFont(new Font("Ubuntu Condensed", Font.BOLD, 25));
		lblNewLabel.setBounds(120, 101, 349, 25);
		login.add(lblNewLabel);
		//Logo label
		label.setIcon(new ImageIcon("/home/nmarkovic/Downloads/green-power.png"));
		label.setBounds(216, 12, 70, 64);
		login.add(label);
		//Login button
		btnLogin.addActionListener((ActionListener) this);
		btnLogin.setForeground(new Color(50, 205, 50));
		btnLogin.setBackground(new Color(255, 255, 255));
		btnLogin.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogin.setFont(new Font("Ubuntu Condensed", Font.BOLD, 15));
		btnLogin.setBounds(158, 329, 65, 25);
		login.add(btnLogin);
		
	}
	//////////////////////////
	//Event Listeners
	@Override
	//Manage login click
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnLogin) {
			try {
				con = ConnectionManager.getConnection();
				stmt = con.createStatement();
				String sql = "SELECT * FROM `user` WHERE username = "+ "'"+textFieldUsername.getText()+"'"+" AND password="+"'"+passwordField.getText().toString()+"'"; ;
				rs = stmt.executeQuery(sql);
				
				if(rs.next()) {
					login.dispose();
					HomePage homePage = new HomePage();
				} else { 
				
					 JOptionPane.showMessageDialog(login,"Wrong Password And Username!");  
				}
				con.close();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			
		}
	}
	//Manage register clcik
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		login.dispose();
		RegisterPage registerPage = new RegisterPage();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		lblRegister.setForeground(new Color(255, 255, 255));
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		lblRegister.setForeground(new Color(50, 205, 50));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	




}
