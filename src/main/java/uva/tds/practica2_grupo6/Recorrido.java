package uva.tds.practica2_grupo6;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Class dedicated for the representation of the route.
 * 
 * In this class you will can consult the identification of the route, the
 * origin and destination of the travel, in which method of transport it will
 * be, the price of the travel, the date and time of the travel, the number of
 * seats which are available and the duration of the travel.
 * 
 * The ID of the route will be used to compare the routes
 * 
 * @author diebomb
 * @author hugcubi
 * @author migudel
 * 
 * @version 09/11/23
 */
public class Recorrido {

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param origin
	 * @param destination
	 * @param transport
	 * @param price
	 * @param date
	 * @param time
	 * @param numSeats
	 * @param duration    in minutes
	 * 
	 * @throws NullPointerException     if id is null
	 * @throws IllegalArgumentException if id have less than 1 character
	 * @throws NullPointerException     if origin is null
	 * @throws IllegalArgumentException if origin have less than 1 character
	 * @throws NullPointerException     if destination is null
	 * @throws IllegalArgumentException if destination have less than 1 character
	 * @throws IllegalArgumentException if origin and destination are the same
	 * @throws NullPointerException     if transport is null
	 * @throws IllegalArgumentException if transport is not a bus or train
	 * @throws IllegalArgumentException if price is less than 0
	 * @throws NullPointerException     if date is null
	 * @throws NullPointerException     if time is null
	 * @throws IllegalArgumentException if numSeats is less than 1 or more than 50
	 *                                  in the case of bus or more than 250 in the
	 *                                  case of train
	 * @throws IllegalArgumentException if duration is less or equal than 0
	 */
	public Recorrido(String id, String origin, String destination, String transport, double price, LocalDate date,
			LocalTime time, int numSeats, int duration) {
	}

	/**
	 * Consult the id of the Recorrido
	 * 
	 * @return id
	 */
	public String getID() {
		return null;
	}

	/**
	 * Consult the origin of the Recorrido
	 * 
	 * @return origin
	 */
	public String getOrigin() {
		return null;
	}

	/**
	 * Consult the destination of the Recorrido
	 * 
	 * @return destination
	 */
	public String getDestination() {
		return null;
	}

	/**
	 * Consult the transport of the Recorrido
	 * 
	 * @return transport
	 */
	public String getTransport() {
		return null;
	}

	/**
	 * Consult the price of the Recorrido
	 * 
	 * @return price
	 */
	public double getPrice() {
		return -0.1;
	}

	/**
	 * Consult the date of the Recorrido
	 * 
	 * @return date
	 */
	public LocalDate getDate() {
		return null;
	}

	/**
	 * Consult the time of the Recorrido
	 * 
	 * @return time
	 */
	public LocalTime getTime() {
		return null;
	}

	/**
	 * Consult the Date time of the recorrido
	 * 
	 * @return date time of recorrido
	 */
	public LocalDateTime getDateTime() {
		return null;
	}

	/**
	 * Consult the number of available seats
	 * 
	 * @return numAvailableSeats
	 */
	public int getNumAvailableSeats() {
		return 0;
	}

	/**
	 * Consult the number of the whole transport seats
	 * 
	 * @return the
	 */
	public int getTotalSeats() {
		return 0;
	}

	/**
	 * Consult the duration of the Recorrido
	 * 
	 * @return duration
	 */
	public int getDuration() {
		return 0;
	}

	/**
	 * Update the date of a recorrido
	 * 
	 * @param newDate
	 * 
	 * @throws NullPointerException  if newDate is null
	 * @throws IllegalStateException if the new date is the already the set
	 */
	public void updateDate(LocalDate newDate) {
	}

	/**
	 * Update the time of the recorrido
	 * 
	 * @param newTime
	 * 
	 * @throws NullPointerException  if newTime is null
	 * @throws IllegalStateException if the new time is the already the set
	 */
	public void updateTime(LocalTime newTime) {
	}

	/**
	 * Update the date and time of the recorrido
	 * 
	 * @param newDateTime
	 * 
	 * @throws NullPointerException  if newDateTime is null
	 * @throws IllegalStateException if the new date and time is the already the set
	 */
	public void updateDateTime(LocalDateTime newDateTime) {
	}

	/**
	 * Update the date and time of the recorrido
	 * 
	 * @param newDate
	 * @param newTime
	 * 
	 * @throws NullPointerException  if newDate is null
	 * @throws NullPointerException  if newTime is null
	 * @throws IllegalStateException if the new date and time is the already the set
	 */
	public void updateDateTime(LocalDate newDate, LocalTime newTime) {
	}

	/**
	 * Compare if two Recorridos are the same
	 * 
	 * @param o Recorrido to compare
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
