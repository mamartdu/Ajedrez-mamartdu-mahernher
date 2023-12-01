package Juego;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class Peticion implements Runnable {
    private Socket s;
    private HashMap<String, Jugador> jugadores;

    public Peticion(Socket s, HashMap<String, Jugador> _jugadores) {
        this.s = s;
        this.jugadores = _jugadores;
    }

    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream())) {
            out.writeObject(this.jugadores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}