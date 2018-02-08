
public class IdFilter implements Filterer {
	private int limit;

	public IdFilter(int limit) {
		super();
		this.limit = limit;
	}

	@Override
	public boolean doFilter(Customer c) {
		// TODO Auto-generated method stub
		if (c.getCustomerID() < limit) {
			return true;
		}
		return false;
	}

}
