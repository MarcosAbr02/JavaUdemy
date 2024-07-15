package iniciante;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class _038 {
	final static int ESPACAMENTO = 20;

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner teclado = new Scanner(System.in);

		ArrayList<Lanche> lanches = new ArrayList<>();
		lanches.add(new Lanche(1, "Cachorro Quente", 4));
		lanches.add(new Lanche(2, "X-Salada", 4.5));
		lanches.add(new Lanche(3, "X-Bacon", 5));
		lanches.add(new Lanche(4, "Torrada Simples", 2));
		lanches.add(new Lanche(5, "Refrigerante", 1.5));
		lanches.add(new Lanche(6, "X-Tudão", 10));

		ArrayList<Pedido> pedidos = new ArrayList<>();

		boolean on = true;
		while (on) {
			int opc = 0;
			try {
				opc = menuPrincipal(teclado);
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				continue;
			}

			switch (opc) {
			case 1:
				try {
					menuLanche(teclado, lanches, pedidos);
				} catch (InputMismatchException e) {
					System.out.println(e.getMessage());
					continue;
				}
				break;
			case 2:
				try {
					removerLanche(teclado, lanches, pedidos);
				} catch (InputMismatchException e) {
					System.out.println(e.getMessage());
					teclado.nextLine();
					continue;
				}
				break;
			case 3:
				imprimirPedidos(pedidos);
				break;
			case 4:
				imprimirPedidos(pedidos);
				on = false;
				break;
			default:
				System.out.println("Digite uma opção");
				break;
			}

		}
		teclado.close();
	}

	// Menu com as 4 ações possíveis (add, remove, see, end)
	// Lança exceção caso o usuário digite algo não numérico*/
	private static int menuPrincipal(Scanner teclado) throws InputMismatchException {
		for (int i = 0; i < (ESPACAMENTO + 6); i++) {
			System.out.print("-");
		}
		System.out.println();

		System.out.println("|" + stringCentralizada("MENU", ESPACAMENTO + 4) + "|");

		for (int i = 0; i < (ESPACAMENTO + 6); i++) {
			System.out.print("-");
		}
		System.out.println();

		System.out.printf("|1 - %-20s|%n|2 - %-20s|%n|3 - %-20s|%n|4 - %-20s|%n", "Adicionar Lanche", "Remover Lanche",
				"Ver Sacola", "Encerrar Pedido");

		for (int i = 0; i < (ESPACAMENTO + 6); i++) {
			System.out.print("-");
		}
		System.out.println();

		System.out.print(">");
		try {
			int opc = teclado.nextInt();
			return opc;
		} catch (InputMismatchException e) {
			teclado.nextLine();
			throw new InputMismatchException("Digite um número");
		}

	}

	// Permite ao usuário adicionar um lanche a lista.
	// Se o lanche já havia sido adicionado a quantidade pode ser iterada
	private static void menuLanche(Scanner teclado, ArrayList<Lanche> lanches, ArrayList<Pedido> pedidos)
			throws InputMismatchException {

		for (int i = 0; i < (ESPACAMENTO * 3 + 4); i++) {
			System.out.print("-");
		}
		System.out.println();

		// Título
		System.out.println("|" + stringCentralizada("Código", ESPACAMENTO) + "|"
				+ stringCentralizada("Lanche", ESPACAMENTO) + "|" + stringCentralizada("Preço", ESPACAMENTO) + "|");

		for (int i = 0; i < (ESPACAMENTO * 3 + 4); i++) {
			System.out.print("-");
		}
		System.out.println();

		for (Lanche lanche : lanches) {
			System.out.println("|" + stringCentralizada(String.valueOf(lanche.getCode()), 20) + "|"
					+ stringCentralizada(lanche.getName(), ESPACAMENTO) + "|"
					+ stringCentralizada(String.format("R$ %.2f", lanche.getPrice()), ESPACAMENTO) + "|");
		}

		for (int i = 0; i < (ESPACAMENTO * 3 + 4); i++) {
			System.out.print("-");
		}
		System.out.println();
		System.out.print(">");

		int opc = 0;
		int quantidade = 0;

		// Selecionar um lanche
		try {
			opc = teclado.nextInt();

			if (opc <= 0 || opc > lanches.size()) {
				System.out.println("A entrada fornecida não corresponde a uma opção.");
				return;
			}

			// Selecionar uma quantidade
			Lanche escolhido = null;
			for (Lanche lanche : lanches) {
				if (lanche.getCode() == opc) {
					escolhido = lanche;
				}
			}

			// Verifica se o item já está na sacola
			if (!pedidos.isEmpty()) {
				for (Pedido pedido : pedidos) {
					if (pedido.getLanche() == escolhido) {
						System.out.println(pedido);
						System.out.println(
								"Você já possui este lanche adicionado\nDigite a quantidade que deseja adicionar deste");
						System.out.print(">");
						quantidade = teclado.nextInt();

						// Pode lançar exceção
						pedido.addQuantidade(quantidade);
						System.out.println(pedido);
						System.out.println("Quantidade adicionada com sucesso");
						return;
					}
				}
			}

			// Cria um pedido com o lanche
			if (escolhido != null) {
				System.out.println(escolhido.toString());
				System.out.println("Qual a quantidade deste lanche que você deseja inserir?");
				System.out.print(">");

				quantidade = teclado.nextInt();
				if (quantidade <= 0) {
					System.out.println("Quantidade inválida");
					return;
				}

				Pedido pedido = new Pedido(quantidade, escolhido);
				pedidos.add(pedido);

				System.out.println("Quantidade adicionada: " + quantidade);
				System.out.println(pedido);

			} else {
				System.out.println("Nenhum lanche selecionado");
				return;
			}
		} catch (InputMismatchException e) {
			teclado.nextLine();
			throw new InputMismatchException("Digite um número");
		} catch (Exception e) {
			teclado.nextLine();
			System.out.println(e.getMessage());
		}
	}

	// Remove uma quantidade ou o lanche por completo
	private static void removerLanche(Scanner teclado, ArrayList<Lanche> lanches, ArrayList<Pedido> pedidos)
			throws InputMismatchException {
		if (pedidos.isEmpty()) {
			System.out.println("Não há lanches a serem removidos!");
			return;
		}

		imprimirPedidos(pedidos);
		System.out.println("Digite o código do lanche que deseja remover");
		System.out.print(">");

		try {
			int opc = teclado.nextInt();

			if (opc <= 0 || opc > lanches.size()) {
				throw new InputMismatchException("Opção inválida!");
			}

			Pedido escolhido = null;
			for (Pedido pedido : pedidos) {
				if (pedido.getLanche().getCode() == opc) {
					escolhido = pedido;
					break;
				}
			}

			System.out.println(escolhido);

			if (!(escolhido == null)) {
				System.out.println("1-Remoção completa\n2-Remover apenas uma quantidade");
			} else {
				System.out.println("Nenhum lanche selecionado");
				return;
			}
			opc = teclado.nextInt();

			if (opc == 1) {
				pedidos.remove(escolhido);
				System.out.println("Remoção realizada com sucesso");
			}
			if (opc == 2) {
				System.out.println("Digite a quantidade que deseja remover");
				System.out.print(">");
				int quantidade = teclado.nextInt();

				if (quantidade >= escolhido.getQuantidade()) {
					pedidos.remove(escolhido);
					System.out.println("Remoção realizada com sucesso");
				} else if (quantidade <= 0) {
					System.out.println("Quantidade inválida");
				} else {
					escolhido.removerQuantidade(quantidade);
					System.out.println(escolhido);
				}
			}
		} catch (InputMismatchException e) {
			throw new InputMismatchException("Opção inválida!");
		}
	}

	// Métodos auxiliares abaixo
	private static void imprimirPedidos(ArrayList<Pedido> pedidos) {
		if (pedidos.isEmpty()) {
			System.out.println("Não a pedidos a serem exibidos");
			return;
		}

		double total = 0;
		int espacamento = 30;
		for (Pedido pedido : pedidos) {
			total += pedido.total();
		}

		for (int i = 0; i < espacamento; i++) {
			System.out.print("-");
		}
		System.out.println();

		System.out.println("|" + stringCentralizada("Sacola", espacamento - 2) + "|");
		for (Pedido pedido : pedidos) {
			System.out.print(pedido.toSacola(espacamento));
		}

		for (int i = 0; i < espacamento; i++) {
			System.out.print("-");
		}
		System.out.println();

		System.out.println("|" + String.format("%-" + (espacamento - 2) + "s", "Total da sacola: R$ " + total) + "|");

		for (int i = 0; i < espacamento; i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	private static String stringCentralizada(String a, int espacamento) {
		int tamanho = a.length();
		espacamento -= tamanho;

		if (espacamento <= 1) {
			return a;
		}

		int espacamentoEsquerda = espacamento / 2;
		int espacamentoDireita = espacamento - espacamentoEsquerda;

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < espacamentoEsquerda; i++) {
			builder.append(" ");
		}

		builder.append(a);

		for (int i = 0; i < espacamentoDireita; i++) {
			builder.append(" ");
		}

		return builder.toString();
	}

}

class Pedido {
	private int quantidade;
	private Lanche lanche;

	public Pedido(int quantidade, Lanche lanche) throws Exception {
		if (quantidade <= 0) {
			throw new Exception("Quantidade inválida");
		}

		this.quantidade = quantidade;
		this.lanche = lanche;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Lanche getLanche() {
		return lanche;
	}

	public void addQuantidade(int quantidade) throws Exception {
		if (quantidade <= 0) {
			throw new Exception("Quantidade inválida");
		}
		this.quantidade += quantidade;
	}

	public void removerQuantidade(int quantidade) {
		this.quantidade -= quantidade;
	}

	public double total() {
		return lanche.getPrice() * quantidade;
	}

	@Override
	public String toString() {
		String a = "Código: " + lanche.getCode();
		String b = "Lanche: " + lanche.getName();
		String c = "Preço: R$ " + String.format("%.2f", lanche.getPrice());
		String d = "Quantidade: " + quantidade;
		String e = "Total: " + String.format("%.2f", total());

		int maior = Math.max(Math.max(Math.max(Math.max(a.length(), b.length()), c.length()), d.length()), e.length());

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < maior + 4; i++) {
			builder.append("-");
		}
		builder.append("\n");

		builder.append("| ").append(String.format("%-" + maior + "s", a)).append(" |\n");
		builder.append("| ").append(String.format("%-" + maior + "s", b)).append(" |\n");
		builder.append("| ").append(String.format("%-" + maior + "s", c)).append(" |\n");
		builder.append("| ").append(String.format("%-" + maior + "s", d)).append(" |\n");
		builder.append("| ").append(String.format("%-" + maior + "s", e)).append(" |\n");

		for (int i = 0; i < maior + 4; i++) {
			builder.append("-");
		}

		return builder.toString();
	}

	public String toSacola(int maior) {
		String a = "Código: " + lanche.getCode();
		String b = "Lanche: " + lanche.getName();
		String c = "Preço: R$ " + String.format("%.2f", lanche.getPrice());
		String d = "Quantidade: " + quantidade;
		String e = "Total: " + String.format("%.2f", total());
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < maior; i++) {
			builder.append("-");
		}
		builder.append("\n");

		builder.append("| ").append(String.format("%-" + (maior - 4) + "s", a)).append(" |\n");
		builder.append("| ").append(String.format("%-" + (maior - 4) + "s", b)).append(" |\n");
		builder.append("| ").append(String.format("%-" + (maior - 4) + "s", c)).append(" |\n");
		builder.append("| ").append(String.format("%-" + (maior - 4) + "s", d)).append(" |\n");
		builder.append("| ").append(String.format("%-" + (maior - 4) + "s", e)).append(" |\n");

		return builder.toString();
	}
}

class Lanche {
	private int code;
	private String name;
	private double price;

	public Lanche(int code, String name, double price) {
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		String a = "Código: " + code;
		String b = "Nome: " + name;
		String c = "Preço: R$ " + String.format("%.2f", price);

		int maior = Math.max(Math.max(a.length(), b.length()), c.length());

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < maior + 4; i++) {
			builder.append("-");
		}
		builder.append("\n");

		builder.append("| ").append(String.format("%-" + maior + "s", a)).append(" |\n");
		builder.append("| ").append(String.format("%-" + maior + "s", b)).append(" |\n");
		builder.append("| ").append(String.format("%-" + maior + "s", c)).append(" |\n");

		for (int i = 0; i < maior + 4; i++) {
			builder.append("-");
		}
		return builder.toString();
	}
}