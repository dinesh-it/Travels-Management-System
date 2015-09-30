package Util;
import java.text.SimpleDateFormat;

public class Time {

	public static String date_format = "dd/MM/yyyy";

	public static int now() {
		int epoch = 0;
		try{
			epoch = (int) (System.currentTimeMillis()/1000);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return epoch;
	}

	public static String get_date(int epoch){
		String date_string = null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(date_format);
			date_string = sdf.format(((long)epoch * 1000));

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return date_string;
	}

	public static String get_date(int epoch,String format){
		String date_string = null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date_string = sdf.format(((long)epoch * 1000));

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return date_string;
	}


	public static int get_epoch(String date_string){
		int epoch = 0;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(date_format);
			epoch = (int) (sdf.parse(date_string).getTime()/1000);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return epoch;
	}

	public static int get_epoch(String date_string,String format){
		int epoch = 0;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			epoch = (int) (sdf.parse(date_string).getTime()/1000);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return epoch;
	}
}
