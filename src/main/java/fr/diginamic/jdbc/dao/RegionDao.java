package fr.diginamic.jdbc.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.mariadb.jdbc.Driver;

import fr.diginamic.jdbc.entites.Fournisseur;
import fr.diginamic.jdbc.entites.Region;

public class RegionDao {

	public List<Region> extraire() {
		// Etape 1 : chargement du driver avec méthode registerDriver ou
		// classe.forName("...Driver")
		ArrayList<Region> listeRegion = new ArrayList<Region>();

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
			PreparedStatement stat = connection.prepareStatement("SELECT * FROM REGION");
			// création du curseur contenant chaque ligne de la bdd
			ResultSet curseur = stat.executeQuery();

			// création d'une liste de fournisseur

			// pour chaque ligne de la bdd créer le fournisseur associé
			while (curseur.next()) {
				Integer id = curseur.getInt("ID");
				String nom = curseur.getString("NOM");
				listeRegion.add(new Region(id, nom));
			}

			// boucle parcourant la listeFournisseur et affichant chacun d'entre eux
			for (Region region : listeRegion) {
				System.out.println(region.toString());
			}

			// fermeture du Statement et de la connection
			stat.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return listeRegion;
	}
	
	
	public void insert(Region region) {
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
			PreparedStatement stat =connection.prepareStatement("INSERT INTO `REGION`(`ID`, `NOM`) VALUES (?,?)");
			
			// Insere un nouveau fournisseur
			
			int id=region.getId();
			String nom = region.getNom();
			stat.setInt(1, id);
			stat.setString(2, nom);
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
			PreparedStatement stat=connection.prepareStatement("UPDATE `REGION` SET `NOM`='?' WHERE NOM ='?'");
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
	
	public boolean delete(Region region) {
		// Etape 1 : chargement du driver avec méthode registerDriver ou
		// classe.forName("...Driver")
		
		int id=region.getId();
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
			PreparedStatement stat = connection.prepareStatement("DELETE FROM `REGION` WHERE ID = '?'");
			stat.setInt(1, id);
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
