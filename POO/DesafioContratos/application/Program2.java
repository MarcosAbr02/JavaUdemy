package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import entites.Department;
import entites.HourContract;
import entites.Worker;
import entites.enums.WorkerLevel;

public class Program2 {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner teclado = new Scanner(System.in);

		ArrayList<Worker> workers = new ArrayList<>();

		System.out.println("Bem-vindo ao sistema de gerenciamento de funcionários!");

		boolean principal = true;
		while (principal) {
			menu();

			int opc;
			try {
				opc = teclado.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Digite um número inteiro!");
				teclado.nextLine();
				continue;
			}

			switch (opc) {
			case ADD_WORKER:
				addWorker(teclado, workers);
				break;

			case REMOVE_WORKER:
				removeWorker(teclado, workers);
				break;

			case EDIT_WORKER_CONTRACTS:
				editWorkerContracts(teclado, workers);
				break;

			case SHOW_WORKER_CONTRACTS:
				if (workers.isEmpty()) {
					System.out.println("Não há funcionários para que sejam exibidos contratos!");
					break;
				}

				showWorkers(workers);

				System.out.println("\nDigite o índice do funcionário que deseja digitar!");

				int index;
				while (true) {
					try {
						index = teclado.nextInt();

						if (index < 0 || index > workers.size()) {
							System.out.println("O número digitado não corresponde a nenhum funionário!");
							continue;
						}

						break;
					} catch (InputMismatchException e) {
						System.out.println("\nDigite um número válido ou 0 para sair.");
						teclado.nextLine();
						continue;
					}
				}
				
				if (index == 0) {
					returnMsg();
					break;
				}

				// Array List começam em 0 e lista exibição "showWorkers" começa em 0
				Worker opcWorker = workers.get(index - 1);
				
				showWorkerContracts(opcWorker);

				break;
			case SHOW_WORKERS:
				showWorkers(workers);
				break;
			case EXIT:
				principal = false;
				System.out.println("\nEncerrando o programa");
				break;
			default:
				System.out.println("\nO número digitado não corresponde a nenhuma das opções!");
				break;
			}

		}
	}

	public static void addWorker(Scanner teclado, ArrayList<Worker> workers) {
		System.out.println("\nDigite 0 para desistir da ação a qualquer momento!");

		System.out.print("\nEntre com o nome do departamento: ");
		String department = teclado.next();

		if (department.equals("0")) {
			returnMsg();
			return;
		}

		System.out.println("\nAgora, precisarei dos dados desse funcionário!\n");

		System.out.print("Name:");
		String name = teclado.next();

		if (name.equals("0")) {
			returnMsg();
			return;
		}

		System.out.print("Level:");

		WorkerLevel level;
		while (true) {
			try {
				String temp = teclado.next().toUpperCase();
				if (temp.equals("0")) {
					returnMsg();
					return;
				}

				level = WorkerLevel.valueOf(temp);
				break;

			} catch (IllegalArgumentException e) {
				System.out.println("\nOpa, parece que você digitou um valor que não corresponde a nenhum level:\n");
				System.out.println("JUNIOR,\n" + "MID_LEVEL,\n" + "SENIOR\n");
				System.out.println("Digite um level dessa exata maneira!\n");
				continue;
			}
		}

		System.out.print("Salário Base:");

		double baseSalary;
		while (true) {
			try {
				baseSalary = teclado.nextDouble();
				break;
			} catch (InputMismatchException e) {
				System.out.println("\nPor favor insira um número válido!");
				teclado.nextLine();
				continue;
			}
		}

		if (baseSalary <= 0) {
			returnMsg();
			return;
		}

		workers.add(new Worker(name, level, baseSalary, new Department(department)));
	}

	public static void removeWorker(Scanner teclado, ArrayList<Worker> workers) {
		System.out.println("\nDigite 0 para desistir da ação a qualquer momento!");

		if (workers.size() == 0) {
			System.out.println("\nParece que não há funcionários há serem deletados!");
			return;
		}

		showWorkers(workers);

		System.out.println("\nDigite o indíce do funcionário que deseja apagar:");

		int index;
		while (true) {
			try {
				index = teclado.nextInt();
				if (index > workers.size() || index < 0) {
					System.out.println("\nParece que o índice digitado não corresponde a nenhum funcionário"
							+ ", por favor tente novamente ou digite 0 para sair");
					continue;
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("\nDigite um número inteiro ou 0 para sair!");
				teclado.nextLine();
				continue;
			}
		}

		if (index == 0) {
			returnMsg();
			return;
		}

		System.out.println("\nFuncionário removido:");
		System.out.println(workers.get(index - 1));
		workers.remove(index - 1);
	}

	public static void editWorkerContracts(Scanner teclado, ArrayList<Worker> workers) {
		showWorkers(workers);

		System.out.println("\nDigite o indíce do funcionário do qual você deseja alterar os contratos!");

		int index;
		while (true) {
			try {
				index = teclado.nextInt();

				if (index < 0 || index > workers.size()) {
					System.out.println("\nParece que o índice digitado não corresponde a nenhum funcionário"
							+ ", por favor tente novamente ou digite 0 para sair");
					teclado.nextLine();
					continue;
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("\nDigite um número válido!");
				continue;
			}
		}

		// -1 Pois o arrayList começa em 0 mas os funcionários são listados a partir do
		// 1
		Worker opcWorker = workers.get(index - 1);

		if (index <= 0 || index > workers.size()) {
			returnMsg();
			return;
		}

		showWorkerContracts(opcWorker);

		boolean menuEditContracts = true;
		while (menuEditContracts) {

			System.out.println("\nOque deseja fazer?");
			menuEditContracts();

			int opc;
			while (true) {
				try {
					opc = teclado.nextInt();
					break;
				} catch (InputMismatchException e) {
					System.out.println("\nDigite um número válido!");
					teclado.nextLine();
					continue;
				}
			}

			if (opc <= 0) {
				returnMsg();
				return;
			}

			switch (opc) {
			case ADD_CONTRACT:
				addContract(teclado, opcWorker);
				break;
			case REMOVE_CONTRACT:
				removeContract(teclado, opcWorker);
				break;
			case SHOW_CONTRACTS:
				showWorkerContracts(opcWorker);
				break;
			case SEARCH_BY_MONTH:
				searchByMonth(teclado, opcWorker);
				break;
			case EXIT:
				returnMsg();
				return;
			default:
				System.out.println("\nO número digitado não corresponde a nenhuma das opções!");
				break;
			}
		}
	}

	public static void showWorkers(ArrayList<Worker> workers) {
		for (int i = 0; i < workers.size(); i++) {
			System.out.println("\n" + (i + 1) + "-" + workers.get(i));
		}

	}

	public static void showWorkerContracts(Worker worker) {
		if (worker.getContracts().isEmpty()) {
			System.out.println("Parece que esse funcionário não possui nenhum contrato cadastrado!");
		} else {
			worker.showContracts();
		}
	}

	public static void menu() {
		System.out.println("\n1-Adicionar funcionário");
		System.out.println("2-Remover Funcionário");
		System.out.println("3-Editar contratos de um funcionário");
		System.out.println("4-Ver contratos de um funcionário");
		System.out.println("5-Listar Funcionários");
		System.out.println("0-Sair");
	}

	public static void returnMsg() {
		System.out.println("\nEntendido! voltando ao início!");
	}

	// Metódos especifícos de contratos
	public static void menuEditContracts() {
		System.out.println("\n1-Adicionar contrato\n2-Remover contrato\n3-Exibir Contratos\n4-Pesquisar contratos por mês e ano\n0-Sair");
	}

	public static void addContract(Scanner teclado, Worker worker) {
		System.out.println("\nCerto, vou precisar dos dados desse contrato:");
		System.out.print("\nData (DD/MM/YYYY): ");

		LocalDate date;
		while (true) {
			try {
				String dateTemp = teclado.next();

				if (dateTemp.equals("0")) {
					returnMsg();
					return;
				}

				DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				date = LocalDate.parse(dateTemp, fmt1);
				break;
			} catch (DateTimeParseException e) {
				System.out.println("Digite uma data no formato DD/MM/YYYY ou 0 para sair");
				teclado.nextLine();
				continue;
			}
		}

		System.out.print("Valor por hora:");

		double valuePerHour;
		while (true) {
			try {
				valuePerHour = teclado.nextDouble();

				if (valuePerHour == 0) {
					returnMsg();
					return;
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("Digite um número real ou 0 para sair!");
				teclado.nextLine();
				continue;
			}
		}

		System.out.print("Duração (horas): ");

		int duration;
		while (true) {
			try {
				duration = teclado.nextInt();

				if (duration == 0) {
					returnMsg();
					return;
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("Digite um número válido ou 0 para sair!");
				teclado.nextLine();
				continue;
			}
		}

		worker.addContract(new HourContract(date, valuePerHour, duration));
		System.out.println("\nContrato adicionado com sucesso!");
		worker.showContracts();
	}

	public static void removeContract(Scanner teclado, Worker worker) {
		System.out
				.println("\nMuito bem, para deletar o contrato vou precisar que você digite a mesma data do contrato");
		worker.showContracts();

		LocalDate date;
		while (true) {
			try {
				System.out.print("\nDigite a data (DD/MM/YYYY): ");
				String dateTemp = teclado.next();

				if (dateTemp.equals("0")) {
					returnMsg();
					return;
				}

				date = LocalDate.parse(dateTemp, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				break;
			} catch (DateTimeParseException e) {
				System.out.println("\nDigite uma data em um formato válido! ou 0 para sair");
			}
		}

		HourContract removeContract = null;
		for (HourContract contract : worker.getContracts()) {
			if (contract.getDate().equals(date)) {
				System.out.println("\nData removida com sucesso!");
				System.out.println(contract);

				removeContract = contract;
				break;
			}
		}

		if (removeContract != null) {
			worker.removeContract(removeContract);
		} else {
			System.out.println("\nNão encontramos nenhum contrato que correponda a essa data!");
		}
	}

	public static void searchByMonth(Scanner teclado, Worker worker) {
		if (worker.getContracts().isEmpty()) {
			System.out.println("Parece que esse funcionário não possui nenhum contrato cadastrado!");
			return;
		}
		System.out.println("\nDigite o mês e ano dos contratos que deseja visualizar:");
		
		System.out.print("\nMês: ");
		int month;
		while(true) {
			try {
			month = teclado.nextInt();
			
			break;
			}
			catch(InputMismatchException e) {
				System.out.println("\nDigite um número válido! ou 0 para sair!");
				teclado.nextLine();
				continue;
			}
		}
		
		if(month==0) {
			returnMsg();
			return;
		}
		
		System.out.print("Ano: ");
		int year;
		while(true) {
			try {
			year = teclado.nextInt();
			break;
		}
			catch(InputMismatchException e ) {
				System.out.println("\nDigite um número válido! ou 0 para sair!");
				teclado.nextLine();
				continue;
			}
		}
		
		if(year==0) {
			returnMsg();
			return;
		}
		
		worker.filterContracts(year, month);
	}

	// Constantes do programa principal
	private static final int EXIT = 0;
	private static final int ADD_WORKER = 1;
	private static final int REMOVE_WORKER = 2;
	private static final int EDIT_WORKER_CONTRACTS = 3;
	private static final int SHOW_WORKER_CONTRACTS = 4;
	private static final int SHOW_WORKERS = 5;

	// Constantes da função de editar contratos
	private static final int ADD_CONTRACT = 1;
	private static final int REMOVE_CONTRACT = 2;
	private static final int SHOW_CONTRACTS = 3;
	private static final int SEARCH_BY_MONTH = 4;
}
