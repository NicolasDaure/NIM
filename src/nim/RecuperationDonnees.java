package nim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class RecuperationDonnees {
	
	public static boolean existeJoueur(String pseudo){
		pseudo = "'" + pseudo + "'";
		try{ 
			Class.forName("org.postgresql.Driver"); // charge le driver
			String url = "jdbc:postgresql://localhost:5432/Projet_NIM"; // URL de la base
			String user = "postgres";  // Nom de la base
			String passwd = "3210Azerty"; // mot de passe de la base
			Connection conn = DriverManager.getConnection(url, user, passwd);// connecte la base à l'application 
			
			ResultSet Requete = null; // indispensable pour le count (sinom java pas content)
			Statement state = conn.createStatement();
			Requete = state.executeQuery("SELECT COUNT(*) FROM joueurs WHERE pseudo_joueur ="+pseudo);
	     
			int nb; // variable qui sera affectée par le COUNT de la requete
			if(Requete.next()){
				nb = Requete.getInt(1); // affectation de Count par la requete
				if (nb == 1) return true;
			}		
		}
	    catch (Exception e) { // leve une exception en cas d'erreur
		      e.printStackTrace();			      
	    }
	    return false;
	}
	
	public static void recuperationDonnees(String pseudo){
		pseudo = "'" + pseudo + "'";
		try {
		     Class.forName("org.postgresql.Driver"); // charge le driver

		     String url = "jdbc:postgresql://localhost:5432/Projet_NIM"; // URL de la base
		     String user = "postgres";  // Nom de la base
		     String passwd = "3210Azerty"; // mot de passe de la base

		     Connection conn = DriverManager.getConnection(url, user, passwd);// connecte la base √† l'application mais le transfert de donn√©e n'est pas encore permis
		      
		     Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

		     ResultSet result = state.executeQuery("SELECT * FROM joueurs WHERE pseudo_joueur ="+pseudo);
		   	    
		     // 1 numero joueur 
		     // 2 nb parties jouees
		     // 3 pseudo joueur
		     // 4 nb victoires
		     // 5 mot de passe
		     int identifiant;
		     int partieJouees;
		     String pseu;
		     int vic;
		     
		     if(result.next()){
		    	 identifiant = (int) result.getObject(1); 
		    	 partieJouees = (int) result.getObject(2);  
		    	 pseu = result.getObject(3).toString();
		    	 vic = (int) result.getObject(4);
		     }	
		     
		     state.close();
		}
		catch(Exception e) { // leve une exception en cas d'erreur
			e.printStackTrace();		      	      
		}
	}
	
	
	public static int getNumJoueur(String pseudo){
		pseudo = "'" + pseudo + "'";
		int identifiant = 0;
		try {
			Class.forName("org.postgresql.Driver"); 

			String url = "jdbc:postgresql://localhost:5432/Projet_NIM";
			String user = "postgres";  
			String passwd = "3210Azerty"; 

			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet result = state.executeQuery("SELECT * FROM joueurs WHERE pseudo_joueur ="+pseudo);
			if( result.next()){
				identifiant =(int) result.getObject(1);//ERREUR ICI
			}  
		}	
		catch (Exception e) { 
			e.printStackTrace();		      	      
		}
		return identifiant;
	}
	
	
	public static int getNbPartiesJouees(String pseudo){
		pseudo = "'" + pseudo + "'";
		int nbPartiesJouees = 0;
		try {
			Class.forName("org.postgresql.Driver"); 

			String url = "jdbc:postgresql://localhost:5432/Projet_NIM";
			String user = "postgres";  
			String passwd = "3210Azerty"; 

			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet result = state.executeQuery("SELECT * FROM joueurs WHERE pseudo_joueur ="+pseudo);
			if(result.next()){
				nbPartiesJouees  =(int) result.getObject(2); 
			}  
		}	
		catch (Exception e) { 
			e.printStackTrace();		      	      
		}
		return nbPartiesJouees;
	}
	
	
	
	public static int getWin(String pseudo){
		pseudo = "'" + pseudo + "'";
		int Win = 0;
		try {
			Class.forName("org.postgresql.Driver"); 

			String url = "jdbc:postgresql://localhost:5432/Projet_NIM";
			String user = "postgres";  
			String passwd = "3210Azerty"; 

			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet result = state.executeQuery("SELECT * FROM joueurs WHERE pseudo_joueur ="+pseudo);
			if(result.next()){
				Win  =(int) result.getObject(4); 
			}  
		}	
		catch (Exception e) { 
			e.printStackTrace();		      	      
		}
		return Win;
	}
	
	
	public static String getMotDePasse(String pseudo){
		pseudo = "'" + pseudo + "'";
		String pseu = "ERROR";
		try {
			Class.forName("org.postgresql.Driver"); 

			String url = "jdbc:postgresql://localhost:5432/Projet_NIM";
			String user = "postgres";  
			String passwd = "3210Azerty"; 

			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet result = state.executeQuery("SELECT * FROM joueurs WHERE pseudo_joueur ="+pseudo);
			if( result.next()){
				pseu = result.getObject(5).toString();
			}  
		}
		catch (Exception e) { 
			e.printStackTrace();		      	      
		}
		return pseu;
	}
	
	public static void main(String[] args) {      
		getNumJoueur("Garen");
		getNbPartiesJouees("Garen");
		getWin("Garen");
		getMotDePasse("Garen");
	}
}
