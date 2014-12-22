package nim;

public class ChargeDriver {
	   public static void main(String[] args) {
	       try {
	           Class.forName("com.mysql.jdbc.Driver").newInstance();
	           System.out.println("Le chargement du driver fonctionne");
	       } catch (Exception ex) {
	           // traitement de l'erreur
	       }
	   }   
}
