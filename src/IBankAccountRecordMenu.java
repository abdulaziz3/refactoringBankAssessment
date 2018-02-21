import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * 
 * @author abdulaziz
 * https://github.com/abdulaziz3/refactoringBankAssessment
 */
public interface IBankAccountRecordMenu {

	void initComponents();

	JMenu getRecordsMenu();

	JMenuItem getCreateItem();

	JMenuItem getModifyItem();

	JMenuItem getDeleteItem();

	JMenuItem getSetOverdraft();

	JMenuItem getSetInterest();

}