package view.carta;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serial;

import controller.ControllerPartita;
import model.carta.Carta;
import view.AudioManager;
import view.Impostazioni;

public class PanelCartaMano extends PanelCarta implements MouseListener{
	
	@Serial
    private static final long serialVersionUID = 1L;

	/** Variabile statica che rappresenta la carta scelta tra i PanelCartaMano */
	private static PanelCartaMano cartaScelta = null;
	/** Rappresenta se le carta puo' essere giocata */
	private static boolean isAbilitato = false;

	/** Controller della partita */
	private final ControllerPartita controller;

	/** id del giocatore di cui sono le carte*/
	private final Integer idGiocatore;

	/**
	 * Crea la carta e gli assegna un proprietari(id) e il controller della partita per giocarla
	 * @param idGiocatore id del giocatore
	 * @param carta carta rappresentata
	 * @param controller controller della partita
	 */
	public PanelCartaMano(Integer idGiocatore, Carta carta, ControllerPartita controller) {
		super(carta);
		
		this.controller = controller;
		this.idGiocatore = idGiocatore;
		
		addMouseListener(this);

	}

	/**
	 * Disegna la carta
	 * @param g the <code>Graphics</code> object to protect
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		
		//Per evitare che si creai una copia della carta creo un rect bianco sopra la carta e la ridisegno in alto di 10
		g2d.setColor(Impostazioni.COL_TERRENO);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		int offset = 0;
		if (cartaScelta == this) {
			offset = -10;
		}
		if(getImgCarta() != null) {
			g2d.drawImage(getImgCarta(), 0, offset, this);
		}
		g2d.dispose();
	}

	/** Abilita la possibilita di giocare la carta */
	public static void abilita() {
		isAbilitato = true;
	}

	/** Invia al controller la carta che si vuole giocare */
	private void giocaCarta() {
		if(isAbilitato) {
			controller.giocaCarta(idGiocatore , super.getCarta());
			isAbilitato = false;
		}
	}

	/**
	 * Gestisce la carta che viene clickata
	 * <p>
	 *     Un click alza la carta
	 * </p>
	 * <p>
	 *     Due click viene giocata se si puo'
	 * </p>
	 * @param e the event to be processed
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		AudioManager.getInstance().play("src/resources/audio/card-slide-8.wav");
		if(cartaScelta != this) {
			if(cartaScelta != null) {
				cartaScelta.repaint();
			}
			cartaScelta = this;
			repaint();
			revalidate();
		} else {
			giocaCarta();
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
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
	}

}
