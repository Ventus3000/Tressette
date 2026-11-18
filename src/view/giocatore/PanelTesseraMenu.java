package view.giocatore;

import controller.Assistente;
import view.Impostazioni;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.Serial;

/**
 * JPanel che contiene varie informazioni sul utente(nome, img profilo, livello)
 * <p>
 *     Estende JPanel
 * </p>
 *
 * @see view.MenuPrincipale
 * @see model.dati.ProfiloGiocatoreUmano
 */
public class PanelTesseraMenu extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;

    /** Label nome giocatore */
    private JLabel labNome;
    /** Panel foto profilo */
    private PanelImgProfilo panelImgProfilo;
    /** Pennello a sinistra */
    private JPanel panelSin;

    /** Costruttore tessera */
    public PanelTesseraMenu() {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        creaTessera();
    }

    private void creaTessera() {
        String nome = Assistente.getInstance().getProfiloUtente().getNome();
        String imgPath = Assistente.getInstance().getProfiloUtente().getImgPath();
        int livello = Assistente.getInstance().getProfiloUtente().getLivello();

        setLayout(new BorderLayout());

        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new GridLayout(1, 2, 5, 5));

        panelSin = new JPanel();
        //panelSin.setBackground(Color.BLUE);
        panelSin.setPreferredSize(Impostazioni.DIM_FOTO_PROFILO);
        panelSin.setBorder(new LineBorder(Impostazioni.BORDO_BIANCO, 5));
        panelSin.setLayout(new GridLayout(1, 1, 5, 5));
        panelImgProfilo = new PanelImgProfilo(imgPath);
        panelSin.add(panelImgProfilo);

        JPanel panelDes = new JPanel();
        //panelDes.setBackground(Color.GREEN);
        panelDes.setLayout(new GridLayout(2, 1, 1, 1));

        JPanel panelDesTop = new JPanel();
        JPanel panelDesBot = new JPanel();


        labNome = new JLabel(nome, SwingConstants.LEFT);
        JLabel labLivello = new JLabel("lv: " + livello, SwingConstants.LEFT);
        labNome.setFont(new Font("Arial", Font.BOLD, 18));
        labLivello.setFont(new Font("Arial", Font.BOLD, 16));
        labNome.setPreferredSize(Impostazioni.DIM_TESTO_PROFILO);
        labLivello.setPreferredSize(Impostazioni.DIM_TESTO_PROFILO);
        panelDesTop.add(labNome);
        panelDesBot.add(labLivello);

        panelDes.add(panelDesTop);
        panelDes.add(panelDesBot);

        panelCentro.add(panelSin);
        panelCentro.add(panelDes);
        add(panelCentro, BorderLayout.CENTER);

        JButton btnProfilo = new JButton("Modifica Profilo");
        btnProfilo.addActionListener(e -> {
            JDProfilo jdProfilo = new JDProfilo(this , nome, imgPath);
            jdProfilo.setVisible(true);
        });

        add(btnProfilo, BorderLayout.SOUTH);
    }

    /** Aggiorna i dati della tessera */
    protected void aggiornaProfiloUtente(){
        String nome = Assistente.getInstance().getProfiloUtente().getNome();
        String imgPath = Assistente.getInstance().getProfiloUtente().getImgPath();
        labNome.setText(nome);
        
        panelSin.remove(panelImgProfilo);
        panelImgProfilo = new PanelImgProfilo(imgPath);
        panelSin.add(panelImgProfilo);
        panelSin.revalidate();
        panelSin.repaint();
    }
}
