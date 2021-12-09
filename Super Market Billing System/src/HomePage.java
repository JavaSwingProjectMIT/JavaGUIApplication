import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HomePage {

	HomePagePanel homePanel;
	LeftSideMenu panelLeftSideMenu;
	TopBaner topBaner;
	Login login;
	UsersOverviewPanel userOverviewPanel;
	ManageProductsPanel manageProductsPanel;

	public void setActivePanel(JPanel panel, JPanel[] panels) {

		for (int i = 0; i < panels.length; i++) {
			if (panel == panels[i]) {
				panel.setVisible(true);

			} else {
				panels[i].setVisible(false);

			}
		}

	}

	public void setLeftSideMenuTabsHover(JPanel[] tabs) {
		for (int i = 0; i < tabs.length; i++) {
			final int j = i;
			tabs[i].addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {

				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					tabs[j].setBackground(Colors.aquaMarine);

				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					tabs[j].setBackground(Colors.teal);
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					tabs[j].setBackground(Colors.aquaMarine);
				}
			});

		}

	}

	public HomePage() {

		JFrame homepage = new JFrame();
		panelLeftSideMenu = new LeftSideMenu();
		topBaner = new TopBaner();
		homePanel = new HomePagePanel();
		userOverviewPanel = new UsersOverviewPanel();
		manageProductsPanel=new ManageProductsPanel();
		// Arrays elements order is very important!!!!!!!
		String[] titles = { HomePagePanel.title, UsersOverviewPanel.title, ManageProductsPanel.title };
		JPanel[] panels = { homePanel, userOverviewPanel, manageProductsPanel };
		JPanel[] leftSideTabs = { LeftSideMenu.panelHome, LeftSideMenu.panelUserOverview,
				LeftSideMenu.panelManageProducts, LeftSideMenu.panelAdditionalSettings, LeftSideMenu.panelMakeSale,
				LeftSideMenu.panelManualSale, LeftSideMenu.panelSettings, LeftSideMenu.panelSalesOverview };
		homepage.setBounds(0, 0, 1200, 700);
		homepage.setVisible(true);
		homepage.setLayout(null);
		homepage.add(panelLeftSideMenu);
		homepage.add(topBaner);
		homepage.add(homePanel);
		homepage.add(userOverviewPanel);
		homepage.add(manageProductsPanel);

		// Get back to login page on logout click
		TopBaner.lblLogoutIcon.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				homepage.dispose();
				TopBaner.lblBanerTitle.setText("Home Page");
				try {
					login = new Login();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		setLeftSideMenuTabsHover(leftSideTabs);
		// Matching which tab is clicked and setting corresponding panel to active
		for (int i = 0; i < leftSideTabs.length; i++) {
			final int j = i;
			leftSideTabs[j].addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					for (int k = 0; k < panels.length; k++)
						setActivePanel(panels[j], panels);
					TopBaner.lblBanerTitle.setText(titles[j]);

				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

			});
		}
	}

}
