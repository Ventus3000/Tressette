package model.giocatore;

import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta una squadra al interno della partita
 *
 * @see ColoreSquadra
 * @see Giocatore
 */
public class Squadra {

	/** Identificativo della squadra */
	private final ColoreSquadra colore;
	/** Lista dei giocatori nella squadra */
	private final List<Giocatore> membri;

	/** Costruttore della squadra */
	public Squadra(ColoreSquadra colore) {
		this.colore = colore;
		this.membri = new ArrayList<>();
	}

	/** Aggiunge un giocatore all'interno della squadra */
	public void aggiungiMembro(Giocatore giocatore) {
		membri.add(giocatore);
		giocatore.entraInSquadra(this);
	}

	public ColoreSquadra getColore() {
		return colore;
	}

	public List<Giocatore> getMembri() {
		return membri;
	}
	
	@Override
	public String toString() {
		return "SQUADRA " + colore;
	}
	
}
