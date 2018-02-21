
public interface IBankAccount {
	
	public int getAccountID();
	
	public void setAccountID(int accountID);
	
	public String getAccountNumber() ;
	
	public void setAccountNumber(String accountNumber) ;
	
	public String getSurname() ;
	
	public void setSurname(String surname);
	
	public String getAccountType() ;
	
	public void setAccountType(String accountType);
	
	public void setAccType(String accType);
	
	public String getAccType();
	
	public String getFirstName();
	
	public void setFirstName(String firstName);
	
	public double getBalance() ;
	
	public void setBalance(double balance);
	
	public double getOverdraft() ;
	
	public void setOverdraft(double overdraft);
	
	public String toString();
}
