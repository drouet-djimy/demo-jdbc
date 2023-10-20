package fr.diginamic.jdbc;

import java.sql.*;
import java.util.ArrayList;

import org.mariadb.jdbc.Driver;
import fr.diginamic.jdbc.entites.Fournisseur;

public class TestSelect {

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
			// création du curseur contenant chaque ligne de la bdd
			ResultSet curseur = stat.executeQuery("SELECT * FROM fournisseur");

			// création d'une liste de fournisseur
			ArrayList<Fournisseur> listeFournisseur = new ArrayList<Fournisseur>();

			// pour chaque ligne de la bdd créer le fournisseur associé
			while (curseur.next()) {
				Integer id = curseur.getInt("ID");
				String nom = curseur.getString("NOM");
				listeFournisseur.add(new Fournisseur(id, nom));
			}

			// boucle parcourant la listeFournisseur et affichant chacun d'entre eux
			for (Fournisseur fournisseur : listeFournisseur) {
				System.out.println(fournisseur.toString());
			}
			
			// fermeture du Statement et de la connection
			stat.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
