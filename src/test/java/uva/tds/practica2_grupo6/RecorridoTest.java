package uva.tds.practica2_grupo6;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class dedicated to execute the tests for the methods of the instances of
 * {@link Recorrido}. In this case the constructor
 * 
 * @author diebomb
 * @author hugcubi
 * @author migudel
 * @version 29/10/23 11:41
 */
class RecorridoTest {
	/**
	 * Error margin for the tests which implicates operations with float and/ or
	 * double values
	 */
	private static final double ERROR_MARGIN = 0.00001;
	/**
	 * Type of transport bus
	 */
	private static final String BUS = "bus";
	/**
	 * Type of transport train
	 */
	private static final String TRAIN = "train";

	private String id;
	private String origin;
	private String destination;
	private String transport;
	private double price;
	private LocalDate date;
	private LocalTime time;
	private int numAvailableSeats;
	private int duration;
	private Recorrido recorrido;
	private Recorrido sameRecorrido;
	private Recorrido differentRecorrido;
	private LocalDate newDate;
	private LocalTime newTime;
	private LocalDateTime newDateTime;

	@BeforeEach
	void setUp() {
		id = "c12345";
		origin = "Valladolid";
		destination = "Palencia";
		transport = "bus";
		price = 0.0;
		date = LocalDate.of(2023, 10, 27);
		time = LocalTime.of(19, 06, 50);
		numAvailableSeats = 20;
		duration = 30;
		recorrido = new Recorrido(id, origin, destination, transport, price, date, time, numAvailableSeats, duration);
		sameRecorrido = new Recorrido(id, origin, destination, transport, price, date, time, numAvailableSeats,
				duration);
		differentRecorrido = new Recorrido("dif", destination, origin, transport, price, date, time, numAvailableSeats,
				duration);
		newDate = LocalDate.of(2025, 12, 30);
		newTime = LocalTime.of(16, 20, 37);
		newDateTime = LocalDateTime.of(2030, 5, 6, 19, 15, 13);
	}

	@Test
	void testConstructorValidoLimiteInferior() {
		String id = "c";
		String origin = "V";
		String destination = "P";
		String transport = BUS;
		double price = 0.0;
		int numAvailableSeats = 1;
		int duration = 1;

		Recorrido recorrido = new Recorrido(id, origin, destination, transport, price, date, time, numAvailableSeats,
				duration);
		assertNotNull(recorrido);
		assertEquals(id, recorrido.getID());
		assertEquals(origin, recorrido.getOrigin());
		assertEquals(destination, recorrido.getDestination());
		assertEquals(transport, recorrido.getTransport());
		assertEquals(price, recorrido.getPrice(), ERROR_MARGIN);
		assertEquals(date, recorrido.getDate());
		assertEquals(time, recorrido.getTime());
		assertEquals(numAvailableSeats, recorrido.getNumAvailableSeats());
		assertEquals(duration, recorrido.getDuration());

		transport = TRAIN;

		recorrido = new Recorrido(id, origin, destination, transport, price, date, time, numAvailableSeats, duration);
		assertNotNull(recorrido);
		assertEquals(id, recorrido.getID());
		assertEquals(origin, recorrido.getOrigin());
		assertEquals(destination, recorrido.getDestination());
		assertEquals(transport, recorrido.getTransport());
		assertEquals(price, recorrido.getPrice(), ERROR_MARGIN);
		assertEquals(date, recorrido.getDate());
		assertEquals(time, recorrido.getTime());
		assertEquals(numAvailableSeats, recorrido.getNumAvailableSeats());
		assertEquals(duration, recorrido.getDuration());
	}

	@Test
	void testConstructorValidoLimiteSuperior() {
		String id = "c123";
		String origin = "Valladolid";
		String destination = "Palencia";
		String transport = BUS;
		double price = 25.50;
		int numAvailableSeats = 50;
		int duration = 120;

		Recorrido recorrido = new Recorrido(id, origin, destination, transport, price, date, time, numAvailableSeats,
				duration);
		assertNotNull(recorrido);
		assertEquals(id, recorrido.getID());
		assertEquals(origin, recorrido.getOrigin());
		assertEquals(destination, recorrido.getDestination());
		assertEquals(transport, recorrido.getTransport());
		assertEquals(price, recorrido.getPrice(), ERROR_MARGIN);
		assertEquals(date, recorrido.getDate());
		assertEquals(time, recorrido.getTime());
		assertEquals(numAvailableSeats, recorrido.getNumAvailableSeats());
		assertEquals(duration, recorrido.getDuration());

		transport = TRAIN;
		numAvailableSeats = 250;

		recorrido = new Recorrido(id, origin, destination, transport, price, date, time, numAvailableSeats, duration);
		assertNotNull(recorrido);
		assertEquals(id, recorrido.getID());
		assertEquals(origin, recorrido.getOrigin());
		assertEquals(destination, recorrido.getDestination());
		assertEquals(transport, recorrido.getTransport());
		assertEquals(price, recorrido.getPrice(), ERROR_MARGIN);
		assertEquals(date, recorrido.getDate());
		assertEquals(time, recorrido.getTime());
		assertEquals(numAvailableSeats, recorrido.getNumAvailableSeats());
		assertEquals(duration, recorrido.getDuration());
	}

	@Test
	void testConstructorNoValidoConIdNull() {
		assertThrows(NullPointerException.class, () -> {
			new Recorrido(null, origin, destination, transport, price, date, time, numAvailableSeats, duration);
		});
	}

	@Test
	void testConstructorNoValidoConIdLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Recorrido("", origin, destination, transport, price, date, time, numAvailableSeats, duration);
		});
	}

	@Test
	void testConstructorNoValidoConOriginNull() {
		assertThrows(NullPointerException.class, () -> {
			new Recorrido(id, null, destination, transport, price, date, time, numAvailableSeats, duration);
		});
	}

	@Test
	void testConstructorNoValidoConOriginLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Recorrido(id, "", destination, transport, price, date, time, numAvailableSeats, duration);
		});
	}

	@Test
	void testConstructorNoValidoConDestinationNull() {
		assertThrows(NullPointerException.class, () -> {
			new Recorrido(id, origin, null, transport, price, date, time, numAvailableSeats, duration);
		});
	}

	@Test
	void testConstructorNoValidoConDestinationLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Recorrido(id, origin, "", transport, price, date, time, numAvailableSeats, duration);
		});
	}

	@Test
	void testConstructorNoValidoConOrigenYDestinoIguales() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Recorrido(id, origin, origin, transport, price, date, time, numAvailableSeats, duration);
		});
	}

	@Test
	void testConstructorNoValidoConTransportNull() {
		assertThrows(NullPointerException.class, () -> {
			new Recorrido(id, origin, destination, null, price, date, time, numAvailableSeats, duration);
		});
	}

	@Test
	void testConstructorNoValidoConTransportValorDiferente() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Recorrido(id, origin, destination, "boat", price, date, time, numAvailableSeats, duration);
		});
	}

	@Test
	void testConstructorNoValidoConPriceLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Recorrido(id, origin, destination, transport, -0.1, date, time, numAvailableSeats, duration);
		});
	}

	@Test
	void testConstructorNoValidoConDateNull() {
		assertThrows(NullPointerException.class, () -> {
			new Recorrido(id, origin, destination, transport, price, null, time, numAvailableSeats, duration);
		});
	}

	@Test
	void testConstructorNoValidoConTimeNull() {
		assertThrows(NullPointerException.class, () -> {
			new Recorrido(id, origin, destination, transport, price, date, null, numAvailableSeats, duration);
		});
	}

	@Test
	void testConstructorNoValidoConNumAvailableSeatsLimiteInferiorTipoBus() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Recorrido(id, origin, destination, BUS, price, date, time, 0, duration);
		});
	}

	@Test
	void testConstructorNoValidoConNumAvailableSeatsLimiteSuperiorTipoBus() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Recorrido(id, origin, destination, BUS, price, date, time, 51, duration);
		});
	}

	@Test
	void testConstructorNoValidoConNumAvailableSeatsLimiteInferiorTipoTrain() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Recorrido(id, origin, destination, TRAIN, price, date, time, 0, duration);
		});
	}

	@Test
	void testConstructorNoValidoConNumAvailableSeatsLimiteSuperiorTipoTrain() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Recorrido(id, origin, destination, TRAIN, price, date, time, 251, duration);
		});
	}

	@Test
	void testConstructorNoValidoConDurationLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Recorrido(id, origin, destination, transport, price, date, time, numAvailableSeats, 0);
		});
	}

	@Test
	void testUpdateValidos() {
		assertNotEquals(newDate, recorrido.getDate());
		recorrido.updateDate(newDate);
		assertEquals(newDate, recorrido.getDate());

		assertNotEquals(newTime, recorrido.getTime());
		recorrido.updateTime(newTime);
		assertEquals(newTime, recorrido.getTime());

		assertNotEquals(newDateTime, recorrido.getDateTime());
		recorrido.updateDateTime(newDateTime);
		assertEquals(newDateTime, recorrido.getDateTime());

		assertNotEquals(newDate, recorrido.getDate());
		assertNotEquals(newTime, recorrido.getTime());
		recorrido.updateDateTime(newDate, newTime);
		assertEquals(newDate, recorrido.getDate());
		assertEquals(newTime, recorrido.getTime());
	}

	@Test
	void testUpdateDateConDateNull() {
		assertThrows(NullPointerException.class, () -> {
			recorrido.updateDate(null);
		});
	}

	@Test
	void testUpdateDateConDateActual() {
		assertThrows(IllegalStateException.class, () -> {
			recorrido.updateDate(recorrido.getDate());
		});
	}

	@Test
	void testUpdateTimeConTimeNull() {
		assertThrows(NullPointerException.class, () -> {
			recorrido.updateTime(null);
		});
	}

	@Test
	void testUpdateTimeConTimeActual() {
		assertThrows(IllegalStateException.class, () -> {
			recorrido.updateTime(recorrido.getTime());
		});
	}

	@Test
	void testUpdateDateTimeConDateNull() {
		assertThrows(NullPointerException.class, () -> {
			recorrido.updateDateTime(null, newTime);
		});
	}

	@Test
	void testUpdateDateTimeConTimeNull() {
		assertThrows(NullPointerException.class, () -> {
			recorrido.updateDateTime(newDate, null);
		});
	}

	@Test
	void testUpdateDateTimeConDateYTimeActual() {
		assertThrows(IllegalStateException.class, () -> {
			recorrido.updateDateTime(recorrido.getDate(), recorrido.getTime());
		});
	}

	@Test
	void testUpdateDateTimeConDateTimeNull() {
		assertThrows(NullPointerException.class, () -> {
			recorrido.updateDateTime(null);
		});
	}

	@Test
	void testUpdateDateTimeConDateTimeActual() {
		// TODO Especificar que fue modificado, cambio de null a state
		assertThrows(IllegalArgumentException.class, () -> {
			recorrido.updateDateTime(recorrido.getDateTime());
		});
	}

	@Test
	void testEqualsValido() {
		assertEquals(recorrido.getID(), sameRecorrido.getID());
		assertNotEquals(recorrido.getID(), differentRecorrido.getID());
		assertTrue(recorrido.equals(recorrido));
		assertTrue(recorrido.equals(sameRecorrido));
		assertFalse(recorrido.equals(true));
		assertFalse(recorrido.equals(differentRecorrido));
	}

	@Test
	void testEqualsNoValidoCon() {
		assertThrows(NullPointerException.class, () -> {
			recorrido.equals(null);
		});
	}
}
