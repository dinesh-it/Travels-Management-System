package DataBaseInterface;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import Util.*;
import UserInterface.ProgressBar;

// This class will provide database handler across the application
public class Handler {
	private static SessionFactory factory;
	private static 	Handler dbh = new Handler();

	public static Handler getInstance() {
		return dbh;
	}

	private Handler() {			
		try{	
			ProgressBar.showProgress("Initializing configurations....",20);
			Configuration configuration = new Configuration().configure();
			ProgressBar.showProgress("Initializing service registry builder....",30);
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
					applySettings(configuration.getProperties());
			ProgressBar.showProgress("Building service factory....",30);
			factory = configuration.buildSessionFactory(builder.build());
		}catch (Throwable ex) {
			Logger.log.severe("Failed to create sessionFactory object." + ex.toString());
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
		Logger.log.info("Handler Instance created !");
	}	

	//Table Insert Methods
	public Integer add_system_user(String name, String username,String email,String password ,String role){
		Session session = factory.openSession();
		Transaction tx = null;
		Integer user_id = null;
		try{
			tx = session.beginTransaction();
			SystemUser user = new SystemUser(name,username,email,password,role);
			user_id = (Integer) session.save(user);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			Logger.log.severe(e.toString());
			e.printStackTrace();
		}finally {
			session.close();
		}
		return user_id;
	}

	public Integer add_trip(int trip_date,int vehicle_id,String trip_circle,double distance,double number_of_hours,double slab_amount,
			double extra_hours,double extra_distance,double extra_amount,double driver_advance,double office_advance,double check_post_fee,
			double parking_toll_fee,double total_amount,String comments,int created_user_id,int created_epoch ){
		Session session = factory.openSession();
		Transaction tx = null;
		Integer trip_id = null;
		try{
			tx = session.beginTransaction();
			Trip trip =new Trip(trip_date,vehicle_id,trip_circle,distance,number_of_hours,slab_amount,extra_hours,extra_distance,
					extra_amount,driver_advance,office_advance,check_post_fee,parking_toll_fee,total_amount,comments,created_user_id,created_epoch );

			trip_id = (Integer) session.save(trip);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			Logger.log.severe(e.toString());
			e.printStackTrace();
		}finally {
			session.close();
		}
		return trip_id;
	}

	public Integer add_customer(String name, String mobile, String email, String address, Integer created_user_id ,Integer created_epoch) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer customer_id = null;
		try{
			tx = session.beginTransaction();
			Customer customer =new Customer(name,mobile,email,address,created_user_id,created_epoch);
			customer_id = (Integer) session.save(customer);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			Logger.log.severe(e.toString());
		}finally {
			session.close();
		}
		return customer_id;

	}

	public Integer add_customer_vehicle(Integer customer_id, String vehicle_number, String make, Integer created_user_id,
			Integer created_epoch) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer customer_vehicle_id = null;
		try{
			tx = session.beginTransaction();
			CustomerVehicle customer_vehicle =new CustomerVehicle(customer_id,vehicle_number,make,created_user_id,created_epoch);
			customer_vehicle_id = (Integer) session.save(customer_vehicle);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			Logger.log.severe(e.toString());
		}finally {
			session.close();
		}
		return customer_vehicle_id;		
	}

	public Integer add_trip_slab(int time, int distance, int created_user_id, int created_epoch) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer  trip_slab_id = null;
		try{
			tx = session.beginTransaction();
			TripSlab trip_slab =new TripSlab(time,distance,created_user_id,created_epoch);
			trip_slab_id = (Integer) session.save(trip_slab);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			Logger.log.severe(e.toString());
		}finally {
			session.close();
		}
		return trip_slab_id;
	}

	public Integer add_service_particulars(String service_name, boolean is_multiple, boolean is_free_service,boolean is_sms_sent, Integer created_user_id, Integer created_epoch) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer service_particulars_id = null;
		try{
			tx = session.beginTransaction();
			ServiceParticulars sp =new ServiceParticulars(service_name,is_multiple,is_free_service,created_user_id,created_epoch);
			service_particulars_id = (Integer) session.save(sp);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			Logger.log.severe(e.toString());
		}finally {
			session.close();
		}
		return service_particulars_id;
	}

	public Integer add_vehicle(String vehicle_number, String make, Integer created_user_id,
			Integer created_epoch) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer vehicle_id = null;
		try{
			tx = session.beginTransaction();
			Vehicle vehicle =new Vehicle(vehicle_number,make,created_user_id,created_epoch);
			vehicle_id = (Integer) session.save(vehicle);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			Logger.log.severe(e.toString());
		}finally {
			session.close();
		}
		return vehicle_id;		

	}

	public Integer add_service_detail(Integer service_particulars_id,Integer service_bill_id, Integer quantity, double amount){
		Session session = factory.openSession();
		Transaction tx = null;
		Integer vehicle_service_id = null;
		try{
			tx = session.beginTransaction();
			ServiceDetail service_detail =new ServiceDetail(service_particulars_id,service_bill_id,quantity,amount);
			vehicle_service_id = (Integer) session.save(service_detail);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			Logger.log.severe(e.toString());
		}finally {
			session.close();
		}
		return vehicle_service_id;			

	}

	public Integer add_sms_template(String template, Integer user_id, int created_epoch) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer template_id = null;
		try{
			tx = session.beginTransaction();
			SMSTemplate sms_template = new SMSTemplate(template,user_id,created_epoch);
			template_id = (Integer) session.save(sms_template);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			Logger.log.severe(e.toString());
		}finally {
			session.close();
		}
		return template_id;
	}

	public Integer add_sms_queue(int service_bill_id, String mobile_number, String msg, Integer user_id, int created_epoch) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer sms_queue_id = null;
		try{
			tx = session.beginTransaction();
			SMSQueue sms_queue = new SMSQueue(service_bill_id,mobile_number,msg,user_id,created_epoch);
			sms_queue_id = (Integer) session.save(sms_queue);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			Logger.log.severe(e.toString());
		}finally {
			session.close();
		}
		return sms_queue_id;
	}

	public Integer add_template_parameter(String name, String value_ref, Integer user_id, int created_epoch) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer temp_param_id = null;
		try{
			tx = session.beginTransaction();
			TemplateParameter temp_param = new TemplateParameter(name,value_ref,user_id,created_epoch);
			temp_param_id = (Integer) session.save(temp_param);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			Logger.log.severe(e.toString());
		}finally {
			session.close();
		}
		return temp_param_id;
	}

	public Integer add_service_bill(Integer vehicle_id, String comment, double amt, Integer user_id, int created_epoch) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer service_bill_id = null;
		try{
			tx = session.beginTransaction();
			ServiceBill bill = new ServiceBill(vehicle_id,comment,amt,user_id,created_epoch);
			service_bill_id = (Integer) session.save(bill);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			Logger.log.severe(e.toString());
		}finally {
			session.close();
		}
		return service_bill_id;
	}



	// Table update methods 
	public void update(Object obj){
		Session sess = factory.openSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			sess.update(obj);
			tx.commit();
		}
		catch (Exception e) {
			if (tx!=null) tx.rollback();
			Logger.log.severe(e.toString());
		}
		finally {
			sess.close();
		}
	}

	public void saveOrUpdate(Object obj){
		Session sess = factory.openSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			sess.saveOrUpdate(obj);
			tx.commit();
		}
		catch (Exception e) {
			if (tx!=null) tx.rollback();
			Logger.log.severe(e.toString());
		}
		finally {
			sess.close();
		}
	}

	// Table select methods
	public List<ServiceParticulars> list_service_particulars( ){

		Session session = factory.openSession();
		List<ServiceParticulars> sp_list = null;
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			sp_list = session.createQuery("FROM ServiceParticulars").list();
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			Logger.log.severe(e.toString());
		}finally {
			session.close();
		}
		return sp_list;
	}

	public CustomerVehicle getVehicle(String vehicle_no){
		Session session = factory.openSession();
		CustomerVehicle cv = null;
		try{
			cv = (CustomerVehicle)session.createQuery("FROM CustomerVehicle WHERE vehicle_number = '" + vehicle_no + "'").uniqueResult();
		}catch (HibernateException e) {
			e.printStackTrace();
			Logger.log.severe(e.toString());
		}finally {
			session.close();
		}

		return cv;
	}

	public Customer getCustomer(int id){
		Session session = factory.openSession();
		Customer cust = null;
		try{
			cust = (Customer)session.createQuery("FROM Customer WHERE id = '" + id + "'").uniqueResult();
		}catch (HibernateException e) {
			e.printStackTrace();
			Logger.log.severe(e.toString());
		}finally {
			session.close();
		}

		return cust;
	}

	public int get_next_bill_id(){
		Session session = factory.openSession();
		int last_id = 0;
		try{
			last_id = (int)session.createQuery("SELECT COALESCE(MAX(id), 0) FROM ServiceBill").uniqueResult();
		}catch (HibernateException e) {
			e.printStackTrace();
			Logger.log.severe(e.toString());
		}finally {
			session.close();
		}

		return last_id + 1;
	}

	public String get_sms_template(){
		Session session = factory.openSession();
		String template = null;
		try{
			// Get the latest template
			template = (String)session.createQuery("SELECT template FROM SMSTemplate ORDER BY id DESC").setMaxResults(1).uniqueResult();
		}catch (HibernateException e) {
			e.printStackTrace();
			Logger.log.severe(e.toString());
		}finally {
			session.close();
		}

		return template;
	}

	public List<TemplateParameter> get_template_params_list(){
		List<TemplateParameter> params = null;
		Session session = factory.openSession();
		try{
			params = session.createQuery("FROM TemplateParameter").list();
		}catch (HibernateException e) {
			e.printStackTrace();
			Logger.log.severe(e.toString());
		}finally {
			session.close();
		}
		return params;
	}

	public ServiceParticulars get_service_particular(int id){
		Session session = factory.openSession();
		ServiceParticulars service_particular = null;
		try{
			service_particular = (ServiceParticulars)session.createQuery("FROM ServiceParticulars WHERE id = '" + id + "'").uniqueResult();
		}catch (HibernateException e) {
			e.printStackTrace();
			Logger.log.severe(e.toString());
		}finally {
			session.close();
		}

		return service_particular;
	}

	public String get_value(String table_name, String field_name, Integer primary_value){
		Object obj = null;
		Session session = factory.openSession();
		try{
			// Get the latest template
			obj = session.createQuery("SELECT " + field_name + " FROM " + table_name + " WHERE id = " + primary_value).uniqueResult();
		}catch (HibernateException e) {
			e.printStackTrace();
			Logger.log.severe(e.toString());
		}finally {
			session.close();
		}
		if(obj != null) return obj.toString();
		return "";
	}

	public List<?> get_sms_queue() {
		System.out.println("Geeting queue");

		Session session = factory.openSession();
		List<?> data = null;
		try{

			data = session.createQuery("FROM SMSQueue WHERE sent = 'f'").list();
		}catch (HibernateException e) {
			e.printStackTrace();
			Logger.log.severe(e.toString());
		}finally {
			session.close();
		}
		return data;
	}

	public List<Object[]> get_single_bill_detail(int bill_id){
		Session session = factory.openSession();

		List<Object[]> result = null;
		try{
			// Query have to be optimize (instead of get all columns, should fetch required columns)
			//fetching particular columns throws error. 
			SQLQuery query = session.createSQLQuery(
					"SELECT *" 
							+ " FROM service_particulars as sp "
							+ " JOIN service_detail as sd ON sp.id = sd.service_particular_id "
							+ " JOIN service_bill as sb ON sd.service_bill_id = sb.id"
							+ " JOIN customer_vehicle as veh ON sb.vehicle_id = veh.id"
							+ " JOIN customer as cus ON veh.customer_id = cus.id"
							+ " WHERE sb.id = " + bill_id + " OR sb.id IS NULL"
					);
			query.addEntity(ServiceParticulars.class);
			query.addEntity(ServiceDetail.class);
			query.addEntity(ServiceBill.class);
			query.addEntity(CustomerVehicle.class);
			query.addEntity(Customer.class);

			result = query.list();

		}catch (HibernateException e) {
			e.printStackTrace();
			Logger.log.severe(e.toString());
		}finally {
			session.close();
		}
		return result;
	}
}
