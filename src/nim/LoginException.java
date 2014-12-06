package nim;

public class LoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7956152162755753974L;
	
	public LoginException(String pseudo){
		System.out.println("[ERREUR] Ce pseudo n'existe pas.");
	}
	
	public LoginException(String pseudo, String password){
		System.out.println("[ERREUR LOGIN] Le mot de passe n'est pas bon.");
	}

}
