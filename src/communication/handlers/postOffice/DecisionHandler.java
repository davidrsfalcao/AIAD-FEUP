package communication.handlers.postOffice;

import agents.PostOffice;
import communication.messages.ProposalMessage;
import elements.PostManID;
import elements.Proposal;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import utils.WaitingReception;

import java.util.ArrayList;

public class DecisionHandler {

    static ACLMessage parse(ACLMessage message, PostOffice postOffice) {

        AID aid = message.getSender();
        PostManID postManID = null;

        for(int i=0; i < postOffice.getPostMen().size(); i++){
            if(postOffice.getPostMen().get(i).getId().getLocalName().equals(aid.getLocalName())){
                postManID = postOffice.getPostMen().get(i);
                ProposalMessage props = new ProposalMessage(message);
                postOffice.addProposal(new Proposal(postManID,props.getCost(),postOffice.getPendingOrder()));
            }
        }

        WaitingReception waitingReception = new WaitingReception(postOffice);
        Thread thread = new Thread(waitingReception);
        thread.start();

        while(thread.isAlive()){/*wait*/}

        ACLMessage reply = null;


        if(postManID.getName() == postOffice.chooseProposal().getPostManID().getName()){
            reply = new ProposalMessage(ACLMessage.ACCEPT_PROPOSAL, aid).toACL();

        }
        else {
            reply = new ProposalMessage(ACLMessage.REJECT_PROPOSAL,aid).toACL();
        }

        postOffice.setProposals(new ArrayList<>());

        if(postOffice.getPendingOrder() != null){
            postOffice.addOrder(postOffice.getPendingOrder());
            postOffice.setPendingOrder(null);
        }

        return reply;
    }

}
