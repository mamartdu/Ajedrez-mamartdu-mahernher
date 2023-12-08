package Juego;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Fichas.Pieza;
import setup.Constantes;

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
	
	public void colocarPieza(Pieza pieza) {
		
		int x = pieza.getX();
		int y = pieza.getY();
		
		this.tablero[x][y] = pieza;
	
		pieza.setX(x);
		pieza.setY(y);
	}
	
	   public List<Pieza> getPiezasNegras() {
	        List<Pieza> piezas = new ArrayList<Pieza>();

	        for (int x = 0; x < 8; x++) {
	            for (int y = 0; y < 8; y++) {
	                Pieza pieza = this.tablero[x][y];
	                if (pieza != null) {
	                	if(pieza.getColor() == Constantes.COLOR_NEGRO) {
	                		piezas.add(pieza);
	                	}
	                    
	                }
	            }
	        }

	        return piezas;
	    }
	   
	   public List<Pieza> getPiezasBlancas() {
	        List<Pieza> piezas = new ArrayList<Pieza>();

	        for (int x = 0; x < 8; x++) {
	            for (int y = 0; y < 8; y++) {
	                Pieza pieza = this.tablero[x][y];
	                if (pieza != null) {
	                	if(pieza.getColor() == Constantes.COLOR_BLANCO) {
	                		piezas.add(pieza);
	                	}
	                    
	                }
	            }
	        }

	        return piezas;
	    }
	
}
