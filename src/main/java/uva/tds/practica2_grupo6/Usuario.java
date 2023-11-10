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
	
	private String nif;
	private String nombre;

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
		if(nif == null) {
			throw new NullPointerException("Nif nulo\n");
		}
		if(nombre == null) {
			throw new NullPointerException("Nombre nulo\n");
		}
		if(nif.isEmpty()){
			throw new IllegalArgumentException("Nif vacio\n");
		}
		if(nombre.isEmpty()) {
			throw new IllegalArgumentException("Nombre vacio\n");
		}
		if(nombre.length()>15) {
			throw new IllegalArgumentException("Nombre demasiado largo\n");
		}
		if(nif.length()<=8) {
			throw new IllegalArgumentException("Nif demasiado corto\n");
		}
		if(nif.length()>9) {
			throw new IllegalArgumentException("Nif demasiado largo\n");
		}
		if(!Character.isLetter(nif.charAt(8))) {
			throw new IllegalArgumentException("Nif no contiene la letra\n");
		}
		if(nif.charAt(8) == 'P' || nif.charAt(8) == 'I' || nif.charAt(8) == 'O' || nif.charAt(8) == 'Ñ') {
			throw new IllegalArgumentException("Nif contiene la letra erronea\n");
		}
		this.nif=nif;
		this.nombre=nombre;
	}

	/**
	 * Consult the nif of the Usuario
	 * 
	 * @return nif
	 */
	public String getNif() {
		return this.nif;
	}

	/**
	 * Consult the name of Usuario
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return this.nombre;
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
