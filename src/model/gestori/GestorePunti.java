package model.gestori;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.carta.Carta;
import model.giocatore.Squadra;

/**
 * Classe che si occupa di gestire i punti delle squadre nella partita
 *
 * @see controller.ControllerPartita
 * @see Squadra
 */
public class GestorePunti {

	/** Mappa che associa a ogni squadra i suoi punti */
	private final Map<Squadra, Integer> puntiXSquadra;

	/**
	 * Costruttore del gestore punti
	 * @param squadre squadre che giocano la partita
	 */
	public GestorePunti(List<Squadra> squadre) {
		this.puntiXSquadra = new HashMap<>();
		
		for(Squadra squadra : squadre) {
			this.puntiXSquadra.put(squadra, 0);
		}
	}

	/**
	 * Assegna i punti dati alla squadra data
	 * @param squadra squadra
	 * @param puntiVinti punti vinti dalla squadra
	 */
	public void assegnaPunti(Squadra squadra, int puntiVinti) {
		int puntiCorrenti = puntiXSquadra.get(squadra);
		puntiXSquadra.put(squadra, puntiCorrenti + puntiVinti);
	}

	/**
	 * Date le carte restituisce quando valgono
	 * @param carte carte da contare
	 * @return punteggio delle carte
	 */
	public int contaCartePrese(List<Carta> carte) {
		int puntiVinti = 0;
		//Contatore per le carte che valgono 1/3
		int c = 0;
		for (Carta carta: carte) {
			switch (carta.getValore()) {
				case ASSO:
					puntiVinti += 1;  
					break;
				case RE:
				case CAVALLO:
				case FANTE:
				case TRE:
				case DUE:
					c ++;
					break;
				default:
					//Messo per rende piu comprensibile che non si ottine nulla
					puntiVinti += 0; 
					break;
			}
		}
		//normallizzo il punteggio
		puntiVinti += c/3;
		
		return puntiVinti;
	}

	/**
	 * Restituisce una lista con le squadre che hanno vinto la partita
	 * @return lista squadre vincitrici
	 */
	public List<Squadra> vincitoriPartita() {
		List<Squadra> vincitori = new ArrayList<>();
		
		int puntiMax = puntiMassimiAttuali();
		
		
		//Questo mi serve per vedere se ci sono piu' vincitori
		for(Map.Entry<Squadra, Integer> entry : puntiXSquadra.entrySet()) {
			if(entry.getValue() == puntiMax) {
				vincitori.add(entry.getKey());
			}
		}
		
		return vincitori;
	}

	/**
	 * Trova la quantita' di punti maggiore raggiunta da una della squadre
	 * @return punti max attuali
	 */
	public int puntiMassimiAttuali() {
		return Collections.max(puntiXSquadra.values());
	}

	/**
	 * Restituisce i punti che la squadra data ha
	 * @param squadra di cui si vuole sapere i punti
	 * @return punti della squadra
	 */
	public int getPuntiSquadra(Squadra squadra) {
		return puntiXSquadra.get(squadra);
	}

}
