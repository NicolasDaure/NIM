package nim;

public class OperationsBDD {

	public static boolean pseudoTrouve(String pseudo){  //fonction OK
		if (RecuperationDonnees.existeJoueur(pseudo)){
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean motDePasseCorrespondJoueur(String pseudo, String password){ // fonction OK
		if(RecuperationDonnees.getMotDePasse(pseudo).equals(password)){
			return true;
		}
		else {
			return false;
		}
	}

	public static void insererJoueurBD(String pseudo, String password){  //fonction OK
		int n = AjoutJoueur.nbJoueurs() + 1;
		AjoutJoueur.ajouterNouveauJoueur(n, pseudo, password);
	}

	public static void uploadJoueur(Joueur j){ // fonction OK
		int n = j.getIdJoueur();
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


	public static void uploadCoups(Joueur j){
		int numPartie = InsertPartie.getLastIdParties();
		int idJoueur = j.getIdJoueur();
		for(int i = 0; i < j.getListeCoups().size(); i++){
			int n = InsertCoup.nbCoups() + 1;
			int nbjetons = j.getListeCoups().get(i).getNombreJetonsRetires();;
			int ligne =j.getListeCoups().get(i).getNumeroLigne() + 1;
			InsertCoup.insertCoup(n,nbjetons,ligne,idJoueur,numPartie);
		}
	}
	
	public static void uploadPartie(Partie p){
		
	}
	
}
