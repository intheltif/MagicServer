package server;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * This class represents a concrete implementation of a magic server that uses
 * the UDP transport layer protocol.
 *
 * @author Evert Ball
 * @version 01 November 2019
 */
public class UdpMagicServer extends AbstractMagicServer {

    // TODO Add needed fields, if any
    /** The maximum size of the byte array used to transport data */
    private static final int MAX_SIZE = 256;
    
   
    /**
     * Creates a new <code>UdpMagicServer</code> that listens for connections 
     * on the default magic UDP port, and uses the default card source.
     *
     * @throws FileNotFoundException if the file used to initialize the cards 
     * is not found.
     */
    public UdpMagicServer() throws FileNotFoundException {

        // TODO finish constructor.
        super();

    } // end empty constructor.

    /**
     * Creates a new <code>UdpMagicServer</code> that listens for connections 
     * on the specified port, and uses the default card source.
     *
     * @param port The port the server will listen at.
     *
     * @throws FileNotFoundException if the file used to initialize the cards 
     * is not found.
     */
    public UdpMagicServer(int port) throws FileNotFoundException {

        // TODO finish constructor.
        super(port);

    } // end empty constructor.

    /**
     * Creates a new <code>UdpMagicServer</code> that listens for connections 
     * on the default port, and uses the specified card source.
     *
     * @param source The source used to generate cards.
     *
     * @throws FileNotFoundException if the file used to initialize the cards 
     * is not found.
     */
    public UdpMagicServer(CardSource source) throws FileNotFoundException {

        // TODO finish constructor.
        super(source);

    } // end empty constructor.
    
    /**
     * Creates a new <code>UdpMagicServer</code> that listens for connections 
     * on the specified port, and uses the specified card source.
     *
     * @param port   The port the server will listen at.
     * @param source The source used to generate cards.
     *
     * @throws FileNotFoundException if the file used to initialize the cards 
     * is not found.
     */
    public UdpMagicServer(int port, CardSource source) 
        throws FileNotFoundException {

        // TODO finish constructor.
        super(port, source);

    } // end empty constructor.

    /**
     * Causes the magic server to listen for requests.
     *
     * @throws MagicServerException If an error occurs while trying to listen
     *                              for connections.
     */
    public void listen() throws MagicServerException {

        //TODO Finish listen() method
        try {
            //Create socket, buffer, and packet for incoming data
            DatagramSocket socket = new DatagramSocket(this.getPort());
            byte[] incomingBuffer = new byte[MAX_SIZE];
            DatagramPacket incomingPacket =
                    new DatagramPacket(incomingBuffer, incomingBuffer.length);

            //Create the streams for data coming from the client
            ByteArrayInputStream incomingStream =
                    new ByteArrayInputStream(incomingBuffer);
            ObjectInputStream incomingObjectStream =
                    new ObjectInputStream(incomingStream);

            //TODO Do the cards stuff here

            // close streams
            incomingObjectStream.close();
            incomingStream.close();

            // close socket
            socket.close();
        } catch(IOException ioe) {
            throw new MagicServerException("IO Error", ioe);
        } catch(ClassNotFoundException cnfe) {
            throw new MagicServerException("The requested class could not be " +
                    "found.", cnfe);
        } // end try-catch


} // end listen method

} // end UdpMagicServer class
