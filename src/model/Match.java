package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.gestori.RegoleDiGioco;
import model.giocatore.InfoMatch;
import model.observer.*;

import model.carta.Carta;
import model.carta.Mazzo;
import model.carta.SemeCarta;
import model.carta.ValoreCarta;
import model.gestori.GestoreRounds;
import model.gestori.GestoreTurni;
import model.giocatore.Giocatore;

/**
 * Classe che rappresenta un match
 * <p>
 *     Gestisce il flusso di un match
 * </p>
 * <p>
 *     Estente {@link ObservableMatch}
 * </p>
 *
 * @see Giocatore
 * @see Mazzo
 * @see GestoreRounds
 * @see GestoreTurni
 * @see RegoleDiGioco
 * @see Partita
 * @see ObservableMatch
 * @see ObserverMatch
 */
public class Match extends ObservableMatch{
	/** Contatore statico del numero di match creati */
	private static int counterMatch = 0;
	/** Lista di giocatori */
	private final List<Giocatore> giocatori;
	/** Mazzo di carte utilizzato nella partita */
	private final Mazzo mazzo;

	/** Gestore rounds si occupa di gestire le carte giocate(terreno) dai giocatori
	 * e di gestire la fine di un round */
	private GestoreRounds gestoreRounds;
	//Gestore turni si occupa del ordine dei giocatori
	/** Gestore dei turni decide l'ordine di giocata dei giocatori */
	private GestoreTurni gestoreTurni;

	/**
	 * Costruttore match
	 * @param giocatori giocatori del match
	 */
	public Match(List<Giocatore> giocatori) {
		this.giocatori = giocatori;
		this.mazzo = new Mazzo();
		
		counterMatch++;
	}

	/**
	 * Inizia il match
	 * Ordine degli eventi:
	 * <ol>
	 *     <li>Mescola il mazzo</li>
	 *     <li>Distribuisce le carte</li>
	 *     <li>Inizializza i gestori</li>
	 *     <li>Determina il primo giocatore</li>
	 *     <li>Avvia il ciclo dei turni</li>
	 * </ol>
	 */
	public void inizia() {
		//Sezione carte
		mazzo.mescola();
		distribuisciCarte();
		
		//Aggiorno la mano di ogni giocatore
		for(Giocatore giocatore : giocatori) {
			notificaAggiornaManoGiocatore(giocatore.getId(), giocatore.getMano());
		}
		
		//Sezione gestori
		this.gestoreRounds = new GestoreRounds(giocatori.size());
		
		Giocatore primoGiocatore = determinaPrimoGiocatore();
		this.gestoreTurni = new GestoreTurni(giocatori, primoGiocatore);
		
		giocaTurni();
	}
	
	private void distribuisciCarte() {
		int carteXGiocatore = 10;
		
		//Distribuisco le carte
		//Ad ogni giocatore do n carte ricavate dalla formula sopra
		for (Giocatore giocatore: giocatori) {
			for (int i = 0; i < carteXGiocatore; i++) {
				giocatore.pescaCarta(mazzo.pesca());
			}
		}
    }
	
	private Giocatore determinaPrimoGiocatore() {
		if(counterMatch == 1) {
			for (Giocatore giocatore: giocatori) {
				if (giocatore.haCarta(new Carta(SemeCarta.DENARI, ValoreCarta.QUATTRO))) {
					return giocatore;
				}
			}
		}
		//nel caso non ci sia il 4 di denari per scartato nel caso ci sia in una modalita
		//dove tutte le carte non sono distibuite esempio 1v1v1
		Random random = new Random();
		//evento random
		return giocatori.get(random.nextInt(giocatori.size()));
	}
	
	private void giocaTurni() {
		while(giocatori.stream().anyMatch(Giocatore::haCarte)) {

			//Controlla se bisogna pescare una carta
			if(!mazzo.isEmpty()) {
				if(gestoreRounds.getCounterRounds() != 1 &&  mazzo.size() >= giocatori.size()) {
					for (Giocatore giocatore: giocatori) {
						giocatore.pescaCarta(mazzo.pesca());
						notificaCartaPescata();
						notificaAggiornaManoGiocatore(giocatore.getId(), giocatore.getMano());
					}
				}
			} else {
				notificaMazzoFinito();
			}

			Giocatore giocatoreCorrente = gestoreTurni.getGiocatoreCorrente();
			notificaTurnoDiGiocatore(giocatoreCorrente.getId(), gestoreTurni.getCounterTurni());

			giocataCarta(giocatoreCorrente);
			notificaCartaGiocata();

			notificaAggiornaTerreno(gestoreRounds.getCarteGiocate());
			
			if(gestoreRounds.isRoundCompleto()) {
				Giocatore vincitore = gestoreRounds.fineRound();
				notificaVincitoreRound(vincitore.getId(), gestoreRounds.getCounterRounds());
				notificaAggiornaCartePrese(vincitore.getId(), vincitore.getCartePrese());
				
				//Per permettre hai giocatori di vedere l'ultima carta
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				notificaAggiornaTerreno(new ArrayList<>());
				
				gestoreTurni.setProssimoGiocatore(vincitore);
			} else {
				gestoreTurni.prossimoTurno();
			}
		}
	}

	private void giocataCarta(Giocatore giocatoreCorrente) {
		InfoMatch infoMatch = dammiInfo();
		while(true) {
			//Prima vedo la carta che viene suggerita
			Carta cartaScelta = giocatoreCorrente.scegliCarta(infoMatch);

			//Controllo se e' valida
			if(!RegoleDiGioco.isCartaGiocabile(
					cartaScelta,
					giocatoreCorrente.getMano(),
					gestoreRounds.getCarteGiocate()
			)) {
				notificaCartaNonValida(giocatoreCorrente.getId());
			} else {
				//Se si la faccio giocare
				giocatoreCorrente.giocaCarta(cartaScelta);
				notificaAggiornaManoGiocatore(giocatoreCorrente.getId(), giocatoreCorrente.getMano());
				gestoreRounds.aggiungiGiocata(giocatoreCorrente, cartaScelta);
				break;
			}
			
		}
	}
	
	private InfoMatch dammiInfo() {
		return new InfoMatch(gestoreRounds.getCarteGiocate());
	}

	/** Termina il match */
	public void termina() {	
		for (Giocatore giocatore: giocatori) {
			giocatore.partitaFinita();
			notificaAggiornaManoGiocatore(giocatore.getId(), new ArrayList<>());
		}
		notificaAggiornaTerreno(new ArrayList<>());
	}

	public int getCounterMatch() {
		return counterMatch;
	}
	
}
