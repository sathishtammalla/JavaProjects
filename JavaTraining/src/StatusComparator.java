import java.util.Comparator;

public class StatusComparator implements Comparator<Customer> {

	@Override
	public int compare(Customer o1, Customer o2) {
		// TODO Auto-generated method stub
		return o1.getStatus().toString().compareTo(o2.getStatus().toString());
	}

}
