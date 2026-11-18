package view.carta;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import model.carta.Carta;
import model.carta.SemeCarta;
import model.carta.ValoreCarta;

import java.io.IOException;
import java.io.Serial;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import view.Impostazioni;

/**
 * Classe che crea un JButton che se premuto apre un popup
 * che permette al utente di vedere le carte che ha preso durante il game
 */
public class BtCartePrese extends JButton implements MouseListener{
	
	@Serial
    private static final long serialVersionUID = 1L;

	/** Mazzo con tutte le carte di default coperte */
	private final Map<Carta, PanelCarta> mazzo;

	/** Crea il bottone */
	public BtCartePrese() {
		setPreferredSize(Impostazioni.DIM_CARTA_BOTTONE);

        try {
            setIcon(new ImageIcon (RendererCarta.getInstance().getRetro()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		
		addMouseListener(this);

		this.mazzo = new LinkedHashMap<>();
		generaMazzo();
	}

	/**
	 * Se clickato apre un pop up con le carte prese
	 * @param e the event to be processed
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		apriPopup();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	private void apriPopup() {
		JDialog popup = new JDialog();
		popup.setTitle("CartePrese");
		//Cosi non posso clickare dietro
		popup.setModal(true);
		popup.setSize(Impostazioni.DIM_POPUP);
		popup.setLocationRelativeTo(null);//Cosi sta al centro
		
		JPanel contentPanel = new JPanel();
		//
		contentPanel.setLayout(new GridLayout(4, 10, 5, 5));
		
		for(Map.Entry<Carta, PanelCarta> entry : mazzo.entrySet()) {
			contentPanel.add(entry.getValue());
		}
		
		popup.setContentPane(contentPanel);
		popup.setVisible(true);
	}
	
	private void generaMazzo() {
		for(SemeCarta seme : SemeCarta.values()) {
			for(ValoreCarta valore : ValoreCarta.values()) {
				mazzo.put(new Carta(seme, valore), new PanelCartaCoperta());
			}
		}
	}

	/**
	 * Aggiorna le carte prese aggiungendo quelle date
	 * @param cartePrese carte prese
	 */
	public void aggiornaMazzo(List<Carta> cartePrese) {
		for(Carta carta : cartePrese) {
			if(mazzo.containsKey(carta)) {
				mazzo.put(carta, new PanelCarta(carta));
			}
		}
	}
}
