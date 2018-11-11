package communication;

/**
 * Interface with all headers of messages
 */
public interface Header {
    String SEPARATOR = " ";
    String Null = "null";

    String Handshake = "Handshake";
    String Order = "Order";
    String Proposal = "Proposal";
    String Decision = "Decision";
    String Alive = "Alive";
    String Full = "Full";
}
