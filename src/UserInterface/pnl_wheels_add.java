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
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
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
import java.text.ParseException;

import javax.swing.JFormattedTextField;


public class pnl_wheels_add extends JPanel implements TableModelListener {
	private JTextField txt_mobile_no, txt_customer_name, txt_vehicle_make, txt_date, txt_bill_no;
	private JTable tbl_particulars;
	private JPanel pnl_buttons;
	private DefaultTableModel mdl_particulars;
	private Object[] is_multiple ;
	private Handler dbh = Handler.getInstance();
	private double[] particulars_total;
	private JLabel lbl_total_value;
	private JFormattedTextField txt_vehicle_no;

	public pnl_wheels_add() {
		setSize(1362,715);
		setLayout(null);

		JPanel pnl_customer_details = new JPanel();
		pnl_customer_details.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Customer Details", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		pnl_customer_details.setBounds(86, 81, 1189, 128);
		add(pnl_customer_details);
		pnl_customer_details.setLayout(null);

		JLabel lbl_mobile_no = new JLabel("Mobile No");
		lbl_mobile_no.setBounds(499, 41, 80, 15);
		pnl_customer_details.add(lbl_mobile_no);

		txt_mobile_no = new JTextField();
		txt_mobile_no.setFont(new Font("Dialog", Font.BOLD, 15));
		txt_mobile_no.setBounds(597, 37, 172, 24);
		pnl_customer_details.add(txt_mobile_no);
		txt_mobile_no.setColumns(10);

		JLabel lbl_customer_name = new JLabel("Customer Name");
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
		txt_date.setText(Time.get_date(Time.now()));
		txt_date.setColumns(10);

		JLabel lbl_bill_no = new JLabel("Bill No");
		lbl_bill_no.setBounds(905, 75, 44, 15);
		pnl_customer_details.add(lbl_bill_no);

		txt_bill_no = new JTextField();
		txt_bill_no.setFont(new Font("Dialog", Font.BOLD, 15));
		txt_bill_no.setBounds(951, 73, 132, 24);
		pnl_customer_details.add(txt_bill_no);
		txt_bill_no.setColumns(10);
		txt_bill_no.setText(dbh.get_next_bill_id() + "");
		
		txt_vehicle_no = new JFormattedTextField();
		txt_vehicle_no.setText("KA");
		txt_vehicle_no.setFont(new Font("Dialog", Font.BOLD, 15));
		txt_vehicle_no.setBounds(193, 39, 163, 24);
		pnl_customer_details.add(txt_vehicle_no);


		tbl_particulars = new JTable(){

			public boolean isCellEditable(int row, int column) { 

				if(column == 0 || column == 1){
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
		tbl_particulars_main.setBounds(270, 231, 804, 285);
		add(tbl_particulars_main);
		

		pnl_buttons = new JPanel();
		pnl_buttons.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnl_buttons.setBounds(440, 568, 458, 76);
		add(pnl_buttons);
		pnl_buttons.setLayout(null);

		JButton btn_save_bill = new JButton("Save Bill");
		
		btn_save_bill.setBounds(88, 24, 117, 25);
		pnl_buttons.add(btn_save_bill);

		JButton btn_clear_form = new JButton("Clear Form");
		btn_clear_form.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: refresh form
			}
		});
		btn_clear_form.setBounds(259, 24, 117, 25);
		pnl_buttons.add(btn_clear_form);

		List<?> sp_list = dbh.list_service_particulars();
		
		particulars_total = new double[sp_list.size() + 1];
		
		ArrayList<Boolean>  is_multiple_list = new ArrayList(); 
		for (Iterator iterator = sp_list.iterator(); iterator.hasNext();){

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
		lbl_total.setBounds(837, 528, 93, 15);
		add(lbl_total);
		
		lbl_total_value = new JLabel("0.0");
		lbl_total_value.setFont(new Font("Dialog", Font.BOLD, 17));
		lbl_total_value.setBounds(942, 528, 132, 15);
		add(lbl_total_value);


		tbl_particulars.getModel().addTableModelListener(this);
		
		txt_vehicle_no.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String vehicle_no = txt_vehicle_no.getText().toUpperCase();
				populate_fields(vehicle_no);
			}
		});
		
		btn_save_bill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// TODO: save wheel bill
				// 1. Create customer if not exist and get customer_id
				// 2. Create vehicle if not exist and get vehicle_id
				// 3. For each service entered
					// get service id
					// set SMS flag if needed
					// store Vehicle service record
				// 4. Create SMS queue if flag set
			}
		});
	}

	private void populate_fields(String vehicle_no){
		txt_vehicle_no.setText(vehicle_no);
		CustomerVehicle cv = dbh.getVehicle(vehicle_no);
		if(cv != null){
			txt_vehicle_make.setText(cv.getVehicle_make());
			Customer c = dbh.getCustomer(cv.getCustomer_id());
			txt_customer_name.setText(c.getName());
			txt_mobile_no.setText(c.getMobile());
		}
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
	
	public void alert(String msg){
		JOptionPane.showMessageDialog(this, msg);
	}
	
	public void setFieldFocus(){
		txt_vehicle_no.requestFocusInWindow();
	}
}
