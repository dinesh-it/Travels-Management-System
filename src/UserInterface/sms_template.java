package UserInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class sms_template extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sms_template frame = new sms_template();
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
	public sms_template() {
		setTitle("Edit SMS Template");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 455, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnl_template = new JPanel();
		pnl_template.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnl_template.setBounds(12, 12, 429, 193);
		contentPane.add(pnl_template);
		pnl_template.setLayout(null);
		
		JLabel lbl_parameter = new JLabel("Parameter");
		lbl_parameter.setBounds(12, 12, 85, 15);
		pnl_template.add(lbl_parameter);
		
		JComboBox cmb_parameter = new JComboBox();
		cmb_parameter.setModel(new DefaultComboBoxModel(new String[] {"Customer Name", "Vehicle Number", "Vehicle Make", "Bill Number", "Due Date"}));
		cmb_parameter.setBounds(103, 7, 217, 24);
		pnl_template.add(cmb_parameter);
		
		JButton btn_parameter_add = new JButton("Add");
		btn_parameter_add.setBounds(332, 7, 85, 25);
		pnl_template.add(btn_parameter_add);
		
		JLabel lbl_template = new JLabel("Template");
		lbl_template.setBounds(12, 39, 70, 15);
		pnl_template.add(lbl_template);
		
		JTextArea txt_template_value = new JTextArea();
		txt_template_value.setLineWrap(true);
		txt_template_value.setText("[CustomerName] Your Vehicle number [VehicleNumber] [VehicleMake] is due for free checkup. ABHI WHEELS (wheel Alignment & Emission test center For Petrol &Diesel - Due date [DueDate]");
		txt_template_value.setBounds(12, 66, 405, 115);
		pnl_template.add(txt_template_value);
		
		JPanel pnl_actions = new JPanel();
		pnl_actions.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnl_actions.setBounds(12, 225, 429, 48);
		contentPane.add(pnl_actions);
		pnl_actions.setLayout(null);
		
		JButton btn_save_template = new JButton("Save Template");
		btn_save_template.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btn_save_template.setBounds(12, 12, 143, 25);
		pnl_actions.add(btn_save_template);
		
		JButton btn_apply_template = new JButton("Apply Template");
		btn_apply_template.setBounds(167, 12, 148, 25);
		pnl_actions.add(btn_apply_template);
		
		JButton btn_close = new JButton("Close");
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn_close.setBounds(327, 12, 90, 25);
		pnl_actions.add(btn_close);
	}
}
