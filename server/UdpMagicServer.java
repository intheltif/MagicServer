package server;

import common.Card;

import java.io.*;
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

        super(port, source);

    } // end empty constructor.

    /**
     * Causes the magic server to listen for requests.
     *
     * @throws MagicServerException If an error occurs while trying to listen
     *                              for connections.
     */
    public void listen() throws MagicServerException {

        try {
            //Create socket, buffer, and packet for incoming data
            DatagramSocket socket = new DatagramSocket(this.getPort());

            while(!socket.isClosed()) {
                byte[] incomingBuffer = new byte[MAX_SIZE];
                DatagramPacket packet = new DatagramPacket(incomingBuffer, MAX_SIZE);
                socket.receive(packet);

                String flags = new String(packet.getData(), packet.getOffset(), packet.getLength());
                System.out.println("Flags: " + flags);
                // Set cards to send to what the client requested
                this.setCardsReturned(flags);

                //Create the streams for data going to the client
                ByteArrayOutputStream outgoingStream =
                        new ByteArrayOutputStream(MAX_SIZE);
                ObjectOutputStream outgoingObjectStream =
                        new ObjectOutputStream(outgoingStream);
                outgoingObjectStream.flush();

                byte[] outBuffer = null;
                DatagramPacket outPack;
                for (int i = 0; i < this.getItemsToSend(); i++) {
                    System.out.println("i = " + i);
                    System.out.println("Sending cards...");
                    Card card = this.getSource().next();
                    System.out.println("Card: " + card.toString());
                    outgoingObjectStream.reset();
                    outgoingObjectStream.writeObject(card);
                    outgoingObjectStream.flush();
                    outBuffer = outgoingStream.toByteArray();
                    outPack = new DatagramPacket(outBuffer, outBuffer.length, packet.getAddress(), packet.getPort());
                    socket.send(outPack);
                } // end for loop
                System.out.println("done sending");
                outBuffer = new byte[0];
                outPack = new DatagramPacket(outBuffer, outBuffer.length, packet.getAddress(), packet.getPort());
                socket.send(outPack);


            } // end while loop
            // close socket
            socket.close();
        } catch(IOException ioe) {
            throw new MagicServerException("IO Error", ioe);
        }// end try-catch

    } // end listen method

} // end UdpMagicServer class
