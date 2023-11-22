package uva.tds.practica2_grupo6;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

/**
 * Class dedicated for the management of the different instances of
 * {@link Recorrido}, {@link Billete} and {@link Usuario}.
 * 
 * The management will be based on:
 * <ul>
 * <li>Add a Recorrido in to the system<br>
 * {@link System#addRecorrido(Recorrido)}</li>
 * <li>Remove a Recorrido of system<br>
 * {@link System#removeRecorrido(Recorrido)}</li>
 * <li>Consult the list of recorridos in system<br>
 * {@link System#getRecorridos()}</li> Consult the total price accumulated by a
 * user<br>
 * {@link System#getPrecioTotalBilletesUsuario(String)}</li>
 * <li>Consult the list of Recorridos that have a specific date<br>
 * {@link System#getRecorridosDisponiblesFecha(LocalDate)}</li>
 * <li>Consult the list of Billetes which are associated to the recorrido
 * route<br>
 * {@link System#getAssociatedBilletesToRoute(Recorrido)}</li>
 * <li>Consult the date of the route which have the id specified<br>
 * {@link System#getDateOfRecorrido(String)}</li>
 * <li>Consult the time of the route which have the id specified<br>
 * {@link System#getTimeOfRecorrido(String)}</li>
 * <li>Consult the date and time of the route which have the id specified<br>
 * {@link System#getDateTimeOfRecorrido(String)}</li>
 * <li>Update the date of a recorrido<br>
 * {@link System#updateRecorridoDate(Recorrido, LocalDate)}</li>
 * <li>Update the time of a recorrido<br>
 * {@link System#updateRecorridoTime(Recorrido, LocalTime)}</li>
 * <li>Update the time and date of a recorrido<br>
 * {@link System#updateRecorrido(Recorrido, LocalDate, LocalTime)}</li>
 * <li>Reserve tickets for a Recorrido and a Usuario<br>
 * {@link System#reservarBilletes(String, Usuario, Recorrido, int)}</li>
 * <li>Cancel a reservation<br>
 * {@link System#anularReserva(String, int)}</li>
 * <li>Return the tickets<br>
 * {@link System#devolverBilletes(String, int)}</li>
 * <li>Buy tickets for a Recorrido and a Usuario<br>
 * {@link System#comprarBilletes(String, Usuario, Recorrido, int)}</li>
 * <li>Buy reserved tickets (the previously reserved lot)<br>
 * {@link System#comprarBilletesReservados(String)}</li>
 * </ul>
 * 
 * @author hugcubi
 * @author diebomb
 * @author migudel
 * 
 * @version 09/11/23
 */
public class System {
	/**
	 * List of routes registered in the system
	 */
	private List<Recorrido> routes;
	
	/**
	 * List of tickets registered in the system
	 */
	private List<Billete> tickets;

	/**
	 * Instance the System
	 */
	public System() {
		routes = new ArrayList<>();
		tickets = new ArrayList<>();
	}

	/**
	 * Add a Recorrido in to the system
	 * 
	 * @param route to add
	 * 
	 * @throws IllegalArgumentException  if route is null
	 * @throws IllegalStateException if route is already in the system
	 */
	public void addRecorrido(Recorrido route) {
	}

	/**
	 * Remove a Recorrido of system
	 * 
	 * @param route to remove
	 * 
	 * @throws IllegalArgumentException  if route is null
	 * @throws IllegalStateException if route is not in the system
	 * @throws IllegalStateException if route has associated billetes
	 */
	public void removeRecorrido(String route) {
	}

	/**
	 * Consult the list of recorridos in system
	 * 
	 * @return list of recorridos in system
	 */
	public List<Recorrido> getRecorridos() {
		return null;
	}

	/**
	 * Returns the price of all the tickets associated with a user, if the tickets
	 * are valid. In addition, it should be checked whether the journey is by train
	 * or bus. If it is by train, the price of each ticket should have a 10%
	 * discount.
	 * 
	 * @param nif
	 * 
	 * @return total price
	 *
	 * @throws IllegalArgumentException     if the NIF is null.
	 * @throws IllegalArgumentException if the nif is empty
	 * 
	 * @throws IllegalArgumentException if the number of NIF digits exceeds 8
	 * @throws IllegalArgumentException if the number of NIF digits is less than 8
	 * @throws IllegalArgumentException if the NIF does not end with a letter,
	 *                                  except {I,Ñ,O,U}
	 * @throws IllegalArgumentException if the NIF value does not correspond to the
	 *                                  letter
	 * @throws IllegalStateException    if the nif holder isn't in the system.
	 * @throws IllegalStateException    if the nif holder not have associated
	 *                                  tickets.
	 */
	public double getPrecioTotalBilletesUsuario(String nif) {
		return -1.0;
	}

	/**
	 * Returns a list of available journeys for that date, both for buses and
	 * trains.
	 *
	 * @param fecha
	 * @return list of routes or an empty list if there are no routes for that date.
	 *
	 * @throws IllegalArgumentException  if the date is null.
	 * @throws IllegalStateException if the date does not have associated route.
	 */
	public List<Recorrido> getRecorridosDisponiblesFecha(LocalDate fecha) {
		return null;
	}

	/**
	 * Consult the list of Billetes which are associated to the recorrido route
	 * 
	 * @param route
	 * 
	 * @return list of Billetes
	 * 
	 * @throws IllegalArgumentException  if route is null
	 * @throws IllegalStateException if route isn't in the system
	 */
	public List<Billete> getAssociatedBilletesToRoute(String route) {
		return null;
	}

	/**
	 * Consult the date of the route which have the id specified
	 * 
	 * @param id of the route
	 * 
	 * @return the date of the route
	 * 
	 * @throws IllegalArgumentException     if id is null
	 * @throws IllegalArgumentException if id have less than 1 character
	 * @throws IllegalStateException    if there is no route in the system with that
	 *                                  id
	 */
	public LocalDate getDateOfRecorrido(String id) {
		return null;
	}

	/**
	 * Consult the time of the route which have the id specified
	 * 
	 * @param id of the route
	 * 
	 * @return the time of the route
	 * 
	 * @throws IllegalArgumentException     if id is null
	 * @throws IllegalArgumentException if id have less than 1 character
	 * @throws IllegalStateException    if there is no route in the system with that
	 *                                  id
	 */
	public LocalTime getTimeOfRecorrido(String id) {
		return null;
	}

	/**
	 * Consult the date and time of the route which have the id specified
	 * 
	 * @param id of the route
	 * 
	 * @return the DateTime of the route
	 * 
	 * @throws IllegalArgumentException     if id is null
	 * @throws IllegalArgumentException if id have less than 1 character
	 * @throws IllegalStateException    if there is no route in the system with that
	 *                                  id
	 */
	public LocalDateTime getDateTimeOfRecorrido(String id) {
		return null;
	}

	/**
	 * Update the date of a recorrido
	 * 
	 * @param route
	 * @param newDate
	 * 
	 * @throws IllegalArgumentException  if route is null
	 * @throws IllegalStateException if route is not in the system
	 * @throws IllegalArgumentException  if newDate is null
	 * @throws IllegalStateException if the new date is the already the set
	 */
	public void updateRecorridoDate(String route, LocalDate newDate) {
	}

	/**
	 * Update the time of a recorrido
	 * 
	 * @param route
	 * @param newTime
	 * 
	 * @throws IllegalArgumentException  if route is null
	 * @throws IllegalStateException if route is not in the system
	 * @throws IllegalArgumentException  if newTime is null
	 * @throws IllegalStateException if the new time is the already the set
	 */
	public void updateRecorridoTime(String route, LocalTime newTime) {
	}

	/**
	 * Update the time and date of a recorrido
	 * 
	 * @param route
	 * @param newDateTime
	 * 
	 * @throws IllegalArgumentException  if route is null
	 * @throws IllegalStateException if route is not in the system
	 * @throws IllegalArgumentException  if newDateTime is null
	 * @throws IllegalStateException if the new Date time is the already the set
	 */
	public void updateRecorridoDateTime(String route, LocalDateTime newDateTime) {
	}

	/**
	 * Update the time and date of a recorrido
	 * 
	 * @param route
	 * @param newDate
	 * @param newTime
	 * 
	 * @throws IllegalArgumentException  if route is null
	 * @throws IllegalStateException if route is not in the system
	 * @throws IllegalArgumentException  if newDate is null
	 * @throws IllegalArgumentException  if newTime is null
	 * @throws IllegalStateException if the new Date time is the already the set
	 */
	public void updateRecorrido(String route, LocalDate newDate, LocalTime newTime) {
	}

	/**
	 * Reserva un número de billetes para un recorrido
	 *
	 * @param localizador
	 * @param user
	 * @param recorrido
	 * @param numBilletesReservar
	 * 
	 * @return lot of reserved tickets
	 * 
	 * @throws IllegalArgumentException if the number of tickets exceeds the
	 *                                  available seat limit.
	 * @throws IllegalArgumentException if the number of available tickets is less
	 *                                  than half of the total.
	 * @throws IllegalArgumentException if the identifier is empty.
	 * @throws IllegalArgumentException if a locator that has been used previously
	 *                                  is passed.
	 * @throws IllegalStateException  	if the route doesn't exist
	 * @throws IllegalArgumentException     if user is null.
	 * @throws IllegalArgumentException     if recorrido is null.
	 * @throws IllegalArgumentException     if recorrido is null.
	 */
	public List<Billete> reservarBilletes(String localizador, Usuario user, Recorrido recorrido, int numBilletesReservar) {
		if(user == null)
			throw new IllegalArgumentException("El usuario no puede ser null");
		if(recorrido == null)
			throw new IllegalArgumentException("El recorrido no puede ser null");
		if(localizador == null)
			throw new IllegalArgumentException("El localizador no puede ser null");
		if (numBilletesReservar > recorrido.getNumAvailableSeats())
			throw new IllegalArgumentException("No se puede reservar si el número de billetes es mayor a los asientos disponibles");
		if (recorrido.getNumAvailableSeats() < recorrido.getTotalSeats())
			throw new IllegalArgumentException("No se puede reservar si el número de asientos disponibles es menor a la mitad del número total de asientos");
		if (localizador.equals(""))
			throw new IllegalArgumentException("El localizador no puede ser vacio");
		for (Billete billetes: this.tickets) {
			if (billetes.getLocalizador().equals(localizador))
				throw new IllegalArgumentException("Ese localizador ya ha sido utilizado");
		}
		int count = 0;
		for(Recorrido recorridos: this.routes) {
			if(recorrido.equals(recorridos))
				count += 1;
		}
		if (count == 0)
			throw new IllegalStateException("El recorrido no existe en el sistema");
		
		List<Billete> billetesReservados = new ArrayList<Billete>();
		for (int i = 0; i < numBilletesReservar; i++) {
			Billete billete = new Billete(localizador,recorrido, user, "reservado");
			billetesReservados.add(billete);
		}
		recorrido.decreaseAvailableSeats(numBilletesReservar);
		tickets.addAll(billetesReservados);
		return billetesReservados;
		
	}

	/**
	 * Cancel the reservation of a specific number of reserved tickets identified by
	 * the locator
	 * 
	 * @param localizador
	 * @param numBilletesAnular
	 * 
	 * @throws IllegalArgumentException     if the localizador is null
	 * @throws IllegalArgumentException if the localizador is empty
	 * @throws IllegalStateException    if the locator isn't in the system
	 * @throws IllegalStateException    if the locator belongs to tickets that are
	 *                                  not reserved.
	 * @throws IllegalArgumentException if the number of tickets is less or equal to
	 *                                  0
	 * @throws IllegalStateException    if the number of tickets is more than the
	 *                                  number of reserved tickets with that locator
	 */
	public void anularReserva(String localizador, int numBilletesAnular) {
		if(localizador == null)
			throw new IllegalArgumentException("El localizador no puede ser null");
		if (localizador.equals(""))
			throw new IllegalArgumentException("El localizador no puede ser vacio");
		if (numBilletesAnular < 1)
			throw new IllegalArgumentException("No se puede reservar si el número de billetes es menor que 1");	
		int counter = 0;
		for (Billete billetes: this.tickets) {
			if (billetes.getLocalizador().equals(localizador)) {
				counter += 1;
				if(!billetes.getEstado().equals("reservado"))
					throw new IllegalStateException("El localizador no corresponde con tickets reservados");
		}
		}
		if(counter == 0)
			throw new IllegalStateException("El localizador no corresponde con tickets reservados");
		
		int count = 0;
		for (Billete billetes: this.tickets) {
			if (billetes.getLocalizador().equals(localizador))
				count += 1;
		}
		if (count < numBilletesAnular)
				throw new IllegalStateException("Hay menos tickets de los que se quieren anular con ese localizador");
		
		int contador = 0;
		while (contador < numBilletesAnular) {
			for (Billete billetes: this.tickets) {
				if (billetes.getLocalizador().equals(localizador)) {					
					tickets.remove(billetes);
					contador += 1;
					break;
				}
			}
		}
	}

	/**
	 * Returns a specific number of tickets belonging to a lot identified by the
	 * locator
	 * 
	 * @param localizador         of the lot or tickets (it's the same)
	 * @param numBilletesDevolver
	 * 
	 * @throws IllegalArgumentException     if the localizador is null
	 * @throws IllegalArgumentException if the localizador is empty
	 * @throws IllegalStateException    if the locator isn't in the system
	 * @throws IllegalStateException    if the tickets to be returned have not been
	 *                                  purchased comprados
	 * @throws IllegalArgumentException if the number of tickets ir less or equal to
	 *                                  0
	 * @throws IllegalStateException    if the number of tickets is more than the
	 *                                  number of tickets with that locator
	 */
	public void devolverBilletes(String localizador, int numBilletesDevolver) {

	}

	/**
	 * Buy tickets for a trip.
	 *
	 * @param localizador the locator of the tickets
	 * @param usr         the user who is buying the tickets
	 * @param recorrido   the trip for which the tickets are being bought
	 * @param numBilletes the number of tickets to buy
	 * 
	 * @return lot of tickets bought
	 * 
	 * @throws IllegalArgumentException     if the localizador is null
	 * @throws IllegalArgumentException if the localizador is empty
	 * @throws IllegalArgumentException if the locator has already been used in
	 *                                  another ticket, either on the same route or
	 *                                  another route
	 * @throws IllegalArgumentException     if the usr is null
	 * @throws IllegalArgumentException     if the recorrido is null
	 * @throws IllegalArgumentException if the number of tickets is greater or less
	 *                                  than the limit of available seats per
	 *                                  vehicle
	 * @throws IllegalStateException    if the number of tickets is greater or less
	 *                                  than the limit of free seats
	 * @throws IllegalArgumentException if a previously used locator is passed
	 */
	public List<Billete> comprarBilletes(String localizador, Usuario usr, Recorrido recorrido, int numBilletes) {
		return null;
	}

	/**
	 * Buy reserved tickets (the previously reserved lot)
	 * 
	 * @param locator of the reserved lot of tickets
	 *
	 * @return lot of tickets bought
	 * 
	 * @throws IllegalArgumentException     if the localizador is null
	 * @throws IllegalArgumentException if locator is less than 1 character or more
	 *                                  than 8 characters
	 * @throws IllegalStateException    if there are no tickets booked in the system
	 *                                  with that locator
	 */
	public List<Billete> comprarBilletesReservados(String locator) {
		return null;
	}
}
















