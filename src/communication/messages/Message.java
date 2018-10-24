package communication.messages;

import communication.Header;
import jade.lang.acl.ACLMessage;

public abstract class Message implements Header {

    protected String type = "NONE";

    public static Message parse(ACLMessage message){

        switch (message.getPerformative()){

            case ACLMessage.INFORM:
                return parseInformMessage(message);

            default:
                return null;

        }

    }


    private static Message parseInformMessage(ACLMessage message){
        String[] args = message.getContent().split(Header.SEPARATOR);
        String tp;

        tp = args[0];

        switch (tp){
            case HELLO:
                return new HelloMessage(message);

            case HELLOR:
                return new HelloResponse(message);
            
            default:
                return null;

        }


    }

    public String getType() {
        return type;
    }

    public abstract ACLMessage toACL();
}