package model.dati;

import java.io.Serial;
import java.io.Serializable;

/**
 * Classe astratta che rappresenta un profilo giocatore generico
 * <p>
 *     Implementa {@link Serializable}
 * </p>
 *
 * @see ProfiloGiocatoreAI
 * @see ProfiloGiocatoreUmano
 * @see Database
 */
public abstract class ProfiloGiocatore implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** Contatore statico per creare id univoci */
    private static int contatoreId = 0;

    /** Identificatore giocatore */
    private final Integer idGiocatore;
    /** Nome del profilo */
    private String nome;
    /** Path img profilo */
    private String imgPath;

    /**
     * Costruttore profilo giocatore
     * @param nome nome del profilo
     * @param imgPath path img del profilo
     */
    public ProfiloGiocatore(String nome, String imgPath) {
        contatoreId++;

        this.idGiocatore = contatoreId;
        this.nome = nome;
        this.imgPath = imgPath;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getIdGiocatore() {
        return idGiocatore;
    }

    public String getNome() {
        return nome;
    }

    public String getImgPath() {
        return imgPath;
    }
}
