package Juego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Fichas.*;
import setup.Constantes;

public class Interfaz extends JPanel{
	private Juego juego;
	private JButton[][] casillas;
	private JFrame ventana=new JFrame("Distribuidos");
	
	public Interfaz(Juego juego) {
		this.juego=juego;
		
		casillas = new JButton[8][8];
		
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				casillas[j][i] = new JButton();

				if(juego.getTablero().getPiezaPosicion(j, i) instanceof Peon) {
					if(juego.getTablero().getPiezaPosicion(j, i).getColor()== Constantes.COLOR_BLANCO) {
						casillas[j][i].setIcon(new ImageIcon("./src/img/peonBlanco.png"));
					}else {
						casillas[j][i].setIcon(new ImageIcon("./src/img/peonNegro.png"));
					}
				}
				if(juego.getTablero().getPiezaPosicion(j, i) instanceof Torre) {
					//botones[i][j].setText("T");
					if(juego.getTablero().getPiezaPosicion(j, i).getColor() ==  Constantes.COLOR_BLANCO)
						casillas[j][i].setIcon(new ImageIcon("./src/img/torreBlanco.png"));
					else {
						casillas[j][i].setIcon(new ImageIcon("./src/img/torreNegro.png"));
					}
				}
				if(juego.getTablero().getPiezaPosicion(j, i) instanceof Alfil) {
					if(juego.getTablero().getPiezaPosicion(j, i).getColor()==Constantes.COLOR_BLANCO)
						casillas[j][i].setIcon(new ImageIcon("./src/img/alfilBlanco.png"));
					else {
						casillas[j][i].setIcon(new ImageIcon("./src/img/alfilNegro.png"));
					}
				}
				if(juego.getTablero().getPiezaPosicion(j, i) instanceof Caballo) {
					if(juego.getTablero().getPiezaPosicion(j, i).getColor()==Constantes.COLOR_BLANCO)
						casillas[j][i].setIcon(new ImageIcon("./src/img/caballoBlanco.png"));
					else {
						casillas[j][i].setIcon(new ImageIcon("./src/img/caballoNegro.png"));
					}
				}
				if(juego.getTablero().getPiezaPosicion(j, i) instanceof Rey) {
					if(juego.getTablero().getPiezaPosicion(j, i).getColor()==Constantes.COLOR_BLANCO)
						casillas[j][i].setIcon(new ImageIcon("./src/img/reyBlanco.png"));
					else {
						casillas[j][i].setIcon(new ImageIcon("./src/img/reyNegro.png"));
					}
				}
				if(juego.getTablero().getPiezaPosicion(j, i) instanceof Reina) {
					if(juego.getTablero().getPiezaPosicion(j, i).getColor()==Constantes.COLOR_BLANCO)
						casillas[j][i].setIcon(new ImageIcon("./src/img/reinaBlanco.png"));
					else {
						casillas[j][i].setIcon(new ImageIcon("./src/img/reinaNegro.png"));
					}
				}
				
				
				casillas[j][i].setPreferredSize(new Dimension(60, 60));
				if ((i + j + 1) % 2 == 0) {
					casillas[j][i].setBackground(new Color(184, 103, 38));
				}else {
					casillas[j][i].setBackground(new Color(246, 217, 183));
				}
				
				casillas[j][i].addMouseListener(new pulsador(juego.getTablero().getPiezaPosicion(j, i)));
					
					
			
				add(casillas[j][i]);
			}
	}
		setLayout(new GridLayout(8, 8));
	}
	
	private class pulsador implements MouseListener{

		public pulsador(Pieza piezaPosicion) {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.print("hola");
			
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
	
	public JFrame getVentana() {
		return ventana;
	}
	
	public void mostrar() {
		
		ventana.add(this);
		ventana.pack();
		ventana.setVisible(true);
		ventana.repaint();
	}
	
		
	

}
