import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class HomePagePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton changePanel = new JButton();
	UsersOverviewPanel overviewUser;
	LeftSideMenu leftSideMenu;
	public static String title = "Home Page";
	public static String username ="";
	/**
	 * Create the panel.
	 */
	public HomePagePanel() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		setLayout(null);
		setBounds(300,0,900,700);
		setBackground(Color.WHITE);
		
		JLabel lblNewLabel = new JLabel("Welcome, dear");
		lblNewLabel.setForeground(Colors.darkPurple);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 33));
		lblNewLabel.setBounds(300, 300, 238, 40);
		add(lblNewLabel);
		
		JLabel lblUsernameH1 = new JLabel("");
		lblUsernameH1.setFont(new Font("Comic Sans MS", Font.BOLD, 33));
		lblUsernameH1.setForeground(Colors.darkPurple);
		lblUsernameH1.setBounds(550, 300, 166, 40);
		add(lblUsernameH1);
		
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
		
		JLabel lblLoggedAsName = new JLabel("");
		//Settings label name to name which depends of user logged
		try {
			con = ConnectionManager.getConnection();
			stmt = con.createStatement();
			sql = "SELECT first_name FROM `user` WHERE username = '" + Login.textFieldUsername.getText() + "'";
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				String nameUserInputed = rs.getString(1);
				String firstLatterToUpper = nameUserInputed.substring(0,1).toUpperCase()+nameUserInputed.substring(1).toLowerCase();
				HomePagePanel.username= firstLatterToUpper;
				lblLoggedAsName.setText(firstLatterToUpper);
				lblUsernameH1.setText(firstLatterToUpper);
			}else {
				
				System.out.println("Could not find user");
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
