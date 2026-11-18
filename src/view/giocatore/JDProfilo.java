package view.giocatore;

import controller.Assistente;
import view.Impostazioni;

import javax.swing.*;
import java.awt.*;

/**
 * JDialog che permette di modificare il nome e la foto profilo del giocatore
 * <p>
 *     Estende {@link JDialog}
 * </p>
 *
 * @see view.MenuPrincipale
 */
public class JDProfilo extends JDialog {

    /**
     * Costruisce il popuop
     * @param tessera tessera del giocatore nel menu
     * @param nome nome del giocatore
     * @param imgPath path per img profilo del giocatore
     */
    public JDProfilo(PanelTesseraMenu tessera, String nome, String imgPath) {
        setTitle("Modifica il profilo");
        setSize(Impostazioni.DIM_POPUP);
        setModal(true);
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel();
        setContentPane(contentPanel);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JLabel testoNome = new JLabel("Nuovo nome", SwingConstants.LEFT);
        testoNome.setFont(new Font("Arial", Font.BOLD, 18));
        contentPanel.add(testoNome);

        JTextField campoNome = new JTextField(nome);
        campoNome.setFont(new Font("Arial", Font.BOLD, 18));
        contentPanel.add(campoNome);

        JLabel testoImgPath = new JLabel("Nuovo path imaggine", SwingConstants.LEFT);
        testoImgPath.setFont(new Font("Arial", Font.BOLD, 18));
        contentPanel.add(testoImgPath);

        JTextField campoImg = new JTextField(imgPath);
        campoImg.setFont(new Font("Arial", Font.BOLD, 18));
        contentPanel.add(campoImg);

        JButton jbtConferma = new JButton("Conferma");
        contentPanel.add(jbtConferma);

        jbtConferma.addActionListener(e1 -> {
            Assistente.getInstance().aggiornaProfiloUtente(campoNome.getText(), campoImg.getText());
            tessera.aggiornaProfiloUtente();
            dispose();
        });
    }

}
