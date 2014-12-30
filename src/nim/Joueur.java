package nim;

import java.util.*;

/**
 * @author Nicolas
 *
 */
public class Joueur {
	
	/**
	 * Attribut représentant l'identifiant du Joueur (Clé primaire). Cet identifiant est unique.
	 */
	private int idJoueur;
	
	/**
	 * Attribut représentant le pseudo du joueur. Cet pseudo est unique.
	 */
	private String pseudoJoueur;
	
	/**
	 * Attribut représentant le nombre de partie jouées par le joueur.
	 */
	private int partiesJouees;
	
	/**
	 * Attribut représentant le nombre de partie gagnées par le joueur.
	 */
	private int partieGagnees;
	
	/**
	 * Liste de tous les coups joués par un joueur.
	 */
	private ArrayList<Coup> listeCoups;
	
	public Joueur(){
		
	}
	
	/**
	 * Constructeur d'un joueur à partir d'un idJoueur et d'un pseudo (nouveau Joueur).
	 * @param idJoueur
	 * 			Identifiant du joueur.
	 * @param pseudo
	 * 			Pseudo du joueur.
	 */
	public Joueur(int idJoueur, String pseudo){
		this.setIdJoueur(idJoueur);
		this.setPseudoJoueur(pseudo);
		this.setPartiesJouees(0);
		this.setPartieGagnees(0);
		this.setListeCoups(new ArrayList<Coup>());
	}
	
	/**
	 * Constructeur strict d'un joueur (récupération des données d'un joueur dans la base).
	 * 
	 * @param idJoueur
	 * 			Identifiant du joueur.
	 * @param pseudo
	 * 			Pseudo du joueur.
	 * @param nbPartiesJouees
	 * 			Nombre de parties jouées par le joueur.
	 * @param nbPartieGagnees
	 * 			Nombre de parties gagnées par le joueur.
	 */
	public Joueur(int idJoueur, String pseudo, int nbPartiesJouees, int nbPartieGagnees){
		this.setIdJoueur(idJoueur);
		this.setPseudoJoueur(pseudo);
		this.setPartiesJouees(nbPartiesJouees);
		this.setPartieGagnees(nbPartieGagnees);
		this.setListeCoups(new ArrayList<Coup>());
	}
	

	/**
	 * Permet au joueur d'appel de jouer un coup dans une partie.
	 * @param p
	 * 			La partie courante.
	 * @param indexLigne
	 * 			La ligne où le joueur veut retirer des jetons.
	 * @param nbJetons
	 * 			Le nombre de jetons que le joueur veut retirer.
	 * @return true si le coup est validé
	 */
	public boolean jouerCoup(Partie p, int indexLigne, int nbJetons){
		boolean coupReussi = false;
		try{
			coupReussi = p.enleverXJetonsLigne(indexLigne, nbJetons);
			this.listeCoups.add(new Coup(p.getIdPartie(), indexLigne, nbJetons));
		}
		catch(CoupException ce){
			ce.getMessage();
		}
		return coupReussi;
	}
	
	/**
	 * Méthode récursive qui retourne le gagnant de la partie. Permet au joueur de jouer son tour (contre un adversaire humain).
	 * <p>
	 * <ul>
	 * Cette méthode procède en plusieurs étapes :
	 * <li>
	 * Une condition d'arrêt : si la partie est vide, le jeu s'interrompt et l'adversaire gagne.
	 * </li>
	 * <li>
	 * Tant que le coup du joueur n'est pas valide, on esaye de jouer un coup.
	 * </li>
	 * <li>
	 * On fait un appel récursif en inversant le joueur d'appel et l'adversaire.
	 * </li>
	 * </ul>
	 * </p>
	 * 
	 * @param clavier
	 * 			Le scanner utilisé par les joueurs (pas de déclaration multiple).
	 * @param p
	 * 			La partie courante.
	 * @param adversaire
	 * 			L'adversaire du joueur appelant.
	 * @return le gagnant de la partie.
	 */
	public Joueur jouerTour(Scanner clavier, Partie p, Joueur adversaire){
		if(p.estVide()){
			return adversaire;
		}
		else{
			boolean tourJoue = false;
			System.out.println(this.getPseudoJoueur() + " !");
			System.out.println(p.toString());
			do{
				System.out.println("Quelle ligne ?");
				int numeroLigne = clavier.nextInt();
				System.out.println("Combien de jetons ?");
				int nbJetons = clavier.nextInt();
				tourJoue = this.jouerCoup(p, numeroLigne - 1, nbJetons);
			}while(!tourJoue);
			return adversaire.jouerTour(clavier, p, this);
		}	
	}
	
	public Joueur jouerTourJoueur(Scanner clavier, Partie p, IA ia){
		if(p.estVide()){
			return ia;
		}
		else{
			boolean tourJoue = false;
			System.out.println(this.getPseudoJoueur() + " !");
			System.out.println(p.toString());
			do{
				System.out.println("Quelle ligne ?");
				int numeroLigne = clavier.nextInt();
				System.out.println("Combien de jetons ?");
				int nbJetons = clavier.nextInt();
				tourJoue = this.jouerCoup(p, numeroLigne - 1, nbJetons);
			}while(!tourJoue);
			return ia.jouerTourIA(clavier, p, this);
		}
	}
	
	public String toString(){
		String str = "------Joueur------\n"
				+ "Id: " + this.getIdJoueur() + "\n"
				+ "Pseudo : " + this.getPseudoJoueur() + "\n"
				+ "Parties jouées : " + this.getPartiesJouees() + "\n"
				+ "Parties gagnées : " + this.getPartieGagnees() +"\n"
				+ "------------------\n";
		return str;
	}
	
	public int getIdJoueur() {
		return idJoueur;
	}
	
	public void setIdJoueur(int idJoueur) {
		this.idJoueur = idJoueur;
	}
	
	public String getPseudoJoueur() {
		return pseudoJoueur;
	}
	
	public void setPseudoJoueur(String pseudoJoueur) {
		this.pseudoJoueur = pseudoJoueur;
	}
	
	public int getPartiesJouees() {
		return partiesJouees;
	}
	
	public void setPartiesJouees(int partiesJouees) {
		this.partiesJouees = partiesJouees;
	}

	public int getPartieGagnees() {
		return partieGagnees;
	}

	public void setPartieGagnees(int partieGagnees) {
		this.partieGagnees = partieGagnees;
	}

	public ArrayList<Coup> getListeCoups() {
		return listeCoups;
	}

	public void setListeCoups(ArrayList<Coup> listeCoups) {
		this.listeCoups = listeCoups;
	}

}
