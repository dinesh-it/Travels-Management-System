package DataBaseInterface;

public class Customer {
	private int id;
	private String name;
	private String mobile;
	private String email;
	private String address;
	private int created_user_id;
	private int created_epoch;

	public Customer(){}

	public Customer(String name, String mobile, String email, String address, Integer created_user_id,
			Integer created_epoch) {
		this.name = name;
		this.mobile = mobile;
		this.email =email;
		this.address =address;
		this.created_user_id= created_user_id;
		this.created_epoch = created_epoch;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
