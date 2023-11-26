import java.net.Socket;

public class Jugador {
	private Socket socket;
	private boolean color;

	public Jugador(Socket _socket, boolean _color) {
		this.socket = _socket;
		this.color = _color;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket _socket) {
		this.socket = _socket;
	}

	public boolean getColor() {
		return color;
	}

	public void setColor(boolean _color) {
		this.color = _color;
	}

}
