package de.unifr.acp.fst;

/**
 * This class declares special symbols to be used in conjunction with the input
 * alphabet. The symbols are represented as {@link String}s but avoid conflicts
 * with any valid Java identifiers or keywords.
 * 
 * @author Mohammad Shahabi
 * @author Manuel Geffken
 */
public final class MetaCharacters {

    /**
     * Hidden constructor.
     */
    private MetaCharacters() {
    }

    /**
     * String representation for question mark transitions representing the
     * whole alphabet ( e.g. in x.? ).
     */
    public static final String QUESTION_MARK = "(QUESTION)";
    
    /**
     * The epsilon character (input alphabet).
     */
    public static final String EPSILON = "(EPSILON)";
}
