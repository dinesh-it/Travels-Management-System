package DataBaseInterface;

import DataBaseInterface.SystemUser;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//import org.postgresql.*;

public class TestMain {

	private static TestMain user;
	private static SessionFactory factory;

	
	public static void main(String[] args) throws Exception{
		
		try{	
			Configuration configuration = new Configuration().configure();
				StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
				applySettings(configuration.getProperties());
				 factory = configuration.buildSessionFactory(builder.build());
			
			}catch (Throwable ex) {
				System.err.println("Failed to create sessionFactory object." + ex);
				throw new ExceptionInInitializerError(ex);
			}
			
		user = new TestMain();
		
		/* Add few users
		 * 
		 */
		
			/* Add few employee records in database */
			Integer user_id = user.addSystemUser("baskar","Baskar nallathambi","bas@gmail.com","12345","Admin");
			
			System.out.println("User Created with Id : "+user_id);
			
			
		
			/* List down all the employees */
			//user.listEmployees();
			/* Update employee's records */
			//ME.updateEmployee(empID1, 5000);
		
	}
	
	public Integer addSystemUser(String name, String username,String email,String password ,String role){
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
		e.printStackTrace();
		}finally {
		session.close();
		}
		return user_id;
		}
	
}
