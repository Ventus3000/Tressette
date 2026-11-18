package view.giocatore;

import javax.swing.JPanel;

/**
 * Classe che rappresenta un giocatore al interno della view
 *
 * @see controller.Assistente
 * @see model.dati.ProfiloGiocatore
 */
public class GiocatoreView {

	/** Id del giocatore */
	private final Integer id;
	/** Nome del giocatore */
	private final String nome;
	/** Path img profilo del giocatore */
	private final String imgPath;

	/** Panel che tiene le carte del giocatore */
	private JPanel mano;
	/** Icona nome punti del giocatore */
	private PanelTesseraGiocatore tessera;

	/**
	 * Construttore giocatore view
	 * @param id id del giocatore
	 * @param nome nome del giocatore
	 * @param imgPath img path della foto profilo
	 */
	public GiocatoreView(Integer id, String nome, String imgPath) {
		this.id = id;
		this.nome = nome;
		this.imgPath = imgPath;
	}

	public Integer getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public String getImgPath() {
		return imgPath;
	}

	public JPanel getMano() {
		return mano;
	}

	public void setMano(JPanel mano) {
		this.mano = mano;
	}

	public PanelTesseraGiocatore getTessera() {
		return tessera;
	}

	public void setTessera(PanelTesseraGiocatore tessera) {
		this.tessera = tessera;
	}

}
