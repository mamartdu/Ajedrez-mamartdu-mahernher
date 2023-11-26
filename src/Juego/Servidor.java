package Juego;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Servidor {
	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newCachedThreadPool();
		ServerSocket socket =null;
		Socket oConexion = null;
		Partida oPartida=null;
		try {
			socket = new ServerSocket(80);
			while (true) {
				try {
					oConexion = socket.accept();
					oPartida =new Partida(oConexion);
					pool.execute(oPartida);
					
				} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}finally {
			pool.shutdown();
		}
	}
	
}
