package communication.handlers.postOffice;

import agents.PostOffice;
import communication.messages.HelloResponse;
import elements.Point;
import jade.lang.acl.ACLMessage;


public class HelloHandler extends Handler {

    public static ACLMessage parse(ACLMessage message) {
        return new  HelloResponse(message, new Point(0,0)).toACL();
    }


}
