package UserInterface;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class sms_notifications extends JFrame {

	private JPanel pnl_sms_notofication_main;
	private JTable tbl_sms_notifications;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sms_notifications frame = new sms_notifications();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public sms_notifications() {
		setTitle("SMS Notifications List");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 686, 533);
		pnl_sms_notofication_main = new JPanel();
		pnl_sms_notofication_main.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnl_sms_notofication_main);
		pnl_sms_notofication_main.setLayout(null);
		
		JButton btn_export_excel = new JButton("Export as excel");
		btn_export_excel.setBounds(530, 0, 140, 25);
		pnl_sms_notofication_main.add(btn_export_excel);
		
		tbl_sms_notifications = new JTable();
		tbl_sms_notifications.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Mobile Number", "Message", "Action"
			}
		));
		
		JScrollPane scrl_sms_notification = new JScrollPane(tbl_sms_notifications);
		scrl_sms_notification.setBounds(12, 28, 658, 467);
		
		tbl_sms_notifications.getColumnModel().getColumn(0).setPreferredWidth(123);
		tbl_sms_notifications.getColumnModel().getColumn(1).setPreferredWidth(368);
		tbl_sms_notifications.setBounds(0, 37, 440, 241);
		pnl_sms_notofication_main.add(scrl_sms_notification);
	}
}
