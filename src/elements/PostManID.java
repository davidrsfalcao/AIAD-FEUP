package elements;

import jade.core.AID;

public class PostManID {
	private String name;
	private AID id;
	private double rating;
	private int nrEntregas;
	
	public PostManID(String name,AID id) {
		this.name=name;
		this.id=id;
		this.rating=0;
		this.nrEntregas=0;
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
		return this.name;
	}
	public AID getid() {
		return this.id;
	}
	public double getRating() {
		return rating;
	}
	
	public void finishDelivery(double rating) {
		double num = this.rating * nrEntregas;
		this.nrEntregas++;
		this.rating = (num+rating)/nrEntregas;
	}

}
