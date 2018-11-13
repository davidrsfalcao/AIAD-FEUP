package communication.handlers.postOffice;

import agents.PostOffice;
import communication.messages.DeliveryDoneMessage;
import elements.Order;
import elements.PostManID;
import jade.lang.acl.ACLMessage;

public class DeliveryDoneHandler {
	
	   public DeliveryDoneHandler(){
	        super();
	    }
	   
	   static ACLMessage parse(ACLMessage message, PostOffice postOffice) {

		   DeliveryDoneMessage content = new DeliveryDoneMessage(message);
	       postOffice.setOrderDelivered(content.getOrderID());
	       long ATime = System.currentTimeMillis();
	       Order order = postOffice.getOrderByID(content.getOrderID());
	       long temp = (ATime - order.getCreationTime())/3600000;
	       PostManID postman = postOffice.getPostManByAID(message.getSender());
	       if(temp > order.getTimeToDelivery()) {
	    	   postman.finishDelivery(0);
	       }else {
	    	   postman.finishDelivery(1);
	       }
	       
	       return null;
	       

	    }

}
