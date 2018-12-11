package communication.handlers.postMan;

import agents.PostMan;
import communication.messages.OrderMessage;
import communication.messages.ProposalMessage;
import database.Database;
import jade.lang.acl.ACLMessage;

import java.sql.SQLException;


public class ProposalHandler {

    public ProposalHandler(){
        super();
    }

    static ACLMessage parse(ACLMessage message, PostMan postMan) {

        ProposalHandler handler = new ProposalHandler();

        postMan.setNrOrder(postMan.getNrOrder() + 1);
        OrderMessage content = new OrderMessage(message);
        postMan.setPendingOrder(content);
        double price = postMan.costCalculator(content.getPosition());


        try {
            Database.getInstance().insertPostManData(postMan, price);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return new ProposalMessage(price, message.getSender()).toACL();

    }

}
