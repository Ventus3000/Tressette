package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.Assistente;
import controller.ControllerPartita;
import model.carta.Carta;
import model.carta.SemeCarta;
import model.giocatore.ColoreSquadra;
import model.observer.ObserverMediatore;
import view.carta.BtCartePrese;
import view.carta.PanelCarta;
import view.carta.PanelCartaCoperta;
import view.carta.PanelCartaMano;
import view.giocatore.GiocatoreView;
import view.giocatore.PanelTesseraGiocatore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.Serial;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.border.LineBorder;
/**
 * JFrame che si occupa di creare il tavolo da gioco dove la partita e' giocata
 *
 * <p>
 *     Implementa {@link ObserverMediatore}
 * </p>
 */
public class TavoloDaGioco extends JFrame implements ObserverMediatore{

	@Serial
    private static final long serialVersionUID = 1L;
	
	private ControllerPartita controller;

	/** Mappa che associa al id di un giocatore la sua view */
	private final Map<Integer, GiocatoreView> idXProfilo;
	
	private final JPanel contentPane;

	/** Tessera dati del giocatore */
    private PanelTesseraGiocatore tProfiloSud, tProfiloNord, tProfiloOvest, tProfiloEst;

	/** Mano del giocatore */
	private JPanel manoSud,manoNord, manoOvest, manoEst;

	/** Panel del terreno */
	private JPanel terreno;

	/** Panel del mazzo*/
	private PanelCartaCoperta mazzo;
	/** Bottone per vedere le carte prese */
	private BtCartePrese cartePrese;
	/** Label per informazioni a schermo */
	private JLabel prompt;

	/** Varibile che ricoda qual e' la mano evidenziata */
	private JPanel manoEvidenziata;

	/**
	 * Costruttore del tavolo da gioco crea e assembla i componenti
	 * @param giocatori giocatori view
	 */
	public TavoloDaGioco(List<GiocatoreView> giocatori) {
		this.idXProfilo = new HashMap<>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		setSize(Impostazioni.risoluzioneX, Impostazioni.risoluzioneY);
		setLocationRelativeTo(null);

		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		//contentPane.setBackground(new Color(121, 77, 46));

		inizializzaPanelCentro();
		inizializzaPanelSud();
		inizializzaPanelNord();
		inizializzaPanelOvest();
		inizializzaPanelEst();

		//Popolo la mappa
		for(GiocatoreView giocatore : giocatori){
			idXProfilo.put(giocatore.getId(), giocatore);
		}

		assegnaPanels(giocatori);
	}

	private void assegnaPanels(List<GiocatoreView> giocatori) {
		GiocatoreView utente = idXProfilo.get(Assistente.getInstance().getProfiloUtente().getIdGiocatore());
		List<GiocatoreView> giocatoriTemp = new ArrayList<>();

		for (GiocatoreView giocatore : giocatori) {
			if (!giocatore.getId().equals(utente.getId())) {
				giocatoriTemp.add(giocatore);
			}
		}

		assegnaPanel(utente, manoSud, tProfiloSud);

		if(giocatori.size() == 2 ) {
			assegnaPanel(giocatoriTemp.get(0), manoNord, tProfiloNord);
			nascondiElementiPanel(manoOvest, tProfiloOvest);
			nascondiElementiPanel(manoEst, tProfiloEst);
		} else if(giocatori.size() == 3) {
			assegnaPanel(giocatoriTemp.get(0), manoOvest, tProfiloOvest);
			assegnaPanel(giocatoriTemp.get(1), manoEst, tProfiloEst);
			nascondiElementiPanel(manoNord, tProfiloNord);
		} else if(giocatori.size() == 4 ) {
			assegnaPanel(giocatoriTemp.get(0), manoOvest, tProfiloOvest);
			assegnaPanel(giocatoriTemp.get(1), manoNord, tProfiloNord);
			assegnaPanel(giocatoriTemp.get(2), manoEst, tProfiloEst);
		}
	}

	private void assegnaPanel(GiocatoreView giocatore, JPanel mano, PanelTesseraGiocatore tessera) {
		giocatore.setMano(mano);
		giocatore.setTessera(tessera);
		tessera.setNome(giocatore.getNome());
		tessera.setImgPath(giocatore.getImgPath());
	}

	private void nascondiElementiPanel(JPanel mano, PanelTesseraGiocatore tessera) {
		mano.setVisible(false);
		tessera.setVisible(false);
	}
	
	private void inizializzaPanelCentro() {
        JPanel panelCentro = new JPanel();
		panelCentro.setBackground(Impostazioni.COL_TERRENO);
		
		//Per far stare al centro le cose
		panelCentro.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 0, 0, 10);
		terreno = new JPanel();
		terreno.setBackground(new Color(0,0,0));
		terreno.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		terreno.setPreferredSize(new Dimension(
				Impostazioni.W_CARTA * 4 + 60,
				Impostazioni.H_CARTA + 28)
		);
		terreno.setBorder(new LineBorder(new Color(255, 215, 0), 3));
		
		panelCentro.add(terreno, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		mazzo = new PanelCartaCoperta();
		mazzo.setBorder(new LineBorder(Impostazioni.BORDO_EVIDENZIATO, 1));
		
		panelCentro.add(mazzo, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		prompt = new JLabel("Banana", SwingConstants.LEFT);
		prompt.setFont(new Font("Arial", Font.BOLD, 18));
		panelCentro.add(prompt, gbc);
		
		contentPane.add(panelCentro, BorderLayout.CENTER);
		
	}
	
	private void inizializzaPanelSud() {
        //Ovest sinistra
        JPanel panelSud = new JPanel();
		
		panelSud.setBackground(Impostazioni.COL_TERRENO);
		panelSud.setPreferredSize(new Dimension(200, 150));
		
		panelSud.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		tProfiloSud = new PanelTesseraGiocatore();
		panelSud.add(tProfiloSud, gbc);
		
		gbc = new GridBagConstraints();
		
		manoSud = new JPanel();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 10, 0, 10);
		manoSud.setPreferredSize(Impostazioni.DIM_MANO_ASSEX);
		manoSud.setBackground(Impostazioni.COL_TERRENO);
		//manoSud.setBorder(new LineBorder(Impostazioni.BORDO_MANO, 4));
		manoSud.setLayout(Impostazioni.LAYOUT_ASSEX);
		panelSud.add(manoSud, gbc);
		
		gbc = new GridBagConstraints();
		
		cartePrese = new BtCartePrese();
		gbc.gridx = 2;
		gbc.gridy = 0;
		
		panelSud.add(cartePrese, gbc);
		
		contentPane.add(panelSud, BorderLayout.SOUTH);
	}
	
	private void inizializzaPanelNord() {
        JPanel panelNord = new JPanel();
		
		panelNord.setBackground(Impostazioni.COL_TERRENO);
		panelNord.setPreferredSize(new Dimension(150, 150));
		
		panelNord.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		manoNord = new JPanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 0, 10);
		//manoNord.setBackground(Color.blue);
		manoNord.setPreferredSize(Impostazioni.DIM_MANO_ASSEX);
		manoNord.setBackground(Impostazioni.COL_TERRENO);
		//manoNord.setBorder(new LineBorder(Impostazioni.BORDO_MANO, 4));
		manoNord.setLayout(Impostazioni.LAYOUT_ASSEX);
		panelNord.add(manoNord, gbc);
		
		gbc = new GridBagConstraints();

		gbc.gridx = 1;
		gbc.gridy = 0;
		tProfiloNord = new PanelTesseraGiocatore();
		panelNord.add(tProfiloNord, gbc);
		
		contentPane.add(panelNord, BorderLayout.NORTH);
	}
	
	private void inizializzaPanelOvest() {
        JPanel panelOvest = new JPanel();
		
		panelOvest.setBackground(Impostazioni.COL_TERRENO);
		panelOvest.setPreferredSize(new Dimension(300, 200));
		
		panelOvest.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 0, 10);
		tProfiloOvest = new PanelTesseraGiocatore(true);
		panelOvest.add(tProfiloOvest, gbc);
		
		gbc = new GridBagConstraints();
		
		manoOvest = new JPanel();
		gbc.gridx = 1;
		gbc.gridy = 0;
		manoOvest.setBackground(Impostazioni.COL_TERRENO);
		//manoOvest.setBorder(new LineBorder(Impostazioni.BORDO_MANO, 4));
		manoOvest.setPreferredSize(Impostazioni.DIM_MANO_ASSEY);
		//manoOvest.setBorder(Impostazioni.BORDO_VUOTO_MANO);
		manoOvest.setLayout(Impostazioni.LAYOUT_ASSEY);
		panelOvest.add(manoOvest, gbc);
		
		contentPane.add(panelOvest, BorderLayout.WEST);
	}

	private void inizializzaPanelEst() {
        JPanel panelEst = new JPanel();
		
		panelEst.setBackground(Impostazioni.COL_TERRENO);
		panelEst.setPreferredSize(new Dimension(300, 200));
		
		panelEst.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		manoEst = new JPanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		manoEst.setBackground(Impostazioni.COL_TERRENO);
		//manoEst.setBorder(new LineBorder(Impostazioni.BORDO_MANO));
		manoEst.setPreferredSize(Impostazioni.DIM_MANO_ASSEY);
		//manoEst.setBorder(Impostazioni.BORDO_VUOTO_MANO);
		manoEst.setLayout(Impostazioni.LAYOUT_ASSEY);
		panelEst.add(manoEst, gbc);

		gbc = new GridBagConstraints();
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 10, 0, 0);
		tProfiloEst = new PanelTesseraGiocatore(true);
		panelEst.add(tProfiloEst, gbc);
		
		contentPane.add(panelEst, BorderLayout.EAST);
	}

	/**
	 * Imposta il controller partita che il tavolo da gioco usera'
	 * @param controller controller partita
	 */
	public void setController(ControllerPartita controller) {
		this.controller = controller;
	}
	
	private void aggiornaPromt(String testo) {
		SwingUtilities.invokeLater(() -> {
			prompt.setText(testo);
		});
	}

	/** Attualmente non fa nulla */
	@Override
	public void partitaAvvia() {
		//System.out.println("-EVENTO: Partita avviata");
		//aggiornaPromt("Partita avviata");
	}

	/**
	 * Aggiorna il bordo del pannello del giocatore con il colore della sua squadra;
	 * @param idGiocatore id del giocatore
	 * @param colore colore della squadra
	 */
	@Override
	public void squadraAssegnata(Integer idGiocatore, ColoreSquadra colore) {
		//System.out.println("-EVENTO: Squadra assegnata");
		PanelTesseraGiocatore tg = idXProfilo.get(idGiocatore).getTessera();
		tg.setColoreSquadra(colore);
	}

	/**
	 * Aggiorna i punti del giocatore dato
	 * @param idGiocatore id del giocatore
	 * @param puntiSquadra punti della squadra del giocatore
	 */
	@Override
	public void esitoMatch(Integer idGiocatore, int puntiSquadra) {
		//System.out.println("-EVENTO: Esito Match");
		PanelTesseraGiocatore tg = idXProfilo.get(idGiocatore).getTessera();
		
		tg.setPunti(puntiSquadra);
	}

	/**
	 * Crea un thread e pare un JDialog con i vincitori e un bottone per tornare al menu
	 * @param risultatoGiocatori Mappa id giocatore se ha vinto o perso
	 */
	@Override
	public void partitaFinita(Map<Integer,Boolean> risultatoGiocatori) {
		//System.out.println("-EVENTO: Partita Finita");

		//Devo crea un thread perche senno non apre il menu
		SwingUtilities.invokeLater(() -> {
			JDialog popup = new JDialog();
			popup.setTitle("Esisto Partita");
			//Cosi non posso clickare dietro
			popup.setModal(true);
			popup.setSize(Impostazioni.DIM_POPUP);
			popup.setLocationRelativeTo(null);//Cosi sta al centro

			JPanel contentPanelPop = new JPanel();
			contentPanelPop.setLayout(new BorderLayout());

			JPanel panelVincitori = new JPanel();
			panelVincitori.setLayout(new FlowLayout(FlowLayout.CENTER));

			for(var entry : risultatoGiocatori.entrySet()) {
				if(entry.getValue()) {
					JLabel nomeGiocatore = new JLabel(
							idXProfilo.get(entry.getKey()).getNome()+ ", ",
							SwingConstants.CENTER);
					//nomeGiocatore.setBackground(getForeground());
					panelVincitori.add(nomeGiocatore);
				}
			}

			contentPanelPop.add(panelVincitori, BorderLayout.CENTER);

			JButton bntMenu = new JButton("Torna al Menu");
			bntMenu.addActionListener(e -> {
				popup.dispose();
				controller.tornaAlMenu();
			});
			contentPanelPop.add(bntMenu, BorderLayout.SOUTH);

			popup.setContentPane(contentPanelPop);
			popup.setVisible(true);

		});
	}

	/** Riproduce il suono del mescolare di carte */
	@Override
	public void matchIniziato() {
		//System.out.println("-EVENTO: Match Iniziato");
		AudioManager.getInstance().play("src/resources/audio/card-shuffle.wav");
	}

	/**
	 * Aggiorna le carte presenti nella mano del giocatore dato con le carte date
	 * @param idGiocatore id del giocatore
	 * @param carte carte della mano del giocatore
	 */
	@Override
	public void aggiornaManoGiocatore(Integer idGiocatore, List<Carta> carte) {
		//System.out.println("-EVENTO: Aggiorna mano di g" + idGiocatore);
		JPanel panelMano = idXProfilo.get(idGiocatore).getMano();
		
		panelMano.removeAll();
		
		if(panelMano == manoSud) {
			for(Carta carta : carte) {
				PanelCartaMano panelCarta = new PanelCartaMano(idGiocatore , carta, controller);
				panelMano.add(panelCarta);
			}
		} else {
			for(int i = 0; i < carte.size(); i++) {
				if(panelMano == manoNord) {
					panelMano.add(new PanelCartaCoperta());
				} else {
					panelMano.add(new PanelCartaCoperta(true));
				}
				
			}
		}
		
		panelMano.revalidate();
		panelMano.repaint();
		
	}

	/**
	 * Aggiorna il promt per comunicare di chi e' il turno ed evidenzia la mano del giocatore
	 * @param idGiocatore id del giocatore
	 * @param numeroTurno numero del turno
	 */
	@Override
	public void turnoDiGiocatore(Integer idGiocatore, int numeroTurno) {
		//System.out.println("-EVENTO: Turno Di: " + nomeGiocatore);
		aggiornaPromt("Turno numero " + numeroTurno + ", gioca " + idXProfilo.get(idGiocatore).getNome());
		
		JPanel panelMano = idXProfilo.get(idGiocatore).getMano();
		
		if (manoEvidenziata != null) {
			manoEvidenziata.setBorder(new EmptyBorder(4, 4, 4, 4));
		}
		
		panelMano.setBorder(new LineBorder(Impostazioni.BORDO_EVIDENZIATO, 5));
		manoEvidenziata = panelMano;

		if(panelMano == manoSud){
			PanelCartaMano.abilita();
		}

		panelMano.revalidate();
		panelMano.repaint();
	}

	/**
	 * Aggiorna le carte presenti sul terreno
	 * @param carte carte del terreno
	 */
	@Override
	public void aggiornaTerreno(List<Carta> carte) {
		//System.out.println("-EVENTO: Terreno Aggiornato");
		terreno.removeAll();
		
		//Coloro il bordo
		if (!carte.isEmpty()) {
			SemeCarta semeCarta = carte.getFirst().getSeme();
			Color coloreBordo = switch (semeCarta) {
                case COPPE -> Impostazioni.COL_SEME_COPPE;
                case BASTONI -> Impostazioni.COL_SEME_BASTONI;
                case DENARI -> Impostazioni.COL_SEME_DENARI;
                default -> Impostazioni.COL_SEME_SPADE;
            };

            terreno.setBorder(new LineBorder((coloreBordo), 5));
		}
	
		for(Carta carta : carte) {
			terreno.add(new PanelCarta(carta));
		}
		terreno.revalidate();
		terreno.repaint();
	}

	/**
	 * Aggiorna il prompt per comunicare il vincitore del round
	 * @param iGiocatore id giocatore vincitore
	 * @param numeroRound numero del round
	 */
	@Override
	public void vincitoreRound(Integer iGiocatore, int numeroRound) {
		//System.out.println("-EVENTO: Vincitore Round: ");
		aggiornaPromt("Vintore round: " + idXProfilo.get(iGiocatore).getNome());
		AudioManager.getInstance().play("src/resources/audio/card-slide-8.wav");
	}

	/**
	 * Aggiorna la carte prese dal giocatore
	 * <p>Funzione solo per utente</p>
	 * @param idGiocatore id del giocatore
	 * @param cartePrese carte prese dal giocatore
	 */
	@Override
	public void aggiornaCartePrese(Integer idGiocatore, List<Carta> cartePrese) {
		//System.out.println(cartePrese);
		//La soluzione meno matta
		if(idXProfilo.get(idGiocatore).getMano() == manoSud) {
			this.cartePrese.aggiornaMazzo(cartePrese);
		}
	}

	/** Nasconde il mazzo dal tavolo */
	@Override
	public void mazzoFinito() {
		mazzo.setVisible(false);
	}

	/** Riproduce il suono della pesca di una carta */
	@Override
	public void cartaPescata() {
		//Suono carta
		AudioManager.getInstance().play("src/resources/audio/card-slide-8.wav");
	}

	/** Riproduce il suono di una carta poggiate su una superficie solida */
	@Override
	public void cartaGiocata() {
		//Suono carta
		AudioManager.getInstance().play("src/resources/audio/card-place-1.wav");
	}

	/** Aggiorna il prompt per comunicare che la carta giocata non e' valida */
	@Override
	public void cartaNonValida(Integer idGiocatore) {
		aggiornaPromt("Carta non valida: ");
		JPanel panelMano = idXProfilo.get(idGiocatore).getMano();
		if(panelMano == manoSud){
			PanelCartaMano.abilita();
		}
		
	}

}
