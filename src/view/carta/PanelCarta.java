package view.carta;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serial;

import javax.swing.JPanel;

import model.carta.Carta;
import view.Impostazioni;

/**
 * JPanel che ha la forma di una carta
 * <p>
 *     Puo' avere retro o fronte di una carta
 * </p>
 */
public class PanelCarta extends JPanel{

	@Serial
    private static final long serialVersionUID = 1L;

	/** Carta rappresentata se la rappresenta */
	private final Carta carta;
	/** Img della carta */
	private final BufferedImage imgCarta;

	/**
	 *  panello nel caso si carta scoperta data
	 * @param carta carta
	 */
	public PanelCarta(Carta carta) {
		this.carta = carta;

        setPreferredSize(Impostazioni.DIM_CARTA);

        try {
            imgCarta = RendererCarta.getInstance().getImmagineCarta(carta);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

	/** Costruttore retro carta */
	public PanelCarta() {
		this.carta = null;

		setPreferredSize(Impostazioni.DIM_CARTA);
        try {
            imgCarta = RendererCarta.getInstance().getRetro();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

	/**
	 * Disegna la carta
	 * @param g the <code>Graphics</code> object to protect
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		
		if(imgCarta != null) {
			g2d.drawImage(imgCarta, 0, 0, this);
		}
		g2d.dispose();
	}


	/**
	 * Restituisce l'img della carta
	 * @return img carta
	 */
	public BufferedImage getImgCarta() {
		return imgCarta;
	}

	/**
	 * Restituisce la carta carta
	 * @return carta
	 */
	public Carta getCarta() {
		return carta;
	}
}
