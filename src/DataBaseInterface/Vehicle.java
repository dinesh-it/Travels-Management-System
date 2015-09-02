package DataBaseInterface;

public class Vehicle {

	private int id;
	private String  v_number;
	private String  make;
	private int  created_epoch;
	private int created_user_id;

	public Vehicle(){}

	public Vehicle(String vehicle_number, String make, Integer created_user_id, Integer created_epoch) {
		this.v_number = vehicle_number;
		this.make = make;
		this.created_user_id = created_user_id;
		this.created_epoch = created_epoch;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getV_number() {
		return v_number;
	}
	public void setV_number(String v_number) {
		this.v_number = v_number;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
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
