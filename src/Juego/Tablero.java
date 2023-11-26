package Juego;

import java.io.Serializable;

import Fichas.Pieza;

public class Tablero implements Serializable{
	private Pieza[][] tablero;
	
	public Tablero() {
		this.tablero=new Pieza[8][8];
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				this.tablero[i][j]=null;
				
			}
			
		}
	}
	
	public Pieza[][] getTablero(){
		return this.tablero;
	}
	
	public void setTablero(Pieza[][] tablero) {
		this.tablero=tablero;
	}
	
	public Pieza getPiezaPosicion(int x,int y) {
		//null en caso de vacio
		return this.tablero[x][y];
	}
	
	public void moverPieza(Pieza pieza,int x , int y) {
		int xAntigua = pieza.getX();
		int yAntigua = pieza.getY();
		
		this.tablero[x][y] = pieza;
		this.tablero[xAntigua][yAntigua] = null;
		
		pieza.setX(x);
		pieza.setY(y);
		
	}
	
}
