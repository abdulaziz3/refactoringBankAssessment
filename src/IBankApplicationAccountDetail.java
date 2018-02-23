import javax.swing.JLabel;
import javax.swing.JTextField;

/*****
 * 
 * @author abdulaziz
 * https://github.com/abdulaziz3/refactoringBankAssessment
 */
public interface IBankApplicationAccountDetail {

	void initComponents();

	JLabel getAccountIDLabel();

	JLabel getAccountNumberLabel();

	JLabel getFirstNameLabel();

	JLabel getSurnameLabel();

	JLabel getAccountTypeLabel();

	JLabel getBalanceLabel();

	JLabel getOverdraftLabel();

	JTextField getAccountIDTextField();

	JTextField getAccountNumberTextField();

	JTextField getFirstNameTextField();

	JTextField getSurnameTextField();

	JTextField getAccountTypeTextField();

	JTextField getBalanceTextField();

	JTextField getOverdraftTextField();

}