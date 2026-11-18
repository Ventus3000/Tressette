package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;

import controller.MainController;
import controller.modificatori.Durata;
import controller.modificatori.ModalitaDiGioco;

import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.io.Serial;


/**
 * Classe che visulizza un selettore per customizare il gioco
 *
 * @see MainController
 */
public class Selettore extends JFrame {

	@Serial
    private static final long serialVersionUID = 1L;

	/** Contate panel*/
	private final JPanel contentPane;

	/** Radio button per selezionare la modalita' di gioco */
	private JRadioButton rb1vs1, rb2vs2, rb1vs1vs1, rb1vs1x2;
	/** Radio button per selezionale la durata della partita */
	private JRadioButton rbVeloce, rbStandard, rbLunga;
	//private JRadioButton rbFacile, rbNormale , rbDifficile;

	/** Riferimento menu principale per tornare indietro*/
    private final JFrame menu;

	/**
	 * Costruttore selettore crea i componenti del frame
	 * @param menu pagina precedente
	 */
	public Selettore(JFrame menu) {

		this.menu = menu;

		//cose finestra
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Impostazioni.risoluzioneX, Impostazioni.risoluzioneY);
		setLocationRelativeTo(null);
		
		//Dove ci sta la robba
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		setContentPane(contentPane);
		
		JLabel lblTitolo = new JLabel("Personaliza la tua partita");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setFont(new Font("Dialog", Font.BOLD, 48));
		contentPane.add(lblTitolo);
		
		creaSelettoreModalita();
		creaSelettoreDurata();
		//creaSelettoreDifficolta();
		
		JPanel bottoni = new JPanel();
		bottoni.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
		contentPane.add(bottoni);

        JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setFont(new Font("Segoe UI", Font.PLAIN, 40));
        JButton btnAvvia = new JButton("Avvia");
		btnAvvia.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		
		btnAvvia.addActionListener(e -> {
			try {
				avviaPartita();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btnIndietro.addActionListener(e -> indietro());
		
		bottoni.add(btnIndietro);
		bottoni.add(btnAvvia);

	}
	
	private void creaSelettoreModalita() {
		JPanel panel = new JPanel();
		TitledBorder border  = new TitledBorder(
				null,
				"Modalit\u00E0",
				TitledBorder.LEADING,
				TitledBorder.TOP,
				new Font("Dialog", Font.BOLD, 32),
				null
		);
		
		panel.setBorder(border);
		contentPane.add(panel);
		
		rb1vs1 = new JRadioButton("Uno VS Uno");
		rb2vs2 = new JRadioButton("Due VS Due");
		rb1vs1vs1 = new JRadioButton("Uno VS Uno Vs Uno");
		rb1vs1x2 = new JRadioButton("Free For All");
		
		rb1vs1.setFont(new Font ("Segoe UI", Font.PLAIN, 24));
		rb2vs2.setFont(new Font ("Segoe UI", Font.PLAIN, 24));
		rb1vs1vs1.setFont(new Font ("Segoe UI", Font.PLAIN, 24));
		rb1vs1x2.setFont(new Font ("Segoe UI", Font.PLAIN, 24));
		
		ButtonGroup rbModalitaGroup = new ButtonGroup();
		rbModalitaGroup.add(rb1vs1);
		rbModalitaGroup.add(rb1vs1vs1);
		rbModalitaGroup.add(rb2vs2);
		rbModalitaGroup.add(rb1vs1x2);
		
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel.add(rb1vs1);
		panel.add(rb1vs1vs1);
		panel.add(rb1vs1x2);
		panel.add(rb2vs2);
	}
	
	private void creaSelettoreDurata() {
		JPanel panel = new JPanel();
		TitledBorder border  = new TitledBorder(
				null,
				"Durata",
				TitledBorder.LEADING,
				TitledBorder.TOP,
				new Font("Dialog", Font.BOLD, 32),
				null
		);
		panel.setBorder(border);
		contentPane.add(panel);
		
		rbVeloce = new JRadioButton("Veloce");
		rbStandard = new JRadioButton("Standard");
		rbLunga = new JRadioButton("Lunga");
		
		rbVeloce.setFont(new Font ("Segoe UI", Font.PLAIN, 24));
		rbStandard.setFont(new Font ("Segoe UI", Font.PLAIN, 24));
		rbLunga.setFont(new Font ("Segoe UI", Font.PLAIN, 24));
		
		ButtonGroup rbTipoPartitaGroup = new ButtonGroup();
		rbTipoPartitaGroup.add(rbVeloce);
		rbTipoPartitaGroup.add(rbStandard);
		rbTipoPartitaGroup.add(rbLunga);
		
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel.add(rbVeloce);
		panel.add(rbStandard);
		panel.add(rbLunga);
	}
	
	/*private void creaSelettoreDifficolta(){
		JPanel panel = new JPanel();
		TitledBorder border  = new TitledBorder(
				null,
				"Difficolt\u00E0",
				TitledBorder.LEADING,
				TitledBorder.TOP,
				new Font("Dialog", Font.BOLD, 32),
				null
		);
		panel.setBorder(border);
		contentPane.add(panel);
		
		rbFacile = new JRadioButton("Facile");
		rbNormale = new JRadioButton("Normale");
		rbDifficile = new JRadioButton("Difficile");
		
		rbFacile.setFont(new Font ("Segoe UI", Font.PLAIN, 24));
		rbNormale.setFont(new Font ("Segoe UI", Font.PLAIN, 24));
		rbDifficile.setFont(new Font ("Segoe UI", Font.PLAIN, 24));
		
		ButtonGroup rbDifficoltaGroup = new ButtonGroup();
		rbDifficoltaGroup.add(rbFacile);
		rbDifficoltaGroup.add(rbNormale);
		rbDifficoltaGroup.add(rbDifficile);
		
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		//panel.add(rbFacile);
		//panel.add(rbNormale);
		//panel.add(rbDifficile);
	}*/
	
	private void avviaPartita() throws IOException {
		ModalitaDiGioco modalita = modalitaSelezionata();
		Durata durata = durataSelezionata();
		//Difficolta difficolta = difficoltaSelezionata();
		
		if (modalita == null || durata == null /*|| difficolta == null*/) {
			JOptionPane.showMessageDialog(contentPane, "Seleziona un modificatore per categoria");
			return;
		}

		
		MainController.getInstance().avviaPartita(modalita, durata);
		dispose();
	}
	
	private void indietro() {;
		menu.setVisible(true);
		dispose();
	}
	
	private ModalitaDiGioco modalitaSelezionata() {
		if (rb1vs1.isSelected()) {
			return ModalitaDiGioco.UNO_VS_UNO;
		} else if (rb2vs2.isSelected()) {
			return ModalitaDiGioco.DUE_VS_DUE;
		} else if (rb1vs1vs1.isSelected()) {
			return ModalitaDiGioco.UNO_VS_UNO_VS_UNO;
		} else if (rb1vs1x2.isSelected()) {
			return ModalitaDiGioco.FREE_FOR_ALL;
		}
		return null;
	}
	
	private Durata durataSelezionata() {
		if (rbVeloce.isSelected()) {
			return Durata.VELOCE;
		} else if (rbStandard.isSelected()) {
			return Durata.STANDARD;
		} else if (rbLunga.isSelected()) {
			return Durata.LUNGA;
		}
		return null;
	}
	
	/*private Difficolta difficoltaSelezionata() {
		if (rbFacile.isSelected()) {
			return Difficolta.FACILE;
		} else if (rbNormale.isSelected()) {
			return Difficolta.NORMALE;
		} else if (rbDifficile.isSelected()) {
			return Difficolta.DIFFICILE;
		}
		return null;
	}*/
}
