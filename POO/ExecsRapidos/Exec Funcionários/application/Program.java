package application;

import entities.Account;
import entities.BussinessAccount;
import entities.SavingsAccount;

class Program {
	public static void main(String[] args) {

		Account acc = new Account(1001, "Alex", 0.0);
		BussinessAccount bacc = new BussinessAccount(1002, "maria", 0.0, 500.0);

		// UPCASTING

		Account acc1 = bacc;

		Account acc2 = new BussinessAccount(1003, "bob", 0.0, 200.0);

		Account acc3 = new SavingsAccount(1004, "ana", 0.0, 0.01);

		System.out.println(acc2.getClass());

		// DOWNCASTING

		BussinessAccount acc4 = (BussinessAccount) acc1;
		// Informação não é perdida
		System.out.println(acc4.getLoanLimit());

		// Checa se acc3 pertence a classe BussinessAccount
		// Não é permitindo upcasting e downcasting entre SavingAccount e
		// BussinesAccount pois estas não
		// herdam uma a outra, apesar de ambas herdarem da classe Account.

		if (acc3 instanceof BussinessAccount) {
			BussinessAccount acc5 = (BussinessAccount) acc3;
			acc5.loan(200.0);
			System.out.println("loan");
		}

		if (acc3 instanceof SavingsAccount) {
			SavingsAccount acc5 = (SavingsAccount) acc3;
			acc5.updateBalance();
			System.out.println("update");
		}

		if (acc4 instanceof Account) {
			System.out.println("sim");

		}

	}
}
