package UserInterface;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import DataBaseInterface.Handler;

import java.awt.SystemColor;
import java.awt.Cursor;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1;

	static public JPanel pnl_home;
	static 	MainFrame frame;

	public static void main(String[] args) {

		ProgressBar.showProgress("Loading Components....", 10);
		Handler.getInstance();
		ProgressBar.showProgress("Launching Application..", ProgressBar.MAXIMUM_VALUE);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {

		// Frame Properties
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Travels and Wheels");
		Toolkit tool=Toolkit.getDefaultToolkit();
		Dimension dim=tool.getScreenSize();
		setSize(dim.width,dim.height);


		//Group styles
		Color grp_background = new Color(51, 51, 51);

		//Button styles
		Color btn_background = SystemColor.desktop;
		Color btn_foreground = new Color(192, 192, 192);
		Cursor btn_cursor= Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		Font btn_font = new Font("Century Schoolbook L", Font.BOLD, 13);

		//Menu styles
		Color menu_btn_foreground = new Color(127, 255, 0);
		Color menu_btn_background = new Color(72, 61, 139);


		// Initialize menubar
		JMenuBar mbr_home=new JMenuBar();
		mbr_home.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mbr_home.setBorderPainted(false);
		mbr_home.setBackground(new Color(0, 0, 0));
		setJMenuBar(mbr_home);

		// logout button
		JButton btn_logout=new JButton("Logout");
		btn_logout.setForeground(menu_btn_foreground);
		btn_logout.setBackground(menu_btn_background);
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag = JOptionPane.showConfirmDialog(frame, "Do you want to exit ?");
				if (flag == 0){
					System.exit(0);
				}
			}
		});


		// home button with click action listener 
		JButton btn_home = new JButton("Home");
		btn_home.setBackground(menu_btn_background);
		btn_home.setForeground(menu_btn_foreground);
		btn_home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(pnl_home);
			}
		});

		// Add home and Logout button in menubar
		mbr_home.add(Box.createHorizontalGlue());

		JButton btn_sms_notification = new JButton("SMS Notifications");
		btn_sms_notification.setBackground(menu_btn_background);
		btn_sms_notification.setForeground(menu_btn_foreground);
		btn_sms_notification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sms_notifications sms_notify = new sms_notifications(getWidth(),getHeight());
				setContentPane(sms_notify);
			}
		});

		JButton btn_sms_template = new JButton("SMS Template");
		btn_sms_template.setBackground(menu_btn_background);
		btn_sms_template.setForeground(menu_btn_foreground);
		btn_sms_template.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sms_template sms_template = new sms_template();
				sms_template.setVisible(true);
			}
		});
		
		JButton btn_edit_particulars = new JButton("Edit Particulars");
		btn_edit_particulars.setBackground(menu_btn_background);
		btn_edit_particulars.setForeground(menu_btn_foreground);
		btn_edit_particulars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int width =  getWidth();
				int height =  getHeight();
				pnl_edit_service_particulars obj_ep= new pnl_edit_service_particulars(width, height);
				setContentPane(obj_ep);
			}
		});

		mbr_home.add(btn_edit_particulars);
		mbr_home.add(btn_sms_template);
		mbr_home.add(btn_sms_notification);
		mbr_home.add(btn_home);
		mbr_home.add(btn_logout);

		// home screen panel
		pnl_home = new JPanel();
		pnl_home.setBackground(SystemColor.inactiveCaptionText);
		pnl_home.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnl_home);
		pnl_home.setLayout(null);

		// Title Label
		// TODO have to add icon 
		JLabel lbl_title = new JLabel("ABHI WHEELS");
		lbl_title.setForeground(new Color(210, 105, 30));
		lbl_title.setFont(new Font("URW Bookman L", Font.ITALIC, 32));
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setBounds(432, 40, 466, 57);
		pnl_home.add(lbl_title);

		// Group box for trip  buttons
		JPanel pnl_sub_trip = new JPanel();
		pnl_sub_trip.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 2, true), "Trip", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(178, 34, 34)));
		pnl_sub_trip.setBackground(grp_background);
		pnl_sub_trip.setBounds(117, 149, 428, 417);
		pnl_home.add(pnl_sub_trip);
		pnl_sub_trip.setLayout(null);

		// New trip window navigation button with click action listener
		JButton btn_new_trip = new JButton("Add Trip");
		btn_new_trip.setCursor(btn_cursor);
		btn_new_trip.setFont(btn_font);
		btn_new_trip.setForeground(btn_foreground);
		btn_new_trip.setBackground(btn_background);
		btn_new_trip.setBounds(89, 64, 251, 42);
		//		btn_new_trip.setBorder(UIManager.getBorder("Button.border"));

		btn_new_trip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnl_trip_add obj_trip = new pnl_trip_add();
				setContentPane(obj_trip);
			}
		});
		pnl_sub_trip.add(btn_new_trip);


		// view trip window navigation button
		JButton btn_view_trip = new JButton("Show Trips Report");
		btn_view_trip.setFont(btn_font);
		btn_view_trip.setCursor(btn_cursor);
		btn_view_trip.setForeground(btn_foreground);
		btn_view_trip.setBackground(btn_background);
		btn_view_trip.setBounds(89, 288, 251, 42);

		btn_view_trip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inprogress();
				//pnl_trip_view obj_trip_view = new pnl_trip_view();
				//setContentPane(obj_trip_view);
			}
		});

		pnl_sub_trip.add(btn_view_trip);

		// update trip window navigation button
		JButton btn_update_trip = new JButton("Edit Trip");
		btn_update_trip.setBounds(89, 172, 251, 42);
		btn_update_trip.setCursor(btn_cursor);
		btn_update_trip.setForeground(btn_foreground);
		btn_update_trip.setBackground(btn_background);

		btn_update_trip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inprogress();
			}
		});

		pnl_sub_trip.add(btn_update_trip);

		// Group box for Wheel buttons 
		JPanel pnl_sub_wheel = new JPanel();
		pnl_sub_wheel.setLayout(null);
		pnl_sub_wheel.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 2, true), "Wheels", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(178, 34, 34)));
		pnl_sub_wheel.setBackground(grp_background);
		pnl_sub_wheel.setBounds(789, 149, 428, 417);
		pnl_home.add(pnl_sub_wheel);

		// new wheel window navigation button with action listener
		JButton btn_new_wheel = new JButton("Add Wheel Bill");
		btn_new_wheel.setFont(btn_font);
		btn_new_wheel.setCursor(btn_cursor);
		btn_new_wheel.setForeground(btn_foreground);
		btn_new_wheel.setBackground(btn_background);
		btn_new_wheel.setBounds(89, 64, 251, 42);

		btn_new_wheel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pnl_wheels_add obj_wheels = new pnl_wheels_add();
				setContentPane(obj_wheels);
				obj_wheels.setFieldFocus();
			}
		});

		pnl_sub_wheel.add(btn_new_wheel);

		// view wheel window navigation button
		JButton btn_view_wheel = new JButton("Show Wheels Report");
		btn_view_wheel.setFont(btn_font);
		btn_view_wheel.setCursor(btn_cursor);
		btn_view_wheel.setForeground(btn_foreground);
		btn_view_wheel.setBackground(btn_background);
		btn_view_wheel.setBounds(89, 288, 251, 42);

		pnl_sub_wheel.add(btn_view_wheel);

		btn_view_wheel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int width =  getWidth();
				int height =  getHeight();
				
				pnl_wheels_view obj_wheels_view = new pnl_wheels_view(width, height);
				setContentPane(obj_wheels_view);
			}
		});
		// update wheel window navigation button
		JButton btn_update_wheel = new JButton("Edit Wheel Bill");
		btn_update_wheel.setFont(btn_font);
		btn_update_wheel.setCursor(btn_cursor);
		btn_update_wheel.setForeground(btn_foreground);
		btn_update_wheel.setBackground(btn_background);
		btn_update_wheel.setBounds(89, 172, 251, 42);
		btn_update_wheel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inprogress();
			}
		});

		pnl_sub_wheel.add(btn_update_wheel);

		setVisible(true);
	}

	public static JFrame get_main_frame(){
		return frame;
	}

	public void inprogress(){
		JOptionPane.showMessageDialog(frame,"This feature will be added soon");
	}
}
