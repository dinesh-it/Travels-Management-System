package DataBaseInterface;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import DataBaseInterface.*;
import Util.*;


// This class will provide database handler across the application
//
public class Handler {
    private static SessionFactory factory;
    private static 	Handler dbh = new Handler();

    public static Handler getInstance( ) {
        return dbh;
    }

    private Handler() {			
        try{	

            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                applySettings(configuration.getProperties());
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

    public Integer add_trip(int trip_date,int vehicle_id,circle_type trip_circle,double distance,double number_of_hours,double slab_amount,
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

    public Integer add_vehicle_service(Integer vehicle_id, Integer service_particulars_id, Integer quantity, double amount, int free_checkup_date,
            boolean is_sms_sent,String comments, Integer created_user_id, Integer created_epoch) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer vehicle_service_id = null;
        try{
            tx = session.beginTransaction();
            VehicleService vs =new VehicleService(vehicle_id,service_particulars_id,quantity,amount,comments,free_checkup_date,is_sms_sent, created_user_id,created_epoch);
            vehicle_service_id = (Integer) session.save(vs);
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




    // Table update methods 
    // Table select methods

    public List list_service_particulars( ){

        Session session = factory.openSession();
        List sp_list = null;
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


}
