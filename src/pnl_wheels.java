import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;


public class pnl_wheels extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTable table;
	private JPanel panel_1;

	/**
	 * Create the panel.
	 */
	public pnl_wheels() {
		Toolkit tool=Toolkit.getDefaultToolkit();
		Dimension dim=tool.getScreenSize();
		setSize(dim.width,dim.height);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Customer Details", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(86, 81, 1189, 128);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblMobileNo = new JLabel("Mobile No");
		lblMobileNo.setBounds(118, 39, 80, 15);
		panel.add(lblMobileNo);
		
		textField = new JTextField();
		textField.setBounds(216, 35, 172, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(81, 70, 117, 24);
		panel.add(lblCustomerName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(216, 71, 172, 24);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblVehicleN = new JLabel("Vehicle No");
		lblVehicleN.setBounds(528, 39, 80, 15);
		panel.add(lblVehicleN);
		
		textField_2 = new JTextField();
		textField_2.setBounds(614, 35, 34, 24);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblVehicleMake = new JLabel("Vehicle Make");
		lblVehicleMake.setBounds(515, 75, 93, 15);
		panel.add(lblVehicleMake);
		
		textField_3 = new JTextField();
		textField_3.setBounds(614, 71, 163, 23);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(658, 36, 34, 23);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(704, 36, 73, 23);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(908, 39, 41, 15);
		panel.add(lblDate);
		
		textField_6 = new JTextField();
		textField_6.setBounds(951, 35, 132, 24);
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblBillNo = new JLabel("Bill No");
		lblBillNo.setBounds(905, 75, 44, 15);
		panel.add(lblBillNo);
		
		textField_7 = new JTextField();
		textField_7.setBounds(951, 73, 132, 24);
		panel.add(textField_7);
		textField_7.setColumns(10);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"S. No.", "Details", "Quantity", "Amount", "Total"
			}
		));
		
		
		JScrollPane jsp_table = new JScrollPane(table);
		jsp_table.setBounds(270, 231, 804, 325);
		add(jsp_table);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(440, 568, 458, 76);
		add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Save Bill");
		btnNewButton.setBounds(24, 24, 117, 25);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset Form");
		btnNewButton_1.setBounds(169, 24, 117, 25);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Close Form");
		btnNewButton_2.setBounds(308, 24, 117, 25);
		panel_1.add(btnNewButton_2);
	}
}
