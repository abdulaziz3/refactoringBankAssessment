import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/****
 * 
 * @author abdulaziz
 * https://github.com/abdulaziz3/refactoringBankAssessment
 */
public class BankAccountRecordMenu extends JFrame implements IBankAccountRecordMenu {
	
	private JMenu recordsMenu;
	
	private JMenuItem createItem, modifyItem, deleteItem, setOverdraft, setInterest;
	
	public BankAccountRecordMenu() {
		
		super("Bank Account Record Menu");
		
		initComponents();
	}

	public void initComponents() {
		
		recordsMenu = new JMenu("Records");
		
		createItem = new JMenuItem("Create Item");
    	modifyItem = new JMenuItem("Modify Item");
    	deleteItem = new JMenuItem("Delete Item");
    	setOverdraft = new JMenuItem("Set Overdraft");
    	setInterest = new JMenuItem("Set Interest");
    	
    	recordsMenu.add(createItem);
    	recordsMenu.add(modifyItem);
    	recordsMenu.add(deleteItem);
    	recordsMenu.add(setOverdraft);
    	recordsMenu.add(setInterest);
	
	}

	public JMenu getRecordsMenu() {
		return recordsMenu;
	}

	public JMenuItem getCreateItem() {
		return createItem;
	}

	public JMenuItem getModifyItem() {
		return modifyItem;
	}

	public JMenuItem getDeleteItem() {
		return deleteItem;
	}

	public JMenuItem getSetOverdraft() {
		return setOverdraft;
	}

	public JMenuItem getSetInterest() {
		return setInterest;
	}
}
