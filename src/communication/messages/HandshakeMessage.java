package communication.messages;

import communication.Header;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class HandshakeMessage extends Message {

    private AID receiver;
    private ACLMessage message;

    public HandshakeMessage(AID receiver) {
        this.receiver = receiver;
    }

    @Override
    public ACLMessage toACL() {
        ACLMessage msg = new ACLMessage(ACLMessage.SUBSCRIBE);
        msg.addReceiver(receiver);
        msg.setOntology(Header.Handshake);
        msg.setReplyWith(Header.Handshake);
        return msg;
    }
}
