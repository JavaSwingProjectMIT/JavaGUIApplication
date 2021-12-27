import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.channels.Pipe;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

public class ManageProductsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String title = "Manage Products";
	Connection con = null;
	Statement stmt = null;
	String sql = "";
	ResultSet rs = null;
	private JTextField textFieldProductName;
	private JTextField textFieldPrice;
	private JTextField textField;
	private JTextField textFieldProductCode;
	JTextArea textAreaDescription = new JTextArea();
	private JPanel panelEditAddProducts = new JPanel();
	private JComboBox comboBox = new JComboBox();
	Object data1[][];
	private JTable table = new JTable();
	DefaultTableCellRenderer renderer;
	String[] selectedProduct = new String[6];
	JTextField textFieldSearch;
	JButton btnSubmit = new JButton("Submit");
	JButton btnClose = new JButton("Close");
	JButton btnAddProduct = new JButton("Add");
	String sql1 = "";

	/**
	 * Create the panel.
	 */
	public void SetHeaderAlignToLeft(JTable table) {
		renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(JLabel.LEFT);
		renderer.setBorder(BorderFactory.createEmptyBorder());

	}

	public TableModel loadSearchData() {

		Object[] Colheads = { "ID", "Product Name", "Product Desc", "Product Type", "Product Code", "Product Price" };
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

		Object[] Colheads = { "ID", "Product Name", "Product Desc", "Product Type", "Product Code", "Product Price" };
		try {

			con = ConnectionManager.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			sql = "SELECT id,product_name,product_description,product_type,product_code,price FROM product;";

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

	public ManageProductsPanel() {
		setLayout(null);
		setBounds(300, 0, 900, 700);
		setBackground(Colors.white);

		JButton btnEditProduct = new JButton("Edit");
		btnEditProduct.setBackground(Color.WHITE);
		btnEditProduct.setBounds(339, 142, 138, 25);
		btnEditProduct.setVisible(false);
		panelEditAddProducts.setBackground(Colors.darkPurple);
		panelEditAddProducts.add(btnEditProduct);

		JLabel lblEditIcon = new JLabel("");

		lblEditIcon.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (!(selectedProduct[0].isEmpty())) {
					panelEditAddProducts.setVisible(false);
				}
				btnEditProduct.setVisible(true);
				btnAddProduct.setVisible(false);
				panelEditAddProducts.setVisible(true);
				textFieldProductName.setText(selectedProduct[1]);
				textFieldProductCode.setText(selectedProduct[4]);
				textAreaDescription.setText(selectedProduct[2]);
				textField.setText(selectedProduct[3]);
				textFieldPrice.setText(selectedProduct[5]);

				btnEditProduct.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (textFieldProductCode.getText().length() < 4) {

							JOptionPane.showMessageDialog(null, "Product code must be length of 4!");
						} else if(textField.getText().isEmpty()|| textAreaDescription.getText().isEmpty() || textFieldProductName.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Please fill all fields!");
						} else {
							String name = textFieldProductName.getText();
							String code = textFieldProductCode.getText();
							String description = textAreaDescription.getText();
							String type = textField.getText();
							String priceString = textFieldPrice.getText();

							try {
								con = ConnectionManager.getConnection();
								stmt = con.createStatement();
								String sql3 = "UPDATE product SET product_name ='" + name + "', product_code ="
										+ Integer.parseInt(code) + ",product_" + "description ='" + description
										+ "',product_type='" + type + "', price=" + Float.parseFloat(priceString)
										+ "  WHERE id=" + selectedProduct[0] + ";";
								stmt.executeUpdate(sql3);

								stmt.close();
							} catch (SQLException ex) {

								ex.printStackTrace();

							}
							table.setModel(loadData());
							panelEditAddProducts.setVisible(false);
						}
					}
				});
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		lblEditIcon.setBounds(730, 188, 24, 24);
		lblEditIcon.setIcon(new ImageIcon("images/editIcon.png"));
		add(lblEditIcon);

		JLabel lblDeleteIcon = new JLabel("");

		// Delete product
		lblDeleteIcon.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {

				int result = JOptionPane.showConfirmDialog(null, "Are you sure that you want to delete this item?");
				if (result == 0) {
					try {
						con=ConnectionManager.getConnection();
						stmt= con.createStatement();
						String query ="DELETE FROM product WHERE id="+selectedProduct[0]+";";
						stmt.executeUpdate(query);
						table.setModel(loadData());
					}catch(SQLException ex) {
						
					}
				} 
			}
		});
		lblDeleteIcon.setBounds(610, 188, 24, 24);
		lblDeleteIcon.setIcon(new ImageIcon("images/deleteIcon.png"));
		add(lblDeleteIcon);

		JLabel lblAddIcon = new JLabel("");
		lblAddIcon.setBounds(670, 188, 24, 24);
		lblAddIcon.setIcon(new ImageIcon("images/addIcon.png"));

		// Add product submit button

		btnAddProduct.setBounds(339, 142, 138, 25);
		panelEditAddProducts.add(btnAddProduct);
		btnAddProduct.setVisible(false);
		// Click on button
		btnAddProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (textFieldProductCode.getText().length() < 4) {

					JOptionPane.showMessageDialog(null, "Product code must be length of 4!");
				}else if(textField.getText().isEmpty()|| textAreaDescription.getText().isEmpty() || textFieldProductName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill all fields!");
				} else {
					String productName = textFieldProductName.getText();
					String code = textFieldProductCode.getText();
					String description = textAreaDescription.getText();
					String type = textField.getText();
					float price = Float.parseFloat(textFieldPrice.getText());

					try {
						con = ConnectionManager.getConnection();
						stmt = con.createStatement();
						sql = "INSERT INTO `product` (`product_description`, `id`, `product_name`, `product_type`,"
								+ " `price`, `product_code`) " + "VALUES ('" + description + "', NULL, " + "'"
								+ productName + "', '" + type + "', '" + price + "', '" + code + "');";

						stmt.executeUpdate(sql);

						panelEditAddProducts.setVisible(false);
						table.setModel(loadData());

					} catch (SQLException ex) {
						ex.printStackTrace();

					}

				}
			}
		});
		// Add product label
		lblAddIcon.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				panelEditAddProducts.setVisible(true);
				btnAddProduct.setVisible(true);
				btnEditProduct.setVisible(false);
				textFieldProductName.setText("");
				textFieldProductCode.setText("");
				textAreaDescription.setText("");
				textField.setText("");
				textFieldPrice.setText("");

			}
		});
		btnClose.setBackground(Color.WHITE);
		// Closing panel button
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelEditAddProducts.setVisible(false);

			}
		});

		add(lblAddIcon);

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
		lblLoggedAsName.setForeground(new Color(59, 42, 94));
		lblLoggedAsName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblLoggedAsName.setBounds(142, 12, 79, 21);
		add(lblLoggedAsName);
		System.out.print(Login.username);
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

		textAreaDescription.setBounds(128, 48, 349, 38);
		panelEditAddProducts.add(textAreaDescription);

		textFieldPrice = new JTextField();
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(363, 107, 114, 19);
		panelEditAddProducts.add(textFieldPrice);

		textFieldProductName = new JTextField();
		textFieldProductName.setBounds(128, 10, 114, 19);
		panelEditAddProducts.add(textFieldProductName);
		textFieldProductName.setColumns(10);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(128, 109, 114, 19);
		panelEditAddProducts.add(textField);

		panelEditAddProducts.setBounds(119, 430, 506, 179);
		add(panelEditAddProducts);
		panelEditAddProducts.setLayout(null);
		panelEditAddProducts.setVisible(false);

		JLabel lblProductName = new JLabel("Product Name:");
		lblProductName.setForeground(Color.WHITE);
		lblProductName.setBounds(23, 12, 104, 15);
		panelEditAddProducts.add(lblProductName);

		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrice.setBounds(284, 109, 68, 15);
		panelEditAddProducts.add(lblPrice);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setForeground(Color.WHITE);
		lblDescription.setBounds(39, 48, 87, 15);
		panelEditAddProducts.add(lblDescription);

		JLabel lblType = new JLabel("Type:");
		lblType.setForeground(Color.WHITE);
		lblType.setBackground(Color.BLACK);
		lblType.setBounds(83, 111, 39, 15);
		panelEditAddProducts.add(lblType);

		JLabel lblProductCode = new JLabel("Product Code:");
		lblProductCode.setForeground(Color.WHITE);
		lblProductCode.setBounds(253, 10, 104, 15);
		panelEditAddProducts.add(lblProductCode);

		textFieldProductCode = new JTextField();
		textFieldProductCode.setColumns(10);
		textFieldProductCode.setBounds(363, 8, 114, 19);

		textFieldProductCode.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

				if (textFieldProductCode.getText().length() == 4) {
					e.consume();

				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		panelEditAddProducts.add(textFieldProductCode);

		btnClose.setBounds(23, 142, 138, 25);
		panelEditAddProducts.add(btnClose);

		JLabel lblSelectProduct = new JLabel("Select Product");
		lblSelectProduct.setBounds(519, 103, 134, 15);

		add(lblSelectProduct);
		table.setModel(loadData());
		table.setBackground(Colors.white);
		table.setForeground(Color.black);
		table.setFont(new Font(Colors.font, Font.PLAIN, 13));
		table.setShowGrid(false);
		table.setShowHorizontalLines(false);
		table.setShowVerticalLines(false);
		table.setRowHeight(25);
		SetHeaderAlignToLeft(table);
		JTableHeader th = table.getTableHeader();
		th.setFont(new Font(Colors.font, Font.BOLD, 14));
		th.setBackground(Colors.white);
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

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				String name = textFieldProductName.getText();
				String code = textFieldProductCode.getText();
				String description = textAreaDescription.getText();
				String type = textField.getText();
				String priceString = textFieldPrice.getText();
				int row = table.getSelectedRow();// 0

				for (int i = 0; i <= 5; i++) {
					selectedProduct[i] = (String) table.getValueAt(row, i);

					System.out.println(selectedProduct[i]);
				}
				panelEditAddProducts.setVisible(false);

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyListener() {
			String search = "";

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
						sql1 = "SELECT id,product_name,product_description,product_type,product_code,price FROM `product` ORDER by address ASC";
					}
				} else {
					// Concatenating string to make query string
					search = search.concat(helper);
				}
				sql1 = "SELECT id,product_name,product_description,product_type,product_code,price FROM `product` WHERE product_name LIKE '%"
						+ search + "%'";
				if (!sql1.isEmpty()) {
					// Execute query and update table content only if sql1 string isn't empty
					table.setModel(loadSearchData());
				}

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		textFieldSearch.setBounds(119, 186, 171, 28);
		add(textFieldSearch);
		textFieldSearch.setColumns(10);
		JLabel lblSearchUsers = new JLabel("Search Users:");
		lblSearchUsers.setBounds(118, 159, 103, 15);
		add(lblSearchUsers);

		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(119, 226, 642, 179);
		add(jsp);

	}
}
