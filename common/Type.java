package common;

/**
 * Enumeration for allowed <code>Card</code> type in 
 * <em>Magic The Gathering</em>.
 *
 * @author Evert Ball
 * @version 01 November 2019
 */
public enum Type {

    /** Literal for Artifact cards */
    ARTIFACT("Artifact"),

    /** Literal for Creature cards */
    CREATURE("Creature"),

    /** Literal for Land cards */
    LAND("Land"),

    /** Literal for Spell cards */
    SPELL("Spell"),

    /** Literal for Unknown cards */
    UNKNOWN("Uknown");

    private final String cardType;

    private Type(String cardType) {
        this.cardType = cardType;
    }

} // end Type enumeration
