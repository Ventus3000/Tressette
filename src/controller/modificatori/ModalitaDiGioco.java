package controller.modificatori;

public enum ModalitaDiGioco {
	UNO_VS_UNO(2,2), 
	DUE_VS_DUE(4,2), 
	UNO_VS_UNO_VS_UNO(3,3), 
	FREE_FOR_ALL(4,4); 
	
	private final int numeroGiocatori;
	private final int numeroSquadre;
	
	ModalitaDiGioco(int numeroGiocatori, int numeroSquadre) {
		this.numeroGiocatori = numeroGiocatori;
		this.numeroSquadre = numeroSquadre;
	}

	public int getNumeroGiocatori() {
		return numeroGiocatori;
	}

	public int getNumeroSquadre() {
		return numeroSquadre;
	}
	
}
