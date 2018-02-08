import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class CustomerDAO {
	private static ArrayList<Customer> customers = new ArrayList<Customer>();
	private static int index = 0;
	
	public CustomerDAO() {
		index++;
		Customer customer = new Customer(index,"Sathish",PrivilegeStatus.Privileged);
		customers.add(customer);
		
		index++;
		 customer = new Customer(index,"Adarsh",PrivilegeStatus.Normal);
		customers.add(customer);
		
		index++;
		customer = new Customer(index,"Santhosh",PrivilegeStatus.Restricted);
		customers.add(customer);
	}

	public ArrayList<Customer> getCustomers() {

		for (Customer c : customers) {
			System.out.println("Customer ID : " + c.getCustomerID());
			System.out.println("Customer Name : " + c.getName());
			System.out.println("Customer Status : " + c.getStatus());
		}
		//Collections.sort(customers);

		return customers;

	}

	public Customer getCustomerById(int customerID) {
		Customer cust = new Customer();
		for (Customer c : customers) {
			if (c.getCustomerID() == customerID) {
				cust.setCustomerID(c.getCustomerID());
				cust.setName(c.getName());
				cust.setStatus(c.getStatus());
				// System.out.println("Customer ID : " + c.getCustomerID());

			}

		}
		return cust;

	}

	public String updateCustomerStatus(int customerId, PrivilegeStatus status) {

		for (Customer c : customers) {
			if (c.getCustomerID() == customerId) {
				// cust.setCustomerID(c.getCustomerID());
				// cust.setName(c.getName());
				c.setStatus(status);
				// System.out.println("Customer ID : " + c.getCustomerID());
				return "Success";
			}
		}
		return "Failed";

	}

	public int addCustomer(String name, PrivilegeStatus status) {

		index++;
		Customer cust = new Customer(index, name, status);
		insertCustomer(cust);
		return cust.getCustomerID();
	}

	public String insertCustomer(Customer cust) {

		customers.add(cust);
		return "Success";
	}

	public String deleteCustomer(int customerId) {
		for (Customer c : customers) {
			if (c.getCustomerID() == customerId) {
				customers.remove(c);
				// System.out.println("Customer ID : " + c.getCustomerID());
				return "Success";
			}

		}
		return "Failed";
	}
	
	public List<Customer> getCustomersLessThanId(List<Customer> customers, int limit){
		List<Customer> record = new ArrayList<Customer>();
		for(Customer c : customers) {
			if(c.getCustomerID() < limit) {
				record.add(c);
			}
		}
		return record;
		
	}
	
	public List<Customer> filterMethod(List<Customer> customers,Filterer filter){
		List<Customer> result = new ArrayList<Customer>();
		for(Customer c : customers) {
			if(filter.doFilter(c))
			{
				result.add(c);
			}
		}
		return result;
	}
	
	public<T> List<T> filterAny(List<T> inRecords,Predicate<T> filter){
		List<T> result = new ArrayList<T>();
		for(T c : inRecords) {
			if(filter.test(c))
			{
				result.add(c);
			}
		}
		return result;
	}
	
	/*   Predicate and Function to get Any data in String Format from  Customer Collections */
	
	public List<String> getCustomerNames(List<Customer> customers,Predicate<Customer> filter,Function<Customer,String> getter){
		List<String> result = new ArrayList<>();
		for(Customer c : customers) {
			if(filter.test(c)) {
				result.add(getter.apply(c));
			}
		}
		return result;
	}

	/*  Generic Predicate and Function to get Ant data from any Collections */
	
	public<T,R> List<R> getData(List<T> customers,Predicate<T> filter,Function<T,R> getter){
		List<R> result = new ArrayList<>();
		for(T c : customers) {
			if(filter.test(c)) {
				result.add(getter.apply(c));
			}
		}
		return result;
	}
}
