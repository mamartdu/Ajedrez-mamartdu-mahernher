package Juego;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import setup.Constantes;

public class Partida implements Runnable {
	private Juego juego;
	private Tablero tablero;
	private Jugador j1 = null, j2 = null;
	private boolean jaqueMate = false;

	public Partida(Socket s) {
		if (j1 == null) {
			j1 = new Jugador(s,Constantes.COLOR_NEGRO);
		} else {
			j2 = new Jugador(s,Constantes.COLOR_BLANCO);

		}
	}

	public void addJugador(Socket s) {
		j2 = new Jugador(s, Constantes.COLOR_BLANCO);
	}

	public Jugador getJugador1() {
		return j1;
	}

	public void setJugador1(Jugador j1) {
		this.j1 = j1;
	}

	public Jugador getJugador2() {
		return j2;
	}

	public void setJugador2(Jugador j2) {
		this.j2 = j2;
	}
	
	public boolean getJaqueMate() {
		return jaqueMate;
	}

	public void setJaqueMate(boolean _jaqueMate ) {
		this.jaqueMate = _jaqueMate;
	}
	

	public void jugar() {
		juego = new Juego();
		tablero = new Tablero();
		
		try (	
			ObjectInputStream inputJugador1=new ObjectInputStream(j1.getSocket().getInputStream());
			ObjectOutputStream outputJugador1=new ObjectOutputStream(j1.getSocket().getOutputStream());
			ObjectInputStream inputJugador2=new ObjectInputStream(j2.getSocket().getInputStream());
			ObjectOutputStream outputJugador2=new ObjectOutputStream(j2.getSocket().getOutputStream());
			){
			
			while (!getJaqueMate()) {
				outputJugador1.writeObject(tablero);
				tablero = (Tablero) inputJugador1.readObject();
				if(juego.isTurnoNegras()) {
					//MUEVEN NEGRAS
					//Deberia ser 
					//juego.muevesPieza();
					//Dentro de muevesPieza , cambiamos turno
				}
				
				outputJugador2.writeObject(tablero);
				
				if(!getJaqueMate() && juego.isTurnoBlancas()) {
					//MUEVEN BLANCAS
					//Deberia ser 
					//juego.muevesPieza();
					//Dentro de muevesPieza , cambiamos turno
				}
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		if(getJugador1() != null && getJugador2() != null) {
			jugar();
		}
	}

}
