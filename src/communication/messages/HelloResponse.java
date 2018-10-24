package communication.messages;

import elements.Point;
import jade.lang.acl.ACLMessage;

public class HelloResponse extends Message {

    private ACLMessage msg;
    private Point position;

    public HelloResponse(ACLMessage msg, Point position) {
        this.msg = msg;
        this.position = position;
    }

    public HelloResponse(ACLMessage msg){

        String[] args = msg.getContent().split(SEPARATOR);

        if(args.length != 3){
            return;
        }
        else {
            double x =  Double.parseDouble(args[1]);
            double y =  Double.parseDouble(args[2]);
            this.position = new Point(x,y);
            this.type = HELLOR;
        }
    }

    @Override
    public ACLMessage toACL() {
        ACLMessage reply = msg.createReply();
        reply.setPerformative(ACLMessage.INFORM);
        reply.setContent("HELLOR" + SEPARATOR + position.getX() + SEPARATOR + position.getY());
        return reply;
    }

    public Point getPosition() {
        return position;
    }

}
