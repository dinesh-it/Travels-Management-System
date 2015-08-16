package DataBaseInterface;

public class VehicleService {
	private int id;
    private int vehicle_id;
    private int service_id;
    private int quantity;
    private double amount;
    private int free_checkup_date;
    private String comments;
    private int created_epoch;
    private int created_user_id;
    private boolean is_sms_sent;
    
	public VehicleService(Integer vehicle_id, Integer service_particulars_id, Integer quantity, double amount,
			String comments, int free_checkup_date,boolean is_sms_sent, Integer created_user_id, Integer created_epoch) {
		this.vehicle_id = vehicle_id;
		this.service_id = service_particulars_id;
		this.quantity = quantity;
		this.amount = amount;
		this.comments = comments;
		this.free_checkup_date = free_checkup_date;
		this.is_sms_sent = is_sms_sent;
		this.created_user_id = created_user_id;
		this.created_epoch = created_epoch;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public int getService_id() {
		return service_id;
	}
	public void setService_id(int service_id) {
		this.service_id = service_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getFree_checkup_date() {
		return free_checkup_date;
	}
	public void setFree_checkup_date(int free_checkup_date) {
		this.free_checkup_date = free_checkup_date;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getCreated_epoch() {
		return created_epoch;
	}
	public void setCreated_epoch(int created_epoch) {
		this.created_epoch = created_epoch;
	}
	public int getCreated_user_id() {
		return created_user_id;
	}
	public void setCreated_user_id(int created_user_id) {
		this.created_user_id = created_user_id;
	}
	public boolean isIs_sms_sent() {
		return is_sms_sent;
	}
	public void setIs_sms_sent(boolean is_sms_sent) {
		this.is_sms_sent = is_sms_sent;		
	}

}
