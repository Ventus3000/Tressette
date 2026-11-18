package model.observer;

import model.carta.Carta;
import model.giocatore.ColoreSquadra;
import model.giocatore.Squadra;

import java.util.List;
import java.util.Map;

/**
 * Classe che osserva la partita {@link ObservablePartita} e il match{@link ObservableMatch}
 * E a sua volta si fa osservare per canalizzare in un unica interfaccia
 * <p>
 *     Estende {@link Observable}
 * </p>
 *
 * @see ObservablePartita
 * @see ObservableMatch
 * @see ObserverMediatore
 * @see Observable
 */
public class Mediatore extends Observable<ObserverMediatore> implements ObserverMediatore{
	/**
	 * Notifica di aggiornare la mano del giocatore indicato
	 * @param idGiocatore id del giocatore
	 * @param carte carte della mano del giocatore
	 */
	@Override
	public void aggiornaManoGiocatore(Integer idGiocatore, List<Carta> carte) {
		for(ObserverMediatore observer : getObservers()) {
			observer.aggiornaManoGiocatore(idGiocatore, carte);
		}
	}
	/**
	 * Notifica che e' il turno del giocatore indicato
	 * @param idGiocatore id del giocatore
	 * @param numeroTurno numero del turno
	 */
	@Override
	public void turnoDiGiocatore(Integer idGiocatore, int numeroTurno) {
		for(ObserverMediatore observer : getObservers()) {
			observer.turnoDiGiocatore(idGiocatore, numeroTurno);
		}
	}
	/** Notifica che una carta e' stata pescate*/
	@Override
	public void cartaPescata() {
		for(ObserverMediatore observer : getObservers()) {
			observer.cartaPescata();
		}
	}
	/** Notifica che una carta e' stata giocata*/
	@Override
	public void cartaGiocata() {
		for(ObserverMediatore observer : getObservers()) {
			observer.cartaGiocata();
		}
	}
	/**
	 * Notifica che il giocatore indicato non puo giocare quella carta
	 * @param idGiocatore id del giocatore
	 */
	@Override
	public void cartaNonValida(Integer idGiocatore) {
		for(ObserverMediatore observer : getObservers()) {
			observer.cartaNonValida(idGiocatore);
		}
	}
	/**
	 * Notifica di aggiornare le carte sul terreno con quelle fornite
	 * @param carte carte del terreno
	 */
	@Override
	public void aggiornaTerreno(List<Carta> carte) {
		for(ObserverMediatore observer : getObservers()) {
			observer.aggiornaTerreno(carte);
		}
	}
	/**
	 * Notifica chi e' il vincitore del round
	 * @param idGiocatore id giocatore vincitore
	 * @param numeroRound numero del round
	 */
	@Override
	public void vincitoreRound(Integer idGiocatore, int numeroRound) {
		for(ObserverMediatore observer : getObservers()) {
			observer.vincitoreRound(idGiocatore, numeroRound);
		}
	}
	/**
	 * Notifica di aggiornare le carte prese del giocatore dato
	 * @param idGiocatore id del giocatore
	 * @param cartePrese carte prese dal giocatore
	 */
	@Override
	public void aggiornaCartePrese(Integer idGiocatore, List<Carta> cartePrese) {
		for(ObserverMediatore observer : getObservers()) {
			observer.aggiornaCartePrese(idGiocatore, cartePrese);
		}
	}
	/** Notifica che le carte della mazzo sono finite*/
	@Override
	public void mazzoFinito() {
		for(ObserverMediatore observer : getObservers()) {
			observer.mazzoFinito();
		}
	}

	//Metodi Partita
	/** Notifica che la partita e' stata avviata */
	@Override
	public void partitaAvvia() {
		for(ObserverMediatore observer : getObservers()) {
			observer.partitaAvvia();
		}
	}
	/**
	 * Notifica la squadra assegnata al giocatore indicato
	 * @param idGiocatore id del giocatore
	 * @param colore colore della squadra
	 */
	@Override
	public void squadraAssegnata(Integer idGiocatore, ColoreSquadra colore) {
		for(ObserverMediatore observer : getObservers()) {
			observer.squadraAssegnata(idGiocatore, colore);
		}
	}
	/** Notifica che un match e' stato iniziato */
	@Override
	public void matchIniziato() {
		for(ObserverMediatore observer : getObservers()) {
			observer.matchIniziato();
		}
	}
	/**
	 * Notifica il l'esito del match per il giocatore indicato
	 * @param idGiocatore id del giocatore
	 * @param puntiSquadra punti della squadra del giocatore
	 */
	@Override
	public void esitoMatch(Integer idGiocatore, int puntiSquadra) {
		for(ObserverMediatore observer : getObservers()) {
			observer.esitoMatch(idGiocatore, puntiSquadra);
		}
	}
	/**
	 * Notifica che la partita e' finita
	 * @param risultatoGiocatori Mappa id giocatore se ha vinto o perso
	 */
	@Override
	public void partitaFinita(Map<Integer, Boolean> risultatoGiocatori) {
		for(ObserverMediatore observer : getObservers()) {
			observer.partitaFinita(risultatoGiocatori);
		}
	}
}
