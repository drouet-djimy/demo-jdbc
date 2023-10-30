package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mariadb.jdbc.Driver;

import fr.diginamic.jdbc.entites.Ville;

public class VilleDao {
	public List<Ville> extraire() {
		// Etape 1 : chargement du driver avec méthode registerDriver ou
		// classe.forName("...Driver")
		ArrayList<Ville> listeVille = new ArrayList<Ville>();

		try {
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException();
		}

		// Etape 2 : on créer une connecion

		try {
			Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307/recensement", "root", "");
			// Requête SQL
			System.out.println(connection);

			// Création du statement
			PreparedStatement stat = connection.prepareStatement("SELECT * FROM Ville");
			// création du curseur contenant chaque ligne de la bdd
			ResultSet curseur = stat.executeQuery();

			// création d'une liste de fournisseur

			// pour chaque ligne de la bdd créer le fournisseur associé
			while (curseur.next()) {
				Integer id = curseur.getInt("ID");
				String nom = curseur.getString("NOM");
				String population = curseur.getString("POPULATION");
				String idDept = curseur.getString("IDDEPT");
				String idRegion = curseur.getString("IDREGION");
				listeVille.add(new Ville(nom,population,idDept,idRegion));
			}

			// boucle parcourant la listeFournisseur et affichant chacun d'entre eux
			for (Ville ville : listeVille) {
				System.out.println(ville.toString());
			}

			// fermeture du Statement et de la connection
			stat.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return listeVille;
	}
	
	
	public void insert(Ville ville) {
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
			Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307/recensement", "root", "");
			// Requête SQL
			System.out.println(connection);

			// Création du statement
			PreparedStatement stat =connection.prepareStatement("INSERT INTO `ville`(`NOM`, `POPULATION`, `IDDEPT`, `IDREGION`) VALUES ('?','?','?','?','?')");
			
			// Insere un nouveau fournisseur
			
			String nom = ville.getNom();
			String population = ville.getPopulation();
			String idDept =ville.getIdDepartement();
			String idRegion = ville.getIdRegion();
			stat.setString(2, nom);
			stat.setString(3, population);
			stat.setString(4, idDept);
			stat.setString(5, idRegion);
		
			int db =stat.executeUpdate();

			// indique le nombre d'entré effectué dans la bdd
			System.out.println("Vous avez inserer " + db + " nouvelles lignes");

			// fermeture du statement et de la connection
			stat.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
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
			Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307/recensement", "root", "");
			// Requête SQL
			System.out.println(connection);

			// Création du statement
			// Insere un nouveau fournisseur avec les informations passé en parametre 
			PreparedStatement stat=connection.prepareStatement("UPDATE `VILLE` SET `NOM`='?' WHERE NOM ='?'");
			stat.setString(1, nouveauNom);
			stat.setString(2, ancienNom);
			
			db=stat.executeUpdate();
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
	
	public boolean delete(Ville ville) {
		// Etape 1 : chargement du driver avec méthode registerDriver ou
		// classe.forName("...Driver")
		
		String id=ville.getId();
		boolean bool=false;
		
		try {
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException();
		}

		// Etape 2 : on créer une connection

		try {
			Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307/recensement", "root", "");
			// Requête SQL
			System.out.println(connection);

			// Création du statement
			PreparedStatement stat = connection.prepareStatement("DELETE FROM `Ville` WHERE ID = '?'");
			stat.setString(1, id);
			// Supprime le fournisseur dont les Nom est 'La Maison de la Peinture'
			int db =stat.executeUpdate();
			// indique le nombre d'entré effectué dans la bdd
			System.out.println("Vous avez supprimé " + db + " lignes");

			if (db>0) {
				bool=true;
			}
			// fermeture du statement et de la connection
			stat.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return bool;
	}
}
