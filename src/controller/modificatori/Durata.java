package controller.modificatori;

public enum Durata {
	VELOCE(1),
	STANDARD(21),
	LUNGA(41);
	
	private final int puntiVittoria;
	
	Durata(int puntiVittoria){
		this.puntiVittoria = puntiVittoria;
	}
	
	public int getPuntiVittoria() {
		return puntiVittoria;
	}
}
