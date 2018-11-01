package communication.messages;

import communication.Header;
import elements.PostManID;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

public class AliveResponse extends Message {
    private ACLMessage message;

    public AliveResponse(ACLMessage message) {
        this.message = message;
    }

    @Override
    public ACLMessage toACL() {

        ACLMessage reply = message.createReply();
        reply.setPerformative(ACLMessage.CONFIRM);
        reply.setOntology(Header.Alive);
        reply.setReplyWith(null);
        return reply;
    }
}
