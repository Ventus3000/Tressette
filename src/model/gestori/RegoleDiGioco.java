package model.gestori;

import java.util.List;

import model.carta.Carta;
import model.carta.SemeCarta;

/**
 * Classe che valida se vengono rispettare le regole di gioco
 */
public class RegoleDiGioco {

	/**
	 * Verifica se una carta e' giocabile o no
	 * @param carta carta proposta
	 * @param manoGiocatore carte della mano del giocatore
	 * @param carteGiocate carte giocate (terreno)
	 * @return true || false
	 */
	public static boolean isCartaGiocabile(Carta carta, List<Carta> manoGiocatore, List<Carta> carteGiocate) {
		//Se non e' stata giocata nessuna carta
		if (carteGiocate.isEmpty()) {
			return true;
		}
		
		SemeCarta semeRichiesto = carteGiocate.getFirst().getSeme();
		
		//Se la carta va bene
		if(carta.getSeme() == semeRichiesto) {
			return true;
		}
		
		//Questo vede se il giocatore ha una carta del seme giusto se
		//no gli permetto di giocare una carta a suo piacere
		return manoGiocatore.stream().noneMatch(c -> c.getSeme() == semeRichiesto);
	}
}
