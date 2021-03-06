import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Connection;
//*import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.prefs.Preferences;

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

public class Login  implements ActionListener, MouseListener {

	// Creating needed object for frame
	JFrame login = new JFrame();
	JButton btnLogin = new JButton("Login");
	JLabel label = new JLabel();
    JLabel lblusername = new JLabel("Username");
	public static JTextField textFieldUsername = new RoundJTextField(15);
	JLabel lblPassword = new JLabel("Password");
	JPasswordField passwordField = new RoundJPasswordField(15);
	JCheckBox chckbxNewCheckBox = new JCheckBox("Rememmber me");
	JLabel lblHaveNoAccount = new JLabel("Have no account? ");
	JLabel lblNewLabel = new JLabel("Super Market Billing System");
	JLabel lblRegister = new JLabel("Register");
	public static String username =""; 


	Login() throws SQLException {
		//////////////////////////
		// Designing frame
		
		// Setting login frame
		login.setResizable(false);
		login.setVisible(true);
		login.setForeground(Colors.white);
		login.getContentPane().setBackground(Colors.teal);
		login.setLayout(null);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setBounds(100, 100, 500, 500);
		
		// Username label
		lblusername.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblusername.setForeground(Colors.grey);
		lblusername.setBounds(140, 169, 138, 15);
		login.add(lblusername);
		// Username field
		textFieldUsername.setBounds(140, 196, 220, 23);
		login.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		// Password label
		lblPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblPassword.setForeground(Colors.grey);
		lblPassword.setBounds(140, 231, 97, 15);
		login.add(lblPassword);
		// Password field
		passwordField.setBounds(140, 256, 220, 23);
		login.add(passwordField);
		// Rememmber me checkbox
		chckbxNewCheckBox.setForeground(Colors.grey);
		chckbxNewCheckBox.setBackground(Colors.teal);
		chckbxNewCheckBox.setBounds(140, 287, 167, 23);
		login.add(chckbxNewCheckBox);
		// Have no account label
		lblHaveNoAccount.setForeground(Colors.grey);
		lblHaveNoAccount.setBounds(140, 376, 128, 15);
		login.add(lblHaveNoAccount);
		// Register label and event on that
		lblRegister.addMouseListener((MouseListener) this);
		lblRegister.setForeground(Colors.grey);
		lblRegister.setBounds(273, 376, 70, 15);
		login.add(lblRegister);
		// Title label
		lblNewLabel.setForeground(Colors.grey);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		lblNewLabel.setBounds(75, 101, 349, 35);
		login.add(lblNewLabel);
		// Logo label
		label.setIcon(new ImageIcon("images/shop-64.png"));
		label.setBounds(216, 12, 70, 64);
		login.add(label);
		// Login button
		btnLogin.addActionListener((ActionListener) this);
		btnLogin.setForeground(Colors.grey);
		btnLogin.setBackground(Colors.aquaMarine);
		btnLogin.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogin.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		btnLogin.setBounds(140, 329, 65, 25);
		login.add(btnLogin);

	}

	//////////////////////////
	// Event Listeners
	@Override
	// Manage login click
	public void actionPerformed(ActionEvent e) {
		// Initialing DB connection to null
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		if (e.getSource() == btnLogin) {
			try {
				con = ConnectionManager.getConnection();
				stmt = con.createStatement();
				String username = textFieldUsername.getText();
				Login.username=username;
				String password = passwordField.getText().toString();
				String hashedCheck = RegisterPage.hashPassword(password);
				String sql = "SELECT * FROM `user` WHERE username = " + "'" + textFieldUsername.getText() + "'"
						+ " AND password=" + "'" + hashedCheck + "'";
				;
				rs = stmt.executeQuery(sql);

				if (rs.next()) {
					login.dispose();
					HomePage homePage = new HomePage();
				} else if (password.equals(" ") || username.equals("")) {

					JOptionPane.showMessageDialog(login, "Please fill all fields!");
				} else {
					JOptionPane.showMessageDialog(login, "Wrong Password And Username!");
				}

				con.close();

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	// Manage register clcik
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		login.dispose();
		try {
			RegisterPage registerPage = new RegisterPage();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		lblRegister.setForeground(new Color(255, 255, 255));

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		lblRegister.setForeground(new Color(255, 255, 255));
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
