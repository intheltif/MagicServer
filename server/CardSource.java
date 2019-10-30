package server;

import common.Card;
import common.Type;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 * Class that defines the type of cards that can be returned for a deck in
 * Magic the Gathering.
 *
 * @author Evert Ball
 * @version 1 November 2019
 */
public class CardSource {

    // TODO Add fields as necessary.

    /** The deck of Magic the Gathering cards */
    private ArrayList<Card> deck;

    /** The Scanner to read in the cards from the CSV */
    private Scanner input;

    /** Generates Random stuff TODO FIGURE OUT WHAT THIS IS???? */
    private Random generator;

    /** The card type */
    private Type type;
    
    /**
     * Create a new <code>CardSource</code> object to store and choose cards
     * to send back to client.
     *
     * @throws FileNotFoundException If the input file cannot be found
     */
    public CardSource() throws FileNotFoundException {

        // TODO finish constructor

    } //end CardSource constructor
    
    /**
     * Change the type of card allowed to be sent back to the client.
     * Allowed thypes are specified by @see CardType.
     *
     * @param type The type of card allowed to be sent via the network.
     */
    protected void setCardType(Type type) {

        // TODO finish setCardType method

    } // end setCardType method
    
    /**
     * Displays the current deck to the screen.
     */
    public void displayDeck() {

        // TODO finish displayDeck method

    } // end displayDeck method
    
    /**
     * Gets a randomly chosen card to return to the client.
     *
     * @return A randomly chosen card to return to the client.
     */
    public Card next() {

        // TODO finish next method
        Card card = null;

        return card;

    } // end next method

    /**
     * Used for testing data input.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {

        // TODO finish main method

    } // end main method

} // end CardSource class
