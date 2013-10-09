package de.unifr.acp.templates;

public class TraversalExample
	// interface implementation needs to be added
	implements TraversalTarget__ {

        private int priv_int;
	private String priv_string;
	protected boolean prot_boolean;
	protected String prot_string;
	public long publ_long;
	public Object publ_object;
	
	public TraversalExample(int priv_int, String priv_string,
			boolean prot_boolean, String prot_string, long publ_long,
			Object publ_object) {
		super();
		this.priv_int = priv_int;
		this.priv_string = priv_string;
		this.prot_boolean = prot_boolean;
		this.prot_string = prot_string;
		this.publ_long = publ_long;
		this.publ_object = publ_object;
	}

	// method needs to be added
	public void traverse__(Traversal__ t) {
		t.visit__(this, "priv_string", priv_string);
		t.visit__(this, "prot_string", prot_string);
		t.visit__(this, "publ_object", publ_object);
        // may have to traverse fields of the superclass
    }
}
