package server;

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

    } // end empty constructor.

    /**
     * Causes the magic server to listen for requests.
     *
     * @throws MagicServerException If an error occurs while trying to listen
     *                              for connections.
     */
    public void listen() throws MagicServerException {

        //TODO Finish listen() method

    } // end listen method

} // end TcpMagicServer class
