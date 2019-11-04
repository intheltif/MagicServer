package client;

import java.io.IOException;
import java.io.PrintStream;

/**
 * The interface to a magic client component.
 *
 * @author Evert Ball
 * @version 01 November 2019
 */
public interface MagicClient {
    
    /**
     * Establishes a TCP connection to the host/port specified when this object
     * was created, reads a continuous stream of random cards from the socket's
     * input stream, and prints that data to the specified output stream.
     *
     * @param out The stream to which to write the random cards received.
     *
     * @throws IOException if there is an I/O error while receiving the data.
     */
    public void printToStream(PrintStream out) throws IOException;

} // end MagicClient interface
