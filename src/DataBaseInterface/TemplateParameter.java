package DataBaseInterface;

public class TemplateParameter {

	private int id;
	private String name;
	private String value_ref;
	private int created_user_id;
	private int created_epoch;

	public TemplateParameter(){}

	public TemplateParameter(String name, String value_ref, Integer user_id, int created_epoch) {
		this.name = name;
		this.value_ref = value_ref;
		this.created_user_id = user_id;
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

	public String getValue_ref() {
		return value_ref;
	}

	public void setValue_ref(String value_ref) {
		this.value_ref = value_ref;
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
	};

}
