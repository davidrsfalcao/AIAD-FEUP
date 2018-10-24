package elements;

public class Order {
	private Point destiny;
	private int timeToDelivery;
	
	public Order(Point destiny,int timeToDelivery) {
		this.destiny= destiny;
		this.timeToDelivery=timeToDelivery;
	}
	
	public Point getDestiny() {
		return this.destiny;
	}
	
	public int getTimeToDelivery() {
		return this.timeToDelivery;
	}

	

}
