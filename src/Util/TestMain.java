package Util;

public class TestMain {
	public static void main(String[] args) throws Exception{
		int epoch = Time.now();
		System.out.println("Current epoch = "+epoch);

		String today_string = Time.get_date(epoch);
		System.out.println("Today  = "+today_string);

		int today_mid_night_epoch = Time.get_epoch(today_string);
		System.out.println("Today midnight epoch = "+today_mid_night_epoch);

		String format = "dd/MM/yyyy HH:mm:ss";

		String today_string_formated = Time.get_date(epoch,format);
		System.out.println("Today  = "+today_string_formated);

		int epoch_from_string = Time.get_epoch(today_string_formated,format);
		System.out.println("Epoch from given date format  = "+epoch_from_string);

		String date_str = "22/09/2015 10:05 PM";
		int date_epoch = Time.get_epoch(date_str,"dd/MM/yyyy hh:mm a");
		System.out.println("Today  = " + date_str);
		System.out.println("Today  = " + date_epoch);       
		System.out.println("Today  = " + Time.get_date(date_epoch, "dd/MM/yyyy hh:mm a"));

		Logger.log.info("Log this information to a file");
		Logger.log.warning("I am warning you !");
		Logger.log.severe("OMG ! UnExpected Exception !");

	}
}
