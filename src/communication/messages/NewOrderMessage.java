package communication.messages;

import elements.Point;
import elements.PostManID;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

public class NewOrderMessage extends Message {

    private Point position;
    private int estimatedTime;
    private ArrayList<PostManID> receivers;

    public NewOrderMessage(Point position, int estimatedTime, ArrayList<PostManID> receivers) {
        this.position = position;
        this.estimatedTime = estimatedTime;
        this.receivers = receivers;
    }

    public NewOrderMessage(ACLMessage msg){
        String[] args = msg.getContent().split(SEPARATOR);
        if(args.length != 4)
            return;
        else {
            type = NEWORDER;
            double x = Double.parseDouble(args[1]);
            double y = Double.parseDouble(args[2]);
            position = new Point(x,y);
            estimatedTime = Integer.parseInt(args[3]);
        }
    }

    public Point getPosition() {
        return position;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    @Override
    public ACLMessage toACL() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent(NEWORDER + SEPARATOR + position.getX() + SEPARATOR + position.getY() + SEPARATOR + estimatedTime);
        for(PostManID receiver : receivers){
            msg.addReceiver(receiver.getId());
        }
        return msg;
    }
}
