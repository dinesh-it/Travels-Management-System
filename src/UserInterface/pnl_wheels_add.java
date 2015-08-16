package UserInterface;

import java.awt.Dimension;
import java.awt.TextField;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import DataBaseInterface.*;
import javax.swing.ListSelectionModel;


public class pnl_wheels_add extends JPanel implements TableModelListener {
	private JTextField txt_mobile_no, txt_customer_name, txt_vehicle_no_1, txt_vehicl_make, txt_vehicle_no_2, txt_vehicle_no_3, txt_date, txt_bill_no;
	private JTable tbl_particulars;
	private JPanel pnl_buttons;
	private DefaultTableModel mdl_particulars;
	private Object[] is_multiple ;

	private TableModelListener tml; 

	private static SessionFactory factory = new Configuration().configure().buildSessionFactory();;

	public pnl_wheels_add() {
		Toolkit tool=Toolkit.getDefaultToolkit();
		Dimension dim=tool.getScreenSize();
		setSize(dim.width,dim.height);
		setLayout(null);

		JPanel pnl_customer_details = new JPanel();
		pnl_customer_details.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Customer Details", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		pnl_customer_details.setBounds(86, 81, 1189, 128);
		add(pnl_customer_details);
		pnl_customer_details.setLayout(null);

		JLabel lbl_mobile_no = new JLabel("Mobile No");
		lbl_mobile_no.setBounds(118, 39, 80, 15);
		pnl_customer_details.add(lbl_mobile_no);

		txt_mobile_no = new JTextField();
		txt_mobile_no.setBounds(216, 35, 172, 24);
		pnl_customer_details.add(txt_mobile_no);
		txt_mobile_no.setColumns(10);

		JLabel lbl_customer_name = new JLabel("Customer Name");
		lbl_customer_name.setBounds(81, 70, 117, 24);
		pnl_customer_details.add(lbl_customer_name);

		txt_customer_name = new JTextField();
		txt_customer_name.setBounds(216, 71, 172, 24);
		pnl_customer_details.add(txt_customer_name);
		txt_customer_name.setColumns(10);

		JLabel lbl_vehicle_no = new JLabel("Vehicle No");
		lbl_vehicle_no.setBounds(528, 39, 80, 15);
		pnl_customer_details.add(lbl_vehicle_no);

		txt_vehicle_no_1 = new JTextField();
		txt_vehicle_no_1.setBounds(614, 35, 34, 24);
		pnl_customer_details.add(txt_vehicle_no_1);
		txt_vehicle_no_1.setColumns(10);

		JLabel lbl_vehicle_make = new JLabel("Vehicle Make");
		lbl_vehicle_make.setBounds(515, 75, 93, 15);
		pnl_customer_details.add(lbl_vehicle_make);

		txt_vehicl_make = new JTextField();
		txt_vehicl_make.setBounds(614, 71, 163, 23);
		pnl_customer_details.add(txt_vehicl_make);
		txt_vehicl_make.setColumns(10);

		txt_vehicle_no_2 = new JTextField();
		txt_vehicle_no_2.setBounds(658, 36, 34, 23);
		pnl_customer_details.add(txt_vehicle_no_2);
		txt_vehicle_no_2.setColumns(10);

		txt_vehicle_no_3 = new JTextField();
		txt_vehicle_no_3.setBounds(704, 36, 73, 23);
		pnl_customer_details.add(txt_vehicle_no_3);
		txt_vehicle_no_3.setColumns(10);

		JLabel lbl_date = new JLabel("Date");
		lbl_date.setBounds(908, 39, 41, 15);
		pnl_customer_details.add(lbl_date);

		txt_date = new JTextField();
		txt_date.setBounds(951, 35, 132, 24);
		pnl_customer_details.add(txt_date);
		txt_date.setColumns(10);

		JLabel lbl_bill_no = new JLabel("Bill No");
		lbl_bill_no.setBounds(905, 75, 44, 15);
		pnl_customer_details.add(lbl_bill_no);

		txt_bill_no = new JTextField();
		txt_bill_no.setBounds(951, 73, 132, 24);
		pnl_customer_details.add(txt_bill_no);
		txt_bill_no.setColumns(10);


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
		tbl_particulars_main.setBounds(270, 231, 804, 325);
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
		btn_clear_form.setBounds(259, 24, 117, 25);
		pnl_buttons.add(btn_clear_form);

		list_service_particulars();
		tbl_particulars.setModel(mdl_particulars);


		tbl_particulars.getModel().addTableModelListener(this);

	}

	public void list_service_particulars( ){

		Session session = factory.openSession();

		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			List trip_slab = session.createQuery("FROM ServiceParticulars").list();
			ArrayList<Boolean>  is_multiple_list = new ArrayList(); 
			for (Iterator iterator = trip_slab.iterator(); iterator.hasNext();){

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
			tx.commit();
			is_multiple =  is_multiple_list.toArray();

		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}



	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
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
					}
				}
				catch (NumberFormatException nex){
					JOptionPane.showMessageDialog(this, "Invalid input");
					if(column == 3)
						tbl_particulars.setValueAt(new Double(0), row, column);
					else if(column == 2)
						tbl_particulars.setValueAt(new Integer(1), row,column);
				}catch(Exception ex){
					JOptionPane.showMessageDialog(this, ex);
				}
			}
		}
	}
}
