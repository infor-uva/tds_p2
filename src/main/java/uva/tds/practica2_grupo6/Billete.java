package uva.tds.practica2_grupo6;

/**
 * Class dedicated for the representation of the ticket.
 * 
 * In this class you will can consult the locator, route, user and the state of
 * the ticket.
 * 
 * For compare two packs of tickets will be used the locator, for compare two
 * tickets will be used the locator and state
 * 
 * @author hugcubi
 * @author diebomb
 * @author migudel
 * 
 * @version 09/11/23
 */
public class Billete {

	/**
	 * @param localizador
	 * @param recorrido
	 * @param usuario
	 * @param estado
	 * @throws IllegalArgumentException si localizador tiene menos de 1 y mas de 8
	 *                                  caracteres
	 * @throws NullPointerException     si recorrido, usuario o estado son null
	 * @throws IllegalArgumentException si estado es diferente de "comprado" y
	 *                                  "reservado"
	 */
	public Billete(String localizador, Recorrido recorrido, Usuario usuario, String estado) {
	}

	/**
	 * Consult the locator of the ticket
	 * 
	 * @return localizador
	 */
	public String getLocalizador() {
		return null;
	}

	/**
	 * Consult the route of the ticket
	 * 
	 * @return localizador
	 */
	public Recorrido getRecorrido() {
		return null;
	}

	/**
	 * Consult the user of the ticket
	 * 
	 * @return localizador
	 */
	public Usuario getUsuario() {
		return null;
	}

	/**
	 * Consult the state of the ticket
	 * <ul>
	 * <li>Comprado</li>
	 * <li>Reservado</li>
	 * </ul>
	 * 
	 * @return localizador
	 */
	public String getEstado() {
		return null;
	}

	/**
	 * Set ticket as after booking
	 * 
	 * @throws IllegalStateException if the state of the ticket is not Booked
	 */
	public void setComprado() {
	}

	/**
	 * Compare if two tickets are the same
	 * 
	 * @param o ticket to compare
	 * 
	 * @return if are the same
	 * 
	 * @throws NullPointerException if o is null
	 */
	@Override
	public boolean equals(Object o) {
		return false;
	}
}
