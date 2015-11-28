package UserInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import DataBaseInterface.Handler;
import DataBaseInterface.SMSQueue;
import Util.*;

public class sms_notifications extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable tbl_sms_notifications;
	private static Handler dbh = Handler.getInstance();
	int window_width,window_height;
	SMSQueue sms;
	Toolkit tool=Toolkit.getDefaultToolkit();
	Dimension dim=tool.getScreenSize();

	public sms_notifications(int width,int height) {

		setSize(width, height-30);

		window_width = this.getWidth();
		window_height = this.getHeight();
		setLayout(null);

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
			items[i][3] = "Send"; 
			i++;
		}

		tbl_sms_notifications = new JTable(){
			private static final long serialVersionUID = 3l;

			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}         

			public boolean isCellEditable(int row, int column) {return true;}
		};

		tbl_sms_notifications.setModel(new DefaultTableModel(items,columns));
		tbl_sms_notifications.setDefaultRenderer(String.class, new LabelRenderer());
		tbl_sms_notifications.setRowHeight(30);

		tbl_sms_notifications.getColumnModel().getColumn(0).setMinWidth(20);
		tbl_sms_notifications.getColumnModel().getColumn(0).setMaxWidth(60);
		tbl_sms_notifications.getColumnModel().getColumn(1).setMinWidth(150);
		tbl_sms_notifications.getColumnModel().getColumn(1).setMaxWidth(200);
		tbl_sms_notifications.getColumnModel().getColumn(3).setMinWidth(100);
		tbl_sms_notifications.getColumnModel().getColumn(3).setMaxWidth(150);

		//tbl_sms_notifications.setBounds(0, 37, 440, 241);
		tbl_sms_notifications.addMouseListener(new java.awt.event.MouseAdapter()  {

			public void mouseClicked(java.awt.event.MouseEvent e) {
				int row=tbl_sms_notifications.rowAtPoint(e.getPoint());
				int col= tbl_sms_notifications.columnAtPoint(e.getPoint());
				String label = tbl_sms_notifications.getValueAt(row,col).toString();
				if( col == 3 && label == "Send"){

					String to = tbl_sms_notifications.getValueAt(row,1).toString();
					String msg = tbl_sms_notifications.getValueAt(row,2).toString();
					int send_confirm = JOptionPane.showConfirmDialog(null, "Mobile: " + to + "\n\nMessage: " + msg, "Send this message?", JOptionPane.OK_CANCEL_OPTION);

					if(send_confirm == JOptionPane.OK_OPTION){
						tbl_sms_notifications.setValueAt("Sending...", row, col);
						try {
							SendUnicodeSms sms_sender = new SendUnicodeSms();
							if(sms_sender.send_message(to, msg)){
								sms.setSent(true);
								sms.setUpdated_user_id(1);
								sms.setUpdated_epoch(Time.now());
								sms.setSent_epoch(Time.now());

								//update this queue
								dbh.update(sms);

								//tbl_sms_notifications.removeColumn(tbl_sms_notifications.getColumn(col));
								tbl_sms_notifications.getSelectionModel().clearSelection();
								((DefaultTableModel)tbl_sms_notifications.getModel()).removeRow(row);
							}
							else{
								Logger.log.severe("Error while sending SMS,send sms returned false");
								JOptionPane.showMessageDialog(null, "Error while sending SMS, please try again,\nor contact development team with log file");
								tbl_sms_notifications.setValueAt("Send", row, col);
							}

						}catch(UnknownHostException uke){
							tbl_sms_notifications.setValueAt("Send", row, col);
							JOptionPane.showMessageDialog(null, "Unable to connect to network...\nPlease check your internet connection");
							Logger.log.severe("Error while triggering send SMS, Unknownhost exception");
							Logger.log.severe(uke.getMessage());
						}catch(Exception e1){
							tbl_sms_notifications.setValueAt("Send", row, col);
							JOptionPane.showMessageDialog(null, "Error while sending SMS, please try again,\nor contact development team with log file");
							Logger.log.severe("Error while triggering send SMS");
							Logger.log.severe(e1.getMessage());
							e1.printStackTrace();
						}
					}
				}
			}
		}
				);

		JScrollPane scrl_sms_notification = new JScrollPane(tbl_sms_notifications);
		scrl_sms_notification.setBounds(20, 50, window_width - 40 , window_height - 70);
		this.add(scrl_sms_notification);

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