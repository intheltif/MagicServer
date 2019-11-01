package server;

import common.Card;
import common.Type;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    /** Generates a random integer for an index into the deck of cards */
    private Random generator;

    /** The card type */
    private ArrayList<Type> typeArray;
    
    /**
     * Create a new <code>CardSource</code> object to store and choose cards
     * to send back to client.
     *
     * @throws FileNotFoundException If the input file cannot be found
     */
    public CardSource() throws FileNotFoundException {
        try {
            File cardFile = new File("/home/evert/Documents/classes/cs465/projects/project2/server/cards.csv");
            this.deck = new ArrayList<>();
            buildDeck(cardFile);
            this.generator = new Random();
            this.typeArray = new ArrayList<>();
        } catch(NullPointerException npe) {
            System.err.println("Pathname is null.");
            System.exit(1);
        }
    } //end CardSource constructor
    
    /**
     * Change the type of card allowed to be sent back to the client.
     * Allowed types are specified by @see Type
     *
     * @param type The type of card allowed to be sent via the network.
     */
    protected void setCardType(Type type) {

        this.typeArray.add(type);

    } // end setCardType method
    
    /**
     * Displays the current deck to the screen.
     */
    public void displayDeck() {

        // Displays the deck one card using the formatted String from the
        // Card's toString method.
        for(Card card : this.deck) {
            System.out.println(card.toString());
        }

    } // end displayDeck method
    
    /**
     * Gets a randomly chosen card to return to the client.
     *
     * @return A randomly chosen card to return to the client.
     */
    public Card next() {

        int randomIndex = this.generator.nextInt(this.deck.size() - 1);
        Card card = this.deck.get(randomIndex);
        for(Type type : typeArray) {
            while (!card.getType().equals(type)) {
                randomIndex = this.generator.nextInt(this.deck.size() - 1);
                card = this.deck.get(randomIndex);
            }
        } // end for-each

        return card;

    } // end next method

    /**
     * Builds the deck of cards based on the file named "<em>cards.csv</em>".
     *
     * @param inputFile The file to use to build the deck.
     * @throws FileNotFoundException If the input file cannot be found
     */
    private void buildDeck(File inputFile) throws FileNotFoundException {
        this.input = new Scanner(inputFile);

        // While there is more to add, create a card and add it to our deck
        while(input.hasNextLine()) {
            try {
                String line = input.nextLine();
                String[] lineArray = line.split(",");

                // Parse the line for the input we need
                short id = Short.parseShort(lineArray[0]);
                String name = lineArray[1].replaceAll("\"", "");
                Type type = determineType(lineArray[2]);
                String mana = lineArray[3];

                this.deck.add(new Card(id, name, type, mana));
            } catch(NoSuchElementException nsee) {
                //finish catching
            }
        }

        this.input.close();
    }

    /**
     * Returns Type of a card based on certain Regular Expressions.
     *
     * If the specified String contains "Creature" or "Planeswalker", then the
     * CREATURE type is returned.
     *
     * If the specified String contains "Artifact", "Instant", "Enchantment",
     * or "Sorcery", then the SPELL type is returned.
     *
     * If the specified String contains "Land", then the LAND type is returned.
     *
     * If none of the above Strings are matched, the UNKNOWN type is returned.
     *
     * @param typeStr The string to parse for certain words.
     * @return The Type of card we found.
     */
    private Type determineType(String typeStr) {
        Type typeToReturn = Type.UNKNOWN;

        // pattern and matcher to find any word that is a creature in MTG
        Pattern creature = Pattern.compile("([Cc]reature)+|([Pp]laneswalker)+");
        Matcher findCreature = creature.matcher(typeStr);

        // pattern and matcher to find any word that is a spell in MTG
        Pattern spell = Pattern.compile("([Aa]rtifact)+|([Ii]nstant)+|([Ee]nchantment)+|([Ss]orcery)+");
        Matcher findSpell = spell.matcher(typeStr);

        // pattern and matcher to find any word that is a land in MTG
        Pattern land = Pattern.compile("([Ll]and)+");
        Matcher findLand = land.matcher(typeStr);

        // Perform the boolean operations to find if the given string matches
        // our types
        if(findCreature.find()) {
            typeToReturn = Type.CREATURE;
        } else if(findSpell.find()) {
            typeToReturn = Type.SPELL;
        } else if(findLand.find()) {
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

        try {
            CardSource cs = new CardSource();
            cs.setCardType(Type.CREATURE);
            for(int i=0; i < 50; i++) {
                System.out.println(cs.next().toString());
            }
        } catch(FileNotFoundException fnfe) {
            System.err.println("File not found. Dang...");
            System.exit(1);
        }

    } // end main method

} // end CardSource class
