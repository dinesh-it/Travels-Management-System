package DataBaseInterface;

public class SystemUser {
	private int id;
	private String  name;
	private String  user_name;
	private String  email;
	private String  password;
	private String  role;
	
	public SystemUser(String user,String username,String email,String password,String role) {
		this.name = user;
		this.setUser_name(username);
		this.email =email;
		this.password=password;
		this.role=role;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	
	

}
