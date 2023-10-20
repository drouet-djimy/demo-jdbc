package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.mariadb.jdbc.Driver;

public class TestConnectionJdbc {
	public static void main(String[] args) {

		// Etape 1 : chargement du drivert avec méthode registerDriver ou
		// classe.forName("...Driver")
		try {
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException();

		}

		// Etape 2 : on créer une connecion

		try {
			Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/compta", "root", "");

			// Requête SQL
			System.out.println(conn);

			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
