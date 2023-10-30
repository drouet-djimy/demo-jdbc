package fr.diginamic.jdbc.entites;

public class Departement {
	int id;
	String code;
	/**Constructor
	 * @param id
	 * @param code
	 */
	public Departement(int id, String code) {
		super();
		this.id = id;
		this.code = code;
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**Setter
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return "Departement [id=" + id + ", code=" + code + "]";
	}
	
	
}
