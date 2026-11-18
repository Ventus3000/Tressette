package model.carta;

import java.util.Objects;

/**
 * Rappresenta una carta
 *
 * @see SemeCarta
 * @see ValoreCarta
 */
public class Carta {

	/** Seme della carta (SPADE, COPPE, DENARI, BASTONI */
	private final SemeCarta seme;

	/** Valore della carta */
    private final ValoreCarta valore;

	/**
	 * Costruttore carta
	 * @param seme (SPADE, COPPE, DENARI, BASTONI)
	 * @param valore 1 a 10
	 */
    public Carta(SemeCarta seme, ValoreCarta valore) {
        this.seme = seme;
        this.valore = valore;
    }

	/** @return Seme della carta */
	public SemeCarta getSeme() {
        return seme;
    }

	/** @return Valore della carta */
    public ValoreCarta getValore() {
        return valore;
    }

	/** @return Emoji della carta */
    @Override
    public String toString() {
    	String iconaSeme = switch (seme) {
            case SPADE -> "⚔️";
            case COPPE -> "🏆";
            case DENARI -> "🪙";
            case BASTONI -> "🥖";
        };
        return iconaSeme + valore.getValoreNumerico();
    }

	@Override
	public int hashCode() {
		return Objects.hash(seme, valore);
	}
}
