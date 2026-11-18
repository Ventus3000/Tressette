package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

/**
 * Classe che contiene tutte le impostazioni di tipo grafico dell'app
 */
public class Impostazioni {
	/** Risoluzione orizzontale*/
	public static int risoluzioneX = 1920;
	/** Risoluzione verticale*/
	public static int risoluzioneY = 1080;

	/** Path per tilemap delle carte*/
	public static String skinCarte = "/resources/textures/carte_tilemap_v2.png";
	/** Colore del bordo evidenziato*/
	public static Color BORDO_EVIDENZIATO = new Color(255, 215, 0);
	//public static Color BORDO_MANO = new Color(30, 160, 80);
	/** Colore del bordo bianco*/
	public static Color BORDO_BIANCO = new Color(255, 255, 255);
	/** Colore del bordo terreno prima carta coppe*/
	public static Color COL_SEME_COPPE = new Color(255, 46, 31);
	/** Colore del bordo terreno prima carta denari*/
	public static Color COL_SEME_DENARI = new Color(0, 87, 184);
	/** Colore del bordo terreno prima carta bastoni*/
	public static Color COL_SEME_BASTONI = Color.GREEN;
	/** Colore del bordo terreno prima carta spade*/
	public static Color COL_SEME_SPADE = new Color(255, 255, 255);
	/** Colore del terreno tutto*/
	public static Color COL_TERRENO = new Color(34, 139, 34);

	/** Dimensione pixel orizzontali carta */
	public static final int W_CARTA = 42;
	/** Dimensione pixel verticali carta */
	public static final int H_CARTA = 60;

	/** dim carta d2 */
	public static final Dimension DIM_CARTA = new Dimension(
			W_CARTA, 
			H_CARTA
	);

	/** Dimensione mano giocatore orizzontale */
	public static final Dimension DIM_MANO_ASSEX = new Dimension(
			W_CARTA * 10 + 50, 
			H_CARTA + 28
	);
	/** Dimensione mano giocatore verticale */
	public static final Dimension DIM_MANO_ASSEY = new Dimension(
			H_CARTA + 28,
            W_CARTA * 10
			
	);

	/** Layout mano giocatore orizzontale */
	public static final FlowLayout LAYOUT_ASSEX = new FlowLayout(FlowLayout.CENTER, -5, 10);
	/** Layout mano giocatore verticale */
	public static final FlowLayout LAYOUT_ASSEY = new FlowLayout(FlowLayout.CENTER, 10, -3);

	/** Dimensione bottone a forma di carta */
	public static final Dimension DIM_CARTA_BOTTONE = new Dimension(
			W_CARTA+ 4,
			H_CARTA + 4
	);

	/** Dimensione foto profilo */
	public static final Dimension DIM_FOTO_PROFILO = new Dimension(100, 100);
	/** Dimensione panel testo profilo */
	public static final Dimension DIM_TESTO_PROFILO = new Dimension(100, 20);
	/** Dimensione pop */
	public static final Dimension DIM_POPUP = new Dimension(W_CARTA * 11, H_CARTA * 5);

}
