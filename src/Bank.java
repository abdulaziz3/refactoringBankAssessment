import java.util.ArrayList;
import java.util.HashMap;
/****
 * @author abdulaziz
 *
 */
public class Bank implements IBank {
	
	private int TABLE_SIZE = 29;
	
	ArrayList<BankAccount> accountList;

	private HashMap<Integer, BankAccount> table = new HashMap<Integer, BankAccount>();
	
	public void put(int key, BankAccount value){
		int hash = (key%getTableSize());

		while(getTable().containsKey(key)){
			hash = hash+1;
		}
		getTable().put(hash, value);
	}

	public  int getTableSize() {
		return TABLE_SIZE;
	}

	public HashMap<Integer, BankAccount> getTable() {
		return table;
	}

	public void setTable(HashMap<Integer, BankAccount> table) {
		this.table = table;
	}
}
