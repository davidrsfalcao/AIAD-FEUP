package communication.messages;

import communication.Header;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class ProposalMessage extends Message {

    private double cost;
    private AID receiver;

    public ProposalMessage(double cost, AID receiver) {
        this.cost = cost;
        this.receiver = receiver;
    }

    public ProposalMessage(ACLMessage msg){
        String[] args = msg.getContent().split(SEPARATOR);
        cost = Double.parseDouble(args[0]);
    }

    public double getCost() {
        return cost;
    }

    @Override
    public ACLMessage toACL() {
        ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
        msg.addReceiver(receiver);
        msg.setOntology(Header.Proposal);
        msg.setReplyWith(Header.Decision);
        msg.setContent(cost + "");

        return msg;
    }
}
