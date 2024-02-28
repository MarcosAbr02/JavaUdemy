package app6;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import entites6.Pessoa;
import entites6.PessoaFisica;
import entites6.PessoaJurídica;

public class Program6 {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner teclado = new Scanner(System.in);
		ArrayList<Pessoa> pessoas = new ArrayList<>();

		System.out.print("Digirte o número de devedores: ");
		int n = teclado.nextInt();

		for (int i = 1; i <= n; i++) {
			System.out.println("Dados do " + i + "# devedor:");

			System.out.print("Pessoa fisíca ou jurídica? (f/j)? ");
			char resp = teclado.next().toLowerCase().charAt(0);

			System.out.print("Nome: ");
			teclado.nextLine();
			String nome = teclado.nextLine();

			System.out.print("Renda anual: ");
			double rendaAnual = teclado.nextDouble();

			if (resp == 'f') {
				System.out.print("Despesas médicas: ");
				double despesasSaude = teclado.nextDouble();

				pessoas.add(new PessoaFisica(nome, rendaAnual, despesasSaude));
			} else {
				System.out.print("Número de empregados: ");
				int numeroFuncionarios = teclado.nextInt();

				pessoas.add(new PessoaJurídica(nome, rendaAnual, numeroFuncionarios));
			}
		}

		System.out.println();
		System.out.println("Impostos pagos:");

		double soma = 0;
		for (Pessoa pessoa : pessoas) {
			System.out.println(pessoa.getNome() + ": R$ " + String.format("%.2f", pessoa.imposto()));
			soma += pessoa.imposto();
		}

		System.out.println();
		System.out.printf("Impostos totais: R$ %.2f", soma);

		teclado.close();
	}
}
