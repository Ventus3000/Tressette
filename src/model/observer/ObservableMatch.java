package model.observer;

import java.util.List;

import model.carta.Carta;

/**
 * Classe astratratta che estende {@link Observable} che notifica eventi relativi alla match {@link ObserverMatch}
 *
 * @see Observable
 * @see ObserverMatch
 * @see model.Match
 */
public abstract class ObservableMatch extends Observable<ObserverMatch>{

	/**
	 * Notifica gli observer di aggiornare la mano del giocatore dato
	 * @param idGiocatore id del giocatore la cui mano va aggiornata
	 * @param carte carte della mano
	 */
	protected void notificaAggiornaManoGiocatore(Integer idGiocatore, List<Carta> carte) {
		for(ObserverMatch observer : getObservers()) {
			observer.aggiornaManoGiocatore(idGiocatore, carte);
		}
	}

	/**
	 * Notifica gli observer di chi e' il turno
	 * @param idGiocatore id del giocatore di cui e' il turno
	 * @param numeroTurno contatore dei turni
	 */
	protected void notificaTurnoDiGiocatore(Integer idGiocatore, int numeroTurno) {
		for(ObserverMatch observer : getObservers()) {
			observer.turnoDiGiocatore(idGiocatore, numeroTurno);
		}
	}

	/** Notifica gli observer che una carta e' stata pescata*/
	protected void notificaCartaPescata() {
		for(ObserverMatch observer : getObservers()) {
			observer.cartaPescata();
		}
	}

	/** Notifica gli observer che una carta e' stata giocata*/
	protected void notificaCartaGiocata() {
		for(ObserverMatch observer : getObservers()) {
			observer.cartaGiocata();
		}
	}

	/** Notifica gli observer di aggiornare le carte sul terreno*/
	protected void notificaAggiornaTerreno(List<Carta> carte) {
		for(ObserverMatch observer : getObservers()) {
			observer.aggiornaTerreno(carte);
		}
	}

	/**
	 * Notifica gli observer chi ha vinto il round
	 * @param idGiocatore id del vincitore
	 * @param numeroRound counter dei round
	 */
	protected void notificaVincitoreRound(Integer idGiocatore, int numeroRound) {
		for(ObserverMatch observer : getObservers()) {
			observer.vincitoreRound(idGiocatore, numeroRound);
		}
	}

	/**
	 * Notifica gli observer di aggiornare le carte prese dal giocatore
	 * @param idGiocatore id del giocatore
	 * @param cartePrese carte prese dal giocatore
	 */
	protected void notificaAggiornaCartePrese(Integer idGiocatore, List<Carta> cartePrese) {
		for(ObserverMatch observer : getObservers()) {
			observer.aggiornaCartePrese(idGiocatore, cartePrese);
		}
	}

	/**  Notifica gli observer che le carte del mazzo sono finite */
	protected void notificaMazzoFinito() {
		for(ObserverMatch observer : getObservers()) {
			observer.mazzoFinito();
		}
	}

	/**
	 * Notifica il giocatore dato che la carta scelta non e' valida
	 * @param idGiocatore id giocatore
	 */
	protected void notificaCartaNonValida(Integer idGiocatore) {
		for(ObserverMatch observer : getObservers()) {
			observer.cartaNonValida(idGiocatore);
		}
	}
	
}
