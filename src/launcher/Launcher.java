package launcher;


/**
 * Main class. Initiates the execution
 */
public class Launcher {

    /**
     * Main function
     *
     * @param args - called args
     */
    public static void main(String[] args){

        MailCompany mailCompany = new MailCompany();
        mailCompany.start();

    }
}
