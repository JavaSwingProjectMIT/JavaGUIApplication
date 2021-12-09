import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
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

	/**
	 * Create the panel.
	 */
	public ManageProductsPanel() {
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

		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.setBounds(147, 135, 138, 25);
		add(btnAddProduct);
		btnAddProduct.setVisible(false);
		JButton btnEditProduct = new JButton("Edit Product");
		btnEditProduct.setBounds(314, 135, 138, 25);
		add(btnEditProduct);
		btnEditProduct.setVisible(false);

		JPanel panelEditAddProducts = new JPanel();
		panelEditAddProducts.setBounds(147, 192, 506, 179);
		add(panelEditAddProducts);
		panelEditAddProducts.setLayout(null);
		panelEditAddProducts.setVisible(false);

		JLabel lblProductName = new JLabel("Product Name:");
		lblProductName.setBounds(23, 12, 104, 15);
		panelEditAddProducts.add(lblProductName);

		textFieldProductName = new JTextField();
		textFieldProductName.setBounds(128, 10, 114, 19);
		panelEditAddProducts.add(textFieldProductName);
		textFieldProductName.setColumns(10);

		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrice.setBounds(284, 109, 68, 15);
		panelEditAddProducts.add(lblPrice);

		textFieldPrice = new JTextField();
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(363, 107, 114, 19);
		panelEditAddProducts.add(textFieldPrice);

		JTextArea textAreaDescription = new JTextArea();
		textAreaDescription.setBounds(128, 48, 349, 38);
		panelEditAddProducts.add(textAreaDescription);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(39, 48, 87, 15);
		panelEditAddProducts.add(lblDescription);

		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(83, 111, 39, 15);
		panelEditAddProducts.add(lblType);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(128, 109, 114, 19);
		panelEditAddProducts.add(textField);

		JLabel lblProductCode = new JLabel("Product Code:");
		lblProductCode.setBounds(253, 10, 104, 15);
		panelEditAddProducts.add(lblProductCode);

		textFieldProductCode = new JTextField();
		textFieldProductCode.setColumns(10);
		textFieldProductCode.setBounds(363, 8, 114, 19);
		panelEditAddProducts.add(textFieldProductCode);

		JButton btn = new JButton("Edit Product");
		btn.setBounds(339, 142, 138, 25);
		panelEditAddProducts.add(btn);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[]  {"cakalada"}));

		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (comboBox.getSelectedItem() == "cakalada") {
					panelEditAddProducts.setVisible(true);
				}
			}

		});
		String [] items = {} ;
		comboBox.setBounds(519, 135, 156, 25);
		add(comboBox);
		try {
			con = ConnectionManager.getConnection();
			stmt = con.createStatement();
			sql = "SELECT product_name FROM product WHERE id IS NOT NULL";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
			
				
				

			}
			System.out.print(items);
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JLabel lblSelectProduct = new JLabel("Select Product");
		lblSelectProduct.setBounds(519, 103, 134, 15);
		add(lblSelectProduct);

	}
}
