import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class pnl_trip_add extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_20;
	private JTextField textField_4;
	private JTextField textField_21;
	private JTextField textField_6;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_22;

	/**
	 * Create the panel.
	 */
	public pnl_trip_add() {
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension dim = tool.getScreenSize();
		setSize(dim.width, dim.height);
		setLayout(null);
		
		JCheckBox chckbxOtherTravels = new JCheckBox("Other Travels");
		chckbxOtherTravels.setHorizontalAlignment(SwingConstants.RIGHT);
		chckbxOtherTravels.setBounds(621, 285, 121, 23);
		add(chckbxOtherTravels);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.window);
		panel_2.setBounds(61, 51, 1239, 67);
		add(panel_2);
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(51, 51, 51), 1, true), "Trip Details", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("Visiting Place");
		label.setBounds(26, 35, 105, 15);
		panel_2.add(label);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Local", "Outstation"}));
		comboBox.setFont(new Font("Dialog", Font.BOLD, 17));
		comboBox.setBounds(135, 30, 119, 24);
		panel_2.add(comboBox);
		
		JLabel lblVehicleNo = new JLabel("Vehicle No");
		lblVehicleNo.setBounds(309, 35, 88, 15);
		panel_2.add(lblVehicleNo);
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField.setBounds(404, 30, 33, 25);
		panel_2.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_1.setBounds(449, 30, 33, 24);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_2.setBounds(497, 30, 69, 24);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_2 = new JLabel("Make");
		label_2.setBounds(623, 34, 47, 17);
		panel_2.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_3.setBounds(672, 30, 114, 22);
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel label_3 = new JLabel("Date");
		label_3.setBounds(856, 31, 47, 17);
		panel_2.add(label_3);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_4.setColumns(10);
		textField_4.setBounds(901, 30, 105, 24);
		panel_2.add(textField_4);
		
		JLabel label_4 = new JLabel("Trip No");
		label_4.setBounds(1061, 35, 59, 15);
		panel_2.add(label_4);
		
		JSpinner spinner_4 = new JSpinner();
		spinner_4.setBounds(1119, 30, 96, 25);
		panel_2.add(spinner_4);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(51, 51, 51), 1, true), "Customer", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(224, 130, 377, 495);
		add(panel);
		
		JLabel label_5 = new JLabel("Name");
		label_5.setFont(new Font("Dialog", Font.BOLD, 14));
		label_5.setBounds(12, 41, 119, 15);
		panel.add(label_5);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_5.setColumns(10);
		textField_5.setBounds(149, 29, 211, 29);
		panel.add(textField_5);
		
		JLabel label_6 = new JLabel("Km");
		label_6.setFont(new Font("Dialog", Font.BOLD, 14));
		label_6.setBounds(149, 72, 33, 15);
		panel.add(label_6);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(175, 65, 90, 29);
		panel.add(spinner);
		
		JLabel label_7 = new JLabel("Usage");
		label_7.setFont(new Font("Dialog", Font.BOLD, 14));
		label_7.setBounds(12, 72, 70, 15);
		panel.add(label_7);
		
		JLabel lblHrs = new JLabel("Hrs");
		lblHrs.setFont(new Font("Dialog", Font.BOLD, 14));
		lblHrs.setBounds(271, 70, 33, 15);
		panel.add(lblHrs);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(301, 65, 57, 29);
		panel.add(spinner_1);
		
		JLabel label_10 = new JLabel("Extra Amount");
		label_10.setFont(new Font("Dialog", Font.BOLD, 14));
		label_10.setBounds(12, 200, 119, 15);
		panel.add(label_10);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_7.setColumns(10);
		textField_7.setBounds(149, 193, 211, 29);
		panel.add(textField_7);
		
		JLabel label_11 = new JLabel("Total");
		label_11.setFont(new Font("Dialog", Font.BOLD, 14));
		label_11.setBounds(12, 348, 70, 15);
		panel.add(label_11);
		
		JLabel label_12 = new JLabel("345.00");
		label_12.setForeground(Color.BLUE);
		label_12.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		label_12.setBounds(180, 348, 180, 15);
		panel.add(label_12);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_8.setColumns(10);
		textField_8.setBounds(149, 375, 211, 29);
		panel.add(textField_8);
		
		JLabel label_13 = new JLabel("Driver Advance");
		label_13.setFont(new Font("Dialog", Font.BOLD, 14));
		label_13.setBounds(12, 382, 130, 15);
		panel.add(label_13);
		
		JLabel label_14 = new JLabel("Office Advance");
		label_14.setFont(new Font("Dialog", Font.BOLD, 14));
		label_14.setBounds(12, 419, 130, 15);
		panel.add(label_14);
		
		textField_9 = new JTextField();
		textField_9.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_9.setColumns(10);
		textField_9.setBounds(149, 411, 211, 29);
		panel.add(textField_9);
		
		JLabel label_15 = new JLabel("Comments");
		label_15.setFont(new Font("Dialog", Font.BOLD, 14));
		label_15.setBounds(12, 460, 119, 15);
		panel.add(label_15);
		
		JLabel label_16 = new JLabel("Slab Amount");
		label_16.setFont(new Font("Dialog", Font.BOLD, 14));
		label_16.setBounds(12, 145, 105, 15);
		panel.add(label_16);
		
		textField_10 = new JTextField();
		textField_10.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_10.setColumns(10);
		textField_10.setBounds(149, 138, 211, 27);
		panel.add(textField_10);
		
		JLabel label_17 = new JLabel("Bata");
		label_17.setFont(new Font("Dialog", Font.BOLD, 14));
		label_17.setBounds(12, 237, 70, 15);
		panel.add(label_17);
		
		textField_11 = new JTextField();
		textField_11.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_11.setColumns(10);
		textField_11.setBounds(149, 229, 211, 29);
		panel.add(textField_11);
		
		JLabel label_20 = new JLabel("Check Post");
		label_20.setFont(new Font("Dialog", Font.BOLD, 14));
		label_20.setBounds(12, 274, 119, 15);
		panel.add(label_20);
		
		textField_12 = new JTextField();
		textField_12.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_12.setColumns(10);
		textField_12.setBounds(149, 266, 211, 29);
		panel.add(textField_12);
		
		JLabel label_34 = new JLabel("Parking Toll");
		label_34.setFont(new Font("Dialog", Font.BOLD, 14));
		label_34.setBounds(12, 315, 119, 15);
		panel.add(label_34);
		
		textField_20 = new JTextField();
		textField_20.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_20.setColumns(10);
		textField_20.setBounds(149, 307, 211, 29);
		panel.add(textField_20);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(149, 102, 211, 24);
		panel.add(comboBox_1);
		
		JLabel label_35 = new JLabel("Extra: 20Km , 5Hrs");
		label_35.setBounds(149, 174, 216, 15);
		panel.add(label_35);
		
		JLabel label_36 = new JLabel("Slab (Km/Hr)");
		label_36.setFont(new Font("Dialog", Font.BOLD, 14));
		label_36.setBounds(12, 107, 105, 15);
		panel.add(label_36);
		
		textField_21 = new JTextField();
		textField_21.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_21.setBounds(149, 452, 211, 29);
		panel.add(textField_21);
		textField_21.setColumns(10);
		
		JLabel lblRs = new JLabel("Rs");
		lblRs.setForeground(Color.BLUE);
		lblRs.setFont(new Font("Dialog", Font.BOLD, 15));
		lblRs.setBounds(149, 348, 26, 15);
		panel.add(lblRs);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.window);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBounds(460, 637, 445, 50);
		add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnSaveTrip = new JButton("Save Trip");
		btnSaveTrip.setBounds(36, 12, 117, 25);
		panel_3.add(btnSaveTrip);
		
		JButton btnClearForm = new JButton("Clear Form");
		btnClearForm.setBounds(165, 12, 117, 25);
		panel_3.add(btnClearForm);
		
		JButton btnCloseForm = new JButton("Close Form");
		btnCloseForm.setBounds(294, 12, 117, 25);
		panel_3.add(btnCloseForm);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.window);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(51, 51, 51), 1, true), "Travels", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(760, 130, 377, 495);
		add(panel_1);
		
		JLabel label_8 = new JLabel("Name");
		label_8.setBackground(UIManager.getColor("Checkbox.select"));
		label_8.setFont(new Font("Dialog", Font.BOLD, 14));
		label_8.setBounds(12, 41, 119, 15);
		panel_1.add(label_8);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_6.setColumns(10);
		textField_6.setBounds(149, 29, 211, 29);
		panel_1.add(textField_6);
		
		JLabel label_9 = new JLabel("Km");
		label_9.setFont(new Font("Dialog", Font.BOLD, 14));
		label_9.setBounds(149, 72, 33, 15);
		panel_1.add(label_9);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(175, 65, 90, 29);
		panel_1.add(spinner_2);
		
		JLabel label_18 = new JLabel("Usage");
		label_18.setFont(new Font("Dialog", Font.BOLD, 14));
		label_18.setBounds(12, 72, 70, 15);
		panel_1.add(label_18);
		
		JLabel label_19 = new JLabel("Hrs");
		label_19.setFont(new Font("Dialog", Font.BOLD, 14));
		label_19.setBounds(271, 70, 33, 15);
		panel_1.add(label_19);
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setBounds(301, 65, 57, 29);
		panel_1.add(spinner_3);
		
		JLabel label_21 = new JLabel("Extra Amount");
		label_21.setFont(new Font("Dialog", Font.BOLD, 14));
		label_21.setBounds(12, 200, 119, 15);
		panel_1.add(label_21);
		
		textField_13 = new JTextField();
		textField_13.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_13.setColumns(10);
		textField_13.setBounds(149, 193, 211, 29);
		panel_1.add(textField_13);
		
		JLabel label_22 = new JLabel("Total");
		label_22.setFont(new Font("Dialog", Font.BOLD, 14));
		label_22.setBounds(12, 348, 70, 15);
		panel_1.add(label_22);
		
		JLabel label_23 = new JLabel("345.00");
		label_23.setForeground(Color.BLUE);
		label_23.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		label_23.setBounds(180, 348, 180, 15);
		panel_1.add(label_23);
		
		textField_14 = new JTextField();
		textField_14.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_14.setColumns(10);
		textField_14.setBounds(149, 375, 211, 29);
		panel_1.add(textField_14);
		
		JLabel label_24 = new JLabel("Driver Advance");
		label_24.setFont(new Font("Dialog", Font.BOLD, 14));
		label_24.setBounds(12, 382, 130, 15);
		panel_1.add(label_24);
		
		JLabel label_25 = new JLabel("Office Advance");
		label_25.setFont(new Font("Dialog", Font.BOLD, 14));
		label_25.setBounds(12, 419, 130, 15);
		panel_1.add(label_25);
		
		textField_15 = new JTextField();
		textField_15.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_15.setColumns(10);
		textField_15.setBounds(149, 411, 211, 29);
		panel_1.add(textField_15);
		
		JLabel label_26 = new JLabel("Comments");
		label_26.setFont(new Font("Dialog", Font.BOLD, 14));
		label_26.setBounds(12, 460, 119, 15);
		panel_1.add(label_26);
		
		JLabel label_27 = new JLabel("Slab Amount");
		label_27.setFont(new Font("Dialog", Font.BOLD, 14));
		label_27.setBounds(12, 145, 105, 15);
		panel_1.add(label_27);
		
		textField_16 = new JTextField();
		textField_16.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_16.setColumns(10);
		textField_16.setBounds(149, 138, 211, 27);
		panel_1.add(textField_16);
		
		JLabel label_28 = new JLabel("Bata");
		label_28.setFont(new Font("Dialog", Font.BOLD, 14));
		label_28.setBounds(12, 237, 70, 15);
		panel_1.add(label_28);
		
		textField_17 = new JTextField();
		textField_17.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_17.setColumns(10);
		textField_17.setBounds(149, 229, 211, 29);
		panel_1.add(textField_17);
		
		JLabel label_29 = new JLabel("Check Post");
		label_29.setFont(new Font("Dialog", Font.BOLD, 14));
		label_29.setBounds(12, 274, 119, 15);
		panel_1.add(label_29);
		
		textField_18 = new JTextField();
		textField_18.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_18.setColumns(10);
		textField_18.setBounds(149, 266, 211, 29);
		panel_1.add(textField_18);
		
		JLabel label_30 = new JLabel("Parking Toll");
		label_30.setFont(new Font("Dialog", Font.BOLD, 14));
		label_30.setBounds(12, 315, 119, 15);
		panel_1.add(label_30);
		
		textField_19 = new JTextField();
		textField_19.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_19.setColumns(10);
		textField_19.setBounds(149, 307, 211, 29);
		panel_1.add(textField_19);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(149, 102, 211, 24);
		panel_1.add(comboBox_2);
		
		JLabel label_31 = new JLabel("Extra: 20Km , 5Hrs");
		label_31.setBounds(149, 174, 253, 15);
		panel_1.add(label_31);
		
		JLabel label_32 = new JLabel("Slab (Km/Hr)");
		label_32.setFont(new Font("Dialog", Font.BOLD, 14));
		label_32.setBounds(12, 107, 105, 15);
		panel_1.add(label_32);
		
		textField_22 = new JTextField();
		textField_22.setFont(new Font("Dialog", Font.PLAIN, 17));
		textField_22.setColumns(10);
		textField_22.setBounds(149, 452, 211, 29);
		panel_1.add(textField_22);
		
		JLabel label_33 = new JLabel("Rs");
		label_33.setForeground(Color.BLUE);
		label_33.setFont(new Font("Dialog", Font.BOLD, 15));
		label_33.setBounds(149, 348, 26, 15);
		panel_1.add(label_33);
		

	}
}
