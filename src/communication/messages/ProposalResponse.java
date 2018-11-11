package communication.messages;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;


public class ProposalResponse extends Message{

    private int performative;
    private int orderId;
    private ArrayList<AID> receivers = new ArrayList<>();

    public ProposalResponse(AID receiver, int orderID) {
        this.performative = ACLMessage.ACCEPT_PROPOSAL;
        receivers.add(receiver);
        this.orderId = orderID;
    }

    public ProposalResponse(ArrayList<AID> receivers) {
        this.performative = ACLMessage.REJECT_PROPOSAL;
        this.receivers = receivers;
        orderId = -1;
    }

    public ProposalResponse(ACLMessage message) {
        String content = message.getContent();
        orderId = Integer.parseInt(content);
    }

    public int getOrderId() {
        return orderId;
    }

    @Override
    public ACLMessage toACL() {
        ACLMessage response = new ACLMessage(performative);
        response.setReplyWith(null);
        response.setOntology(Decision);
        for(AID receiver : receivers)
            response.addReceiver(receiver);
        response.setContent(null);
        response.setContent((orderId == -1) ? null : orderId + "");
        return response;
    }
}
