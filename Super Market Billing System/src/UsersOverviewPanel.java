import javax.swing.JPanel;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsersOverviewPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public UsersOverviewPanel() {
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
		setLayout(null);
		setBounds(300,0,900,700);
		setBackground(Color.WHITE);
		
		
	}

}
