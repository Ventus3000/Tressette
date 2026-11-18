package model.giocatore;

import java.util.List;

import model.carta.Carta;
import model.carta.SemeCarta;

/**
 * Si occupa di raccogliere le informazioni del match
 */
public class InfoMatch {
	/**
	 * Carte giocate durante il round
	 */
	private final List<Carta> carteGiocate;

	/**
	 * Costruttore informazioni match
	 * @param carteGiocate carte giocate nel round
	 */
	public InfoMatch(List<Carta> carteGiocate) {
		this.carteGiocate = carteGiocate;
	}

	/**
	 * Restituisce il seme della prima carta
	 * @return seme carta
	 */
	public SemeCarta getSemeRichiesto() {
		if(carteGiocate.isEmpty()) {
			return null;
		}
		return carteGiocate.getFirst().getSeme();
	}

	public List<Carta> getCarteGiocate() {
		return carteGiocate;
	}

}
