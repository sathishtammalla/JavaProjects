import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Collections;

public class CustomerService {

	public static void main(String args[]) {
		/*
		 * Customer cust1 = new Customer(1, "Sathish", PrivilegeStatus.Privileged);
		 * Customer cust2 = new Customer(2, "Adarsh", PrivilegeStatus.Nomal); Customer
		 * cust3 = new Customer(3, "Santhosh", PrivilegeStatus.Restricted);
		 * 
		 * customers.add(cust1);
		 * 
		 * customers.add(cust2);
		 * 
		 * customers.add(cust3);
		 * 
		 */

		CustomerDAO custDao = new CustomerDAO();

		// Add Few more customers
		custDao.addCustomer("Srini", PrivilegeStatus.Normal);

		custDao.addCustomer("Manjeet", PrivilegeStatus.Normal);
		custDao.addCustomer("Namitha", PrivilegeStatus.Normal);
		custDao.addCustomer("Anil", PrivilegeStatus.Normal);

		// CustomerService service = new CustomerService();

		// service.getCustomers();

		System.out.println("From DAP");

		custDao.deleteCustomer(4);

		custDao.getCustomers();

		ArrayList<Customer> customers = new ArrayList<Customer>();

		System.out.println("Display Customer from Collections");
		System.out.println("-----------------------------------");
		customers = custDao.getCustomers();

		Collections.sort(customers);

		custDao.getCustomers();

		Collections.sort(customers, (c1, c2) -> c1.getName().compareTo(c2.getName()));

		System.out.println("After name Sort------------------");

		custDao.getCustomers();

		NameComparator nc = new NameComparator();

		Collections.sort(customers, nc);

		System.out.println("After name Sort is using NC------------------");

		custDao.getCustomers();

		StatusComparator sc = new StatusComparator();

		Collections.sort(customers, sc);

		System.out.println("After Status Sort is ------------------");

		custDao.getCustomers();

		System.out.println("Customer ID is Less Than.........");

		IdFilter id1 = new IdFilter(2);

		List<Customer> recs = custDao.filterMethod(customers, id1);
		recs.forEach(s -> System.out.println(s));

		NameFilter nf1 = new NameFilter("Sa");

		System.out.println("Name Filter Starting with.........");

		List<Customer> nameRec = custDao.filterMethod(customers, nf1);
		nameRec.forEach(s -> System.out.println(s));

		/* Using the above filters with Lambda Expressions */

		List<Customer> custLambda = custDao.filterMethod(customers, (Customer c) -> c.getCustomerID() < 3);

		System.out.println("ID Filter less than 3.........");

		custLambda.forEach(s -> System.out.println(s));

		List<Customer> custNameLambda = custDao.filterMethod(customers, (Customer c) -> c.getName().startsWith("Ad"));

		System.out.println("Name Filter Starting with.........");

		custNameLambda.forEach(s -> System.out.println(s));

		List<Customer> custNameAny = custDao.filterAny(customers, (c) -> c.getName().startsWith("Ad"));

		System.out.println("Name Filter Any Starting with .........");

		custNameAny.forEach(s -> System.out.println(s));

		// List<Customer> custNameLength = custDao.filterMethod(customers, (Customer c)
		// -> c.getName().length());

		System.out.println("Name Filter Starting with.........");

		custNameLambda.forEach(s -> System.out.println(s));

		/* Using Function to Map */

		List<String> custNames = custDao.getCustomerNames(customers, (c) -> c.getCustomerID() < 4, (c) -> c.getName());

		System.out.println("Printing Names with Predicate and Function.........");

		custNames.forEach(System.out::println);

		List<String> custStatus = custDao.getData(customers, (c) -> c.getCustomerID() < 4,
				(c) -> c.getStatus().toString());

		System.out.println("Printing Names with Predicate and Function.........");

		custStatus.forEach(System.out::println);
		
		/*
		 * ................. Using Streams........ Write a method to return the names of
		 * customers who have a status of Normal. Use JDK 8 streams.
		 */

		List<String> custStatusStream = customers.stream()// .peek(c -> System.out.println("peek 1 : " + c))
				.filter(c -> c.getStatus() == (PrivilegeStatus.Normal))
				.map(c -> c.getName())
				.collect(Collectors.toList());

		System.out.println("Printing Names with Stream and Lambda, Printing all Normal Customers.........");

		custStatusStream.forEach(System.out::println);

		List<Integer> custNameLengthStream = customers.stream()// .peek(c -> System.out.println("peek 1 : " + c))
				.filter(c -> c.getStatus() == (PrivilegeStatus.Normal))
				.map(c -> c.getName().length())
				.collect(Collectors.toList());

		System.out.println("Printing Names Length with Stream and Lambda, Printing all Normal Customers.........");

		custNameLengthStream.forEach(System.out::println);
		
		/* Generate Stream  */
		
		Stream<String> streams = Stream.generate(()->"*");
		
		streams.limit(100).forEach(System.out::print);

		/* Generate a Map of Customers using Stream    */
		
		Map<Integer,Customer> custMap = customers.stream()
										.filter(c -> c.getStatus()==(PrivilegeStatus.Normal))
										.collect(Collectors.toMap(c -> c.getCustomerID(),c -> c));
		
		System.out.println("Printing the Map of Customers............");
		
		custMap.forEach((k,v)->System.out.printf("Key : %d, Val : %s, %n", k,v));
		
		Map<Integer,String> custNameMap = customers.stream()
										.filter(c -> c.getStatus()==(PrivilegeStatus.Normal))
										.collect(Collectors.toMap(c -> c.getCustomerID(), c -> c.getName()));
		
		System.out.println("Printing the Map of Normal Status Customer Names............");
		
		custNameMap.forEach((k,v)->System.out.printf("Key : %d, Val : %s %n", k,v));
	}

}
