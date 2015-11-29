package UserInterface;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

import java.awt.SystemColor;
import javax.swing.JScrollPane;
import DataBaseInterface.*;
import Util.*;
import javax.swing.UIManager;

public class wheel_bill_print extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable tbl_particulars;
	private JLabel lbl_name_value, lbl_mobile_value, lbl_vehicle_no_value, lbl_make_value, lbl_date_value, lbl_time_value, lbl_total_value, lbl_free_checkup_date;
	private JTextArea txt_rupees, txt_comments;
	private DefaultTableModel mdl_particulars;
	private Handler dbh;
	private JPanel bill_panel;

	// Settings values
	String address = "# 53, 14th \"C\" Cross, Geleyarabalaga, Mahalakshmipuram, Behind Nandini Theatre, Opp. Dr. Rajkumar Indoor Stadium, Bangalore - 86";

	public wheel_bill_print(int bill_id) {
		setTitle("Bill Preview");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 0, 559, 757);
		setResizable(false);
		setBackground(Color.WHITE);

		bill_panel = new JPanel();
		bill_panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		bill_panel.setBackground(Color.WHITE);
		bill_panel.setLayout(null);
		setContentPane(bill_panel);

		Insets text_area_margin = new Insets(0, 5, 0, 0);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 11, 175, 97);
		ImageIcon wheels_logo = getScaledImage("/resources/abhi wheels logo.jpg", lblNewLabel.getWidth(), lblNewLabel.getHeight());

		lblNewLabel.setIcon(wheels_logo);

		bill_panel.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(10, 155, 535, 7);
		bill_panel.add(separator);

		dbh = Handler.getInstance();
		mdl_particulars = new DefaultTableModel();

		String[] columns =  {
				"S.No.",
				"Particulars",
				"Qty",
				"Amount",
				"Sub Total"
		};

		mdl_particulars.setColumnIdentifiers(columns);

		List<Object[]> bill_details = dbh.get_single_bill_detail(bill_id);
		String c_name = null, c_mobile = null, v_num = null, v_make = null, date = null, time = null;
		String free_checkup_date = null, comments = null;
		double total_amt = 0.0;

		if(bill_details.isEmpty()){
			JOptionPane.showMessageDialog(null, "Bill not available");
			this.setVisible(false);
		}

		// Load all service particulars to the bill
		int count = 1;
		Map<Integer, Integer> service_row_map = new TreeMap<Integer, Integer>();
		List<ServiceParticulars> sp_list = dbh.list_service_particulars();
		for (ServiceParticulars sp : sp_list){
			String[] row = new String[5];
			row[0] = count + "";
			row[1] = sp.getService_name();
			row[2] = "";
			row[3] = "";
			row[4] = "";

			service_row_map.put(sp.getId(), count -1);
			mdl_particulars.addRow(row);
			count++;
		}

		for(Object[] obj : bill_details){

			ServiceDetail      obj_sd 	= (ServiceDetail)obj[0];
			ServiceBill        obj_sb 	= (ServiceBill)obj[1];
			CustomerVehicle    obj_cveh = (CustomerVehicle)obj[2];
			Customer           obj_cus 	= (Customer)obj[3];

			if(c_name == null){
				c_name = obj_cus.getName();
				c_mobile = obj_cus.getMobile();
				v_num = Formatter.getFormattedVehicleNo(obj_cveh.getVehicle_number());
				v_make = obj_cveh.getVehicle_make();
				System.out.println("CreatedEpoch " + obj_sb.getSb_created_epoch());
				date = Time.get_date(obj_sb.getSb_created_epoch());
				time = Time.get_date(obj_sb.getSb_created_epoch(),"hh:mm a");
				if(obj_sb.getFree_checkup_date() != 0){
					free_checkup_date = Time.get_date(obj_sb.getFree_checkup_date());
				}
				comments = obj_sb.getComments();
				total_amt = obj_sb.getTotal_amount();
			}

			String quantity = obj_sd.getQuantity() + "";

			// Set value as 01, 02, 10 ..
			if(Integer.parseInt(quantity) < 10){
				quantity = "0" + quantity;
			}

			// Append space before the quantity value for clear visiblity
			quantity = " " + quantity;
			String total = Formatter.getCurrencyFormat(obj_sd.getQuantity() * obj_sd.getAmount());

			String amount = obj_sd.getAmount() + "" ;

			// Set amount as Free if it is 0
			if(Double.parseDouble(amount) == 0){
				amount = "Free";
				total = "0.00";
			}
			else{
				amount = Formatter.getCurrencyFormat(Double.parseDouble(amount));
			}

			mdl_particulars.setValueAt(quantity, service_row_map.get(obj_sd.getService_particular_id()), 2);
			mdl_particulars.setValueAt(amount, service_row_map.get(obj_sd.getService_particular_id()), 3);
			mdl_particulars.setValueAt(total, service_row_map.get(obj_sd.getService_particular_id()), 4);
		}

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblName.setBounds(12, 178, 59, 15);
		bill_panel.add(lblName);

		JLabel lblMobile = new JLabel("Mobile:");
		lblMobile.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMobile.setBounds(12, 197, 59, 15);
		bill_panel.add(lblMobile);

		lbl_name_value = new JLabel("name_value");
		lbl_name_value.setBounds(83, 178, 235, 15);
		lbl_name_value.setText(c_name);
		bill_panel.add(lbl_name_value);

		lbl_mobile_value = new JLabel("mobile_value");
		lbl_mobile_value.setBounds(83, 197, 115, 15);
		lbl_mobile_value.setText(c_mobile);
		bill_panel.add(lbl_mobile_value);

		JLabel lblNewLabel_1 = new JLabel("Vehicle No.:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(318, 178, 90, 15);
		bill_panel.add(lblNewLabel_1);

		lbl_vehicle_no_value = new JLabel("vehicle_no_value");
		lbl_vehicle_no_value.setBounds(413, 178, 132, 15);
		lbl_vehicle_no_value.setText(v_num);
		bill_panel.add(lbl_vehicle_no_value);

		JLabel lblMake = new JLabel("Make:");
		lblMake.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMake.setBounds(318, 196, 53, 15);
		bill_panel.add(lblMake);

		lbl_make_value = new JLabel("make_value");
		lbl_make_value.setBounds(413, 196, 115, 15);
		lbl_make_value.setText(v_make);
		bill_panel.add(lbl_make_value);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDate.setBounds(12, 159, 44, 15);
		bill_panel.add(lblDate);

		lbl_date_value = new JLabel("date_value");
		lbl_date_value.setBounds(83, 159, 93, 15);
		lbl_date_value.setText(date);
		bill_panel.add(lbl_date_value);

		JLabel lbl_time = new JLabel("Time:");
		lbl_time.setFont(new Font("Dialog", Font.PLAIN, 12));
		lbl_time.setBounds(318, 159, 70, 15);
		bill_panel.add(lbl_time);

		lbl_time_value = new JLabel("bill_no_value");
		lbl_time_value.setBounds(413, 159, 115, 15);
		lbl_time_value.setText(time);
		bill_panel.add(lbl_time_value);

		tbl_particulars = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) { 
				return false;
			}
		};
		tbl_particulars.setModel(mdl_particulars);
		tbl_particulars.setColumnSelectionAllowed(false);
		//tbl_particulars.setBorder(new LineBorder(new Color(0, 0, 0)));

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		JTableHeader table_header = tbl_particulars.getTableHeader();
		table_header.setFont(new Font("Dialog", Font.BOLD, 12));

		tbl_particulars.getColumnModel().getColumn(0).setResizable(false);
		tbl_particulars.getColumnModel().getColumn(0).setPreferredWidth(40);
		tbl_particulars.getColumnModel().getColumn(0).setMaxWidth(40);
		tbl_particulars.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		tbl_particulars.getColumnModel().getColumn(1).setResizable(false);
		tbl_particulars.getColumnModel().getColumn(1).setPreferredWidth(230);
		tbl_particulars.getColumnModel().getColumn(1).setMaxWidth(250);

		tbl_particulars.getColumnModel().getColumn(2).setResizable(false);
		tbl_particulars.getColumnModel().getColumn(2).setPreferredWidth(30);
		tbl_particulars.getColumnModel().getColumn(2).setMaxWidth(40);

		tbl_particulars.getColumnModel().getColumn(3).setResizable(false);
		tbl_particulars.getColumnModel().getColumn(3).setPreferredWidth(100);
		tbl_particulars.getColumnModel().getColumn(3).setMaxWidth(100);
		tbl_particulars.getColumnModel().getColumn(3).setCellRenderer(NumberRenderer.getCurrencyRenderer());

		tbl_particulars.getColumnModel().getColumn(4).setResizable(false);
		tbl_particulars.getColumnModel().getColumn(4).setPreferredWidth(120);
		tbl_particulars.getColumnModel().getColumn(4).setMaxWidth(130);
		tbl_particulars.getColumnModel().getColumn(4).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		tbl_particulars.setBounds(10, 224, 535, 265);

		// Don't change background color on select
		tbl_particulars.setSelectionBackground(getBackground());

		// 260 is the maximum table height space available
		int table_row_height = 260 / mdl_particulars.getRowCount();
		tbl_particulars.setRowHeight(table_row_height);
		Logger.log.info("Setting Table row height to " + table_row_height);

		JScrollPane tbl_particulars_main = new JScrollPane(tbl_particulars);
		tbl_particulars_main.setViewportBorder(null);
		tbl_particulars_main.getViewport().setBackground(Color.WHITE);
		tbl_particulars_main.setBorder(null);

		// Adjusting ScrollPane size to fit the table
		// + 22 for header
		tbl_particulars_main.setBounds(10, 216, 535, (table_row_height * mdl_particulars.getRowCount()) + 22);
		bill_panel.add(tbl_particulars_main);

		JLabel lblRupees = new JLabel("Rupees:");
		lblRupees.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblRupees.setBounds(10, 522, 70, 15);
		bill_panel.add(lblRupees);

		final JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		btnClose.setBounds(422, 653, 76, 25);
		bill_panel.add(btnClose);

		final JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPrint.setVisible(false);
				btnClose.setVisible(false);
				print();
				btnPrint.setVisible(true);
				btnClose.setVisible(true);
			}
		});
		btnPrint.setBounds(421, 613, 76, 25);
		bill_panel.add(btnPrint);

		JLabel lblAbhiEnterprices = new JLabel("ABHI ENTERPRISES");
		lblAbhiEnterprices.setFont(new Font("Dialog", Font.BOLD, 30));
		lblAbhiEnterprices.setBounds(197, 11, 331, 36);
		bill_panel.add(lblAbhiEnterprices);

		JTextPane txt_address = new JTextPane();
		txt_address.setFont(new Font("Dialog", Font.BOLD, 12));
		txt_address.setBackground(UIManager.getColor("ColorChooser.background"));
		txt_address.setEditable(false);
		txt_address.setContentType("text/html");
		//txt_address.setText(address);
		txt_address.setText("<html><center>" + address + "</center></html>");
		txt_address.setBounds(17, 113, 525, 42);
		bill_panel.add(txt_address);

		JLabel lblComputerizedEmissionTest = new JLabel("Computerised Emission test center for Petrol and Diesel");
		lblComputerizedEmissionTest.setFont(new Font("Dialog", Font.BOLD, 11));
		lblComputerizedEmissionTest.setBounds(185, 39, 365, 20);
		bill_panel.add(lblComputerizedEmissionTest);

		JLabel lblEmail = new JLabel("email:");
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblEmail.setBounds(196, 76, 37, 15);
		bill_panel.add(lblEmail);

		JLabel lblAbhiweelsgmailcom = new JLabel("abhiweels@gmail.com");
		lblAbhiweelsgmailcom.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblAbhiweelsgmailcom.setBounds(239, 76, 176, 15);
		bill_panel.add(lblAbhiweelsgmailcom);

		JLabel lblMobile_1 = new JLabel("Mobile:");
		lblMobile_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblMobile_1.setBounds(190, 93, 44, 15);
		bill_panel.add(lblMobile_1);

		JLabel label = new JLabel("9880067133, 9379667133, 8023597133");
		label.setFont(new Font("Dialog", Font.PLAIN, 12));
		label.setBounds(240, 93, 305, 15);
		bill_panel.add(label);

		txt_rupees = new JTextArea();
		txt_rupees.setEditable(false);
		txt_rupees.setFont(new Font("Dialog", Font.BOLD, 12));
		txt_rupees.setBorder(null);
		txt_rupees.setMargin(text_area_margin);
		txt_rupees.setBounds(67, 522, 478, 36);
		bill_panel.add(txt_rupees);

		JLabel lblTotal = new JLabel("Total: ");
		lblTotal.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTotal.setBounds(367, 500, 53, 15);
		bill_panel.add(lblTotal);

		lbl_total_value = new JLabel("");
		lbl_total_value.setFont(new Font("Dialog", Font.BOLD, 15));
		lbl_total_value.setBounds(423, 495, 124, 25);
		if(total_amt > 0){
			lbl_total_value.setText(Formatter.getCurrencyFormat(total_amt));
			txt_rupees.setText(NumberWordConverter.convert((int)Math.round(total_amt)) + " rupees only");
		}
		else{
			lbl_total_value.setText("0.00 \u20B9");
			txt_rupees.setText("FREE");
		}
		bill_panel.add(lbl_total_value);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(10, 558, 535, 8);
		bill_panel.add(separator_1);

		JTextPane txtpnFreeCheckupWithin = new JTextPane();
		txtpnFreeCheckupWithin.setEditable(false);
		txtpnFreeCheckupWithin.setBounds(20, 565, 298, 25);

		lbl_free_checkup_date = new JLabel();
		lbl_free_checkup_date.setBounds(29, 591, 277, 15);

		if(free_checkup_date != null && total_amt > 0.1){
			txtpnFreeCheckupWithin.setFont(new Font("Dialog", Font.BOLD, 15));
			txtpnFreeCheckupWithin.setBackground(SystemColor.window);
			txtpnFreeCheckupWithin.setText("FREE CHECK-UP WITHIN 21 DAYS");
			bill_panel.add(txtpnFreeCheckupWithin);

			lbl_free_checkup_date.setFont(new Font("Dialog", Font.PLAIN, 12));
			lbl_free_checkup_date.setText("Your free checkup due date is " + free_checkup_date);
			bill_panel.add(lbl_free_checkup_date);
		}

		JLabel lblAbhiWheels = new JLabel("ABHI WHEELS");
		lblAbhiWheels.setFont(new Font("Dialog", Font.BOLD, 20));
		lblAbhiWheels.setBounds(375, 576, 168, 22);
		bill_panel.add(lblAbhiWheels);

		JLabel lblFor = new JLabel("for");
		lblFor.setBounds(346, 578, 29, 15);
		bill_panel.add(lblFor);

		JLabel lblRemarksByTechnician = new JLabel("Remarks by technician:");
		lblRemarksByTechnician.setBounds(26, 612, 168, 15);
		bill_panel.add(lblRemarksByTechnician);

		txt_comments = new JTextArea();
		txt_comments.setEditable(false);
		txt_comments.setBorder(new LineBorder(new Color(0, 0, 0)));
		txt_comments.setText(" " + comments);
		txt_comments.setBounds(28, 629, 305, 69);
		txt_comments.setMargin(text_area_margin);
		bill_panel.add(txt_comments);

		JLabel lblAlignmentTyres = new JLabel("Wheel Alignment & Multi Brand Tyres");
		lblAlignmentTyres.setFont(new Font("Dialog", Font.BOLD, 12));
		lblAlignmentTyres.setBounds(225, 55, 273, 15);
		bill_panel.add(lblAlignmentTyres);

	}

	// Source: http://stackoverflow.com/questions/30703329/how-to-print-jpanel-in-java
	public void print(){
		PrinterJob pjob = PrinterJob.getPrinterJob();
		PageFormat preformat = pjob.defaultPage();
		preformat.setOrientation(PageFormat.PORTRAIT);

		PrintRequestAttributeSet printRequestAttrSet = new HashPrintRequestAttributeSet();
		printRequestAttrSet.add(MediaSizeName.ISO_A5);
		printRequestAttrSet.add(new MediaPrintableArea((float)0.0,(float)0.0,350,500, MediaPrintableArea.MM));

		// To show page setup dialog
		//PageFormat postformat = pjob.pageDialog(preformat);

		//Set print component
		// Shows printer select dialog
		pjob.setPrintable(new Printer(bill_panel), preformat);
		if (pjob.printDialog(printRequestAttrSet)) {
			try {
				pjob.print(printRequestAttrSet);
			} catch (PrinterException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Print not succeed!!! Someting went wrong!");
			}
		}
	}

	private ImageIcon getScaledImage(String path, int w, int h){
		ImageIcon imageIcon = new ImageIcon(getClass().getResource(path));
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(w, h,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		return new ImageIcon(newimg);
	}
}
