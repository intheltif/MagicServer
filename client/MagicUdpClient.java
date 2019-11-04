package client;
import org.omg.CORBA.portable.OutputStream;

import java.io.IOException;
import java.net.*;
import java.io.PrintStream;

/**
 * This class represents a concrete implementation of a magic client that uses
 * the UDP network layer protocol.
 *
 * @author Evert Ball, Garrett Starkey
 * @version 01 November 2019
 */
public class MagicUdpClient extends AbstractMagicClient {

    /** A constant to represent a failed exit */
    private static final int FAILURE = 1;

    /** A constant string to print out when we encounter an IOException. */
    private static final String IO_ERROR = "I/O error...Something went wrong.";
    
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
    public void printToStream(PrintStream out) throws IOException {
        try {
            // These don't seem right to me
            String host = getHost(args[0]);
            int port = getPort(Integer.parseInt(args[1]));

            // Creating a buffer
            byte[] buffer = new byte[0];
            DatagramPacket send = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(host), port);
            DatagramSocket receive = new DatagramSocket();
            receive.send(send);

            // Create streams to collect what is coming from the server
            byte[] retrieve = new byte[256];
            DatagramPacket incomingInfo = new DatagramPacket(retrieve, retrieve.length);
            receive.receive(incomingInfo);

            // Prints to specified output stream
            // TODO figure out how to print to a specific output stream
            out.write();

        } catch(IOException ioe) {
            System.out.println(IO_ERROR);
            System.out.println(FAILURE);
        }

    } // end printToStream method

} // end MagicUdpClient class
