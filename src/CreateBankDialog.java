
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
/****
 * 
 * @author abdulaziz
 * https://github.com/abdulaziz3/refactoringBankAssessment
 */
public class CreateBankDialog extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Random rand = new Random();

	Bank bank = new Bank();
	IBankApplicationAccountDetail bankApplicationAccountDetail = new BankApplicationAccountDetail();
	JComboBox comboBox;
	
	CreateBankDialog(HashMap<Integer, BankAccount> accounts) {
		
		super("Add Bank Details");
		
		bank.setTable(accounts);
		
		setLayout(new BorderLayout());
		
		JPanel dataPanel = new JPanel(new MigLayout());
		String[] comboTypes = {"Current", "Deposit"};
		
		final JComboBox comboBox = new JComboBox(comboTypes);
	
		
		bankApplicationAccountDetail.getAccountNumberTextField().setEditable(true);
		
		dataPanel.add(bankApplicationAccountDetail.getAccountNumberLabel(), "growx, pushx");
		dataPanel.add(bankApplicationAccountDetail.getAccountNumberTextField(), "growx, pushx, wrap");

		bankApplicationAccountDetail.getSurnameTextField().setEditable(true);
		
		dataPanel.add(bankApplicationAccountDetail.getSurnameLabel(), "growx, pushx");
		dataPanel.add(bankApplicationAccountDetail.getSurnameTextField(), "growx, pushx, wrap");

		bankApplicationAccountDetail.getFirstNameTextField().setEditable(true);
		
		dataPanel.add(bankApplicationAccountDetail.getFirstNameLabel(), "growx, pushx");
		dataPanel.add(bankApplicationAccountDetail.getFirstNameTextField(), "growx, pushx, wrap");

		bankApplicationAccountDetail.getAccountTypeTextField().setEditable(true);
		
		dataPanel.add(bankApplicationAccountDetail.getAccountTypeLabel(), "growx, pushx");	
		dataPanel.add(comboBox, "growx, pushx, wrap");

		bankApplicationAccountDetail.getBalanceTextField().setText("0.0");
		bankApplicationAccountDetail.getBalanceTextField().setEditable(false);
		
		dataPanel.add(bankApplicationAccountDetail.getBalanceLabel(), "growx, pushx");
		dataPanel.add(bankApplicationAccountDetail.getBalanceTextField(), "growx, pushx, wrap");
		
		bankApplicationAccountDetail.getOverdraftTextField().setText("0.0");
		bankApplicationAccountDetail.getOverdraftTextField().setEditable(false);
		
		dataPanel.add(bankApplicationAccountDetail.getOverdraftLabel(), "growx, pushx");
		dataPanel.add(bankApplicationAccountDetail.getOverdraftTextField(), "growx, pushx, wrap");
		
		add(dataPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton addButton = new JButton("Add");
		JButton cancelButton = new JButton("Cancel");
		
		buttonPanel.add(addButton);
		buttonPanel.add(cancelButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
		
		defineEventListenerForAdd(addButton, comboBox);
		defineEventListenerForCancel(cancelButton);
		
		setSize(400,800);
		pack();
		setVisible(true);

	}
	
	/*****
	 * Method to add event listener for add button & combo box
	 * @param addButton
	 */
	void defineEventListenerForAdd(JButton addButton,  JComboBox comboBox)
	{
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String accountNumber = bankApplicationAccountDetail.getAccountNumberTextField().getText();
				
				String surname = bankApplicationAccountDetail.getSurnameTextField().getText();
				String firstName = bankApplicationAccountDetail.getFirstNameTextField().getText();
			
				String accountType = comboBox.getSelectedItem().toString();
				
				String balanceStr = bankApplicationAccountDetail.getBalanceTextField().getText();
				String overdraftStr = bankApplicationAccountDetail.getOverdraftTextField().getText();

				double balance;
				double overdraft;

				if (accountNumber != null && accountNumber.length()==8 && surname != null && firstName != null && accountType != null) {
					try {
						
						boolean idTaken = false;
						boolean accNumTaken=false;
							
							int randNumber = rand.nextInt(24) + 1;
						
						 for (Map.Entry<Integer, BankAccount> entry : bank.getTable().entrySet()) {
							 
							 while(randNumber == entry.getValue().getAccountID()){
								 idTaken = true;
								 randNumber = rand.nextInt(24)+1;
							 }		 
						 }
					 
							for (Map.Entry<Integer, BankAccount> entry : bank.getTable().entrySet()) {					
								 if(entry.getValue().getAccountNumber().trim().equals(bankApplicationAccountDetail.getAccountNumberTextField().getText())){
									 accNumTaken=true;	
								 }
							 }
						
						if(!accNumTaken){

							BankAccount account = new BankAccount(randNumber, accountNumber, surname, firstName, accountType, 0.0, 0.0);

							int key = Integer.parseInt(account.getAccountNumber());
							
							int hash = (key%bank.getTableSize());
							
							while(bank.getTable().containsKey(hash)){
								hash = hash+1;
							}
							bank.getTable().put(hash, account);
						}
						else{
							JOptionPane.showMessageDialog(null, "Account Number must be unique");
						}
					}
					catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Number format exception");					
					}
				}
				else JOptionPane.showMessageDialog(null, "Please make sure all fields have values, and Account Number is a unique 8 digit number");
				dispose();
			}
		});
	}
/**
 * 
 * @param cancelButton
 */
	void defineEventListenerForCancel(JButton cancelButton){
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}