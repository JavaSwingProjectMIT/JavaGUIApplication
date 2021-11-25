/*import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Color;

public class RegisterPage {

	JFrame home = new JFrame();
	JLabel welcomeLabel = new JLabel("WELCOME");
	
	JLabel lblFirstName = new JLabel("First Name:");
	
	JLabel lblLastName = new JLabel("Last Name:");
	JTextField firstNameTextField = new JTextField();
	JTextField lastNameTextField = new JTextField();
	
	JLabel lblUserName = new JLabel("Username:");
	JTextField userNameTextField = new JTextField();
	
	JLabel lblEmail = new JLabel("E-mail:");
	JTextField emailTextField = new JTextField();
	JLabel lblPassword = new JLabel("Password:");
	JPasswordField password = new JPasswordField();
	JLabel lblConfirmPassword = new JLabel("Confirm Password:");
	private final JTextField confirmPasswordTextField = new JTextField();
	
	
	
	
	RegisterPage(){
		confirmPasswordTextField.setBounds(56, 313, 174, 20);
		confirmPasswordTextField.setColumns(10);
		emailTextField.setToolTipText("");
		emailTextField.setBounds(56, 217, 174, 20);
		emailTextField.setColumns(10);
		userNameTextField.setBounds(56, 167, 174, 20);
		userNameTextField.setColumns(10);
		
		welcomeLabel.setBounds(168,11,138,50);
		welcomeLabel.setFont(new Font(null,Font.BOLD,25));
		welcomeLabel.setForeground(new Color(50,205,50));
		
		home.getContentPane().add(welcomeLabel);
		home.getContentPane().setBackground(Color.DARK_GRAY);
		home.setTitle("Register me");
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		home.setSize(500,500);
		home.getContentPane().setLayout(null);
		
		
		lblFirstName.setBounds(56, 94, 88, 14);
		lblFirstName.setForeground(new Color(50,205,50));
		lblFirstName.setFont(new Font("Arial",Font.BOLD,15));
		
		home.getContentPane().add(lblFirstName);
		lblLastName.setBounds(247, 94, 88, 14);
		lblLastName.setForeground(new Color(50,205,50));
		lblLastName.setFont(new Font("Arial",Font.BOLD, 15));
		
		home.getContentPane().add(lblLastName);
		firstNameTextField.setBounds(56, 117, 174, 20);
		home.getContentPane().add(firstNameTextField);
		firstNameTextField.setColumns(10);
		
		lastNameTextField.setBounds(247, 117, 180, 20);
		home.getContentPane().add(lastNameTextField);
		lastNameTextField.setColumns(10);
		lblUserName.setBounds(56, 148, 76, 14);
		lblUserName.setForeground(new Color(50,205,50));
		lblUserName.setFont(new Font("Arial", Font.BOLD, 15));
		
		home.getContentPane().add(lblUserName);
		
		home.getContentPane().add(userNameTextField);
		lblEmail.setBounds(56, 198, 55, 14);
		lblEmail.setForeground(new Color(50,205,50));
		lblEmail.setFont(new Font("Arial",Font.BOLD, 15));
		
		home.getContentPane().add(lblEmail);
		
		home.getContentPane().add(emailTextField);
		lblPassword.setBounds(56, 248, 76, 14);
		lblPassword.setForeground(new Color(50,205,50));
		lblPassword.setFont(new Font("Arial", Font.BOLD,15));
		
		home.getContentPane().add(lblPassword);
		home.setVisible(true);
		
		password.setBounds(56,265,174,20);
		home.getContentPane().add(password);
		lblConfirmPassword.setBounds(56, 296, 138, 14);
		lblConfirmPassword.setForeground(new Color(50,205,50));
		lblConfirmPassword.setFont(new Font("Arial",Font.BOLD,15));
		home.getContentPane().add(lblConfirmPassword);
		
		home.getContentPane().add(confirmPasswordTextField);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Male");
		rdbtnNewRadioButton.setBounds(318, 145, 109, 23);
		home.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Female");
		rdbtnNewRadioButton_1.setBounds(318, 171, 109, 23);
		home.getContentPane().add(rdbtnNewRadioButton_1);
	}
}*/
