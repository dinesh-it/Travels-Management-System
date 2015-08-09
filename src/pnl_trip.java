import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;


public class pnl_trip extends JPanel {

	/**
	 * Create the panel.
	 */
	public pnl_trip() {
		Toolkit tool=Toolkit.getDefaultToolkit();
		Dimension dim=tool.getScreenSize();
		setSize(dim.width,dim.height);
		setLayout(null);
		
		JLabel lblTripPanel = new JLabel("Trip panel");
		lblTripPanel.setFont(new Font("Dialog", Font.BOLD, 34));
		lblTripPanel.setBounds(146, 171, 203, 64);
		add(lblTripPanel);
	

	}
}
