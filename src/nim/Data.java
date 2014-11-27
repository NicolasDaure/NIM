package nim;

import java.util.ArrayList;

public class Data implements java.io.Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7250145256585646857L;
	private long nbParties;
	private ArrayList<Joueur> listeJoueurs;
	
	public Data(){
		this.setListeJoueurs(new ArrayList<Joueur>());
		this.setNbParties(0);
	}
	
	public void ajouterJoueur(Joueur j){
		this.listeJoueurs.add(j);
	}
	
	public void supprimerJoueur(Joueur j){
		this.listeJoueurs.remove(j);
	}
	
	public String toString(){
		String str = "";
		for(int i = 0; i < this.listeJoueurs.size(); i++){
			str  += "* " + this.listeJoueurs.get(i).getIdJoueur() + " - " + this.listeJoueurs.get(i).getPseudoJoueur() + "\n";
		}
		return str;
	}
	
	public long getNbParties() {
		return nbParties;
	}
	
	public void setNbParties(long nbParties) {
		this.nbParties = nbParties;
	}
	
	public ArrayList<Joueur> getListeJoueurs() {
		return listeJoueurs;
	}
	
	public void setListeJoueurs(ArrayList<Joueur> listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}
	
}
