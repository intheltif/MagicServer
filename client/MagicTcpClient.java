package client;
import common.Card;

import java.io.*;
import java.net.Socket;
import java.net.*;

/**
 * This class represents a concrete implementation of a magic client that uses
 * the TCP network layer protocol.
 *
 * @author Evert Ball, Garrett Starkey
 * @version 01 November 2019
 */
public class MagicTcpClient extends AbstractMagicClient {

    //Initializing variables
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
     * Initializes a new <code>MagicTcpClient</code> with the specified host
     * and flag.
     *
     * @param host The address of the remote host to which to connect.
     * @param flag The arguments to send to the server.
     */
    public MagicTcpClient(InetAddress host, String flag) {

        super(host, flag);

    } // end constructor w/ host & port

    /**
     * Initializes a new <code>MagicTcpClient</code> with the specified host, 
     * port, and flag.
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
            //Creates the client socket
            Socket client = new Socket(this.getHost(), this.getPort());
            System.out.println("Made a socket");

            PrintWriter clientPW = new PrintWriter(client.getOutputStream(), true);
            clientPW.println(this.getFlag());

            InputStream incoming = client.getInputStream();
            ObjectInputStream  incomingCard = new ObjectInputStream(incoming);

            Card card = (Card)incomingCard.readObject();

            while(card != null) {
                out.print(card.toString() + "\n");
                card = (Card)incomingCard.readObject();
            }

            clientPW.close();
            client.close();

        } catch(IOException ioe) {
            System.out.println("Me?");
            System.out.println(IO_ERROR);
            System.exit(FAILURE);
        } catch(ClassNotFoundException cnfe) {
            System.err.println("Class could not be found." );
            System.exit(FAILURE);
        }

    } // end printToStream method

} // end MagicTcpClient class
