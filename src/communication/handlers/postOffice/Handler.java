package communication.handlers.postOffice;

import agents.PostOffice;
import jade.lang.acl.ACLMessage;

import static communication.Header.Handshake;
import static communication.Header.Null;


public class Handler {

    public static ACLMessage parse(ACLMessage message, PostOffice postOffice){

        if(message != null){
            String replyOntology = (message.getReplyWith() != null) ? message.getReplyWith() : Null;

            switch (replyOntology){

                case Handshake:
                    return HandshakeHandler.parse(message, postOffice);

                default:
                    return null;

            }
        }
        else return null;
    }

}
