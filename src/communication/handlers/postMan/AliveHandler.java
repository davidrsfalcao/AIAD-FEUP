package communication.handlers.postMan;

import communication.messages.AliveResponse;
import jade.lang.acl.ACLMessage;

class AliveHandler {
    static ACLMessage parse(ACLMessage message) {
        return new AliveResponse(message).toACL();
    }
}
