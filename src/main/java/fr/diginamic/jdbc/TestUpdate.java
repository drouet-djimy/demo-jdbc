package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.mariadb.jdbc.Driver;

public class TestUpdate {
	public static void main(String[] args) {

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
			// Insere un nouveau fournisseur d'ID '4' et de nom 'La Maison de la Peinture'
			int db = stat.executeUpdate(
					"UPDATE `fournisseur` SET `NOM`='La Maison des Peintures' WHERE NOM ='La Maison de la Peinture'");

			// indique le nombre d'entré effectué dans la bdd
			System.out.println("Vous avez modifier " + db + " lignes");

			// fermeture du statement et de la connection
			stat.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
