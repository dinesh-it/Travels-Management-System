package UserInterface;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.SystemColor;

import Util.*;

public class pnl_trip_add extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txt_vehicle_no_1, txt_vehicle_no_2, txt_vehicle_no_3, txt_vehicle_name, txt_date,
	txt_cust_name, txt_cust_extra_amt, txt_cust_driver_advance, txt_cust_office_advance, txt_cust_slab_amt, txt_cust_bata, txt_cust_check_post, txt_cust_parking_toll, txt_cust_comments, 
	txt_trvl_name, txt_trvl_extra_amt, txt_trvl_driver_advance, txt_trvl_office_advance, txt_trvl_slab_amt, txt_trvl_bata, txt_trvl_check_post, txt_trvl_parking_toll, txt_trvl_comments;

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

		JCheckBox ckb_other_travels = new JCheckBox("Other Travels");
		ckb_other_travels.setHorizontalAlignment(SwingConstants.RIGHT);
		ckb_other_travels.setBounds(621, 285, 121, 23);
		add(ckb_other_travels);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.window);
		panel_2.setBounds(61, 51, 1239, 67);
		add(panel_2);
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(51, 51, 51), 1, true), "Trip Details", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_2.setLayout(null);

		JLabel lbl_circle = new JLabel("Visiting Place");
		lbl_circle.setBounds(26, 35, 105, 15);
		panel_2.add(lbl_circle);

		JComboBox<String> cmb_circle = new JComboBox<String>();
		lbl_circle.setLabelFor(cmb_circle);
		cmb_circle.setModel(new DefaultComboBoxModel<String>(new String[] {"Local", "Outstation"}));
		cmb_circle.setFont(new Font("Dialog", Font.BOLD, 17));
		cmb_circle.setBounds(135, 30, 119, 24);
		panel_2.add(cmb_circle);

		JLabel lbl_vehicle_no = new JLabel("Vehicle No");
		lbl_vehicle_no.setBounds(309, 35, 88, 15);
		panel_2.add(lbl_vehicle_no);

		txt_vehicle_no_1 = new JTextField();
		txt_vehicle_no_1.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_vehicle_no_1.setBounds(404, 30, 33, 25);
		panel_2.add(txt_vehicle_no_1);
		txt_vehicle_no_1.setColumns(10);

		txt_vehicle_no_2 = new JTextField();
		txt_vehicle_no_2.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_vehicle_no_2.setBounds(449, 30, 33, 24);
		panel_2.add(txt_vehicle_no_2);
		txt_vehicle_no_2.setColumns(10);

		txt_vehicle_no_3 = new JTextField();
		txt_vehicle_no_3.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_vehicle_no_3.setBounds(497, 30, 69, 24);
		panel_2.add(txt_vehicle_no_3);
		txt_vehicle_no_3.setColumns(10);

		JLabel lbl_vehicle_make = new JLabel("Make");
		lbl_vehicle_make.setBounds(623, 34, 47, 17);
		panel_2.add(lbl_vehicle_make);

		txt_vehicle_name = new JTextField();
		txt_vehicle_name.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_vehicle_name.setBounds(672, 30, 114, 22);
		panel_2.add(txt_vehicle_name);
		txt_vehicle_name.setColumns(10);

		JLabel lbl_date = new JLabel("Date");
		lbl_date.setBounds(856, 31, 47, 17);
		panel_2.add(lbl_date);

		txt_date = new JTextField();
		txt_date.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_date.setColumns(10);
		txt_date.setBounds(901, 30, 105, 24);
		txt_date.setText(Time.get_date(Time.now()));
		panel_2.add(txt_date);

		JLabel lbl_trip_no = new JLabel("Trip No");
		lbl_trip_no.setBounds(1061, 35, 59, 15);
		panel_2.add(lbl_trip_no);

		JSpinner spn_trip_no = new JSpinner();
		spn_trip_no.setBounds(1119, 30, 96, 25);
		panel_2.add(spn_trip_no);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(51, 51, 51), 1, true), "Customer", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(224, 130, 377, 495);
		add(panel);

		JLabel lbl_cust_name = new JLabel("Name");
		lbl_cust_name.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_cust_name.setBounds(12, 37, 119, 15);
		panel.add(lbl_cust_name);

		txt_cust_name = new JTextField();
		txt_cust_name.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_cust_name.setColumns(10);
		txt_cust_name.setBounds(149, 29, 211, 29);
		panel.add(txt_cust_name);

		JLabel label_6 = new JLabel("Km");
		label_6.setFont(new Font("Dialog", Font.BOLD, 14));
		label_6.setBounds(149, 72, 33, 15);
		panel.add(label_6);

		JSpinner spn_cust_usage_km = new JSpinner();
		spn_cust_usage_km.setBounds(175, 65, 90, 29);
		panel.add(spn_cust_usage_km);

		JLabel lbl_cust_usage = new JLabel("Usage");
		lbl_cust_usage.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_cust_usage.setBounds(12, 72, 70, 15);
		panel.add(lbl_cust_usage);

		JLabel lblHrs = new JLabel("Hrs");
		lblHrs.setFont(new Font("Dialog", Font.BOLD, 14));
		lblHrs.setBounds(271, 70, 33, 15);
		panel.add(lblHrs);

		JSpinner spn_cust_usage_hrs = new JSpinner();
		spn_cust_usage_hrs.setBounds(301, 65, 57, 29);
		panel.add(spn_cust_usage_hrs);

		JLabel lbl_cust_extra_amt = new JLabel("Extra Amount");
		lbl_cust_extra_amt.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_cust_extra_amt.setBounds(12, 200, 119, 15);
		panel.add(lbl_cust_extra_amt);

		txt_cust_extra_amt = new JTextField();
		txt_cust_extra_amt.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_cust_extra_amt.setColumns(10);
		txt_cust_extra_amt.setBounds(149, 193, 211, 29);
		panel.add(txt_cust_extra_amt);

		JLabel lbl_cust_total = new JLabel("Total");
		lbl_cust_total.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_cust_total.setBounds(12, 348, 70, 15);
		panel.add(lbl_cust_total);

		JLabel lbl_cust_total_value = new JLabel("345.00");
		lbl_cust_total_value.setForeground(Color.BLUE);
		lbl_cust_total_value.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lbl_cust_total_value.setBounds(180, 348, 180, 15);
		panel.add(lbl_cust_total_value);

		txt_cust_driver_advance = new JTextField();
		txt_cust_driver_advance.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_cust_driver_advance.setColumns(10);
		txt_cust_driver_advance.setBounds(149, 375, 211, 29);
		panel.add(txt_cust_driver_advance);

		JLabel lbl_cust_driver_advance = new JLabel("Driver Advance");
		lbl_cust_driver_advance.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_cust_driver_advance.setBounds(12, 382, 130, 15);
		panel.add(lbl_cust_driver_advance);

		JLabel lbl_cust_office_advance = new JLabel("Office Advance");
		lbl_cust_office_advance.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_cust_office_advance.setBounds(12, 419, 130, 15);
		panel.add(lbl_cust_office_advance);

		txt_cust_office_advance = new JTextField();
		txt_cust_office_advance.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_cust_office_advance.setColumns(10);
		txt_cust_office_advance.setBounds(149, 411, 211, 29);
		panel.add(txt_cust_office_advance);

		JLabel lbl_cust_comments = new JLabel("Comments");
		lbl_cust_comments.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_cust_comments.setBounds(12, 460, 119, 15);
		panel.add(lbl_cust_comments);

		JLabel lbl_cust_slab_amt = new JLabel("Slab Amount");
		lbl_cust_slab_amt.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_cust_slab_amt.setBounds(12, 145, 105, 15);
		panel.add(lbl_cust_slab_amt);

		txt_cust_slab_amt = new JTextField();
		txt_cust_slab_amt.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_cust_slab_amt.setColumns(10);
		txt_cust_slab_amt.setBounds(149, 138, 211, 27);
		panel.add(txt_cust_slab_amt);

		JLabel lbl_cust_bata = new JLabel("Bata");
		lbl_cust_bata.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_cust_bata.setBounds(12, 237, 70, 15);
		panel.add(lbl_cust_bata);

		txt_cust_bata = new JTextField();
		txt_cust_bata.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_cust_bata.setColumns(10);
		txt_cust_bata.setBounds(149, 229, 211, 29);
		panel.add(txt_cust_bata);

		JLabel lbl_cust_check_post = new JLabel("Check Post");
		lbl_cust_check_post.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_cust_check_post.setBounds(12, 274, 119, 15);
		panel.add(lbl_cust_check_post);

		txt_cust_check_post = new JTextField();
		txt_cust_check_post.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_cust_check_post.setColumns(10);
		txt_cust_check_post.setBounds(149, 266, 211, 29);
		panel.add(txt_cust_check_post);

		JLabel lbl_cust_parking_toll = new JLabel("Parking Toll");
		lbl_cust_parking_toll.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_cust_parking_toll.setBounds(12, 315, 119, 15);
		panel.add(lbl_cust_parking_toll);

		txt_cust_parking_toll = new JTextField();
		txt_cust_parking_toll.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_cust_parking_toll.setColumns(10);
		txt_cust_parking_toll.setBounds(149, 307, 211, 29);
		panel.add(txt_cust_parking_toll);

		JComboBox<String> cmb_cust_slab = new JComboBox<String>();
		cmb_cust_slab.setBounds(149, 102, 211, 24);
		panel.add(cmb_cust_slab);

		JLabel lbl_cust_extra_usage = new JLabel("Extra: 20Km , 5Hrs");
		lbl_cust_extra_usage.setBounds(149, 174, 216, 15);
		panel.add(lbl_cust_extra_usage);

		JLabel lbl_cust_slab = new JLabel("Slab (Km/Hr)");
		lbl_cust_slab.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_cust_slab.setBounds(12, 107, 105, 15);
		panel.add(lbl_cust_slab);

		txt_cust_comments = new JTextField();
		txt_cust_comments.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_cust_comments.setBounds(149, 452, 211, 29);
		panel.add(txt_cust_comments);
		txt_cust_comments.setColumns(10);

		JLabel lbl_cust_rs = new JLabel("Rs");
		lbl_cust_rs.setForeground(Color.BLUE);
		lbl_cust_rs.setFont(new Font("Dialog", Font.BOLD, 15));
		lbl_cust_rs.setBounds(149, 348, 26, 15);
		panel.add(lbl_cust_rs);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.window);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBounds(460, 637, 445, 50);
		add(panel_3);
		panel_3.setLayout(null);

		JButton btn_save_trip = new JButton("Save Trip");
		btn_save_trip.setBounds(36, 12, 117, 25);
		panel_3.add(btn_save_trip);

		JButton btn_clear_from = new JButton("Clear Form");
		btn_clear_from.setBounds(165, 12, 117, 25);
		panel_3.add(btn_clear_from);

		JButton btn_close_form = new JButton("Close Form");
		btn_close_form.setBounds(294, 12, 117, 25);
		panel_3.add(btn_close_form);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.window);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(51, 51, 51), 1, true), "Travels", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(760, 130, 377, 495);
		add(panel_1);

		JLabel lbl_trvl_name = new JLabel("Name");
		lbl_trvl_name.setBackground(UIManager.getColor("Checkbox.select"));
		lbl_trvl_name.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_trvl_name.setBounds(12, 37, 119, 15);
		panel_1.add(lbl_trvl_name);

		txt_trvl_name = new JTextField();
		txt_trvl_name.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_trvl_name.setColumns(10);
		txt_trvl_name.setBounds(149, 29, 211, 29);
		panel_1.add(txt_trvl_name);

		JLabel label_9 = new JLabel("Km");
		label_9.setFont(new Font("Dialog", Font.BOLD, 14));
		label_9.setBounds(149, 72, 33, 15);
		panel_1.add(label_9);

		JSpinner spn_trvl_usage_km = new JSpinner();
		spn_trvl_usage_km.setBounds(175, 65, 90, 29);
		panel_1.add(spn_trvl_usage_km);

		JLabel lbl_trvl_usage = new JLabel("Usage");
		lbl_trvl_usage.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_trvl_usage.setBounds(12, 72, 70, 15);
		panel_1.add(lbl_trvl_usage);

		JLabel label_19 = new JLabel("Hrs");
		label_19.setFont(new Font("Dialog", Font.BOLD, 14));
		label_19.setBounds(271, 70, 33, 15);
		panel_1.add(label_19);

		JSpinner spn_trvl_usage_hrs = new JSpinner();
		spn_trvl_usage_hrs.setBounds(301, 65, 57, 29);
		panel_1.add(spn_trvl_usage_hrs);

		JLabel lbl_trvl_extra_amt = new JLabel("Extra Amount");
		lbl_trvl_extra_amt.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_trvl_extra_amt.setBounds(12, 200, 119, 15);
		panel_1.add(lbl_trvl_extra_amt);

		txt_trvl_extra_amt = new JTextField();
		txt_trvl_extra_amt.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_trvl_extra_amt.setColumns(10);
		txt_trvl_extra_amt.setBounds(149, 193, 211, 29);
		panel_1.add(txt_trvl_extra_amt);

		JLabel lbl_trvl_total = new JLabel("Total");
		lbl_trvl_total.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_trvl_total.setBounds(12, 348, 70, 15);
		panel_1.add(lbl_trvl_total);

		JLabel lbl_trvl_total_value = new JLabel("345.00");
		lbl_trvl_total_value.setForeground(Color.BLUE);
		lbl_trvl_total_value.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		lbl_trvl_total_value.setBounds(180, 348, 180, 15);
		panel_1.add(lbl_trvl_total_value);

		txt_trvl_driver_advance = new JTextField();
		txt_trvl_driver_advance.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_trvl_driver_advance.setColumns(10);
		txt_trvl_driver_advance.setBounds(149, 375, 211, 29);
		panel_1.add(txt_trvl_driver_advance);

		JLabel lbl_trvl_driver_advance = new JLabel("Driver Advance");
		lbl_trvl_driver_advance.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_trvl_driver_advance.setBounds(12, 382, 130, 15);
		panel_1.add(lbl_trvl_driver_advance);

		JLabel lbl_trvl_office_advance = new JLabel("Office Advance");
		lbl_trvl_office_advance.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_trvl_office_advance.setBounds(12, 419, 130, 15);
		panel_1.add(lbl_trvl_office_advance);

		txt_trvl_office_advance = new JTextField();
		txt_trvl_office_advance.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_trvl_office_advance.setColumns(10);
		txt_trvl_office_advance.setBounds(149, 411, 211, 29);
		panel_1.add(txt_trvl_office_advance);

		JLabel lbl_trvl_comments = new JLabel("Comments");
		lbl_trvl_comments.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_trvl_comments.setBounds(12, 460, 119, 15);
		panel_1.add(lbl_trvl_comments);

		JLabel lbl_trvl_slab_amt = new JLabel("Slab Amount");
		lbl_trvl_slab_amt.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_trvl_slab_amt.setBounds(12, 145, 105, 15);
		panel_1.add(lbl_trvl_slab_amt);

		txt_trvl_slab_amt = new JTextField();
		txt_trvl_slab_amt.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_trvl_slab_amt.setColumns(10);
		txt_trvl_slab_amt.setBounds(149, 138, 211, 27);
		panel_1.add(txt_trvl_slab_amt);

		JLabel lbl_trvl_bata = new JLabel("Bata");
		lbl_trvl_bata.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_trvl_bata.setBounds(12, 237, 70, 15);
		panel_1.add(lbl_trvl_bata);

		txt_trvl_bata = new JTextField();
		txt_trvl_bata.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_trvl_bata.setColumns(10);
		txt_trvl_bata.setBounds(149, 229, 211, 29);
		panel_1.add(txt_trvl_bata);

		JLabel lbl_trvl_check_post = new JLabel("Check Post");
		lbl_trvl_check_post.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_trvl_check_post.setBounds(12, 274, 119, 15);
		panel_1.add(lbl_trvl_check_post);

		txt_trvl_check_post = new JTextField();
		txt_trvl_check_post.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_trvl_check_post.setColumns(10);
		txt_trvl_check_post.setBounds(149, 266, 211, 29);
		panel_1.add(txt_trvl_check_post);

		JLabel lbl_trvl_parking_toll = new JLabel("Parking Toll");
		lbl_trvl_parking_toll.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_trvl_parking_toll.setBounds(12, 315, 119, 15);
		panel_1.add(lbl_trvl_parking_toll);

		txt_trvl_parking_toll = new JTextField();
		txt_trvl_parking_toll.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_trvl_parking_toll.setColumns(10);
		txt_trvl_parking_toll.setBounds(149, 307, 211, 29);
		panel_1.add(txt_trvl_parking_toll);

		JComboBox<String> cmb_trvl_slab = new JComboBox<String>();
		cmb_trvl_slab.setBounds(149, 102, 211, 24);
		panel_1.add(cmb_trvl_slab);

		JLabel lbl_trvl_extra_usage = new JLabel("Extra: 20Km , 5Hrs");
		lbl_trvl_extra_usage.setBounds(149, 174, 216, 15);
		panel_1.add(lbl_trvl_extra_usage);

		JLabel lbl_trvl_slab = new JLabel("Slab (Km/Hr)");
		lbl_trvl_slab.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_trvl_slab.setBounds(12, 107, 105, 15);
		panel_1.add(lbl_trvl_slab);

		txt_trvl_comments = new JTextField();
		txt_trvl_comments.setFont(new Font("Dialog", Font.PLAIN, 17));
		txt_trvl_comments.setColumns(10);
		txt_trvl_comments.setBounds(149, 452, 211, 29);
		panel_1.add(txt_trvl_comments);

		JLabel lbl_trvl_rs = new JLabel("Rs");
		lbl_trvl_rs.setForeground(Color.BLUE);
		lbl_trvl_rs.setFont(new Font("Dialog", Font.BOLD, 15));
		lbl_trvl_rs.setBounds(149, 348, 26, 15);
		panel_1.add(lbl_trvl_rs);


	}
}
