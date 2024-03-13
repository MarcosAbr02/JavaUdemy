package model.entities;

import model.exceptions.WithdrawException;

public class Account {
	private Integer number;
	private String holder;
	private Double balance;
	private Double withdrawLimit;

	public Account(int number, String holder, double balance, double withdrawLimit) {
		this.number = number;
		this.holder = holder;
		this.balance = balance;
		this.withdrawLimit = withdrawLimit;
	}

	public int getNumber() {
		return number;
	}

	public String getHolder() {
		return holder;
	}

	public double getBalance() {
		return balance;
	}

	public double getWithdrawLimit() {
		return withdrawLimit;
	}

	public void deposit(Double amount) {
		balance += amount;
	}

	public void withdraw(Double amount) throws WithdrawException {

		if (amount > withdrawLimit) {
			throw new WithdrawException("A quantia de saque é superior ao seu limite de saque.");
		}
		if (amount > balance) {
			throw new WithdrawException("A quantia de saque é superior a quantia em conta.");
		}

		balance -= amount;
	}
}
