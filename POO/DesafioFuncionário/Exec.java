package listas;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Exec {

	private static final int ADD_EMPLOYEE = 1;
	private static final int REMOVE_EMPLOYEE = 2;
	private static final int INCREASE_SALARY = 3;
	private static final int LIST_EMPLOYEES = 4;

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner teclado = new Scanner(System.in);
		ArrayList<Employer> func = new ArrayList<>();

		int resp;

		System.out.println("Bem vindo ao painel de funcionários!");
		// Programa principal
		do {
			System.out.println("O que deseja fazer?");
			showMenu();

			resp = checkOptions(teclado);

			switch (resp) {
			// Adicionar funcionário
			case ADD_EMPLOYEE:
				System.out.println();
				System.out.println("Vou precisar de alguns dados desse funcionário,"
						+ " para cancelar, digite 0 a qualquer momento!");
				System.out.println();

				addEmployee(teclado, func);
				break;

			// Remover funcionário
			case REMOVE_EMPLOYEE:
				if (func.size() == 0) {
					System.out.println();
					System.out.println("Não há funcionários há serem removidos!");
					System.out.println();
					break;
				}

				removeEmployee(teclado, func);
				break;
			// ALteração de salário
			// Concertar caso 3, está incompleto
			case INCREASE_SALARY:

				if (func.size() == 0) {
					System.out.println();
					System.out.println("Não há funcionários para que o salário seja alterado!");
					System.out.println();
					break;
				}

				increaseSalary(teclado, func);
				break;

			// Listar funcionários
			case LIST_EMPLOYEES:
				if (func.size() == 0) {
					System.out.println();
					System.out.println("Não há funcionários há serem listados!");
					System.out.println();
					break;
				}

				listEmployees(teclado, func);
				break;
			}

		} while (resp != 0);

		System.out.println();
		System.out.println("Tenha um bom dia!");
		teclado.close();

	}

	private static int checkOptions(Scanner teclado) {
		int[] options = new int[] { 1, 2, 3, 4, 0 };
		int n;

		while (true) {
			try {
				System.out.print("COMANDO: ");

				n = teclado.nextInt();
			} catch (InputMismatchException e) {
				System.out.println(
						"Você não digitou um número inteiro" + ", favor considere digitar uma das opções disponíveis!");
				teclado.nextLine();
				showMenu();
				continue;
			}

			for (int x : options) {
				if (n == x) {
					return n;
				}
			}
			System.out.println("Parece que o número digitado não corresponde" + " a uma das opções, tente novamente!");
			showMenu();
		}
	}

	private static void showMenu() {
		System.out.println();
		System.out.println("1-Adicionar funcionário");
		System.out.println("2-Remover funcionário");
		System.out.println("3-Alteração de salário");
		System.out.println("4-Exibir funcionários");
		System.out.println("0-Fechar o programa");
		System.out.println();
	}

	private static void addEmployee(Scanner teclado, ArrayList<Employer> func) {

		while (true) {

			// Id check
			int id;
			while (true) {
				System.out.print("Id: ");

				try {
					id = teclado.nextInt();
				} catch (InputMismatchException e) {
					System.out.println();
					System.out.println("Digite um número válido!");
					System.out.println();
					teclado.nextLine();
					continue;
				}
				if (id < 0) {
					System.out.println();
					System.out.println("Digite um número positivo, ou 0 para cancelar!");
					System.out.println();
					continue;
				}

				boolean exists = false;

				for (Employer employ : func) {
					if (employ.getId() == id) {
						System.out.println();
						System.out.println("Nós já possuímos um empregado com esse id!");
						System.out.println(employ);
						System.out.println();
						exists = true;
					}
				}

				if (exists) {
					continue;
				}

				break;
			}

			if (id == 0) {
				System.out.println();
				System.out.println("OK, retornando ao início!");
				System.out.println();
				break;
			}

			// Object's name
			System.out.print("Name:");
			teclado.nextLine();
			String name = teclado.nextLine();

			if (name.equals("0")) {
				System.out.println();
				System.out.println("OK, retornando ao início!");
				System.out.println();
				break;
			}

			name = name.trim();

			// salary check
			double salary;

			while (true) {
				System.out.print("Salary:");

				try {
					salary = teclado.nextDouble();
				} catch (InputMismatchException e) {
					System.out.println();
					System.out.println("Digite um número real ou 0 para cancelar!");
					System.out.println();
					teclado.nextLine();
					continue;
				}

				if (salary < 0) {
					System.out.println();
					System.out.println("Digite um valor positivo!");
					System.out.println();
					continue;
				}

				break;
			}

			if (salary == 0) {
				System.out.println();
				System.out.println("Ok, retornando ao início!");
				System.out.println();
				break;
			}

			func.add(new Employer(id, name, salary));
			System.out.println();
			break;
		}
	}

	private static void removeEmployee(Scanner teclado, ArrayList<Employer> func) {
		System.out.println();
		System.out.println("Digite o id do funcionário para que possamos deleta-lo");
		System.out.print("id:");

		int id = teclado.nextInt();

		/*
		 * Não se pode remover uma instância enquanto a lista está sendo iterada, por
		 * isto a variável "remove" foi criada
		 */
		Employer remove = null;

		for (Employer employ : func) {
			if (employ.getId() == id) {
				System.out.println();
				System.out.println(employ);
				System.out.println("Deletamento concluído");
				System.out.println();
				remove = employ;
				break;
			}
		}

		if (remove == null) {
			System.out.println();
			System.out.println("Não há um funcionário com este id!");
			System.out.println();
		} else {
			func.remove(remove);
		}
	}

	private static void increaseSalary(Scanner teclado, ArrayList<Employer> func) {
		int id;
		double percent;

		while (true) {
			try {
				System.out.println();
				System.out.println("Entre com o id do funcionário que terá o salário aumentado:");
				System.out.print("id: ");
				id = teclado.nextInt();

				System.out.print("Quantos porcento de aumento: ");
				percent = teclado.nextDouble();

			} catch (InputMismatchException e) {
				System.out.println("Insira um número válido!");
				continue;
			}
			break;
		}

		if (id <= 0 || percent <= 0) {
			System.out.println();
			System.out.println("Retornando ao início!");
			System.out.println();
		} else {

			for (Employer employ : func) {
				if (employ.getId() == id) {
					employ.increaseSalary(percent);

					System.out.println();
					System.out.println("Salário aumentado com sucesso!");
					System.out.println();
					System.out.println(employ);
					System.out.println();
					break;
				} else {
					System.out.println();
					System.out.println("Não há funcionário com esse id!");
					System.out.println();
					break;
				}
			}
		}

	}

	private static void listEmployees(Scanner teclado, ArrayList<Employer> func) {
		System.out.println();
		System.out.println("Lista de funcionários:");
		for (Employer fun : func) {
			System.out.println(fun);
		}
		System.out.println();
	}
}