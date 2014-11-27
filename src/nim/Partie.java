package nim;

import java.util.*;

public class Partie {
	
	static int nbJetonsMaxLigne = 14;
	static Random aleatoire = new Random();

	private long idPartie;
	private int nbLignes;
	private int nbJetons;
	private ArrayList<Ligne> lignesPartie;

	public Partie(int lignes, int jetons){
		this.setIdPartie(0);
		this.nbLignes = lignes;
		this.nbJetons = jetons;
		this.lignesPartie = new ArrayList<Ligne>();
		for(int i = 0; i < this.nbLignes; i ++){
			this.lignesPartie.add(new Ligne());
		}
	}
	
	public Partie(long codePartie){
		this.setIdPartie(codePartie);
		this.nbLignes = 3 + aleatoire.nextInt(3);
		this.nbJetons = this.nbLignes * nbJetonsMaxLigne - aleatoire.nextInt((this.nbLignes * nbJetonsMaxLigne)/3);
		this.lignesPartie = new ArrayList<Ligne>();
		for(int i = 0; i < this.nbLignes; i ++){
			this.lignesPartie.add(new Ligne());
		}
	}
	
	public boolean estVideSimple(){
		return this.nbJetons == 0;
	}

	public boolean estVide(){
		return this.estVideAux(0);
	}
	
	public boolean estVideAux(int i){
		if(i >= this.nbLignes - 1){
			return this.lignesPartie.get(i).estVide();
		}
		else{
			return this.lignesPartie.get(i).estVide() && this.estVideAux(i + 1);
		}
	}
	
	public boolean lignePleine(int index){
		return this.lignesPartie.get(index).longueurLigne() >= nbJetonsMaxLigne;
	}
	
	public boolean ligneVide(int index){
		return this.lignesPartie.get(index).estVide();
	}
	
	public void ajouterJetonLigne(int index){
		if(!this.lignePleine(index)){
			this.lignesPartie.get(index).ajouterJeton();
		}
	}
	
	public void enleverJetonLigne(int indexLigne){
		if(!this.ligneVide(indexLigne)){
			this.lignesPartie.get(indexLigne).supprimerJeton();
			this.nbJetons--;
		}
	}
	
	public boolean enleverXJetonsLigne(int index, int JetonsARetirer) throws CoupException{
		if(index > this.nbLignes - 1 || index < 0){
			throw new CoupException();
		}
		else if(this.lignesPartie.get(index).estVide()){
			throw new CoupException(index);
		}
		else if(JetonsARetirer > this.lignesPartie.get(index).longueurLigne()){
			throw new CoupException(index, JetonsARetirer);
		}
		else{
			for(int i = 0; i < JetonsARetirer; i++){
				this.enleverJetonLigne(index);
			}
			return true;
		}
	}
	
	public void remplir(){
		int nbJetonsADistribuer = this.nbJetons;
		int ligneCourante = 0;
		while(nbJetonsADistribuer >= 0){
			if(ligneCourante >= this.nbLignes){
				ligneCourante = 0;
			}
			if(aleatoire.nextInt(10) < 6){
				this.ajouterJetonLigne(ligneCourante);
				nbJetonsADistribuer--;
			}
			ligneCourante++;
		}
	}
	
	public String toString(){
		String str = "";
		for(int i = 0; i < this.lignesPartie.size(); i++){
			str += this.lignesPartie.get(i).toString() + "\n";
		}
		return str;
	}

	public int getNbJetons(){
		return this.nbJetons;
	}
	
	public int getNbLignes(){
		return this.nbLignes;
	}
	
	public ArrayList<Ligne> getLignesPartie(){
		return this.lignesPartie;
	}

	public long getIdPartie() {
		return idPartie;
	}

	public void setIdPartie(long idPartie) {
		this.idPartie = idPartie;
	}
	
}
