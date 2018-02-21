import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * 
 * @author abdulaziz
 * https://github.com/abdulaziz3/refactoringBankAssessment
 */
public interface IBankAccountNavigate {

	void initComponents();

	/**
	 * @return the navigateMenu
	 */
	JMenu getNavigateMenu();

	JMenuItem getListAll();

	JMenuItem getNextItem();

	JMenuItem getPrevItem();

	JMenuItem getFirstItem();

	JMenuItem getLastItem();

	JMenuItem getFindByAccount();

	JMenuItem getFindBySurname();

	JButton getFirstItemButton();

	JButton getLastItemButton();

	JButton getNextItemButton();

	JButton getPrevItemButton();

}