package nim;

public class CoupException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4466828972157982283L;

	public CoupException(){
		System.out.println("[COUP REFUSÉ] La ligne saisie n'existe pas.");
	}
	
	public CoupException(int indexLigne){
		System.out.println("[COUP REFUSÉ] La ligne "+ (indexLigne + 1) +" est vide.");
	}
	
	public CoupException(int indexLigne, int nbJetonsARetirer){
		System.out.println("[COUP REFUSÉ] La ligne "+ (indexLigne + 1) +" ne contient pas autant de jetons.");
	}

}
