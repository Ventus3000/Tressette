package model.observer;

import java.util.List;
import java.util.Map;

import model.giocatore.ColoreSquadra;
import model.giocatore.Squadra;

/**
 * Interfaccia observer per ricevere notifiche da gli eventi della partita
 *
 * @see ObserverPartita
 * @see model.Partita
 */
public interface ObserverPartita{
	/** Notifica che la partita e' stata avviata */
	void partitaAvvia();
	/**
	 * Notifica la squadra assegnata al giocatore indicato
	 * @param idGiocatore id del giocatore
	 * @param colore colore della squadra
	 */
	void squadraAssegnata(Integer idGiocatore, ColoreSquadra colore);
	/** Notifica che un match e' stato iniziato */
	void matchIniziato();
	/**
	 * Notifica il l'esito del match per il giocatore indicato
	 * @param idGiocatore id del giocatore
	 * @param puntiSquadra punti della squadra del giocatore
	 */
	void esitoMatch(Integer idGiocatore, int puntiSquadra);
	/**
	 * Notifica che la partita e' finita
	 * @param risultatoGiocatori Mappa id giocatore se ha vinto o perso
	 */
	void partitaFinita(Map<Integer, Boolean> risultatoGiocatori);
}
