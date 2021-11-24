import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class RegisterPage {

	
	
	JFrame home = new JFrame();
	JLabel welcomeLabel = new JLabel("WELCOME");
	
	
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
