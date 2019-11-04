package client;
import java.io.IOException;
import java.net.*;
import java.io.PrintStream;

/**
 * This class represents a concrete implementation of a magic client that uses
 * the TCP network layer protocol.
 *
 * @author Evert Ball, Garrett Starkey
 * @version 01 November 2019
 */
public class MagicTcpClient extends AbstractMagicClient {

    //Initializing variables
    InetAddress host;

    int port = DEFAULT_PORT;

    String flag = DEFAULT_FLAG;

    /** The first command line argument */
    private static final int FIRST_ARG = 0;

    /** The second command line argument */
    private static final int SECOND_ARG = 1;

    /** A constant to represent a failed exit */
    private static final int FAILURE = 1;

    /** A constant string to print out when we encounter an IOException. */
    private static final String IO_ERROR = "I/O error...Something went wrong.";

    /**
     * Initializes a new <code>MagicTcpClient</code> with the specified host, 
     * and the default port.
     *
     * @param host The address of the remote host to which to connect.
     */
    public MagicTcpClient(InetAddress host) {

        super(host);

    } // end constructor w/ host

    /**
     * Initializes a new <code>MagicTcpClient</code> with the specified host 
     * and port.
     *
     * @param host The address of the remote host to which to connect.
     * @param port The port on the remote host to which to connect.
     */
    public MagicTcpClient(InetAddress host, int port) {

        super(host, port);

    } // end constructor w/ host & port
    
    /**
     * Initializes a new <code>MagicTcpClient</code> with the specified host, 
     * port, and flag.
     *
     * @param host The address of the remote host to which to connect.
     * @param port The port on the remote host to which to connect.
     * @param flag The arguments to send to the server.
     */
    public MagicTcpClient(InetAddress host, int port, String flag) {

       super(host, port, flag);

    } // end constructor w/ host, port, & flag

    /**
     * Establishes a TCP connection to the host/port specified when this object 
     * was created, reads a continuous stream of random cards from the socket's 
     * input stream, and prints that data to the specified output stream.
     * TODO Specified By sections. See Dr. Barlowe on how to do that
     *
     * @param out The stream to which to write the random cards received.
     *
     * @throws IOException If there is an I/O error while receiving the data.
     */

    public void printToStream(PrintStream out) throws IOException {
        try {
            host = InetAddress.getByName(args[FIRST_ARG]);
            port = Integer.parseInt(args[SECOND_ARG]);

            //Creates the client socket
            Socket client = new Socket(host, port);

            //Creates the streams for the client to be able to communicate
            //with the server.




        } catch(IOException ioe) {
            System.out.println(IO_ERROR);
            System.exit(FAILURE);
        }

    } // end printToStream method

} // end MagicTcpClient class
