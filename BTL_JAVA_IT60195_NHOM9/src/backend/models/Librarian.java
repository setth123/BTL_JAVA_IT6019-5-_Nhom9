package backend.models;

public class Librarian {
	private String accountName;
	private String password;
	public Librarian() {
		
	}
	public Librarian(String accountName, String password) {
		super();
		this.accountName = accountName;
		this.password = password;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Override
    public String toString() {
        return (accountName+"|"+password);
    }
	
}
