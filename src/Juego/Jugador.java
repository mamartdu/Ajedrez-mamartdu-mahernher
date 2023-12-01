package Juego;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class Jugador implements Serializable{
	//private Socket socket;
	private int puerto;
	private String ip;
	private int color;

	public Jugador(int _color,int _puerto,String _ip) {
		//this.socket = _socket;
		this.color = _color;
		this.ip = _ip;
		this.puerto = _puerto;
		
	}
	
	public Jugador(int _puerto,String _nombre) {
		//this.socket = _socket;
		this.ip = _nombre;
		this.puerto = _puerto;

		
	}

	/*public Socket getSocket() {
		return socket;
	}*/

	/*public void setSocket(Socket _socket) {
		this.socket = _socket;
	}*/

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int _color) {
		this.color = _color;
	}
	
	


}
