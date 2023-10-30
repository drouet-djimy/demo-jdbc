package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mariadb.jdbc.Driver;

import fr.diginamic.jdbc.entites.Departement;

public class DepartementDao {

	public List<Departement> extraire() {
		// Etape 1 : chargement du driver avec méthode registerDriver ou
		// classe.forName("...Driver")
		ArrayList<Departement> listeDepartement = new ArrayList<Departement>();

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
			PreparedStatement stat = connection.prepareStatement("SELECT * FROM DEPARTEMENT");
			// création du curseur contenant chaque ligne de la bdd
			ResultSet curseur = stat.executeQuery();

			// création d'une liste de fournisseur

			// pour chaque ligne de la bdd créer le fournisseur associé
			while (curseur.next()) {
				Integer id = curseur.getInt("ID");
				String code = curseur.getString("CODE");
				listeDepartement.add(new Departement(id, code));
			}

			// boucle parcourant la listeFournisseur et affichant chacun d'entre eux
			for (Departement departement : listeDepartement) {
				System.out.println(departement.toString());
			}

			// fermeture du Statement et de la connection
			stat.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return listeDepartement;
	}
	
	
	public void insert(Departement departement) {
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
			PreparedStatement stat =connection.prepareStatement("INSERT INTO `DEPARTEMENT`(`ID`, `NOM`) VALUES (?,?)");
			
			// Insere un nouveau fournisseur
			
			int id=departement.getId();
			String code = departement.getCode();
			stat.setInt(1, id);
			stat.setString(2, code);
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
	
	public int update(int ancienCode, int nouveauCode) {
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
			PreparedStatement stat=connection.prepareStatement("UPDATE `DEPARTEMENT` SET `CODE`='?' WHERE CODE ='?'");
			stat.setInt(1, nouveauCode);
			stat.setInt(2, ancienCode);
			
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
	
	public boolean delete(Departement departement) {
		// Etape 1 : chargement du driver avec méthode registerDriver ou
		// classe.forName("...Driver")
		
		int id=departement.getId();
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
			PreparedStatement stat = connection.prepareStatement("DELETE FROM `DEPARTEMENT` WHERE ID = '?'");
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
