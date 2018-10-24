package communication.handlers.postOffice;

import agents.PostOffice;
import communication.messages.Message;
import jade.lang.acl.ACLMessage;

import static communication.Header.HELLO;

public class Handler {

    public static ACLMessage parse(ACLMessage message){

        if(message != null){
            Message content = Message.parse(message);
            if(content != null){
                switch (content.getType()){

                    case HELLO:
                        return HelloHandler.parse(message);
                }


            }
        }

        return null;

    }
}
