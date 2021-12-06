import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

public class RegisterPage implements MouseListener {

	// Validation mail
	public static boolean patternMatches(String emailAddress, String regexPattern) {
		return Pattern.compile(regexPattern).matcher(emailAddress).matches();
	}

	// Md5hash password
	public static String hashPassword(String password) {
		String hashPassword = "";
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");

			// Add password bytes to digest
			md.update(password.getBytes());

			// Get the hash's bytes123
			byte[] bytes = md.digest();

			// This bytes[] has bytes in decimal format. Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			// Get complete hashed password in hex format
			hashPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return hashPassword;
	}

	JFrame home = new JFrame();
	JLabel welcomeLabel = new JLabel("Super Market Billing System");
	JLabel lblFirstName = new JLabel("First Name:");
	JLabel lblLastName = new JLabel("Last Name:");
	JTextField firstNameTextField = new RoundJTextField(15);
	JTextField lastNameTextField = new RoundJTextField(15);
	JLabel lblUserName = new JLabel("Username:");
	JTextField userNameTextField = new RoundJTextField(15);
	JLabel lblEmail = new JLabel("E-mail:");
	JTextField emailTextField = new RoundJTextField(15);
	JLabel lblPassword = new JLabel("Password:");
	JPasswordField password = new RoundJPasswordField(15);
	JLabel lblConfirmPassword = new JLabel("Confirm Password:");
	private final JPasswordField confirmPasswordTextField = new RoundJPasswordField(15);
	JPanel gender = new JPanel();
	JRadioButton male = new JRadioButton("male");
	JLabel setGender = new JLabel("Set Gender:");
	JLabel Lblcity = new JLabel("City:");
	JTextField cityTextField = new RoundJTextField(15);
	JLabel lblAddress = new JLabel("Address");
	JTextField addressTextField = new RoundJTextField(15);
	ButtonGroup bG = new ButtonGroup();
	JLabel lblSignUp = new JLabel("");
	JLabel lblsignUpText = new JLabel("Sign Up");
	JLabel lblClose = new JLabel("");
	JLabel lblCloset = new JLabel("Close");
	JRadioButton famale = new JRadioButton("famale");

	RegisterPage() throws IOException {
		
		confirmPasswordTextField.setBounds(56, 313, 174, 20);
		confirmPasswordTextField.setColumns(10);
		emailTextField.setToolTipText("");
		emailTextField.setBounds(56, 217, 174, 20);
		emailTextField.setColumns(10);
		userNameTextField.setBounds(56, 167, 174, 20);
		userNameTextField.setColumns(10);

		welcomeLabel.setBounds(69, 11, 400, 50);
		welcomeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		welcomeLabel.setForeground(Colors.white);

		home.getContentPane().add(welcomeLabel);
		
		home.setResizable(false);
		home.getContentPane().setBackground(Colors.teal);
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		home.setSize(500, 500);
		home.getContentPane().setLayout(null);

		lblFirstName.setBounds(56, 94, 88, 14);
		lblFirstName.setForeground(Colors.grey);
		lblFirstName.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

		home.getContentPane().add(lblFirstName);
		lblLastName.setBounds(247, 94, 88, 14);
		lblLastName.setForeground(Colors.grey);
		lblLastName.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

		home.getContentPane().add(lblLastName);
		firstNameTextField.setBounds(56, 117, 174, 20);
		home.getContentPane().add(firstNameTextField);
		firstNameTextField.setColumns(10);

		lastNameTextField.setBounds(247, 117, 180, 20);
		home.getContentPane().add(lastNameTextField);
		lastNameTextField.setColumns(10);
		lblUserName.setBounds(56, 148, 76, 14);
		lblUserName.setForeground(Colors.grey);
		lblUserName.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

		home.getContentPane().add(lblUserName);

		home.getContentPane().add(userNameTextField);
		lblEmail.setBounds(56, 198, 55, 14);
		lblEmail.setForeground(Colors.grey);
		lblEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

		home.getContentPane().add(lblEmail);

		home.getContentPane().add(emailTextField);
		lblPassword.setBounds(56, 248, 76, 14);
		lblPassword.setForeground(Colors.grey);
		lblPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

		home.getContentPane().add(lblPassword);
		home.setVisible(true);

		password.setBounds(56, 265, 174, 20);
		home.getContentPane().add(password);
		lblConfirmPassword.setBounds(56, 296, 138, 14);
		lblConfirmPassword.setForeground(Colors.grey);
		lblConfirmPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		home.getContentPane().add(lblConfirmPassword);

		home.getContentPane().add(confirmPasswordTextField);

		gender.setBounds(247, 160, 90, 80);
		gender.setBackground(Colors.teal);
		home.getContentPane().add(gender);

		male.setBounds(0, 0, 200, 20);
		male.setForeground(Colors.grey);
		male.setBackground(Colors.teal);

		famale.setBounds(247, 190, 100, 20);
		famale.setForeground(Colors.grey);
		famale.setBackground(Colors.teal);

		bG.add(male);
		bG.add(famale);

		setGender.setForeground(Colors.grey);
		setGender.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		gender.setLayout(new BoxLayout(gender, BoxLayout.Y_AXIS));
		gender.add(setGender);
		gender.add(male);
		gender.add(famale);

		Lblcity.setBounds(247, 247, 138, 14);
		Lblcity.setForeground(Colors.grey);
		Lblcity.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		home.getContentPane().add(Lblcity);

		cityTextField.setBounds(247, 265, 180, 20);
		home.getContentPane().add(cityTextField);

		lblAddress.setBounds(247, 296, 138, 14);
		lblAddress.setForeground(Colors.grey);
		lblAddress.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		home.getContentPane().add(lblAddress);

		addressTextField.setBounds(247, 313, 180, 20);
		home.getContentPane().add(addressTextField);

		lblsignUpText.setBounds(310, 350, 90, 48);
		lblsignUpText.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblsignUpText.setForeground(Color.WHITE);
		home.getContentPane().add(lblsignUpText);
		lblSignUp.setBounds(315, 390, 48, 48);
		lblSignUp.setIcon(new ImageIcon("images/signUp44.png"));
		lblSignUp.addMouseListener((MouseListener) this);
		home.getContentPane().add(lblSignUp);

		lblCloset.setBounds(105, 350, 90, 48);
		lblCloset.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblCloset.setForeground(Color.WHITE);
		home.getContentPane().add(lblCloset);
		lblClose.setBounds(100, 390, 48, 48);
		lblClose.setIcon(new ImageIcon("images/close.png"));
		lblClose.addMouseListener(this);
		home.getContentPane().add(lblClose);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		if (e.getSource() == lblClose) {

			try {
				home.dispose();
				Login loginPage = new Login();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == lblSignUp) {
			// Getting data from user input
			String firstName = firstNameTextField.getText();
			String username = userNameTextField.getText();
			String lastName = lastNameTextField.getText();
			String _password = password.getText().toString();
			String confirmPassword = confirmPasswordTextField.getText().toString();
			String gender;
			String email = emailTextField.getText();
			String address = addressTextField.getText();
			String hashedPassword = hashPassword(_password);
			Boolean usernameExsist = false;
			// User input validation

			try {
				con = ConnectionManager.getConnection();
				stmt = con.createStatement();
				sql = "SELECT * FROM `user` WHERE username = '" + username + "'";
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					usernameExsist = true;

				}
				stmt.close();

			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			if (male.isSelected()) {
				gender = "m";
			} else if (famale.isSelected()) {
				gender = "f";
			} else {

				gender = "";
			}

			if (firstName.equals("") || lastName.equals("") || _password.equals("") || confirmPassword.equals("")
					|| gender.equals("") || address.equals("") || email.equals("")) {
				JOptionPane.showMessageDialog(home, "Please fill all fields!");

			} else if (!(patternMatches(email, "^(.+)@(\\S+)$"))) {

				JOptionPane.showMessageDialog(home, "Invalid email!");
			} else if (firstName.length() > 25) {

				JOptionPane.showMessageDialog(home, "Too long firstname!");
			} else if (lastName.length() > 25) {

				JOptionPane.showMessageDialog(home, "Too long lastname!");
			} else if (usernameExsist) {
				JOptionPane.showMessageDialog(home, "Username already exsist!");
				usernameExsist = false;
			} else if (_password.toString().contentEquals(confirmPassword.toString())) {

				try {
					con = ConnectionManager.getConnection();
					stmt = con.createStatement();
					sql = "INSERT INTO `user` (`user_id`, `first_name`, `last_name`, `username`, `password`, `gender`, `address`) VALUES (NULL, '"
							+ firstName + "', '" + lastName + "', '" + username + "', '" + hashedPassword + "','"
							+ gender + "', '" + address + "');";
					stmt.executeUpdate(sql);
					stmt.close();
					home.dispose();
					Login login = new Login();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {

				JOptionPane.showMessageDialog(home, "Password and confirm password does not match!");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

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
