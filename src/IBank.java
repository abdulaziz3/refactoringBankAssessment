import java.util.HashMap;

public interface IBank {
	public void put(int key, BankAccount value);
	
	public int getTableSize() ;

	public HashMap<Integer, BankAccount> getTable() ;

	public void setTable(HashMap<Integer, BankAccount> table);

}
