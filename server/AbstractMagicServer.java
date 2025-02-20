package server;

import common.Type;

import java.io.FileNotFoundException;

/**
 * An abstract class that contains fields and methods that may be common to
 * implementations of the 'chargen' server
 */
public abstract class AbstractMagicServer implements MagicServer {

    /** The number of cards to be sent for 3 types of cards */
    private static final int THREE_TYPES = 60;

    /** The number of cards to be sent for 2 types of cards */
    private static final int TWO_TYPES = 40;

    /** The number of cards to be sent for 1 type of card */
    private static final int ONE_TYPE = 20;

    /** The default port on which a magic server listens */
    private static final int DEFAULT_PORT = 5791;

    /** Represents a FAILED exit status */
    private static final int FAILURE = 1;

    /** The default number of items to send back */
    private static final int NUM_ITEMS = THREE_TYPES;

    /** The port for the MagicServer to do work over the network */
    private int port;

    /** The <code>CardSource</code> to use to send cards across the network */
    private CardSource source;

    /** The number of Cards to send back to the client, based on client flags */
    private int numItemsToSend;
    
    /**
     * Initializes a new <code>AbstractMagicServer</code> using the default 
     * port and the default source.
     *
     * @throws FileNotFoundException If the source file cannot be found.
     */
    public AbstractMagicServer() throws FileNotFoundException {

        this.port = DEFAULT_PORT;
        this.source = new CardSource();
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
        this.source = new CardSource();
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

        // Make sure that the number to change to is within acceptable range
        if(numItems < ONE_TYPE) {
            System.err.println("Unable to send less than 20 cards");
            System.exit(FAILURE);
        } else if(numItems > THREE_TYPES){
            System.err.println("Unable to send more than 60 cards");
            System.exit(FAILURE);
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

        switch (command) {
            case "-A":
                changeItemsToSend(THREE_TYPES);
                this.source.setCardType(Type.LAND);
                this.source.setCardType(Type.CREATURE);
                this.source.setCardType(Type.SPELL);
                break;
            case "-C":
                changeItemsToSend(ONE_TYPE);
                this.source.setCardType(Type.CREATURE);
                break;
            case "-L":
                changeItemsToSend(ONE_TYPE);
                this.source.setCardType(Type.LAND);
                break;
            case "-S":
                changeItemsToSend(ONE_TYPE);
                this.source.setCardType(Type.SPELL);
                break;

            case "-CL":
            case "-LC":
                changeItemsToSend(TWO_TYPES);
                this.source.setCardType(Type.CREATURE);
                this.source.setCardType(Type.LAND);
                break;

            case "-SL":
            case "-LS":
                changeItemsToSend(TWO_TYPES);
                this.source.setCardType(Type.SPELL);
                this.source.setCardType(Type.LAND);
                break;

            case "-SC":
            case "-CS":
                changeItemsToSend(TWO_TYPES);
                this.source.setCardType(Type.CREATURE);
                this.source.setCardType(Type.SPELL);
                break;
            default:
                System.out.println("Unrecognized flags present. Unable to change source.");
                System.exit(FAILURE);
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
