package UserInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import DataBaseInterface.Customer;
import DataBaseInterface.CustomerVehicle;
import DataBaseInterface.Handler;
import DataBaseInterface.ServiceBill;
import DataBaseInterface.ServiceDetail;
import DataBaseInterface.ServiceParticulars;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import org.jdesktop.swingx.JXDatePicker;
import Util.*;

public class pnl_wheels_view extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable tbl_service_details;

	private TableRowSorter<TableModel> rowSorter ;

	private JTextField txt_search_word,txt_search_text;
	private JLabel lbl_total;
	private JButton btn_toggle_total;
	private DefaultTableModel mdl_service_details;
	private JComboBox<String> cmb_search_catagory;

	private JXDatePicker jdp_from,jdp_to;

	private Handler dbh = Handler.getInstance();
	int window_width,window_height;

	private ArrayList<Integer> vehicle_id = new ArrayList<Integer>();
	private ArrayList<Integer> fcd_completed_date = new ArrayList<Integer>();
	private ArrayList<Integer> fcd_completed_due_date = new ArrayList<Integer>();

	public pnl_wheels_view(int width, int height ) {
		setBackground(Color.WHITE);

		setSize(width,height - 30);

		window_width = this.getWidth();
		window_height = this.getHeight();

		setLayout(null);

		tbl_service_details =  new JTable(){

			private static final long serialVersionUID = 1L;

			protected JTableHeader createDefaultTableHeader() {
				return new JTableHeader(columnModel) {

					private static final long serialVersionUID = 1L;

					public String getToolTipText(MouseEvent e) {
						String tip = null;
						java.awt.Point p = e.getPoint();
						int index = columnModel.getColumnIndexAtX(p.x);

						if(index == 8){
							tip = "Enable checkbox if Free checkup is Completed";
						}

						return tip;

					}
				};
			}

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component comp = super.prepareRenderer(renderer, row, col);
				int index = (int) getModel().getValueAt(row, 0);
				int due_date = fcd_completed_due_date.get(index-1);
				if (col == 8) {
					if (due_date == 0) {
						comp.setBackground(Color.LIGHT_GRAY);
					}
				} 
				return comp;
			}
		};

		tbl_service_details.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tbl_service_details.setRowSelectionAllowed(false);
		mdl_service_details = new DefaultTableModel(){

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int col) {

				if (col == 9) 
					return true;
				else if( col == 8 && fcd_completed_due_date .get((int)getValueAt(row, 0) -1 ) != 0) 
					return true;
				else 
					return false; 
			}

			public Class<?> getColumnClass(int c) {

				if(this.getRowCount() >= 1) {
					return getValueAt(0, c).getClass();
				} else {
					return new String().getClass();
				}
			}
		};

		String[] columns =  {
				"S.NO",
				"Customer Name",
				"Mobile No",
				"Vehicle No",
				"Bill Date",
				"Remarks",
				"Particulars",
				"Total",
				"FCD",
				"Info"
		};

		mdl_service_details.setColumnIdentifiers(columns);

		load_all_sbd();

		tbl_service_details.setModel(mdl_service_details);
		tbl_service_details.setFont(new Font("Arial", Font.PLAIN, 14));
		tbl_service_details.setForeground(Color.DARK_GRAY);
		tbl_service_details.setGridColor(Color.BLUE);
		tbl_service_details.setBackground(Color.WHITE);
		tbl_service_details.setRowMargin(5);
		tbl_service_details.setRowHeight(50);

		tbl_service_details.getColumn("Particulars").setCellRenderer(new SubTableRenderer());
		tbl_service_details.getColumn("Customer Name").setCellRenderer(new WrapCellRenderer());
		tbl_service_details.getColumn("Mobile No").setCellRenderer(new WrapCellRenderer());
		tbl_service_details.getColumn("Vehicle No").setCellRenderer(new WrapCellRenderer());
		tbl_service_details.getColumn("Bill Date").setCellRenderer(new WrapCellRenderer());
		tbl_service_details.getColumn("Remarks").setCellRenderer(new WrapCellRenderer());


		Action update_fcd  = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

				int row = Integer.valueOf( e.getActionCommand() );

				int completed_date = fcd_completed_date.get(row);

				int due_date = fcd_completed_due_date.get(row); 

				if (due_date > 0 ) {
					String msg="";
					if (completed_date > 0 ) {
						msg = "<html><Table>"								
						         + "<tr>"
						            + "<td><font color='green'> FreeCheckup Status </font></td>"
						            + "<td> : </td>"
						            + "<td><font color='green'> Completed </font></td>"
						         + "</tr>"
						         + "<tr>"
						            + "<td><font color='green'> Completed Date </font></td>"
						            + "<td> : </td>"
						            + "<td><font color='green'>" + Time.get_date(completed_date,"dd/MM/yyyy hh:mm:ss a") + " </font></td>"
						         + "</tr>"						        
						      +"</Table></html>";
						
					}else if (completed_date == 0 ) {
						msg = "<html><Table>"
						         + "<tr>"
						            + "<td><font color='red'> FreeCheckup Status </font></td>"
						            + "<td> : </td>"
						            + "<td><font color='red'> Not Completed </font></td>"
						         + "</tr>"
						         + "<tr>"
						            + "<td><font color='red'> Due Date </font></td>"
						            + "<td> : </td>"
						            + "<td><font color='red'>" + Time.get_date(due_date) + " </font></td>"
						         + "</tr>"
						      +"</Table></html>";
						
					}
					JOptionPane.showMessageDialog(tbl_service_details, msg);
				} else {
					JOptionPane.showMessageDialog(tbl_service_details, " This bill don't have any service which has Free Checkup");
				}

			}
		};

		@SuppressWarnings("unused")
		ButtonColumn buttonColumn = new ButtonColumn(tbl_service_details, update_fcd, 9);


		tbl_service_details.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				
				int col = tbl_service_details.columnAtPoint(evt.getPoint());
				int row = tbl_service_details.rowAtPoint(evt.getPoint());

				ServiceBill obj_service_bill=null;

				int index = (int) tbl_service_details.getValueAt(row, 0) - 1;

				boolean is_fcd_update = (col == 8  && fcd_completed_due_date.get(index) > 0) ? true : false;

				if( col== 0 || is_fcd_update ) {
					String date_str = tbl_service_details.getValueAt(row, 4).toString();

					obj_service_bill = dbh.get_service_bill(vehicle_id.get(index), date_str);
					
				}
				
				if((col == 0 || is_fcd_update) &&  obj_service_bill == null) {

					JOptionPane.showMessageDialog(tbl_service_details, "Error while getting service bill object");
					Logger.log.severe("Error while getting service bill object for service bill id " + index);
					
				}else if(col == 0){

					wheel_bill_print obj_wbp = new wheel_bill_print(obj_service_bill.getId());
					
					obj_wbp.setVisible(true);

				} else if(is_fcd_update) {

					int epoch = (boolean) tbl_service_details.getValueAt(row, col) ? Time.now() : 0;

					fcd_completed_date.set(index, epoch);

					obj_service_bill.setFree_checkup_completed(epoch);
					dbh.update(obj_service_bill);

				}
			}

		});

		JTableHeader  obj_jtable_header = tbl_service_details.getTableHeader();
		obj_jtable_header.setBackground(Color.BLUE);
		obj_jtable_header.setForeground(Color.WHITE);
		obj_jtable_header.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));

		JScrollPane jsp_service_details =new JScrollPane(tbl_service_details);
		jsp_service_details.setForeground(new Color(102, 153, 255));
		jsp_service_details.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(204, 51, 102)));
		jsp_service_details.setBackground(new Color(204, 102, 153));
		jsp_service_details.setBounds(20, 120, window_width - 40 , window_height - 220);
		add(jsp_service_details);

		int tbl_width = jsp_service_details.getWidth();

		tbl_service_details.getColumn("S.NO").setMaxWidth((tbl_width/100)*4);
		tbl_service_details.getColumn("Customer Name").setPreferredWidth((tbl_width/100)*9);
		tbl_service_details.getColumn("Mobile No").setWidth((tbl_width/100)*9);
		tbl_service_details.getColumn("Vehicle No").setWidth((tbl_width/100)*7);
		tbl_service_details.getColumn("Bill Date").setWidth((tbl_width/100)*10);
		tbl_service_details.getColumn("Remarks").setPreferredWidth((tbl_width/100)*8);
		tbl_service_details.getColumn("Total").setMaxWidth((tbl_width/100)*8);
		tbl_service_details.getColumn("FCD").setMaxWidth((tbl_width/100)*10);
		tbl_service_details.getColumn("Info").setMaxWidth((tbl_width/100) * 8);
		tbl_service_details.getColumn("Particulars").setPreferredWidth((tbl_width/100)*28);

		rowSorter = new TableRowSorter<>(tbl_service_details.getModel());
		tbl_service_details.setRowSorter(rowSorter);

		JPanel pnl_search = new JPanel();
		pnl_search.setBackground(new Color(204, 51, 153));
		pnl_search.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(204, 51, 153)));
		pnl_search.setBounds(20, 95, tbl_width, 30);
		add(pnl_search);
		pnl_search.setLayout(new BoxLayout(pnl_search, BoxLayout.X_AXIS));

		JLabel lbl_search_word = new JLabel("Search Text        ");
		lbl_search_word.setBackground(new Color(204, 153, 0));
		lbl_search_word.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnl_search.add(lbl_search_word);
		lbl_search_word.setForeground(new Color(255, 255, 153));
		lbl_search_word.setFont(new Font("Bitstream Charter", Font.BOLD | Font.ITALIC, 18));

		txt_search_word = new JTextField();
		txt_search_word.setBackground(new Color(255, 255, 255));
		pnl_search.add(txt_search_word);
		txt_search_word.setColumns(10);
		txt_search_word.getDocument().addDocumentListener(new DocumentListener(){
			@Override
			public void insertUpdate(DocumentEvent e) {

				String text = txt_search_word.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
				calculate_total();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {

				String text = txt_search_word.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
				calculate_total();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}

		});

		JPanel pnl_total = new JPanel();
		pnl_total.setBackground(Color.WHITE);
		pnl_total.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(204, 51, 153)));
		pnl_total.setBounds(20, window_height - 105, tbl_width, 40);
		add(pnl_total);
		pnl_total.setLayout(new BoxLayout(pnl_total, BoxLayout.X_AXIS));

		pnl_total.add(Box.createHorizontalGlue());

		lbl_total = new JLabel("Total        ");
		lbl_total.setBackground(new Color(204, 153, 0));
		lbl_total.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnl_total.add(lbl_total);
		lbl_total.setForeground(Color.BLUE);
		lbl_total.setVisible(false);
		lbl_total.setFont(new Font("Bitstream Charter", Font.BOLD | Font.ITALIC, 18));
		
		btn_toggle_total = new JButton("Show Total");
		btn_toggle_total.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String btn_text = btn_toggle_total.getText();
				
				if(btn_text.equals("Show Total")) {
					lbl_total.setVisible(true);
					btn_toggle_total.setText("Hide Total");
				} else {
					lbl_total.setVisible(false);
					btn_toggle_total.setText("Show Total");
				}
			}
		});
		pnl_total.add(btn_toggle_total);	

		cmb_search_catagory	 = new JComboBox<String>();
		cmb_search_catagory.setBounds((window_width /2) - 245, 45, 225, 24);
		cmb_search_catagory.addItem("Vehicle Number");
		cmb_search_catagory.addItem("Customer Name");
		cmb_search_catagory.addItem("Date Period");
		cmb_search_catagory.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (cmb_search_catagory.getSelectedIndex() != 2) {
					load_all_sbd();
					calculate_total();
					jdp_from.setVisible(false);
					jdp_to.setVisible(false);
					txt_search_text.setVisible(true);
				} else {
					jdp_from.setVisible(true);
					jdp_to.setVisible(true);
					txt_search_text.setVisible(false);
				}
			}
		});
		add(cmb_search_catagory);


		jdp_from = new JXDatePicker();
		jdp_from.setDate(Calendar.getInstance().getTime());
		jdp_from.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		jdp_from.setBounds((window_width/2), 28, 225, 24);
		jdp_from.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				load_sbd_bw_date_range();
			}
		});
		add(jdp_from);
		jdp_from.setVisible(false);

		jdp_to = new JXDatePicker();
		jdp_to.setDate(Calendar.getInstance().getTime());
		jdp_to.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
		jdp_to.setBounds((window_width/2), 58, 225, 24);
		jdp_to.setVisible(false);
		jdp_to.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				jdp_from.getMonthView().setUpperBound(jdp_to.getDate());
				load_sbd_bw_date_range();
			}
		});
		add(jdp_to);

		txt_search_text = new JTextField();
		txt_search_text.setBorder(new LineBorder(new Color(0, 0, 0)));
		txt_search_text.setBounds((window_width/2), 45, 225, 24);
		add(txt_search_text);
		txt_search_text.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				update_filter();
				calculate_total();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				update_filter();
				calculate_total();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				update_filter();
				calculate_total();
			}
		});


		calculate_total();

		txt_search_word.setText(Time.get_current_date_time());

	}

	//This method used to load all service bill details 
	public void load_all_sbd () {
		List<Object[]> bill_details = dbh.get_bill_details();

		load_service_bill_details(bill_details);

	}

	//This method used to load service bill details between the date range
	public void load_sbd_bw_date_range () {
		List<Object[]> bill_details =dbh.get_bill_details_between_date_range(jdp_from.getDate().toString(), jdp_to.getDate().toString());
		load_service_bill_details(bill_details);
		calculate_total();
	}

	public void calculate_total () {

		String text  = "Total :-       ";
		int tbl_row_count = tbl_service_details.getRowCount();
		double total = 0;

		for(int i=0; i < tbl_row_count; i++ ) {
			if (tbl_service_details.getValueAt(i,7) != null)
				total = total + (double)tbl_service_details.getValueAt(i, 7);
		}

		text = text + total + "  ";
		lbl_total.setText(text);

	}

	public void load_service_bill_details(List<Object[]> bill_details){

		mdl_service_details.setNumRows(0);
		vehicle_id.clear();
		fcd_completed_date.clear();
		fcd_completed_due_date.clear();

		if(bill_details.size() == 0)
			return;

		int serial_number = 0, quantity, pre_bill_epoch = 0;

		String pre_customer_name = "", cur_customer_name = "", service_name ;

		Object[] row = new Object[10];

		ArrayList<String> service_particulars = new ArrayList<String>();

		//List<Object[]> bill_details = dbh.get_bill_details();

		for (Object[] object : bill_details){

			ServiceDetail      obj_sd 	= (ServiceDetail) 		object[0];
			ServiceBill        obj_sb 	= (ServiceBill)   		object[1];
			ServiceParticulars obj_sp 	= (ServiceParticulars) 	object[2];
			CustomerVehicle    obj_cveh = (CustomerVehicle)     object[3];
			Customer           obj_cus 	= (Customer) 			object[4];

			cur_customer_name = obj_cus.getName();

			boolean is_same_name = pre_customer_name.equals(cur_customer_name);

			if( is_same_name && pre_bill_epoch == obj_sb.getSb_created_epoch()) {

				service_name  = obj_sp.getService_name();
				quantity      = obj_sd.getQuantity();
				double amount = obj_sd.getAmount();

				service_particulars.add(service_name);
				service_particulars.add(quantity + " * " + amount + " = " +(quantity * amount));

			} else {

				//Don't execute for first row
				if (pre_customer_name != "") {

					row[6]  = get_string_array(service_particulars);
					service_particulars.clear();
					mdl_service_details.addRow(row);
					row = new Object[10];
				}

				row[0] = ++serial_number;
				row[1] = cur_customer_name;
				row[2] = obj_cus.getMobile();
				row[3] = obj_cveh.getVehicle_number() + "\n" + obj_cveh.getVehicle_make();
				vehicle_id.add(obj_sb.getVehicle_id());
				pre_bill_epoch = obj_sb.getSb_created_epoch();
				row[4] = Time.get_date(pre_bill_epoch, "dd/MM/yyyy hh:mm:ss a");
				row[5] = obj_sb.getComments();

				service_name  = obj_sp.getService_name();
				quantity      = obj_sd.getQuantity();
				double amount = obj_sd.getAmount();

				service_particulars.add(service_name);
				service_particulars.add(quantity + " * " + amount + " = " +(quantity * amount));

				row[7] = obj_sb.getTotal_amount();

				int fcd_value = obj_sb.getFree_checkup_completed();
				fcd_completed_due_date.add( obj_sb.getFree_checkup_date());

				row[8] = fcd_value > 0 ? true : false;

				fcd_completed_date.add(fcd_value);

				row[9] = "Info";

				pre_customer_name = cur_customer_name;
			}
		}

		//adding last record
		row[6]  = get_string_array(service_particulars);



		mdl_service_details.addRow(row);
	}

	//This method used to convert the ArrayList to String[][]
	public String[][] get_string_array(ArrayList< String> al ) {

		String[][] srt_array = new String[(al.size()/2)][2];

		int ind = 0;

		for (int i=0;i < al.size(); i++)
		{

			srt_array[ind][0]= al.get(i);

			i++;
			srt_array[ind][1]= al.get(i);

			if (i != 0 && i % 2 != 0)
				ind++;
		}

		return srt_array;

	}

	//This method is used to filter table by column
	protected void update_filter() {
		txt_search_word.setText("");
		Object selected = cmb_search_catagory.getSelectedItem();
		TableRowSorter<?> sorter = (TableRowSorter<?>) tbl_service_details.getRowSorter();
		String text = "(?i)" + txt_search_text.getText();
		if ("Nothing".equals(selected)) {
			sorter.setRowFilter(null);
		} else {
			int col = -1;
			if ("Customer Name".equals(selected)) {
				col = 1;
			} else if ("Vehicle Number".equals(selected)) {
				col = 3;
			}
			sorter.setRowFilter(RowFilter.regexFilter(text, col));

		}
	}

};

class  SubTableRenderer extends JTable implements TableCellRenderer { 


	private static final long serialVersionUID = 1L;
	//TableCellRenderer jTableCellRenderer = new TableCellRenderer() {
	/* These are necessary variables to store the row's height */

	private int tbl_row_height = -1;
	private int curr_height = -1;
	/* Magic Happens */
	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		/* If what we're displaying isn't an array of values we
return the normal renderer*/

		if(value ==  null || (!value.getClass().isArray())){
			return table.getDefaultRenderer( 
					value.getClass()).getTableCellRendererComponent(
							table, value, isSelected, hasFocus,row, column);
		}
		else{
			final Object[][] passed = (Object[][])value;
			/* We calculate the row's height to display data
			 * THis is not complete and has some bugs that
			 * will be analyzed in further articles */
			if(tbl_row_height == -1){
				tbl_row_height = table.getRowHeight();
			}
			if(curr_height != tbl_row_height){
				curr_height = (passed.length * passed.length + 20 ) + tbl_row_height;

				table.setRowHeight(row,curr_height);
			} 

			/* We create the table that will hold the multi value
			 *fields and that will be embedded in the main table */

			JTable tbl = new JTable(new AbstractTableModel() {

				private static final long serialVersionUID = 1L;

				public int getColumnCount() {
					return 2;
				}
				public int getRowCount() {
					return passed.length;
				}
				public Object getValueAt(int rowIndex, int columnIndex) {

					return passed[rowIndex][columnIndex];
				}
				public boolean isCellEditable(int row, int col){ return true; }
			});

			tbl.setShowGrid(false);

			return tbl;

		}
	}
}


class WrapCellRenderer extends JTextArea implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	public WrapCellRenderer() {
		setLineWrap(true);
		setWrapStyleWord(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object
			value, boolean isSelected, boolean hasFocus, int row, int column) {

		if(value != null)
			setText(value.toString()) ;


		setSize(table.getColumnModel().getColumn(column).getWidth(),
				getPreferredSize().height);

		if (table.getRowHeight(row) != getPreferredSize().height) {
			table.setRowHeight(row, getPreferredSize().height);
		}

		return this;
	}
} 



