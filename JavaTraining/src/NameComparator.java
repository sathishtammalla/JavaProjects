import java.util.Comparator;

public class NameComparator implements Comparator<Customer> {

	@Override
	public int compare(Customer c1, Customer c2) {
		// TODO Auto-generated method stub
		return c2.getName().compareTo(c1.getName());
	}
	
	
	
		
}
