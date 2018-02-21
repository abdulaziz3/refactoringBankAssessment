
import java.util.HashMap;
/****
 * 
 * @author abdulaziz
 * https://github.com/abdulaziz3/refactoringBankAssessment
 */
public interface IBank {
	public void put(int key, BankAccount value);
	
	public int getTableSize() ;

	public HashMap<Integer, BankAccount> getTable() ;

	public void setTable(HashMap<Integer, BankAccount> table);

}
