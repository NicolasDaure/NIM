package nim;

import java.util.*;

public class Jeu {

	public static Joueur joueur1;
	public static Joueur joueur2;
	public static Scanner clavier = new Scanner(System.in);
	public static boolean joueur2Connecte = false;
	public static boolean joueurConnecte = false;

	
	public static void main(String args[]){
		
		char choix;

		//Connextion joueur 1
		System.out.println("<<<<<<<<<<<<<<<BIENVENUE SUR JEU DE NIM>>>>>>>>>>>>>>>\n"
				+ "Saisir le nombre correspondant...\n"
				+ "1.Login\n"
				+ "2.Inscription");
		do{
			choix = clavier.next().charAt(0);
		}while(choix != '1' && choix != '2');
		choixTypeLog(choix);
		joueur1 = OperationsComptes.joueurTemp;
		joueurConnecte = true;

		//À partir de là on a le premier joueur.
		while(joueurConnecte){			
			System.out.println("<<<<<<<<<<<<<<< MENU >>>>>>>>>>>>>>>\n"
					+ "Saisir le nombre correspondant...\n"
					+ "1.Partie Solo\n"
					+ "2.Partie 2 joueurs");
			do{
				choix = clavier.next().charAt(0);
			}while(choix != '1' && choix != '2');

			if(choix == 1){
				IA bot = new IA();
				OperationsJeu.nouvellePartie(clavier, joueur1, bot);
			}

			else if(choix == 2){
				if(joueur2Connecte == false){
					System.out.println("<<<<<<<<<<<<<<< JOUEUR 2 >>>>>>>>>>>>>>>\n"
							+ "Cette partie nécessite 2 joueurs, saisir le nombre correspondant...\n"
							+ "1.Login\n"
							+ "2.Inscription");
					do{
						choix = clavier.next().charAt(0);
					}while(choix != '1' && choix != '2');
					choixTypeLog(choix);
					joueur2 = OperationsComptes.joueurTemp;
					joueur2Connecte = true;
				}
				OperationsJeu.nouvellePartie2J(clavier, joueur1, joueur2);
			}
		}
	}
	
	
	
	
	public static void choixTypeLog(char c){		
		if(c == 1){
			boolean loginReussi = false;
			while(!loginReussi){
				loginReussi = OperationsComptes.loginJoueur(clavier);
			}
		}
		else if(c == 2){
			boolean inscriptionReussie = false;
			while(!inscriptionReussie){
				inscriptionReussie = OperationsComptes.inscrireJoueur(clavier);
			}
		}		
	}
	
	
}

