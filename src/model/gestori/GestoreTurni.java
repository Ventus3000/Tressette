package model.gestori;

import java.util.List;

import model.giocatore.Giocatore;

/**
 * Classe che si occupa di gestire l'ordine dei giocatori al interno del match
 *
 * @see model.Match
 */
public class GestoreTurni {
	/** Lista giocatori del match */
	private final List<Giocatore> giocatori;
	/** Indice del giocatore corrente riferimento alla lista giocatori */
	private int indiceGiocatore;

	/** Counter dei turni giocati */
	private int counterTurni = 1;

	/**
	 * Costruttore del gestore turni carica i giocatori ed assegna il primo giocatore
	 * @param giocatori Lista giocatori
	 * @param primoGiocatore Primo giocatore
	 */
	public GestoreTurni(List<Giocatore> giocatori, Giocatore primoGiocatore) {
		this.giocatori = giocatori;
		this.indiceGiocatore = giocatori.indexOf(primoGiocatore);	
	}

	/**
	 * Restituisce il giocatore corrente
	 * @return giocatore corrente
	 */
	public Giocatore getGiocatoreCorrente() {
		return giocatori.get(indiceGiocatore);
	}

	/** Avanza al giocatore successivo ed incrementa il counter */
	public void prossimoTurno() {
		indiceGiocatore = (indiceGiocatore - 1 + giocatori.size()) % giocatori.size();
		counterTurni += 1;
	}

	/**
	 * Setta il prossimo giocatore
	 * @param giocatore Prossimo giocatore
	 */
	public void setProssimoGiocatore(Giocatore giocatore) {
		this.indiceGiocatore = giocatori.indexOf(giocatore);
		counterTurni += 1;
	}

	/** Restituisce il numero di turni giocati */
	public int getCounterTurni() {
		return counterTurni;
	}
	
}
