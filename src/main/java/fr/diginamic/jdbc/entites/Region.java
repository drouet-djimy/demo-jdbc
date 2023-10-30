package fr.diginamic.jdbc.entites;

public class Region {
int id;
String nom;
/**Constructor
 * @param id
 * @param nom
 */
public Region(int id, String nom) {
	super();
	this.id = id;
	this.nom = nom;
}
/**Getter
 * @return the id
 */
public int getId() {
	return id;
}
/**Setter
 * @param id the id to set
 */
public void setId(int id) {
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
@Override
public String toString() {
	return "Region [id=" + id + ", nom=" + nom + "]";
}


}
