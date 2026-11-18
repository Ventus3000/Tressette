package model.carta;

import java.util.Collections;
import java.util.Stack;

/**
 * Rappresenta un mazzo di carte
 * <p>
 *     Il mazzo contine tutte le combinazioni seme valore delle carte italiane.
 *     Il mazzo e' generato automaticamente.
 * </p>
 *
 * @see Carta
 * @see SemeCarta
 * @see ValoreCarta
 */
public class Mazzo {

	/** Stack che contiene tutte le carte del mazzo */
	private final Stack<Carta> carte;

	/**
	 * Costruice il mazzo di carte.
	 * <p>
	 *     Il mazzo viene automaticamente creato.
	 * </p>
	 */
    public Mazzo() {
        carte = new Stack<>();
        genera();
    }

	/** Genera le carte della mazzo con tutte le combinazioni seme valore*/
	private void genera() {
		for(SemeCarta seme : SemeCarta.values()) {
			for(ValoreCarta valore : ValoreCarta.values()) {
				carte.add(new Carta(seme, valore));
			}
		}
		
	}

	/** Mescola le carte del mazzo*/
	public void mescola() {
		Collections.shuffle(carte);
	}

	/**
	 * Pesca la carta dalla cima del mazzo.
	 * La carta viene rimossa dal mazzo.
	 *
	 * @return carta in cima al mazzo
	 */
	public Carta pesca() {
		return carte.pop();
	}

	/**
	 * Controlla se ci sono carte rimaste nel mazzo.
	 *
	 * @return true o false
	 */
	public	boolean isEmpty() {
		return carte.isEmpty();
	}

	/**
	 * Ritorna il numero di carte presenti nel mazzo
	 * @return numero di carte
	 */
	public int size() {
		return carte.size();
	}
	
}
