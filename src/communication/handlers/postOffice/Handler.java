package communication.handlers.postOffice;

import agents.PostOffice;
import jade.lang.acl.ACLMessage;

import static communication.Header.Handshake;


public class Handler {

    public static ACLMessage parse(ACLMessage message, PostOffice postOffice){

        if(message != null){
            if(message.getReplyWith() != null){
                switch (message.getReplyWith()){

                    case Handshake:
                        return HandshakeHandler.parse(message, postOffice);

                    default:
                        return null;

                }
            }
            else return null;
        }
        else return null;
    }

}
