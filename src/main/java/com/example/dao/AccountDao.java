package com.example.dao;

import java.sql.SQLException;
import java.util.List;

import com.example.models.Account;

public interface AccountDao {
	List<Account> getAllAccounts();
	void createAccount(Account a)throws SQLException;
	void updateAccount(Account a);
	List<Account> getUserAccounts(int userId);
	Account getAccountById(int id);
}
