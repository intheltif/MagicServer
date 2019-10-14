package server;

/**
 * The interface to a magic server.
 *
 * @author Evert Ball
 * @version 01 November 2019
 */
public interface MagicServer {
    
    /**
     * Causes the magic server to listen for requests
     *
     * @throws MagicServerException if an error occurs while trying to listen
     *                              for connections.
     */
    public void listen() throws MagicServerException;

} // end MagicServer interface
