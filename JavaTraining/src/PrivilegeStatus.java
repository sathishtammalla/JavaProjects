
public enum PrivilegeStatus {
	Privileged(10),Normal(20),Restricted(30);
	
	private int value;
	
	PrivilegeStatus(int value){
		this.value = value;
	}
	
	public int getStatus() {
		return this.value;
	}
}
