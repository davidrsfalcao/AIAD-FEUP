package communication.handlers.postMan;

import agents.PostMan;
import communication.messages.AliveMessage;
import communication.messages.AliveResponse;
import jade.lang.acl.ACLMessage;

class AliveHandler {
    static ACLMessage parse(ACLMessage message, PostMan postMan) {

        AliveMessage msg = new AliveMessage(message);
        postMan.update(msg.getDelta());

        return new AliveResponse(message).toACL();
    }
}
