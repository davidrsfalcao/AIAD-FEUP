package communication.handlers.postMan;

import agents.PostMan;
import communication.messages.ProposalResponse;
import elements.Order;
import jade.lang.acl.ACLMessage;

public class DecisionHandler {

    static ACLMessage parse(ACLMessage message, PostMan postMan) {

        if(message.getPerformative() == ACLMessage.REJECT_PROPOSAL){
            postMan.setPendingOrder(null);
        }
        else{
            ProposalResponse response = new ProposalResponse(message);
            int orderId = response.getOrderId();

            Order order = new Order(postMan.getPendingOrder().getPosition(), postMan.getPendingOrder().getEstimatedTime());
            order.setID(orderId);

            System.out.println("[POSTMAN " + postMan.getPostManName() + " ] Added order with ID:" +  orderId);

            postMan.addOrder(order);
            postMan.setPendingOrder(null);
        }

        return null;
    }


}
