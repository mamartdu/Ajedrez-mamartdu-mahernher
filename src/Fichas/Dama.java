package Fichas;

import Juego.Tablero;

public class Dama extends Pieza{

	public Dama(int color,int x,int y) {
		super(color,x,y);
	}

	@Override
	public boolean movimientosPosibles(int xNuevo, int yNuevo, Tablero tablero) {
		boolean [][]b=new boolean[8][8];
		int x = this.getX();
		int y = this.getY();
		int num=x;
		int letra=y;
		for(int i=1;i<8;i++) {
			if(num+i<=7){
				if(letra+i<=7) {
					b[num+i][letra+i]=true;
				}
				if(letra-i>=0) {
					b[num+i][letra-i]=true;
				}
			}
			if(num-i>=0){
				if(letra+i<=7) {
					b[num-i][letra+i]=true;
				}
				if(letra-i>=0) {
					b[num-i][letra-i]=true;
				}
			}
				
		}
		
		if(b[xNuevo][yNuevo]==true && intermediasVacias(xNuevo,yNuevo, tablero)) {
			
			if(tablero.getPiezaPosicion(xNuevo,yNuevo) != null || tablero.getPiezaPosicion(xNuevo,yNuevo).getColor()!=this.getColor()) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean intermediasVacias(int xNuevo, int yNuevo, Tablero tablero) {//DEVUELVE TRUE SI NO ENCUENTRA PIEZAS EN SU CAMINO
		boolean vacio=true;
		
		int casillas=Math.abs(xNuevo-this.getX());
		for(int i=1;i<casillas && vacio;i++){
			if(this.getX()>xNuevo) {
				if(this.getY()>yNuevo) {//mirar hacia arriba
					if(!(tablero.getPiezaPosicion(this.getX()-i,this.getY()-i) != null )) {
						vacio=false;
					}
				}
				else {
					if(!(tablero.getPiezaPosicion(this.getX()-i,this.getY()+i) != null)) {
						vacio=false;
					}
				}
			}
			else {
				if(this.getY()>yNuevo) {//mirar hacia abajo
					if(!(tablero.getPiezaPosicion(this.getX()+i,this.getY()-i) != null)) {
						vacio=false;
					}
				}
				else {
					if(!(tablero.getPiezaPosicion(this.getX()+i,this.getY()+i) != null)) {
						vacio=false;
					}

				}
			}
			
		}
		
		return vacio;
		
	}

}
