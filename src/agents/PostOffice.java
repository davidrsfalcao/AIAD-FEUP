package agents;

import communication.handlers.postOffice.Handler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import communication.messages.AliveMessage;
import communication.messages.OrderMessage;
import communication.messages.ProposalResponse;
import database.Database;
import elements.Order;
import elements.Point;
import elements.PostManID;
import elements.Proposal;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.xml.crypto.Data;

public class PostOffice extends Agent {

    private Point position;
	private ArrayList<PostManID> postMen = new ArrayList<>();
	private ArrayList<Order> orders = new ArrayList<>();
	private long lastOrderTime = System.currentTimeMillis();
    private ArrayList<Proposal> proposals = new ArrayList<>();
    private Order pendingOrder;
    private long lastUpdate = System.currentTimeMillis();

    private PostOffice instance;

    public PostOffice( Point position ){
        this.position = position;
        instance = this;
    }

    public void setup() {
        addBehaviour(new PostOfficeBehaviour());
        addBehaviour(new OrderGeneratorBehaviour());
        addBehaviour(new ProposalSelectionBehaviour());
        addBehaviour(new UpdateBehaviour());
    }

    public void addPostman(PostManID postManID){
        postMen.add(postManID);
        System.out.println(" --- POSTOFFICE ADDED "+ postManID.getName()  +" ---");
    }
    
    public Order getOrderByID(int orderID){
    	for(int i=0;i < orders.size();i++) {
    		if(orders.get(i).getID() == orderID) {
    			return orders.get(i);
    		}
    	}
		return null;
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

            if(diff >= 5000 && pendingOrder == null){
                lastOrderTime = System.currentTimeMillis();

                Random rand = new Random();
                double x =  (double)Math.round(-25 + (25 + 25) * rand.nextDouble() * 1000d) / 1000d;
                double y = (double)Math.round(-25 + (25 + 25) * rand.nextDouble() * 1000d) / 1000d;
                int i = rand.nextInt(8) + 1;

                ACLMessage message = new OrderMessage(new Point(x, y),i*12,postMen).toACL();
                if(message != null){
                    System.out.println("\n\n[POSTOFFICE] " + message.getPerformative() + " - " + message.getOntology() + " - " + message.getContent());
                    send(message);
                    pendingOrder = new Order(new Point(x, y),i*12);
                    pendingOrder.setTimeCreation(System.currentTimeMillis());

                    
                    try {
                        Database.getInstance().insertPostOfficeData(postMen.size(), getNumberActiveOrders() );
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }

        }

    }

    class ProposalSelectionBehaviour extends CyclicBehaviour{
        @Override
        public void action() {
            if(pendingOrder != null && (proposals.size() == postMen.size()) ){

                PostManID chosen = chooseProposal().getPostManID();
                System.out.println("[POSTOFFICE] Order assigned to " + chosen.getName());
                int orderId = orders.size();

                ACLMessage reply = new ProposalResponse(chosen.getId(), orderId).toACL();

                if(reply != null){
                    send(reply);
                    System.out.println("[POSTOFFICE] " + reply.getPerformative() + " - " + reply.getOntology() + " - "
                            + reply.getContent() + " | " + chosen.getName() );
                }


                ArrayList<AID> receivers = new ArrayList<>();
                for(int i=0; i <postMen.size(); i++){
                    if(!postMen.get(i).getName().equals(chosen.getName())){
                        receivers.add(postMen.get(i).getId());
                    }
                }

                reply = new ProposalResponse(receivers, orderId).toACL();

                if(reply != null){
                    send(reply);
                    System.out.println("[POSTOFFICE] " + reply.getPerformative() + " - " + reply.getOntology() + " | Others");

                }

                resetProposals();
                orders.add(pendingOrder);
                pendingOrder = null;

            }
        }
    }

    class UpdateBehaviour extends CyclicBehaviour{
        @Override
        public void action() {
            long diff = System.currentTimeMillis() - lastUpdate;

            if(diff >= 1000){
                send(new AliveMessage(postMen, diff).toACL());
                lastUpdate = System.currentTimeMillis();
            }

        }
    }

    public Proposal chooseProposal() {

        double min= 0;
    	Proposal pA = null;
    	for(int i=0;i < proposals.size();i++) {
    		int ind = postMen.indexOf(proposals.get(i).getPostManID());
    		if(i==0) {
    			min = proposals.get(i).getPrice() / postMen.get(ind).getRating();
    			pA= proposals.get(i);
    		}else {
    			double temp = proposals.get(i).getPrice() / postMen.get(ind).getRating();
    			if(temp < min) {
    				min = temp;
    				pA=proposals.get(i);
    			}
    		}

    	}
    	return pA;
    }

    public ArrayList<PostManID> getPostMen(){
        return postMen;
    }

    public Order getPendingOrder() {
        return pendingOrder;
    }

    public ArrayList<Proposal> getProposals() {
        return proposals;
    }

    public void addProposal(Proposal proposal){
        proposals.add(proposal);
    }

    public void resetProposals() {
        this.proposals.clear();
    }
    
    public void setOrderDelivered(int orderID) {
    	for(int i=0; i < orders.size();i++) {
    		if(orders.get(i).getID() == orderID) {
    			orders.get(i).setType(2);
    		}
    	}
    }
    
    public PostManID getPostManByAID(AID id) {
    	for(int i=0; i < postMen.size();i++) {
    		if(postMen.get(i).getId() == id) {
    			return postMen.get(i);
    		}
    	}
    	return null;
    }

    public int getNumberActiveOrders(){

        int count = 0;

        for (Order order : orders){

            if(order.getType() != 2)
                count++;

        }

        return ++count;

    }



}
