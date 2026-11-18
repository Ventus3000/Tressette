package model.carta;

/**
 * Rapressenta i valori possibili di una carta/
 * Il valore e' diviso in due parti
 * <ul>
 *     <li>Valore numerico (1-10)</li>
 *     <li>Grado (dipende dalla carta)</li>
 * </ul>
 *
 * @see Carta
 */
public enum ValoreCarta {

    /** Valore numerico: 1, grado: 8 */
	ASSO(1, 8),
    /** Valore numerico: 1, grado: 9 */
    DUE(2, 9),
    /** Valore numerico: 1, grado: 10 */
    TRE(3, 10),
    /** Valore numerico: 1, grado: 1 */
    QUATTRO(4, 1),
    /** Valore numerico: 1, grado: 2 */
    CINQUE(5, 2),
    /** Valore numerico: 1, grado: 3 */
	SEI(6, 3),
    /** Valore numerico: 1, grado: 4 */
    SETTE(7, 4),
    /** Valore numerico: 1, grado: 5 */
    FANTE(8, 5),
    /** Valore numerico: 1, grado: 6 */
    CAVALLO(9, 6),
    /** Valore numerico: 1, grado: 7 */
    RE(10, 7);

    /** Valore numerico */
	private final int valoreNumerico;
    /** Grado della carta */
	private final int grado;

    ValoreCarta(int valoreNumerico, int grado) {
        this.valoreNumerico = valoreNumerico;
        this.grado = grado;
    }

    /**
     * Restituisce il valore numerico della carta
     * @return valore numeri (1-10)
     */
    public int getValoreNumerico() {
        return valoreNumerico;
    }

    /**
     * Restituisce il valore numerico della carta
     * Gradi di forza:
     * <ol>
     *     <li>Tre</li>
     *     <li>Due</li>
     *     <li>Asso</li>
     *     <li>Re</li>
     *     <li>Cavallo/Regina</li>
     *     <li>Fante</li>
     *     <li>Sette</li>
     *     <li>Sei</li>
     *     <li>Cinque</li>
     *     <li>Quatro</li>
     * </ol>
     * @return grado
     */
    public int getGrado() {
        return grado;
    }
}
