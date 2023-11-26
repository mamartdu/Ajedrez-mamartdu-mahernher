package Juego;
import java.net.Socket;

public class Jugador {
	private Socket socket;
	private int color;

	public Jugador(Socket _socket, int _color) {
		this.socket = _socket;
		this.color = _color;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket _socket) {
		this.socket = _socket;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int _color) {
		this.color = _color;
	}

}
