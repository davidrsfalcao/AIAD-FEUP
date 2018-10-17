package agents;

import jade.core.Agent;
import utils.Headers;

public class PostOffice extends Agent {

    public PostOffice(){
    }

    public void setup() {
        System.out.println("[POSTOFFICE] a minha posição é x: " + Headers.postOfficePosition.getX() + "  y: " + Headers.postOfficePosition.getY() );
    }


}
