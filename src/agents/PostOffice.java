package agents;

import communication.handlers.postOffice.Handler;
import java.util.ArrayList;
import java.util.HashMap;
import elements.Order;
import elements.PostManID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import utils.Headers;

public class PostOffice extends Agent {
	
	private ArrayList<PostManID> postmen = new ArrayList<PostManID>();
	private ArrayList<Order> orders = new ArrayList<Order>();

    private PostOffice instance;

    public PostOffice( ){
        instance = this;
    }

    //Quando o PostOffice receber a mensagem de um PostMan a dizer que está disponivel,vai ser chamado o PostManID.
    
    
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
                ACLMessage reply = Handler.parse(msg, instance);
                if(reply != null){
                    send(reply);
                }
            } else {
                block();
            }
        }
    }



}
