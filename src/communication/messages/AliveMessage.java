package communication.messages;

import communication.Header;
import elements.PostManID;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

public class AliveMessage extends Message {
    private ArrayList<PostManID> receivers;
    private double delta;

    public AliveMessage(ArrayList<PostManID> receivers, double delta) {
        this.receivers = receivers;
        this.delta = delta;
    }

    public AliveMessage(ACLMessage message){
        delta = Double.parseDouble(message.getContent());
    }

    public double getDelta() {
        return delta;
    }

    @Override
    public ACLMessage toACL() {
        ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
        for(PostManID receiver : receivers){
            msg.addReceiver(receiver.getId());
        }
        msg.setOntology(Header.Alive);
        msg.setReplyWith(Header.Alive);
        msg.setContent(delta + "");
        return msg;
    }
}
