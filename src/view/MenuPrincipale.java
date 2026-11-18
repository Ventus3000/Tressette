package view;

import view.giocatore.PanelTesseraMenu;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

import javax.swing.JLabel;

/**
 * JFrame che si occupa di creare il menu principale
 *
 * <p>
 *     Implementa {@link ActionListener}
 * </p>
 */
public class MenuPrincipale extends JFrame implements ActionListener{

	@Serial
    private static final long serialVersionUID = 1L;

	/** Contate panel*/
	private final JPanel contentPane;

	/** Bottone per avviare un game */
	private JButton btnPlay, btnOpzioni;
	/** Bottone per chiudere il gioco */
	private JButton btnQuit;
	
	private JButton btnSkins, btnTrofei;

	/** Costruttore del menu e lo crea */
	public MenuPrincipale() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Impostazioni.risoluzioneX, Impostazioni.risoluzioneY);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
			
		inizializzaPanelloTop();
		inizializzaPanelloCenter();
		inizializzaPanelloBot();

		btnTrofei.setVisible(false);
		btnSkins.setVisible(false);
		btnOpzioni.setVisible(false);
	}

	private void inizializzaPanelloTop() {
		JPanel panelTop = new JPanel();
		contentPane.add(panelTop, BorderLayout.NORTH);

		panelTop.setLayout(new BorderLayout());

		//Creo il "bottone" profilo
		PanelTesseraMenu pannelProfilo = new PanelTesseraMenu();
		panelTop.add(pannelProfilo , BorderLayout.WEST);

	}
	
	private void inizializzaPanelloCenter() {
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(new BorderLayout());

		JLabel jblTitolo = new JLabel("JTRESSETTE");
		jblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		jblTitolo.setFont(new Font("Dialog", Font.BOLD, 48));
		panelCenter.add(jblTitolo, BorderLayout.NORTH);

		JPanel panelCenterCenter = new JPanel();
		panelCenter.add(panelCenterCenter, BorderLayout.CENTER);
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenterCenter.setLayout(new GridBagLayout());
		
		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new BoxLayout(panelBtn, BoxLayout.Y_AXIS));
		panelCenterCenter.add(panelBtn);
		
		btnPlay = new JButton("PLAY");
		btnOpzioni = new JButton("OPZIONI");
		btnQuit = new JButton("QUIT");
		
		btnPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnOpzioni.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnQuit.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		btnPlay.addActionListener(this);
		btnOpzioni.addActionListener(this);
		btnQuit.addActionListener(this);
		
		panelBtn.add(btnPlay);
		panelBtn.add(btnOpzioni);
		panelBtn.add(btnQuit);
	}
	
	private void inizializzaPanelloBot() {
		JPanel panelBot = new JPanel();
		contentPane.add(panelBot, BorderLayout.SOUTH);
		panelBot.setLayout(new GridLayout(1, 3, 0, 0));
		
		JPanel pannelSkins = new JPanel();
		btnSkins = new JButton("Skins");
		btnSkins.addActionListener(this);
		pannelSkins.add(btnSkins);
		
		JPanel pannelTrofei = new JPanel();
		btnTrofei = new JButton("Trofei");
		btnTrofei.addActionListener(this);
		pannelTrofei.add(btnTrofei);
		
		panelBot.add(pannelSkins);
		panelBot.add(new JPanel());
		panelBot.add(pannelTrofei);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPlay) {
			Selettore selettore = new Selettore(this);
			selettore.setVisible(true);
			dispose();
		} else if (e.getSource() == btnQuit) {
			System.exit(0);
		}
		
	}
	
}
