package model.observer;

import java.util.List;
import java.util.ArrayList;

/**
 * Classe astratta per realizzare il pattern Observer/Observable
 * <p>
 *     Si occupa di registrare/deregustrare degli observer per ricevere notifiche
 * </p>
 *
 * @param <T> tipo di interfaccia observe utilizzata
 *
 * @see ObservableMatch
 * @see ObservablePartita
 * @see Mediatore
 * @see ObservableMatch
 * @see ObservablePartita
 */
abstract class Observable<T> {

	/** Lista degli observer registrati */
	private final List<T> observers;

	/** Costruttore di observable */
	public Observable() {
		this.observers = new ArrayList<>();
	}

	/**
	 * Aggiunge un observer alla lista degli observer
	 *
	 * @param observer observer da aggiungere
	 */
	public void aggiungiObserver(T observer) {
		if(observer != null && ! observers.contains(observer)) {
			observers.add(observer);
		}
	}

	/**
	 * Rimuove l'observer fornito
	 * @param observer observer da rimuovere
	 */
	public void rimuoviObserver(T observer) {
		observers.remove(observer);
	}	
	
	//Mi serve per la classe figlio che senno non puo prendere observers

	/**
	 * Restituisce una copia della lista degli observer
	 * @return lista observer
	 */
	protected List<T> getObservers(){
		return List.copyOf(observers);
	}
	
}
