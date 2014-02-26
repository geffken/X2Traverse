package de.unifr.acp.runtime.contract;

/** A special (invalid Java) identifier representing the @ symbol. */
public class AtLit extends Identifier {

    public AtLit() {
        super("@");
    }

}
