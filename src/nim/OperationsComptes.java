package nim;

public class OperationsComptes {
	
	

	/*--------------------------OPERATIONS PSEUDO------------------------------*/


	public static void pseudoConforme(String pseudo) throws InscriptionException{
		if(pseudo.length() < 4 || pseudo.length() > 10){
			throw new InscriptionException(pseudo.length());
		}
		else{
			for(int i = 1; i < pseudo.length() - 1; i++){
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
