import javax.swing.JMenu;
import javax.swing.JMenuItem;

/*****
 * 
 * @author abdulaziz
 * https://github.com/abdulaziz3/refactoringBankAssessment
 */
public interface IBankAccountTransactionMenu {

	void initComponents();

	JMenu getTransactionsMenu();

	JMenuItem getDeposit();

	JMenuItem getWithdraw();

	JMenuItem getCalcInterest();

}