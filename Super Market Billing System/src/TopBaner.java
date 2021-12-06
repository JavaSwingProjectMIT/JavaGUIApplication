import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class TopBaner extends JPanel {

	Timer updateTimer;

	/**
	 * Create the panel.
	 */
	public TopBaner() {

		setBounds(300, 50, 900, 80);
		setVisible(true);
		setBackground(Colors.darkPurple);
		setLayout(null);

		JPanel panelDate = new JPanel();
		panelDate.setBounds(324, 18, 214, 37);
		panelDate.setBackground(Colors.darkPurple);
		add(panelDate);
		panelDate.setLayout(null);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(12, 15, 41, 15);
		lblDate.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblDate.setForeground(Colors.grey);
		panelDate.add(lblDate);

		JLabel date = new JLabel("");
		date.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		date.setFont(new Font(Colors.font,Font.BOLD,12));
		date.setBounds(52, 15, 137, 15);
		date.setForeground(Colors.grey);
		panelDate.add(date);

		JPanel panelTime = new JPanel();
		panelTime.setBounds(551, 18, 151, 37);
		panelTime.setBackground(Colors.darkPurple);
		add(panelTime);
		panelTime.setLayout(null);

		JLabel lblTime = new JLabel("Time:");
		lblTime.setBounds(22, 15, 43, 15);
		lblTime.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblTime.setForeground(Colors.grey);
		panelTime.add(lblTime);

		JLabel lblHours = new JLabel("");
		lblHours.setHorizontalAlignment(SwingConstants.LEFT);
		lblHours.setBounds(64, 15, 22, 15);
		lblHours.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblHours.setForeground(Colors.grey);
		panelTime.add(lblHours);
		
		JLabel lblMinutes = new JLabel("");
		lblMinutes.setHorizontalAlignment(SwingConstants.LEFT);
		lblMinutes.setBounds(86, 15, 22, 15);
		lblMinutes.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblMinutes.setForeground(Colors.grey);
		panelTime.add(lblMinutes);
		
		JLabel lblSeconds = new JLabel("");
		lblSeconds.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeconds.setBounds(110, 15, 22, 15);
		lblSeconds.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSeconds.setForeground(Colors.grey);
		panelTime.add(lblSeconds);
		
		JLabel lblSeparator = new JLabel(":");
		lblSeparator.setBounds(104, 15, 10, 15);
		lblSeparator.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblSeparator.setForeground(Colors.grey);
		panelTime.add(lblSeparator);
		
		JLabel lblSeparator_1 = new JLabel(":");
		lblSeparator_1.setBounds(80, 15, 10, 15);
		lblSeparator_1.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblSeparator_1.setForeground(Colors.grey);
		panelTime.add(lblSeparator_1);

		JLabel lblLogout = new JLabel("Logout");
		lblLogout.setBounds(735, 27, 55, 21);
		lblLogout.setForeground(Colors.grey);
		lblLogout.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		add(lblLogout);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/home/nmarkovic/git/JavaGUIApplication/Super Market Billing System/images/logoutIcon.png"));
		lblNewLabel.setBounds(804, 23, 32, 32);
		add(lblNewLabel);
		
		JLabel lblHomePage = new JLabel("Home Page");
		lblHomePage.setFont(new Font(Colors.font, Font.BOLD, 20));
		lblHomePage.setHorizontalAlignment(SwingConstants.CENTER);
		lblHomePage.setForeground(Color.WHITE);
		lblHomePage.setBounds(25, 0, 151, 78);
		add(lblHomePage);

		// Clock and Date
		updateTimer = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Date currentTime = new Date();
				int hours = currentTime.getHours();
				int minutes = currentTime.getMinutes();
				int seconds = currentTime.getSeconds();
				if(hours<10) {
				lblHours.setText("0"+String.valueOf(hours));
				} else {
					lblHours.setText(String.valueOf(hours));
				}
				if(minutes <10) {
				lblMinutes.setText("0"+String.valueOf(minutes));
				} else {
					lblMinutes.setText(String.valueOf(minutes));
				}
				if(seconds<10) {
				lblSeconds.setText("0"+String.valueOf(seconds));
				} else {
					lblSeconds.setText(String.valueOf(seconds));
				}
				String formatString = "dd-MM-yyyy";
				DateFormat formatDate = new SimpleDateFormat(formatString);
				String dateString = formatDate.format(currentTime);
				date.setText(dateString);
				
			}
			
		}

		);
		updateTimer.start();


	}
}
