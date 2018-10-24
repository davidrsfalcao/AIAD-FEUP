package communication.messages;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class HelloMessage extends Message {

    private String name;
    private AID receiver;

    public HelloMessage(String name, AID receiver) {
        this.name = name;
        this.receiver = receiver;
    }

    public HelloMessage(ACLMessage msg){
        String[] args = msg.getContent().split(SEPARATOR);
        if(args.length != 2)
            return;
        else {
            this.type = HELLO;
            this.name = args[1];
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public ACLMessage toACL() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent(HELLO + SEPARATOR + name);
        msg.addReceiver(receiver);
        return msg;
    }
}
