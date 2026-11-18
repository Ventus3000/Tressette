package controller;

import model.dati.Database;
import model.dati.ProfiloGiocatoreAI;
import model.dati.ProfiloGiocatoreUmano;
import model.giocatore.Giocatore;
import model.giocatore.MetodoAI;
import model.giocatore.MetodoUmano;
import view.giocatore.GiocatoreView;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Classe che si occupa di fare da cuscino per comunicare con il database
 *
 * @see Database
 * @see model.dati.ProfiloGiocatore
 * @see ProfiloGiocatoreUmano
 * @see ProfiloGiocatoreAI
 */
public class Assistente {

    /** Variable di istanza per il singleton */
    private static Assistente instance;

    /** Data base usato */
    private final Database db;

    /** Path di dove si trova il file con i dati del utente */
    private final File fileUtente =  new File("src/model/dati/db_utente.dat");
    /** Path di dove si trova il file con i dati dei profili della AI */
    private final File fileProfiliAI =  new File("src/model/dati/db_profili_ai.dat");

    /** Costruttore privato di assistente per implementare singleton */
    private Assistente() {
        this.db = Database.getInstance();
        //settaUtente();
        //settaProfili();
    }

    /**
     * Restituisce l'istanza di assistente se e' gia creato senno la crea
     * @return istanza di assistente
     */
    public static Assistente getInstance() {
        if (instance == null) {
            instance = new Assistente();
        }
        return instance;
    }

    /** Dice al db di caricare i dati */
    public void caricaDatiDB(){
        try {
            db.readProfiliAI(fileProfiliAI);
            db.readProfiloUtente(fileUtente);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void settaUtente()  {
        ProfiloGiocatoreUmano utente = new ProfiloGiocatoreUmano("TU", "src/resources/imgProfili/utente.jfif");
        db.setProfiloUtente(utente);
        try {
            db.writeProfiloUtente(fileUtente);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void settaProfili(){
        db.addProfiloAI(new ProfiloGiocatoreAI("Giuseppina", "src/resources/imgProfili/vecchia.jfif"));
        db.addProfiloAI(new ProfiloGiocatoreAI("Osvaldo", "src/resources/imgProfili/osvaldo.jfif"));
        db.addProfiloAI(new ProfiloGiocatoreAI("Bimbo Triste", "src/resources/imgProfili/bimbo.jfif"));

        try {
            db.writeProfiliAI(fileProfiliAI);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Genera i giocatori per il model tramite i profili
     * @param numeroGiocatori numero di giocatori
     * @return Lista giocatori partita
     * @throws IOException ...
     */
    public List<Giocatore> getGiocatoriModel(int numeroGiocatori) throws IOException {
        List<Giocatore> giocatori = new LinkedList<>();

        ProfiloGiocatoreUmano utente = db.getProfiloUtente();
        giocatori.add(new Giocatore(
                utente.getIdGiocatore(),
                new MetodoUmano()
                //new MetodoAI()
        ));

        List<ProfiloGiocatoreAI> profili = db.getProfiliAI();
        for(int i = 0; i < numeroGiocatori - 1; i++){
            giocatori.add(new Giocatore(
                    profili.get(i).getIdGiocatore(),
                    new MetodoAI()
            ));
        }

        return giocatori;
    }

    /**
     * Genera i giocatori per la view tramite i profili
     * @param numeroGiocatori numero di giocatori
     * @return Lista giocatori usati nella view
     * @throws IOException ...
     */
    public List<GiocatoreView> getGiocatoriView(int numeroGiocatori) throws IOException {
        List<GiocatoreView> giocatori = new LinkedList<>();

        ProfiloGiocatoreUmano utente = db.getProfiloUtente();
        giocatori.add(new GiocatoreView(
                utente.getIdGiocatore(),
                utente.getNome(),
                utente.getImgPath()
        ));

        List<ProfiloGiocatoreAI> profili = db.getProfiliAI();
        for(int i = 0; i < numeroGiocatori - 1; i++){
            giocatori.add(new GiocatoreView(
                    profili.get(i).getIdGiocatore(),
                    profili.get(i).getNome(),
                    profili.get(i).getImgPath()
            ));
        }

        return giocatori;
    }

    /**
     * Restituisce il profilo del utente prendendolo dal db
     * @return profilo utente
     */
    public ProfiloGiocatoreUmano getProfiloUtente() {
        return db.getProfiloUtente();
    }

    /**
     * Aggiorna i profili dei giocatori con l'esito della partita
     * <p>
     *     Attualmente aggiorna solo utente
     * </p>
     * @param risultatoGiocatori mappa id giocatore esito se ha vinto o no
     * @throws IOException ...
     */
    public void aggiornaProfili(Map<Integer, Boolean> risultatoGiocatori) throws IOException {
        ProfiloGiocatoreUmano utente = db.getProfiloUtente();

        if(risultatoGiocatori.containsKey(utente.getIdGiocatore())){
            utente.aggiungiPartitaGiocata(risultatoGiocatori.get(utente.getIdGiocatore()));
        }

        try {
            db.writeProfiloUtente(fileUtente);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Aggiorna i dati del profilo del utente che usa l'app
     * @param nome nome nuovo
     * @param imgPath nuovo img
     */
    public void aggiornaProfiloUtente(String nome, String imgPath) {
        ProfiloGiocatoreUmano profilo = getProfiloUtente();
        profilo.setNome(nome);
        profilo.setImgPath(imgPath);

        try {
            db.writeProfiloUtente(fileUtente);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
