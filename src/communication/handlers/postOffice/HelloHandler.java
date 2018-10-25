package communication.handlers.postOffice;

import agents.PostOffice;
import communication.messages.HandshakeMessage;
import communication.messages.HandshakeResponse;
import elements.PostManID;
import jade.lang.acl.ACLMessage;


public class HelloHandler extends Handler {

    public static ACLMessage parse(ACLMessage message, PostOffice postOffice) {
        String name = new HandshakeMessage(message).getName();
        postOffice.addPostman(new PostManID(name, message.getSender()));

        return new HandshakeResponse(message, postOffice.getPosition()).toACL();
    }
}
