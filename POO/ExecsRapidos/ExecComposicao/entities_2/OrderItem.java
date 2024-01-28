package entities_2;

public class OrderItem {
	private Integer quantity;
	private Product product;

	public OrderItem(Integer quantity, Product product) {
		this.quantity = quantity;
		this.product = product;

	}

	public Integer getQuantity() {
		return quantity;
	}

	public Double subTotal() {
		return product.getPrice()*quantity;
	}
	
	@Override
	public String toString() {
		return product+", "+"Quantidade: "+quantity+String.format
				(", Subtotal: $%.2f",subTotal());
	}
}
