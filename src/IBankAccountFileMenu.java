import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * @author abdulaziz
 * https://github.com/abdulaziz3/refactoringBankAssessment
 */
public interface IBankAccountFileMenu {

	void initComponents();

	JMenu getFileMenu();

	JMenuItem getOpen();

	JMenuItem getSave();

	JMenuItem getSaveAs();

}