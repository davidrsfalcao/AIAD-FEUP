package communication.handlers.postMan;

import agents.PostMan;
import communication.messages.OrderMessage;
import communication.messages.ProposalMessage;
import jade.lang.acl.ACLMessage;


public class ProposalHandler {

    public ProposalHandler(){
        super();
    }

    static ACLMessage parse(ACLMessage message, PostMan postMan) {

        ProposalHandler handler = new ProposalHandler();

        OrderMessage content = new OrderMessage(message);
        postMan.setPendingOrder(content);
        double price = postMan.costCalculator(content.getPosition());
        return new ProposalMessage(price, message.getSender()).toACL();

    }

}
