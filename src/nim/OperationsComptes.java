package nim;

import java.util.Scanner;

public class OperationsComptes {
	
	public static Joueur joueurTemp;

	
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
			OperationsBDD.insererJoueur(pseudoNouveauJoueur, password1);
			joueurTemp = OperationsBDD.downloadJoueur(pseudoNouveauJoueur);
			inscriptionReussie = true;
		}
		catch(InscriptionException ie){
			ie.getMessage();
		}
		return inscriptionReussie;
	}


	/*--------------------------OPERATIONS PSEUDO------------------------------*/


	public static void pseudoConforme(String pseudo) throws InscriptionException{
		if(pseudo.length() < 4 || pseudo.length() > 10){
			throw new InscriptionException(pseudo.length());
		}
		else{
			for(int i = 1; i < pseudo.length() - 1; i++){//A verifier...
				char caractereCourant = pseudo.charAt(i);
				if(!estChiffre(caractereCourant) && !estLettreMaj(caractereCourant) && !estLettreMin(caractereCourant)){
					throw new InscriptionException();
				}
			}
		}
	}

	public static void pseudoLibre(String pseudo) throws InscriptionException{
		if(OperationsBDD.pseudoTrouve(pseudo)){
			throw new InscriptionException(pseudo);
		}
	}

	public static void pseudoExistant(String pseudo) throws LoginException{
		if(!OperationsBDD.pseudoTrouve(pseudo)){
			throw new LoginException(pseudo);
		}
	}
	
	/*-------------------------- OPERATIONS MDP ------------------------------*/


	public static void mdp1EqualsMdp2(String mdp1, String mdp2) throws InscriptionException{
		if(!mdp1.equals(mdp2)){
			throw new InscriptionException(mdp1, mdp2);
		}
	}

	public static void verificationMdp(String pseudo, String password) throws LoginException{
		if(!OperationsBDD.motDePasseCorrespondJoueur(pseudo, password)){
			throw new LoginException(pseudo, password);
		}
	}
	
	/*-------------------------- EXPRESSIONS REGULIERES  ------------------------------*/

	public static boolean estChiffre(char c){
		return (int)c >= 48 && (int)c <= 57;
	}

	public static boolean estLettreMaj(char c){
		return (int)c >= 65 && (int)c <= 90;
	}

	public static boolean estLettreMin(char c){
		return (int)c >= 97 && (int)c <= 122;
	}
}
