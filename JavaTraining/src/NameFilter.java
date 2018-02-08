
public class NameFilter implements Filterer {
	private String staStr;
	

	public NameFilter(String staStr) {
		super();
		this.staStr = staStr;
	}


	@Override
	public boolean doFilter(Customer c) {
		if(c.getName().startsWith(staStr)) {
			return true;
		}
			
		return false;
	}

}
