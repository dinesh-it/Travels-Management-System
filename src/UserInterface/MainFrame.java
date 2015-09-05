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


public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;

	static public JPanel pnl_home;
	static 	MainFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Handler.getInstance();
					//TODO Add progress bar later , if it takes more time
					frame = new MainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {

		// Frame Properties
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Travels and Wheels");
		Toolkit tool=Toolkit.getDefaultToolkit();
		Dimension dim=tool.getScreenSize();
		setSize(dim.width,dim.height);

		// Initialize menubar
		JMenuBar mbr_home=new JMenuBar();
		mbr_home.setBackground(new Color(119, 136, 153));
		setJMenuBar(mbr_home);

		// logout button
		JButton btn_logout=new JButton("Logout");
		btn_logout.setForeground(new Color(127, 255, 0));
		btn_logout.setBackground(new Color(72, 61, 139));

		// home button with click action listener 
		JButton btn_home = new JButton("Home");
		btn_home.setBackground(new Color(72, 61, 139));
		btn_home.setForeground(new Color(127, 255, 0));
		btn_home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(pnl_home);
			}
		});

		// Add home and Logout button in menubar
		mbr_home.add(Box.createHorizontalGlue());

		JButton btn_sms_notification = new JButton("SMS Notifications");
		btn_sms_notification.setBackground(new Color(72, 61, 139));
		btn_sms_notification.setForeground(new Color(127, 255, 0));
		btn_sms_notification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sms_notifications sms_notify = new sms_notifications();
				sms_notify.setDefaultCloseOperation(HIDE_ON_CLOSE);
				sms_notify.setVisible(true);
			}
		});

		JButton btn_sms_template = new JButton("SMS Template");
		btn_sms_template.setBackground(new Color(72, 61, 139));
		btn_sms_template.setForeground(new Color(127, 255, 0));
		btn_sms_template.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sms_template sms_template = new sms_template();
				sms_template.setVisible(true);
			}
		});

		mbr_home.add(btn_sms_template);
		mbr_home.add(btn_sms_notification);

		mbr_home.add(btn_home);
		mbr_home.add(btn_logout);

		// home screen panel
		pnl_home = new JPanel();
		pnl_home.setBackground(new Color(0, 206, 209));
		pnl_home.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnl_home);
		pnl_home.setLayout(null);

		// Title Lable
		// TODO have to add icon 
		JLabel lbl_title = new JLabel("ABHI TRAVELS AND WHEELS");
		lbl_title.setForeground(new Color(210, 105, 30));
		lbl_title.setFont(new Font("URW Bookman L", Font.ITALIC, 32));
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_title.setBounds(432, 64, 466, 33);
		pnl_home.add(lbl_title);

		// Group box for trip  buttons
		JPanel pnl_sub_trip = new JPanel();
		pnl_sub_trip.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Trip", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(178, 34, 34)));
		pnl_sub_trip.setBackground(new Color(0, 206, 209));
		pnl_sub_trip.setBounds(117, 149, 428, 417);
		pnl_home.add(pnl_sub_trip);
		pnl_sub_trip.setLayout(null);

		// New trip window navigation button with click action listener
		JButton btn_new_trip = new JButton("Add Trip");
		btn_new_trip.setBounds(89, 64, 251, 42);
		pnl_sub_trip.add(btn_new_trip);
		btn_new_trip.setBorder(null);
		btn_new_trip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnl_trip_add obj_trip = new pnl_trip_add();
				setContentPane(obj_trip);
			}
		});

		btn_new_trip.setForeground(new Color(255, 255, 255));
		btn_new_trip.setBackground(new Color(219, 112, 147));


		// view trip window navigation button
		JButton btn_view_trip = new JButton("Show Trips Report");
		/*btn_view_trip.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			pnl_trip_view obj_trip_view = new pnl_trip_view();
			setContentPane(obj_trip_view);
			}
		});*/
		btn_view_trip.setBounds(89, 288, 251, 42);
		pnl_sub_trip.add(btn_view_trip);
		btn_view_trip.setForeground(Color.WHITE);
		btn_view_trip.setBackground(new Color(219, 112, 147));

		// update trip window navigation button
		JButton btn_update_trip = new JButton("Edit Trip");
		btn_update_trip.setBounds(89, 172, 251, 42);
		pnl_sub_trip.add(btn_update_trip);
		btn_update_trip.setForeground(Color.WHITE);
		btn_update_trip.setBackground(new Color(219, 112, 147));

		// Group box for Wheel buttons 
		JPanel pnl_sub_wheel = new JPanel();
		pnl_sub_wheel.setLayout(null);
		pnl_sub_wheel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Wheels", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(178, 34, 34)));
		pnl_sub_wheel.setBackground(new Color(0, 206, 209));
		pnl_sub_wheel.setBounds(789, 149, 428, 417);
		pnl_home.add(pnl_sub_wheel);

		// new wheel window navigation button with action listener
		JButton btn_new_wheel = new JButton("Add Wheel Bill");
		btn_new_wheel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pnl_wheels_add obj_wheels = new pnl_wheels_add();
				setContentPane(obj_wheels);
				obj_wheels.setFieldFocus();
			}
		});
		btn_new_wheel.setForeground(Color.WHITE);
		btn_new_wheel.setBorder(null);
		btn_new_wheel.setBackground(new Color(219, 112, 147));
		btn_new_wheel.setBounds(89, 64, 251, 42);
		pnl_sub_wheel.add(btn_new_wheel);

		// view wheel window navigation button
		JButton btn_view_wheel = new JButton("Show Wheels Report");
		btn_view_wheel.setForeground(Color.WHITE);
		btn_view_wheel.setBackground(new Color(219, 112, 147));
		btn_view_wheel.setBounds(89, 288, 251, 42);
		pnl_sub_wheel.add(btn_view_wheel);

		// update wheel window navigation button
		JButton btn_update_wheel = new JButton("Edit Wheel Bill");
		btn_update_wheel.setForeground(Color.WHITE);
		btn_update_wheel.setBackground(new Color(219, 112, 147));
		btn_update_wheel.setBounds(89, 172, 251, 42);
		pnl_sub_wheel.add(btn_update_wheel);

		setVisible(true);
	}

	public static JFrame get_main_frame(){
		return frame;
	}
}
