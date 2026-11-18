package model.observer;

import java.util.List;

import model.carta.Carta;

/**
 * Interfaccia observer per ricevere notifiche da gli eventi di un match
 *
 * @see ObserverMatch
 * @see model.Match
 */
public interface ObserverMatch {
	/**
	 * Notifica di aggiornare la mano del giocatore indicato
	 * @param idGiocatore id del giocatore
	 * @param carte carte della mano del giocatore
	 */
	void aggiornaManoGiocatore(Integer idGiocatore, List<Carta> carte);
	/**
	 * Notifica che e' il turno del giocatore indicato
	 * @param idGiocatore id del giocatore
	 * @param numeroTurno numero del turno
	 */
	void turnoDiGiocatore(Integer idGiocatore, int numeroTurno);
	/** Notifica che una carta e' stata pescate*/
	void cartaPescata();
	/** Notifica che una carta e' stata giocata*/
	void cartaGiocata();
	/**
	 * Notifica che il giocatore indicato non puo giocare quella carta
	 * @param idGiocatore id del giocatore
	 */
	void cartaNonValida(Integer idGiocatore);
	/**
	 * Notifica di aggiornare le carte sul terreno con quelle fornite
	 * @param carte carte del terreno
	 */
	void aggiornaTerreno(List<Carta> carte);
	/**
	 * Notifica chi e' il vincitore del round
	 * @param idGiocatore id giocatore vincitore
	 * @param numeroRound numero del round
	 */
	void vincitoreRound(Integer idGiocatore, int numeroRound);
	/**
	 * Notifica di aggiornare le carte prese del giocatore dato
	 * @param idGiocatore id del giocatore
	 * @param cartePrese carte prese dal giocatore
	 */
	void aggiornaCartePrese(Integer idGiocatore, List<Carta> cartePrese);
	/** Notifica che le carte della mazzo sono finite*/
	void mazzoFinito();
}