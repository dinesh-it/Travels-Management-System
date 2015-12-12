package DataBaseInterface;
import DataBaseInterface.Handler;

public class TestMain {

	private static Handler dbh;

	public static void main(String[] args) throws Exception{

		dbh = Handler.getInstance();
		Integer user_id = dbh.add_system_user("baskar","Baskar nallathambi","bas@gmail.com","12345","Admin");
		System.out.println("User Created with Id : "+user_id);

		Integer trip_slab_id = dbh.add_trip_slab(100,100,user_id,111);
		System.out.println("Trip Slab Created with Id : "+trip_slab_id);

		Integer trip_id = dbh.add_trip(111,123, "Local", 1.0, 1.0,100.0,0.0 ,0.0,0.0,0.0,0.0,0.0,0.0,100.0, "cocmments",user_id,1);
		System.out.println("Trip Created with Id : "+trip_id);

		Integer customer_id = dbh.add_customer("name","+911232","bas@bas.com","address 1",user_id,111);
		System.out.println("Customer Created with Id : "+customer_id);

		Integer customer_vehicle_id = dbh.add_customer_vehicle(customer_id,"TN 47","TATA",user_id,111);
		System.out.println("Customer vehicle Created with Id : "+customer_vehicle_id);

		Integer service_particulars_id = dbh.add_service_particulars("service_name",true,true,user_id,111);
		System.out.println("SP Created with Id : "+ service_particulars_id);

		Integer vehicle_id = dbh.add_vehicle("TN 45","MARUTHI", user_id, 111);
		System.out.println("Vehicle Created with Id : "+vehicle_id);

		Integer service_bill_id = dbh.add_service_bill(vehicle_id,"comment",100.0,user_id,111);
		System.out.println("Service bill Created with Id : " + service_bill_id);


		Integer service_detail_id = dbh.add_service_detail(service_particulars_id,service_bill_id,1,100.0);
		System.out.println("Vehicle Service Created with Id : "+ service_detail_id);

		Integer sms_temp_id = dbh.add_sms_template("Template",user_id,111);
		System.out.println("SMS Template Created with Id : " + sms_temp_id);

		Integer sms_queue_id = dbh.add_sms_queue(service_bill_id,"+0123456789","Hello World",user_id,111);
		System.out.println("SMS Queue entry Created with Id : " + sms_queue_id);

		Integer temp_param_id = dbh.add_template_parameter("CustomerName","Customer.name",user_id,111);
		System.out.println("Template Parameter Created with Id : " + temp_param_id);

		System.exit(0);

	}	
}
