package controller;


import model.Partita;
import model.carta.Carta;
import model.giocatore.Giocatore;
import model.giocatore.MetodoUmano;
import view.TavoloDaGioco;

import java.util.Map;

/**
 * Classe che controlla la partita
 * <p>
 *     Gestisce la creazione della comunicazione mvc tra view e model
 * </p>
 * <p>
 *     Si occupa di interagire con il model per la view
 * <p/>
 *
 * @see Partita
 * @see TavoloDaGioco
 * @see MainController
 * @see model.observer.Mediatore
 * @see model.gestori.GestorePunti
 */
public class ControllerPartita{

	/** Partita controllata */
	private final Partita partita;
	/** View controllata*/
	private final TavoloDaGioco view;
	/** Esisto della partita*/
	private Map<Integer, Boolean> risultatoGiocatori;

	/**
	 * Costruttore del controller partita
	 * @param partita partita da controllare
	 * @param view view da controllare
	 */
	public ControllerPartita(Partita partita, TavoloDaGioco view) {
		this.partita = partita;
		this.view = view;
	}

	/**
	 * Rende la view visibile e avvia la partita su un thread separato
	 */
	public void avviaPartita() {
		view.setVisible(true);
		
		new Thread(() -> {
			partita.avvia();
			risultatoGiocatori = partita.termina();
		}).start();

	}

	/**
	 * Comunica al model la carta scelta dal giocatore dato
	 * @param idGiocatore id giocatore della carta
	 * @param carta carta gioca
	 */
	public void giocaCarta(Integer idGiocatore, Carta carta) {
		Giocatore giocatore = partita.getGiocatore(idGiocatore);
		MetodoUmano metodo  = (MetodoUmano) giocatore.getMetodoDiGioco();
		//System.out.println("Carta giocata: " + carta);
		metodo.setCartaScelta(carta);
	}

	/**
	 * Comunica al main controller di chiudere la partita e chiude la view
	 */
	public void tornaAlMenu() {
		view.dispose();
		MainController.getInstance().partitaTerminata(risultatoGiocatori);
	}
	
}
