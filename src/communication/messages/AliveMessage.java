package communication.messages;

import communication.Header;
import elements.PostManID;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

public class AliveMessage extends Message {
    private ArrayList<PostManID> receivers;

    public AliveMessage(ArrayList<PostManID> receivers) {
        this.receivers = receivers;
    }

    @Override
    public ACLMessage toACL() {
        ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
        for(PostManID receiver : receivers){
            msg.addReceiver(receiver.getId());
        }
        msg.setOntology(Header.Alive);
        msg.setReplyWith(Header.Alive);
        return msg;
    }
}
