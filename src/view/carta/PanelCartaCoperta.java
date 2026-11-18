package view.carta;

import java.awt.*;
import java.io.Serial;

import view.Impostazioni;

/**
 * Questa classe rappresenta una carta coperta
 * <p>
 *     Estende {@link PanelCarta}
 * <p/>
 */
public class PanelCartaCoperta extends PanelCarta{

	@Serial
    private static final long serialVersionUID = 1L;

	/** Idicatore se la carta e' ruotata di 90 gradi o no */
	private final boolean isRuotata;

	/** Costruttore pigro per fare un panel carta non ruotata */
	public PanelCartaCoperta() {
		this(false);
	}

	/**
	 * Genera il panel carta coperta
	 * @param isRuotata se messo true la carta viene ruotata di 90 gradi
	 */
	public PanelCartaCoperta(boolean isRuotata) {
		super();
		this.isRuotata = isRuotata;

		if(isRuotata) {
			setPreferredSize(new Dimension(
					Impostazioni.H_CARTA,
					Impostazioni.W_CARTA
			));
		} else {
			setPreferredSize(Impostazioni.DIM_CARTA);
		}
    }

	/**
	 * Disegna la carta
	 * @param g the <code>Graphics</code> object to protect
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		
		if(getImgCarta() != null) {
			if(isRuotata) {
				//Ruoto
				g2d.rotate(Math.toRadians(90), (double) getWidth() / 2, (double) getHeight() / 2);
				
				g2d.drawImage(getImgCarta(),
						(getWidth() - getImgCarta().getWidth()) / 2,
						(getHeight() - getImgCarta().getHeight()) / 2, this);
			} else {
				g2d.drawImage(getImgCarta(), 0, 0, this);
			}
		}
		g2d.dispose();
	}

	/**
	 * Restituisce se la carta e' ruotata
	 * @return true || false
	 */
	public boolean getIsRuotata() {
		return this.isRuotata;
	}

}
