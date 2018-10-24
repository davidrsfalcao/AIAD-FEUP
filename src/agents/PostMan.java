package agents;

import java.util.ArrayList;
import java.util.Random;
import communication.handlers.postMan.Handler;
import communication.messages.HelloMessage;
import elements.Order;
import elements.Point;
import elements.Vehicle;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class PostMan extends Agent {
    private String name;
    private Point position;
    private Point postOfficePosition;
    private AID postOffice;
    private ArrayList<Order> orders = new ArrayList();
    private Vehicle vehicle;
    private PostMan instance;

    public PostMan(String name, Point position, AID postOffice,int capacity){
        this.name = name;
        this.position = position;
        this.postOffice = postOffice;
        Random rnd = new Random();
        int comsuption = rnd.nextInt(51)+100;
        vehicle = new Vehicle(capacity,comsuption);
        instance = this;

    }

    public void setup() {
        addBehaviour(new PostManBehaviour());
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public Point getPosition() {
        return position;
    }

    public void updatePostOfficePosition(Point position){
        this.postOfficePosition = position;

        System.out.println(" --- " + name + " updated PostOffice Position to " + postOfficePosition + " ---");
    }

    class PostManBehaviour extends CyclicBehaviour {

        @Override
        public void action() {
            System.out.println("[POSTMAN " + name + "] 7 - HELLO " + name);
            send(new HelloMessage(name, postOffice).toACL());

            while(true){
                ACLMessage msg = receive();
                if(msg != null) {
                    ACLMessage reply = Handler.parse(msg, instance);

                    if(reply != null){
                        send(reply);
                        System.out.println("[POSTMAN " + name + " ] " + reply.getPerformative() + " - " + reply.getContent());
                    }
                  
                } else {
                    block();
                }
            }
        }
    }

}
