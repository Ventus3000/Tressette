package controller;

import controller.modificatori.Durata;
import controller.modificatori.ModalitaDiGioco;
import model.observer.Mediatore;
import model.Partita;
import model.giocatore.Giocatore;
import view.MenuPrincipale;
import view.giocatore.GiocatoreView;
import view.TavoloDaGioco;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Controller principale
 * <p>
 *     Implementa il pattern singleton per garantire un unica istanza di se
 * </p>
 * Cose che fa:
 * <ul>
 *     <li>Apre il menu principale</li>
 *     <li>Crea i "componenti" del pattern mvc</li>
 *     <li>Crea e gestisce l'inizializzazione della partita</li>
 *     <li>Comunica con {@link  Assistente} per la gestione dei dati</li>
 * </ul>
 *
 * @see
 */
public class MainController {

    /** Variable di istanza per il singleton */
    private static MainController instance;
    /** Si occupa di gestire i dati del database */
    private final Assistente assistente;

    /** Costruttore privato di main controller per implementare singleton */
    private MainController() {
        this.assistente = Assistente.getInstance();
        assistente.caricaDatiDB();
    }

    /**
     * Restituisce l'istanza di controller se e' gia creato senno la crea
     * @return istanza di main controller
     */
    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    /** Apre il menu principale */
    public void apriMenu() {
        MenuPrincipale menu = new MenuPrincipale();
        menu.setVisible(true);
    }

    /**
     * Avvia la partita creando tutti i "componenti" del pattern mvc e applica i modificatori alla partita
     * @param modalita modificatore della modalita' (numero giocatori, numero squadre)
     * @param durata quanti punti servono per vincere
     * @throws IOException ..
     */
    public void avviaPartita(ModalitaDiGioco modalita, Durata durata) throws IOException {
        //1
        List<Giocatore> giocatori = assistente.getGiocatoriModel(modalita.getNumeroGiocatori());
        List<GiocatoreView> giocatoriView = assistente.getGiocatoriView(modalita.getNumeroGiocatori());

        //2
        Mediatore mediatore = new Mediatore();

        //3
        TavoloDaGioco view = new TavoloDaGioco(giocatoriView);

        //4
        mediatore.aggiungiObserver(view);

        //5
        Partita partita = new Partita(giocatori,
                modalita.getNumeroSquadre(),
                durata.getPuntiVittoria(),
                mediatore);

        //6
        ControllerPartita controllerPartita = new ControllerPartita(partita, view);

        //7
        view.setController(controllerPartita );

        //8
        controllerPartita.avviaPartita();
    }

    /**
     * Termina la partita e aggiorna i profili dei giocatori con i risultati
     * @param risultatoGiocatori mappa che associa al id del giocatore se ha vinto o no
     */
    public void partitaTerminata(Map<Integer, Boolean> risultatoGiocatori){
        try {
            assistente.aggiornaProfili(risultatoGiocatori);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        apriMenu();
    }
}
