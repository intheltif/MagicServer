package server;

import java.io.FileNotFoundException;

/**
 * An abstract class that contains fields and methods that may be common to
 * implementations of the 'chargen' server
 */
public abstract class AbstractMagicServer implements MagicServer {

    // TODO Document first three constant fields

    /** The number of cards to be sent for 3 types of cards */
    protected static final int THREE_TYPES = 60;

    /** The number of cards to be sent for 2 types of cards */
    protected static final int TWO_TYPES = 40;

    /** The number of cards to be sent for 1 type of card */
    protected static final int ONE_TYPE = 20;

    /** The default port on which a magic server listens */
    private static final int DEFAULT_PORT = 5791;
    
    /** The default source from which to send cards to clients */
    private static final CardSource DEFAULT_SOURCE = null;

    /** The default number of items to send back */
    private static final int NUM_ITEMS = ONE_TYPE;

    private int port;

    private CardSource source;

    private int numItemsToSend;
    
    /**
     * Initializes a new <code>AbstractMagicServer</code> using the default 
     * port and the default source.
     *
     * @throws FileNotFoundException If the source file cannot be found.
     */
    public AbstractMagicServer() throws FileNotFoundException {

        this.port = DEFAULT_PORT;
        this.source = DEFAULT_SOURCE;
        this.numItemsToSend = NUM_ITEMS;

    } // end empty AbstractMagicServer constructor

    /**
     * Initializes a new <code>AbstractMagicServer</code> using the specified
     * port and the default source.
     *
     * @param port The port to which the server will bind and listen for 
     *             incoming connections.
     *
     * @throws FileNotFoundException If the source file cannot be found.
     */
    public AbstractMagicServer(int port) throws FileNotFoundException {

        this.port = port;
        this.source = DEFAULT_SOURCE;
        this.numItemsToSend = NUM_ITEMS;

    } // end AbstractMagicServer constructor w/ port

    /**
     * Initializes a new <code>AbstractMagicServer</code> using the default
     * port and the specified source.
     *
     * @param source The source to use to send cards to connecting clients.
     */
    public AbstractMagicServer(CardSource source) {

        this.port = DEFAULT_PORT;
        this.source = source;
        this.numItemsToSend = NUM_ITEMS;
    
    } // end AbstractMagicServer constructor w/ source

    /**
     * Initializes a new <code>AbstractMagicServer</code> using the specified
     * port and source.
     *
     * @param port   The port to which the server will bind and listen for 
     *               incoming connections.
     * @param source The source to use to send cards to connecting clients.
     */
    public AbstractMagicServer(int port, CardSource source) {
        
        this.port = port;
        this.source = source;
        this.numItemsToSend = NUM_ITEMS;

    } // end AbstractMagicServer constructor w/ port & source

    /**
     * Initializes a new <code>AbstractMagicServer</code> using the specified
     * port, card source, and number of items to send.
     *
     * @param port     The port to which the server will bind and listen for
     *                 incoming connections.
     * @param source   The character stream source to use to send characters to
     *                 connecting clients.
     * @param numItems The number of items to send over the network before 
     *                 closing the connection.
     */
    public AbstractMagicServer(int port, CardSource source, int numItems) {

        this.port = port;
        this.source = source;
        this.numItemsToSend = numItems;

    } // end AbstractMagicServer constructor w/ port, source, & numItems
    
    /**
     * Get the port to which the server will bind and listen for incoming 
     * connections.
     *
     * @return The port to whic the server will bind and listen for incoming
     *         connections.
     */
    protected int getPort() {

        return this.port;

    } // end getPort method

    /**
     * Get the <code>CardSource</code> to use for generating the character
     * stream to send to clients.
     *
     * @return The <code>CardSource</code> used to generate the cards being
     *         sent to clients.
     */
    protected CardSource getSource() {

        return this.source;

    } // end getSource method
    
    /**
     * Change which source is being used to generate characters for the server.
     *
     * @param source A <code>CardSource</code> used to generate cards.
     */
    protected void changeSource(CardSource source) {

        this.source = source;

    } // end changeSource method

    /**
     * Change how many items to send back to the client. This number cannot
     * fall below the default of 20 items.
     *
     * @param numItems The number of items to send back to the client.
     */
    protected void changeItemsToSend(int numItems) {

        //TODO Make sure this is the correct check we should do here
        if(numItems < ONE_TYPE) {
            System.err.println("Unable to send less than 20 cards");
        } else if(numItems > THREE_TYPES){
            System.err.println("Unable to send more than 60 cards");
        } else {
            this.numItemsToSend = numItems;
        }

    } // end changeItemsToSend method

    /**
     * Determine the current number of items we should be sending back to the
     * client.
     *
     * @return The number of items to send back to the client.
     */
    protected int getItemsToSend() {

        return this.numItemsToSend;

    } // end getItemsToSend method

    /**
     * Determine the type (Spell, Creature, Land, or some combination thereof) 
     * as well as the number returned to the client. Three types of cards 
     * returns 60 cards, 2 types 40, 1 type 20.
     *
     * @param command The flag data returned from the server.
     */
    protected void setCardsReturned(String command) {

        // TODO Finish switch cases, use changeCardSource to choose which
        //      cards are to be sent back.
        switch (command) {
            case "A":
                changeItemsToSend(THREE_TYPES);
                break;

            case "C":
            case "L":
            case "S":
                changeItemsToSend(ONE_TYPE);
                break;

            case "CL":
            case "LC":
                changeItemsToSend(TWO_TYPES);

                break;

            case"SL":
            case "LS":
                changeItemsToSend(TWO_TYPES);
                break;

            case "SC":
            case "CS":
                changeItemsToSend(TWO_TYPES);
                break;
        } // end switch statement
    } // end setCardsReturned method

    /**
     * Causes the magic server to listen for requests.
     *
     * @throws MagicServerException if an error occurs while trying to listen
     *                              for connections.
     */
    public abstract void listen() throws MagicServerException;

} // end AbstractMagicServer abstract class
