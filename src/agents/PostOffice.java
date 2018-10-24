package agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import utils.Headers;

public class PostOffice extends Agent {

    public PostOffice( ){
    }

    public void setup() {
        //System.out.println("[POSTOFFICE] a minha posição é x: " + Headers.postOfficePosition.getX() + "  y: " + Headers.postOfficePosition.getY() );
        addBehaviour(new PostOfficeBehaviour());
    }

    class PostOfficeBehaviour extends CyclicBehaviour {
        @Override
        public void action() {
            ACLMessage msg = receive();


            if(msg != null) {
                System.out.println("[POSTOFFICE] " + msg.getContent());
                ACLMessage reply = msg.createReply();
                reply.setPerformative(ACLMessage.INFORM);
                reply.setContent("Got your message! " + msg.getSender());
                send(reply);
            } else {
                block();
            }
        }
    }



}
