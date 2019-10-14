package common;

/**
 * This class represents simple cards in the game of <em>Magic the Gathering
 * </em>.
 *
 * @author Evert Ball
 * @version 01 November 2019
 */
public class Card implements Serializable {
    
    /** The unique identifier for a card (currently location in input file */
    private short id;

    /** The name of the card. */
    private String name;

    /** The Type of card (Land, Creature, etc) */
    private Type type;

    /** The energy required to use the card. */
    private String mana;
    
    /**
     * Create a single card for Magic the Gathering.
     *
     * @param id   Unique identifier (currently location in input file).
     * @param name The name of the card.
     * @param type The Type of card (Land, Creature, etc) as a Type object.
     * @param mana The energy required to use the card.
     */
    public Card(short id, String name, Type type, String mana) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.mana = mana;

    } // end constructor w/ Type object parameter included

    /**
     * Create a single card for Magic the Gathering.
     *
     * @param id   Unique identifier (currently location in input file).
     * @param name The name of the card.
     * @param type The type of card (Land, Creature, etc) as a String.
     * @param mana Energy required to use the card.
     */
    public Card(short id, String name, String type, String mana) {

        // TODO finish constructor
        this.id = id;
        this.name = name;
        //TODO Maybe make toLowerCase(). Not sure yet.
        this.type = Type.valueOf(type.toUpperCase());
        this.mana = mana;

    } // end constructor w/ Type object parameter included

} // end Card class
