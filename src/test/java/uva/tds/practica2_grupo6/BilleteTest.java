package uva.tds.practica2_grupo6;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BilleteTest {

	private static final String ESTADO_RESERVADO = "reservado";
	private static final String ESTADO_COMPRADO = "comprado";

	private String nif;
	private String nombre;
	private Usuario user;
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
	private String locator;
	private Billete ticket;

	@BeforeEach
	void setUp() {
		nif = "32698478E";
		nombre = "Geronimo";
		user = new Usuario(nif, nombre);
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
		locator = "c123";
		ticket = new Billete(locator, recorrido, user, ESTADO_RESERVADO);
	}

	@Test
	public void testLocalizadorValidoConEstadoComprado() {
		Billete billete = new Billete("ABC12345", recorrido, user, ESTADO_COMPRADO);
		assertEquals("ABC12345", billete.getLocalizador());
		assertEquals(user, billete.getUsuario());
		assertEquals(recorrido, billete.getRecorrido());
		assertEquals(ESTADO_COMPRADO, billete.getEstado());
	}

	@Test
	public void testLocalizadorValidoConEstadoReservado() {
		Billete billete = new Billete("ABC12345", recorrido, user, ESTADO_RESERVADO);
		assertEquals("ABC12345", billete.getLocalizador());
		assertEquals(user, billete.getUsuario());
		assertEquals(recorrido, billete.getRecorrido());
		assertEquals(ESTADO_RESERVADO, billete.getEstado());
	}

	@Test
	public void testLocalizadorInvalidoDemasiadoCorto() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Billete("", recorrido, user, ESTADO_COMPRADO);
		});

	}

	@Test
	public void testLocalizadorInvalidoDemasiadoLargo() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Billete("123456789", recorrido, user, ESTADO_COMPRADO);
		});

	}

	@Test
	public void testRecorridoValido() {
		Billete billete = new Billete("ABC12345", recorrido, user, ESTADO_COMPRADO);
		assertSame(recorrido, billete.getRecorrido());
	}

	@Test
	public void testUsuarioValido() {
		Billete billete = new Billete("ABC12345", recorrido, user, ESTADO_COMPRADO);
		assertSame(user, billete.getUsuario());
	}

	@Test
	public void testUsuarioNull() {
		assertThrows(NullPointerException.class, () -> {
			new Billete("1234567890", recorrido, null, ESTADO_COMPRADO);
		});

	}

	@Test
	public void testRecorridoNull() {
		assertThrows(NullPointerException.class, () -> {
			new Billete("1234567890", null, user, ESTADO_COMPRADO);
		});
	}

	@Test
	void testEstadoNull() {
		assertThrows(NullPointerException.class, () -> {
			new Billete("1234567", recorrido, user, null);
		});
	}

	@Test
	void testEstadoDiferenteDeCompradoYReservado() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Billete("1234567890", recorrido, user, "OtroEstado");
		});
	}

	@Test
	void testSetCompradoValido() {
		assertEquals(ESTADO_RESERVADO, ticket.getEstado());
		ticket.setComprado();
		assertEquals(ESTADO_COMPRADO, ticket.getEstado());
	}

	@Test
	void testSetCompradoConBilleteYaComprado() {
		Billete billete = new Billete("ABC12345", recorrido, user, ESTADO_COMPRADO);
		assertEquals(ESTADO_COMPRADO, billete.getEstado());
		assertThrows(IllegalStateException.class, () -> {
			billete.setComprado();
		});
	}

	@Test
	void testEqualsValido() {
		// Mismo paquete
		Billete b2 = new Billete(locator, recorrido, user, ESTADO_RESERVADO);
		assertTrue(ticket.equals(b2));

		b2.setComprado();
		assertFalse(ticket.equals(b2));

		assertFalse(ticket.equals(false));
	}

	@Test
	void testEqualsNull() {
		assertThrows(NullPointerException.class, () -> {
			ticket.equals(null);
		});
	}
}
