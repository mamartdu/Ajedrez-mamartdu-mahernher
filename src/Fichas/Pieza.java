package Fichas;

import java.io.Serializable;

public abstract class Pieza implements Serializable{
	    private String color;
	    
	    
	    public Pieza(String color) {
	        this.color = color;

	    }
	    
	    public String getColor() {
	        return color;
	    }
	    
		public abstract boolean[][] movimientosPosibles(int x, int y);


}


