import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.prefs.Preferences;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class UsersOverviewPanel extends JPanel {
	Object data1[][];
	public static String title = "Users Overview";
	private JTable table;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	String sql = "";
	String sql1 = "";
	JComboBox comboBox = new JComboBox();
	DefaultTableCellRenderer renderer;
	private JTextField textFieldSearch;

	/**
	 * Create the panel.
	 */
	// Set table header alignment to left
	public void SetHeaderAlignToLeft(JTable table) {
		renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(JLabel.LEFT);
		renderer.setBorder(BorderFactory.createEmptyBorder());

	}

	// Load data from database and set table model to table
	public TableModel loadSearchData() {

		Object[] Colheads = { "First Name", "Last Name", "Gender", "Address", "City" };
		try {

			con = ConnectionManager.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			rs = stmt.executeQuery(sql1);

			// Counting rows
			rs.last();
			int rows = rs.getRow();
			rs.beforeFirst();
			ResultSetMetaData metaData = rs.getMetaData();
			int colummm = metaData.getColumnCount();

			if (Colheads.length != colummm) {

				JOptionPane.showMessageDialog(this,
						"Incorrect Column Headers quantity listed in array! The program will now exit.", "System Error",
						JOptionPane.ERROR_MESSAGE);

			}

			data1 = new Object[rows][Colheads.length];

			for (int i1 = 0; i1 < rows; i1++) {
				rs.next();
				for (int j1 = 0; j1 < Colheads.length; j1++) {

					data1[i1][j1] = rs.getString(j1 + 1);

				}
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new DefaultTableModel(data1, Colheads);

	}

	public TableModel loadData() {

		Object[] Colheads = { "First Name", "Last Name", "Gender", "Address", "City" };
		try {

			con = ConnectionManager.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			if (comboBox.getSelectedItem() == "A-Z") {

				sql = "SELECT first_name,last_name,gender,address,city FROM `user` ORDER by first_name ASC";
			} else if (comboBox.getSelectedItem() == "Z-A") {
				sql = "SELECT first_name,last_name,gender,address,city FROM `user` ORDER by first_name DESC";
			} else if (comboBox.getSelectedItem() == "Address") {
				sql = "SELECT first_name,last_name,gender,address,city FROM `user` ORDER by address ASC";
			} else if (sql1.isEmpty()) {

				sql = "SELECT first_name,last_name,gender,address,city FROM `user` ORDER by address ASC";
			}

			rs = stmt.executeQuery(sql);

			// Counting rows
			rs.last();
			int rows = rs.getRow();
			rs.beforeFirst();
			ResultSetMetaData metaData = rs.getMetaData();
			int colummm = metaData.getColumnCount();

			if (Colheads.length != colummm) {

				JOptionPane.showMessageDialog(this,
						"Incorrect Column Headers quantity listed in array! The program will now exit.", "System Error",
						JOptionPane.ERROR_MESSAGE);

			}

			data1 = new Object[rows][Colheads.length];

			for (int i1 = 0; i1 < rows; i1++) {
				rs.next();
				for (int j1 = 0; j1 < Colheads.length; j1++) {

					data1[i1][j1] = rs.getString(j1 + 1);

				}
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new DefaultTableModel(data1, Colheads);

	}

	public UsersOverviewPanel() {

		setLayout(null);
		setBounds(300, 0, 900, 700);
		setBackground(Color.WHITE);

		JLabel lblSearchIcon = new JLabel("");
		lblSearchIcon.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSearchIcon.setIcon(new ImageIcon("images/iconSearch.png"));
		lblSearchIcon.setBounds(262, 186, 24, 24);
		add(lblSearchIcon);

		JLabel lblLoggedInAs = new JLabel("Logged in as");

		lblLoggedInAs.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblLoggedInAs.setBounds(52, 12, 102, 21);
		lblLoggedInAs.setForeground(Colors.darkPurple);
		add(lblLoggedInAs);
		JLabel lblLoggedAsName = new JLabel("");
		// Settings label name to name which depends of user logged
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

		lblLoggedAsName.setForeground(new Color(59, 42, 94));
		lblLoggedAsName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblLoggedAsName.setBounds(142, 12, 79, 21);
		add(lblLoggedAsName);
		JTable table = new JTable(loadData());
		System.out.println(table.getTableHeader());
		table.setBackground(Colors.white);
		table.setForeground(Color.black);
		table.setFont(new Font(Colors.font, Font.PLAIN, 13));
		table.setShowGrid(false);
		table.setShowHorizontalLines(false);
		table.setShowVerticalLines(false);
		table.setRowHeight(25);
		SetHeaderAlignToLeft(table);
		// Get table header
		JTableHeader th = table.getTableHeader();
		th.setFont(new Font(Colors.font, Font.BOLD, 14));
		th.setBackground(Colors.white);
		/////
		// Getting table rows and columns
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (!isSelected) { // Important check, see comment below!
					boolean levelBreak = row % 2 == 0;
					if (!levelBreak) {

					}
					comp.setBackground(levelBreak ? Colors.grey : Colors.white);

				}
				return comp;
			}
		});

		// Add table to scroll pane
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(119, 226, 642, 282);
		add(jsp);

		comboBox.setModel(new DefaultComboBoxModel(new String[] { "A-Z", "Z-A", "Address" }));
		comboBox.setBounds(644, 550, 117, 24);
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(loadData());

			}
		});
		add(comboBox);

		JLabel lblRefreshTableIcon = new JLabel("");
		lblRefreshTableIcon.setIcon(new ImageIcon("images/iconRefresh24.png"));
		lblRefreshTableIcon.setBounds(729, 186, 32, 32);
		// Refresh table if user is registered manual
		lblRefreshTableIcon.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				table.setModel(loadData());

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblRefreshTableIcon.setIcon(new ImageIcon("images/refreshIcon.png"));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				lblRefreshTableIcon.setIcon(new ImageIcon("images/iconRefresh24.png"));
			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}
		});

		JLabel lblSortUsers = new JLabel("Sort Users");
		lblSortUsers.setForeground(Colors.darkPurple);
		lblSortUsers.setBounds(644, 530, 88, 15);
		add(lblSortUsers);
		add(lblRefreshTableIcon);

		textFieldSearch = new JTextField();
		textFieldSearch.setBounds(119, 186, 171, 28);
		textFieldSearch.addKeyListener(new KeyListener() {
			String search = "";

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// Getting char pressed on keyboard
				char c = e.getKeyChar();
				// Converting char to string
				String helper = String.valueOf(c);
				// Backspace pressed?
				if (e.getKeyChar() == '') {
					// Cutting search string
					if (!search.isEmpty()) {
						search = search.substring(0, search.length() - 1);
					} else {
						sql1 = "SELECT first_name,last_name,gender,address,city FROM `user` ORDER by address ASC";
					}
				} else {
					// Concatenating string to make query string
					search = search.concat(helper);
				}
				sql1 = "SELECT first_name,last_name,gender,address,city FROM `user` WHERE first_name LIKE '%" + search
						+ "%'";
				if (!sql1.isEmpty()) {
					// Execute query and update table content only if sql1 string isn't empty
					table.setModel(loadSearchData());
				}

			}
		});
		add(textFieldSearch);
		textFieldSearch.setColumns(10);
		JLabel lblSearchUsers = new JLabel("Search Users:");
		lblSearchUsers.setBounds(118, 159, 103, 15);
		add(lblSearchUsers);

	}
}
