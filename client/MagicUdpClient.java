package client;

/**
 * This class represents a concrete implementation of a magic client that uses
 * the UDP network layer protocol.
 *
 * @author Evert Ball, Garrett Starkey
 * @version 01 November 2019
 */
public class MagicUdpClient extends AbstractMagicClient {

    // TODO Add fields needed, if any.
    
    /**
     * Initializes a new <code>MagicUdpClient</code> with the specified host, 
     * and the default port.
     *
     * @param host The address of the remote host to which to connect.
     */
    public MagicUdpClient(InetAddress host) {

        super(host);

    } // end constructor w/ host

    /**
     * Initializes a new <code>MagicUdpClient</code> with the specified host 
     * and port.
     *
     * @param host The address of the remote host to which to connect.
     * @param port The port on the remote host to which to connect.
     */
    public MagicUdpClient(InetAddress host, int port) {

        super(host, port);

    } // end constructor w/ host & port
    
    /**
     * Initializes a new <code>MagicUdpClient</code> with the specified host, 
     * port, and flag.
     *
     * @param host The address of the remote host to which to connect.
     * @param port The port on the remote host to which to connect.
     * @param flag The arguments to send to the server.
     */
    public MagicUdpClient(InetAddress host, int port, String flag) {

        super(host, port, flag);

    } // end constructor w/ host, port, & flag

    /**
     * Establishes a UDP connection to the host/port specified when this object 
     * was created, reads a continuous stream of random cards from the socket's 
     * input stream, and prints that data to the specified output stream.
     * TODO Specified By sections. See Dr. Barlowe on how to do that
     *
     * @param out The stream to which to write the random cards received.
     *
     * @throws IOException If there is an I/O error while receiving the data.
     */
    @Override //TODO Is this needed?
    public void printToStream(PrintStream out) throws IOException {
        
        //TODO finish printToStream method

    } // end printToStream method

} // end MagicUdpClient class
