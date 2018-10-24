package communication.messages;

import communication.Header;
import jade.lang.acl.ACLMessage;

public abstract class Message implements Header {

    protected String type = "NONE";


    public String getType() {
        return type;
    }

    public static Message parse(ACLMessage message){

        String[] args = message.getContent().split(Header.SEPARATOR);
        String tp;

        tp = args[0];

        switch (tp){
            case HELLO:
                return new HelloMessage(message);

        }

        return null;

    }

    public abstract ACLMessage toACL();
}