package view.carta;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.carta.Carta;
import model.carta.SemeCarta;
import view.Impostazioni;

/**
 * Questa classe si occupa di caricare la tilemap delle carte tagliarla e restituire la carta richiesta
 * <p>
 *     Implementa singleton
 * </p>
 */
public class RendererCarta {
	/** Variable di istanza per il singleton */
	private static RendererCarta instance;
	//private static final int CarteXRiga = 13;
	//private static final int rige = 4;

	private final BufferedImage tilemap;

	/** Costruttore privato di render carta per implementare singleton */
	private RendererCarta() {
		System.out.print(Impostazioni.skinCarte);
        try {
            tilemap = ImageIO.read(getClass().getResourceAsStream(Impostazioni.skinCarte));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

	/**
	 * Restituisce l'istanza di render carta se e' gia creato senno la crea
	 * @return istanza di render carta
	 */
	public static RendererCarta getInstance() throws IOException {
		if (instance == null) {
			instance = new RendererCarta();
		}
		return instance;
	}

	/**
	 * Restituisce l'img della carte richiesta
	 * @param carta carta richiesta
	 * @return img della carta
	 */
	public BufferedImage getImmagineCarta(Carta carta) {
		//Mi trovo la posizione della carta nella tilemap
		int x = (carta.getValore().getValoreNumerico() - 1) * Impostazioni.W_CARTA;
		int y = getRigaSeme(carta.getSeme()) * Impostazioni.H_CARTA;

        return tilemap.getSubimage(x, y, Impostazioni.W_CARTA, Impostazioni.H_CARTA);
	}
	
	//Questo mi serve per dirmi dove si trova un determinato seme del immagine
	private int getRigaSeme(SemeCarta seme) {
        return switch (seme) {
            case COPPE -> 0;
            case DENARI -> 1;
            case BASTONI -> 2;
            case SPADE -> 3;
            default ->
                //1 perche' dove si trova il back delle carte
				1;
        };
	}

	/**
	 * Restituisce l'img di del retro di una carta
	 * @return retro carta
	 */
	//Questo metodo mi restituisce il retro delle carte
	public BufferedImage getRetro() {
		int x = 13 * Impostazioni.W_CARTA;
		int y = Impostazioni.H_CARTA;
		return tilemap.getSubimage(x, y, Impostazioni.W_CARTA, Impostazioni.H_CARTA);
	}
	
}
