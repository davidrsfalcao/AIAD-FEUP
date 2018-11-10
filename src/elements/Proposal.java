package elements;

public class Proposal {
	
	private PostManID postman;
	private double price;
	private Order order;
	
	public Proposal(PostManID postman, double price, Order order) {
		this.postman = postman;
		this.price = price;
		this.order = order;
	}
	
	public PostManID getPostManID() {
		return postman;
	}

	public double getPrice() {
		return price;
	
	}
	public Order getOrder() {
		return order;
	}

}
