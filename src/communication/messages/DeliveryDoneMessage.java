package communication.messages;

import communication.Header;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class DeliveryDoneMessage extends Message {

	private AID receiver;
	private int orderID;
	
	public DeliveryDoneMessage(AID receiver, int orderID) {
		this.receiver = receiver;
		this.orderID = orderID;
	}
	
    public DeliveryDoneMessage(ACLMessage msg){
        String[] args = msg.getContent().split(SEPARATOR);
        orderID  = Integer.parseInt(args[0]);

    }
    public int getOrderID() {
        return orderID;
    }

	@Override
	public ACLMessage toACL() {
		 ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	     msg.addReceiver(receiver);
	     msg.setOntology(Header.Delivered);
	     msg.setContent(orderID + "");
	     return msg;
	}
	

}
