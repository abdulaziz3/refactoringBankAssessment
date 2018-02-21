
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

/****
 * 
 * @author abdulaziz
 *	https://github.com/abdulaziz3/refactoringBankAssessment
 */
public class BankApplication extends JFrame {

	ArrayList<BankAccount> accountList = new ArrayList<BankAccount>();
	
	static Bank bank = new Bank();
	
	static private final String newline = "\n";
	IBankAccountNavigate bankAccountNavigate = new BankAccountNavigate();
	IBankAccountRecordMenu bankAccountRecordMenu = new BankAccountRecordMenu();
	IBankAccountTransactionMenu bankAccountTransactionMenu = new BankAccountTransactionMenu();
	IBankAccountFileMenu bankAccountFileMenu = new BankAccountFileMenu();

	JMenuBar menuBar;
	JMenu exitMenu;
	JMenuItem closeApp;

	JLabel accountIDLabel, accountNumberLabel, firstNameLabel, surnameLabel, accountTypeLabel, balanceLabel, overdraftLabel;
	JTextField accountIDTextField, accountNumberTextField, firstNameTextField, surnameTextField, accountTypeTextField, balanceTextField, overdraftTextField;
	static JFileChooser fc;
	JTable jTable;
	double interestRate;

	int currentItem = 0;

	boolean openValues;

	public BankApplication() {

		super("Bank Application");

		int currentItem;

		initComponents();
	}

	public void initComponents() {
		setLayout(new BorderLayout());
		JPanel displayPanel = new JPanel(new MigLayout());

		accountIDLabel = new JLabel("Account ID: ");
		accountIDTextField = new JTextField(15);
		accountIDTextField.setEditable(false);

		displayPanel.add(accountIDLabel, "growx, pushx");
		displayPanel.add(accountIDTextField, "growx, pushx, wrap");

		accountNumberLabel = new JLabel("Account Number: ");
		accountNumberTextField = new JTextField(15);
		accountNumberTextField.setEditable(false);

		displayPanel.add(accountNumberLabel, "growx, pushx");
		displayPanel.add(accountNumberTextField, "growx, pushx, wrap");

		surnameLabel = new JLabel("Last Name: ");
		surnameTextField = new JTextField(15);
		surnameTextField.setEditable(false);

		displayPanel.add(surnameLabel, "growx, pushx");
		displayPanel.add(surnameTextField, "growx, pushx, wrap");

		firstNameLabel = new JLabel("First Name: ");
		firstNameTextField = new JTextField(15);
		firstNameTextField.setEditable(false);

		displayPanel.add(firstNameLabel, "growx, pushx");
		displayPanel.add(firstNameTextField, "growx, pushx, wrap");

		accountTypeLabel = new JLabel("Account Type: ");
		accountTypeTextField = new JTextField(5);
		accountTypeTextField.setEditable(false);

		displayPanel.add(accountTypeLabel, "growx, pushx");
		displayPanel.add(accountTypeTextField, "growx, pushx, wrap");

		balanceLabel = new JLabel("Balance: ");
		balanceTextField = new JTextField(10);
		balanceTextField.setEditable(false);

		displayPanel.add(balanceLabel, "growx, pushx");
		displayPanel.add(balanceTextField, "growx, pushx, wrap");

		overdraftLabel = new JLabel("Overdraft: ");
		overdraftTextField = new JTextField(10);
		overdraftTextField.setEditable(false);

		displayPanel.add(overdraftLabel, "growx, pushx");
		displayPanel.add(overdraftTextField, "growx, pushx, wrap");

		add(displayPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new GridLayout(1, 4));

		buttonPanel.add(bankAccountNavigate.getFirstItemButton());
		buttonPanel.add(bankAccountNavigate.getPrevItemButton());
		buttonPanel.add(bankAccountNavigate.getNextItemButton());
		buttonPanel.add(bankAccountNavigate.getLastItemButton());

		add(buttonPanel, BorderLayout.SOUTH);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuBar.add(bankAccountNavigate.getNavigateMenu());

		menuBar.add(bankAccountRecordMenu.getRecordsMenu());

		menuBar.add(bankAccountTransactionMenu.getTransactionsMenu());

		menuBar.add(bankAccountFileMenu.getFileMenu());

		exitMenu = new JMenu("Exit");

		closeApp = new JMenuItem("Close Application");

		exitMenu.add(closeApp);

		menuBar.add(exitMenu);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		bankAccountRecordMenu.getSetOverdraft().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (bank.getTable().get(currentItem).getAccountType().trim().equals("Current")) {
					String newOverdraftStr = JOptionPane.showInputDialog(null, "Enter new Overdraft",
							JOptionPane.OK_CANCEL_OPTION);
					overdraftTextField.setText(newOverdraftStr);
					bank.getTable().get(currentItem).setOverdraft(Double.parseDouble(newOverdraftStr));
				} else
					JOptionPane.showMessageDialog(null, "Overdraft only applies to Current Accounts");

			}
		});

		ActionListener first = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				saveOpenValues();

				currentItem = 0;
				while (!bank.getTable().containsKey(currentItem)) {
					currentItem++;
				}
				displayDetails(currentItem);
			}
		};

		ActionListener next = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveOpenValues();
				// No next if at end of list.
				if (currentItem != (bank.getTable().size() - 1)) {
					// Move to next item.
					currentItem++;
					while (!bank.getTable().containsKey(currentItem)) {
						currentItem++;
					}
					displayDetails(currentItem);
				}
			}
		};

		ActionListener next1 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<Integer> keyList = new ArrayList<Integer>();
				int i = 0;

				while (i < bank.getTableSize()) {
					i++;
					if (bank.getTable().containsKey(i))
						keyList.add(i);
				}

				int maxKey = Collections.max(keyList);

				saveOpenValues();

				if (currentItem < maxKey) {
					currentItem++;
					while (!bank.getTable().containsKey(currentItem)) {
						currentItem++;
					}
				}
				displayDetails(currentItem);
			}
		};

		ActionListener prev = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<Integer> keyList = new ArrayList<Integer>();
				int i = 0;

				while (i < bank.getTableSize()) {
					i++;
					if (bank.getTable().containsKey(i))
						keyList.add(i);
				}

				int minKey = Collections.min(keyList);
				// System.out.println(minKey);

				if (currentItem > minKey) {
					currentItem--;
					while (!bank.getTable().containsKey(currentItem)) {
						currentItem--;
					}
				}
				displayDetails(currentItem);
			}
		};

		ActionListener last = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveOpenValues();
				currentItem = 29;

				while (!bank.getTable().containsKey(currentItem)) {
					currentItem--;
				}
				displayDetails(currentItem);
			}
		};
		
		bankAccountNavigate.getNextItemButton().addActionListener(next1);
		bankAccountNavigate.getNextItem().addActionListener(next1);

		bankAccountNavigate.getPrevItemButton().addActionListener(prev);
		bankAccountNavigate.getPrevItem().addActionListener(prev);

		bankAccountNavigate.getFirstItemButton().addActionListener(first);
		bankAccountNavigate.getFirstItem().addActionListener(first);

		bankAccountNavigate.getLastItemButton().addActionListener(last);
		bankAccountNavigate.getLastItem().addActionListener(last);

		bankAccountRecordMenu.getDeleteItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				bank.getTable().remove(currentItem);
				JOptionPane.showMessageDialog(null, "Account Deleted");

				currentItem = 0;
				while (!bank.getTable().containsKey(currentItem)) {
					currentItem++;
				}
				displayDetails(currentItem);
			}
		});

		bankAccountRecordMenu.getCreateItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateBankDialog(bank.getTable());
			}
		});

		bankAccountRecordMenu.getModifyItem().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				surnameTextField.setEditable(true);
				firstNameTextField.setEditable(true);

				openValues = true;
			}
		});

		bankAccountRecordMenu.getSetInterest().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String interestRateStr = JOptionPane.showInputDialog("Enter Interest Rate: (do not type the % sign)");
				if (interestRateStr != null)
					interestRate = Double.parseDouble(interestRateStr);
			}
		});

		bankAccountNavigate.getListAll().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFrame frame = new JFrame("TableDemo");
				JPanel pan = new JPanel();

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				String col[] = { "ID", "Number", "Name", "Account Type", "Balance", "Overdraft" };

				DefaultTableModel tableModel = new DefaultTableModel(col, 0);
				jTable = new JTable(tableModel);
				JScrollPane scrollPane = new JScrollPane(jTable);
				jTable.setAutoCreateRowSorter(true);

				for (Map.Entry<Integer, BankAccount> entry : bank.getTable().entrySet()) {

					Object[] objs = { entry.getValue().getAccountID(), entry.getValue().getAccountNumber(),
							entry.getValue().getFirstName().trim() + " " + entry.getValue().getSurname().trim(),
							entry.getValue().getAccountType(), entry.getValue().getBalance(),
							entry.getValue().getOverdraft() };

					tableModel.addRow(objs);
				}
				frame.setSize(600, 500);
				frame.add(scrollPane);
				frame.setVisible(true);
			}
		});

		bankAccountFileMenu.getOpen().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readFile();
				currentItem = 0;
				while (!bank.getTable().containsKey(currentItem)) {
					currentItem++;
				}
				displayDetails(currentItem);
			}
		});

		bankAccountFileMenu.getSave().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeFile();
			}
		});

		bankAccountFileMenu.getSaveAs().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFileAs();
			}
		});

		closeApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int answer = JOptionPane.showConfirmDialog(BankApplication.this,
						"Do you want to save before quitting?");
				if (answer == JOptionPane.YES_OPTION) {
					saveFileAs();
					dispose();
				} else if (answer == JOptionPane.NO_OPTION)
					dispose();
				else if (answer == 0)
					;
			}
		});

		bankAccountNavigate.getFindBySurname().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sName = JOptionPane.showInputDialog("Search for surname: ");
				boolean found = false;

				for (Map.Entry<Integer, BankAccount> entry : bank.getTable().entrySet()) {

					if (sName.equalsIgnoreCase((entry.getValue().getSurname().trim()))) {
						found = true;
						accountIDTextField.setText(entry.getValue().getAccountID() + "");
						accountNumberTextField.setText(entry.getValue().getAccountNumber());
						surnameTextField.setText(entry.getValue().getSurname());
						firstNameTextField.setText(entry.getValue().getFirstName());
						accountTypeTextField.setText(entry.getValue().getAccountType());
						balanceTextField.setText(entry.getValue().getBalance() + "");
						overdraftTextField.setText(entry.getValue().getOverdraft() + "");
					}
				}
				if (found)
					JOptionPane.showMessageDialog(null, "Surname  " + sName + " found.");
				else
					JOptionPane.showMessageDialog(null, "Surname " + sName + " not found.");
			}
		});

		bankAccountNavigate.getFindByAccount().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String accNum = JOptionPane.showInputDialog("Search for account number: ");
				boolean found = false;

				for (Map.Entry<Integer, BankAccount> entry : bank.getTable().entrySet()) {

					if (accNum.equals(entry.getValue().getAccountNumber().trim())) {
						found = true;
						accountIDTextField.setText(entry.getValue().getAccountID() + "");
						accountNumberTextField.setText(entry.getValue().getAccountNumber());
						surnameTextField.setText(entry.getValue().getSurname());
						firstNameTextField.setText(entry.getValue().getFirstName());
						accountTypeTextField.setText(entry.getValue().getAccountType());
						balanceTextField.setText(entry.getValue().getBalance() + "");
						overdraftTextField.setText(entry.getValue().getOverdraft() + "");

					}
				}
				if (found)
					JOptionPane.showMessageDialog(null, "Account number " + accNum + " found.");
				else
					JOptionPane.showMessageDialog(null, "Account number " + accNum + " not found.");

			}
		});

		bankAccountTransactionMenu.getDeposit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accNum = JOptionPane.showInputDialog("Account number to deposit into: ");
				boolean found = false;

				for (Map.Entry<Integer, BankAccount> entry : bank.getTable().entrySet()) {
					if (accNum.equals(entry.getValue().getAccountNumber().trim())) {
						found = true;
						String toDeposit = JOptionPane.showInputDialog("Account found, Enter Amount to Deposit: ");
						entry.getValue().setBalance(entry.getValue().getBalance() + Double.parseDouble(toDeposit));
						displayDetails(entry.getKey());
					}
				}
				if (!found)
					JOptionPane.showMessageDialog(null, "Account number " + accNum + " not found.");
			}
		});

		bankAccountTransactionMenu.getWithdraw().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accNum = JOptionPane.showInputDialog("Account number to withdraw from: ");
				String toWithdraw = JOptionPane.showInputDialog("Account found, Enter Amount to Withdraw: ");
				boolean found;

				for (Map.Entry<Integer, BankAccount> entry : bank.getTable().entrySet()) {

					if (accNum.equals(entry.getValue().getAccountNumber().trim())) {

						found = true;

						if (entry.getValue().getAccountType().trim().equals("Current")) {
							if (Double.parseDouble(toWithdraw) > entry.getValue().getBalance()
									+ entry.getValue().getOverdraft())
								JOptionPane.showMessageDialog(null, "Transaction exceeds overdraft limit");
							else {
								entry.getValue()
										.setBalance(entry.getValue().getBalance() - Double.parseDouble(toWithdraw));
								displayDetails(entry.getKey());
							}
						} else if (entry.getValue().getAccountType().trim().equals("Deposit")) {
							if (Double.parseDouble(toWithdraw) <= entry.getValue().getBalance()) {
								entry.getValue()
										.setBalance(entry.getValue().getBalance() - Double.parseDouble(toWithdraw));
								displayDetails(entry.getKey());
							} else
								JOptionPane.showMessageDialog(null, "Insufficient funds.");
						}
					}
				}
			}
		});

		bankAccountTransactionMenu.getCalcInterest().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Map.Entry<Integer, BankAccount> entry : bank.getTable().entrySet()) {
					if (entry.getValue().getAccountType().equals("Deposit")) {
						double equation = 1 + ((interestRate) / 100);
						entry.getValue().setBalance(entry.getValue().getBalance() * equation);
						JOptionPane.showMessageDialog(null, "Balances Updated");
						displayDetails(entry.getKey());
					}
				}
			}
		});
	}

	public void saveOpenValues() {
		if (openValues) {
			surnameTextField.setEditable(false);
			firstNameTextField.setEditable(false);

			bank.getTable().get(currentItem).setSurname(surnameTextField.getText());
			bank.getTable().get(currentItem).setFirstName(firstNameTextField.getText());
		}
	}

	public void displayDetails(int currentItem) {

		accountIDTextField.setText(bank.getTable().get(currentItem).getAccountID() + "");
		accountNumberTextField.setText(bank.getTable().get(currentItem).getAccountNumber());
		surnameTextField.setText(bank.getTable().get(currentItem).getSurname());
		firstNameTextField.setText(bank.getTable().get(currentItem).getFirstName());
		accountTypeTextField.setText(bank.getTable().get(currentItem).getAccountType());
		balanceTextField.setText(bank.getTable().get(currentItem).getBalance() + "");
		if (accountTypeTextField.getText().trim().equals("Current"))
			overdraftTextField.setText(bank.getTable().get(currentItem).getOverdraft() + "");
		else
			overdraftTextField.setText("Only applies to current accs");

	}

	private static RandomAccessFile input;
	private static RandomAccessFile output;
	private static final int NUMBER_RECORDS = 100;

	public static void openFileRead() {

		bank.getTable().clear();

		fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();

		} else {
		}

		try
		{
			if (fc.getSelectedFile() != null)
				input = new RandomAccessFile(fc.getSelectedFile(), "r");
		} 
		catch (IOException ioException) {
			JOptionPane.showMessageDialog(null, "File Does Not Exist.");
		}

	} 

	static String fileToSaveAs = "";

	public static void openFileWrite() {
		if (fileToSaveAs != "") {
			try 
			{
				output = new RandomAccessFile(fileToSaveAs, "rw");
				JOptionPane.showMessageDialog(null, "Accounts saved to " + fileToSaveAs);
			} 
			catch (IOException ioException) {
				JOptionPane.showMessageDialog(null, "File does not exist.");
			} 
		} else
			saveToFileAs();
	}

	public static void saveToFileAs() {

		fc = new JFileChooser();

		int returnVal = fc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();

			fileToSaveAs = file.getName();
			JOptionPane.showMessageDialog(null, "Accounts saved to " + file.getName());
		} else {
			JOptionPane.showMessageDialog(null, "Save cancelled by user");
		}

		try {
			if (fc.getSelectedFile() == null) {
				JOptionPane.showMessageDialog(null, "Cancelled");
			} else
				output = new RandomAccessFile(fc.getSelectedFile(), "rw");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void closeFile() {
		try 
		{
			if (input != null)
				input.close();
		} // end try
		catch (IOException ioException) {

			JOptionPane.showMessageDialog(null, "Error closing file.");
		} 
	}

	public static void readRecords() {

		RandomAccessBankAccount record = new RandomAccessBankAccount();

		try 
		{
			while (true) {
				do {
					if (input != null)
						record.read(input);
				} while (record.getAccountID() == 0);

				BankAccount ba = new BankAccount(record.getAccountID(), record.getAccountNumber(),
						record.getFirstName(), record.getSurname(), record.getAccountType(), record.getBalance(),
						record.getOverdraft());

				Integer key = Integer.valueOf(ba.getAccountNumber().trim());

				int hash = (key % bank.getTableSize());

				while (bank.getTable().containsKey(hash)) {

					hash = hash + 1;
				}

				bank.getTable().put(hash, ba);

			} 
		} 
		catch (EOFException eofException) 
		{
			return;
		} 
		catch (IOException ioException) {
			JOptionPane.showMessageDialog(null, "Error reading file.");
			System.exit(1);
		} 
	}

	public static void saveToFile() {

		RandomAccessBankAccount record = new RandomAccessBankAccount();

		Scanner input = new Scanner(System.in);

		for (Map.Entry<Integer, BankAccount> entry : bank.getTable().entrySet()) {
			record.setAccountID(entry.getValue().getAccountID());
			record.setAccountNumber(entry.getValue().getAccountNumber());
			record.setFirstName(entry.getValue().getFirstName());
			record.setSurname(entry.getValue().getSurname());
			record.setAccountType(entry.getValue().getAccountType());
			record.setBalance(entry.getValue().getBalance());
			record.setOverdraft(entry.getValue().getOverdraft());

			if (output != null) {

				try {
					record.write(output);
				} catch (IOException u) {
					u.printStackTrace();
				}
			}
		}
	}

	public static void writeFile() {
		openFileWrite();
		saveToFile();;
		closeFile();
	}

	public static void saveFileAs() {
		saveToFileAs();
		saveToFile();
		closeFile();
	}

	public static void readFile() {
		openFileRead();
		readRecords();
		closeFile();
	}

	public void put(int key, BankAccount value) {
		int hash = (key % bank.getTableSize());

		while (bank.getTable().containsKey(key)) {
			hash = hash + 1;
		}
		bank.getTable().put(hash, value);

	}
}
