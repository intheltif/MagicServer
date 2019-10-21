package client;

/**
 * An abstract class that contains fields and methods that may be common to
 * implementations of the 'magic' protocol.
 *
 * @author Evert Ball
 * @version 01 November 2019
 */
public abstract class AbstractMagicClient implements MagicClient {

    /** The default port on which a magic server listens. */
    public static final int DEFAULT_PORT = 5555;

    /** The default port on which a magic server listens. */
    public static final String DEFAULT_FLAG = "-A";
    
    /** The host to connect to */
    private InetAddress host;
    
    /** The port to connect over */
    private int port;
    
    /** The flag that determines which cards to send back */
    private String flag;

    // CONSTRUCTORS BELOW
    
    /**
     * Initializes a new <code>AbstractMagicClient</code> with the specified
     * host, default port, and default flag.
     *
     * @param host The address of the remote host to connect to.
     */
    public AbstractMagicClient(InetAddress host) {

        this.host = host;
        this.port = DEFAULT_PORT;
        this.flag = DEFAULT_FLAG;

    } // end constructor from host
    
    /**
     * Initializes a new <code>AbstractMagicClient</code> with the specified
     * host and port with the default flag.
     *
     * @param host The address of the remote host to connect to.
     * @param port The port on which to connect to the remote host.
     */
    public AbstractMagicClient(InetAddress host, int port) {

        this.host = host;
        this.port = port;
        this.flag = DEFAULT_FLAG;

    } // end constructor from host and port

    /**
     * Initializes a new <code>AbstractMagicClient</code> with the specified
     * host, port, and flag.
     *
     * @param host The address of the remote host to connect to.
     * @param port The port on which to connect to the remote host.
     * @param flag The flags which determine which cards to send back.
     */
    public AbstractMagicClient(InetAddress host, int port, String flag) {

        this.host = host;
        this.port = port;
        this.flag = flag;

    } // end constructor from host, port, and flag

    // METHODS BELOW
    
    /**
     * Returns the address of the host to connect to.
     *
     * @return The address of the host to connect to.
     */
    protected InetAddress getHost() {

        return this.host;

    } // end getHost method

    /**
     * Returns the port on which the remote host is listening.
     *
     * @return The port on which the remote host is listening.
     */
    protected int getPort() {

        return this.port;

    } // end getPort method

    /**
     * Returns the flags we want to send the server.
     *
     * @return The flags to send to the server.
     */
    protected String getFlag() {

        return this.flag;

    } // end getFlag method

    /**
     * Establishes a TCP connection to the host/port specified when this object
     * was created, reads a continuous stream of random cards from the socket's
     * input stream, and prints that data to the specified output stream.
     *
     * @param out The stream to which to write the random cards received.
     *
     * @throws IOException if there is an I/O error while receiving the data.
     */
    public abstract void printToStream(PrintStream out);



} // end AbstractMagicClient abstract class
