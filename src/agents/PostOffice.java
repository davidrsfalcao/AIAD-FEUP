package agents;

import communication.handlers.postOffice.Handler;
import java.util.ArrayList;
import elements.Order;
import elements.Point;
import elements.PostManID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class PostOffice extends Agent {

    private Point position;
	private ArrayList<PostManID> postMen = new ArrayList<PostManID>();
	private ArrayList<Order> orders = new ArrayList<Order>();

    private PostOffice instance;

    public PostOffice( Point position ){
        this.position = position;
        instance = this;
    }

    //Quando o PostOffice receber a mensagem de um PostMan a dizer que está disponivel,vai ser chamado o PostManID.

    public void setup() {
        //System.out.println("[POSTOFFICE] a minha posição é x: " + Headers.postOfficePosition.getX() + "  y: " + Headers.postOfficePosition.getY() );
        addBehaviour(new PostOfficeBehaviour());
    }

    public void addPostman(PostManID postManID){
        postMen.add(postManID);

        System.out.println(" --- NEW POSTMAN ---");
    }

    public Point getPosition() {
        return position;
    }

    public class PostOfficeBehaviour extends CyclicBehaviour {
        @Override
        public void action() {
            ACLMessage msg = receive();

            if(msg != null) {
                ACLMessage reply = Handler.parse(msg, instance);
                if(reply != null){
                    send(reply);
                    System.out.println("[POSTOFFICE] " + reply.getPerformative() + " - " + reply.getContent());
                }
            } else {
                block();
            }
        }
    }



}
