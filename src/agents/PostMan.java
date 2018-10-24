package agents;

import communication.messages.HelloMessage;
import elements.Point;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;



public class PostMan extends Agent {

    private String name;
    private Point position;
    private AID postOffice;

    public PostMan(String name, Point position, AID postOffice){
        this.name = name;
        this.position = position;
        this.postOffice = postOffice;
    }

    public void setup() {
        //System.out.println("[" + name + "] a minha posição é x: " + position.getX() + "  y: " + position.getY() );
        addBehaviour(new PostManBehaviour());
    }

    class PostManBehaviour extends CyclicBehaviour {

        @Override
        public void action() {
            send(new HelloMessage(name, postOffice).toACL());

            while(true){
                ACLMessage receive = receive();
                if(receive != null) {
                    System.out.println("[POSTMAN] " + receive.getContent());
                    ACLMessage reply = receive.createReply();
                    reply.setPerformative(ACLMessage.INFORM);
                    reply.setContent("Ola!");

                    send(reply);
                } else {
                    block();
                }
            }

            /*
            ACLMessage reply = msg.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            reply.setContent("Don't have a clue...");
            send(reply);
            */
        }
    }

}
