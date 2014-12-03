import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


public class récuperation_Données {
	public static boolean existeJoueur(String pseudo){
		try{ 
				Class.forName("org.postgresql.Driver"); // charge le driver
				String url = "jdbc:postgresql://localhost:5432/Projet_NIM"; // URL de la base
				String user = "postgres";  // Nom de la base
				String passwd = "3210Azerty"; // mot de passe de la base
				Connection conn = DriverManager.getConnection(url, user, passwd);// connecte la base à l'application 
				
				ResultSet Requete = null; // indispensable pour le count (sinom java pas content)
				Statement state = conn.createStatement();
				Requete = state.executeQuery("SELECT COUNT(*) FROM joueurs WHERE pseudo_joueur ="+pseudo);
	      
				int nb; // varriable qui serra affecté par le COUNT de la requete
				if (Requete.next()) {
					nb = Requete.getInt(1); // affectation de Count par la requete
					if (nb == 1) return true;
				}
				
		}
	     catch (Exception e) { // leve une exception en cas d'erreur
		      e.printStackTrace();	
		      
	    }
	     return false;
	}
	
	public static void recuperationDonnées(String pseudo){
		try {
		      Class.forName("org.postgresql.Driver"); // charge le driver

		      String url = "jdbc:postgresql://localhost:5432/Projet_NIM"; // URL de la base
		      String user = "postgres";  // Nom de la base
		      String passwd = "3210Azerty"; // mot de passe de la base

		      Connection conn = DriverManager.getConnection(url, user, passwd);// connecte la base à l'application mais le transfert de donnée n'est pas encore permis
		      
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
		      

		
		   if( result.next()){
		      identifiant =(int) result.getObject(1); 
		      partieJouees =(int) result.getObject(2);  
		      pseu = result.getObject(3).toString();
		      vic = (int) result.getObject(4);
		     
		      System.out.println("Num_Joueur : "+identifiant);
		      System.out.println("Parties Jouees : "+partieJouees);
		      System.out.println("Pseudo : " +pseu);
		      System.out.println("Nb victoires : "+vic);
		   }
		

		
		 state.close();
		    }
		    catch (Exception e) { // leve une exception en cas d'erreur
			      e.printStackTrace();		      
			      
		    }
		}
	public static void main(String[] args) {      
		String pseudo ="'???'";
		
		if (existeJoueur(pseudo)){
			System.out.println("le joueur existe");
			recuperationDonnées(pseudo);
		}
		else System.out.println("joueur inconnu");
		
	}
}
