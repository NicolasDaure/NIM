package nim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateJoueur {
	 
	public static void updateNbWin(int n, int nbWin){
		try {
			Class.forName("org.postgresql.Driver"); 

			String url = "jdbc:postgresql://localhost:5432/Projet_NIM";
			String user = "postgres";  
			String passwd = "3210Azerty"; 

			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			Statement state2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet result = state.executeQuery("UPDATE joueurs SET nb_victoires ="+nbWin+"WHERE num_joueur ="+n);
	 
		}
		catch (Exception e) { 	      	      
		}
	 }
	
	
	public static void updateNbPartiesJouees(int n, int nbPartiesJouees){
		try {
			Class.forName("org.postgresql.Driver"); 

			String url = "jdbc:postgresql://localhost:5432/Projet_NIM";
			String user = "postgres";  
			String passwd = "3210Azerty"; 

			Connection conn = DriverManager.getConnection(url, user, passwd);
			Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			Statement state2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet result = state.executeQuery("UPDATE joueurs SET nb_parties_jouees ="+nbPartiesJouees+"WHERE num_joueur ="+n);
			    
		}
		catch (Exception e) { 		      	      
		}
	}
	
	public static void main(String[] args) {      
	}
}
