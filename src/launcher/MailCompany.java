package launcher;

import agents.PostMan;
import agents.PostOffice;
import elements.Point;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class MailCompany {

    AgentContainer mainContainer;
    AgentContainer agentsContainer;

    public MailCompany() {
        super();
    }

    public void start(){
        try {
            initJade();
            initAgents();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }

    public void initJade() throws StaleProxyException {
        // Get a hold on JADE runtime
        Runtime rt = Runtime.instance();

        // Exit the JVM when there are no more containers around
        rt.setCloseVM(true);
        System.out.print("runtime created\n");

        // Create a default profile
        Profile profile = new ProfileImpl(null, 1200, null);
        System.out.print("profile created\n");

        System.out.println("Launching a whole in-process platform..."+profile);
        mainContainer = rt.createMainContainer(profile);

        // now set the default Profile to start a container
        ProfileImpl pContainer = new ProfileImpl(null, 1200, null);
        System.out.println("Launching the agent container ..."+pContainer);

        agentsContainer = rt.createAgentContainer(pContainer);
        System.out.println("Launching the agent container after ..."+pContainer);

        System.out.println("containers created");
        System.out.println("Launching the rma agent on the main container ...");
        AgentController rma = mainContainer.createNewAgent("rma", "jade.tools.rma.rma", new Object[0]);
        rma.start();

    }

    public void initAgents() throws StaleProxyException {

        PostOffice postOffice = new PostOffice(new Point(0,0));

        agentsContainer.acceptNewAgent("PostOffice", postOffice).start();

        PostMan postMan = new PostMan("David", new Point(1,1), postOffice.getAID(),10);
        agentsContainer.acceptNewAgent("PostMan David" , postMan).start();

        PostMan postMan1 = new PostMan("Pedro", new Point(2,2), postOffice.getAID(),9);
        agentsContainer.acceptNewAgent("PostMan Pedro", postMan1).start();

        PostMan postMan2 = new PostMan("Veronica", new Point(10,-10), postOffice.getAID(),4);
        agentsContainer.acceptNewAgent("PostMan Veronica", postMan2).start();

    }
    
}
