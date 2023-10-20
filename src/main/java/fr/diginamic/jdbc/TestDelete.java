package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.mariadb.jdbc.Driver;

public class TestDelete {

	public static void main(String[] args) {

		// Etape 1 : chargement du driver avec méthode registerDriver ou
		// classe.forName("...Driver")
		try {
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException();
		}

		// Etape 2 : on créer une connecion

		try {
			Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307/compta", "root", "");
			// Requête SQL
			System.out.println(connection);

			// Création du statement
			Statement stat = connection.createStatement();
			// Supprime le fournisseur dont les Nom est 'La Maison de la Peinture'
			int db = stat.executeUpdate("DELETE FROM `fournisseur` WHERE NOM = 'La Maison des Peintures'");

			// indique le nombre d'entré effectué dans la bdd
			System.out.println("Vous avez supprimé " + db + " lignes");

			// fermeture du statement et de la connection
			stat.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
