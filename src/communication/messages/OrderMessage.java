package communication.messages;

import communication.Header;
import elements.Point;
import elements.PostManID;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

public class OrderMessage extends Message {

    private Point position;
    private int estimatedTime;
    private ArrayList<PostManID> receivers;

    public OrderMessage(Point position, int estimatedTime, ArrayList<PostManID> receivers) {
        this.position = position;
        this.estimatedTime = estimatedTime;
        this.receivers = receivers;
    }

    public OrderMessage(ACLMessage msg){
        String[] args = msg.getContent().split(SEPARATOR);
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        position = new Point(x,y);
        estimatedTime = Integer.parseInt(args[2]);

    }

    public Point getPosition() {
        return position;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    @Override
    public ACLMessage toACL() {
        ACLMessage msg = new ACLMessage(ACLMessage.PROPAGATE);
        for(PostManID receiver : receivers){
            msg.addReceiver(receiver.getId());
        }
        msg.setOntology(Header.Order);
        msg.setReplyWith(Header.Proposal);
        msg.setContent(position.getX() + SEPARATOR + position.getY() + SEPARATOR + estimatedTime);
        return msg;
    }
}
