package model.giocatore;

import model.carta.Carta;

/**
 * Implementazione di {@link MetodoDiGioco} che rappresenta un giocatore umano
 *
 * @see MetodoDiGioco
 * @see Giocatore
 * @see controller.ControllerPartita
 */
public class MetodoUmano implements MetodoDiGioco{

	/** Carta scelta dal giocatore */
	private Carta cartaScelta = null;
	/** Oggetto per bloccare il thread della partita in attesa che la carta venga scelta */
	private final Object lock = new Object();

	/**
	 * Questo metodo si occupa di bloccare il thread della partita
	 * fino a quando una carta non viene scelta {@link #setCartaScelta(Carta)}
	 * @param giocatore Giocatore che sceglie la carta
	 * @param info informazioni sullo stato corrente della partita
	 * @return carta scelta
	 */
	@Override
	public Carta scegliCarta(Giocatore giocatore, InfoMatch info) {
		synchronized (lock) {
			while (cartaScelta == null) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Carta carta = cartaScelta;
			cartaScelta = null;
			return carta;
		}
	}

	/** Imposta la carta scelta e sblocca il thread della partita */
	public void setCartaScelta(Carta carta) {
		synchronized (lock) {
			this.cartaScelta = carta;
			//Sblocca i thread
			lock.notifyAll();
		}
	}

}
