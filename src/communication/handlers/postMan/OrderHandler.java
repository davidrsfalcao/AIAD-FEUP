package communication.handlers.postMan;

import agents.PostMan;
import communication.messages.HandshakeResponse;
import jade.lang.acl.ACLMessage;

public class OrderHandler extends Handler {

    public static ACLMessage parse(ACLMessage message, PostMan postMan) {


        //postMan.updatePostOfficePosition(msg.getPosition());
        System.out.println("[POSTMAN " + postMan.getPostManName() + " ] " + message.getPerformative() + " - " + message.getContent());

        return null;
    }
}
