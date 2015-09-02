package DataBaseInterface;

public class ServiceParticulars {
	private int id;
	private String service_name;
	private boolean is_multiple;
	private boolean is_free_service;
	private int created_epoch;
	private int created_user_id;


	public ServiceParticulars(){}

	public ServiceParticulars(String service_name, boolean is_multiple, boolean is_free_service,
			Integer created_user_id, Integer created_epoch) {
		this.service_name = service_name;
		this.is_free_service = is_free_service;
		this.is_multiple = is_multiple;

		this.created_epoch = created_epoch;
		this.created_user_id = created_user_id;

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public boolean isIs_multiple() {
		return is_multiple;
	}
	public void setIs_multiple(boolean is_multiple) {
		this.is_multiple = is_multiple;
	}
	public boolean isIs_free_service() {
		return is_free_service;
	}
	public void setIs_free_service(boolean is_free_service) {
		this.is_free_service = is_free_service;
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
