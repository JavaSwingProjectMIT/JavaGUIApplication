import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import javax.management.loading.MLet;
import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.Destination;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;
import com.mysql.cj.x.protobuf.MysqlxNotice.Frame.Scope;
import com.sun.jdi.connect.spi.Connection;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class MakeSalePanel extends JPanel {

	java.sql.Connection con = null;
	Statement stmt = null;
	String sql = "";
	ResultSet rs = null;
	static String title = "Make Sale";
	private JTextField textFieldPorductCode;
	private JTextField textFieldQuantity;
	private JTextField textFieldMoneyGiven;
	private DefaultTableModel model;
	JPanel panelConfirmCheckout = new JPanel();
	int totalProducts = 0;
	int totalPrice = 0;
	JLabel lblTotalProductsValue = new JLabel("");
	JLabel lblTotalPriceValue = new JLabel("");
	JButton btnCancel = new JButton("Cancel");
	JLabel lblChangeValue = new JLabel("");
	String paragraphToAdd = "";
	String paragraphContent = "";
	String paragraphContent2 = "";
	String totalPriceString = "";

	float moneyGiven = 0;
	float change = 0;

	public void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 15; // Min width
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 1, width);
			}
			if (width > 300)
				width = 300;
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}
//	public String makeParagraph(String paragraphContent) {
//		paragraphToAdd+=paragraphContent;
//		
//		
//		
//	}

	public MakeSalePanel() {
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

		JLabel lblProductCode = new JLabel("Product code:");
		lblProductCode.setBounds(63, 152, 102, 15);
		add(lblProductCode);

		textFieldPorductCode = new JTextField();
		textFieldPorductCode.setBounds(170, 150, 114, 19);
		add(textFieldPorductCode);
		textFieldPorductCode.setColumns(10);

		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(328, 152, 70, 15);
		add(lblQuantity);

		textFieldQuantity = new JTextField();
		textFieldQuantity.setBounds(416, 150, 114, 19);
		add(textFieldQuantity);
		textFieldQuantity.setColumns(10);

		JLabel lblCard = new JLabel("Card:");
		lblCard.setBounds(540, 244, 70, 15);
		add(lblCard);

		String[] colHeadings = { "Product Code", "Product Name", "x", "Product Quantity", "Product Price",
				"Delete product" };
		int numRows = 0;
		model = new DefaultTableModel(numRows, colHeadings.length);
		JTable cardTable = new JTable(model);
		cardTable.setBounds(300, 271, 500, 192);
		cardTable.setBorder(null);
		cardTable.setBackground(Colors.white);
		cardTable.setForeground(Color.black);
		cardTable.setFont(new Font(Colors.font, Font.PLAIN, 13));
		cardTable.setShowGrid(false);
		cardTable.setShowHorizontalLines(false);
		cardTable.setShowVerticalLines(false);
		cardTable.setRowHeight(25);
		cardTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		add(cardTable);
		// Adding to card
		JButton btnAddToCard = new JButton("Add to card");

		JLabel deleteFromCard = new JLabel("X");
		btnAddToCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					con = ConnectionManager.getConnection();
					stmt = con.createStatement();
					sql = "SELECT product_code,product_name,price FROM product WHERE product_code ='"
							+ textFieldPorductCode.getText() + "';";
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						String productName = rs.getString("product_name");
						String productCode = rs.getString("product_code");
						float price = rs.getFloat("price");
						int quantity = Integer.parseInt(textFieldQuantity.getText());
						// upise se u card ovo ispod
						model.addRow(
								new Object[] { productCode, productName, quantity, "x", price * quantity + "$", "X" });
						resizeColumnWidth(cardTable);

					} else {

						JOptionPane.showMessageDialog(null, "Unable to find product,check product code!");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		cardTable.setTableHeader(null);
		JScrollPane scrollPane = new JScrollPane(cardTable);

		scrollPane.setBounds(540, 271, 230, 192);
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.getViewport().setBackground(new Color(255, 255, 255));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		add(scrollPane);

		cardTable.addMouseListener(new MouseListener() {

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
				if (cardTable.getSelectedColumn() == 5) {

					int row = cardTable.getSelectedRow();
					int answer = JOptionPane.showConfirmDialog(null, "Are you sure that you want to delete from card?");
					if (answer == 0) {
						model.removeRow(row);
					}
				}

			}
		});

		btnAddToCard.setBounds(63, 239, 117, 25);
		add(btnAddToCard);
		int getTotalPrice = totalPrice;
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.setBounds(653, 511, 117, 25);
		add(btnCheckout);
		btnCheckout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Provera da tabela nije prazna
				if (cardTable.getRowCount() != 0) {

					String helpCountTotalPrice = "";
					// panel na vidljiv
					panelConfirmCheckout.setVisible(true);
					// Uzimamo sve redove
					for (int i = 0; i < cardTable.getRowCount(); i++) {

						// uzimamo quantity iz tabele u svakom redu
						totalProducts += (int) cardTable.getValueAt(i, 2);
						// uzimamo vrednost cene sa dolarom 40.0$
						helpCountTotalPrice = (String) cardTable.getValueAt(i, 4);
						totalPrice += Float
								.parseFloat(helpCountTotalPrice.substring(0, helpCountTotalPrice.length() - 1));
						System.out.print(totalPrice);

					}
					lblTotalProductsValue.setText(String.valueOf(totalProducts));
					lblTotalPriceValue.setText(totalPrice + "$");
					btnCheckout.setEnabled(false);
					btnCancel.setEnabled(false);
					btnAddToCard.setEnabled(false);
					textFieldPorductCode.setEditable(false);
					textFieldQuantity.setEditable(false);
					cardTable.disable();

				} else {

					JOptionPane.showMessageDialog(null, "Please add products to card!");
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model = new DefaultTableModel(0, colHeadings.length);
				cardTable.setModel(model);
			}
		});
		btnCancel.setBounds(540, 511, 117, 25);
		add(btnCancel);

		// Confirm checkout

		panelConfirmCheckout.setVisible(false);
		panelConfirmCheckout.setBounds(63, 333, 359, 220);
		add(panelConfirmCheckout);
		panelConfirmCheckout.setLayout(null);

		JLabel lblTotalProducts = new JLabel("Total products:");
		lblTotalProducts.setBounds(12, 22, 116, 15);
		panelConfirmCheckout.add(lblTotalProducts);

		JLabel lblNewLabel = new JLabel("Total price:");
		lblNewLabel.setBounds(12, 59, 85, 15);
		panelConfirmCheckout.add(lblNewLabel);

		JLabel lblMoneyGiven = new JLabel("Money given:");
		lblMoneyGiven.setBounds(12, 105, 100, 15);
		panelConfirmCheckout.add(lblMoneyGiven);

		JLabel lblChange = new JLabel("Change:");
		lblChange.setBounds(12, 150, 70, 15);
		panelConfirmCheckout.add(lblChange);

		JButton btnMakeSale = new JButton("Make Sale");
		btnMakeSale.setBounds(231, 183, 116, 25);
		panelConfirmCheckout.add(btnMakeSale);

		JButton btnCancelCheckoutConfirm = new JButton("Cancel");
		btnCancelCheckoutConfirm.setBounds(10, 183, 117, 25);
		panelConfirmCheckout.add(btnCancelCheckoutConfirm);

		btnCancelCheckoutConfirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				btnCheckout.setEnabled(true);
				btnCancel.setEnabled(true);
				panelConfirmCheckout.setVisible(false);

				totalPrice = 0;
				totalProducts = 0;
				cardTable.enable();
				btnAddToCard.setEnabled(true);
				textFieldPorductCode.setEditable(true);
				textFieldQuantity.setEditable(true);
				lblChangeValue.setText("");
				textFieldMoneyGiven.setText("");
			}
		});

		btnMakeSale.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// omogucimo opet izmene sve
				btnCheckout.setEnabled(true);
				btnCancel.setEnabled(true);
				panelConfirmCheckout.setVisible(false);
				// Postavio ukupnu cenu i ukupne produkte na 0
				totalPrice = 0;
				totalProducts = 0;
				cardTable.enable();
				btnAddToCard.setEnabled(true);
				textFieldPorductCode.setEditable(true);
				textFieldQuantity.setEditable(true);
				lblChangeValue.setText("");
				textFieldMoneyGiven.setText("");
				totalPriceString = lblTotalPriceValue.getText();
				// Getting date string to name a bill unique
				Date date = Calendar.getInstance().getTime();// 01023019209129012
				DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy-hh-mm-ss");
				DateFormat dateForDatabase = new SimpleDateFormat("dd-mm-yyyy");
				String strDate = dateFormat.format(date);// 22-12-2021-18-56-26 222221312412
				String[] arr = strDate.split("-", 6);
				// arr=[22,12,2021,18]
				String dateString = "";
				dateString = String.join("", arr);
				// [22,12,2012,18] = 2212201218

				// Getting user_id so multiple users cant generate same bill
				String id = "";
				String name = "";
				String lastname = "";
				String nameToUpper = "";
				String lastnameToUpper = "";
				String finalName = "";
				try {

					con = ConnectionManager.getConnection();
					stmt = con.createStatement();
					String queryToGetUserID = "SELECT user_id,first_name,last_name FROM user WHERE username ='"
							+ Login.username + "';";
					rs = stmt.executeQuery(queryToGetUserID);
					while (rs.next()) {

						id = rs.getString(1);
						name = rs.getString("first_name");
						lastname = rs.getString("last_name");
						nameToUpper = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
						lastnameToUpper = lastname.substring(0, 1).toUpperCase()
								+ lastname.substring(1, lastname.length());
						finalName = nameToUpper + " " + lastnameToUpper;
					}
				} catch (SQLException e) {

					e.printStackTrace();
				}
				// Rect to define pdf size
				Rectangle pdfSize = new Rectangle(212, 279);
				// String builder so we can append strings from card table
				StringBuilder stringBuilderParagraph1 = new StringBuilder();// kod ime
				StringBuilder stringBuilderParagraph2 = new StringBuilder();// 2x40$

				try {

					// pravimo dokument
					Document bill = new Document();
					// generate a PDF at the specified location
					PdfWriter writer = PdfWriter.getInstance(bill, new FileOutputStream(
							"/home/nmarkovic/git/JavaGUIApplication/bill_" + id + "_" + dateString + ".pdf"));
					bill.setPageSize(pdfSize);
					// opens the PDF
					bill.open();

					// adds paragraph to the PDF file
					Paragraph limiterParagraph = new Paragraph("-----------------------------------");
					limiterParagraph.setAlignment(Element.ALIGN_CENTER);

					Paragraph shopNameParagraph = new Paragraph("PODUNAVLJE A.D.");
					shopNameParagraph.setAlignment(Element.ALIGN_CENTER);

					Paragraph address = new Paragraph("JUGOSLOVENSKE ARMIJE 42");
					address.setAlignment(Element.ALIGN_CENTER);

					Paragraph city = new Paragraph("21400 BACKA PALANKA");
					city.setAlignment(Element.ALIGN_CENTER);

					Paragraph company = new Paragraph("CELAREVCANKA");
					company.setAlignment(Element.ALIGN_CENTER);

					Paragraph companyAddres = new Paragraph("SLOVACKA 29");
					companyAddres.setAlignment(Element.ALIGN_CENTER);

					Paragraph postCode = new Paragraph("21413 CELAREVO");
					postCode.setAlignment(Element.ALIGN_CENTER);

					Paragraph pib = new Paragraph("PIB:  100496731");
					pib.setAlignment(Element.ALIGN_LEFT);
					Paragraph ibfm = new Paragraph("IBFM:  AJ030871");
					ibfm.setAlignment(Element.ALIGN_LEFT);

					bill.add(limiterParagraph);
					bill.add(shopNameParagraph);
					bill.add(address);
					bill.add(city);
					bill.add(company);
					bill.add(companyAddres);
					bill.add(postCode);
					bill.add(new Paragraph("\n"));
					bill.add(pib);
					bill.add(ibfm);
					bill.add(limiterParagraph);
					String blanko = " ";
					// getitng values from table to put in pdf
					// getting row
					String priceString = "";
					for (int i = 0; i < cardTable.getRowCount(); i++) {
						// getting column
						for (int j = 0; j < cardTable.getColumnCount(); j++) {
							// if column num is 4, skip cuz we dont want X buttton on bill

							// i=1 j=0
							if (j == 5) {

								continue;
							}
							// Taking product code and name to first paragraph
							if (j == 0 || j == 1) {
								// if column is 0,we dont want want add space before,otherwise we want
								if (j != 0) {
									// Appending columns on stringBuilder
									// stringbuilder = 1312 kikiriki semenke
									stringBuilderParagraph1.append(" " + String.valueOf(cardTable.getValueAt(i, j)));
								} else {
									stringBuilderParagraph1.append(String.valueOf(cardTable.getValueAt(i, j)));
								}
								// creating a final string to add to pdf pharagraph1 with code and name
								// paragrapContent =1312 kikiriki semenke
								paragraphContent = stringBuilderParagraph1.toString();

							}
							// taking product price, x char , and quantity
							if (j == 2 || j == 3 || j == 4) {
								if (j == 4) {

									priceString = String.valueOf(cardTable.getValueAt(i, j));

								}
								if (j != 2) {
									// Appending columns on stringBuilder
									// string builder = x 2 40$
									stringBuilderParagraph2.append(" " + String.valueOf(cardTable.getValueAt(i, j)));
								} else {
									stringBuilderParagraph2.append(String.valueOf(cardTable.getValueAt(i, j)));
								}
								// creating a final string to add to pdf pharagraph
								// paragraph = x 2 40$
								paragraphContent2 = stringBuilderParagraph2.toString();

							}
						}
						// kraj j petlje
						// nstavak i petlje
						// Checking if paragraph content is accidenlty empty cuz we dont want empty para
						// in our bill
						if (paragraphContent == "") {
							System.out.println("emptystr");
						} else {
							// Creating new paragraph with content made of single row
							Paragraph addToBillParagraph = new Paragraph(paragraphContent);
							Paragraph addToBillParagraph2 = new Paragraph(paragraphContent2 + "    " + priceString);
							// Setting paragraph alignment to center of pdf document
							addToBillParagraph.setAlignment(Element.ALIGN_LEFT);
							addToBillParagraph2.setAlignment(Element.ALIGN_LEFT);
							// Adding paragraph to document
							bill.add(addToBillParagraph);
							bill.add(addToBillParagraph2);
						}
						// After adding to pdf, clearing paragraph content string and string builder so
						// next row can come in
						paragraphContent = "";
						paragraphContent2 = "";
						stringBuilderParagraph1.setLength(0);
						stringBuilderParagraph2.setLength(0);

					} // kraj i petlje
					bill.add(limiterParagraph);
					Paragraph sdj = new Paragraph("SDJ:                  20.0%");
					sdj.setAlignment(Element.ALIGN_LEFT);
					Paragraph se = new Paragraph("SE:                    10.0%");
					se.setAlignment(Element.ALIGN_LEFT);
					Paragraph pdj = new Paragraph("PDJ:                  343.86");
					pdj.setAlignment(Element.ALIGN_LEFT);
					Paragraph pe = new Paragraph("PE:                    39.23");
					pe.setAlignment(Element.ALIGN_LEFT);
					Paragraph pt = new Paragraph("PT:                    383.09");
					pt.setAlignment(Element.ALIGN_LEFT);
					Paragraph edj = new Paragraph("EDJ:                  2.063,16");
					edj.setAlignment(Element.ALIGN_LEFT);
					Paragraph ee = new Paragraph("EE:                    431.51");
					ee.setAlignment(Element.ALIGN_LEFT);
					Paragraph et = new Paragraph("ET:                    2.494,67");
					et.setAlignment(Element.ALIGN_LEFT);
					bill.add(sdj);
					bill.add(se);
					bill.add(pdj);
					bill.add(pe);
					bill.add(pt);
					bill.add(edj);
					bill.add(ee);
					bill.add(et);

					bill.add(limiterParagraph);
					Paragraph totalPriceParagraph = new Paragraph("ZA UPLATU:       " + totalPriceString);
					totalPriceParagraph.setAlignment(Element.ALIGN_LEFT);
					bill.add(totalPriceParagraph);

					Paragraph payedWithParagraph = new Paragraph("UPLACENO:       " + moneyGiven + "$");
					payedWithParagraph.setAlignment(Element.ALIGN_LEFT);
					bill.add(payedWithParagraph);

					Paragraph changeParagraph = new Paragraph("POVRACAJ:        " + String.valueOf(change) + "$");
					changeParagraph.setAlignment(Element.ALIGN_LEFT);
					bill.add(changeParagraph);

					Paragraph dateParagraph = new Paragraph(strDate);
					dateParagraph.setAlignment(Element.ALIGN_LEFT);
					bill.add(dateParagraph);

					Paragraph biParagraph = new Paragraph("BI:  145109");
					biParagraph.setAlignment(Element.ALIGN_LEFT);
					bill.add(biParagraph);

					bill.add(limiterParagraph);
					Paragraph employeeParagraph = new Paragraph("KASIR:");
					employeeParagraph.setAlignment(Element.ALIGN_LEFT);
					bill.add(employeeParagraph);

					Paragraph employeeNameParagraph = new Paragraph(finalName);
					employeeNameParagraph.setAlignment(Element.ALIGN_LEFT);
					bill.add(employeeNameParagraph);

					bill.add(limiterParagraph);

					// close the PDF file
					bill.close();
					// closes the writer
					writer.close();
					
					 
					   // set the output file as a destination 
					
					PrinterJob pj = PrinterJob.getPrinterJob();
					java.sql.Date sqlDate = new java.sql.Date(date.getTime());
					// ...
					if (pj.printDialog()) {
						
						try {
							pj.print();
						} catch (PrinterException exc) {
							System.out.println(exc);
						}
					}
					
					try {
						con=ConnectionManager.getConnection();
						stmt=con.createStatement();
						String queryString = "INSERT INTO `sales` (`sale_id`, `employee`, `total_price`, `sale_change`, `user_id`, `created_at`, `product_quantity`,`money_given`) VALUES (NULL, '"+finalName+"', '"+totalPriceString+"', '"+String.valueOf(change)+"$', '"+Integer.parseInt(id)+"', '"+sqlDate+"', '"+lblTotalProductsValue.getText()+"','"+String.valueOf(moneyGiven)+"$"+"');";
						stmt.executeUpdate(queryString);
					}catch(SQLException e) {
						
						e.printStackTrace();
					}
					
					// ...
				} catch (DocumentException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			}
		});

		lblTotalProductsValue.setBounds(123, 22, 70, 15);
		panelConfirmCheckout.add(lblTotalProductsValue);

		lblTotalPriceValue.setBounds(101, 59, 70, 15);
		panelConfirmCheckout.add(lblTotalPriceValue);

		textFieldMoneyGiven = new JTextField();
		textFieldMoneyGiven.setBounds(109, 103, 114, 19);
		panelConfirmCheckout.add(textFieldMoneyGiven);
		textFieldMoneyGiven.setColumns(10);

		lblChangeValue.setBounds(76, 150, 70, 15);
		panelConfirmCheckout.add(lblChangeValue);

		JButton btnGetChange = new JButton("Get Change");
		btnGetChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!(textFieldMoneyGiven.getText().isEmpty())) {
					moneyGiven = Float.parseFloat(textFieldMoneyGiven.getText());
					change = moneyGiven - totalPrice;
					lblChangeValue.setText(String.valueOf(change));
				} else {
					JOptionPane.showMessageDialog(null, "Please enter money paid with");
				}
			}

		});
		btnGetChange.setBounds(231, 145, 117, 25);
		panelConfirmCheckout.add(btnGetChange);

	}
}
