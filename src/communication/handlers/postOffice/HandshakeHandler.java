package communication.handlers.postOffice;

import agents.PostOffice;
import communication.messages.HandshakeResponse;
import elements.PostManID;
import jade.lang.acl.ACLMessage;


public class HandshakeHandler extends Handler {

    public static ACLMessage parse(ACLMessage message, PostOffice postOffice) {
        postOffice.addPostman(new PostManID(message.getSender()));
        return new HandshakeResponse(message, postOffice.getPosition()).toACL();
    }
}
