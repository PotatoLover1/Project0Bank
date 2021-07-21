package com.example.dao;

import java.sql.SQLException;
import java.util.List;

import com.example.models.BankAccount;

public interface BankAccountDao {
	List<BankAccount> getAllBankAccounts();

	BankAccount getBankAccountByUserId(int userId);

	void createBankAccount(int userId,double startBalance) throws SQLException;

	void updateBankAccount(BankAccount a);
	
	void removeBankAccount(BankAccount a);
	

}
