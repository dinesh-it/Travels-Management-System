package Util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;


public class Logger{
	//TODO Add file path at app level config
	private static String file = "Travels-Management-System.log";
	public static  java.util.logging.Logger log;  
	private static FileHandler fh;
	
	@SuppressWarnings("unused")
	private static Logger log_instance = new Logger();

	private Logger() {

		try {
			log =  java.util.logging.Logger.getLogger("Log");	  
			fh = new FileHandler(file,true);
			log.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			log.setUseParentHandlers(false);
			//log.info("Log instance created");
		} catch (SecurityException | IOException e1) {
			e1.printStackTrace();
		}
	}

}
