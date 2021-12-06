import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class HomePage {

	HomePagePanel panelHome;
	LeftSideMenu panelLeftSideMenu;
	TopBaner topBaner;
	Login login;

	public HomePage() {

		JFrame homepage = new JFrame();
		panelLeftSideMenu = new LeftSideMenu();
		topBaner = new TopBaner();
		panelHome = new HomePagePanel();

		homepage.setBounds(0, 0, 1200, 700);
		homepage.setVisible(true);
		homepage.setLayout(null);
		homepage.add(panelLeftSideMenu);
		homepage.add(topBaner);
		homepage.add(panelHome);
//		homepage.add(panelUserOverview);
		
		//Get back to login page on logout click
		TopBaner.lblLogoutIcon.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				homepage.dispose();
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
		LeftSideMenu.panelHome.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				LeftSideMenu.panelHome.setBackground(Colors.aquaMarine);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				LeftSideMenu.panelHome.setBackground(Colors.teal);

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
		// User overview button listener
		LeftSideMenu.panelUserOverview.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				LeftSideMenu.panelUserOverview.setBackground(Colors.aquaMarine);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				LeftSideMenu.panelUserOverview.setBackground(Colors.teal);

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
		// panelManageProducts listener
		LeftSideMenu.panelManageProducts.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				LeftSideMenu.panelManageProducts.setBackground(Colors.aquaMarine);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				LeftSideMenu.panelManageProducts.setBackground(Colors.teal);

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
		// panelMakeSale listener
		LeftSideMenu.panelMakeSale.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				LeftSideMenu.panelMakeSale.setBackground(Colors.aquaMarine);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				LeftSideMenu.panelMakeSale.setBackground(Colors.teal);

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
		// Panel Manual Sale listener
		LeftSideMenu.panelManualSale.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				LeftSideMenu.panelManualSale.setBackground(Colors.aquaMarine);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				LeftSideMenu.panelManualSale.setBackground(Colors.teal);

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
		// Panel Sales Overview Listener
		LeftSideMenu.panelSalesOverview.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				LeftSideMenu.panelSalesOverview.setBackground(Colors.aquaMarine);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				LeftSideMenu.panelSalesOverview.setBackground(Colors.teal);

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
		// Panel Settings Listener
		LeftSideMenu.panelSettings.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				LeftSideMenu.panelSettings.setBackground(Colors.aquaMarine);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				LeftSideMenu.panelSettings.setBackground(Colors.teal);

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
		// Panel Additional Settings Listener
		LeftSideMenu.panelAdditionalSettings.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				LeftSideMenu.panelAdditionalSettings.setBackground(Colors.aquaMarine);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				LeftSideMenu.panelAdditionalSettings.setBackground(Colors.teal);

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
