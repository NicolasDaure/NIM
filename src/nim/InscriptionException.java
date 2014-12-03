package nim;

public class InscriptionException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3809540103303453481L;

	public InscriptionException(int longueurPseudo){
		System.out.println("[ERREUR] Le pseudo ne doit faire entre 4 et 10 caractères.");
	}
	
	public InscriptionException(){
		System.out.println("[ERREUR] Le pseudo ne doit contenir que des caractères 0-9, a-z, A-Z.");
	}

	public InscriptionException(String pseudo){
		System.out.println("[ERREUR] Ce pseudo est déjà prit.");
	}
	
	public InscriptionException(String password1, String password2){
		System.out.println("[ERREUR] Les deux mots de passe ne correspondent pas !.");
	}
}
