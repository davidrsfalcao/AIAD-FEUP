package agents;

import elements.Point;
import jade.core.Agent;


public class PostMan extends Agent {

    private String name;
    private Point position;

    public PostMan(String name, Point position){
        this.name = name;
        this.position = position;
    }

    public void setup() {
        System.out.println("[ " + name + "] a minha posição é x: " + position.getX() + "  y: " + position.getY() );
    }

}
