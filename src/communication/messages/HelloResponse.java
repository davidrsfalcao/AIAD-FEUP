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

        if(args.length != 1){
            return;
        }
        else {
            this.type = HELLO;
        }
    }


    @Override
    public ACLMessage toACL() {
        ACLMessage reply = msg.createReply();
        reply.setPerformative(ACLMessage.INFORM);
        reply.setContent("HELLO BACK");
        return reply;
    }

}
