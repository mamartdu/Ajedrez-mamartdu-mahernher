package Fichas;

import java.io.Serializable;

import Juego.Tablero;

public abstract class Pieza implements Serializable{
	    private int color;
	    private int x;
	    private int y;
	    
	    
	    public Pieza(int color,int x ,int y) {
	        this.color = color;
	        this.x = x;
	        this.y= y;
	    }
	    
	    public int getColor() {
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

