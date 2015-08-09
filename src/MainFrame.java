import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
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


public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	
	private JPanel pnl_home;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		
		// Frame Properties
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Travals and Wheels");
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
		JButton btn_new_trip = new JButton("New");
		btn_new_trip.setBounds(89, 64, 251, 42);
		pnl_sub_trip.add(btn_new_trip);
		btn_new_trip.setBorder(null);
		btn_new_trip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnl_trip obj_trip = new pnl_trip();
				setContentPane(obj_trip);
			}
		});
		
		btn_new_trip.setForeground(new Color(255, 255, 255));
		btn_new_trip.setBackground(new Color(219, 112, 147));

		
		// view trip window navigation button
		JButton btn_view_trip = new JButton("View");
		btn_view_trip.setBounds(89, 288, 251, 42);
		pnl_sub_trip.add(btn_view_trip);
		btn_view_trip.setForeground(Color.WHITE);
		btn_view_trip.setBackground(new Color(219, 112, 147));
		
		// update trip window navigation button
		JButton btn_update_trip = new JButton("Update");
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
		JButton btn_new_wheel = new JButton("New");
		btn_new_wheel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pnl_wheels obj_wheels = new pnl_wheels();
				setContentPane(obj_wheels);
			}
		});
		btn_new_wheel.setForeground(Color.WHITE);
		btn_new_wheel.setBorder(null);
		btn_new_wheel.setBackground(new Color(219, 112, 147));
		btn_new_wheel.setBounds(89, 64, 251, 42);
		pnl_sub_wheel.add(btn_new_wheel);
		
		// view wheel window navigation button
		JButton btn_view_wheel = new JButton("View");
		btn_view_wheel.setForeground(Color.WHITE);
		btn_view_wheel.setBackground(new Color(219, 112, 147));
		btn_view_wheel.setBounds(89, 288, 251, 42);
		pnl_sub_wheel.add(btn_view_wheel);
		
		// update wheel window navigation button
		JButton btn_update_wheel = new JButton("Update");
		btn_update_wheel.setForeground(Color.WHITE);
		btn_update_wheel.setBackground(new Color(219, 112, 147));
		btn_update_wheel.setBounds(89, 172, 251, 42);
		pnl_sub_wheel.add(btn_update_wheel);
		
	}
}
