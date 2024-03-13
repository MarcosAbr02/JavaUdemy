package application;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Account;
import model.exceptions.WithdrawException;

public class Program4 {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner teclado = new Scanner(System.in);

		try {
			System.out.println("Entre com os dados da conta");

			System.out.print("Número: ");
			int number = teclado.nextInt();

			System.out.print("Holder: ");
			teclado.nextLine();
			String holder = teclado.nextLine();

			System.out.print("Balanço inicial: ");
			double balance = teclado.nextDouble();

			System.out.print("Limite de saque: ");
			double withdrawLimit = teclado.nextDouble();

			Account account = new Account(number, holder, balance, withdrawLimit);

			saque(teclado, account);

			System.out.printf("Novo saldo %.2f", account.getBalance());

		} catch (InputMismatchException e) {
			System.out.println("Dados incosistentes foram digitados, encerrando o programa.");
		}

		teclado.close();
	}

	public static void saque(Scanner teclado, Account account) {
		while (true) {
			try {
				System.out.println();
				System.out.print("Digite a quantidade para saque: ");
				double amount = teclado.nextDouble();

				account.withdraw(amount);
				break;
			} catch (WithdrawException e) {
				System.out.println("Erro de Saque: " + e.getMessage());
				teclado.nextLine();
				continue;
			}
		}
	}
}
