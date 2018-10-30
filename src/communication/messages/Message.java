package communication.messages;

import communication.Header;
import jade.lang.acl.ACLMessage;

public abstract class Message implements Header {

    public abstract ACLMessage toACL();
}