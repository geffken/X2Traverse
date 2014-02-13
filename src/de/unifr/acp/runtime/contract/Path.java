package de.unifr.acp.runtime.contract;

/**
* Concrete Regular Access Path Expressions (in EBNF-like form):
* Path              = Or
* Or                = Concat { '|' Concat }
* Concat            = StarLike { '.' StarLike }
* StarLike          = PathLiteral { ( '*' | '+' | '?' ) }
* PathLiteral       = AnyPath (*, ?????, ?*) | PropPerm | '(' PathPerm ')' { | EmptyPathSet ({}, ???) | EmptyPathWord ('', ??) }
* PropPerm          = RegExp (/.../) | Name | AnyProp (?, /([\s\S]*)/) | AtProp (@, /EmptyPropSet^/)
*/
abstract public class Path {
    
    public Concat concatenate(Path path) {
        return new Concat(this, path);
    }
    
    public Or or(Path path) {
        return new Or(this, path);
    }
    
    public And and(Path path) {
        return new And(this, path);
    }
    
    abstract public boolean isNullable();
}
