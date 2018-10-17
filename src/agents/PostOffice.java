package agents;

import elements.Point;
import jade.core.Agent;

public class PostOffice extends Agent {

    Point position;

    public PostOffice(Point position){
        this.position = position;
    }

    public void setup() {
        System.out.println("[POSTOFFICE] a minha posição é x: " + position.getX() + "  y: " + position.getY() );
    }


}
