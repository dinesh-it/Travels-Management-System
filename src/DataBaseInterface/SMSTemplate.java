package DataBaseInterface;

public class SMSTemplate {

	private int id;
	private String template;
	private int created_user_id;
	private int created_epoch;

	public SMSTemplate(){};


	public SMSTemplate(String template, Integer user_id, int created_epoch) {
		this.template = template;
		this.created_user_id = user_id;
		this.created_epoch = created_epoch;

	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
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
