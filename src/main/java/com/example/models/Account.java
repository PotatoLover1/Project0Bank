package com.example.models;

public class Account {
	private int id;
	private double balance;
	private int uId;
	private int pin;
	public Account() {
		super();
	}
	public Account(int id , double balance, int uId, int pin) {
		super();
		this.id=id;
		this.balance=balance;
		this.uId=uId;
		this.pin=pin;
	}
	public Account(double balance,int uId, int pin) {
		super();
		this.balance=balance;
		this.uId=uId;
		this.pin=pin;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", uId=" + uId + "]";
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	
}
