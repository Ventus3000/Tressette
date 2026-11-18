package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.gestori.GestorePunti;
import model.giocatore.ColoreSquadra;
import model.giocatore.Giocatore;
import model.giocatore.Squadra;

import model.observer.Mediatore;
import model.observer.ObservablePartita;

/**
 * Classe che rapresenta una partita
 * <p>
 *     Si occupa di gestrire il fusso di una partita
 * </p>
 * <p>
 *     Estende {@link ObservablePartita}
 * </p>
 *
 * @see Match
 * @see controller.ControllerPartita
 * @see ObservablePartita
 * @see Mediatore
 * @see model.observer.ObserverMediatore
 */
public class Partita extends ObservablePartita{
	/** Lista di giocatori */
	private final List<Giocatore> giocatori;
	/** Lista delle squadre della partita */
	private final List<Squadra> squadre;
	/** Quantita di punti necessaria per la vittori */
	private final int puntiXVittoria;

	/** Si occupa di gestire i punti associati a ogni squadra */
	private final GestorePunti gestorePunti;

	/** Si occupa osservare partita e match */
	private final Mediatore mediatore;

	/**
	 * Inizializza la partita
	 * <p>
	 *     Inizializza le squadre e le assegna
	 * </p>
	 * <p>
	 *     Assegna il suo observer
	 * </p>
	 * @param giocatori Lista giocatori nella partita
	 * @param numeroSquadre Numero di squadre nella partita
	 * @param puntiXVittoria Punti neccesari per vincere
	 * @param mediatore Observer della partita e del match
	 */
	public Partita(List<Giocatore> giocatori, int numeroSquadre, int puntiXVittoria, Mediatore mediatore) {
		this.giocatori = giocatori;
		this.squadre = new ArrayList<>();
		this.mediatore = mediatore;
		
		aggiungiObserver(mediatore);
		
		inizializzaSquadre(numeroSquadre);
		assegnaSquadre();
		for(Giocatore giocatore : giocatori) {
			notificaSquadraAssegnata(giocatore.getId(), giocatore.getSquadra().getColore());
		}
		
		this.puntiXVittoria = puntiXVittoria;
		this.gestorePunti = new GestorePunti(squadre);
	}
	
	private void inizializzaSquadre(int numeroSquadre) {
		List<ColoreSquadra> colori = List.of(ColoreSquadra.values());
		for(int i = 0; i < numeroSquadre; i++) {
			squadre.add(new Squadra(colori.get(i)));
		}
	}
	
	private void assegnaSquadre() {
		for(int i = 0; i < giocatori.size(); i++) {
			Squadra squadraCorrente = squadre.get(i % squadre.size());
			squadraCorrente.aggiungiMembro(giocatori.get(i));
		}
	}

	/**
	 * Avvia la partita iniziando il loop per il numero di match da giocare
	 */
	public void avvia() {
		notificaPartitaAvvia();
		
		while(gestorePunti.puntiMassimiAttuali() < puntiXVittoria) {
			Match match = new Match(giocatori);
			notificaMatchIniziato();
			
			match.aggiungiObserver(mediatore);
			
			match.inizia();
			
			//Qua mi restituisce gli esisti della partita
			risultatoMatch();
			
			match.termina();
			match.rimuoviObserver(mediatore);
		}
	}
	
	private void risultatoMatch() {
		for(Squadra squadra : squadre) {
			int puntiSquadra = 0;
			for(Giocatore giocatore : squadra.getMembri()) {
				puntiSquadra += gestorePunti.contaCartePrese(giocatore.getCartePrese());
			}
			gestorePunti.assegnaPunti(squadra, puntiSquadra);
			
			for(Giocatore giocatore : squadra.getMembri()) {
				notificaEsistoMatch(giocatore.getId(), gestorePunti.getPuntiSquadra(squadra));
			}
		}
	}

	/**
	 * Termina la partita restiturendo una mappa id giocatore e se ha vinto o no
	 * @return mappa giocatore se ha vinto o perso
	 */
	public Map<Integer, Boolean> termina() {
		List<Squadra> squadreVincentirici = gestorePunti.vincitoriPartita();
		Map<Integer, Boolean> risultatoGiocatori = new HashMap<>();
		for(Giocatore giocatore : giocatori) {
			risultatoGiocatori.put(
					giocatore.getId(),
					squadreVincentirici.contains(giocatore.getSquadra())
			);
		}

		notificaPartitaFinita(risultatoGiocatori);
		return risultatoGiocatori;
	}

	/**
	 * Restituisce il giocatore associato al id dato
	 * @param idGiocatore id giocatore richiesto
	 * @return giocatore richiesto
	 */
	public Giocatore getGiocatore(Integer idGiocatore) {
		Giocatore giocatorePreso = null;
		
		for(Giocatore giocatore : giocatori) {
			if(giocatore.getId().equals(idGiocatore)) {
				giocatorePreso = giocatore;
			}
		}
		
		return giocatorePreso;
	}
}