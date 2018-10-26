package communication.handlers.postMan;

import agents.PostMan;
import communication.messages.Message;
import jade.lang.acl.ACLMessage;
import static communication.Header.HANDSHAKE;
import static communication.Header.ORDER;

public class Handler {

    public static ACLMessage parse(ACLMessage message, PostMan postMan){

        if(message != null){
            Message content = Message.parse(message);
            if(content != null){
                switch (content.getType()){
                    case HANDSHAKE:
                        return HelloHandler.parse(message, postMan);

                    case ORDER:
                        return OrderHandler.parse(message, postMan);

                    default:
                         return null;
                }
            }
        }
        return null;
    }
}
