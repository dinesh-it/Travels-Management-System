package Util;

import java.net.UnknownHostException;

import DataBaseInterface.Handler;
import DataBaseInterface.SMSQueue;

public class SMSDispatcher implements Runnable {

	private SMSQueue sms_global;
	private static Handler dbh = Handler.getInstance();
	private Thread t;

	public SMSDispatcher(SMSQueue sms){
		sms_global = sms;
	}

	@Override
	public void run() {
		int id = sms_global.getId();
		try {
			System.out.println("In thread for sms " + sms_global.getId());
			SendUnicodeSms sms_sender = new SendUnicodeSms();
			String to = sms_global.getMobile_number();
			String msg = sms_global.getMessage();
			if(sms_sender.send_message(to, msg)){
				sms_global.setSent(true);
				sms_global.setSent_epoch(Time.now());

				//update this queue
				dbh.update(sms_global);
			}
			else{
				Logger.log.severe("Error while sending SMS,send sms returned false");
			}
		}catch(UnknownHostException uke){
			Logger.log.severe("Error while triggering send SMS id " + id + ", Unknownhost exception " + uke.getMessage());
		}catch(Exception e1){
			Logger.log.severe("Error while triggering send SMS id " + id + ",Error: " + e1.getMessage());
		}		
	}

	public void send_sms(){
		if(t == null){
			t = new Thread(this, "sms_" + sms_global.getId());
			t.start();
		}
	}

}
