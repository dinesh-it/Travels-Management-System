package DataBaseInterface;
//import DataBaseInterface.circle_type;

public class Trip {
	private int id;
	private int trip_date;
	private int vehicle_id;
	// private DataBaseInterface.circle_type trip_circle;
	private String trip_circle; 
	private double distance;
	private double number_of_hours;
	private double slab_amount;
	private double extra_hours;
	private double extra_distance;
	private double extra_amount;
	private double driver_advance;
	private double office_advance;
	private double check_post_fee;
	private double parking_toll_fee;
	private double total_amount;
	private String comments;
	private int created_user_id;
	private int created_epoch;
	private int updated_user_id;
	private int updated_epoch;

	public Trip() {}
	
	public Trip(int trip_date, int vehicle_id, String trip_circle, double distance, double number_of_hours,
			double slab_amount, double extra_hours, double extra_distance, double extra_amount,
			double driver_advance, double office_advance, double check_post_fee, double parking_toll_fee,
			double total_amount, String comments, int created_user_id, int created_epoch) {
		this.trip_date = trip_date;
		this.vehicle_id =vehicle_id;
		this.trip_circle = trip_circle;
		this.distance =  distance;
		this.number_of_hours = number_of_hours;
		this.slab_amount = slab_amount;
		this.extra_hours = extra_hours;
		this.extra_distance = extra_distance;
		this.extra_amount = extra_amount;
		this.driver_advance =  driver_advance;
		this.office_advance = office_advance;
		this.check_post_fee = check_post_fee;
		this.parking_toll_fee = parking_toll_fee;
		this.total_amount = total_amount;
		this.comments = comments;
		this.created_user_id = created_user_id;
		this.created_epoch = created_epoch;

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTrip_date() {
		return trip_date;
	}
	public void setTrip_date(int trip_date) {
		this.trip_date = trip_date;
	}
	public int getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getNumber_of_hours() {
		return number_of_hours;
	}
	public void setNumber_of_hours(double number_of_hours) {
		this.number_of_hours = number_of_hours;
	}
	public double getSlab_amount() {
		return slab_amount;
	}
	public void setSlab_amount(double slab_amount) {
		this.slab_amount = slab_amount;
	}
	public double getExtra_hours() {
		return extra_hours;
	}
	public void setExtra_hours(double extra_hours) {
		this.extra_hours = extra_hours;
	}
	public double getExtra_distance() {
		return extra_distance;
	}
	public void setExtra_distance(double extra_distance) {
		this.extra_distance = extra_distance;
	}
	public double getExtra_amount() {
		return extra_amount;
	}
	public void setExtra_amount(double extra_amount) {
		this.extra_amount = extra_amount;
	}
	public double getDriver_advance() {
		return driver_advance;
	}
	public void setDriver_advance(double driver_advance) {
		this.driver_advance = driver_advance;
	}
	public double getOffice_advance() {
		return office_advance;
	}
	public void setOffice_advance(double office_advance) {
		this.office_advance = office_advance;
	}
	public double getCheck_post_fee() {
		return check_post_fee;
	}
	public void setCheck_post_fee(double check_post_fee) {
		this.check_post_fee = check_post_fee;
	}
	public double getParking_toll_fee() {
		return parking_toll_fee;
	}
	public void setParking_toll_fee(double parking_toll_fee) {
		this.parking_toll_fee = parking_toll_fee;
	}
	public double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	public int getUpdated_user_id() {
		return updated_user_id;
	}
	public void setUpdated_user_id(int updated_user_id) {
		this.updated_user_id = updated_user_id;
	}
	public int getUpdated_epoch() {
		return updated_epoch;
	}
	public void setUpdated_epoch(int updated_epoch) {
		this.updated_epoch = updated_epoch;
	}
	public String getTrip_circle() {
		return trip_circle;
	}
	public void setTrip_circle(String trip_circle) {
		this.trip_circle = trip_circle;
	}

}
