package launcher;

public class Launcher {

    private static jade.wrapper.AgentContainer mainContainer;

    public static void main(String[] args){

        MailCompany mailCompany = new MailCompany();
        mailCompany.start();

    }


}
