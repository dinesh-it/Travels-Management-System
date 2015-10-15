package UserInterface;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;

import DataBaseInterface.Handler;
import DataBaseInterface.SMSQueue;
import Util.*;

public class sms_notifications extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnl_sms_notofication_main;
	private JTable tbl_sms_notifications;
	private static Handler dbh = Handler.getInstance();
	SMSQueue sms;
	Toolkit tool=Toolkit.getDefaultToolkit();
	Dimension dim=tool.getScreenSize();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sms_notifications frame = new sms_notifications();
					frame.setAlwaysOnTop(true);
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

		setSize(dim.width,dim.height);		
		//setBounds(100, 100, 686, 533);
		pnl_sms_notofication_main = new JPanel();
		pnl_sms_notofication_main.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnl_sms_notofication_main);
		pnl_sms_notofication_main.setLayout(null);

		//JButton btn_export_excel = new JButton("Download");
		//btn_export_excel.setBounds(530, 0, 140, 25);
		//pnl_sms_notofication_main.add(btn_export_excel);

		List<?> data = dbh.get_sms_queue();
		int i = 0;
		int max_size = data.size();

		String[] columns = new String[] {"Id","Mobile Number", "Message", "Action"};
		Object items[][] = new Object[max_size][max_size*columns.length];

		for (Iterator<?> iterator = data.iterator(); iterator.hasNext();){
			sms = (SMSQueue) iterator.next();

			items[i][0] = sms.getId();
			items[i][1] = sms.getMobile_number();
			items[i][2] = sms.getMessage();
			items[i][3] = "Sent"; 
			i++;
		}

		tbl_sms_notifications = new JTable(){
			private static final long serialVersionUID = 3l;

			public Class<?> getColumnClass(int column) {
				//Logger.log.info("Column get "+column);
				//Logger.log.info(getValueAt(0, column);
				return getValueAt(0, column).getClass();
			}         

			public boolean isCellEditable(int row, int column) {return true;}
		};

		tbl_sms_notifications.setModel(new DefaultTableModel(items,columns));
		tbl_sms_notifications.setDefaultRenderer(String.class, new LabelRenderer());

		JScrollPane scrl_sms_notification = new JScrollPane(tbl_sms_notifications);
		//scrl_sms_notification.setBounds(12, 28, 658, 467);
		scrl_sms_notification.setSize(dim.width,dim.height);

		tbl_sms_notifications.getColumnModel().getColumn(0).setPreferredWidth(5);
		//tbl_sms_notifications.getColumnModel().getColumn(1).setPreferredWidth(10);
		tbl_sms_notifications.getColumnModel().getColumn(2).setPreferredWidth(1000);
		tbl_sms_notifications.getColumnModel().getColumn(3).setPreferredWidth(10);
		tbl_sms_notifications.setBounds(0, 37, 440, 241);
		pnl_sms_notofication_main.add(scrl_sms_notification);
		tbl_sms_notifications.addMouseListener(new java.awt.event.MouseAdapter()  {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				int row=tbl_sms_notifications.rowAtPoint(e.getPoint());
				int col= tbl_sms_notifications.columnAtPoint(e.getPoint());
				if( col == 3){

					//JOptionPane.showMessageDialog(null," Value in the cell clicked :"+ " " +tbl_sms_notifications.getValueAt(row,col).toString());
					//System.out.println(" Value in the cell clicked :"+ " " +tbl_sms_notifications.getValueAt(row,col).toString());
					sms.setSent(true);
					sms.setUpdated_user_id(1);
					sms.setUpdated_epoch(Time.now());
					//update this queue
					dbh.update(sms);
					//tbl_sms_notifications.removeColumn(tbl_sms_notifications.getColumn(col));
					tbl_sms_notifications.getSelectionModel().clearSelection();
					((DefaultTableModel)tbl_sms_notifications.getModel()).removeRow(row);
				}
			}
		}
				);
	}
}

/**
 * Displays Strings as JLabels
 */
class LabelRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 2l;
	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {

		JLabel label = new JLabel(value.toString());
		if ( column == 3 ) {
			//System.out.println("setting colors for"+label.getText());
			java.awt.Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			cellComponent.setFont(new Font("Century Schoolbook L", Font.BOLD, 20));
			cellComponent.setForeground(SystemColor.RED);
			//cellComponent.setBackground(UIManager.getColor("Button.focus"));
			cellComponent.setBackground(Color.BLACK);
			return cellComponent;
		}
		return label;
	}
}