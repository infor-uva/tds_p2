package uva.tds.practica2_grupo6;

/**
 * Class dedicated for the representation of the User.
 * 
 * In this class you can check the user's name and ID number.
 * 
 * For the instantiation of this class, review
 * {@link Usuario#Usuario(String, String)}
 * 
 * @author diebomb
 * @author migudel
 * @author hugcubi
 * 
 * @version 09/10/23
 */
public class Usuario {

	/**
	 * Constructor
	 * 
	 * @param nif
	 * @param nombre
	 * 
	 * @throws IllegalArgumentException if the NIF is empty
	 * @throws NullPointerException     if the NIF is null
	 * @throws IllegalArgumentException if the name is empty
	 * @throws NullPointerException     if the name is null
	 * @throws IllegalArgumentException if the number of characters in the name
	 *                                  exceeds 15
	 * @throws IllegalArgumentException if the number of NIF digits exceeds 8
	 * @throws IllegalArgumentException if the number of NIF digits is less than 8
	 * @throws IllegalArgumentException if the NIF does not end with a letter,
	 *                                  except {I,Ñ,O,U}
	 * @throws IllegalArgumentException if the NIF value does not correspond to the
	 *                                  letter
	 */
	public Usuario(String nif, String nombre) {

	}

	/**
	 * Consult the nif of the Usuario
	 * 
	 * @return nif
	 */
	public String getNif() {
		return null;
	}

	/**
	 * Consult the name of Usuario
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return null;
	}

	/**
	 * Compare if two Usuarios are the same
	 * 
	 * @param o Usuario to compare
	 * 
	 * @return if are the same
	 * 
	 * @throws NullPointerException if o is a null
	 */
	@Override
	public boolean equals(Object o) {
		return false;
	}
}
