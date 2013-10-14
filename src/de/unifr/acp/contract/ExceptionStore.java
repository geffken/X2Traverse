package de.unifr.acp.contract;

import java.util.HashMap;
import java.util.Map;

public class ExceptionStore {
	private Map<Integer,String> exceptions = new HashMap <Integer,String> ();

	public int excCounter = 0;

	public void insertException (String str){
		exceptions.put(new Integer (excCounter), str);
	}

	public String[] getExceptions (){
		return exceptions.values().toArray(new String [exceptions.size()]);
	}

}