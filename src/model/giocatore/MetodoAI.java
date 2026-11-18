package model.giocatore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import model.carta.Carta;

/**
 * Implementazione di {@link MetodoDiGioco} che rappresenta un AI semplice
 * <p>
 *     Comportamento che ha:
 *     <ol>
 * 	      <li>Se ha una carta del seme richiesto la gioca</li>
 * 	      <li>Altrimenti gioca una carta random</li>
 * 	   </ol>
 * </p>
 *
 * @see MetodoDiGioco
 * @see Giocatore
 * @see InfoMatch
 */

public class MetodoAI implements MetodoDiGioco{


	/**
	 * Sceglie una carta da giocare in base seguendo le regole del gioco
	 * <ol>
	 *     <li>Se ha una carta del seme richiesto la gioca</li>
	 *     <li>Altrimenti gioca una carta random</li>
	 * </ol>
	 * @param giocatore Giocatore che sceglie la carta
	 * @param info informazioni sullo stato corrente della partita
	 * @return carta scelta
	 */
	@Override
	public Carta scegliCarta(Giocatore giocatore, InfoMatch info) {
		
		//Creo un numero random per rendere la partita piu "analogica"
		Random random = new Random();
		
		try {
			//Tra  0 e 3000 milisec
			Thread.sleep(random.nextInt(2000) + 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		List<Carta> mano = giocatore.getMano();
		
		List<Carta> carteGiocabili = mano.stream()
				.filter(carta -> carta.getSeme() == info.getSemeRichiesto()
				).collect(Collectors.toList()
		);
		
		
		if(carteGiocabili.isEmpty()) {
			carteGiocabili = new ArrayList<>(mano);
		}
		
		return carteGiocabili.get(random.nextInt(carteGiocabili.size()));
	}

}
