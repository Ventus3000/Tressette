package model.gestori;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.giocatore.Giocatore;
import model.carta.Carta;
import model.carta.SemeCarta;

/**
 * Classe che si occupa di registrare le carte giocate da ogni giocatore in un round
 * e determinarne il vincitore
 *
 * @see model.Match
 * @see Giocatore
 * @see Carta
 */
public class GestoreRounds {

	/**
	 * Mappa che associa a ogni giocatore la carta che ha giocato
	 */
	//Giocate e' l'equivalente del terreno
	private final Map<Giocatore, Carta> giocate;
	/** Numero di giocatori nel match */
	private final int numeroGiocatori;
	/** Contatore del numero di round giocati */
	private int counterRounds = 0;

	/**
	 * Costruttore del gestore rounds
	 * @param numeroGiocatori numero di giocatori in partita
	 */
	public GestoreRounds(int numeroGiocatori) {
		//Linked per mentenere l'ordine di inserimento
		this.giocate = new LinkedHashMap<>();
		this.numeroGiocatori = numeroGiocatori;
	}

	/**
	 * Aggiunge una giocata alla mappa delle giocate
	 * @param giocatore giocatore
	 * @param carta carta giocata
	 */
	public void aggiungiGiocata(Giocatore giocatore, Carta carta) {
		if(giocate.size() <= numeroGiocatori) {
			giocate.put(giocatore, carta);
		}
	}

	/**
	 * Comunica se il round e' completo controllando se giocate == numero di giocatori
	 * @return true || false
	 */
	public boolean isRoundCompleto() {
		return giocate.size() == numeroGiocatori;
	}

	/**
	 * Termina il round e restituisce il vincitore di esso
	 * @return vincitore round
	 */
	public Giocatore fineRound() {
		Giocatore vincitore = vincitoreRound();
		
		List<Carta> carteVinte = new ArrayList<>(giocate.values());
		
		vincitore.prendiCarte(carteVinte);
		
		//Pulisco la mappa
		giocate.clear();
		
		//Il vincitore mi server per notificarlo
		return vincitore;
	}
	
	private Giocatore vincitoreRound() {
		Giocatore vincitore = null;
	
		int gradoMax = -1;
		
		SemeCarta palo = giocate.entrySet().iterator().next().getValue().getSeme();
		
		for(Map.Entry<Giocatore, Carta> entry : giocate.entrySet()) {
			Carta carta = entry.getValue();
			int gradoCarta = carta.getValore().getGrado();
			if(gradoCarta > gradoMax && carta.getSeme() == palo) {
				vincitore = entry.getKey();
				gradoMax = gradoCarta;
			}
		}
		
		counterRounds += 1;
		
		return vincitore;
	}

	/**
	 * Restituisce una copia della mappa delle giocate
	 * @return mappa giocate
	 */
	public Map<Giocatore, Carta> getGiocate() {
		return Map.copyOf(giocate);
	}

	/**
	 * Restituisce una lista copia solo delle carte giocate
	 * @return carte giocate
	 */
	public List<Carta> getCarteGiocate() {
		return List.copyOf(new ArrayList<>(giocate.values()));
	}

	/**
	 * Restituisce il numero di rounds giocati
	 * @return numero rounds
	 */
	public int getCounterRounds() {
		return counterRounds;
	}

}
