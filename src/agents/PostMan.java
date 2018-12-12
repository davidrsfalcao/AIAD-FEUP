package agents;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import communication.Header;
import communication.handlers.postMan.Handler;
import communication.messages.DeliveryDoneMessage;
import communication.messages.HandshakeMessage;
import communication.messages.OrderMessage;
import database.Database;
import elements.Destiny;
import elements.Order;
import elements.Point;
import elements.Vehicle;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import utils.Utils;

import static communication.Header.Alive;


public class PostMan extends Agent {
    private String name;
    private Point position;
    private Point postOfficePosition;
    private AID postOffice;
    private ArrayList<Order> orders = new ArrayList();
    private Vehicle vehicle;
    private PostMan instance;
    private boolean goingToPostOffice;
    private OrderMessage pendingOrder;
    private Destiny destiny;
    private double personalGain;
    private int nrOrder;
    private int id;

    public PostMan(String name, Point position, AID postOffice, int capacity, double personalGain){
        this.name = name;
        this.position = position;
        this.postOffice = postOffice;
        vehicle = new Vehicle(capacity,120);
        instance = this;
        goingToPostOffice = false;
        this.personalGain=personalGain;
        nrOrder = 0;
        try {
            id = Database.getInstance().getPostManId(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(double delta){

        String dest;
        if(destiny != null){
           dest = destiny.getPosition() + "";
        }
        else dest = position + "";

        System.out.println(" ---  " + name + "  on  " + position + " -> "+ dest + "   active orders:" + orders.size() + "    load:" + vehicle.getCurrentLoad());


        if(destiny != null){
            destiny.update(delta);

            if(destiny.getPosition().equals(postOfficePosition)){

                if(orders.size() <= vehicle.getMaximumLoad()){
                    goingToPostOffice = true;
                }
                else {
                    goingToPostOffice = false;
                }


            }


            if(destiny.getTime() < 0){
                if(destiny.getPosition().equals(postOfficePosition)){
                    System.out.println(name + " in the PostOffice");
                    for(int k =0; k < orders.size(); k++){
                        if(vehicle.getCurrentLoad() == vehicle.getMaximumLoad())
                            break;
                        else if(orders.get(k).getType() == 0){
                            orders.get(k).setType(1);
                            vehicle.addOrder();
                        }
                    }
                    Utils.sortOrders(this);
                    position = new Point(postOfficePosition.getX(), postOfficePosition.getY());
                    goingToPostOffice = false;
                }
                else {
                    int i;
                    for(i = 0; i < orders.size(); i++ ){
                        if(orders.get(i).getDestiny().equals(destiny.getPosition()))
                            break;
                    }

                    Order order = orders.get(i);

                    if(order.getType() == 1){

                        vehicle.removeOrder();
                        orders.remove(order);

                        ACLMessage reply = new DeliveryDoneMessage(postOffice, order.getID()).toACL();
                        System.out.println("[POSTMAN " + name + "] " + reply.getPerformative() + " - " + reply.getOntology() + " - " + reply.getContent());
                        if(reply != null){
                            send(reply);
                        }
                    }
                }

                if(orders.size() == 0){
                    position = new Point(destiny.getPosition().getX(), destiny.getPosition().getY());
                    destiny = null;

                }
                else{
                    double dd = Math.abs(destiny.getTime());

                    if(orders.get(0).getType() == 1){
                        Point des = orders.get(0).getDestiny();
                        Point pos = destiny.getPosition();
                        destiny = new Destiny(des,pos,vehicle.getTravelTime(des,pos));
                    }
                    else {
                         Point pos = new Point( destiny.getPosition().getX(), destiny.getPosition().getY());
                         destiny = new Destiny(postOfficePosition,pos,vehicle.getTravelTime(postOfficePosition,pos));
                    }
                    destiny.update(dd);
                }
            }

            if(destiny != null){
                double per = 1 - destiny.getTime()/destiny.getTotalTime();
                double x = destiny.getInitialPosition().getX() + (destiny.getDeltaX()*per);
                double y = destiny.getInitialPosition().getY() + (destiny.getDeltaY()*per);

                position.set(x,y);

            }

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
    		if(((orders.size()/vehicle.getMaximumLoad())*100) >= 80) { // Since the car is almost full,the postman will charge more
    			return vehicle.getTravelPrice(dist) * personalGain * 2;
    		}else {
    			return vehicle.getTravelPrice(dist) * personalGain;
    		}
    	}else {
    		double dist1 = position.getDistance(postOfficePosition);
    		double dist2 = postOfficePosition.getDistance(orderPos);
    		if(((orders.size()/vehicle.getMaximumLoad())*100) >= 80) { // Since the car is almost full,the postman will charge more
    			return (vehicle.getTravelPrice(dist1) + vehicle.getTravelPrice(dist2)) * personalGain * 2;
    		}else {
    			return (vehicle.getTravelPrice(dist1) + vehicle.getTravelPrice(dist2)) * personalGain;
    		}
    	}
    	
    }

    public void setup() {
        addBehaviour(new PostManBehaviour());
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public double getPersonalGain() {
    	return this.personalGain;
    }

    public Point getPosition() {
        return position;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void addDestinyPostOffice(){
        destiny = new Destiny(postOfficePosition, new Point(position.getX(), position.getY()) ,vehicle.getTravelTime(postOfficePosition,position));

        if(orders.size() <= vehicle.getMaximumLoad()){
            goingToPostOffice = true;
        }
        else {
            goingToPostOffice = false;
        }
        goingToPostOffice = true;
    }

    public int isGoingToPostOffice(){
        if (goingToPostOffice)
            return 1;
        else return 0;
    }

    public Destiny getDestiny() {
        return destiny;
    }

    public int getNrOrder() {
        return nrOrder;
    }

    public int getId() {
        return id;
    }

    public Point getPostOfficePosition() {
        return postOfficePosition;
    }

    public void setNrOrder(int nrOrder) {
        this.nrOrder = nrOrder;
    }

    public void setDestiny(Destiny destiny) {
        this.destiny = destiny;
    }

    public String getPostManName() {
        return name;
    }

    public void updatePostOfficePosition(Point position){
        postOfficePosition = position;

        System.out.println(" --- " + name + " updated PostOffice Position to " + postOfficePosition + " ---");

    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public void setPendingOrder(OrderMessage pendingOrder) {
        this.pendingOrder = pendingOrder;
    }

    public OrderMessage getPendingOrder() {
        return pendingOrder;
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
                        if(reply.getOntology() != Alive )
                            System.out.println("[POSTMAN " + name + "] " + reply.getPerformative() + " - " + reply.getOntology() + " - " + reply.getContent());
                    }
                  
                } else {
                    block();
                }
            }
        }
    }

}
