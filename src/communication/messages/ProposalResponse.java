package communication.messages;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;


public class ProposalResponse extends Message{

    private int performative;
    private ArrayList<AID> receivers = new ArrayList<>();

    public ProposalResponse(AID receiver) {
        this.performative = ACLMessage.ACCEPT_PROPOSAL;
        receivers.add(receiver);
    }

    public ProposalResponse(ArrayList<AID> receivers) {
        this.performative = ACLMessage.REJECT_PROPOSAL;
        this.receivers = receivers;
    }

    @Override
    public ACLMessage toACL() {
        ACLMessage response = new ACLMessage(performative);
        response.setReplyWith(null);
        response.setOntology(Decision);
        for(AID receiver : receivers)
            response.addReceiver(receiver);
        response.setContent(null);
        return response;
    }
}
