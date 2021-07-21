package com.example.services;

import java.sql.SQLException;
import java.util.List;

import com.example.dao.AccountDao;
import com.example.dao.BankAccountDao;
import com.example.exceptions.InvalidTransactionException;
import com.example.models.Account;
import com.example.models.BankAccount;

public class CustomerS {
	private int userId;
	private AccountDao acDao;
	private BankAccountDao apDao;
	
	public CustomerS(int userId,AccountDao ac, BankAccountDao ap) {
		this.userId = userId;
		acDao = ac;
		apDao = ap;
	}
	
	public void apply(double start) {
		try {
		apDao.createBankAccount(userId, start);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void deposit(int accId,double amount)throws InvalidTransactionException{
		if(amount < 0.00) {
			throw new InvalidTransactionException();
		}
		Account a = acDao.getAccountById(accId);
		Double current = a.getBalance();
		current = current + amount;
		a.setBalance(current);
		acDao.updateAccount(a);
		
	}
	
	public void withdrawl(int accId,double amount)throws InvalidTransactionException{
		if(amount <0.00) {
			throw new InvalidTransactionException();
		}
		Account a = acDao.getAccountById(accId);
		Double current = a.getBalance();
		if(current-amount <0) {
			throw new InvalidTransactionException();
		}else {
			current = current - amount;
			a.setBalance(current);
			acDao.updateAccount(a);
		}
	}
	
	
	public void checkApplications() {
		BankAccount a = apDao.getBankAccountByUserId(userId);
		switch(a.getStatus().toLowerCase()) {
		case "approved":
			System.out.println("Application is accepted with a default pin of (0000)");
			apDao.removeBankAccount(a);
			break;
		case "denied" :
			System.out.println("Application is denied");
			apDao.removeBankAccount(a);
			break;
		default :
			System.out.println("No account in queue");
			break;
			
		}
		
	}
	public void changePin(int accId, int currentPin, int pin) {
		Account a = acDao.getAccountById(accId);
		if(a.getPin()==currentPin) {
			a.setPin(pin);
			acDao.updateAccount(a);
			System.out.println("New PIN created");
		}else {
			System.out.println("Invalid PIN, try again");
		}
		
	}
	public List<Account> getUserAccounts() {
		List<Account> accountList = acDao.getUserAccounts(userId);
		return accountList;
	}
	 
}
