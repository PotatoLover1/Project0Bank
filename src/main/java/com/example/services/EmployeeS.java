package com.example.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.dao.AccountDao;
import com.example.dao.BankAccountDao;
import com.example.models.Account;
import com.example.models.BankAccount;


public class EmployeeS {
	private AccountDao acDao;
	private BankAccountDao apDao;
	
	public EmployeeS(AccountDao ac, BankAccountDao ap) {
		acDao = ac;
		apDao = ap;
	}
	public void viewAccounts() {
		ArrayList<Account> accountList = new ArrayList<Account>();
		accountList = (ArrayList<Account>) acDao.getAllAccounts();
		for(Account a : accountList) {
			System.out.println(a);
		}
	}
	
	
	public void reviewApplications(Scanner in) {
		int input;
		List<BankAccount> appList = new ArrayList<BankAccount>();
		appList = apDao.getAllBankAccounts();
		for(BankAccount a : appList) {
			System.out.println(a);
			System.out.print("Press 1 to Approve, Press 2 to Reject: ");
			input = Integer.parseInt(in.nextLine());
			switch(input) {
			case 1: 
				a.setStatus("approved");
				apDao.updateBankAccount(a);
				Account acc = new Account(a.getStartBalance(),a.getUserId(),0000);
				try{
					acDao.createAccount(acc);
				}catch(SQLException e) {
					e.printStackTrace();
				}
				System.out.println("approved");
				break;
			case 2:
				a.setStatus("denied");
				apDao.updateBankAccount(a);
				System.out.println("denied");
				break;
			default: 
				System.out.println("Invalid input, application is set to pending");
				break;
			
			}
		}
	}
}

