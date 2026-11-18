package view.giocatore;

import view.Impostazioni;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Panel img profilo di un giocatore
 *<p>
 *     Estende JPanel
 *</p>
 *
 * @see PanelTesseraMenu
 * @see PanelTesseraGiocatore
 */
public class PanelImgProfilo extends JPanel {

    /** Img profilo */
    private final BufferedImage imgProfilo;

    /**
     * Costruisce il panel contenente l'img del profilo
     * @param imgPath path del img
     */
    public PanelImgProfilo(String imgPath) {

        setPreferredSize(Impostazioni.DIM_FOTO_PROFILO);

        try {
            imgProfilo = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Disegna la carta
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(imgProfilo != null){
            g.drawImage(imgProfilo, 0, 0, getWidth(), getHeight(), null);
        }
    }

}
