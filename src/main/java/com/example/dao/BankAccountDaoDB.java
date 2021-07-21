package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.models.BankAccount;
import com.example.utils.ConnectionUtil;

public class BankAccountDaoDB implements BankAccountDao{
	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();
	@Override
	public List<BankAccount> getAllBankAccounts() {
		List<BankAccount> bankaccountList = new ArrayList<BankAccount>();
		try {
			Connection con = conUtil.getConnection();
			String sql = "SELECT * FROM application";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				bankaccountList.add(new BankAccount(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getDouble(4)));
			}
			return bankaccountList;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BankAccount getBankAccountByUserId(int userId) {
		BankAccount a = new BankAccount();
		try {
		Connection con = conUtil.getConnection();
		String sql = "SELECT * FROM application WHERE customer_id = "+ userId;
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
		a.setId(rs.getInt(1));
		a.setStatus(rs.getString(2));
		a.setUserId(rs.getInt(3));
		a.setStartBalance(rs.getDouble(4));
		
		return a;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void createBankAccount(int userId, double startBalance) throws SQLException {
		try {
		Connection con = conUtil.getConnection();
		String sql = "INSERT INTO application(customer_id,balance) VALUES (?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, userId);
		ps.setDouble(2, startBalance);
		
		ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateBankAccount(BankAccount a) {
		try {
			Connection con = conUtil.getConnection();
			String sql = "UPDATE application SET status = ? WHERE customer_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, a.getStatus());
			ps.setInt(2, a.getUserId());
			ps.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void removeBankAccount(BankAccount a) {
		try {
			Connection con = conUtil.getConnection();
			String sql = "DELETE FROM application WHERE account_id = "+a.getId();
			Statement s = con.createStatement();
			s.executeQuery(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
