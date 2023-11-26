package Fichas;

import java.io.Serializable;

import Juego.Tablero;

public abstract class Pieza implements Serializable{
	    private String color;
	    private int x;
	    private int y;
	    
	    
	    public Pieza(String color) {
	        this.color = color;

	    }
	    
	    public String getColor() {
	        return color;
	    }

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

	    
		public abstract boolean[][] movimientosPosibles(Tablero tablero);


}

