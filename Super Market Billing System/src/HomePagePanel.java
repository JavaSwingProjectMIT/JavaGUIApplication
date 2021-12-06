import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class HomePagePanel extends JPanel {
	JButton changePanel = new JButton();
	UsersOverviewPanel overviewUser;
	LeftSideMenu leftSideMenu;
	/**
	 * Create the panel.
	 */
	public HomePagePanel() {
		setVisible(true);
		setLayout(null);
		setBounds(300,0,900,700);
		setBackground(Color.WHITE);
		
		JLabel lblNewLabel = new JLabel("Welcome, dear");
		lblNewLabel.setForeground(Colors.darkPurple);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 33));
		lblNewLabel.setBounds(300, 300, 238, 40);
		add(lblNewLabel);
		
		JLabel lblNikola = new JLabel("Nikola");
		lblNikola.setFont(new Font("Comic Sans MS", Font.BOLD, 33));
		lblNikola.setForeground(Colors.darkPurple);
		lblNikola.setBounds(550, 300, 166, 40);
		add(lblNikola);
		
		JLabel lblLetsMakeSome = new JLabel("Let's make some good sales");
		lblLetsMakeSome.setHorizontalAlignment(SwingConstants.CENTER);
		lblLetsMakeSome.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		lblLetsMakeSome.setForeground(Colors.darkPurple);
		lblLetsMakeSome.setBounds(246, 352, 461, 47);
		add(lblLetsMakeSome);
		
		JButton btnGetStarted = new JButton("Get started");
		btnGetStarted.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnGetStarted.setBounds(374, 442, 224, 47);
		btnGetStarted.setForeground(Colors.grey);
		btnGetStarted.setBackground(Colors.darkPurple);
		add(btnGetStarted);
		
		JLabel lblLoggedInAs = new JLabel("Logged in as");
		lblLoggedInAs.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblLoggedInAs.setBounds(52, 12, 102, 21);
		lblLoggedInAs.setForeground(Colors.darkPurple);
		add(lblLoggedInAs);
		
		JLabel lblLoggedAsName = new JLabel("Nikola");
		lblLoggedAsName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblLoggedAsName.setBounds(142, 12, 79, 21);
		lblLoggedAsName.setForeground(Colors.darkPurple);
		add(lblLoggedAsName);
		
		JLabel lblUserIcon = new JLabel("");
		lblUserIcon.setIcon(new ImageIcon("/home/nmarkovic/git/JavaGUIApplication/Super Market Billing System/images/userIcon.png"));
		lblUserIcon.setBounds(24, 16, 16, 16);
		add(lblUserIcon);
		
		

	}
}
