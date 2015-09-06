package DataBaseInterface;

public class ServiceDetail {
	private int id;
	private int service_particular_id;
	private int service_bill_id;
	private int quantity;
	private double amount;

	public ServiceDetail(){}

	public ServiceDetail(Integer service_particular_id, Integer service_bill_id,Integer quantity, double amount){
		this.service_particular_id = service_particular_id;
		this.service_bill_id = service_bill_id;
		this.quantity = quantity;
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getService_particular_id() {
		return service_particular_id;
	}

	public void setService_particular_id(int service_particular_id) {
		this.service_particular_id = service_particular_id;
	}

	public int getService_bill_id() {
		return service_bill_id;
	}

	public void setService_bill_id(int service_bill_id) {
		this.service_bill_id = service_bill_id;
	}

}
