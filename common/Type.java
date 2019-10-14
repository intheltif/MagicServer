package common;

/**
 * Enumeration for allowed <code>Card</code> type in 
 * <em>Magic The Gathering</em>.
 *
 * @author Evert Ball
 * @version 01 November 2019
 */
public enum Type extends Enum<Type> {

    // TODO finish enum
    
    /** Literal for Artifact cards */
    public static final Type ARTIFACT;

    /** Literal for Creature cards */
    public static final Type CREATURE;

    /** Literal for Land cards */
    public static final Type LAND;

    /** Literal for Spell cards */
    public static final Type SPELL;

    /** Literal for Unknown cards */
    public static final Type UNKNOWN;
    
    /**
     * Returns an array containing the constants of this enum type, in the
     * order they are declared. This method may be used to iterate over the
     * constants as follows: 
     * <code>
     * for(Type C : Type.values())
     *     System.out.println(c);
     * </code>
     * 
     * @return An array containing the constants of this enum type, in the
     *         order they are declared.
     */
    public static Type[] values() {
        //TODO Is this the right thing to do?
        Type[] types = new Type[5];

        types[0] = ARTIFACT;
        types[1] = CREATURE;
        types[2] = LAND;
        types[3] = SPELL;
        types[4] = UNKNOWN;

        return types;

    } // end values method

    /**
     * Returns the enum constant of this type with the specified name. The 
     * string must match <em>exactly</em> an identifier used to declare an 
     * enum constant in this type (extraneous whitespace characters are not
     * permitted).
     *
     * @param name The name of the enum constant to be returned.
     *
     * @return The enum constant with the specified name.
     *
     * @throws IllegalArgumentException If this enum type has no constant with 
     *         the specified name.
     * @throws NullPointerException If the argument is null.
     */
    public static Type valueOf(String name) {

        // TODO return the enum type

    } // end valueOf method
    
    /**
     * Returns the String representation of this Type enum constant.
     *
     * @return The String representation of this Type enum constant.
     */
    @Override
    public String toString() {

        // TODO return enum type as a string

    } // end toString method

} // end Type enumeration
