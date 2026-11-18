package model.dati;

/**
 * Classe che rappresenta il profilo di un giocatore Umano / Utente
 * <p>
 *     Aggiunge il livello e tracker partite giocate
 * </p>
 * <p>
 *     Estende {@link ProfiloGiocatore}
 * </p>
 *
 * @see ProfiloGiocatore
 */
public class ProfiloGiocatoreUmano extends ProfiloGiocatore {
    /** Contatore partita giocate */
    private int partiteGiocate = 0;
    /** Contatore partita vinte */
    private int partiteVinte = 0;
    /** Contatore partita perse */
    private int partitePerse = 0;
    /** Livello del profilo */
    private int livello = 1;

    /**
     * Costruttore profilo giocatore AI
     * @param nome nome del profilo
     * @param imgPath path img del profilo
     */
    public ProfiloGiocatoreUmano(String nome, String imgPath) {
        super(nome, imgPath);
    }

    public void aggiungiPartitaGiocata(boolean risultato) {
        if(risultato) {
            partiteVinte++;
        } else{
            partitePerse++;
        }
        partiteGiocate++;
        levelUp();
    }

    /**
     * Esegue la formula per aumentare il livello
     */
    private void levelUp() {
        int puntiTot = (partiteVinte * 10) + (partitePerse * 5);
        livello = (int) (Math.sqrt(puntiTot) / 3);
    }

    public int getPartiteGiocate() {
        return partiteGiocate;
    }

    public int getPartiteVinte() {
        return partiteVinte;
    }

    public int getPartitePerse() {
        return partitePerse;
    }

    public int getLivello() {
        return livello;
    }
}
