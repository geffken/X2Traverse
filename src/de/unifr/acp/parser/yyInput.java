package de.unifr.acp.parser;

import java.io.IOException;

/** must be implemented by a scanner object to supply input to the parser.
    Does not depend on parser class.
*/
public interface yyInput {

    /** move on to next token.
      @return <tt>false</tt> if positioned beyond tokens.
      @throws IOException on input error.
    */
    boolean advance () throws java.io.IOException;

    /** classifies current token.
      Should not be called if {@link #advance()} returned <tt>false</tt>.
      @return current <tt>%token</tt> or single character.
    */
    int token ();

    /** associated with current token.
      Should not be called if {@link #advance()} returned <tt>false</tt>.
      @return value for {@link #token()}.
    */
    Object value ();
}
