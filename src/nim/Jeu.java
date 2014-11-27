package nim;

import java.util.*;

public class Jeu {
	
	public static Random aleatoire = new Random();
		
	public static void main(String args[]){

		Scanner clavier = new Scanner(System.in);
		Data donnees = new Data();
		Joueur j1 = new Joueur(0, "Nicolas");
		Joueur j2 = new Joueur(1, "Florent");
		donnees.ajouterJoueur(j1);
		donnees.ajouterJoueur(j2);
		System.out.println(donnees.toString());
		nouvellePartie2J(clavier, 0, j1, j2);
		System.out.println(j1.toString());
		System.out.println(j2.toString());
		
	}
	
	public static void nouvellePartie2J(Scanner clavier, long idPartie, Joueur j1, Joueur j2){
		System.out.println("*******NOUVELLE PARTIE 2J**********\n");
		j1.setPartiesJouees(j1.getPartiesJouees() + 1);
		j2.setPartiesJouees(j2.getPartiesJouees() + 1);
		Partie theGame = new Partie(idPartie);
		theGame.remplir();	
		Joueur premierAJouer = premierJoueur(j1,j2);
		if(premierAJouer.equals(j2)){
			j2 = j1;
			j1 = premierAJouer;
			
		}
		Joueur gagnant = j1.jouerTour(clavier, theGame, j2);
		System.out.println("Félicitations " + gagnant.getPseudoJoueur() + ", vous avez gagné !");
		gagnant.setPartieGagnees(gagnant.getPartieGagnees() + 1);
	}
	
	public static void nouvellePartie(Scanner clavier, long idPartie, Joueur j, IA ia){
		System.out.println("*******NOUVELLE PARTIE 1J**********\n");
		j.setPartiesJouees(j.getPartiesJouees() + 1);
		Partie theGame = new Partie(idPartie);
		theGame.remplir();
		Joueur premierAJouer = premierJoueur(j, ia);
		Joueur gagnant;
		if(premierAJouer.equals(j)){
			gagnant = j.jouerTourJoueur(clavier, theGame, ia);
		}
		else{
			gagnant = ia.jouerTourIA(clavier, theGame, j);
		}
		if(gagnant.equals(j)){
			System.out.println("Félicitations ! Vous avez gagné !");
			j.setPartieGagnees(j.getPartieGagnees() + 1);
		}
		else{
			System.out.println("Vous avez perdu");
		}
		
	}
		
	public static Joueur premierJoueur(Joueur j1, Joueur j2){
		int pileFace = aleatoire.nextInt(2);
		if(pileFace < 1){
			return j1;
		}
		else{
			return j2;
		}
	}
	
	
	
	
	

}

