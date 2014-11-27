package nim;

import java.util.*;

public class Joueur {
	
	/**
	 * 
	 */
	private int idJoueur;
	private String pseudoJoueur;
	private int partiesJouees;
	private int partieGagnees;
	private ArrayList<Coup> listeCoups;
	
	public Joueur(int idJoueur, String pseudo){
		this.setIdJoueur(idJoueur);
		this.setPseudoJoueur(pseudo);
		this.setPartiesJouees(0);
		this.setPartieGagnees(0);
		this.setListeCoups(new ArrayList<Coup>());
	}
	
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
