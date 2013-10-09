package de.unifr.acp.contract;

/**
* Concrete Regular Access Path Expressions (in EBNF-like form):
* Path              = Or
* Or                = Concat { '|' Concat }
* Concat            = StarLike { '.' StarLike }
* StarLike          = PathLiteral { ( '*' | '+' | '?' ) }
* PathLiteral       = AnyPath (*, ¬∅, ?*) | PropPerm | '(' PathPerm ')' { | EmptyPathSet ({}, ∅) | EmptyPathWord ('', ε) }
* PropPerm          = RegExp (/.../) | Name | AnyProp (?, /([\s\S]*)/) | AtProp (@, /EmptyPropSet^/)
*/
public class Path {
    
    public Concat concatenate(Path path) {
        return new Concat(this, path);
    }
    
    public Or or(Path path) {
        return new Or(this, path);
    }
    
    public And and(Path path) {
        return new And(this, path);
    }

}
