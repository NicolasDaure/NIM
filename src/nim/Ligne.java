package nim;

public class Ligne {

	private Jeton queueDeLigne;
	
	/**
	 * Constructeur d'une nouvelle ligne vide.
	 */
	public Ligne(){
		this.setQueueDeLigne(new Jeton());
	}
	
	/**
	 * Retourne vrai si la ligne contient seulement un jeton fantôme.
	 * @return
	 */
	public boolean estVide(){
		return this.queueDeLigne.getPrecedent() == null;
	}
	
	/**
	 * Retourne la longueur de la ligne.
	 * @return
	 */
	public int longueurLigne(){
		return this.queueDeLigne.longueur();
	}
	
	/**
	 * Ajoute un jeton en queue de la ligne d'appel.
	 */
	public void ajouterJeton(){
		this.queueDeLigne = new Jeton(this.queueDeLigne, this.queueDeLigne.getValeur() + 1);
	}
	
	/**
	 * Supprime la queue de la ligne d'appel. 
	 * Si elle ne contient pas de jeton, la méthode ne fait rien.
	 * Si la ligne ne contient qu'une queue, elle devient un jeton fantôme.
	 * Sinon la queue devient le jeton précédent.
	 */
	public void supprimerJeton(){
		if(!this.estVide()){	
			this.queueDeLigne.setValeur(this.queueDeLigne.getPrecedent().getValeur());
			this.queueDeLigne.setPrecedent(this.queueDeLigne.getPrecedent().getPrecedent());
		}
	}
	
	public String toString(){
		return this.queueDeLigne.toString();
	}

	/**
	 * Getter permettant d'obtenir la queue de la ligne d'appel.
	 * @return queueDeLigne
	 */
	public Jeton getQueueDeLigne() {
		return queueDeLigne;
	}

	/**
	 * Défini le jeton passé en paramètre comme nouvelle queue de ligne.
	 * @param queueDeLigne
	 */
	public void setQueueDeLigne(Jeton queueDeLigne) {
		this.queueDeLigne = queueDeLigne;
	}
	
	
}
