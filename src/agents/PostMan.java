package agents;

import java.util.ArrayList;
import java.util.Random;

import communication.Header;
import communication.handlers.postMan.Handler;
import communication.messages.HandshakeMessage;
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
    private boolean goingToPostOffice;

    public PostMan(String name, Point position, AID postOffice, int capacity){
        this.name = name;
        this.position = position;
        this.postOffice = postOffice;
        Random rnd = new Random();
        int consumption = rnd.nextInt(51)+100;
        vehicle = new Vehicle(capacity,consumption);
        instance = this;
        goingToPostOffice = false;

    }
    
    public boolean isGoingToPostOffice() {
    	return goingToPostOffice;
    }
    
    public void updateGoingToPostOffice() {
    	
    	Point p= postOfficePosition;
    	
    	if(position == p) {
    		goingToPostOffice = false;
    		return;
    	}
    	
    	if(goingToPostOffice) {
    		goingToPostOffice = false;
    	}else {
    		goingToPostOffice = true;
    	}
    }
    
    public double costCalculator(Point orderPos) {
    	
    	if(goingToPostOffice){
    		int index = 0;
    		double dist = 0;
    		for(int i=0;i < orders.size();i++) {
    			if(orderPos.getDistance(this.postOfficePosition) < orders.get(i).getDestiny().getDistance(this.postOfficePosition)) {
    				index = i-1;
    				break;
    			}
    		}
    		if(index == -1) {
    			dist = orderPos.getDistance(this.postOfficePosition);
    		}else {
    			dist = orders.get(index).getDestiny().getDistance(orderPos); //distance between closest Position and New Order
    		}
    		if(((vehicle.getCurrentLoad()/vehicle.getMaximumLoad())*100) >= 80) { // Since the car is almost full,the postman will charge more
    			return vehicle.getTravelPrice(dist)*1.5;
    		}else {
    			return vehicle.getTravelPrice(dist);
    		}
    	}else {
    		double dist1 = position.getDistance(postOfficePosition);
    		double dist2 = postOfficePosition.getDistance(orderPos);
    		if(((vehicle.getCurrentLoad()/vehicle.getMaximumLoad())*100) >= 80) { // Since the car is almost full,the postman will charge more
    			return (vehicle.getTravelPrice(dist1) + vehicle.getTravelPrice(dist2))*1.5;
    		}else {
    			return vehicle.getTravelPrice(dist1) + vehicle.getTravelPrice(dist2);
    		}
    	}
    	
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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void updatePostOfficePosition(Point position){
        this.postOfficePosition = position;

        System.out.println(" --- " + name + " updated PostOffice Position to " + postOfficePosition + " ---");
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    class PostManBehaviour extends CyclicBehaviour {

        @Override
        public void action() {
            System.out.println("[POSTMAN " + name + "] " + ACLMessage.SUBSCRIBE + " - " + Header.Handshake);
            send(new HandshakeMessage(postOffice).toACL());

            while(true){
                ACLMessage msg = receive();
                if(msg != null) {
                    ACLMessage reply = Handler.parse(msg, instance);

                    if(reply != null){
                        send(reply);
                        System.out.println("[POSTMAN " + name + " ] " + reply.getPerformative() + " - " + reply.getOntology() + " - " + reply.getContent());
                    }
                  
                } else {
                    block();
                }
            }
        }
    }

}
