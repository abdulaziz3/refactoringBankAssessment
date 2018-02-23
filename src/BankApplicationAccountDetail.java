import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*****
 * 
 * @author abdulaziz
 * https://github.com/abdulaziz3/refactoringBankAssessment
 */
public class BankApplicationAccountDetail extends JFrame implements IBankApplicationAccountDetail {
	
	private static final long serialVersionUID = 1L;
	
	JLabel accountIDLabel, accountNumberLabel, firstNameLabel, surnameLabel, accountTypeLabel, balanceLabel, overdraftLabel;
	JTextField accountIDTextField, accountNumberTextField, firstNameTextField, surnameTextField, accountTypeTextField, balanceTextField, overdraftTextField;
	
	public BankApplicationAccountDetail (){
		
		super("information");
		
		initComponents();
	}
	
	/* (non-Javadoc)
	 * @see IBankApplicationAccountDetail#initComponents()
	 */
	@Override
	public void initComponents() {
		accountIDLabel = new JLabel("Account ID: ");
		accountIDTextField = new JTextField(15);
		
		accountNumberLabel = new JLabel("Account Number: ");
		accountNumberTextField = new JTextField(15);
		
		surnameLabel = new JLabel("Last Name: ");
		surnameTextField = new JTextField(15);
		
		firstNameLabel = new JLabel("First Name: ");
		firstNameTextField = new JTextField(15);
		
		accountTypeLabel = new JLabel("Account Type: ");
		accountTypeTextField = new JTextField(5);
		
		balanceLabel = new JLabel("Balance: ");
		balanceTextField = new JTextField(10);
		
		overdraftLabel = new JLabel("Overdraft: ");
		overdraftTextField = new JTextField(10);
	}

	/* (non-Javadoc)
	 * @see IBankApplicationAccountDetail#getAccountIDLabel()
	 */
	@Override
	public JLabel getAccountIDLabel() {
		return accountIDLabel;
	}

	/* (non-Javadoc)
	 * @see IBankApplicationAccountDetail#getAccountNumberLabel()
	 */
	@Override
	public JLabel getAccountNumberLabel() {
		return accountNumberLabel;
	}

	/* (non-Javadoc)
	 * @see IBankApplicationAccountDetail#getFirstNameLabel()
	 */
	@Override
	public JLabel getFirstNameLabel() {
		return firstNameLabel;
	}

	/* (non-Javadoc)
	 * @see IBankApplicationAccountDetail#getSurnameLabel()
	 */
	@Override
	public JLabel getSurnameLabel() {
		return surnameLabel;
	}

	/* (non-Javadoc)
	 * @see IBankApplicationAccountDetail#getAccountTypeLabel()
	 */
	@Override
	public JLabel getAccountTypeLabel() {
		return accountTypeLabel;
	}

	/* (non-Javadoc)
	 * @see IBankApplicationAccountDetail#getBalanceLabel()
	 */
	@Override
	public JLabel getBalanceLabel() {
		return balanceLabel;
	}

	/* (non-Javadoc)
	 * @see IBankApplicationAccountDetail#getOverdraftLabel()
	 */
	@Override
	public JLabel getOverdraftLabel() {
		return overdraftLabel;
	}

	/* (non-Javadoc)
	 * @see IBankApplicationAccountDetail#getAccountIDTextField()
	 */
	@Override
	public JTextField getAccountIDTextField() {
		return accountIDTextField;
	}

	/* (non-Javadoc)
	 * @see IBankApplicationAccountDetail#getAccountNumberTextField()
	 */
	@Override
	public JTextField getAccountNumberTextField() {
		return accountNumberTextField;
	}

	/* (non-Javadoc)
	 * @see IBankApplicationAccountDetail#getFirstNameTextField()
	 */
	@Override
	public JTextField getFirstNameTextField() {
		return firstNameTextField;
	}

	/* (non-Javadoc)
	 * @see IBankApplicationAccountDetail#getSurnameTextField()
	 */
	@Override
	public JTextField getSurnameTextField() {
		return surnameTextField;
	}

	/* (non-Javadoc)
	 * @see IBankApplicationAccountDetail#getAccountTypeTextField()
	 */
	@Override
	public JTextField getAccountTypeTextField() {
		return accountTypeTextField;
	}

	/* (non-Javadoc)
	 * @see IBankApplicationAccountDetail#getBalanceTextField()
	 */
	@Override
	public JTextField getBalanceTextField() {
		return balanceTextField;
	}

	/* (non-Javadoc)
	 * @see IBankApplicationAccountDetail#getOverdraftTextField()
	 */
	@Override
	public JTextField getOverdraftTextField() {
		return overdraftTextField;
	}
}
