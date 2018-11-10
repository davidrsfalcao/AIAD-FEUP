package communication.handlers.postOffice;

import agents.PostOffice;
import jade.lang.acl.ACLMessage;

import static communication.Header.*;


public class Handler {

    public static ACLMessage parse(ACLMessage message, PostOffice postOffice){

        if(message != null){
            String replyOntology = (message.getReplyWith() != null) ? message.getReplyWith() : Null;

            switch (replyOntology){

                case Handshake:
                    return HandshakeHandler.parse(message, postOffice);

                case Decision:
                    return DecisionHandler.parse(message, postOffice);

                case Null:
                    String ontology = (message.getOntology() != null) ? message.getOntology() : Null;
                    switch (ontology){
                        case Alive:
                            return AliveHandler.parse(message, postOffice);

                        default:
                            return null;

                    }

                default:
                    return null;

            }
        }
        else return null;
    }

}
