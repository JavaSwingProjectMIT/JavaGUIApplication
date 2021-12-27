import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class SettingPanel extends JPanel {

	HomePage homePage ;
	
	public static String title = "Settings";
	Connection con = null;
	Statement stmt = null;
	ResultSet rs= null;
	String sql="";
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JTextField textFieldConfirmPassword;
	Login login;
	String passwordString="";

	/**
	 * Create the panel.
	 */
	public SettingPanel() {
		
		
	
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
		
		JButton btnDeleteAccount = new JButton("Delete Account");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int answer = JOptionPane.showConfirmDialog(null, "Are you sure that you want to delete this account?");
				if(answer == 0) {
					
					
					try {
						con = ConnectionManager.getConnection();
						stmt = con.createStatement();
						String queryString = "DELETE from user WHERE username ='"+Login.username+"';";
						stmt.executeUpdate(queryString);
						
						try {
							HomePage.homepage.dispose();
							login = new Login();
							
							
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				
			}
		});
		btnDeleteAccount.setBounds(498, 442, 200, 25);
		add(btnDeleteAccount);
		
		JButton btnChangeAccountData = new JButton("Change Account Data");
		btnChangeAccountData.setBounds(224, 442, 200, 25);
		add(btnChangeAccountData);
		
		btnChangeAccountData.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				textFieldUsername.setEditable(true);
				
				
				try {
					con=ConnectionManager.getConnection();
					stmt=con.createStatement();
					String sqlString = "SELECT password FROM user WHERE username ='"+Login.username+"';";
					rs=stmt.executeQuery(sqlString);
					if(rs.next()) {
						passwordString= rs.getString("password");
						
					}
					con.close();
					
				} catch (SQLException e) {
					// TODO: handle exception
				}
				
			}
		});
		System.out.println(passwordString);
		
		JPanel panel = new JPanel();
		panel.setBounds(224, 241, 542, 189);
		add(panel);
		panel.setLayout(null);
		
		
		
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(12, 27, 90, 15);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(12, 71, 90, 15);
		panel.add(lblPassword);
		
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(120, 25, 143, 19);
		panel.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		textFieldUsername.setText(Login.username);
		textFieldUsername.setEditable(false);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(117, 69, 151, 19);
		panel.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(12, 117, 137, 15);
		panel.add(lblConfirmPassword);
		
		textFieldConfirmPassword = new JTextField();
		textFieldConfirmPassword.setBounds(169, 115, 151, 19);
		panel.add(textFieldConfirmPassword);
		textFieldConfirmPassword.setColumns(10);
		String password="";
		try {
			con=ConnectionManager.getConnection();
			stmt=con.createStatement();
			String queryString = "SELECT password FROM user WHERE username='"+Login.username+"';";
			rs=stmt.executeQuery(queryString);
			if(rs.next()) {
			password = rs.getString("password");
				
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}
		
		

	}
}
