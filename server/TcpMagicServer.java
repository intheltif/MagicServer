package server;

import common.Card;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class represents a concrete implementation of a magic server that uses
 * the TCP transport layer protocol.
 *
 * @author Evert Ball
 * @version 01 November 2019
 */
public class TcpMagicServer extends AbstractMagicServer {

    // TODO Add needed fields, if any
   
    /**
     * Creates a new <code>TcpMagicServer</code> that listens for connections 
     * on the default magic TCP port, and uses the default card source.
     *
     * @throws FileNotFoundException if the file used to initialize the cards 
     * is not found.
     */
    public TcpMagicServer() throws FileNotFoundException {

        // TODO finish constructor.
        super();
    } // end empty constructor.

    /**
     * Creates a new <code>TcpMagicServer</code> that listens for connections 
     * on the specified port, and uses the default card source.
     *
     * @param port The port the server will listen at.
     *
     * @throws FileNotFoundException if the file used to initialize the cards 
     * is not found.
     */
    public TcpMagicServer(int port) throws FileNotFoundException {

        // TODO finish constructor.
        super(port);

    } // end empty constructor.

    /**
     * Creates a new <code>TcpMagicServer</code> that listens for connections 
     * on the default port, and uses the specified card source.
     *
     * @param source The source used to generate cards.
     *
     * @throws FileNotFoundException if the file used to initialize the cards 
     * is not found.
     */
    public TcpMagicServer(CardSource source) throws FileNotFoundException {

        // TODO finish constructor.
        super(source);

    } // end empty constructor.
    
    /**
     * Creates a new <code>TcpMagicServer</code> that listens for connections 
     * on the specified port, and uses the specified card source.
     *
     * @param port   The port the server will listen at.
     * @param source The source used to generate cards.
     *
     * @throws FileNotFoundException if the file used to initialize the cards 
     * is not found.
     */
    public TcpMagicServer(int port, CardSource source) 
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

        String flags = "";

        try{
            //Create the welcoming socket
            ServerSocket serverSock = new ServerSocket(this.getPort());

            while(!serverSock.isClosed()) {
                // Create socket to accept client data
                Socket sock = serverSock.accept();

                // Create input streams
                InputStreamReader incomingStringReader =
                        new InputStreamReader(sock.getInputStream());
                BufferedReader buffer =
                        new BufferedReader(incomingStringReader);

                // Get the flags the client sent
                flags = buffer.readLine();
                this.setCardsReturned(flags);

                // Create output streams
                OutputStream outStream = sock.getOutputStream();
                ObjectOutputStream objOutStream =
                        new ObjectOutputStream(outStream);

                // Create and send back correct number of cards
                for(int i=0; i < this.getItemsToSend(); i++) {
                    Card card = this.getSource().next();
                    objOutStream.writeObject(card);
                }

                // Close the socket that accepts client data
                sock.close();
            } // end while loop

            // Close sockets
            serverSock.close();

        } catch(IOException ioe) {
            throw new MagicServerException("I/O error, something went wrong.",
                    ioe);
        } // end try-catch

    } // end listen method

} // end TcpMagicServer class
