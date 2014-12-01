package nim;

public class Coup {
	
	private long idPartie; // coucou
	private int numeroLigne;
	private int nombreJetonsRetires;
	
	/**
	 * Constructeur d'un coup à partir d'un code de partie, d'un numéro de ligne et d'un nombre de jetons.
	 * @param codePartie
	 * @param lignes
	 * @param jetonsEnleves
	 */
	public Coup(long codePartie, int lignes, int jetonsEnleves){
		this.setIdPartie(codePartie);
		this.setNumeroLigne(lignes);
		this.setNombreJetonsRetires(jetonsEnleves);
	}
	
	public String toString(){
		String str = "[Partie n°" + this.idPartie 
				+ "; Ligne " + this.numeroLigne
				+ "; Nb jetons " + this.nombreJetonsRetires + "]";
		return str;
	}

	public int getNumeroLigne() {
		return numeroLigne;
	}

	public void setNumeroLigne(int numeroLigne) {
		this.numeroLigne = numeroLigne;
	}

	public int getNombreJetonsRetires() {
		return nombreJetonsRetires;
	}

	public void setNombreJetonsRetires(int nombreJetonsRetires) {
		this.nombreJetonsRetires = nombreJetonsRetires;
	}

	public long getIdPartie() {
		return idPartie;
	}

	public void setIdPartie(long idPartie) {
		this.idPartie = idPartie;
	}
	
}
