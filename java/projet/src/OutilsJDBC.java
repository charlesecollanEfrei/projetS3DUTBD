import java.sql.*;
import java.text.SimpleDateFormat;
public class OutilsJDBC {
	public static Connection openConnection (String url) {
		Connection co=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			co= DriverManager.getConnection(url);
		}
		catch (ClassNotFoundException e){
			System.out.println("il manque le driver oracle");
			System.exit(1);
		}
		catch (SQLException e) {
			System.out.println("impossible de se connecter à l'url : "+url);
			System.exit(1);
		}
		return co;
		}
	public static ResultSet exec1Requete (String requete, Connection co, int type){
		ResultSet res=null;
		try {
			Statement st;
			if (type==0){
				st=co.createStatement();}
			else {
				st=co.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					       	ResultSet.CONCUR_READ_ONLY);
				};
			res= st.executeQuery(requete);
		}
		catch (SQLException e){
			System.out.println("Problème lors de l'exécution de la requete : "+requete);
		};
		return res;
	}

	public static void closeConnection(Connection co){
		try {
			co.close();
			System.out.println("Connexion fermée!");
		}
		catch (SQLException e) {
			System.out.println("Impossible de fermer la connexion");
		}
}
	public static void main(String[] args) throws SQLException{
		String url="jdbc:oracle:thin:cecolla/Charles1995@oracle.iut-orsay.fr:1521:etudom"; 
		Connection co=OutilsJDBC.openConnection(url); 
		System.out.println("connexion ouverte");
		
		
		Statement stmt;
		try {

			//Question 1
			System.out.println("**** Question 1 **** ");
			CallableStatement cst1 = co.prepareCall("{? = call stageAvec}");
			cst1.registerOutParameter(1, java.sql.Types.INTEGER);
			boolean success1 = cst1.execute();
			
			if (!success1) {
				int nb1 = cst1.getInt(1);
				System.out.println( "Il y a " + nb1 + " étudiants avec un stage cette année");
			}
			else {
				System.out.println("Erreur de fonction à la question 1 ");
			}
			cst1.close();
			
			
			
			
			// Question 2
			System.out.println("\n**** Question 2 **** ");
			CallableStatement cst2 = co.prepareCall("{? = call stageSans}");
			cst2.registerOutParameter(1, java.sql.Types.INTEGER);
			boolean success2 = cst2.execute();
			
			if (!success2) {
				int nb2 = cst2.getInt(1);
				System.out.println( "Il y a " + nb2 + " étudiants sans stage cette année");
			}
			else {
				System.out.println("Erreur de fonction à la question 2 ");
			}
			cst2.close();
			
			
			
			
			// Question 3
			System.out.println("\n**** Question 3 **** ");
			System.out.println("Veuillez indiquez une date entre 2013 et 2016 ");
			int date;
			do{
				date = saisie.entier();
			}while(date > 2016 || date < 2013);
			
			CallableStatement cst3 = co.prepareCall("{? = call stageSansAnnee(?)}");
			cst3.registerOutParameter(1, java.sql.Types.INTEGER);
			cst3.setInt(2,date);
			boolean success3 = cst3.execute();
			
			if (!success3) {
				int nb3 = cst3.getInt(1);
				System.out.println( "Il y a " + nb3 + " étudiants sans stage l'année " + date);
			}
			else {
				System.out.println("Erreur de fonction à la question 3 ");
			}
			cst3.close();
			
			
			
			
			// Question 6
			System.out.println("\n**** Question 6 **** ");
			System.out.println("Veuillez indiquez un département ");
			int departement;
			do{
				departement = saisie.entier();
			}while(departement > 100 || departement < 1);
			
			System.out.println("Veuillez indiquez une ville ");
			String ville;
			ville = saisie.chaine();
	
			CallableStatement cst6 = co.prepareCall("{? = call stageGeographiqueChoisi(?,?)}");
			cst6.registerOutParameter(1, java.sql.Types.INTEGER);
			cst6.setInt(2,departement);
			cst6.setString(3,ville);
			boolean success6 = cst6.execute();
						
			if (!success6) {
				int nb6 = cst6.getInt(1);
				System.out.println( "Il y a " + nb6 + " étudiants en stage (ou par le passé) dans le departement " + departement + " et la ville " + ville);
			}
			else {
				System.out.println("Erreur de fonction à la question 6 ");
			}
			cst6.close();
			
			
			
			
			
			// Question 7
			System.out.println("\n**** Question 7 **** ");
			CallableStatement cst7 = co.prepareCall("{? = call stageGeographique}");
			cst7.registerOutParameter(1, java.sql.Types.INTEGER);
			boolean success7 = cst7.execute();
									
			if (!success7) {
				int nb7 = cst7.getInt(1);
				System.out.println( "Il y a " + nb7 + " étudiants en stage (ou par le passé)");
			}
			else {
				System.out.println("Erreur de fonction à la question 7 ");
			}
			cst6.close();
						
			

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		OutilsJDBC.closeConnection(co);
		//System.out.println("connexion fermee");

	}
}
