package communication.handlers.postMan;

import agents.PostMan;
import jade.lang.acl.ACLMessage;
import static communication.Header.Handshake;
import static communication.Header.Proposal;

public class Handler {

    public static ACLMessage parse(ACLMessage message, PostMan postMan){
        if(message != null){
            if(message.getReplyWith() != null){
                switch (message.getReplyWith()){
                    case Handshake:
                        return HandshakeHandler.parse(message, postMan);

                    case Proposal:
                        return ProposalHandler.parse(message, postMan);

                    default:
                        return null;

                }
            }
            else return null;
        }
        else return null;
    }
}
