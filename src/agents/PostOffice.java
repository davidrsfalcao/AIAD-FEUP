package agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import utils.Headers;

public class PostOffice extends Agent {

    public PostOffice(){
    }

    public void setup() {
        //System.out.println("[POSTOFFICE] a minha posição é x: " + Headers.postOfficePosition.getX() + "  y: " + Headers.postOfficePosition.getY() );
        addBehaviour(new PostOfficeBehaviour());
    }

    class PostOfficeBehaviour extends CyclicBehaviour {
        @Override
        public void action() {
            ACLMessage msg = receive();
            System.out.println("FUCKING HERE");
            if(msg != null) {
                System.out.println(msg);
                ACLMessage reply = msg.createReply();
                reply.setPerformative(ACLMessage.INFORM);
                reply.setContent("Got your message!");
                send(reply);
            } else {
                block();
            }
        }
    }



}
