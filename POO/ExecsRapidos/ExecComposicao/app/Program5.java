package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities_2.*;
import entities_2.enums.OrderStatus;

public class Program5 {
	public static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner teclado = new Scanner(System.in);

		System.out.println("Digite os dados do cliente:");

		System.out.print("Name: ");
		String name = teclado.nextLine();

		System.out.print("Email: ");
		String email = teclado.next();

		System.out.print("Birth date (DD/MM/YYYY): ");
		String date = teclado.next();

		System.out.println("Digite o status do pedido:");
		System.out.print("Status: ");

		Order pedido = new Order(OrderStatus.valueOf(teclado.next().toUpperCase()),
				new Client(name, email, LocalDate.parse(date, FMT)));

		System.out.println("Quantos itens terão nesse pedido?");
		int n = teclado.nextInt();

		for (int i = 0; i < n; i++) {
			System.out.println("Entre com os dados do " + (i + 1) + "# pedido:");

			System.out.print("Product Name: ");
			String productName = teclado.next();

			System.out.print("Product Price: ");
			double price = teclado.nextDouble();

			System.out.print("Quantity: ");
			int quantity = teclado.nextInt();

			pedido.addItem(new OrderItem(quantity, new Product(productName, price)));
		}
		
		teclado.close();
		System.out.println(pedido);
	}
}
