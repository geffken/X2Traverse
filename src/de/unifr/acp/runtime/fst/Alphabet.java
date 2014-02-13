package de.unifr.acp.runtime.fst;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the alphabet for the {@link de.unifr.acp.runtime.fst.FST}.
 * 
 * @author Mohammad Shahabi
 * @author Manuel Geffken
 */
public class Alphabet {

    /**
     * The internal alphabet.
     */
    private Set<Character> charSet;

    /**
     * All the possible and allowed characters represented as a String.
     */
    private static String defaultCharSetString = "0123456789.,@$#%^&-+abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static Set<Character> defaultCharSet = stringToCharSet(defaultCharSetString);

    private static Set<Character> stringToCharSet(String str) {
        Set<Character> ret = new HashSet<Character>();
        for (char c : str.toCharArray()) {
            ret.add(c);
        }
        return ret;
    }

    /**
     * Constructs a new Alphabet as defined in
     * {@link Alphabet#defaultCharSetString}.
     */
    public Alphabet() {
        charSet = defaultCharSet;
    }

    /**
     * Constructor allocating a new alphabet using the specified char set.
     * 
     * @param charSet
     *            is the allowed {@link Alphabet#charSet}
     * @throws IllegalArgumentException
     *             the specified char set is not a subset of
     *             {@link Alphabet#defaultCharSetString}
     */
    public Alphabet(final Set<Character> charSet) {
        if (!validAlphabet(charSet)) {
            throw new IllegalArgumentException("Illegal char set.");
        }
        this.charSet = charSet;
    }

    /**
     * Checks whether the specified characters are valid comparing them with the
     * {@link Alphabet#defaultCharSetString}.
     * 
     * @param characters
     *            the character set to check
     * @return <code>true</code> if it is a valid alphabet (characters.length >=
     *         1 && characters <= definedAlphabet)
     */
    public final boolean validAlphabet(Set<Character> characters) {
        if (characters.size() < 1) {
            return false;
        }

        if (!defaultCharSet.containsAll(characters)) {
            return false;
        }

        return true;
    }

    /**
     * Checks whether the specified character is in the {@link Alphabet#charSet}
     * and whether the alphabet is valid.
     * 
     * @param testChar
     *            The input character to be checked
     * @return <code>true</code> if the character is valid and is in the
     *         {@link Alphabet#charSet}
     */
    public final boolean valid(final char testChar) {
        return charSet.contains(testChar);
    }

    /**
     * Given an input {@link String} this method validates whether the String is
     * of allowed characters or not.
     * 
     * @param testString
     *            The input {@link String} to be checked
     * @return <code>true</code> if it is a valid {@link String}
     */
    public final boolean valid(final String testString) {
        if (testString.length() == 0) {
            return false;
        }

        charSet.containsAll(stringToCharSet(testString));

        return true;
    }
}
