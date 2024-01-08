package matrizes;

import java.util.Scanner;

public class Matriz2 {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);

		System.out.println("Número de linhas:");
		int m = teclado.nextInt();
		System.out.println("Número de colunas:");
		int n = teclado.nextInt();

		int[][] mat = new int[m][n];

		System.out.println("Digite " + m * n + " números:");

		// povoando matriz
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				mat[i][j] = teclado.nextInt();
			}
		}

		System.out.println("Digite um número x:");
		int x = teclado.nextInt();

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				if (mat[i][j] == x) {
					System.out.println("Position: " + i + "," + j + ":");

					if (j - 1 >= 0) {
						System.out.println("Left: " + mat[i][j - 1]);
					}
					
					if (i - 1 >= 0) {
						System.out.println("Up: " + mat[i - 1][j]);
					}
					
					if (j + 1 < mat[i].length) {
						System.out.println("Right: "+mat[i][j+1]);
					}
					
					if (i+1<mat.length) {
						System.out.println("Down: "+mat[i+1][j]);
					}
					System.out.println();
				}
			}
			
		}

		teclado.close();
	}
}
