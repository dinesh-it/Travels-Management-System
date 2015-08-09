import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class pnl_wheels extends JPanel {

	/**
	 * Create the panel.
	 */
	public pnl_wheels() {
		Toolkit tool=Toolkit.getDefaultToolkit();
		Dimension dim=tool.getScreenSize();
		setSize(dim.width,dim.height);
		setLayout(null);
		
		JLabel lblTripPanel = new JLabel("Wheels panel");
		lblTripPanel.setFont(new Font("Dialog", Font.BOLD, 34));
		lblTripPanel.setBounds(146, 171, 258, 40);
		add(lblTripPanel);
	}

}
