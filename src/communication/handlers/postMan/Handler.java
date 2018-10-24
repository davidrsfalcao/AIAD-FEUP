package communication.handlers.postMan;

import agents.PostMan;
import communication.messages.Message;
import jade.lang.acl.ACLMessage;

import static communication.Header.HELLOR;

public class Handler {

    public static ACLMessage parse(ACLMessage message, PostMan postMan){

        if(message != null){
            Message content = Message.parse(message);
            if(content != null){
                switch (content.getType()){
                    case HELLOR:
                        return HelloHandler.parse(message, postMan);
                }
            }
        }

        return null;

    }
}
