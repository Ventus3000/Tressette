package model.giocatore;

import model.carta.Carta;

/**
 * Interfaccia che si occupa di definire come il giocatore sceglie la carta da giocare
 *
 * @see Giocatore
 * @see MetodoUmano
 * @see MetodoAI
 */
public interface MetodoDiGioco {
	/** Metodo che si occupa di scegliere la carta da giocare basandosi quelle informazioni della partita*/
	Carta scegliCarta(Giocatore giocatore, InfoMatch info);
}
