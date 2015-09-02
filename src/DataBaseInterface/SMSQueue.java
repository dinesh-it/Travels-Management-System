package DataBaseInterface;

public class SMSQueue {

	private int id;
	private int service_bill_id;
	private String mobile_number;
	private String message;
	private boolean sent;
	private int created_user_id;
	private int created_epoch;
	private int updated_user_id;
	private int updated_epoch;
	private int sent_epoch;

	public SMSQueue() {};

	public SMSQueue(int service_bill_id, String mobile_number, String msg, Integer user_id, int created_epoch) {
		this.service_bill_id = service_bill_id;
		this.mobile_number = mobile_number;
		this.message = msg;
		this.created_user_id = user_id;
		this.created_epoch = created_epoch;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getService_bill_id() {
		return service_bill_id;
	}
	public void setService_bill_id(int service_bill_id) {
		this.service_bill_id = service_bill_id;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSent() {
		return sent;
	}
	public void setSent(boolean sent) {
		this.sent = sent;
	}
	public int getCreated_user_id() {
		return created_user_id;
	}
	public void setCreated_user_id(int created_user_id) {
		this.created_user_id = created_user_id;
	}
	public int getCreated_epoch() {
		return created_epoch;
	}
	public void setCreated_epoch(int created_epoch) {
		this.created_epoch = created_epoch;
	}
	public int getUpdated_epoch() {
		return updated_epoch;
	}
	public void setUpdated_epoch(int updated_epoch) {
		this.updated_epoch = updated_epoch;
	}
	public int getSent_epoch() {
		return sent_epoch;
	}
	public void setSent_epoch(int sent_epoch) {
		this.sent_epoch = sent_epoch;
	}
	public int getUpdated_user_id() {
		return updated_user_id;
	}
	public void setUpdated_user_id(int updated_user_id) {
		this.updated_user_id = updated_user_id;
	}


}
