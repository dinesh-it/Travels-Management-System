package DataBaseInterface;

public class CustomerVehicle {
	private int id;
    private int customer_id;
    private String vehicle_number;
    private String vehicle_make;
    private int created_epoch;
    private int created_user_id;
    
    public CustomerVehicle(){}
    
	public CustomerVehicle(Integer customer_id, String vehicle_number, String make, Integer created_user_id,
			Integer created_epoch) {
		
		this.customer_id = customer_id;
		this.vehicle_number = vehicle_number;
		this.vehicle_make = make;
		this.created_user_id = created_user_id;
		this.created_epoch = created_epoch;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getVehicle_number() {
		return vehicle_number;
	}
	public void setVehicle_number(String vehicle_number) {
		this.vehicle_number = vehicle_number;
	}
	public String getVehicle_make() {
		return vehicle_make;
	}
	public void setVehicle_make(String vehicle_make) {
		this.vehicle_make = vehicle_make;
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

}
