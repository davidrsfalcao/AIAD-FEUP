package elements;

import jade.core.AID;

public class PostManID {
	private String name;
	private AID id;
	private double rating;
	private int nrEntregas;
	
	public PostManID(AID id) {
		name = id.getLocalName();
		this.id = id;
		rating = 0;
		nrEntregas = 0;
	}
	
	public void setName(String name) {
		this.name=name;
	}

	public void setId(AID id) {
		this.id=id;
	}

	public void setRating(double rating) {
		this.rating=rating;
	}
	
	public String getName() {
		return name;
	}

	public AID getId() {
		return id;
	}

	public double getRating() {
		return rating;
	}
	
	public void finishDelivery(double rating) {
		double num = this.rating * nrEntregas;
		nrEntregas++;
		this.rating = (num+rating)/nrEntregas;
	}

}
