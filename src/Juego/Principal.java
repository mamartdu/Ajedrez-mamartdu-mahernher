package Juego;

public class Principal {

	public static void main(String[] args) {
			Juego juego =new Juego();
			juego.colocarFichasIniciales();
			
			Tablero tablero = juego.getTablero();
			
			//Peon
			/*System.out.println("---Peon---");
	
			System.out.println(tablero.getPiezaPosicion(1,1).movimientosPosibles(1,2, tablero));
			
			tablero.moverPieza(tablero.getPiezaPosicion(1,1), 1, 3);
			System.out.println(tablero.getPiezaPosicion(1,3).movimientosPosibles(2,4, tablero));
			tablero.moverPieza(tablero.getPiezaPosicion(2,6), 2, 4);
			System.out.println(tablero.getPiezaPosicion(1,3).movimientosPosibles(2,4, tablero));//comer 
			
			//Rey
			System.out.println("---rey---");
			System.out.println(tablero.getPiezaPosicion(3,0).movimientosPosibles(4,0, tablero));
			System.out.println(tablero.getPiezaPosicion(3,0).movimientosPosibles(3,1, tablero));
			tablero.moverPieza(tablero.getPiezaPosicion(3,1), 3, 5);
			System.out.println(tablero.getPiezaPosicion(3,0).movimientosPosibles(3,1, tablero));
			
			tablero.moverPieza(tablero.getPiezaPosicion(3,7), 3, 4);
			tablero.moverPieza(tablero.getPiezaPosicion(3,0), 3, 3);
			System.out.println(tablero.getPiezaPosicion(3,3).movimientosPosibles(3,4, tablero));//comer 
			System.out.println(tablero.getPiezaPosicion(3,3).movimientosPosibles(4,3, tablero));
			System.out.println(tablero.getPiezaPosicion(3,3).movimientosPosibles(3,5, tablero));
			System.out.println(tablero.getPiezaPosicion(3,3).movimientosPosibles(2,2, tablero));
			System.out.println(tablero.getPiezaPosicion(3,3).movimientosPosibles(4,4, tablero));*/
			
			Interfaz i =new Interfaz(juego);
			i.mostrarTablero();
			
			
	}

}
