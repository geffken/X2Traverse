package de.unifr.acp.templates;

public class TraversalImpl implements Traversal__ {
	// instance variable to keep automaton state
	public TraversalImpl() {
		// initialize automaton state
	}

	@Override
	public void visit__(String fieldname, Object fieldvalue) {
		if (!(fieldvalue instanceof TraversalTarget__)) {
			return;
		}
		// remember automaton state
		// step automaton according to field name
		((TraversalTarget__)fieldvalue).traverse__(this);
		// backtrack to previous automaton state
	}

}
