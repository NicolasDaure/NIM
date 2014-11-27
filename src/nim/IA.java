package nim;

import java.util.*;

public class IA extends Joueur {

	public IA() {
		super(0, "Sabatroll");
		// TODO Auto-generated constructor stub
	}

	public Joueur retournerIA(){
		IA ia = new IA();
		return ia;
		
	}
	
	public Joueur jouerTourIA(Scanner clavier, Partie p, Joueur joueur){
		if(p.estVide()){
			return joueur;
		}
		else{
			//TODO IA
			return joueur;
		}
	}
	
	public void jouerCoup(Partie p){
		
	}
}
