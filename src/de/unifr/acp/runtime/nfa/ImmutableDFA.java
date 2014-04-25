package de.unifr.acp.runtime.nfa;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ImmutableDFA extends DFA {
    public ImmutableDFA(DFA dfa) {
        super();
        
        Set<NFAState> allDFAStates = new HashSet<>();
        allDFAStates.add(dfa.getStartState());
        Set<NFAState> newStates = Collections.singleton(dfa.getStartState());
        while (true) {
            boolean foundNewStates = false;
            for (NFAState newState : newStates) {
            for (Set<NFAState> targetStates : newState.getTransitionRelation().values()) {
                assert (targetStates.size() == 1);
                foundNewStates |= allDFAStates.addAll(targetStates);
            }
            }
            if (!foundNewStates) {
                break;
            } else {
                //.newState
            }
        }
        
        
    }
}
