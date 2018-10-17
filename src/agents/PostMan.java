package agents;

import elements.Point;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;


public class PostMan extends Agent {

    private String name;
    private Point position;

    public PostMan(String name, Point position){
        this.name = name;
        this.position = position;
    }

    public void setup() {
        //System.out.println("[" + name + "] a minha posição é x: " + position.getX() + "  y: " + position.getY() );
        addBehaviour(new PostManBehaviour());
    }

    class PostManBehaviour extends CyclicBehaviour {

        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.QUERY_IF);

        @Override
        public void action() {
            ACLMessage msg = receive(mt);
            if(msg != null) {
                System.out.println(msg);
                ACLMessage reply = msg.createReply();
                reply.setPerformative(ACLMessage.INFORM);
                reply.setContent("Don't have a clue...");
                send(reply);
            } else {
                block();
            }
        }
    }

}
