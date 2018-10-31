package communication.handlers.postMan;

import agents.PostMan;
import jade.lang.acl.ACLMessage;
import static communication.Header.Handshake;
import static communication.Header.Null;
import static communication.Header.Proposal;

public class Handler {

    public static ACLMessage parse(ACLMessage message, PostMan postMan){
        if(message != null){

            String replyOntology = (message.getReplyWith() != null) ? message.getReplyWith() : Null;

            switch (replyOntology){
                case Proposal:
                    return ProposalHandler.parse(message, postMan);

                case Null:
                    if(message.getOntology() != null){
                        switch(message.getOntology()){
                            case Handshake:
                                return HandshakeHandler.parse(message, postMan);

                        }
                    }

                default:
                    return null;

                }
        }
        else return null;
    }
}
