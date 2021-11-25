import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class RegisterPage {

	JFrame home = new JFrame();
	JLabel welcomeLabel = new JLabel("WELCOME");
	
	//*First Name
	JLabel lblFirstName = new JLabel("First Name:");
	JTextField textFieldFirstName = new JTextField();
	//*Last Name
	JLabel lblLastName = new JLabel("Last Name:");
	JTextField textFieldLastName = new JTextField();
	//*User Name
	JLabel lblUserName = new JLabel("Username:");
	JTextField textFieldUserName = new JTextField();
	//*Email field
	JLabel lblEmail = new JLabel("E-mail:");
	JTextField textFieldEmail = new JTextField();
	//*Password
	JLabel lblPassword = new JLabel("Password:");
	JPasswordField passwordField = new JPasswordField();
	//*Confirm Password
	JLabel lblConfirmPassword = new JLabel("Confirm password");
	JPasswordField confirmPasswordField = new JPasswordField();
	//*Gender
	JRadioButton radioButtonMale = new JRadioButton("Male");
	JRadioButton radioButtonFemale = new JRadioButton("Female");
	//*Remember me
	JCheckBox chckbxRememberCheckBox = new JCheckBox("Remember me!");
	//Register button
	JButton btnRegister = new JButton("Register");
	
	RegisterPage(){
		
		welcomeLabel.setBounds(0,0,100,50);
		welcomeLabel.setFont(new Font(null,Font.PLAIN,25));
		
		home.add(welcomeLabel);
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		home.setSize(1000,1000);
		home.setLayout(null);
		home.setVisible(true);
	}
}
