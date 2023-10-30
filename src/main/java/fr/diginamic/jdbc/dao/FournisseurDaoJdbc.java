package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mariadb.jdbc.Driver;

import fr.diginamic.jdbc.entites.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao {

	@Override
	public List<Fournisseur> extraire() {
		// Etape 1 : chargement du driver avec méthode registerDriver ou
		// classe.forName("...Driver")
		ArrayList<Fournisseur> listeFournisseur = new ArrayList<Fournisseur>();

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
			PreparedStatement stat = connection.prepareStatement("SELECT * FROM fournisseur");
			// création du curseur contenant chaque ligne de la bdd
			ResultSet curseur = stat.executeQuery();

			// création d'une liste de fournisseur

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
		return listeFournisseur;
	}

	@Override
	public void insert(Fournisseur fournisseur) {
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
			
			// Insere un nouveau fournisseur
			
			int id=fournisseur.getId();
			String nom = fournisseur.getNom();
			int db = stat.executeUpdate("INSERT INTO `fournisseur`(`ID`, `NOM`) VALUES ('"+id+"','"+nom+"')");

			// indique le nombre d'entré effectué dans la bdd
			System.out.println("Vous avez inserer " + db + " nouvelles lignes");

			// fermeture du statement et de la connection
			stat.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		// Etape 1 : chargement du driver avec méthode registerDriver ou
		// classe.forName("...Driver")
		
		int db=0;
		
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
			// Insere un nouveau fournisseur avec les informations passé en parametre 
			db = stat.executeUpdate(
					"UPDATE `fournisseur` SET `NOM`='"+nouveauNom+"' WHERE NOM ='"+ancienNom+"'");

			// indique le nombre d'entré effectué dans la bdd
			System.out.println("Vous avez modifier " + db + " lignes");

			// fermeture du statement et de la connection
			stat.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return db;
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
		// Etape 1 : chargement du driver avec méthode registerDriver ou
		// classe.forName("...Driver")
		
		int id=fournisseur.getId();
		boolean bool=false;
		
		try {
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException();
		}

		// Etape 2 : on créer une connection

		try {
			Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307/compta", "root", "");
			// Requête SQL
			System.out.println(connection);

			// Création du statement
			Statement stat = connection.createStatement();
			// Supprime le fournisseur dont les Nom est 'La Maison de la Peinture'
			int db = stat.executeUpdate("DELETE FROM `fournisseur` WHERE ID = '"+id+"'");
			// indique le nombre d'entré effectué dans la bdd
			System.out.println("Vous avez supprimé " + db + " lignes");

			// fermeture du statement et de la connection
			stat.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return bool;
	}
}
