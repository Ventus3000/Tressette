package model.observer;

/**
 * Interfaccia che unifica {@link ObserverPartita} e {@link ObserverMatch}
 *
 * @see ObserverPartita
 * @see ObserverMatch
 * @see Mediatore
 */
public interface ObserverMediatore extends ObserverPartita, ObserverMatch{

}
