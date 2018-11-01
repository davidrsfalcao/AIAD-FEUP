package elements;

/**
 * Class that represents a Order
 *
 */
public class Order {
	private Point destiny;
	private int timeToDelivery;

    /**
     * Order constructor
     *
     * @param destiny - Point to delivery the order
     * @param timeToDelivery - Limit of time for delivering the order
     */
	public Order(Point destiny,int timeToDelivery) {
		this.destiny= destiny;
		this.timeToDelivery=timeToDelivery;
	}

    /**
     * Returns the destination of the order
     *
     * @return destiny - The destination of the order
     */
	public Point getDestiny() {
		return destiny;
	}

    /**
     * Returns the limit time for delivering the order
     *
     * @return timeToDelivery - limit time
     */
	public int getTimeToDelivery() {
		return timeToDelivery;
	}

}
