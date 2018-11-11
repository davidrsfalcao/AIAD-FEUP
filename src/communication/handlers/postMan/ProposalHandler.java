package communication.handlers.postMan;

import agents.PostMan;
import communication.messages.FullMessage;
import communication.messages.OrderMessage;
import communication.messages.ProposalMessage;
import elements.Order;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

public class ProposalHandler {

    public ProposalHandler(){
        super();
    }

    static ACLMessage parse(ACLMessage message, PostMan postMan) {

        ProposalHandler handler = new ProposalHandler();

        OrderMessage content = new OrderMessage(message);
        
        if(postMan.getVehicle().getCurrentLoad() == postMan.getVehicle().getMaximumLoad()) {
        	System.out.println(postMan.getName() + "is already full!");
        	return new FullMessage(message.getSender()).toACL();
        }else {
        	double price = postMan.costCalculator(content.getPosition());
        	return new ProposalMessage(price, message.getSender()).toACL();
        }
        
    }

    public double handlerOrder(OrderMessage message, PostMan postMan){

        ArrayList<Order> orders = (ArrayList<Order>) postMan.getOrders().clone();
        boolean pickUp = !postMan.isGoingToPostOffice();


        return 0.0;
    }

    private double calculatePrice(PostMan postMan){

        return 0.0;
    }

    private double calculateBestPrice(PostMan postMan){

        return 0.0;
    }

}
