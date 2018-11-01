package communication.handlers.postOffice;

import agents.PostOffice;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

class AliveHandler {

     static ACLMessage parse(ACLMessage message, PostOffice postOffice) {

         AID sender = message.getSender();
         // postOffice.updateWorkingPostMen(sender);

         return null;
    }
}
