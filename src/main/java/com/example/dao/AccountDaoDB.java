package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Account;
import com.example.utils.ConnectionUtil;

public class AccountDaoDB implements AccountDao{
	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();
	@Override
	public List<Account> getAllAccounts() {
		List<Account> accountList = new ArrayList<Account>();
		
		try {
			Connection con = conUtil.getConnection();
			String sql = "SELECT * FROM accounts";
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {

				accountList.add(new Account(rs.getInt(1),rs.getDouble(2),rs.getInt(3),rs.getInt(4)));
			}
			return accountList;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void createAccount(Account a) throws SQLException {
		Connection con = conUtil.getConnection();
		String sql = "INSERT INTO accounts(balance, customer_id, pin) values"
				+ "(?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setDouble(1, a.getBalance());
		ps.setInt(2, a.getuId());
		ps.setInt(3, a.getPin());
		
		ps.execute();
		
	}

	@Override
	public void updateAccount(Account a) {
		
		try {
			Connection con = conUtil.getConnection();
			String sql = "UPDATE accounts SET balance = ?, pin = ? "
					+ " WHERE accounts.account_id = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setDouble(1, a.getBalance());
			ps.setInt(2, a.getPin());
			ps.setInt(3, a.getId());
			
			ps.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Account> getUserAccounts(int userId) {
		List<Account> accountList = new ArrayList<Account>();
		try {
			Connection con = conUtil.getConnection();
			String sql = "SELECT * FROM accounts WHERE customer_id = " + userId;
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				accountList.add(new Account(rs.getInt(1),rs.getDouble(2),rs.getInt(3),rs.getInt(4)));
			}
			return accountList;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Account getAccountById(int id) {
		Account a = new Account();
		try {
			Connection con = conUtil.getConnection();
			String sql = "SELECT * FROM accounts WHERE account_id = " + id;
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			a.setId(rs.getInt(1));
			a.setBalance(rs.getDouble(2));
			a.setuId(rs.getInt(3));
			a.setPin(rs.getInt(4));
			return a;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
