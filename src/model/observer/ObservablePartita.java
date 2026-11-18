package model.observer;

import java.util.List;
import java.util.Map;

import model.giocatore.ColoreSquadra;
import model.giocatore.Squadra;

/**
 * Classe astratratta che estende {@link Observable} che notifica eventi relativi alla match {@link ObserverPartita}
 *
 * @see Observable
 * @see ObserverPartita
 * @see model.Partita
 */
public abstract class ObservablePartita extends Observable<ObserverPartita>{

	/** Notifica gli observer che la partita e' iniziata */
	protected void notificaPartitaAvvia() {
		for(ObserverPartita observer : getObservers()) {
			observer.partitaAvvia();
		}
	}

	/**
	 * Notifica gli observer che le squadre sono state assegnate e come sono
	 * @param idGiocatore id giocatore
	 * @param colore colore della squadra in cui il giocatore sta
	 */
	protected void notificaSquadraAssegnata(Integer idGiocatore, ColoreSquadra colore) {
		for(ObserverPartita observer : getObservers()) {
			observer.squadraAssegnata(idGiocatore, colore);
		}
	}

	/** Notifica gli observer che un match e' iniziato */
	protected void notificaMatchIniziato() {
		for(ObserverPartita observer : getObservers()) {
			observer.matchIniziato();
		}
	}

	/** Notifica gli observer il risultato del match e che e' terminato */
	protected void notificaEsistoMatch(Integer idGiocatore, int puntiSquadra) {
		for(ObserverPartita observer : getObservers()) {
			observer.esitoMatch(idGiocatore, puntiSquadra);
		}
	}

	/**
	 * Notifica gli observer il risultato della partita e che e' terminata
	 * @param risultatoGiocatori Mappa id giocatore se ha vinto o perso
	 */
	protected void notificaPartitaFinita(Map<Integer, Boolean> risultatoGiocatori) {
		for(ObserverPartita observer : getObservers()) {
			observer.partitaFinita(risultatoGiocatori);
		}
	}

}
