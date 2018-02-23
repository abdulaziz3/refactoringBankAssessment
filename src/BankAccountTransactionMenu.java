import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/****
 * 
 * @author abdulaziz
 * https://github.com/abdulaziz3/refactoringBankAssessment
 */
public class BankAccountTransactionMenu extends JFrame implements IBankAccountTransactionMenu{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JMenu   transactionsMenu;
	private JMenuItem deposit, withdraw, calcInterest;
	
public BankAccountTransactionMenu() {
		
		super("Bank Account Transaction Menu");

		initComponents();
	}

	public void initComponents() {
		
		transactionsMenu = new JMenu("Transactions");
		
		deposit = new JMenuItem("Deposit");
    	withdraw = new JMenuItem("Withdraw");
    	calcInterest = new JMenuItem("Calculate Interest");
    	
    	transactionsMenu.add(deposit);
    	transactionsMenu.add(withdraw);
    	transactionsMenu.add(calcInterest);
		
	}

	public JMenu getTransactionsMenu() {
		return transactionsMenu;
	}

	public JMenuItem getDeposit() {
		return deposit;
	}

	public JMenuItem getWithdraw() {
		return withdraw;
	}

	public JMenuItem getCalcInterest() {
		return calcInterest;
	}
}
