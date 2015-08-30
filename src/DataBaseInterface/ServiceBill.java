package DataBaseInterface;

public class ServiceBill {
	private int id;
	private int vehicle_id;
	private String comments;
	private double total_amt;
	private int free_checkup_date;
	private boolean free_checkup_completed;
	private int created_epoch;
	private int created_user_id;
	
	public ServiceBill() {}

	public ServiceBill(Integer vehicle_id2, String comment2, double amt, Integer user_id, int created_epoch2) {
		this.setVehicle_id(vehicle_id2);
		this.setComments(comment2);
		this.setTotal_amt(amt);
		this.setCreated_user_id(user_id);
		this.setCreated_epoch(created_epoch2);
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public double getTotal_amt() {
		return total_amt;
	}

	public void setTotal_amt(double total_amt) {
		this.total_amt = total_amt;
	}

	public int getFree_checkup_date() {
		return free_checkup_date;
	}

	public void setFree_checkup_date(int free_checkup_date) {
		this.free_checkup_date = free_checkup_date;
	}

	public boolean isFree_checkup_completed() {
		return free_checkup_completed;
	}

	public void setFree_checkup_completed(boolean free_checkup_completed) {
		this.free_checkup_completed = free_checkup_completed;
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
	};	

}
