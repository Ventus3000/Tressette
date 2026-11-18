package model.dati;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe che si occupa di conservare i dati dei profili
 *
 * <p>
 *     Implementa singleton
 * </p>
 *
 * @see controller.Assistente
 * @see ProfiloGiocatore
 * @see ProfiloGiocatoreUmano
 * @see ProfiloGiocatoreAI
 */
public class Database {
    /** Variable di istanza per il singleton */
    private static Database instance;
    /** Lista profili AI*/
    private final List<ProfiloGiocatoreAI> profiliAI;
    /** Profilo giocatore umano / utente */
    private ProfiloGiocatoreUmano profiloUtente;
    /** Costruttore privato del database per implementare singleton */
    private Database(){
        profiliAI = new ArrayList<>();
    }
    /**
     * Restituisce l'istanza di database se e' gia creato senno la crea
     * @return istanza di database
     */
    public static Database getInstance(){
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Imposta il profilo utente
     * @param profiloUtente profilo utente che vuoi impostare
     */
    public void setProfiloUtente(ProfiloGiocatoreUmano profiloUtente){
        this.profiloUtente = profiloUtente;
    }

    /**
     * Restituisce il profilo del utente
     * @return profilo utente
     */
    public ProfiloGiocatoreUmano getProfiloUtente(){
        return profiloUtente;
    }

    /**
     * Aggiunge il profilo AI dato alla lista dei profili AI
     * @param profilo profilo dell'ai
     */
    public void addProfiloAI(ProfiloGiocatoreAI profilo){
        profiliAI.add(profilo);
    }

    /**
     * Restituisce la lista dei profili delle AI
     * @return profili AI
     */
    public List<ProfiloGiocatoreAI> getProfiliAI() {
        return profiliAI;
    }

    /**
     * Salva i dati dei profili AI nel file dato
     * @param file file profili AI
     * @throws IOException ..
     */
    public void writeProfiliAI(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        ProfiloGiocatoreAI[] arrayProfili = profiliAI.toArray(new ProfiloGiocatoreAI[profiliAI.size()]);

        oos.writeObject(arrayProfili);

        oos.close();
        fos.close();
    }

    /**
     * Carica i dati dei profili AI
     * @param file file profili AI
     * @throws IOException ..
     */
    public void readProfiliAI(File file) throws IOException{
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            ProfiloGiocatoreAI[] profiliCaricati = (ProfiloGiocatoreAI[]) ois.readObject();

            profiliAI.clear();
            profiliAI.addAll(Arrays.asList(profiliCaricati));

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        fis.close();
        ois.close();
    }

    /**
     * Salva i dati del profilo utente
     * @param file file profilo utente
     * @throws IOException ...
     */
    public void writeProfiloUtente(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(profiloUtente);

        oos.close();
        fos.close();
    }

    /**
     * Carica i dati del profilo utente
     * @param file file profilo utente
     * @throws IOException ...
     */
    public void readProfiloUtente(File file) throws IOException{
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            profiloUtente = (ProfiloGiocatoreUmano) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        fis.close();
        ois.close();
    }
}
