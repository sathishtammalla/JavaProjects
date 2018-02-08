
public class Customer implements Comparable<Customer>{
	
	private int customerID;
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getName() {
		return name;
	}
	public Customer(int customerID, String name, PrivilegeStatus status) {
		super();
		this.customerID = customerID;
		this.name = name;
		this.status = status;
	}
	
	public Customer() {
		
	}
	public void setName(String name) {
		this.name = name;
	}
	public PrivilegeStatus getStatus() {
		return status;
	}
	public void setStatus(PrivilegeStatus status) {
		this.status = status;
	}
	private String name;
	private PrivilegeStatus status;
	
	@Override
	public int compareTo(Customer customer) {
		// TODO Auto-generated method stub
		return customer.customerID - this.customerID ;
	}
	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", name=" + name + ", status=" + status + "]";
	}
	
	
}
