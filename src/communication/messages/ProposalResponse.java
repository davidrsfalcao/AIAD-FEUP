package communication.messages;

import jade.core.AID;
import jade.lang.acl.ACLMessage;


public class ProposalResponse extends Message{

    private int performative;
    private AID receiver;

    public ProposalResponse(int performative, AID receiver) {
        this.performative = performative;
        this.receiver = receiver;
    }

    @Override
    public ACLMessage toACL() {
        ACLMessage response = new ACLMessage(performative);
        response.setReplyWith(null);
        response.setOntology(Decision);
        response.addReceiver(receiver);
        response.setContent(null);
        return response;
    }
}
