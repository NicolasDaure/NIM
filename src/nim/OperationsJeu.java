package nim;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class OperationsJeu {

	public static Random aleatoire = new Random();

	
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
		OperationsBDD.uploadCoups(j);
		j.setListeCoups(new ArrayList<Coup>());	
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
		OperationsBDD.uploadCoups(j1);
		OperationsBDD.uploadCoups(j2);
		j1.setListeCoups(new ArrayList<Coup>());
		j2.setListeCoups(new ArrayList<Coup>());
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
