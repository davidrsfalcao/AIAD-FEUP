package communication.messages;

import communication.Header;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class FullMessage extends Message {
	
	private AID receiver;
	
    public FullMessage(AID receiver) {

        this.receiver = receiver;
    }

	@Override
	public ACLMessage toACL() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(receiver);
        msg.setOntology(Header.Full);

        return msg;
    }
}

