package view.giocatore;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.Serial;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.giocatore.ColoreSquadra;
import view.Impostazioni;

/**
 * JPanel che contiene varie informazioni sul utente(nome, img profilo, punti)
 * <p>
 *     Estende JPanel
 * </p>
 *
 * @see view.TavoloDaGioco
 * @see model.dati.ProfiloGiocatore
 */
public class PanelTesseraGiocatore extends JPanel{
	
	@Serial
    private static final long serialVersionUID = 1L;

	/** Path per img profilo */
	private String imgPath;
	/** Indica se il panello devo essere ruotato */
	private final boolean isVerticale;

	/** Nome del giocatore */
	private  String nome;
	/** Punti del giocatore */
	private int punti;
	/** Label dei punti */
	private JLabel labPunti;
	/** Label del nome */
	private JLabel labNome;
	/** Pennello a sinistra */
	private JPanel panelSin;

	/** Costruttore pigro per non specificare che sia orizzontale */
    public PanelTesseraGiocatore() {
		this(false);
	}

	/**
	 * Costruttore tessera del giocatore
	 * @param isVerticale se la tessera e' verticale o no
	 */
	public PanelTesseraGiocatore(boolean isVerticale) {	
		this.isVerticale = isVerticale;
		
		this.punti = 0;
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		creaTessera();
	}
	
	private void creaTessera() {
		if(isVerticale) {
			setLayout(new GridLayout(2, 1, 5, 5));
			//setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		} else {
			setLayout(new GridLayout(1, 2, 5, 5));
			//setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		}
		
		panelSin = new JPanel();
		//panelSin.setBackground(Color.BLUE);
		panelSin.setPreferredSize(Impostazioni.DIM_FOTO_PROFILO);
		panelSin.setBorder(new LineBorder(Impostazioni.BORDO_BIANCO, 5));
		panelSin.setLayout(new GridLayout(1, 1, 5, 5));
		
		JPanel panelDes = new JPanel();
		//panelDes.setBackground(Color.GREEN);
		panelDes.setLayout(new GridLayout(2, 1, 1, 1));
		
		JPanel panelDesTop = new JPanel();
		JPanel panelDesBot = new JPanel();
		//panelDesTop.setBackground(Color.GRAY);		
		//panelDesBot.setBackground(Color.WHITE);
		//panelDesTop.setPreferredSize(Impostazioni.DIM_TESTO_PROFILO);
		//panelDesBot.setPreferredSize(Impostazioni.DIM_TESTO_PROFILO);
		
		labNome = new JLabel(" ", SwingConstants.LEFT);
		labPunti = new JLabel("P: " + punti, SwingConstants.LEFT);
		labNome.setFont(new Font("Arial", Font.BOLD, 18));
		labPunti.setFont(new Font("Arial", Font.BOLD, 16));
		panelDesTop.add(labNome);
		panelDesBot.add(labPunti);
		
		panelDes.add(panelDesTop);
		panelDes.add(panelDesBot);
		
		add(panelSin);
		add(panelDes);
	}

	/** Imposta i punti sulla tessera */
	public void setPunti(int punti) {
		this.punti = punti;
		labPunti.setText("P: " + punti);
		revalidate();
		repaint();
	}

	/**
	 * Imposta il colore del bordo della tessera
	 * @param colore colore della quadra del giocatore
	 */
	public void setColoreSquadra(ColoreSquadra colore) {
        Color coloreSquadra = switch (colore) {
            case BLU -> Color.BLUE;
            case ROSSO -> Color.RED;
            case GIALLO -> Color.YELLOW;
            case VERDE -> Color.GREEN;
        };

        setBorder(new LineBorder(coloreSquadra, 5));

		revalidate();
		repaint();
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
		labNome.setText(nome);
		revalidate();
		repaint();
	}
	
	public String getImgPath() {
		return imgPath;
	}
	
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
		PanelImgProfilo panelImgProfilo = new PanelImgProfilo(imgPath);
		panelSin.add(panelImgProfilo);
		revalidate();
		repaint();
	}
	
}
