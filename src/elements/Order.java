package elements;

/**
 * Class that represents a Order
 *
 */
public class Order {
	private int ID;
	private Point destiny;
	private int timeToDelivery;
	private int type; /* 0 : pick up | 1 : delivery | 2 : delivered*/

    /**
     * Order constructor
     *
     * @param destiny - Point to delivery the order
     * @param timeToDelivery - Limit of time for delivering the order
     */
	public Order(Point destiny,int timeToDelivery) {
		this.destiny = destiny;
		this.timeToDelivery = timeToDelivery;
		this.type = 1;
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

    /**
     * Returns the type of the order
     *
     * @return type - 0 : pick up | 1 : delivery
     */
    public int getType() {
        return type;
    }

    /**
     * Sets up the type of the order
     *
     * @param type - 0 : pick up | 1 : delivery
     */
    public void setType(int type) {
        this.type = type;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
