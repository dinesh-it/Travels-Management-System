package UserInterface;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import javax.swing.JButton;

import DataBaseInterface.*;
import javax.swing.ListSelectionModel;
import java.awt.Font;

import Util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;


public class pnl_wheels_add extends JPanel implements TableModelListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txt_mobile_no, txt_customer_name, txt_vehicle_make, txt_date, txt_time;
	private JTextArea txt_comment;
	private JTable tbl_particulars;
	private JPanel pnl_buttons;
	private DefaultTableModel mdl_particulars;
	private Object[] is_multiple;
	private Handler dbh = Handler.getInstance();
	private double[] particulars_total;
	private JLabel lbl_total_value;
	private JFormattedTextField txt_vehicle_no;
	private Customer customer;
	private CustomerVehicle vehicle;
	private JButton btn_save_bill; 

	public pnl_wheels_add() {
		setSize(1362,715);
		setLayout(null);

		JPanel pnl_customer_details = new JPanel();
		pnl_customer_details.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Customer Details", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		pnl_customer_details.setBounds(85, 40, 1189, 128);
		add(pnl_customer_details);
		pnl_customer_details.setLayout(null);
		
		int now_epoch = Time.now();

		JLabel lbl_mobile_no = new JLabel("Mobile No");
		lbl_mobile_no.setBounds(499, 41, 80, 15);
		pnl_customer_details.add(lbl_mobile_no);

		txt_mobile_no = new JTextField();
		txt_mobile_no.setFont(new Font("Dialog", Font.BOLD, 15));
		txt_mobile_no.setBounds(597, 37, 172, 24);
		pnl_customer_details.add(txt_mobile_no);
		txt_mobile_no.setColumns(10);

		JLabel lbl_customer_name = new JLabel("Customer Name");
		lbl_customer_name.setFont(new Font("Dialog", Font.BOLD, 12));
		lbl_customer_name.setBounds(462, 72, 117, 24);
		pnl_customer_details.add(lbl_customer_name);

		txt_customer_name = new JTextField();
		txt_customer_name.setFont(new Font("Dialog", Font.BOLD, 15));
		txt_customer_name.setBounds(597, 73, 172, 24);
		pnl_customer_details.add(txt_customer_name);
		txt_customer_name.setColumns(10);

		JLabel lbl_vehicle_no = new JLabel("Vehicle No");
		lbl_vehicle_no.setBounds(107, 42, 80, 15);
		pnl_customer_details.add(lbl_vehicle_no);

		JLabel lbl_vehicle_make = new JLabel("Vehicle Make");
		lbl_vehicle_make.setBounds(94, 78, 93, 15);
		pnl_customer_details.add(lbl_vehicle_make);

		txt_vehicle_make = new JTextField();
		txt_vehicle_make.setFont(new Font("Dialog", Font.BOLD, 15));
		txt_vehicle_make.setBounds(193, 74, 163, 23);
		pnl_customer_details.add(txt_vehicle_make);
		txt_vehicle_make.setColumns(10);

		JLabel lbl_date = new JLabel("Date");
		lbl_date.setBounds(908, 39, 41, 15);
		pnl_customer_details.add(lbl_date);

		txt_date = new JTextField();
		txt_date.setFont(new Font("Dialog", Font.BOLD, 15));
		txt_date.setBounds(951, 35, 132, 24);
		pnl_customer_details.add(txt_date);
		txt_date.setText(Time.get_date(now_epoch));
		txt_date.setColumns(10);

		JLabel lbl_time = new JLabel("Time");
		lbl_time.setBounds(910, 75, 33, 15);
		pnl_customer_details.add(lbl_time);

		txt_time = new JTextField();
		txt_time.setFont(new Font("Dialog", Font.BOLD, 15));
		txt_time.setBounds(951, 73, 132, 24);
		pnl_customer_details.add(txt_time);
		txt_time.setColumns(10);
		txt_time.setText(Time.get_date(now_epoch, "hh:mm a"));

		txt_vehicle_no = new JFormattedTextField();
		txt_vehicle_no.setText("KA");
		txt_vehicle_no.setFont(new Font("Dialog", Font.BOLD, 15));
		txt_vehicle_no.setBounds(193, 39, 163, 24);
		pnl_customer_details.add(txt_vehicle_no);


		tbl_particulars = new JTable(){

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) { 

				if(column == 0 || column == 1 || column == 4){
					return false; 	
				} else if (column == 2 && is_multiple[row].toString() == "false" ) {
					return false;
				}

				return true;
			}
		};
		tbl_particulars.setRowHeight(32);
		//tbl_particulars.getColumnModel().getColumn(0).setPreferredWidth(100);
		tbl_particulars.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		mdl_particulars = new DefaultTableModel();

		String[] columns =  {
				"S.NO",
				"Particulars",
				"Quanity",
				"Amount",
				"Total"
		};
		mdl_particulars.setColumnIdentifiers(columns);

		JScrollPane tbl_particulars_main = new JScrollPane(tbl_particulars);
		tbl_particulars_main.setBounds(270, 180, 804, 278);
		add(tbl_particulars_main);

		pnl_buttons = new JPanel();
		pnl_buttons.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnl_buttons.setBounds(440, 568, 458, 76);
		add(pnl_buttons);
		pnl_buttons.setLayout(null);

		btn_save_bill = new JButton("Save Bill");

		btn_save_bill.setBounds(88, 24, 117, 25);
		pnl_buttons.add(btn_save_bill);

		JButton btn_clear_form = new JButton("Close Form");
		btn_clear_form.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.get_main_frame().setContentPane(MainFrame.pnl_home);
			}
		});
		btn_clear_form.setBounds(259, 24, 117, 25);
		pnl_buttons.add(btn_clear_form);

		dbh.update_tire();
		List<?> sp_list = dbh.list_service_particulars();

		particulars_total = new double[sp_list.size() + 1];

		ArrayList<Boolean>  is_multiple_list = new ArrayList<Boolean>(); 
		for (Iterator<?> iterator = sp_list.iterator(); iterator.hasNext();){

			ServiceParticulars sp = (ServiceParticulars) iterator.next();
			Object[] o = new Object[5];

			o[0] = sp.getId();
			o[1] = sp.getService_name();

			boolean is_multi = sp.isIs_multiple();
			if(is_multi){
				o[2] = new Integer(1);
			} else {
				o[2] = "NA";
			}

			o[3] ="";
			o[4] ="";

			is_multiple_list.add(is_multi);

			mdl_particulars.addRow(o);
		}

		is_multiple =  is_multiple_list.toArray();
		tbl_particulars.setModel(mdl_particulars);

		// Table Column formatting
		tbl_particulars.getModel().addTableModelListener(this);
		tbl_particulars.setFont(new Font("Dialog",Font.BOLD,15));

		tbl_particulars.getColumnModel().getColumn(0).setPreferredWidth(10);
		tbl_particulars.getColumnModel().getColumn(1).setPreferredWidth(300);
		tbl_particulars.getColumnModel().getColumn(2).setPreferredWidth(20);
		tbl_particulars.getColumnModel().getColumn(3).setPreferredWidth(40);
		tbl_particulars.getColumnModel().getColumn(3).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		tbl_particulars.getColumnModel().getColumn(4).setPreferredWidth(60);
		tbl_particulars.getColumnModel().getColumn(4).setCellRenderer(NumberRenderer.getCurrencyRenderer());

		JLabel lbl_total = new JLabel("Total (Rs)");
		lbl_total.setFont(new Font("Dialog", Font.BOLD, 17));
		lbl_total.setBounds(837, 477, 93, 15);
		add(lbl_total);

		lbl_total_value = new JLabel("0.0");
		lbl_total_value.setFont(new Font("Dialog", Font.BOLD, 17));
		lbl_total_value.setBounds(942, 477, 132, 15);
		add(lbl_total_value);


		tbl_particulars.getModel().addTableModelListener(this);

		JLabel lbl_comment = new JLabel("Remarks \nby technician");
		lbl_comment.setFont(new Font("Dialog", Font.BOLD, 12));
		lbl_comment.setBounds(270, 477, 172, 26);
		add(lbl_comment);

		txt_comment = new JTextArea();
		txt_comment.setBorder(new LineBorder(new Color(0, 0, 0)));
		txt_comment.setBounds(435, 476, 393, 49);
		add(txt_comment);

		txt_vehicle_no.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String vehicle_no = txt_vehicle_no.getText().toUpperCase().replace(" ", "");
				populate_fields(vehicle_no);
			}
		});

		btn_save_bill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save_bill();
			}
		});
	}

	private void save_bill(){

		// At-least one particular should be entered to proceed bill saving
		double total_amount = Double.parseDouble(lbl_total_value.getText());
		if(total_amount <= 0){
			alert("No bill entries to save");
			return;
		}

		tbl_particulars.getModel().setValueAt("1", 0, 0);
		try {
			String v_num, v_make, c_name, c_mobile, date, comment, time;
			int vehicle_id, customer_id = 0;
			v_num = txt_vehicle_no.getText().replace(" ", "");
			v_make = txt_vehicle_make.getText();
			c_name = txt_customer_name.getText();
			c_mobile = txt_mobile_no.getText();
			date = txt_date.getText();
			comment = txt_comment.getText();
			time = txt_time.getText();

			// Validation
			if(!this.validate_details()) return;

			String bill_summary;

			bill_summary = "\n      Name: " + c_name + "                              Mobile: " + c_mobile + "\n" ;
			bill_summary += "      Vehicle: " + v_num + " " + v_make + "       Date Time: " + date + " " + time + "\n\n";
			String table_data[][] = this.getTableData(tbl_particulars);
			// For each service entered
			for(String row[] : table_data){

				// Consider only entered rows
				if(row[3] != null && ! row[3].equals("")){
					String service_name = row[1];
					int quantity = 1;
					if(!row[2].equals("NA")){ 
						quantity = Integer.parseInt(row[2]); 
					}
					double amount = Double.parseDouble(row[3]);
					bill_summary += "       " + service_name + "               " + quantity + " x " + amount + "       = " + row[4] + "\n";
				}
			}

			bill_summary += "\n\n " + comment + "\n";
			bill_summary += "\n\n                        Total = " + total_amount + " Rs\n\n";

			int save_confirm = JOptionPane.showConfirmDialog(this, bill_summary);

			// Stop save if user not click the yes option
			if(save_confirm != JOptionPane.YES_OPTION){
				return;
			}

			// DB Operations
			int date_epoch = Time.get_epoch(date + " " + time, "dd/MM/yyyy hh:mm a");
			if(vehicle != null){
				vehicle_id = vehicle.getId();
				if(customer != null){

					// customer for a vehicle is changed
					if(!customer.getName().equalsIgnoreCase(c_name) && !customer.getMobile().equalsIgnoreCase(c_mobile)){
						customer = null;
						customer_id = dbh.add_customer(c_name, c_mobile, " ", " ", 1, date_epoch);
						vehicle.setCustomer_id(customer_id);
						vehicle.setVehicle_make(v_make);
						dbh.update(vehicle);
					}
					// Customer details are changed
					else if(!customer.getName().equalsIgnoreCase(c_name) || !customer.getMobile().equalsIgnoreCase(c_mobile)){
						customer_id = customer.getId();
						customer.setName(c_name);
						customer.setMobile(c_mobile);
						dbh.update(customer);
					}

					// Vehicle make alone changed
					if(vehicle.getVehicle_make().equalsIgnoreCase(v_make)){
						vehicle.setVehicle_make(v_make);
						dbh.update(vehicle);
					}
				}
				else{
					customer_id = dbh.add_customer(c_name, c_mobile, " ", " ", 1, date_epoch);
					vehicle.setCustomer_id(customer_id);
					dbh.update(vehicle);
				}
			}
			else{
				// Create new customer and vehicle
				customer_id = dbh.add_customer(c_name, c_mobile, " ", " ", 1, date_epoch);
				vehicle_id = dbh.add_customer_vehicle(customer_id, v_num, v_make, 1, date_epoch);
			}
			
			int bill_id = dbh.add_service_bill(vehicle_id, comment, total_amount, 1, date_epoch);
			ServiceBill service_bill = new ServiceBill(vehicle_id, comment, total_amount, 1, date_epoch);
			service_bill.setId(bill_id);

			if(customer_id == 0 && customer != null){
				customer_id = customer.getId();
			}

			boolean sms_remiander = false;
			// For each service entered
			for(String row[] : table_data){

				// Consider only entered rows
				if(row[3] != null && ! row[3].equals("")){
					int service_id = Integer.parseInt(row[0]);
					ServiceParticulars service_particular = dbh.get_service_particular(service_id);
					int quantity = 1;
					if(!row[2].equals("NA")){ 
						quantity = Integer.parseInt(row[2]); 
					}
					double amount = Double.parseDouble(row[3]);
					dbh.add_service_detail(service_id, bill_id, quantity, amount);

					// set SMS flag if free service available
					if(service_particular.isIs_free_service()){
						sms_remiander = true;
					}
				}
			}

			// Create SMS queue if flag set
			if(sms_remiander){
				// 1 day * 29 days
				service_bill.setFree_checkup_date(date_epoch + (86000 * 29));
				dbh.update(service_bill);

				String message = this.process_template(bill_id, customer_id, vehicle_id);
				dbh.add_sms_queue(bill_id, c_mobile, message, 1, Time.now());
			}

			// Wheel bill print preview and print 
			wheel_bill_print bill_print = new wheel_bill_print(bill_id);
			bill_print.setVisible(true);

			MainFrame.get_main_frame().setContentPane(MainFrame.pnl_home);
		}
		catch(Exception e){
			Logger.log.severe("!!! Service BILL SAVE FAILED !!!");
			Logger.log.severe(e.getMessage());
			e.printStackTrace();
			alert("Sorry! Something went wrong, Bill not saved properly! \n Call developer for assist!");
		}
	}

	private String process_template(int bill_id,int customer_id,int vehicle_id){
		Map<String, Integer> id_map = new TreeMap<String, Integer>();
		id_map.put("Customer", customer_id);
		id_map.put("CustomerVehicle", vehicle_id);
		id_map.put("ServiceBill", bill_id);

		String message = dbh.get_sms_template();
		List<TemplateParameter> params = dbh.get_template_params_list();

		for(TemplateParameter param : params){
			String name = param.getName();
			String fields[] = param.getValue_ref().split("\\.");
			name = "\\[" + name + "\\]";
			String value = dbh.get_value(fields[0], fields[1], id_map.get(fields[0]));

			// convert epoch values to date
			if(name.matches(".*[Dd]ate.*")){
				try{
					value = Time.get_date(Integer.parseInt(value));
				}
				catch(Exception e){
					value = "Unknown";
				}
			}

			message = message.replaceAll(name, value);
		}

		return message;
	}

	private boolean validate_details() {

		String v_num, v_make, c_name, c_mobile, date, time;
		v_num = txt_vehicle_no.getText().replace(" ", "");
		v_make = txt_vehicle_make.getText();
		c_name = txt_customer_name.getText();
		c_mobile = txt_mobile_no.getText();
		date = txt_date.getText();
		time = txt_time.getText();
		
		if(!v_num.matches(Formatter.VEHICLE_PATTERN)){
			alert("Vehicle number is invalid, Please correct it");
			txt_vehicle_no.requestFocus();
			return false;
		}
		if(!v_make.matches("(\\w\\s?)+")){
			alert("Vehicle make is invalid, Please correct it");
			txt_vehicle_make.requestFocus();
			return false;
		}
		if(!c_name.matches("(\\w\\s?)+")){
			alert("Customer name is invalid, Please correct it");
			txt_customer_name.requestFocus();
			return false;
		}
		if(!c_mobile.matches(Formatter.MOBILE_NUMBER_PATTERN)){
			alert("Mobile number is invalid, Please correct it");
			txt_mobile_no.requestFocus();
			return false;
		}
		if(!date.matches(Formatter.DATE_PATTERN)){
			alert("Please enter a valid date in dd/mm/yyyy format");
			txt_date.requestFocus();
			return false;
		}
		
		if(!time.matches(Formatter.TIME_PATTERN)){
			alert("Please enter a valid time in hh:mm am/pm format");
			txt_time.requestFocus();
			return false;
		}

		return true;
	}

	private void populate_fields(String vehicle_no){
		vehicle = dbh.getVehicle(vehicle_no);
		if(vehicle != null){
			txt_vehicle_make.setText(vehicle.getVehicle_make());
			customer = dbh.getCustomer(vehicle.getCustomer_id());
			txt_customer_name.setText(customer.getName());
			txt_mobile_no.setText(customer.getMobile());
		}
		else{
			txt_vehicle_make.setText("");
			txt_customer_name.setText("");
			txt_mobile_no.setText("");
		}
		txt_vehicle_no.setText(Formatter.getFormattedVehicleNo(vehicle_no));
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		if(e.getType() == TableModelEvent.UPDATE) {
			int row = e.getFirstRow();
			int column = e.getColumn();
			Pattern pattern = Pattern.compile("(^\\d+\\.?\\d*$)");

			// validation for accept only numbers
			String current_cell_str = tbl_particulars.getValueAt(row, column).toString();					
			if((! current_cell_str.trim().isEmpty()) && (!pattern.matcher(current_cell_str).find())){
				if(column == 3 )
					tbl_particulars.setValueAt("", row, column);
				else if(column == 2)
					tbl_particulars.setValueAt(new Integer(1), row, column);
			}

			// Automatic total calculation
			if (column == 3 || column == 2) {

				try{
					// parsing null value will throw NumberFormatException
					// To avoid that exception amount stored in string 
					String str_amount = tbl_particulars.getValueAt(row, 3).toString();

					if(! str_amount.trim().isEmpty()){

						double total = 0;
						double amount = Double.parseDouble(tbl_particulars.getValueAt(row, 3).toString());

						if(is_multiple[row].toString() == "false") {
							total = amount;
						} else { 
							int quantity = Integer.parseInt(tbl_particulars.getValueAt(row, 2).toString());
							total = amount * quantity;
						}

						tbl_particulars.setValueAt(new Double(total), row, 4);

						// sum value for particular row
						particulars_total[row] = total;
					}
				}
				catch (NumberFormatException nex){
					alert("Invalid input");
					if(column == 3)
						tbl_particulars.setValueAt(new Double(0), row, column);
					else if(column == 2)
						tbl_particulars.setValueAt(new Integer(1), row,column);
				}catch(Exception ex){
					alert(ex.getMessage());
				}
			}
		}

		// Calculate total and set it on total label
		double total = 0;
		for (double amt : particulars_total) total += amt;
		lbl_total_value.setText(total + "");
	}

	public String[][] getTableData (JTable table) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
		String[][] tableData = new String[nRow][nCol];
		for (int i = 0 ; i < nRow ; i++)
			for (int j = 0 ; j < nCol ; j++)
				tableData[i][j] = dtm.getValueAt(i,j).toString();
		return tableData;
	}

	public void alert(String msg){
		JOptionPane.showMessageDialog(this, msg);
	}

	public void setFieldFocus(){
		txt_vehicle_no.requestFocusInWindow();
	}
}
