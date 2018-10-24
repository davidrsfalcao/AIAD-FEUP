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
        
        //teste
//        Point p1= new Point(10,10);
//        Point p2 = new Point(2,2);
//        Point p3 = new Point(10,11);
//        Order o1 = new Order(p1,10);
//        Order o2 = new Order(p2,20);
//        Order o3 = new Order(p3,30);
//        this.orders.add(o1);
//        this.orders.add(o2);
//        this.orders.add(o3);
//        System.out.println("Before Sort: "+ this.orders);
//        sortOrders(0,this.orders.size());
//        System.out.println("After Sort: "+ this.orders);
        
    }
    
    public void sortOrders(int start,int end) {
    	//No inicio passar start como 0 e end como this.orders.size();
    	Order aux;
    	int i=start,j = end-1;
    	Point pivot =  this.orders.get(((start+end)/2)).getDestiny();
    	while(i <= j) {
    		while( (this.orders.get(i).getDestiny().getDistance(position)) < pivot.getDistance(position) && i < end ) {
    			i++;
    		}
    		while( (this.orders.get(j).getDestiny().getDistance(position)) > pivot.getDistance(position) && j > start) {
    			j--;
    		}
    		if(i <= j) {
    			aux = this.orders.get(i);
    			this.orders.set(i, this.orders.get(j));
    			this.orders.set(j,aux);
    			i++;
    			j--;
    		}
    		
    	}
    	
    	if(j > start) {
    		sortOrders(start,j+1);
    	}
    	if(i < end) {
    		sortOrders(i,end);
    	}
    	
    }
    
    public void setup() {
        addBehaviour(new PostManBehaviour());
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
