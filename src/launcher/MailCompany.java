package launcher;

import agents.PostMan;
import agents.PostOffice;
import database.Database;
import elements.Point;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

/**
 * Class that represents a Mail Company, with one PostOffice and at least one PostMan
 */
class MailCompany {

    private AgentContainer agentsContainer;

    /**
     * Default constructor of MailCompany
     */
    MailCompany() {
        super();
    }

    /**
     * Start the Mail Company
     */
    void start(){
        try {
            initJade();
            initAgents();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the main containers with ams, df and rma agents and the agents container
     *
     * @throws StaleProxyException - Launches StaleProxyException in case of fail
     */
    private void initJade() throws StaleProxyException {
        // Get a hold on JADE runtime
        Runtime rt = Runtime.instance();

        // Exit the JVM when there are no more containers around
        rt.setCloseVM(true);
        System.out.print("runtime created\n");

        // Create a default profile
        Profile profile = new ProfileImpl(null, 1200, null);
        System.out.print("profile created\n");

        System.out.println("Launching a whole in-process platform..."+profile);
        AgentContainer mainContainer = rt.createMainContainer(profile);

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

    /**
     * Function that add the PostMan and PostOffices to agentsContainer and start them
     *
     * @throws StaleProxyException - Launches StaleProxyException in case of fail
     */
    void initAgents() throws StaleProxyException {

        PostOffice postOffice = new PostOffice(new Point(0,0));
        agentsContainer.acceptNewAgent("PostOffice", postOffice).start();

        PostMan postMan = new PostMan("David", new Point(1,1), postOffice.getAID(),10,1.3);
        agentsContainer.acceptNewAgent("PostMan David" , postMan).start();

        PostMan postMan1 = new PostMan("Pedro", new Point(2,2), postOffice.getAID(),9,1.4);
        agentsContainer.acceptNewAgent("PostMan Pedro", postMan1).start();

        PostMan postMan2 = new PostMan("Veronica", new Point(-1,-2), postOffice.getAID(),4,1.5);
        agentsContainer.acceptNewAgent("PostMan Veronica", postMan2).start();
        
        PostMan postMan3 = new PostMan("Rafael", new Point(-3,0), postOffice.getAID(),7,1.1);
        agentsContainer.acceptNewAgent("PostMan Rafael", postMan3).start();
        
        PostMan postMan4 = new PostMan("Manuel", new Point(-3,3), postOffice.getAID(),7,2);
        agentsContainer.acceptNewAgent("PostMan Manuel", postMan4).start();
        
        PostMan postMan5 = new PostMan("Sofia", new Point(-1,2.2), postOffice.getAID(),7,1.9);
        agentsContainer.acceptNewAgent("PostMan Sofia", postMan5).start();
        
        PostMan postMan6 = new PostMan("Joao", new Point(1,3), postOffice.getAID(),7,1.8);
        agentsContainer.acceptNewAgent("PostMan Joao", postMan6).start();
        
        PostMan postMan7 = new PostMan("Ricardo", new Point(-2,-1), postOffice.getAID(),7,1.7);
        agentsContainer.acceptNewAgent("PostMan Ricardo", postMan7).start();
        
        PostMan postMan8 = new PostMan("Lucas", new Point(-1.25,2.3), postOffice.getAID(),7,1.6);
        agentsContainer.acceptNewAgent("PostMan Lucas", postMan8).start();
        
        PostMan postMan9 = new PostMan("Carolina", new Point(-0.8,1.6), postOffice.getAID(),7,2.2);
        agentsContainer.acceptNewAgent("PostMan Carolina", postMan9).start();
        
        PostMan postMan10 = new PostMan("Josefa", new Point(-1.9,2.4), postOffice.getAID(),7,2.4);
        agentsContainer.acceptNewAgent("PostMan Josefa", postMan10).start();

    }
    
}