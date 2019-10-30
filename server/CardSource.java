package server;

import common.Card;
import common.Type;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.NoSuchElementException;
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

        File cardFile = new File("./cards.csv");
        buildDeck(cardFile);

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
     * Builds the deck of cards based on the file named "<em>cards.csv</em>".
     *
     * @param inputFile The file to use to build the deck.
     */
    private void buildDeck(File inputFile) {
        try {
            this.input = new Scanner(inputFile);
        }catch(FileNotFoundException fnfe) {
            //TODO maybe throw instead, change system.exit to constant value
            System.err.println("The file 'cards.csv' could not be found.");
            System.exit(1);
        }

        // While there is more to add, create a card and add it to our deck
        while(input.hasNextLine()) {
            try {
                String line = input.nextLine();
                String[] lineArray = line.split(",");

                // Parse the line for the input we need
                short id = Short.parseShort(lineArray[0]);
                String name = lineArray[1];
                Type type = getType(lineArray[2]);
                String mana = lineArray[3];

                this.deck.add(new Card(id, name, type, mana));
            } catch(NoSuchElementException nsee) {
                //finish catching
            }
        }

        this.input.close();
    }

    /**
     * //TODO Update this documentation
     * Returns the enum type of the card based on if it matches a certain
     * regex.
     * @param typeStr The string to parse for certain words.
     * @return The Type of card we found.
     */
    private Type getType(String typeStr) {
        Type typeToReturn = Type.UNKNOWN;

        if(typeStr.matches("/([Cc]reature)+|([Pp]laneswalker)+/gm")) {
            typeToReturn = Type.CREATURE;
        } else if(typeStr.matches("/ ([Aa]rtifact)+|([Ii]nstant)+|([Ee]nchantment)+|([Ss]orcery)+/gm")) {
            typeToReturn = Type.SPELL;
        } else if(typeStr.matches("/([Ll]and)+/gm")) {
            typeToReturn = Type.LAND;
        }

        return typeToReturn;
    }

    /**
     * Used for testing data input.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {

        // TODO finish main method

    } // end main method

} // end CardSource class
