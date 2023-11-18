package uva.tds.practica2_grupo6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class made for tests the methods of {@link SistemaPersistencia}
 * 
 * @author hugcubi
 * @author diebomb
 * @author migudel
 * 
 * @version 17/11/23
 */
class SistemaPersistenciaTest {

	private static final double ERROR_MARGIN = 0.00001;
	private static final String BUS = "bus";
	private static final String TRAIN = "train";
	private static final String ESTADO_RESERVADO = "reservado";
	private static final String ESTADO_COMPRADO = "comprado";

	private String nif;
	private String nombre;
	private Usuario user;
	private Usuario differentUser;
	private String id;
	private String origin;
	private String destination;
	private String transport;
	private double price;
	private LocalDate date;
	private LocalTime time;
	private int duration;
	private Recorrido recorrido;
	private Recorrido differentRecorrido;
	private SistemaPersistencia sistema;
	private int numSeats;
	private LocalDateTime newDateTime;
	private LocalDate newDate;
	private LocalTime newTime;

	@BeforeEach
	void setUp() {
		nif = "32698478E";
		nombre = "Geronimo";
		user = new Usuario(nif, nombre);
		differentUser = new Usuario("79105889B", nombre);
		id = "c12345";
		origin = "Valladolid";
		destination = "Galicia";
		transport = BUS;
		date = LocalDate.of(2023, 10, 27);
		time = LocalTime.of(19, 06, 50);
		price = 1.0;
		numSeats = 20;
		duration = 30;
		recorrido = new Recorrido(id, origin, destination, transport, price, date, time, numSeats, duration);
		transport = TRAIN;
		differentRecorrido = new Recorrido("dif", origin, destination, transport, price, date, time, numSeats,
				duration);
		newDateTime = LocalDateTime.of(2023, 5, 14, 22, 56, 20);
		newDate = LocalDate.of(2024, 2, 4);
		newTime = LocalTime.of(12, 2, 4);

		sistema = new SistemaPersistencia();
	}

	/**
	 * FINDME Tests for {@link SistemaPersistencia#system()}
	 */
	@Test
	void testConstructor() {
		// TODO Completar tras fusión en develop
		// Asegurar que todo lo que se encarga de inicializar el constructor lo hace
		SistemaPersistencia sistema = new SistemaPersistencia();
		assertNotNull(sistema);
		assertNotNull(sistema.getRecorridos());
		assertEquals(0, sistema.getRecorridos().size());
	}

	/**
	 * FINDME Tests for {@link SistemaPersistencia#addRecorrido(Recorrido)}
	 */
	@Test
	void testAddRecorridoValido() {
		sistema.addRecorrido(recorrido);
		List<Recorrido> recorridos = sistema.getRecorridos();
		List<Recorrido> recorridosCheck = new ArrayList<>();
		recorridosCheck.add(recorrido);
		assertEquals(recorridosCheck, recorridos);
	}

	@Test
	void testAddRecorridoConRecorridoNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.addRecorrido(null);
		});
	}

	@Test
	void testAddRecorridoConRecorridoYaEnSystem() {
		sistema.addRecorrido(recorrido);
		assertEquals(recorrido, differentRecorrido);
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.addRecorrido(differentRecorrido);
		});
	}

	/**
	 * FINDME Tests for {@link System#removeRecorrido(String))}
	 */
	@Test
	void testRemoveRecorridoValido() {
		List<Recorrido> recorridos = new ArrayList<>();
		recorridos.add(recorrido);
		sistema.addRecorrido(recorrido);
		assertEquals(recorrido, sistema.getRecorridos());
		assertEquals(0, sistema.getAssociatedBilletesToRoute(id));
		sistema.removeRecorrido(id);
		recorridos.remove(0);
		assertEquals(recorrido, sistema.getRecorridos());
	}

	@Test
	void testRemoveRecorridoConRecorridoNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.removeRecorrido(null);
		});
	}

	@Test
	void testRemoveRecorridoConRecorridoFueraSystem() {
		assertThrows(IllegalStateException.class, () -> {
			sistema.removeRecorrido(id);
		});
	}

	@Test
	void testRemoveRecorridoConRecorridoEnSystemConBilletesAsociados() {
		Usuario usuario = new Usuario("32698478E", "Geronimo");
		sistema.addRecorrido(recorrido);

		sistema.comprarBilletes("T12345", usuario, recorrido, 1);
		List<Billete> billetes = new ArrayList<>();
		billetes.add(new Billete("T12345", recorrido, usuario, ESTADO_COMPRADO));
		assertEquals(billetes, sistema.getAssociatedBilletesToRoute(id));

		assertThrows(IllegalStateException.class, () -> {
			sistema.removeRecorrido(id);
		});
	}

	/**
	 * FINDME Tests for {@link SistemaPersistencia#getPrecioTotalBilletesUsuario(String)}
	 */
	@Test
	void testGetPrecioTotalBilletesUsuarioBus() {
		sistema.comprarBilletes("32698478E", user, recorrido, 5);
		assertEquals(5.0, sistema.getPrecioTotalBilletesUsuario("32698478E"), ERROR_MARGIN);
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioPrecioRecorridoTren() {
		sistema.comprarBilletes("32698478E", user, differentRecorrido, 5);
		assertEquals(4.5, sistema.getPrecioTotalBilletesUsuario("32698478E"), ERROR_MARGIN);
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioVacio() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.getPrecioTotalBilletesUsuario(null);
		});
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioSinBilletes() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.getPrecioTotalBilletesUsuario("79105889B");
		});
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioConNifLimiteInferiorDigitos() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.getPrecioTotalBilletesUsuario("3269847E");
		});
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioConNifLimiteSuperiorDigitos() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.getPrecioTotalBilletesUsuario("326984789E");
		});
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioConNifSinCaracter() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.getPrecioTotalBilletesUsuario("3269847");
		});
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioConNifInvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.getPrecioTotalBilletesUsuario("3269847P");
		});
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioConNifInvalidoLetraInvalidaI() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.getPrecioTotalBilletesUsuario("3269847I");
		});
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioConNifInvalidoLetraInvalidaO() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.getPrecioTotalBilletesUsuario("3269847O");
		});
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioConNifInvalidoLetraInvalidaÑ() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.getPrecioTotalBilletesUsuario("3269847Ñ");
		});
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioConNifInvalidoLetraInvalidaU() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.getPrecioTotalBilletesUsuario("3269847U");
		});
	}

	/**
	 * FINDME Tests for {@link SistemaPersistencia#getRecorridosDisponiblesFecha(LocalDate)}
	 */
	@Test
	void testGetRecorridosDisponiblesFecha() {
		ArrayList<Recorrido> recorridos = new ArrayList<>();
		recorridos.add(recorrido);
		recorridos.add(differentRecorrido);
		sistema.addRecorrido(recorrido);
		sistema.addRecorrido(differentRecorrido);
		assertEquals(recorridos, sistema.getRecorridosDisponiblesFecha(date));
	}

	@Test
	void testGetRecorridosDisponiblesFechaSinFecha() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.getRecorridosDisponiblesFecha(null);
		});
	}

	@Test
	void testGetRecorridosDisponiblesFechaSinRecorridos() {
		sistema.addRecorrido(recorrido);
		sistema.addRecorrido(differentRecorrido);
		LocalDate date1 = LocalDate.of(15, 10, 24);
		assertThrows(IllegalStateException.class, () -> {
			sistema.getRecorridosDisponiblesFecha(date1);
		});
	}

	/**
	 * FINDME Tests for {@link System#getAssociatedBilletesToRoute(String))}
	 */
	@Test
	void testGetAssociatedBilletesToRouteValido() {
		String localizator = "123";
		sistema.addRecorrido(recorrido);
		sistema.reservarBilletes(localizator, user, recorrido, 1);
		sistema.comprarBilletes(localizator, differentUser, recorrido, 2);

		List<Billete> expected = new ArrayList<>();
		expected.add(new Billete(localizator, recorrido, user, ESTADO_RESERVADO));
		expected.add(new Billete(localizator, recorrido, differentUser, ESTADO_COMPRADO));
		expected.add(new Billete(localizator, recorrido, differentUser, ESTADO_COMPRADO));

		assertEquals(expected, sistema.getAssociatedBilletesToRoute(id));
	}

	@Test
	void testGetAssociatedBilletesToRouteConRouteNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.getAssociatedBilletesToRoute(null);
		});
	}

	@Test
	void testGetAssociatedBilletesToRouteConRouteConRouteFueraDeSystem() {
		assertThrows(IllegalStateException.class, () -> {
			sistema.getAssociatedBilletesToRoute(id);
		});
	}

	/**
	 * FINDME Tests for {@link SistemaPersistencia#getDateOfRecorrido(String)}
	 */
	@Test
	void testGetDateOfRecorridoValidoConIDLimiteInferior() {
		String id = "1";
		Recorrido r = new Recorrido(id, origin, destination, transport, price, date, time, numSeats, duration);
		sistema.addRecorrido(r);
		assertEquals(r.getDate(), sistema.getDateOfRecorrido(id));
		fail(); // TODO Eliminar en fase verde
	}

	@Test
	void testGetDateOfRecorridoConIDNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.getDateOfRecorrido(null);
		});
	}

	@Test
	void testGetDateOfRecorridoNoValidoConIDLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.getDateOfRecorrido("");
		});
	}

	@Test
	void testGetDateOfRecorridoNoValidoConRecorridoFueraDeSystem() {
		assertThrows(IllegalStateException.class, () -> {
			sistema.getDateOfRecorrido(id);
		});
	}

	/**
	 * FINDME Tests for {@link SistemaPersistencia#getTimeOfRecorrido(String)}
	 */
	@Test
	void testGetTimeOfRecorridoValidoConIDLimiteInferior() {
		String id = "1";
		Recorrido r = new Recorrido(id, origin, destination, transport, price, date, time, numSeats, duration);
		sistema.addRecorrido(r);
		assertEquals(r.getTime(), sistema.getTimeOfRecorrido(id));
		fail(); // TODO Eliminar en fase verde
	}

	@Test
	void testGetTimeOfRecorridoNoValidoConIDNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.getTimeOfRecorrido(null);
		});
	}

	@Test
	void testGetTimeOfRecorridoNoValidoConIDLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.getTimeOfRecorrido("");
		});
	}

	@Test
	void testGetTimeOfRecorridoNoValidoConRecorridoFueraDeSystem() {
		assertThrows(IllegalStateException.class, () -> {
			sistema.getTimeOfRecorrido(id);
		});
	}

	/**
	 * FINDME Tests for {@link SistemaPersistencia#getDateTimeOfRecorrido(String)}
	 */
	@Test
	void testGetDateTimeOfRecorridoValidoConIDLimiteInferior() {
		String id = "1";
		Recorrido r = new Recorrido(id, origin, destination, transport, price, date, time, numSeats, duration);
		sistema.addRecorrido(r);
		assertEquals(r.getDateTime(), sistema.getDateTimeOfRecorrido(id));
		fail(); // TODO Eliminar en fase verde
	}

	@Test
	void testGetDateTimeOfRecorridoNoValidoConIDNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.getDateTimeOfRecorrido(null);
		});
	}

	@Test
	void testGetDateTimeOfRecorridoNoValidoConIDLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.getDateTimeOfRecorrido("");
		});
	}

	@Test
	void testGetDateTimeOfRecorridoNoValidoConRecorridoFueraDeSystem() {
		assertThrows(IllegalStateException.class, () -> {
			sistema.getDateTimeOfRecorrido(id);
		});
	}

	/**
	 * FINDME Tests for {@link SistemaPersistencia#updateRecorridoDate(String, LocalDate)}
	 */
	@Test
	void testUpdateRecorridoDateValido() {
		sistema.addRecorrido(recorrido);
		assertNotEquals(newDate, sistema.getDateOfRecorrido(recorrido.getID()));
		sistema.updateRecorridoDate(id, newDate);
		assertEquals(newDate, sistema.getDateOfRecorrido(recorrido.getID()));
	}

	@Test
	void testUpdateRecorridoDateConRecorridoNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.updateRecorridoDate(null, newDate);
		});
	}

	@Test
	void testUpdateRecorridoDateConRecorridoFueraDelsystem() {
		assertThrows(IllegalStateException.class, () -> {
			sistema.updateRecorridoDate(id, newDate);
		});
	}

	@Test
	void testUpdateRecorridoDateConDateNull() {
		sistema.addRecorrido(recorrido);
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.updateRecorridoDate(id, null);
		});
	}

	@Test
	void testUpdateRecorridoDateConDateAnterior() {
		sistema.addRecorrido(recorrido);
		assertThrows(IllegalStateException.class, () -> {
			sistema.updateRecorridoDate(id, recorrido.getDate());
		});
	}

	/**
	 * FINDME Tests for {@link SistemaPersistencia#updateRecorridoTime(String, LocalTime)}
	 */
	@Test
	void testUpdateRecorridoTimeValido() {
		sistema.addRecorrido(recorrido);
		assertNotEquals(newTime, sistema.getTimeOfRecorrido(recorrido.getID()));
		sistema.updateRecorridoTime(id, newTime);
		assertEquals(newTime, sistema.getTimeOfRecorrido(recorrido.getID()));
	}

	@Test
	void testUpdateRecorridoTimeConRecorridoNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.updateRecorridoTime(null, newTime);
		});
	}

	@Test
	void testUpdateRecorridoTimeConRecorridoFueraDelsystem() {
		assertThrows(IllegalStateException.class, () -> {
			sistema.updateRecorridoTime(id, newTime);
		});
	}

	@Test
	void testUpdateRecorridoTimeConTimeNull() {
		sistema.addRecorrido(recorrido);
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.updateRecorridoTime(id, null);
		});
	}

	@Test
	void testUpdateRecorridoTimeConTimeAnterior() {
		sistema.addRecorrido(recorrido);
		assertThrows(IllegalStateException.class, () -> {
			sistema.updateRecorridoTime(id, recorrido.getTime());
		});
	}

	/**
	 * FINDME Tests for
	 * {@link SistemaPersistencia#updateRecorridoDateTime(String, LocalDateTime)}
	 */
	@Test
	void testUpdateRecorridoDateTimeValido() {
		sistema.addRecorrido(recorrido);
		assertNotEquals(newDateTime, sistema.getDateTimeOfRecorrido(recorrido.getID()));
		sistema.updateRecorridoDateTime(id, newDateTime);
		assertEquals(newDateTime, sistema.getDateTimeOfRecorrido(recorrido.getID()));
	}

	@Test
	void testUpdateRecorridoDateTimeConRecorridoNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.updateRecorridoDateTime(null, newDateTime);
		});
	}

	@Test
	void testUpdateRecorridoDateTimeConRecorridoFueraDelsystem() {
		assertThrows(IllegalStateException.class, () -> {
			sistema.updateRecorridoDateTime(id, newDateTime);
		});
	}

	@Test
	void testUpdateRecorridoDateTimeConDateTimeNull() {
		sistema.addRecorrido(recorrido);
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.updateRecorridoDateTime(id, null);
		});
	}

	@Test
	void testUpdateRecorridoDateTimeConDateYTimeAnterior() {
		sistema.addRecorrido(recorrido);
		assertThrows(IllegalStateException.class, () -> {
			sistema.updateRecorridoDateTime(id, recorrido.getDateTime());
		});
	}

	/**
	 * FINDME Tests for
	 * {@link SistemaPersistencia#updateRecorrido(String, LocalDate, LocalTime)}
	 */
	@Test
	void testUpdateRecorridoValido() {
		sistema.addRecorrido(recorrido);
		assertNotEquals(newDateTime, sistema.getDateTimeOfRecorrido(recorrido.getID()));
		sistema.updateRecorrido(id, newDateTime.toLocalDate(), newDateTime.toLocalTime());
		assertEquals(newDateTime, sistema.getDateTimeOfRecorrido(recorrido.getID()));
	}

	@Test
	void testUpdateRecorridoConRecorridoNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.updateRecorrido(null, newDate, newTime);
		});
	}

	@Test
	void testUpdateRecorridoConRecorridoFueraDelsystem() {
		assertThrows(IllegalStateException.class, () -> {
			sistema.updateRecorrido(id, newDate, newTime);
		});
	}

	@Test
	void testUpdateRecorridoConDateNull() {
		sistema.addRecorrido(recorrido);
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.updateRecorrido(id, null, newTime);
		});
	}

	@Test
	void testUpdateRecorridoConTimeNull() {
		sistema.addRecorrido(recorrido);
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.updateRecorrido(id, newDate, null);
		});
	}

	@Test
	void testUpdateRecorridoConDateYTimeAnterior() {
		sistema.addRecorrido(recorrido);
		assertThrows(IllegalStateException.class, () -> {
			sistema.updateRecorrido(id, recorrido.getDate(), recorrido.getTime());
		});
	}

	/**
	 * FINDME Tests for
	 * {@link SistemaPersistencia#comprarBilletes(String, Usuario, Recorrido, int)}
	 */
	@Test
	void testComprarBilletesValidoBusLimiteInferior() {
		List<Billete> listaBilletes = sistema.comprarBilletes("ABC12345", user, recorrido, 1);
		List<Billete> listaBilletesComprobacion = new ArrayList<>();
		for (int i = 0; i < 1; i++) {
			Billete billeteComprobacion = new Billete("ABC12345", recorrido, user, ESTADO_COMPRADO);
			listaBilletesComprobacion.add(billeteComprobacion);
		}
		assertEquals(listaBilletes, listaBilletesComprobacion);
	}

	@Test
	void testComprarBilletesValidosBusLimiteSuperior() {
		List<Billete> listaBilletes = sistema.comprarBilletes("ABC12345", user, recorrido, 150);
		List<Billete> listaBilletesComprobacion = new ArrayList<>();
		for (int i = 0; i < 150; i++) {
			Billete billeteComprobacion = new Billete("ABC12345", recorrido, user, ESTADO_COMPRADO);
			listaBilletesComprobacion.add(billeteComprobacion);
		}
		assertEquals(listaBilletes, listaBilletesComprobacion);
	}

	@Test
	void testComprarBilletesValidoTrenLimiteInferior() {
		List<Billete> listaBilletes = sistema.comprarBilletes("ABC12345", user, differentRecorrido, 1);
		List<Billete> listaBilletesComprobacion = new ArrayList<>();
		for (int i = 0; i < 1; i++) {
			Billete billeteComprobacion = new Billete("ABC12345", differentRecorrido, user, ESTADO_COMPRADO);
			listaBilletesComprobacion.add(billeteComprobacion);
		}
		assertEquals(listaBilletes, listaBilletesComprobacion);
	}

	@Test
	void testComprarBilletesValidosTrenLimiteSuperior() {
		List<Billete> listaBilletes = sistema.comprarBilletes("ABC12345", user, differentRecorrido, 250);
		List<Billete> listaBilletesComprobacion = new ArrayList<>();
		for (int i = 0; i < 250; i++) {
			Billete billeteComprobacion = new Billete("ABC12345", differentRecorrido, user, ESTADO_COMPRADO);
			listaBilletesComprobacion.add(billeteComprobacion);
		}
		assertEquals(listaBilletes, listaBilletesComprobacion);
	}

	@Test
	void testComprarBilleteBusInvalidoLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.comprarBilletes("ABC12345", user, recorrido, 0);
		});
	}

	@Test
	void testComprarBilleteTrainInvalidoLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.comprarBilletes("ABC12345", user, differentRecorrido, 0);
		});
	}

	@Test
	void testComprarBilleteBusInvalidoLimiteSuperior() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.comprarBilletes("ABC12345", user, recorrido, 151);
		});
	}

	@Test
	void testComprarBilleteTrainInvalidoLimiteSuperior() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.comprarBilletes("ABC12345", user, differentRecorrido, 251);
		});
	}

	@Test
	void testComprarBilleteBusInvalidoLimiteSuperiorDemasiadaCompras() {
		sistema.comprarBilletes("ABC12345", user, recorrido, 149);
		assertThrows(IllegalStateException.class, () -> {
			sistema.comprarBilletes("ABC12345", differentUser, recorrido, 2);
		});
	}

	@Test
	void testComprarBilleteTrainInvalidoLimiteSuperiorDemasiadaCompras() {
		sistema.comprarBilletes("ABC12345", user, differentRecorrido, 249);
		assertThrows(IllegalStateException.class, () -> {
			sistema.comprarBilletes("ABC12345", differentUser, differentRecorrido, 2);
		});
	}

	@Test
	void testComprarBilleteLocalizadorInvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.comprarBilletes("", user, differentRecorrido, 1);
		});
	}

	@Test
	void testComprarBilleteLocalizadorNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.comprarBilletes(null, user, differentRecorrido, 1);
		});
	}

	@Test
	void testComprarBilleteLocalizadorYaUsadoMismoRecorrido() {
		sistema.comprarBilletes("ABC12345", user, differentRecorrido, 2);
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.comprarBilletes("ABC12345", differentUser, differentRecorrido, 1);
		});
	}

	@Test
	void testComprarBilleteLocalizadorYaUsadoDistinoRecorridoRecorrido() {
		sistema.comprarBilletes("ABC12345", user, differentRecorrido, 2);
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.comprarBilletes("ABC12345", differentUser, recorrido, 1);
		});
	}

	@Test
	void testComprarBilleteUsuarioNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.comprarBilletes("ABC12345", null, differentRecorrido, 1);
		});
	}

	@Test
	void testComprarBilleteRecorridoNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.comprarBilletes("ABC12345", user, null, 1);
		});
	}

	/**
	 * FINDME Tests for {@link SistemaPersistencia#comprarBilletesReservados(String)}
	 */
	@Test
	void testComprarBilletesReservadosValidoLimiteInferior() {
		String localizator = "1";
		sistema.addRecorrido(recorrido);
		List<Billete> bookedTicketsCheck = new ArrayList<>();
		// TODO Implementar en billete (Actualizar constructor billete)
		bookedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_RESERVADO));
		bookedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_RESERVADO));
		bookedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_RESERVADO));
		List<Billete> bookedTickets = sistema.reservarBilletes(localizator, user, recorrido, 3);
		assertEquals(bookedTicketsCheck, bookedTickets);

		List<Billete> buyedTicketsCheck = new ArrayList<>();
		buyedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_COMPRADO));
		buyedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_COMPRADO));
		buyedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_COMPRADO));
		List<Billete> buyedTickets = sistema.comprarBilletesReservados(localizator);
		assertEquals(buyedTicketsCheck, buyedTickets);

		assertNotEquals(bookedTickets, buyedTickets);
	}

	@Test
	void testComprarBilletesReservadosValidoLimiteSuperior() {
		String localizator = "12345678";
		sistema.addRecorrido(recorrido);
		List<Billete> bookedTicketsCheck = new ArrayList<>();
		bookedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_RESERVADO));
		bookedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_RESERVADO));
		bookedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_RESERVADO));
		List<Billete> bookedTickets = sistema.reservarBilletes(localizator, user, recorrido, 3);
		assertEquals(bookedTicketsCheck, bookedTickets);

		List<Billete> buyedTicketsCheck = new ArrayList<>();
		buyedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_COMPRADO));
		buyedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_COMPRADO));
		buyedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_COMPRADO));
		List<Billete> buyedTickets = sistema.comprarBilletesReservados(localizator);
		assertEquals(buyedTicketsCheck, buyedTickets);

		assertNotEquals(bookedTickets, buyedTickets);
	}

	@Test
	void testComprarBilletesReservadosConLocatorNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.comprarBilletesReservados(null);
		});
	}

	@Test
	void testComprarBilletesReservadosLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.comprarBilletesReservados("");
		});
	}

	@Test
	void testComprarBilletesReservadosLimiteSuperior() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.comprarBilletesReservados("123456789");
		});
	}

	@Test
	void testComprarBilletesReservadosSinBilletesReservadosEnSystemOConLocalizatorIncorrecto() {
		String localizator = "12345678";
		String localizator2 = "87654321";
		sistema.addRecorrido(recorrido);
		assertNotEquals(localizator, localizator2);
		assertThrows(IllegalStateException.class, () -> {
			sistema.comprarBilletesReservados(localizator2);
		});
	}

	/**
	 * FINDME Tests for
	 * {@link SistemaPersistencia#reservarBilletes(String, Usuario, Recorrido, int)}
	 */
	@Test
	void testReservarBilletesExitosamente() {
		int plazasDisponibles = recorrido.getNumAvailableSeats();
		int numBilletesReservar = 6;
		// Realiza la reserva de los billetes
		List<Billete> reservado = sistema.reservarBilletes("ABC12345", user, recorrido, numBilletesReservar);

		assertEquals(plazasDisponibles - numBilletesReservar, recorrido.getNumAvailableSeats());

		ArrayList<Billete> listaBilletesComprobacion = new ArrayList<>();
		for (int i = 0; i < numBilletesReservar; i++) {
			Billete billeteComprobacion = new Billete("ABC12345", recorrido, user, ESTADO_RESERVADO);
			listaBilletesComprobacion.add(billeteComprobacion);
		}
		assertEquals(reservado, listaBilletesComprobacion);
	}

	@Test
	void testReservarBilleteLocalizadorInvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.reservarBilletes("", user, recorrido, 1);
		});
	}

	@Test
	void testReservarBilleteLocalizadorNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.reservarBilletes(null, user, recorrido, 1);
		});
	}

	@Test
	void testReservarBilleteUsuarioNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.reservarBilletes("ABC12345", null, recorrido, 1);
		});
	}

	@Test
	void testReservarBilleteRecorridoNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.reservarBilletes("ABC12345", user, null, 1);
		});
	}

	@Test
	void testNoSePuedeReservarSiPlazasInsuficientes() {
		int plazasDisponibles = recorrido.getNumAvailableSeats();
		int numBilletesReservar = plazasDisponibles + 1;

		// Intenta reservar más billetes de los disponibles
		sistema.reservarBilletes("ABC12345", user, recorrido, numBilletesReservar);

		assertThrows(IllegalArgumentException.class, () -> {
			sistema.reservarBilletes("ABC12345", user, recorrido, numBilletesReservar);
		});

	}

	@Test
	void testNoSePuedeReservarSiPlazasMenoresQueLaMitad() {
		int numBilletesReservar = recorrido.getTotalSeats() / 2;

		// Intenta reservar más billetes de los disponibles
		sistema.reservarBilletes("ABC12345", user, recorrido, numBilletesReservar);

		assertThrows(IllegalArgumentException.class, () -> {
			sistema.reservarBilletes("ABC12345", user, recorrido, 2);
		});

	}

	/**
	 * FINDME Tests for {@link SistemaPersistencia#anularReserva(String, int)}
	 */
	@Test
	void testAnularReservaAumentaPlazasDisponiblesLimiteInferior() {
		int numBilletesReservar = 6;

		// Realiza la reserva de los billetes
		sistema.reservarBilletes("ABC12345", user, recorrido, numBilletesReservar);

		int plazasDisponiblesAntes = recorrido.getNumAvailableSeats();
		int numBilletesAnular = 1;

		// Realiza la anulación de la reserva
		sistema.anularReserva("ABC12345", numBilletesAnular);

		int plazasDisponiblesDespues = recorrido.getNumAvailableSeats();

		// Verifica que las plazas disponibles aumenten en la cantidad correcta
		assertEquals(plazasDisponiblesAntes + numBilletesAnular, plazasDisponiblesDespues);
	}

	@Test
	void testNoSePuedeAnularReservaSiLocalizadorNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.anularReserva(null, 1);
		});
	}

	@Test
	void testNoSePuedeAnularReservaSiLocalizadorVacío() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.anularReserva("", 1);
		});
	}

	@Test
	void testNoSePuedeAnularReservaSiFueraSistema() {
		int numBilletesAnular = 2;
		// Intenta anular la reserva sin reservar previamente
		assertThrows(IllegalStateException.class, () -> {
			sistema.anularReserva("ABC12345", numBilletesAnular);
		});
	}

	@Test
	void testNoSePuedeAnularReservaSiLocalizadorLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.anularReserva("ABCD12345", 0);
		});
	}

	@Test
	void testNoSePuedeAnularReservaSiLocalizadorPerteneceABilletesComprados() {
		String locator = "ABC12345";
		sistema.comprarBilletes(locator, user, recorrido, 3);
		assertThrows(IllegalStateException.class, () -> {
			sistema.anularReserva(locator, 2);
		});
	}

	@Test
	void testNoSePuedeAnularReservaSiMayorNumeroDeAnulacionesQueBilletesReservados() {
		String locator = "ABC12345";
		int numBilletesAnular = 3;
		sistema.reservarBilletes(locator, user, recorrido, 1);
		assertThrows(IllegalStateException.class, () -> {
			sistema.anularReserva(locator, numBilletesAnular);
		});
	}

	/**
	 * FINDME Tests for {@link SistemaPersistencia#devolverBilletes(String, int)}
	 */
	@Test
	void testDevolverBilletesAumentaPlazasDisponiblesLimiteInferior() {
		int numBilletesComprar = 6;

		// Realiza la reserva de los billetes
		sistema.comprarBilletes("ABC12345", user, recorrido, numBilletesComprar);
		int plazasDisponiblesAntes = recorrido.getNumAvailableSeats();
		int numBilletesDevolver = 1;

		// Realiza la anulación de la reserva
		sistema.devolverBilletes("ABC12345", numBilletesDevolver);

		int plazasDisponiblesDespues = recorrido.getNumAvailableSeats();

		// Verifica que las plazas disponibles aumenten en la cantidad correcta
		assertEquals(plazasDisponiblesAntes + numBilletesDevolver, plazasDisponiblesDespues);
	}

	@Test
	void testNoSePuedeDevolverBilletesSiLocalizadorNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.devolverBilletes(null, 1);
		});
	}

	@Test
	void testNoSePuedeDevolverBilletesSiLocalizadorVacío() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.devolverBilletes("", 1);
		});
	}

	@Test
	void testNoSePuedeDevolverBilletesSiLocalizadorLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () -> {
			sistema.devolverBilletes("ABCD12345", 0);
		});
	}

	@Test
	void testNoSePuedeDevolverBilleteFueraSistema() {
		int numBilletesDevolver = 2;
		// Intenta anular la reserva sin reservar previamente
		assertThrows(IllegalStateException.class, () -> {
			sistema.devolverBilletes("ABC12345", numBilletesDevolver);
		});
	}

	@Test
	void testNoSePuedeDevolverBilleteSiReservados() {
		String locator = "ABC12345";
		int numBilletesDevolver = 2;
		sistema.reservarBilletes(locator, user, recorrido, 5);

		assertThrows(IllegalStateException.class, () -> {
			sistema.devolverBilletes(locator, numBilletesDevolver);
		});
	}

	@Test
	void testNoSePuedeDevolverBilletesConNumLimiteInferior() {
		String locator = "ABC12345";
		sistema.comprarBilletes(locator, user, recorrido, 1);
		assertThrows(IllegalStateException.class, () -> {
			sistema.devolverBilletes(locator, 0);
		});
	}

	@Test
	void testNoSePuedeDevolverBilletesSiMayorNumeroDeDevolucionesQueBilletesComprados() {
		String locator = "ABC12345";
		int numBilletesDevolver = 3;
		sistema.comprarBilletes(locator, user, recorrido, 1);
		assertThrows(IllegalStateException.class, () -> {
			sistema.devolverBilletes(locator, numBilletesDevolver);
		});
	}
}
