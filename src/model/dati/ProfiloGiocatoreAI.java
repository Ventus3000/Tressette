package model.dati;

/**
 * Classe che rappresenta il profilo di un giocatore AI
 *
 * <p>
 *     Estende {@link ProfiloGiocatore}
 * </p>
 *
 * @see ProfiloGiocatore
 */
public class ProfiloGiocatoreAI extends ProfiloGiocatore {

    /**
     * Costruttore profilo giocatore AI
     * @param nome nome del profilo
     * @param imgPath path img del profilo
     */
    public ProfiloGiocatoreAI(String nome, String imgPath) {
        super(nome, imgPath);
    }
}
