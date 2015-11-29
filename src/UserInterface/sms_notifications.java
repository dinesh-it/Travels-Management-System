package UserInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JScrollPane;
import DataBaseInterface.Handler;
import DataBaseInterface.SMSQueue;
import Util.*;

public class sms_notifications extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable tbl_sms_notifications;
	private static Handler dbh = Handler.getInstance();
	private DefaultTableModel mdl_sms_list;
	private JCheckBox ckb_send_sms;
	private JButton btn_sms_send;
	int window_width,window_height;
	Toolkit tool=Toolkit.getDefaultToolkit();
	Dimension dim=tool.getScreenSize();

	public sms_notifications(){
		new sms_notifications(1180,740);
	}

	public sms_notifications(int width,int height) {

		setSize(width, height-30);

		window_width = this.getWidth();
		window_height = this.getHeight();
		int field_heights = 25;
		int point_x = 20;
		int point_y = 30;
		setLayout(null);
		int default_days = 5;

		JLabel lbl_search = new JLabel("Show SMS(s) for due less than ");
		lbl_search.setFont(new Font("Dialog", Font.BOLD, 12));
		lbl_search.setBounds(point_x + 10, point_y, 220, field_heights);
		add(lbl_search);

		final JTextField txt_days_filter = new JTextField();
		txt_days_filter.setFont(new Font("Dialog", Font.BOLD, 15));
		txt_days_filter.setBounds(point_x + 230, point_y, 50, field_heights);
		txt_days_filter.setText(default_days + "");
		add(txt_days_filter);
		txt_days_filter.setColumns(4);

		JLabel lbl_search1 = new JLabel(" days");
		lbl_search1.setFont(new Font("Dialog", Font.BOLD, 12));
		lbl_search1.setBounds(point_x + 280, point_y, 50, field_heights);
		add(lbl_search1);
		
		final JCheckBox ckb_show_sent = new JCheckBox("Show sent SMS");
		ckb_show_sent.setBounds(point_x + 350, point_y , 150, field_heights);
		add(ckb_show_sent);

		JButton btn_search = new JButton("Search");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int due_date = Integer.parseInt(txt_days_filter.getText());
					loadSMSQueues(due_date,ckb_show_sent.isSelected());
				}
				catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(null,"Please enter a valid number of days in the search");
				}
				catch(Exception e1){
					Logger.log.severe("Error while loading SMSQueue");
					Logger.log.severe(e1.getMessage());
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"Sorry! Something went wrong please contact developer team!");
				}
			}
		});
		// x,y, width, height
		btn_search.setBounds(point_x + 500, point_y , 100, field_heights);
		add(btn_search);

		btn_sms_send = new JButton("Send selected SMS");
		btn_sms_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save_sent_sms(ckb_send_sms.isSelected());
				try {
					int due_date = Integer.parseInt(txt_days_filter.getText());
					loadSMSQueues(due_date,ckb_show_sent.isSelected());
				}
				catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(null,"Please enter a valid number of days in the search");
				}
				catch(Exception e1){
					Logger.log.severe("Error while loading SMSQueue");
					Logger.log.severe(e1.getMessage());
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"Sorry! Something went wrong please contact developer team!");
				}
			}
		});
		// x,y, width, height
		btn_sms_send.setBounds(window_width - 220, point_y , 200, field_heights);
		add(btn_sms_send);

		ckb_send_sms = new JCheckBox("Send SMS via API");
		//ckb_send_sms.setHorizontalAlignment(SwingConstants.RIGHT);
		ckb_send_sms.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					btn_sms_send.setText("Send selected SMS(s)");
				}
				else{
					btn_sms_send.setText("Selected SMS(s) Sent");
				}
			}
		});
		ckb_send_sms.setBounds(window_width - 390, 30 , 150, field_heights);
		add(ckb_send_sms);

		tbl_sms_notifications = new JTable(){
			private static final long serialVersionUID = 3l;

			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}         

			public boolean isCellEditable(int row, int column) {
				if(column == 3){
					//return getValueAt(row, column).equals(false);
					return true;
				}
				else{
					return true;
				}
				
			}

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component comp = super.prepareRenderer(renderer, row, col);
				if (col == 3 && getValueAt(row, col).equals(true)) {
						comp.setBackground(Color.LIGHT_GRAY);
				} 
				return comp;
			}
		};

		mdl_sms_list = new DefaultTableModel(){

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int col) {
				return true;
			}

			public Class<?> getColumnClass(int c) {

				if(this.getRowCount() > 0) {
					return getValueAt(0, c).getClass();
				} else {
					return new String().getClass();
				}
			}
		};

		String[] columns = new String[] {"Id","Mobile Number", "Message", "Select"};
		mdl_sms_list.setColumnIdentifiers(columns);

		tbl_sms_notifications.setModel(mdl_sms_list);
		//tbl_sms_notifications.setDefaultRenderer(String.class, new LabelRenderer());
		tbl_sms_notifications.setRowHeight(30);

		tbl_sms_notifications.getColumnModel().getColumn(0).setMinWidth(20);
		tbl_sms_notifications.getColumnModel().getColumn(0).setMaxWidth(60);
		tbl_sms_notifications.getColumnModel().getColumn(1).setMinWidth(150);
		tbl_sms_notifications.getColumnModel().getColumn(1).setMaxWidth(200);
		tbl_sms_notifications.getColumnModel().getColumn(3).setMinWidth(100);
		tbl_sms_notifications.getColumnModel().getColumn(3).setMaxWidth(150);

		JScrollPane scrl_sms_notification = new JScrollPane(tbl_sms_notifications);
		scrl_sms_notification.setBounds(20, field_heights + 40, window_width - 40 , window_height - 110);
		this.add(scrl_sms_notification);

		loadSMSQueues(default_days, ckb_show_sent.isSelected());
	}

	private void loadSMSQueues(int remaining_due_days, boolean show_sent){
		mdl_sms_list.setNumRows(0);

		List<Object[]> data = dbh.get_sms_queue(remaining_due_days,show_sent);

		if(data.size() == 0)
			return;

		Object items[] = new Object[4];
		for (Object[] obj : data){
			SMSQueue sms = (SMSQueue) obj[0];
			items[0] = sms.getId();
			items[1] = sms.getMobile_number();
			items[2] = sms.getMessage();
			items[3] = sms.isSent();
			mdl_sms_list.addRow(items);
		}
	}

	private void save_sent_sms(boolean send_and_save){
		String alert_confirm, success_msg;
		
		if(send_and_save){
			alert_confirm = "Are you sure, you want to send all selected SMS's?";
			success_msg = "SMS's queued success.\nAll messages will be marked as sent automatically once sent successfully.";
		}
		else{
			alert_confirm = "Are you sure, you want to mark all the selected SMS's as sent?";
			success_msg = "Selected SMS's marked as sent successfully";
		}
		
		int save_confirm = JOptionPane.showConfirmDialog(this,alert_confirm, "Confirm", JOptionPane.YES_NO_OPTION);

		// Stop save if user not click the yes option
		if(save_confirm != JOptionPane.YES_OPTION){
			return;
		}
		
		for(int i=0;i<mdl_sms_list.getRowCount();i++){
			
			int id = Integer.parseInt(mdl_sms_list.getValueAt(i, 0).toString());
			String to = mdl_sms_list.getValueAt(i, 1).toString();
			String msg = mdl_sms_list.getValueAt(i, 2).toString();
			boolean selected = mdl_sms_list.getValueAt(i, 3).equals(true);

			SMSQueue sms = dbh.get_sms(id);

			if(selected && !sms.isSent()){

				if(send_and_save){
					sms.setMobile_number(to);
					sms.setMessage(msg);
					sms.setUpdated_user_id(1);
					sms.setUpdated_epoch(Time.now());

					SMSDispatcher sms_d = new SMSDispatcher(sms);
					sms_d.send_sms();
				}
				else{
					try {
						sms.setMobile_number(to);
						sms.setMessage(msg);
						sms.setSent(true);
						sms.setUpdated_user_id(1);
						sms.setUpdated_epoch(Time.now());
						sms.setSent_epoch(Time.now());

						//update this queue
						dbh.update(sms);
					}
					catch(Exception e){
						Logger.log.severe("Error while saving SMS sent status");
						Logger.log.severe(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
		
		JOptionPane.showMessageDialog(null, success_msg);
	}

}
