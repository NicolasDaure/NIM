package nim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AjoutJoueur {

	public static int nbJoueurs(){
		try{ 
			Class.forName("org.postgresql.Driver"); // charge le driver
			String url = "jdbc:postgresql://localhost:5432/Projet_NIM"; // URL de la base
			String user = "postgres";  // Nom de la base
			String passwd = "3210Azerty"; // mot de passe de la base
			Connection conn = DriverManager.getConnection(url, user, passwd);// connecte la base à l'application 

			ResultSet Requete = null; // indispensable pour le count (sinom java pas content)
			Statement state = conn.createStatement();
			Requete = state.executeQuery("SELECT COUNT(*) FROM joueurs");
			if (Requete.next()){
				int nb = Requete.getInt(1); // affectation de Count par la requete
				return nb	;	
			}
		}
		catch (Exception e) { // leve une exception en cas d'erreur
			e.printStackTrace();	

		}
		return -1;	
	}

	public static void ajouterNouveauJoueur(int n, String pseudo,String mdp){
		try {
			Class.forName("org.postgresql.Driver"); // charge le driver

			String url = "jdbc:postgresql://localhost:5432/Projet_NIM"; // URL de la base
			String user = "postgres";  // Nom de la base
			String passwd = "3210Azerty"; // mot de passe de la base

			Connection conn = DriverManager.getConnection(url, user, passwd);// connecte la base à l'application mais le transfert de donnée n'est pas encore permis

			String requete = "INSERT INTO joueurs(num_joueur, nb_parties_jouees, pseudo_joueur , nb_victoires , mot_de_passe) VALUES(?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(requete);
			pst.setInt(1, n);
			pst.setInt(2, 0);
			pst.setString(3, pseudo);
			pst.setInt(4, 0);
			pst.setString(5, mdp);
			pst.executeUpdate();
		}
		catch (Exception e) { // leve une exception en cas d'erreur
			e.printStackTrace();		      

		}
	}

	public static void main(String[] args) {    
		int n=nbJoueurs()+1;
		ajouterNouveauJoueur(n,"Test","Test");
	}

}


