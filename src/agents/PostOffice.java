package agents;

import communication.handlers.postOffice.Handler;
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

    public class PostOfficeBehaviour extends CyclicBehaviour {
        @Override
        public void action() {
            ACLMessage msg = receive();

            if(msg != null) {
                System.out.println("[POSTOFFICE] " + msg.getContent());
                ACLMessage reply = Handler.parse(msg);
                if(reply != null){
                    send(Handler.parse(msg));
                }
            } else {
                block();
            }
        }
    }



}
