package nim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class InsertPartie {
	
	public static int getLastIdParties(){
		try{ 
			Class.forName("org.postgresql.Driver"); // charge le driver
			String url = "jdbc:postgresql://localhost:5432/Projet_NIM"; // URL de la base
			String user = "postgres";  // Nom de la base
			String passwd = "3210Azerty"; // mot de passe de la base
			Connection conn = DriverManager.getConnection(url, user, passwd);// connecte la base à l'application 

			ResultSet Requete = null; // indispensable pour le count (sinom java pas content)
			Statement state = conn.createStatement();
			Requete = state.executeQuery("SELECT MAX(num_partie) FROM parties");
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

	public static void insertPartie(int numPartie,int numJoueur1,int numJoueur2){ 
		try{
			Class.forName("org.postgresql.Driver"); // charge le driver

			String url = "jdbc:postgresql://localhost:5432/Projet_NIM"; // URL de la base
			String user = "postgres";  // Nom de la base
			String passwd = "3210Azerty"; // mot de passe de la base

			Connection conn = DriverManager.getConnection(url, user, passwd);
			String requete = "INSERT INTO parties(num_partie, num_joueur_1 , num_joueur_2,type_partie) VALUES(?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(requete);
			pst.setInt(1, numPartie);
			pst.setInt(2, numJoueur1);
			pst.setInt(3, numJoueur2);
			pst.setString(4,"normale");
			pst.executeUpdate();
		}
		catch (Exception e) { // leve une exception en cas d'erreur
			e.printStackTrace();		     			      
		}		
	}

}
