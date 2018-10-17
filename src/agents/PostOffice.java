package agents;

import elements.Point;
import jade.core.Agent;

public class PostOffice extends Agent {

    Point position;

    public void setup(Point position) {
        this.position = position;
        System.out.println("[ POSTOFICE] a minha posição é x: " + position.getX() + "  y: " + position.getY() );
    }


}
