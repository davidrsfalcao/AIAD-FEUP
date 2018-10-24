package agents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
    private AID postOffice;
    private ArrayList<Order> orders = new ArrayList();
    private Vehicle vehicle;

    
    public PostMan(String name, Point position, AID postOffice,int capacity){
        this.name = name;
        this.position = position;
        this.postOffice = postOffice;
        Random rnd = new Random();
        int comsuption = rnd.nextInt(51)+100;
        vehicle = new Vehicle(capacity,comsuption);
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
