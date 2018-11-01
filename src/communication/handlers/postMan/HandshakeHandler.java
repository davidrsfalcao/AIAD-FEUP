package communication.handlers.postMan;

import agents.PostMan;
import communication.messages.HandshakeResponse;
import jade.lang.acl.ACLMessage;

class HandshakeHandler{

    static ACLMessage parse(ACLMessage message, PostMan postMan) {
        HandshakeResponse msg = new HandshakeResponse(message);
        postMan.updatePostOfficePosition(msg.getPosition());
        return null;
    }
}
