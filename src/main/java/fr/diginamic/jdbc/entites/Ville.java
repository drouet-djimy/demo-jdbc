package fr.diginamic.jdbc.entites;

public class Ville {
	String id;
	String nom;
	String population;
	String idDepartement;
	String idRegion;
	
	
	/**Constructor
	 * @param id
	 * @param nom
	 * @param population
	 * @param idDepartement
	 * @param idRegion
	 */
	public Ville(String nom, String population, String idDepartement, String idRegion) {
		super();
		this.nom = nom;
		this.population = population;
		this.idDepartement = idDepartement;
		this.idRegion = idRegion;
	}
	
	
	/**Getter
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**Setter
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**Getter
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**Setter
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**Getter
	 * @return the population
	 */
	public String getPopulation() {
		return population;
	}
	/**Setter
	 * @param population the population to set
	 */
	public void setPopulation(String population) {
		this.population = population;
	}
	/**Getter
	 * @return the idDepartement
	 */
	public String getIdDepartement() {
		return idDepartement;
	}
	/**Setter
	 * @param idDepartement the idDepartement to set
	 */
	public void setIdDepartement(String idDepartement) {
		this.idDepartement = idDepartement;
	}
	/**Getter
	 * @return the idRegion
	 */
	public String getIdRegion() {
		return idRegion;
	}
	/**Setter
	 * @param idRegion the idRegion to set
	 */
	public void setIdRegion(String idRegion) {
		this.idRegion = idRegion;
	}
	@Override
	public String toString() {
		return "Ville [id=" + id + ", nom=" + nom + ", population=" + population + ", idDepartement=" + idDepartement
				+ ", idRegion=" + idRegion + "]";
	}
	
	
}
