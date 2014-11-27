package nim;

/**
 * @author Nicolas
 *
 */
public class Jeton {

	/**
	 * Pointeur sur le jeton précédent. Si le pointeur est null, il s'agit d'un jeton fantôme.
	 */
	private Jeton precedent;
	
	/**
	 * Valeur du jeton. Utile pour un perfectionnement éventuel de l'interface graphique.
	 */
	private int valeur;
	
	/**
	 * Constructeur d'un jeton fantôme (tête de liste).
	 */
	public Jeton(){
		this.setPrecedent(null);
		this.setValeur(0);
	}
	
	/**
	 * Constructeur d'un jeton pointant sur le jeton passé en paramètre.
	 * @param lePrecedent
	 * 		Jeton sur lequel le nouveau jeton va pointer.
	 */
	public Jeton(Jeton lePrecedent){
		this.setPrecedent(lePrecedent);
		this.setValeur(1);
	}
	
	/**
	 * Constructeur d'un jeton pointant sur le jeton passé en paramètre et une valeur passée en paramètre.
	 * @param lePrecedent
	 * 		Jeton sur lequel le nouveau jeton va pointer.
	 * @param laValeur
	 * 		Valeur du jeton.
	 */
	public Jeton(Jeton lePrecedent, int laValeur){
		this.setPrecedent(lePrecedent);
		this.setValeur(laValeur);
	}
	
	/**
	 * Retourne vrai si le jeton d'appel est un jeton fantôme.
	 * @return true si precedent == null
	 */
	public boolean estVide(){
		return this.precedent == null;
	}
	
	/**
	 * Retourne la longueur de la liste de jeton d'appel.
	 * @return longueur de la liste de jeton d'appel.
	 */
	public int longueur(){
		if(this.estVide()){
			return 0;
		}
		else{
			return this.precedent.longueur() + 1;
		}
	}
	
	
	public String toString(){
		if(this.estVide()){
			return "";
		}
		else{
			return this.precedent.toString() + " " + this.valeur;
		}
	}
	
	
	/**
	 * Retourne la valeur du jeton d'appel.
	 * @return this.valeur
	 */
	public int getValeur() {
		return valeur;
	}
	
	/**
	 * Attribue la valeur passée en paramètre à la valeur du jeton d'appel.
	 * @param valeur
	 */
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	/**
	 * Retourne le jeton précedent du jeton d'appel.
	 * @return this.precedent
	 */
	public Jeton getPrecedent() {
		return precedent;
	}
	
	/**
	 * Attribue le jeton passé en paramètre comme jeton précédent du jeton d'appel.
	 * @param precedent
	 */
	public void setPrecedent(Jeton precedent) {
		this.precedent = precedent;
	}
	
}
