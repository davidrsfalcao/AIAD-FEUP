package communication.messages;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class HandshakeMessage extends Message {

    private String name;
    private AID receiver;

    public HandshakeMessage(String name, AID receiver) {
        this.name = name;
        this.receiver = receiver;
    }

    public HandshakeMessage(ACLMessage msg){
        String[] args = msg.getContent().split(SEPARATOR);
        if(args.length != 2)
            return;
        else {
            this.type = HANDSHAKE;
            this.name = args[1];
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public ACLMessage toACL() {
        ACLMessage msg = new ACLMessage(ACLMessage.SUBSCRIBE);
        msg.setContent(HANDSHAKE + SEPARATOR + name);
        msg.addReceiver(receiver);
        return msg;
    }
}
