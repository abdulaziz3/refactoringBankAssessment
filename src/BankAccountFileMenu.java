
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/****
 * 
 * @author abdulaziz
 * https://github.com/abdulaziz3/refactoringBankAssessment
 */
public class BankAccountFileMenu extends JFrame implements IBankAccountFileMenu {
	
	JMenu   fileMenu;
	JMenuItem open, save, saveAs;
		
	public BankAccountFileMenu() {
		super("Bank Account File");
		
		initComponents();
	}
	
	@Override
	public void initComponents() {
		
		fileMenu = new JMenu("File");
    	
    	open = new JMenuItem("Open File");
    	save = new JMenuItem("Save File");
    	saveAs = new JMenuItem("Save As");
    	
    	fileMenu.add(open);
    	fileMenu.add(save);
    	fileMenu.add(saveAs);
	}
	public JMenu getFileMenu() {
		return fileMenu;
	}
	
	public JMenuItem getOpen() {
		return open;
	}

	public JMenuItem getSave() {
		return save;
	}

	public JMenuItem getSaveAs() {
		return saveAs;
	}
}
