/****
 * 
 * @author abdulaziz
 * https://github.com/abdulaziz3/refactoringBankAssessment
 */
public class BankAccount implements IBankAccount {
	
	private int accountID;
	private String accountNumber;
	private String surname;
	private String firstName;
	private String accountType;
	private double balance;
	private double overdraft;
	
	public static int count = 0;
	
/*****
 *  constructor
 * @param accountID
 * @param accountNumber
 * @param surname
 * @param firstName
 * @param accountType
 * @param balance
 * @param overdraft
 */
	public BankAccount(int accountID, String accountNumber, String surname, String firstName, String accountType, double balance, double overdraft){
		this.accountID = accountID;
		this.accountNumber = accountNumber;
		this.surname = surname;
		this.firstName = firstName;
		this.accountType = accountType;
		this.balance = balance;
		this.overdraft = overdraft;
	}
	
/*****
 * constructor
 */
	public BankAccount(){
		this(0, "", "", "", "", 0.0, 0.0);
	}

	/*****
	 *getters
	 */
	public int getAccountID() {
		return accountID;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public double getOverdraft() {
		return overdraft;
	}
	
/*****
 * setters 
 */
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}
/*****
 * printing out information
 */
	public String toString(){
		return "\nAccount id: " + accountID +  "Account Num: " + accountNumber + "\nName: " + surname + " " + firstName+"\n";
	}

@Override
public void setAccType(String accType) {
	accType = accType;
}

@Override
public String getAccType() {
	return accountType;
}
}
