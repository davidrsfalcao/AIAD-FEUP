package elements;

import jade.core.AID;

/**
 * Class for saving basic information about a PostMan
 */
public class PostManID {
	private String name;
	private AID id;
	private double rating;
	private int nrDeliveries;

    /**
     * Constructor
     *
     * @param id - AID of the postman
     */
	public PostManID(AID id) {
		name = id.getLocalName();
		this.id = id;
		rating = 1;
		nrDeliveries = 0;
	}

    /**
     * Returns the PostMan name
     *
     * @return name - PostMan name
     */
	public String getName() {
		return name;
	}

    /**
     * Returns the AID of PostMan
     *
     * @return id - PostMan AID
     */
	public AID getId() {
		return id;
	}

    /**
     * Returns the average rating of the PostMan
     *
     * @return rating - Average rating
     */
	public double getRating() {
		return rating;
	}

    /**
     * Called when the PostMan finish a order. Updates the number of deliveries and the rating
     *
     * @param rating - the rating of the finished order
     */
	public void finishDelivery(int rating) {
		double num = this.rating * nrDeliveries;
		nrDeliveries++;

		this.rating = (num+rating)/ nrDeliveries;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostManID other = (PostManID) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
