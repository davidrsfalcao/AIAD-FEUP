package communication.handlers.postMan;

import agents.PostMan;
import communication.messages.HelloResponse;
import jade.lang.acl.ACLMessage;

public class HelloHandler extends Handler {

    public static ACLMessage parse(ACLMessage message, PostMan postMan) {

        HelloResponse msg = new HelloResponse(message);
        postMan.updatePostOfficePosition(msg.getPosition());

        return null;
    }
}
