package communication.messages;

import communication.Header;
import jade.lang.acl.ACLMessage;

public abstract class Message implements Header {

    protected String type = "NONE";

    public static Message parse(ACLMessage message){

        switch (message.getPerformative()){
            case ACLMessage.SUBSCRIBE:
                return parseSubscribeMessage(message);

                /*
            case ACLMessage.INFORM:
                return parseInformMessage(message);

                */

            case ACLMessage.CONFIRM:
                return parseConfirmMessage(message);

            default:
                return null;

        }

    }

    private static Message parseSubscribeMessage(ACLMessage message){
        String[] args = message.getContent().split(Header.SEPARATOR);
        String tp;

        tp = args[0];

        switch (tp){
            case HANDSHAKE:
                return new HandshakeMessage(message);

            default:
                return null;

        }

    }

    private static Message parseConfirmMessage(ACLMessage message){
        String[] args = message.getContent().split(Header.SEPARATOR);
        String tp;

        tp = args[0];

        switch (tp){
            case HANDSHAKE:
                return new HandshakeResponse(message);

            default:
                return null;

        }

    }



    public String getType() {
        return type;
    }

    public abstract ACLMessage toACL();
}