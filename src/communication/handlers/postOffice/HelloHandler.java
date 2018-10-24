package communication.handlers.postOffice;

import agents.PostOffice;
import communication.messages.HelloMessage;
import communication.messages.HelloResponse;
import elements.PostManID;
import jade.lang.acl.ACLMessage;


public class HelloHandler extends Handler {

    public static ACLMessage parse(ACLMessage message, PostOffice postOffice) {

        String name = new HelloMessage(message).getName();
        postOffice.addPostman(new PostManID(name, message.getSender()));

        return new  HelloResponse(message, postOffice.getPosition()).toACL();
    }

}
