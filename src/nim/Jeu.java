package nim;

import java.util.*;

public class Jeu {

	public static Joueur joueurTemp;
	public static Joueur joueur1;
	public static Joueur joueur2;

	public static void main(String args[]){
		
	}


	//----------------------------LOGIN--------------------------
	
	public static boolean loginJoueur(Scanner clavier){
		boolean loginReussi = false;
		System.out.println("Saisir votre pseudo :");
		String pseudoJoueur = clavier.next();
		System.out.println("Saisir votre mot de passe :");
		String password = clavier.next();
		try{
			OperationsComptes.pseudoConforme(pseudoJoueur);
		}
		catch(InscriptionException ie){
			ie.getMessage();
		}
		try{
			OperationsComptes.pseudoExistant(pseudoJoueur);
			OperationsComptes.verificationMdp(pseudoJoueur, password);
			joueurTemp = OperationsBDD.downloadJoueur(pseudoJoueur);
			loginReussi = true;
		}
		catch(LoginException le){
			le.getMessage();
		}
		return loginReussi;	
	}

	/*--------------------------INSCRIPTION------------------------------*/

	public static boolean inscrireJoueur(Scanner clavier){
		boolean inscriptionReussie = false;
		System.out.println("Nouveau joueur :");
		String pseudoNouveauJoueur = clavier.next();
		System.out.println("Saisir un mot de passe :");
		String password1 = clavier.next();
		System.out.println("Répéter le mot de passe");
		String password2 = clavier.next();
		try{
			OperationsComptes.pseudoConforme(pseudoNouveauJoueur);
			OperationsComptes.pseudoLibre(pseudoNouveauJoueur);
			OperationsComptes.mdp1EqualsMdp2(password1, password2);
			OperationsBDD.insererJoueurBD(pseudoNouveauJoueur, password1);
			joueurTemp = OperationsBDD.downloadJoueur(pseudoNouveauJoueur);
			inscriptionReussie = true;
		}
		catch(InscriptionException ie){
			ie.getMessage();
		}
		return inscriptionReussie;
	}


}

