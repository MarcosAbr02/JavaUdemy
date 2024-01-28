package entities_2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import entities_2.enums.OrderStatus;

public class Order {
	private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	private LocalDateTime moment = LocalDateTime.now();
	private OrderStatus status;
	private Client client;

	private ArrayList<OrderItem> orders = new ArrayList<>();

	public Order(OrderStatus status, Client client) {
		this.status = status;
		this.client = client;
	}

	public LocalDateTime getMoment() {
		return moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public Client getClient() {
		return client;
	}

	public ArrayList<OrderItem> getOrders() {
		return orders;
	}

	public void addItem(OrderItem orderItem) {
		orders.add(orderItem);
	}

	public void removeItem(OrderItem item) {
		orders.remove(item);
	}

	public Double total() {
		double total = 0;
		for (OrderItem o : orders) {
			total += o.subTotal();
		}

		return total;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("ORDER SUMMARY:\n");
		sb.append("Order moment:"+FMT.format(moment)+"\n");
		sb.append("Order Status: "+status+"\n");
		sb.append("Client: "+client+"\n");
		sb.append("ORDER ITEMS:\n");
		for(OrderItem o : orders) {
			sb.append(o+"\n");
		}
		sb.append("Total Price: "+total());
		
		return sb.toString();
		}
}
