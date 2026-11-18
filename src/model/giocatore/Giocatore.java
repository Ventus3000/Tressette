package model.giocatore;

import java.util.ArrayList;
import java.util.List;

import model.carta.Carta;

/**
 * Rapressenta un giocatore all'interno di una partita
 *
 * @see Squadra
 * @see MetodoDiGioco
 */
public class Giocatore {

	/** Identificativo univoco del giocatore */
	private final int id;
	/** Lista della carte attualmente in mano al giocatore */
	private final List<Carta> mano;

	/** Squadra a cui appartiene il giocatore */
	private Squadra squadra;

	/** Come il giocatore sceglie la carta da giocare */
	private final MetodoDiGioco metodoDiGioco;

	/** Lista di carte prese durante un match */
	private final List<Carta> cartePrese;

	/**
	 * Costruttore giocatore
	 *
	 * @param id Identificativo univoco del giocatore
	 * @param metodoDiGioco Come il giocatore sceglie la carta da giocare
	 */
	public Giocatore(Integer id, MetodoDiGioco metodoDiGioco) {
		this.id = id;
		this.mano = new ArrayList<>();
		
		this.metodoDiGioco = metodoDiGioco;
		
        this.cartePrese = new ArrayList<>(); 
	}

	/**
	 * Assegna una squadra al giocatore
	 *
	 * @param squadra Squadra in cui il giocatore si unisce
	 */
	public void entraInSquadra(Squadra squadra) {
		//Cosi posso assegnarla solo una volta
		if(squadra != null) {
			this.squadra = squadra;
		}
	}

	/** Aggiunge una carta alla mano del giocatore */
	public void pescaCarta(Carta carta) {
		mano.add(carta);
    }

	/**
	 * Propone la carta da giocare tramite il suo metodo di gioco
	 * @param infoMatch informazioni sullo stato corrente del match
	 * @return Carta scelta
	 * @see MetodoDiGioco
	 */
	public Carta scegliCarta(InfoMatch infoMatch ) {
		return metodoDiGioco.scegliCarta(this, infoMatch);
	}

	/**
	 * Rimuove la carta giocata dalla mano del giocatore
	 * @param cartaScelta carta giocata
	 */
	public void giocaCarta(Carta cartaScelta) {
        mano.remove(cartaScelta);
	}

	/**
	 * Verifica se il giocatore ha una specifica carta in mano
	 * @param carta carta da controllare
	 * @return true | false
	 */
	public boolean haCarta(Carta carta) {
		for (Carta c: mano) {
			if (c.equals(carta)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Verifica se il giocatore ha carte in mano o no
	 * @return true | false
	 */
	public boolean haCarte() {
		return !mano.isEmpty();
    }

	/**
	 * Aggiunge carte alle carte prese
	 * @param cartePrese carte prese
	 */
	public void prendiCarte (List<Carta> cartePrese) {
		this.cartePrese.addAll(cartePrese);
	}

	/** Svuota la mano e le carte prese del giocatore */
	public void partitaFinita() {
		mano.clear();
		cartePrese.clear();
	}


	/**
	 * Restituisce l'Id del giocatore
	 * @return id del giocatore
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Restituisce la squadra del giocatore
	 * @return squadra del giocatore
	 */
	public Squadra getSquadra() {
		return squadra;
	}

	/**
	 * Restituisce le carte mano del giocatore
	 * @return copia carte mano giocatore
	 */
	public List<Carta> getMano() {
		return new ArrayList<>(mano);
	}

	/**
	 * Restituisce le carte prese del giocatore
	 * @return copia carte prese giocatore
	 */
	public List<Carta> getCartePrese() {
		return new ArrayList<>(cartePrese);
	}

	/**
	 * Restituisce il metodo di gioco del giocatore
	 * @return metodi di gioco del giocatore
	 */
	public MetodoDiGioco getMetodoDiGioco() {
		return metodoDiGioco;
	}

}
