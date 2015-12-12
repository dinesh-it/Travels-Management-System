package UserInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.event.ActionListener;

import Util.ButtonColumn;
import Util.Logger;
import Util.Time;
import DataBaseInterface.*;

public class pnl_edit_service_particulars extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int window_width,window_height;

	private Handler dbh = Handler.getInstance();

	private JTable tbl_service_particulars;
	private DefaultTableModel mdl_service_particulars;
	private JButton btn_add_sp;

	ArrayList<String> sp_names = new ArrayList<String>();

	public pnl_edit_service_particulars (int width, int height) {

		setBackground(Color.WHITE);

		setSize(width,height - 30);

		window_width = this.getWidth();
		window_height = this.getHeight();

		setLayout(null);

		btn_add_sp = new JButton("Add New Particular");
		btn_add_sp.setBounds(window_width-225, 75, 200, 40);
		add(btn_add_sp);
		btn_add_sp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(sp_names.size() <= 14) {

					String  sp_name = JOptionPane.showInputDialog("Please Enter the service name");
					sp_name = sp_name.trim();
					if (!sp_name.isEmpty()) {

						if(! sp_names.contains(sp_name)) {

							Integer sp_id = dbh.add_service_particulars(sp_name, true, false, 1, Time.now());
							if( sp_id != null) {

								Object[] data = {sp_id, sp_name , false, false, "Update"};

								mdl_service_particulars.addRow(data);

								sp_names.add(sp_name);

								Logger.log.info(sp_name + " service particular added to the table...!");

								JOptionPane.showMessageDialog(tbl_service_particulars, "Service Particular added successfully");

							} else {
								JOptionPane.showMessageDialog(tbl_service_particulars, "Error occured while adding");
								Logger.log.severe("Error occured while adding serive particular " + sp_name );
							}
						} else {
							JOptionPane.showMessageDialog(tbl_service_particulars, "Service Particular already exist");
						}
					}
				} else  {
					JOptionPane.showMessageDialog(tbl_service_particulars, "You can add only 14 Service particulars, more info contact DevTeam");
				}
			}
		});

		tbl_service_particulars =  new JTable();
		tbl_service_particulars.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tbl_service_particulars.setRowSelectionAllowed(false);
		mdl_service_particulars = new DefaultTableModel(){

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int col) {
				if(col == 0)
					return false;
				else 
					return true;
			}

			public Class<?> getColumnClass(int c) {

				if(this.getRowCount() >1) {
					return getValueAt(0, c).getClass();
				} else {
					return new String().getClass();
				}
			}
		};

		String[] columns =  {
				"ID",
				"Particulars",
				"Is_Multiple",
				"Is_Free_Service",
				"Update"
		};

		mdl_service_particulars.setColumnIdentifiers(columns);

		List<?> sp_list = dbh.list_service_particulars();

		for (Iterator<?> iterator = sp_list.iterator(); iterator.hasNext();){

			ServiceParticulars sp = (ServiceParticulars) iterator.next();
			Object[] o = new Object[5];

			o[0] = sp.getId();
			o[1] = sp.getService_name();

			sp_names.add(o[1].toString());

			o[2]= sp.isIs_multiple();

			o[3] =sp.isIs_free_service();
			o[4] ="Update";

			mdl_service_particulars.addRow(o);
		}


		tbl_service_particulars.setModel(mdl_service_particulars);
		tbl_service_particulars.setFont(new Font("Arial", Font.PLAIN, 14));
		tbl_service_particulars.setForeground(Color.DARK_GRAY);
		tbl_service_particulars.setGridColor(Color.BLUE);
		tbl_service_particulars.setBackground(Color.WHITE);
		tbl_service_particulars.setRowMargin(5);
		tbl_service_particulars.setRowHeight(50);


		Action update_particulars  = new AbstractAction() {


			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				int row   = Integer.valueOf( e.getActionCommand() );
				int sp_id = (int) tbl_service_particulars.getValueAt(row, 0); 
				ServiceParticulars sp_obj = dbh.get_service_particular(sp_id);
				if(sp_obj != null ){
					String sp_name = tbl_service_particulars.getValueAt(row, 1).toString();
					sp_name = sp_name.trim();
					if( !sp_name.isEmpty() ) {
						sp_obj.setService_name(sp_name);
						boolean is_multiple = (boolean)tbl_service_particulars.getValueAt(row, 2);
						boolean is_free_service = (boolean)tbl_service_particulars.getValueAt(row, 3);
						sp_obj.setIs_multiple(is_multiple);
						sp_obj.setIs_free_service(is_free_service);


						dbh.update(sp_obj);

						if( ! sp_names.contains(sp_name)) {

							Logger.log.info("service particular "+ sp_names.get(row) +" renamed as " + sp_name);

							sp_names.remove(row);
							sp_names.add(row, sp_name);

						}

						Logger.log.info("service particular id " + sp_id + " updated  with following details sp_name =" + sp_name + " is_multiple = " + is_multiple +  " is_free_service =  " + is_free_service);

						JOptionPane.showMessageDialog(tbl_service_particulars, "Service particular updated successfully");

					} else {
						JOptionPane.showMessageDialog(tbl_service_particulars, "Service Name cannot be empty");
					}
				} else {
					Logger.log.severe("Service Particular object not found for id " + sp_id);
				}

			}
		};

		@SuppressWarnings("unused")
		ButtonColumn buttonColumn = new ButtonColumn(tbl_service_particulars, update_particulars, 4);

		JTableHeader  obj_jtable_header = tbl_service_particulars.getTableHeader();
		obj_jtable_header.setBackground(Color.BLUE);
		obj_jtable_header.setForeground(Color.WHITE);
		obj_jtable_header.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));

		JScrollPane jsp_service_details =new JScrollPane(tbl_service_particulars);
		jsp_service_details.setForeground(new Color(102, 153, 255));
		jsp_service_details.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(204, 51, 102)));
		jsp_service_details.setBackground(new Color(204, 102, 153));
		jsp_service_details.setBounds(20, 120, window_width - 40 , window_height - 220);
		add(jsp_service_details);

		int tbl_width = jsp_service_details.getWidth();
		tbl_service_particulars.getColumn("ID").setMaxWidth((tbl_width/100)*10);
		tbl_service_particulars.getColumn("Particulars").setMaxWidth((tbl_width/100)*40);
		tbl_service_particulars.getColumn("Is_Multiple").setMaxWidth((tbl_width/100)*20);
		tbl_service_particulars.getColumn("Is_Free_Service").setMaxWidth((tbl_width/100)*20);
		tbl_service_particulars.getColumn("Update").setMaxWidth((tbl_width/100)*15);

	}

}
