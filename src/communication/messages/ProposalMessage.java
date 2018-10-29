package communication.messages;

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
        if(args.length != 2)
            return;
        else {
            type = PROPOSAL;
            cost = Double.parseDouble(args[1]);
        }
    }

    public double getCost() {
        return cost;
    }

    public AID getReceiver() {
        return receiver;
    }

    @Override
    public ACLMessage toACL() {
        ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
        msg.setContent(ORDER + SEPARATOR + cost);
        msg.addReceiver(receiver);
        return msg;
    }
}
