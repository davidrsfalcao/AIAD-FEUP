package communication.messages;

import communication.Header;
import elements.Point;
import jade.lang.acl.ACLMessage;
public class HandshakeResponse extends Message {

    private ACLMessage message;
    private Point position;

    public HandshakeResponse(ACLMessage message, Point position) {
        this.message = message;
        this.position = position;
    }

    public HandshakeResponse(ACLMessage msg){
        String[] args = msg.getContent().split(SEPARATOR);
        double x =  Double.parseDouble(args[0]);
        double y =  Double.parseDouble(args[1]);
        position = new Point(x,y);
    }

    public Point getPosition() {
        return position;
    }

    @Override
    public ACLMessage toACL() {
        ACLMessage reply = message.createReply();
        reply.setPerformative(ACLMessage.CONFIRM);
        reply.setOntology(Header.Handshake);
        reply.setReplyWith(null);
        reply.setContent(position.getX() + SEPARATOR + position.getY());
        return reply;
    }

}
