package nim;

import java.util.*;

public class Jeu {
	
	public static Random aleatoire = new Random();
	public static Joueur joueurTemp;
	public static Joueur joueur1;
	public static Joueur joueur2;
		
	public static void main(String args[]){

	}
	
	
	public static boolean loginJoueur(Scanner clavier){
		boolean loginReussi = false;
		System.out.println("Saisir votre pseudo :");
		String pseudoJoueur = clavier.next();
		System.out.println("Saisir votre mot de passe :");
		String password = clavier.next();
		try{
			pseudoConforme(pseudoJoueur);
		}
		catch(InscriptionException ie){
			ie.getMessage();
		}
		try{
			pseudoExistant(pseudoJoueur);
			verificationMdp(pseudoJoueur, password);
			joueurTemp = downloadJoueur(pseudoJoueur);
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
			pseudoConforme(pseudoNouveauJoueur);
			pseudoLibre(pseudoNouveauJoueur);
			mdp1EqualsMdp2(password1, password2);
			insererJoueurBD(pseudoNouveauJoueur, password1);
			joueurTemp = downloadJoueur(pseudoNouveauJoueur);
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
			for(int i = 0; i < pseudo.length(); i++){
				char caractereCourant = pseudo.charAt(i);
				if(!estChiffre(caractereCourant) && !estLettreMaj(caractereCourant) && !estLettreMin(caractereCourant)){
					throw new InscriptionException();
				}
			}
		}
	}
	
	public static void pseudoLibre(String pseudo) throws InscriptionException{
		if(pseudoTrouve(pseudo)){
			throw new InscriptionException(pseudo);
		}
	}
	
	public static void pseudoExistant(String pseudo) throws LoginException{
		if(!pseudoTrouve(pseudo)){
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
		if(!motDePasseCorrespondJoueur(pseudo, password)){
			throw new LoginException(pseudo, password);
		}
	}
	
	/*-------------------------- OPERATIONS BD ------------------------------*/
	
	public static boolean pseudoTrouve(String pseudo){  //fonction OK
		if (RecuperationDonnees.existeJoueur(pseudo)){
			return true;
		}
		else return false;
	}
	
	public static boolean motDePasseCorrespondJoueur(String pseudo, String password){ // fonction OK
		if(RecuperationDonnees.getMotDePasse(pseudo).equals(password))return true;
		else return false;
	}
	
	public static void insererJoueurBD(String pseudo, String password){  //fonction OK
		 int n = AjoutJoueur.nbJoueurs() + 1;
		 AjoutJoueur.ajouterNouveauJoueur(n, pseudo, password);
	}
	
	public static void uploadJoueur(Joueur j){ // fonction OK
		int n =j.getIdJoueur();
		int nbPartiesJouees = j.getPartiesJouees();
		int nbWin = j.getPartieGagnees();
		UpdateJoueur.updateNbWin(n,nbWin);
		UpdateJoueur.updateNbPartiesJouees(n,nbPartiesJouees);
	}
	
	public static Joueur downloadJoueur(String pseudo){ //fonction OK
		int num = RecuperationDonnees.getNumJoueur(pseudo);
		int nbPartie = RecuperationDonnees.getNbPartiesJouees(pseudo);
		int Win = RecuperationDonnees.getWin(pseudo);
		return new Joueur(num,pseudo,nbPartie,Win);
	}
	
	/*-------------------------- EXPRESSIONS REGULIERES  ------------------------------*/
	
	public static boolean estChiffre(char c){
		return (int)c < 48 && (int)c > 57;
	}
	
	public static boolean estLettreMaj(char c){
		return (int)c < 65 && (int)c > 90;
	}
	
	public static boolean estLettreMin(char c){
		return (int)c < 97 && (int)c > 122;
	}
	
	
	/*-------------------------- PARTIE 2 JOUEURS ------------------------------------*/
	
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
	
	
	/*-------------------------- PARTIE 1 JOUEUR -------------------------------------*/
	
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

