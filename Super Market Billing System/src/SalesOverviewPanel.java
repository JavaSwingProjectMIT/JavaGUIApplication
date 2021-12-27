import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class SalesOverviewPanel extends JPanel {
	
	public static String title= "Sales Overview";
	Connection con = null;
	Statement stmt = null;
	ResultSet rs= null;
	String sql="";
	private JTable table = new JTable();
	Object data1[][];
	/**
	 * Create the panel.
	 */
	
	public TableModel loadData() {

		Object[] Colheads = { "ID", "Employee", "Total Price", "Sale Change", "User ID", "Created At","Quantity","Money Given" };
		try {

			con = ConnectionManager.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			sql = "SELECT * FROM sales;";

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
	public SalesOverviewPanel() {
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
		
		
		table.setModel(loadData());
		table.setBackground(Colors.white);
		table.setForeground(Color.black);
		table.setFont(new Font(Colors.font, Font.PLAIN, 13));
		table.setShowGrid(false);
		table.setShowHorizontalLines(false);
		table.setShowVerticalLines(false);
		table.setRowHeight(25);
		
		JTableHeader th = table.getTableHeader();
		th.setFont(new Font(Colors.font, Font.BOLD, 14));
		th.setBackground(Colors.white);
		
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
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(119, 226, 700, 200);
		add(jsp);

	}
	
	
	

}
