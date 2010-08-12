package modelo;

import java.util.LinkedList;

public class ColaSync < T extends Object >{
	
	private LinkedList<T> elems;
	
	public ColaSync(){
		this.elems = new LinkedList<T>();
	}
	
	public synchronized void acolar(T elem) {
		elems.addFirst(elem);
	}
	 
	public synchronized T desacolar() {
		if( !elems.isEmpty() )
			return elems.removeLast();
		else
			return null;			
			
	}
	
	
}
