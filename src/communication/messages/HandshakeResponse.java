package communication.messages;

import elements.Point;
import jade.lang.acl.ACLMessage;
public class HandshakeResponse extends Message {

    private ACLMessage msg;
    private Point position;

    public HandshakeResponse(ACLMessage msg, Point position) {
        this.msg = msg;
        this.position = position;
    }

    public HandshakeResponse(ACLMessage msg){
        String[] args = msg.getContent().split(SEPARATOR);
        if(args.length != 3)
            return;
        else {
            double x =  Double.parseDouble(args[1]);
            double y =  Double.parseDouble(args[2]);
            position = new Point(x,y);
            type = HANDSHAKE;
        }
    }

    public Point getPosition() {
        return position;
    }

    @Override
    public ACLMessage toACL() {
        ACLMessage reply = msg.createReply();
        reply.setPerformative(ACLMessage.CONFIRM);
        reply.setContent(HANDSHAKE + SEPARATOR + position.getX() + SEPARATOR + position.getY());
        return reply;
    }

}
