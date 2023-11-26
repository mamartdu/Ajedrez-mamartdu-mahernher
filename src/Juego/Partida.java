package Juego;
import java.net.Socket;

public class Partida {
	private Tablero tablero;
	private Jugador j1 = null, j2 = null;

	public Partida(Socket s) {
		if (j1 == null) {
			j1 = new Jugador(s, true);
		} else {
			j2 = new Jugador(s, false);

		}
	}

	public void addJugador(Socket s) {
		j2 = new Jugador(s, false);
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

	public void jugar() {

	}

}
