package Juego;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Fichas.*;
import setup.Constantes;

public class Interfaz extends JPanel {
	private Juego juego;
	private JButton[][] casillas;
	private JFrame ventana;
	private Pieza piezaSeleccionada = null;
	private CyclicBarrier barrier = new CyclicBarrier(2);
	private int color;
	private boolean termiando = false;


	public Interfaz(Juego juego,int color) {
		this.juego = juego;
		this.color = color;
		if(color == Constantes.COLOR_BLANCO) {
			this.ventana = new JFrame("Blancas");
		}else {
			this.ventana = new JFrame("Negras");
		}
		
		casillas = new JButton[8][8];

		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				casillas[j][i] = new JButton();

				if (juego.getTablero().getPiezaPosicion(j, i) instanceof Peon) {
					if (juego.getTablero().getPiezaPosicion(j, i).getColor() == Constantes.COLOR_BLANCO) {
						casillas[j][i].setIcon(new ImageIcon("./src/img/peonBlanco.png"));
					} else {
						casillas[j][i].setIcon(new ImageIcon("./src/img/peonNegro.png"));
					}
				}
				if (juego.getTablero().getPiezaPosicion(j, i) instanceof Torre) {
					// botones[i][j].setText("T");
					if (juego.getTablero().getPiezaPosicion(j, i).getColor() == Constantes.COLOR_BLANCO)
						casillas[j][i].setIcon(new ImageIcon("./src/img/torreBlanco.png"));
					else {
						casillas[j][i].setIcon(new ImageIcon("./src/img/torreNegro.png"));
					}
				}
				if (juego.getTablero().getPiezaPosicion(j, i) instanceof Alfil) {
					if (juego.getTablero().getPiezaPosicion(j, i).getColor() == Constantes.COLOR_BLANCO)
						casillas[j][i].setIcon(new ImageIcon("./src/img/alfilBlanco.png"));
					else {
						casillas[j][i].setIcon(new ImageIcon("./src/img/alfilNegro.png"));
					}
				}
				if (juego.getTablero().getPiezaPosicion(j, i) instanceof Caballo) {
					if (juego.getTablero().getPiezaPosicion(j, i).getColor() == Constantes.COLOR_BLANCO)
						casillas[j][i].setIcon(new ImageIcon("./src/img/caballoBlanco.png"));
					else {
						casillas[j][i].setIcon(new ImageIcon("./src/img/caballoNegro.png"));
					}
				}
				if (juego.getTablero().getPiezaPosicion(j, i) instanceof Rey) {
					if (juego.getTablero().getPiezaPosicion(j, i).getColor() == Constantes.COLOR_BLANCO)
						casillas[j][i].setIcon(new ImageIcon("./src/img/reyBlanco.png"));
					else {
						casillas[j][i].setIcon(new ImageIcon("./src/img/reyNegro.png"));
					}
				}
				if (juego.getTablero().getPiezaPosicion(j, i) instanceof Reina) {
					if (juego.getTablero().getPiezaPosicion(j, i).getColor() == Constantes.COLOR_BLANCO)
						casillas[j][i].setIcon(new ImageIcon("./src/img/reinaBlanco.png"));
					else {
						casillas[j][i].setIcon(new ImageIcon("./src/img/reinaNegro.png"));
					}
				}

				casillas[j][i].setPreferredSize(new Dimension(60, 60));
				if ((i + j + 1) % 2 == 0) {
					casillas[j][i].setBackground(new Color(184, 103, 38));
				} else {
					casillas[j][i].setBackground(new Color(246, 217, 183));
				}

				casillas[j][i].addMouseListener(new pulsador(juego.getTablero().getPiezaPosicion(j, i), j, i));

				add(casillas[j][i]);
			}
		}
		setLayout(new GridLayout(8, 8));
	}
	
    public void cambioDeTurno() {
        juego.cambiarTurno();

    }

	private class pulsador implements MouseListener {
		private Pieza pieza;
		private int x;
		private int y;

		public pulsador(Pieza _pieza, int x, int y) {
			this.pieza = _pieza;
			this.x = x;
			this.y = y;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(juego.getTurno() != color) { //comprobar turno
				System.out.println("NO ES TU TURNO");
			}else {
				if (piezaSeleccionada == null) { //seleccionar ficha
					if (pieza != null) {
						if( pieza.getColor() == juego.getTurno()) {
							piezaSeleccionada = pieza;
							System.out.println("seleccionada: "+pieza.getClass().getSimpleName());
						}else {
							System.out.println("NO ES DE TU COLOR");
						}
				
					}else {
						System.out.println("NO ES UNA FICHA");
					}
				} else {
					
					if ((piezaSeleccionada.getColor() == juego.getTurno()) && piezaSeleccionada.movimientosPosibles(x, y, juego.getTablero())  ) {
						juego.getTablero().moverPieza(piezaSeleccionada, x, y);
						piezaSeleccionada = null;
						cambioDeTurno(); 
						
						pintarTablero(juego);
						mostrarTablero();
						try {
							barrier.await();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (BrokenBarrierException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else {
						System.out.println("NO ES UN MOVIMIENTO VALIDO");
					}
					
					piezaSeleccionada = null;
				}
			}
		}
		

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
	
			

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	
	   public CyclicBarrier getBarrier() {
	        return barrier;
	    }
	
	public Juego getJuego() {
		// COMPROBACION DE SI HAY JAQUE MATE O NO
		return juego;
	}
	
	public void terminar() {
		// COMPROBACION DE SI HAY JAQUE MATE O NO
		this.termiando = true;
		
		//eliminamos que puedan pulsar
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				MouseListener[] listado  = casillas[j][i].getMouseListeners();
				for (MouseListener mouse : listado) {
					casillas[j][i].removeMouseListener(mouse);
				}
			}
		}
	}
	
	public Juego setJuego(Juego juego) {
		
		return this.juego = juego;
	}

	public boolean getTurnoBlancas() {
		return this.juego.isTurnoBlancas();
	}

	public boolean getTurnoNegras() {
		return this.juego.isTurnoNegras();
	}

	public JFrame getVentana() {
		return ventana;
	}

	public void mostrarTablero() {
		ventana.add(this);
		ventana.pack();
		ventana.setVisible(true);
		ventana.revalidate();
		ventana.repaint();
	}
	
	public void cerraTablero() {
		ventana.dispose();

	}

	public void pintarTablero(Juego juego) {
		// JButton[][] botones = new JButton[8][8];

		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {

				if (juego.getTablero().getPiezaPosicion(j, i) instanceof Peon) {
					if (juego.getTablero().getPiezaPosicion(j, i).getColor() == Constantes.COLOR_BLANCO) {
						casillas[j][i].setIcon(new ImageIcon("./src/img/peonBlanco.png"));
					} else {
						casillas[j][i].setIcon(new ImageIcon("./src/img/peonNegro.png"));
					}
				}
				if (juego.getTablero().getPiezaPosicion(j, i) instanceof Torre) {
					if (juego.getTablero().getPiezaPosicion(j, i).getColor() == Constantes.COLOR_BLANCO)
						casillas[j][i].setIcon(new ImageIcon("./src/img/torreBlanco.png"));
					else {
						casillas[j][i].setIcon(new ImageIcon("./src/img/torreNegro.png"));
					}
				}
				if (juego.getTablero().getPiezaPosicion(j, i) instanceof Alfil) {
					if (juego.getTablero().getPiezaPosicion(j, i).getColor() == Constantes.COLOR_BLANCO)
						casillas[j][i].setIcon(new ImageIcon("./src/img/alfilBlanco.png"));
					else {
						casillas[j][i].setIcon(new ImageIcon("./src/img/alfilNegro.png"));
					}
				}
				if (juego.getTablero().getPiezaPosicion(j, i) instanceof Caballo) {
					if (juego.getTablero().getPiezaPosicion(j, i).getColor() == Constantes.COLOR_BLANCO)
						casillas[j][i].setIcon(new ImageIcon("./src/img/caballoBlanco.png"));
					else {
						casillas[j][i].setIcon(new ImageIcon("./src/img/caballoNegro.png"));
					}
				}
				if (juego.getTablero().getPiezaPosicion(j, i) instanceof Rey) {
					if (juego.getTablero().getPiezaPosicion(j, i).getColor() == Constantes.COLOR_BLANCO)
						casillas[j][i].setIcon(new ImageIcon("./src/img/reyBlanco.png"));
					else {
						casillas[j][i].setIcon(new ImageIcon("./src/img/reyNegro.png"));
					}
				}
				if (juego.getTablero().getPiezaPosicion(j, i) instanceof Reina) {
					if (juego.getTablero().getPiezaPosicion(j, i).getColor() == Constantes.COLOR_BLANCO)
						casillas[j][i].setIcon(new ImageIcon("./src/img/reinaBlanco.png"));
					else {
						casillas[j][i].setIcon(new ImageIcon("./src/img/reinaNegro.png"));
					}
				}

				if (juego.getTablero().getPiezaPosicion(j, i) == null) {
					casillas[j][i].setIcon(null);
				}

				casillas[j][i].setPreferredSize(new Dimension(60, 60));
				if ((i + j + 1) % 2 == 0) {
					casillas[j][i].setBackground(new Color(184, 103, 38));
				} else {
					casillas[j][i].setBackground(new Color(246, 217, 183));
				}
				
				
				MouseListener[] listado  = casillas[j][i].getMouseListeners();
				for (MouseListener mouse : listado) {
					casillas[j][i].removeMouseListener(mouse);
				}
				
				casillas[j][i].addMouseListener(new pulsador(juego.getTablero().getPiezaPosicion(j, i), j, i));

				add(casillas[j][i]);
			}
		}
	}

}