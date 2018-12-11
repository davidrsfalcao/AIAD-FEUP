package communication.handlers.postMan;

import agents.PostMan;
import communication.messages.ProposalResponse;
import database.Database;
import elements.Order;
import jade.lang.acl.ACLMessage;

import java.sql.SQLException;

public class DecisionHandler {

    static ACLMessage parse(ACLMessage message, PostMan postMan) {

        ProposalResponse res = new ProposalResponse(message);

        if(message.getPerformative() == ACLMessage.REJECT_PROPOSAL){
            postMan.setPendingOrder(null);
            try {
                Database.getInstance().updatePostOfficeData(res.getOrderId(),postMan.getId(), 0);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            ProposalResponse response = new ProposalResponse(message);
            int orderId = response.getOrderId();

            Order order = new Order(postMan.getPendingOrder().getPosition(), postMan.getPendingOrder().getEstimatedTime());
            order.setID(orderId);

            System.out.println("[POSTMAN " + postMan.getPostManName() + "] Added order with ID:" +  orderId);

            postMan.addOrder(order);
            postMan.setPendingOrder(null);

            if(postMan.getDestiny() == null){
                postMan.addDestinyPostOffice();
            }

            try {
                Database.getInstance().updatePostOfficeData(res.getOrderId(),postMan.getId(), 1);
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

        return null;
    }


}
