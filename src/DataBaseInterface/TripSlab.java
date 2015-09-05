package DataBaseInterface;

public class TripSlab {
	private int id;
	private double time;
	private double distance;
	private int created_user_id;
	private int created_epoch;

	public TripSlab() {}
	
	public TripSlab(int time, int distance, int created_user_id, int created_epoch) {
		this.time = time;
		this.distance = distance;
		this.created_user_id = created_user_id;
		this.created_epoch =  created_epoch;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
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


}
