package Util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formatter {

	// Supports CAR 9324, MYS 123, KA 48 S 4957, KA 48 4957
	public static String VEHICLE_PATTERN = "^([A-Za-z]{2,3})[-\\s]?(\\d{1,4})[-\\s]?([A-Za-z]{0,3})[-\\s]?(\\d{1,4})$";
	public static String MOBILE_NUMBER_PATTERN = "^(\\+91)?\\d{10}$";
	public static String DATE_PATTERN = "^\\d{1,2}/\\d{1,2}/\\d{4}$";
	public static String TIME_PATTERN = "^\\d{1,2}:\\d{1,2}\\s[AaPp][Mm]$";

	public static String getCurrencyFormat(double value){
		NumberFormat format =  NumberFormat.getInstance();
		if (format instanceof DecimalFormat) {
			((DecimalFormat) format).setDecimalSeparatorAlwaysShown(true);
			((DecimalFormat) format).applyPattern("##,###.00 \u20B9");
		}
		return format.format(value);
	}

	public static String getFormattedVehicleNo(String vehicle_no){
		String v_number = null;
		Pattern p = Pattern.compile(VEHICLE_PATTERN);
		Matcher m = p.matcher(vehicle_no);
		if (m.find()) {
			v_number = m.replaceFirst("$1 $2 $3 $4");
		}
		else{
			return vehicle_no;
		}

		return v_number;
	}
	
	public static String toCamelCase(final String init) {
	    if (init==null)
	        return null;

	    final StringBuilder ret = new StringBuilder(init.length());

	    for (final String word : init.split(" ")) {
	        if (!word.isEmpty()) {
	            ret.append(word.substring(0, 1).toUpperCase());
	            ret.append(word.substring(1).toLowerCase());
	        }
	        if (!(ret.length()==init.length()))
	            ret.append(" ");
	    }

	    return ret.toString();
	}
}
