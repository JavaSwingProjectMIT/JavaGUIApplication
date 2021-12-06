import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;

public class LeftSideMenu extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JPanel panelUserOverview = new JPanel();
	public static JPanel panelManageProducts = new JPanel();
	public static JPanel panelMakeSale = new JPanel();
	public static JPanel panelManualSale = new JPanel();
	public static JPanel panelSalesOverview = new JPanel();
	public static JPanel panelSettings = new JPanel();
	public static JPanel panelAdditionalSettings = new JPanel();
	public static JPanel panelHome = new JPanel();
	private final JLabel lblLogo = new JLabel("The Best Super Market");
	public static JLabel lblHomePage;

	/**
	 * Create the panel.
	 */

	public static void editPanel(JPanel panel, Color backgorund, int[] bounds) {
		int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
		for (int i = 0; i < bounds.length; i++) {
			x1 = bounds[0];
			y1 = bounds[1];
			x2 = bounds[2];
			y2 = bounds[3];
		}
		panel.setBounds(x1, y1, x2, y2);
		panel.setLayout(null);
		panel.setBackground(backgorund);

	}

	public static JLabel createJLabel(String variableName, int[] bounds, String title, String fontname,
			Color foreground, String string) {
		int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
		for (int i = 0; i < bounds.length; i++) {
			x1 = bounds[0];
			y1 = bounds[1];
			x2 = bounds[2];
			y2 = bounds[3];
		}
		Font font = new Font(fontname, Font.BOLD, 14);
		JLabel variableName1 = new JLabel(title);
		variableName1.setIcon(new ImageIcon(string));
		variableName1.setForeground(foreground);
		variableName1.setFont(font);
		variableName1.setBounds(x1, y1, x2, y2);
		return variableName1;

	}

	public LeftSideMenu() {
		int[] bounds = { 0, 0, 0, 0 };
		setBorder(null);
		setBackground(Colors.teal);
		setLayout(null);
		setBounds(0, 0, 300, 700);

		// Home tab panel
		editPanel(panelHome, Colors.teal, bounds = new int[] { 0, 100, 300, 60 });
		JLabel lblHomePage = createJLabel("lblHomePage", bounds = new int[] { 120, 17, 147, 20 }, "Home Page", Colors.font,
				Colors.grey, "");
		panelHome.add(lblHomePage);
		panelHome.add(
				createJLabel("lblHomePageIcon", bounds = new int[] { 34, 12, 32, 32 }, "", Colors.font, Colors.grey,
						"images/homePageIcon.png"));
		add(panelHome);

		// UserOverview tab panel
		editPanel(panelUserOverview, Colors.teal, bounds = new int[] { 0, 164, 300, 60 });
		panelUserOverview.add(createJLabel("lblUserOverview", bounds = new int[] { 120, 17, 147, 20 }, "User Overview",
				"Comic Sans MS", Colors.grey, ""));
		panelUserOverview.add(createJLabel("lblUserIcon", bounds = new int[] { 34, 12, 32, 32 }, "", Colors.font,
				Colors.grey, "images/usersIcon.png"));
		add(panelUserOverview);

		// ManageProducts tab panel
		editPanel(panelManageProducts, Colors.teal, bounds = new int[] { 0, 224, 300, 60 });
		panelManageProducts.add(createJLabel("lblManageProducts", bounds = new int[] { 120, 17, 157, 20 },
				"Manage Products", "Comic Sans MS", Colors.grey, ""));
		panelManageProducts.add(createJLabel("lblProductIcon", bounds = new int[] { 34, 12, 32, 32 }, "", Colors.font,
				Colors.grey, "images/productIcon.png"));
		add(panelManageProducts);

		// MakeSale tab panel
		editPanel(panelMakeSale, Colors.teal, bounds = new int[] { 0, 284, 300, 60 });
		add(panelMakeSale);
		panelMakeSale.add(createJLabel("lblMakeSale", bounds = new int[] { 120, 17, 157, 20 }, "Make Sale", Colors.font,
				Colors.grey, ""));
		panelMakeSale.add(createJLabel("lblMakeSaleIcon", bounds = new int[] { 34, 12, 32, 32 }, "", Colors.font,
				Colors.grey, "images/saleIcon.png"));

		// ManualSale tab panel
		editPanel(panelManualSale, Colors.teal, bounds = new int[] { 0, 344, 300, 60 });
		panelManualSale.add(createJLabel("lblManualSale", bounds = new int[] { 120, 17, 157, 20 }, "Manual Sale",
				Colors.font, Colors.grey, ""));
		panelManualSale.add(createJLabel("lblManualSaleIcon_1", bounds = new int[] { 32, 17, 32, 32 }, "", Colors.font,
				Colors.grey, "images/manualSaleIcon.png"));
		add(panelManualSale);

		// SalesOverview tab panel
		editPanel(panelSalesOverview, Colors.teal, bounds = new int[] { 0, 404, 300, 60 });
		panelSalesOverview.add(createJLabel("lblSalesOverview", bounds = new int[] { 120, 17, 157, 20 },
				"Sales Overview", Colors.font, Colors.grey, ""));
		panelSalesOverview.add(createJLabel("lblSalesOverviewIcon", bounds = new int[] { 34, 12, 32, 32 }, "",
				Colors.font, Colors.grey, "images/salesOverview.png"));
		add(panelSalesOverview);
		// Settings tab panel
		editPanel(panelSettings, Colors.teal, bounds = new int[] { 0, 464, 300, 60 });
		panelSettings.add(createJLabel("lblSettings", bounds = new int[] { 120, 17, 157, 20 }, "Settings", Colors.font,
				Colors.grey, ""));
		panelSettings.add(createJLabel("lblSettingsIcon", bounds = new int[] { 34, 12, 32, 32 }, "", Colors.font,
				Colors.grey, "images/settingsIcon.png"));
		add(panelSettings);
		editPanel(panelAdditionalSettings, Colors.teal, bounds = new int[] { 0, 524, 300, 60 });
		panelAdditionalSettings.add(createJLabel("lblAdditionalSettings", bounds = new int[] { 120, 17, 157, 20 },
				"Additional Settings", Colors.font, Colors.grey, ""));
		panelAdditionalSettings.add(createJLabel("lblAdditionalSettingsIcon", bounds = new int[] { 34, 12, 32, 32 }, "",
				Colors.font, Colors.grey, "images/additionalSettingsIcon.png"));
		add(panelAdditionalSettings);
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblLogo.setBounds(24, 31, 247, 29);
		add(lblLogo);

	}
}
