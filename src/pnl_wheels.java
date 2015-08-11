import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;


public class pnl_wheels extends JPanel {
	private JTextField txt_mobile_no, txt_customer_name, txt_vehicle_no_1, txt_vehicl_make, txt_vehicle_no_2, txt_vehicle_no_3, txt_date, txt_bill_no;
	private JTable tbl_particulars;
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
		
		JLabel lbl_mobile_no = new JLabel("Mobile No");
		lbl_mobile_no.setBounds(118, 39, 80, 15);
		panel.add(lbl_mobile_no);
		
		txt_mobile_no = new JTextField();
		txt_mobile_no.setBounds(216, 35, 172, 24);
		panel.add(txt_mobile_no);
		txt_mobile_no.setColumns(10);
		
		JLabel lbl_customer_name = new JLabel("Customer Name");
		lbl_customer_name.setBounds(81, 70, 117, 24);
		panel.add(lbl_customer_name);
		
		txt_customer_name = new JTextField();
		txt_customer_name.setBounds(216, 71, 172, 24);
		panel.add(txt_customer_name);
		txt_customer_name.setColumns(10);
		
		JLabel lbl_vehicle_no = new JLabel("Vehicle No");
		lbl_vehicle_no.setBounds(528, 39, 80, 15);
		panel.add(lbl_vehicle_no);
		
		txt_vehicle_no_1 = new JTextField();
		txt_vehicle_no_1.setBounds(614, 35, 34, 24);
		panel.add(txt_vehicle_no_1);
		txt_vehicle_no_1.setColumns(10);
		
		JLabel lbl_vehicle_make = new JLabel("Vehicle Make");
		lbl_vehicle_make.setBounds(515, 75, 93, 15);
		panel.add(lbl_vehicle_make);
		
		txt_vehicl_make = new JTextField();
		txt_vehicl_make.setBounds(614, 71, 163, 23);
		panel.add(txt_vehicl_make);
		txt_vehicl_make.setColumns(10);
		
		txt_vehicle_no_2 = new JTextField();
		txt_vehicle_no_2.setBounds(658, 36, 34, 23);
		panel.add(txt_vehicle_no_2);
		txt_vehicle_no_2.setColumns(10);
		
		txt_vehicle_no_3 = new JTextField();
		txt_vehicle_no_3.setBounds(704, 36, 73, 23);
		panel.add(txt_vehicle_no_3);
		txt_vehicle_no_3.setColumns(10);
		
		JLabel lbl_date = new JLabel("Date");
		lbl_date.setBounds(908, 39, 41, 15);
		panel.add(lbl_date);
		
		txt_date = new JTextField();
		txt_date.setBounds(951, 35, 132, 24);
		panel.add(txt_date);
		txt_date.setColumns(10);
		
		JLabel lbl_bill_no = new JLabel("Bill No");
		lbl_bill_no.setBounds(905, 75, 44, 15);
		panel.add(lbl_bill_no);
		
		txt_bill_no = new JTextField();
		txt_bill_no.setBounds(951, 73, 132, 24);
		panel.add(txt_bill_no);
		txt_bill_no.setColumns(10);
		
		tbl_particulars = new JTable();
		tbl_particulars.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"S. No.", "Details", "Quantity", "Amount", "Total"
			}
		));
		
		
		JScrollPane tbl_particulars_main = new JScrollPane(tbl_particulars);
		tbl_particulars_main.setBounds(270, 231, 804, 325);
		add(tbl_particulars_main);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(440, 568, 458, 76);
		add(panel_1);
		panel_1.setLayout(null);
		
		JButton btn_save_bill = new JButton("Save Bill");
		btn_save_bill.setBounds(24, 24, 117, 25);
		panel_1.add(btn_save_bill);
		
		JButton btn_clear_form = new JButton("Clear Form");
		btn_clear_form.setBounds(169, 24, 117, 25);
		panel_1.add(btn_clear_form);
		
		JButton btn_close_form = new JButton("Close Form");
		btn_close_form.setBounds(308, 24, 117, 25);
		panel_1.add(btn_close_form);
	}
}
