package communication.handlers.postMan;

import agents.PostMan;
import communication.messages.OrderMessage;
import communication.messages.ProposalMessage;
import jade.lang.acl.ACLMessage;

class ProposalHandler {

    static ACLMessage parse(ACLMessage message, PostMan postMan) {

        OrderMessage content = new OrderMessage(message);
        double distance = postMan.getPosition().getDistance(content.getPosition());
        double price = postMan.getVehicle().getTravelPrice(distance);

        return new ProposalMessage(price, message.getSender()).toACL();

    }
}
