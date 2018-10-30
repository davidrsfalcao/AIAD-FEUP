package agents;

import communication.handlers.postOffice.Handler;
import java.util.ArrayList;
import java.util.Random;

import communication.messages.OrderMessage;
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
	private long lastOrderTime = System.currentTimeMillis();

    private PostOffice instance;

    public PostOffice( Point position ){
        this.position = position;
        instance = this;
    }

    public void setup() {
        addBehaviour(new PostOfficeBehaviour());
        addBehaviour(new OrderGeneratorBehaviour());
    }

    public void addPostman(PostManID postManID){
        postMen.add(postManID);
        System.out.println(" --- NEW POSTMAN ---");
    }

    public Point getPosition() {
        return position;
    }

    class PostOfficeBehaviour extends CyclicBehaviour {
        @Override
        public void action() {
            ACLMessage msg = receive();

            if(msg != null) {
                ACLMessage reply = Handler.parse(msg, instance);
                if(reply != null){
                    send(reply);
                    System.out.println("[POSTOFFICE] " + reply.getPerformative() + " - " + reply.getOntology() + " - " + reply.getContent());
                }
            } else {
                block();
            }
        }
    }

    class OrderGeneratorBehaviour extends CyclicBehaviour{

        @Override
        public void action() {

            generateOrder();

        }

        private void generateOrder(){
            long diff = System.currentTimeMillis() - lastOrderTime;

            if(diff >= 5000){
                lastOrderTime = System.currentTimeMillis();

                Random rand = new Random();
                double x =  (double)Math.round(-25 + (25 + 25) * rand.nextDouble() * 1000d) / 1000d;
                double y = (double)Math.round(-25 + (25 + 25) * rand.nextDouble() * 1000d) / 1000d;
                int i = rand.nextInt(8) + 1;

                ACLMessage message = new OrderMessage(new Point(x, y),i*12,postMen).toACL();
                if(message != null){
                    System.out.println("[POSTOFFICE] " + message.getPerformative() + " - " + message.getOntology() + " - " + message.getContent());
                    send(message);
                }

            }

        }

    }


}
