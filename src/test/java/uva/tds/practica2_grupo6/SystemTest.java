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
 * Class made for tests the methods of {@link System}
 * 
 * @author hugcubi
 * @author diebomb
 * @author migudel
 * 
 * @version 18/11/23
 */
class SystemTest {

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
	private System system;
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

		system = new System();
	}

	/**
	 * FINDME Tests for {@link System#system()}
	 */
	@Test
	void testConstructor() {
		// TODO Completar tras fusión en develop
		// Asegurar que todo lo que se encarga de inicializar el constructor lo hace
		System system = new System();
		assertNotNull(system);
		assertEquals(new ArrayList<>(), system.getRecorridos());
		assertEquals(new ArrayList<>(), system.getBilletes());
	}

	/**
	 * FINDME Tests for {@link System#addRecorrido(Recorrido)}
	 */
	@Test
	void testAddRecorridoValido() {
		system.addRecorrido(recorrido);
		List<Recorrido> recorridos = system.getRecorridos();
		List<Recorrido> recorridosCheck = new ArrayList<>();
		recorridosCheck.add(recorrido);
		assertEquals(recorridosCheck, recorridos);
	}
	
	@Test
	void testAddRecorridoConRecorridoNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.addRecorrido(null);
		});
	}
	
	@Test
	void testAddRecorridoConRecorridoYaEnSystem() {
		Recorrido otherRecorrido = new Recorrido(id, destination, origin, transport, price, date, newTime, numSeats, duration);
		system.addRecorrido(recorrido);
		assertEquals(recorrido, otherRecorrido);
		assertThrows(IllegalStateException.class, () -> {
			system.addRecorrido(otherRecorrido);
		});
	}

	/**
	 * FINDME Tests for {@link System#removeRecorrido(String))}
	 */
	@Test
	void testRemoveRecorridoValido() {
		List<Recorrido> recorridos = new ArrayList<>();
		recorridos.add(recorrido);
		system.addRecorrido(recorrido);
		assertEquals(recorridos, system.getRecorridos());
		assertEquals(new ArrayList<>(), system.getAssociatedBilletesToRoute(id));
		system.removeRecorrido(id);
		recorridos.remove(0);
		assertEquals(recorridos, system.getRecorridos());
	}

	@Test
	void testRemoveRecorridoConRecorridoNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.removeRecorrido(null);
		});
	}

	@Test
	void testRemoveRecorridoConRecorridoFueraSystem() {
		assertThrows(IllegalStateException.class, () -> {
			system.removeRecorrido(id);
		});
	}

	@Test
	void testRemoveRecorridoConRecorridoEnSystemConBilletesAsociados() {
		Usuario usuario = new Usuario("32698478E", "Geronimo");
		system.addRecorrido(recorrido);

		system.comprarBilletes("T12345", usuario, recorrido, 1);
		List<Billete> billetes = new ArrayList<>();
		billetes.add(new Billete("T12345", recorrido, usuario, ESTADO_COMPRADO));
		assertEquals(billetes, system.getAssociatedBilletesToRoute(id));

		assertThrows(IllegalStateException.class, () -> {
			system.removeRecorrido(id);
		});
	}
	
	/**
	 * FINDME Tests for {@link System#getRecorrido(String)}
	 */
	@Test
	void testGetRecorridoValido() {
		system.addRecorrido(recorrido);
		assertEquals(recorrido, system.getRecorrido(recorrido.getID()));
	}
	
	@Test
	void testGetRecorridoConIDNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.getRecorrido(null);
		});
	}

	@Test
	void testGetRecorridoConRecorridoFueraDeSistema() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.getRecorrido(id);
		});
	}

	/**
	 * FINDME Tests for {@link System#getPrecioTotalBilletesUsuario(String)}
	 */
	@Test
	void testGetPrecioTotalBilletesUsuarioBus() {
		system.comprarBilletes("32698478E", user, recorrido, 5);
		assertEquals(5.0, system.getPrecioTotalBilletesUsuario("32698478E"), ERROR_MARGIN);
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioPrecioRecorridoTren() {
		system.comprarBilletes("32698478E", user, differentRecorrido, 5);
		assertEquals(4.5, system.getPrecioTotalBilletesUsuario("32698478E"), ERROR_MARGIN);
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioVacio() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.getPrecioTotalBilletesUsuario(null);
		});
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioSinBilletes() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.getPrecioTotalBilletesUsuario("79105889B");
		});
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioConNifLimiteInferiorDigitos() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.getPrecioTotalBilletesUsuario("3269847E");
		});
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioConNifLimiteSuperiorDigitos() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.getPrecioTotalBilletesUsuario("326984789E");
		});
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioConNifSinCaracter() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.getPrecioTotalBilletesUsuario("3269847");
		});
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioConNifInvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.getPrecioTotalBilletesUsuario("3269847P");
		});
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioConNifInvalidoLetraInvalidaI() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.getPrecioTotalBilletesUsuario("3269847I");
		});
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioConNifInvalidoLetraInvalidaO() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.getPrecioTotalBilletesUsuario("3269847O");
		});
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioConNifInvalidoLetraInvalidaÑ() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.getPrecioTotalBilletesUsuario("3269847Ñ");
		});
	}

	@Test
	void testGetPrecioTotalBilletesUsuarioConNifInvalidoLetraInvalidaU() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.getPrecioTotalBilletesUsuario("3269847U");
		});
	}

	/**
	 * FINDME Tests for {@link System#getRecorridosDisponiblesFecha(LocalDate)}
	 */
	@Test
	void testGetRecorridosDisponiblesFecha() {
		ArrayList<Recorrido> recorridos = new ArrayList<>();
		recorridos.add(recorrido);
		recorridos.add(differentRecorrido);
		system.addRecorrido(recorrido);
		system.addRecorrido(differentRecorrido);
		assertEquals(recorridos, system.getRecorridosDisponiblesFecha(date));
	}

	@Test
	void testGetRecorridosDisponiblesFechaSinFecha() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.getRecorridosDisponiblesFecha(null);
		});
	}

	@Test
	void testGetRecorridosDisponiblesFechaSinRecorridos() {
		system.addRecorrido(recorrido);
		system.addRecorrido(differentRecorrido);
		LocalDate date1 = LocalDate.of(15, 10, 24);
		assertThrows(IllegalStateException.class, () -> {
			system.getRecorridosDisponiblesFecha(date1);
		});
	}

	/**
	 * FINDME Tests for {@link System#getAssociatedBilletesToRoute(String))}
	 */
	@Test
	void testGetAssociatedBilletesToRouteValido() {
		String localizator = "123";
		system.addRecorrido(recorrido);
		system.reservarBilletes(localizator, user, recorrido, 1);
		system.comprarBilletes(localizator, differentUser, recorrido, 2);

		List<Billete> expected = new ArrayList<>();
		expected.add(new Billete(localizator, recorrido, user, ESTADO_RESERVADO));
		expected.add(new Billete(localizator, recorrido, differentUser, ESTADO_COMPRADO));
		expected.add(new Billete(localizator, recorrido, differentUser, ESTADO_COMPRADO));

		assertEquals(expected, system.getAssociatedBilletesToRoute(id));
	}

	@Test
	void testGetAssociatedBilletesToRouteConRouteNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.getAssociatedBilletesToRoute(null);
		});
	}

	@Test
	void testGetAssociatedBilletesToRouteConRouteConRouteFueraDeSystem() {
		assertThrows(IllegalStateException.class, () -> {
			system.getAssociatedBilletesToRoute(id);
		});
	}

	/**
	 * FINDME Tests for {@link System#getDateOfRecorrido(String)}
	 */
	@Test
	void testGetDateOfRecorridoValidoConIDLimiteInferior() {
		String id = "1";
		Recorrido r = new Recorrido(id, origin, destination, transport, price, date, time, numSeats, duration);
		system.addRecorrido(r);
		assertEquals(r.getDate(), system.getDateOfRecorrido(id));
		fail(); // TODO Eliminar en fase verde
	}

	@Test
	void testGetDateOfRecorridoConIDNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.getDateOfRecorrido(null);
		});
	}

	@Test
	void testGetDateOfRecorridoNoValidoConIDLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.getDateOfRecorrido("");
		});
	}

	@Test
	void testGetDateOfRecorridoNoValidoConRecorridoFueraDeSystem() {
		assertThrows(IllegalStateException.class, () -> {
			system.getDateOfRecorrido(id);
		});
	}

	/**
	 * FINDME Tests for {@link System#getTimeOfRecorrido(String)}
	 */
	@Test
	void testGetTimeOfRecorridoValidoConIDLimiteInferior() {
		String id = "1";
		Recorrido r = new Recorrido(id, origin, destination, transport, price, date, time, numSeats, duration);
		system.addRecorrido(r);
		assertEquals(r.getTime(), system.getTimeOfRecorrido(id));
		fail(); // TODO Eliminar en fase verde
	}

	@Test
	void testGetTimeOfRecorridoNoValidoConIDNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.getTimeOfRecorrido(null);
		});
	}

	@Test
	void testGetTimeOfRecorridoNoValidoConIDLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.getTimeOfRecorrido("");
		});
	}

	@Test
	void testGetTimeOfRecorridoNoValidoConRecorridoFueraDeSystem() {
		assertThrows(IllegalStateException.class, () -> {
			system.getTimeOfRecorrido(id);
		});
	}

	/**
	 * FINDME Tests for {@link System#getDateTimeOfRecorrido(String)}
	 */
	@Test
	void testGetDateTimeOfRecorridoValidoConIDLimiteInferior() {
		String id = "1";
		Recorrido r = new Recorrido(id, origin, destination, transport, price, date, time, numSeats, duration);
		system.addRecorrido(r);
		assertEquals(r.getDateTime(), system.getDateTimeOfRecorrido(id));
		fail(); // TODO Eliminar en fase verde
	}

	@Test
	void testGetDateTimeOfRecorridoNoValidoConIDNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.getDateTimeOfRecorrido(null);
		});
	}

	@Test
	void testGetDateTimeOfRecorridoNoValidoConIDLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.getDateTimeOfRecorrido("");
		});
	}

	@Test
	void testGetDateTimeOfRecorridoNoValidoConRecorridoFueraDeSystem() {
		assertThrows(IllegalStateException.class, () -> {
			system.getDateTimeOfRecorrido(id);
		});
	}

	/**
	 * FINDME Tests for {@link System#updateRecorridoDate(String, LocalDate)}
	 */
	@Test
	void testUpdateRecorridoDateValido() {
		system.addRecorrido(recorrido);
		assertNotEquals(newDate, system.getDateOfRecorrido(recorrido.getID()));
		system.updateRecorridoDate(id, newDate);
		assertEquals(newDate, system.getDateOfRecorrido(recorrido.getID()));
	}

	@Test
	void testUpdateRecorridoDateConRecorridoNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.updateRecorridoDate(null, newDate);
		});
	}

	@Test
	void testUpdateRecorridoDateConRecorridoFueraDelsystem() {
		assertThrows(IllegalStateException.class, () -> {
			system.updateRecorridoDate(id, newDate);
		});
	}

	@Test
	void testUpdateRecorridoDateConDateNull() {
		system.addRecorrido(recorrido);
		assertThrows(IllegalArgumentException.class, () -> {
			system.updateRecorridoDate(id, null);
		});
	}

	@Test
	void testUpdateRecorridoDateConDateAnterior() {
		system.addRecorrido(recorrido);
		assertThrows(IllegalStateException.class, () -> {
			system.updateRecorridoDate(id, recorrido.getDate());
		});
	}

	/**
	 * FINDME Tests for {@link System#updateRecorridoTime(String, LocalTime)}
	 */
	@Test
	void testUpdateRecorridoTimeValido() {
		system.addRecorrido(recorrido);
		assertNotEquals(newTime, system.getTimeOfRecorrido(recorrido.getID()));
		system.updateRecorridoTime(id, newTime);
		assertEquals(newTime, system.getTimeOfRecorrido(recorrido.getID()));
	}

	@Test
	void testUpdateRecorridoTimeConRecorridoNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.updateRecorridoTime(null, newTime);
		});
	}

	@Test
	void testUpdateRecorridoTimeConRecorridoFueraDelsystem() {
		assertThrows(IllegalStateException.class, () -> {
			system.updateRecorridoTime(id, newTime);
		});
	}

	@Test
	void testUpdateRecorridoTimeConTimeNull() {
		system.addRecorrido(recorrido);
		assertThrows(IllegalArgumentException.class, () -> {
			system.updateRecorridoTime(id, null);
		});
	}

	@Test
	void testUpdateRecorridoTimeConTimeAnterior() {
		system.addRecorrido(recorrido);
		assertThrows(IllegalStateException.class, () -> {
			system.updateRecorridoTime(id, recorrido.getTime());
		});
	}

	/**
	 * FINDME Tests for
	 * {@link System#updateRecorridoDateTime(String, LocalDateTime)}
	 */
	@Test
	void testUpdateRecorridoDateTimeValido() {
		system.addRecorrido(recorrido);
		assertNotEquals(newDateTime, system.getDateTimeOfRecorrido(recorrido.getID()));
		system.updateRecorridoDateTime(id, newDateTime);
		assertEquals(newDateTime, system.getDateTimeOfRecorrido(recorrido.getID()));
	}

	@Test
	void testUpdateRecorridoDateTimeConRecorridoNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.updateRecorridoDateTime(null, newDateTime);
		});
	}

	@Test
	void testUpdateRecorridoDateTimeConRecorridoFueraDelsystem() {
		assertThrows(IllegalStateException.class, () -> {
			system.updateRecorridoDateTime(id, newDateTime);
		});
	}

	@Test
	void testUpdateRecorridoDateTimeConDateTimeNull() {
		system.addRecorrido(recorrido);
		assertThrows(IllegalArgumentException.class, () -> {
			system.updateRecorridoDateTime(id, null);
		});
	}

	@Test
	void testUpdateRecorridoDateTimeConDateYTimeAnterior() {
		system.addRecorrido(recorrido);
		assertThrows(IllegalStateException.class, () -> {
			system.updateRecorridoDateTime(id, recorrido.getDateTime());
		});
	}

	/**
	 * FINDME Tests for
	 * {@link System#updateRecorrido(String, LocalDate, LocalTime)}
	 */
	@Test
	void testUpdateRecorridoValido() {
		system.addRecorrido(recorrido);
		assertNotEquals(newDateTime, system.getDateTimeOfRecorrido(recorrido.getID()));
		system.updateRecorrido(id, newDateTime.toLocalDate(), newDateTime.toLocalTime());
		assertEquals(newDateTime, system.getDateTimeOfRecorrido(recorrido.getID()));
	}

	@Test
	void testUpdateRecorridoConRecorridoNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.updateRecorrido(null, newDate, newTime);
		});
	}

	@Test
	void testUpdateRecorridoConRecorridoFueraDelsystem() {
		assertThrows(IllegalStateException.class, () -> {
			system.updateRecorrido(id, newDate, newTime);
		});
	}

	@Test
	void testUpdateRecorridoConDateNull() {
		system.addRecorrido(recorrido);
		assertThrows(IllegalArgumentException.class, () -> {
			system.updateRecorrido(id, null, newTime);
		});
	}

	@Test
	void testUpdateRecorridoConTimeNull() {
		system.addRecorrido(recorrido);
		assertThrows(IllegalArgumentException.class, () -> {
			system.updateRecorrido(id, newDate, null);
		});
	}

	@Test
	void testUpdateRecorridoConDateYTimeAnterior() {
		system.addRecorrido(recorrido);
		assertThrows(IllegalStateException.class, () -> {
			system.updateRecorrido(id, recorrido.getDate(), recorrido.getTime());
		});
	}

	/**
	 * FINDME Tests for
	 * {@link System#comprarBilletes(String, Usuario, Recorrido, int)}
	 */
	@Test
	void testComprarBilletesValidoBusLimiteInferior() {
		List<Billete> listaBilletes = system.comprarBilletes("ABC12345", user, recorrido, 1);
		List<Billete> listaBilletesComprobacion = new ArrayList<>();
		for (int i = 0; i < 1; i++) {
			Billete billeteComprobacion = new Billete("ABC12345", recorrido, user, ESTADO_COMPRADO);
			listaBilletesComprobacion.add(billeteComprobacion);
		}
		assertEquals(listaBilletes, listaBilletesComprobacion);
	}

	@Test
	void testComprarBilletesValidosBusLimiteSuperior() {
		List<Billete> listaBilletes = system.comprarBilletes("ABC12345", user, recorrido, 150);
		List<Billete> listaBilletesComprobacion = new ArrayList<>();
		for (int i = 0; i < 150; i++) {
			Billete billeteComprobacion = new Billete("ABC12345", recorrido, user, ESTADO_COMPRADO);
			listaBilletesComprobacion.add(billeteComprobacion);
		}
		assertEquals(listaBilletes, listaBilletesComprobacion);
	}

	@Test
	void testComprarBilletesValidoTrenLimiteInferior() {
		List<Billete> listaBilletes = system.comprarBilletes("ABC12345", user, differentRecorrido, 1);
		List<Billete> listaBilletesComprobacion = new ArrayList<>();
		for (int i = 0; i < 1; i++) {
			Billete billeteComprobacion = new Billete("ABC12345", differentRecorrido, user, ESTADO_COMPRADO);
			listaBilletesComprobacion.add(billeteComprobacion);
		}
		assertEquals(listaBilletes, listaBilletesComprobacion);
	}

	@Test
	void testComprarBilletesValidosTrenLimiteSuperior() {
		List<Billete> listaBilletes = system.comprarBilletes("ABC12345", user, differentRecorrido, 250);
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
			system.comprarBilletes("ABC12345", user, recorrido, 0);
		});
	}

	@Test
	void testComprarBilleteTrainInvalidoLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.comprarBilletes("ABC12345", user, differentRecorrido, 0);
		});
	}

	@Test
	void testComprarBilleteBusInvalidoLimiteSuperior() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.comprarBilletes("ABC12345", user, recorrido, 151);
		});
	}

	@Test
	void testComprarBilleteTrainInvalidoLimiteSuperior() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.comprarBilletes("ABC12345", user, differentRecorrido, 251);
		});
	}

	@Test
	void testComprarBilleteBusInvalidoLimiteSuperiorDemasiadaCompras() {
		system.comprarBilletes("ABC12345", user, recorrido, 149);
		assertThrows(IllegalStateException.class, () -> {
			system.comprarBilletes("ABC12345", differentUser, recorrido, 2);
		});
	}

	@Test
	void testComprarBilleteTrainInvalidoLimiteSuperiorDemasiadaCompras() {
		system.comprarBilletes("ABC12345", user, differentRecorrido, 249);
		assertThrows(IllegalStateException.class, () -> {
			system.comprarBilletes("ABC12345", differentUser, differentRecorrido, 2);
		});
	}

	@Test
	void testComprarBilleteLocalizadorInvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.comprarBilletes("", user, differentRecorrido, 1);
		});
	}

	@Test
	void testComprarBilleteLocalizadorNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.comprarBilletes(null, user, differentRecorrido, 1);
		});
	}

	@Test
	void testComprarBilleteLocalizadorYaUsadoMismoRecorrido() {
		system.comprarBilletes("ABC12345", user, differentRecorrido, 2);
		assertThrows(IllegalArgumentException.class, () -> {
			system.comprarBilletes("ABC12345", differentUser, differentRecorrido, 1);
		});
	}

	@Test
	void testComprarBilleteLocalizadorYaUsadoDistinoRecorridoRecorrido() {
		system.comprarBilletes("ABC12345", user, differentRecorrido, 2);
		assertThrows(IllegalArgumentException.class, () -> {
			system.comprarBilletes("ABC12345", differentUser, recorrido, 1);
		});
	}

	@Test
	void testComprarBilleteUsuarioNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.comprarBilletes("ABC12345", null, differentRecorrido, 1);
		});
	}

	@Test
	void testComprarBilleteRecorridoNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.comprarBilletes("ABC12345", user, null, 1);
		});
	}

	/**
	 * FINDME Tests for {@link System#comprarBilletesReservados(String)}
	 */
	@Test
	void testComprarBilletesReservadosValidoLimiteInferior() {
		String localizator = "1";
		system.addRecorrido(recorrido);
		List<Billete> bookedTicketsCheck = new ArrayList<>();
		// TODO Implementar en billete (Actualizar constructor billete)
		bookedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_RESERVADO));
		bookedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_RESERVADO));
		bookedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_RESERVADO));
		List<Billete> bookedTickets = system.reservarBilletes(localizator, user, recorrido, 3);
		assertEquals(bookedTicketsCheck, bookedTickets);

		List<Billete> buyedTicketsCheck = new ArrayList<>();
		buyedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_COMPRADO));
		buyedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_COMPRADO));
		buyedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_COMPRADO));
		List<Billete> buyedTickets = system.comprarBilletesReservados(localizator);
		assertEquals(buyedTicketsCheck, buyedTickets);

		assertNotEquals(bookedTickets, buyedTickets);
	}

	@Test
	void testComprarBilletesReservadosValidoLimiteSuperior() {
		String localizator = "12345678";
		system.addRecorrido(recorrido);
		List<Billete> bookedTicketsCheck = new ArrayList<>();
		bookedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_RESERVADO));
		bookedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_RESERVADO));
		bookedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_RESERVADO));
		List<Billete> bookedTickets = system.reservarBilletes(localizator, user, recorrido, 3);
		assertEquals(bookedTicketsCheck, bookedTickets);

		List<Billete> buyedTicketsCheck = new ArrayList<>();
		buyedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_COMPRADO));
		buyedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_COMPRADO));
		buyedTicketsCheck.add(new Billete(localizator, recorrido, user, ESTADO_COMPRADO));
		List<Billete> buyedTickets = system.comprarBilletesReservados(localizator);
		assertEquals(buyedTicketsCheck, buyedTickets);

		assertNotEquals(bookedTickets, buyedTickets);
	}

	@Test
	void testComprarBilletesReservadosConLocatorNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.comprarBilletesReservados(null);
		});
	}

	@Test
	void testComprarBilletesReservadosLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.comprarBilletesReservados("");
		});
	}

	@Test
	void testComprarBilletesReservadosLimiteSuperior() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.comprarBilletesReservados("123456789");
		});
	}

	@Test
	void testComprarBilletesReservadosSinBilletesReservadosEnSystemOConLocalizatorIncorrecto() {
		String localizator = "12345678";
		String localizator2 = "87654321";
		system.addRecorrido(recorrido);
		assertNotEquals(localizator, localizator2);
		assertThrows(IllegalStateException.class, () -> {
			system.comprarBilletesReservados(localizator2);
		});
	}

	/**
	 * FINDME Tests for
	 * {@link System#reservarBilletes(String, Usuario, Recorrido, int)}
	 */
	@Test
	void testReservarBilletesExitosamente() {
		int plazasDisponibles = recorrido.getNumAvailableSeats();
		int numBilletesReservar = 6;
		// Realiza la reserva de los billetes
		List<Billete> reservado = system.reservarBilletes("ABC12345", user, recorrido, numBilletesReservar);

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
			system.reservarBilletes("", user, recorrido, 1);
		});
	}

	@Test
	void testReservarBilleteLocalizadorNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.reservarBilletes(null, user, recorrido, 1);
		});
	}

	@Test
	void testReservarBilleteUsuarioNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.reservarBilletes("ABC12345", null, recorrido, 1);
		});
	}

	@Test
	void testReservarBilleteRecorridoNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.reservarBilletes("ABC12345", user, null, 1);
		});
	}

	@Test
	void testNoSePuedeReservarSiPlazasInsuficientes() {
		int plazasDisponibles = recorrido.getNumAvailableSeats();
		int numBilletesReservar = plazasDisponibles + 1;

		// Intenta reservar más billetes de los disponibles
		system.reservarBilletes("ABC12345", user, recorrido, numBilletesReservar);

		assertThrows(IllegalArgumentException.class, () -> {
			system.reservarBilletes("ABC12345", user, recorrido, numBilletesReservar);
		});

	}

	@Test
	void testNoSePuedeReservarSiPlazasMenoresQueLaMitad() {
		int numBilletesReservar = recorrido.getTotalSeats() / 2;

		// Intenta reservar más billetes de los disponibles
		system.reservarBilletes("ABC12345", user, recorrido, numBilletesReservar);

		assertThrows(IllegalArgumentException.class, () -> {
			system.reservarBilletes("ABC12345", user, recorrido, 2);
		});

	}

	/**
	 * FINDME Tests for {@link System#anularReserva(String, int)}
	 */
	@Test
	void testAnularReservaAumentaPlazasDisponiblesLimiteInferior() {
		int numBilletesReservar = 6;

		// Realiza la reserva de los billetes
		system.reservarBilletes("ABC12345", user, recorrido, numBilletesReservar);

		int plazasDisponiblesAntes = recorrido.getNumAvailableSeats();
		int numBilletesAnular = 1;

		// Realiza la anulación de la reserva
		system.anularReserva("ABC12345", numBilletesAnular);

		int plazasDisponiblesDespues = recorrido.getNumAvailableSeats();

		// Verifica que las plazas disponibles aumenten en la cantidad correcta
		assertEquals(plazasDisponiblesAntes + numBilletesAnular, plazasDisponiblesDespues);
	}

	@Test
	void testNoSePuedeAnularReservaSiLocalizadorNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.anularReserva(null, 1);
		});
	}

	@Test
	void testNoSePuedeAnularReservaSiLocalizadorVacío() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.anularReserva("", 1);
		});
	}

	@Test
	void testNoSePuedeAnularReservaSiFueraSistema() {
		int numBilletesAnular = 2;
		// Intenta anular la reserva sin reservar previamente
		assertThrows(IllegalStateException.class, () -> {
			system.anularReserva("ABC12345", numBilletesAnular);
		});
	}

	@Test
	void testNoSePuedeAnularReservaSiLocalizadorLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.anularReserva("ABCD12345", 0);
		});
	}

	@Test
	void testNoSePuedeAnularReservaSiLocalizadorPerteneceABilletesComprados() {
		String locator = "ABC12345";
		system.comprarBilletes(locator, user, recorrido, 3);
		assertThrows(IllegalStateException.class, () -> {
			system.anularReserva(locator, 2);
		});
	}

	@Test
	void testNoSePuedeAnularReservaSiMayorNumeroDeAnulacionesQueBilletesReservados() {
		String locator = "ABC12345";
		int numBilletesAnular = 3;
		system.reservarBilletes(locator, user, recorrido, 1);
		assertThrows(IllegalStateException.class, () -> {
			system.anularReserva(locator, numBilletesAnular);
		});
	}

	/**
	 * FINDME Tests for {@link System#devolverBilletes(String, int)}
	 */
	@Test
	void testDevolverBilletesAumentaPlazasDisponiblesLimiteInferior() {
		int numBilletesComprar = 6;

		// Realiza la reserva de los billetes
		system.comprarBilletes("ABC12345", user, recorrido, numBilletesComprar);
		int plazasDisponiblesAntes = recorrido.getNumAvailableSeats();
		int numBilletesDevolver = 1;

		// Realiza la anulación de la reserva
		system.devolverBilletes("ABC12345", numBilletesDevolver);

		int plazasDisponiblesDespues = recorrido.getNumAvailableSeats();

		// Verifica que las plazas disponibles aumenten en la cantidad correcta
		assertEquals(plazasDisponiblesAntes + numBilletesDevolver, plazasDisponiblesDespues);
	}

	@Test
	void testNoSePuedeDevolverBilletesSiLocalizadorNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.devolverBilletes(null, 1);
		});
	}

	@Test
	void testNoSePuedeDevolverBilletesSiLocalizadorVacío() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.devolverBilletes("", 1);
		});
	}

	@Test
	void testNoSePuedeDevolverBilletesSiLocalizadorLimiteInferior() {
		assertThrows(IllegalArgumentException.class, () -> {
			system.devolverBilletes("ABCD12345", 0);
		});
	}

	@Test
	void testNoSePuedeDevolverBilleteFueraSistema() {
		int numBilletesDevolver = 2;
		// Intenta anular la reserva sin reservar previamente
		assertThrows(IllegalStateException.class, () -> {
			system.devolverBilletes("ABC12345", numBilletesDevolver);
		});
	}

	@Test
	void testNoSePuedeDevolverBilleteSiReservados() {
		String locator = "ABC12345";
		int numBilletesDevolver = 2;
		system.reservarBilletes(locator, user, recorrido, 5);

		assertThrows(IllegalStateException.class, () -> {
			system.devolverBilletes(locator, numBilletesDevolver);
		});
	}

	@Test
	void testNoSePuedeDevolverBilletesConNumLimiteInferior() {
		String locator = "ABC12345";
		system.comprarBilletes(locator, user, recorrido, 1);
		assertThrows(IllegalStateException.class, () -> {
			system.devolverBilletes(locator, 0);
		});
	}

	@Test
	void testNoSePuedeDevolverBilletesSiMayorNumeroDeDevolucionesQueBilletesComprados() {
		String locator = "ABC12345";
		int numBilletesDevolver = 3;
		system.comprarBilletes(locator, user, recorrido, 1);
		assertThrows(IllegalStateException.class, () -> {
			system.devolverBilletes(locator, numBilletesDevolver);
		});
	}
}
