package nim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class InsertCoup {

	public static int nbCoups(){
		try{ 
			Class.forName("org.postgresql.Driver"); // charge le driver
			String url = "jdbc:postgresql://localhost:5432/Projet_NIM"; // URL de la base
			String user = "postgres";  // Nom de la base
			String passwd = "3210Azerty"; // mot de passe de la base
			Connection conn = DriverManager.getConnection(url, user, passwd);// connecte la base à l'application 

			ResultSet Requete = null; // indispensable pour le count (sinom java pas content)
			Statement state = conn.createStatement();
			Requete = state.executeQuery("SELECT MAX(num_coup) FROM coups");
			if (Requete.next()){
				int nb = Requete.getInt(1); // affectation de Count par la requete
				return nb;	
			}
		}
		catch (Exception e) { // leve une exception en cas d'erreur
			e.printStackTrace();	     
		}
		return -1;
	}

	public static void insertCoup(int numCoup,int nbJeton,int ligne, int idJoueur,int idPartie){ 
		try{
			Class.forName("org.postgresql.Driver"); // charge le driver

			String url = "jdbc:postgresql://localhost:5432/Projet_NIM"; // URL de la base
			String user = "postgres";  // Nom de la base
			String passwd = "3210Azerty"; // mot de passe de la base

			Connection conn = DriverManager.getConnection(url, user, passwd);
			String requete = "INSERT INTO coups(num_coup, nb_jetons, ligne , num_joueur , num_partie) VALUES(?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(requete);
			pst.setInt(1, numCoup);
			pst.setInt(2, nbJeton);
			pst.setInt(3, ligne);
			pst.setInt(4, idJoueur);
			pst.setInt(5, idPartie);
			pst.executeUpdate();
		}
		catch (Exception e) { // leve une exception en cas d'erreur
			e.printStackTrace();		     			      
		}		
	}

	public static void main(String[] args) {   
		int n=nbCoups()+1;
		insertCoup(n,5,3,1,3);
	}

}

