package de.unifr.acp.parser;
import de.unifr.acp.parser.MyParser.yyInput;
import java.io.IOException;

%%

%{
	@Override
	public boolean advance() throws IOException
	{
	    	boolean result;
	    
	    	do {
	    	    result = yylex ();
	    	}
		   	while (yytext().equals(" ") ||
			    yytext().equals("\t") ||
			    yytext().equals("\r") ||
			    yytext().equals("\n") ||
			    yytext().equals("\b") ||
			    yytext().equals("\015"));
		return result;
	}

	@Override
	public int token()
	{
		if (yytext().equals("*"))
		    return MyParser.star;
		else if (yytext().equals("?"))
		    return MyParser.qmark;
		else if (yytext().equals("+"))
		    return MyParser.plus;
		else if (yytext().equals("@"))
		    return 64;
		else if (yytext().equals(","))
		    return 44;
		else if (yytext().equals("."))
		    return 46;
		else if (yytext().equals("["))
		    return 91;
		else if (yytext().equals("]"))
		    return 93;
		else if (yytext().equals("("))
		    return 40;
		else if (yytext().equals(")"))
		    return 41;
		else if (yytext().equals("|"))
		    return 124;
		else 
		    return MyParser.identifier;
	}

	@Override
	public Object value()
	{
		return yytext();
	}
%}

%public
%class MyScanner
%implements yyInput

%type boolean
%eofval{
	return false;
%eofval}


%%

[a-zA-Z_][a-zA-Z_0-9]* {value();return true;}
\.                     {value();return true;}
,                      {value();return true;}
\@                     {value();return true;}
[ \t\r\n\b\015]        {value();return true;}
\?                     {value();return true;}
\*                     {value();return true;}
\+                     {value();return true;}
\[                     {value();return true;}
\]                     {value();return true;}
\|                     {value();return true;}
\(                     {value();return true;}
\)                     {value();return true;}
