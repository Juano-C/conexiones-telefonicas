package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import backend.GrafoLocalidad;
import backend.Localidad;

public class GrafoLocalidadTest {

	private GrafoLocalidad g;
	private Localidad jose_c_paz;
	private Localidad san_miguel;
	private Localidad pilar;
	private Localidad bella_vista;
	private Localidad moreno;
	private Localidad villa_del_parque;

	@Before
	public void setup() {
		jose_c_paz = new Localidad("Jose.C.Paz", "Buenos Aires", 20, 30);
		san_miguel = new Localidad("San Miguel", "Buenos Aires", 50, 30);
		pilar = new Localidad("Pilar", "Buenos Aires", 10, 30);
		bella_vista = new Localidad("Bella Vista", "Buenos Aires", 70, 30);
		moreno = new Localidad("Moreno", "Buenos Aires", 80, 42);
		villa_del_parque = new Localidad("Villa Del Parque", "Buenos Aires", 48, 23);
		g = new GrafoLocalidad(jose_c_paz, san_miguel, pilar, bella_vista, moreno, villa_del_parque);
	}

	@Test
	public void grafoSinLocalidades() {
		g = new GrafoLocalidad();
	}

	@Test
	public void grafoConUnaLocalidad() {
		g = new GrafoLocalidad(jose_c_paz);
	}

	@Test
	public void grafoConDosLocalidades() {
		g = new GrafoLocalidad(jose_c_paz, villa_del_parque);
	}

	@Test
	public void existeLocalidadNullTest() {

		g = new GrafoLocalidad();
		assertFalse(g.existeVertice(null));

	}

	@Test
	public void existeLocalidadNoEsta() {
		Localidad localidadNoAgregada = new Localidad("Matanza", "Buenos Aires", -40, -30);
		assertFalse(g.existeVertice(localidadNoAgregada));
	}

	@Test
	public void existeLocalidadSiEsta() {
		Localidad localidadYaAgregada = new Localidad("San Miguel", "Buenos Aires", 50, 30);
		assertTrue(g.existeVertice(localidadYaAgregada));
	}

	@Test
	public void agregarLocalidad() {
		Localidad nuevaLocalidad = new Localidad("Capital Federal", "Buenos Aires", 100, 100);
		g.agregarVertice(nuevaLocalidad);
		assertTrue(g.existeVertice(nuevaLocalidad));
	}

	@Test
	public void agregarLocalidadQueYaEstaba() {
		Localidad localidadYaExistia = new Localidad("Jose.C.Paz", "Buenos Aires", 100, 10);
		g.agregarVertice(localidadYaExistia);
		assertTrue(g.existeVertice(localidadYaExistia)); // No la agrega solo se fija en el Nombre y Provincia
		assertTrue(g.existeVertice(new Localidad("Jose.C.Paz", "Buenos Aires", 20, 30)));
	}

	@Test
	public void agregarLocalidadNull() {
		g.agregarVertice(null);
	}

	@Test
	public void agregarArista() {
		g.agregarArista(jose_c_paz, bella_vista);
		assertTrue(g.existeArista(jose_c_paz, bella_vista));
		assertFalse(g.existeArista(jose_c_paz, moreno));
	}

	@Test
	public void agregoMismaArista() {
		g.agregarArista(jose_c_paz, bella_vista);
		g.agregarArista(jose_c_paz, bella_vista);
		assertTrue(g.existeArista(jose_c_paz, bella_vista));
	}

	@Test
	public void vecinosDeUnVericeHappyCase() {
		g.agregarArista(jose_c_paz, san_miguel);
		g.agregarArista(jose_c_paz, bella_vista);
		g.agregarArista(jose_c_paz, moreno);
		LinkedList<Localidad> vecinos = g.vecinos(jose_c_paz);

		assertTrue(vecinos.contains(san_miguel));
		assertTrue(vecinos.contains(bella_vista));
		assertTrue(vecinos.contains(moreno));
		assertFalse(vecinos.contains(pilar));
		assertFalse(vecinos.contains(villa_del_parque));
	}

	@Test
	public void mismosVecinosEnDosLocalidades() {
		g.agregarArista(jose_c_paz, san_miguel);
		g.agregarArista(jose_c_paz, bella_vista);
		g.agregarArista(jose_c_paz, moreno);
		LinkedList<Localidad> vecinosJoseCPaz = g.vecinos(jose_c_paz);

		g.agregarArista(pilar, san_miguel);
		g.agregarArista(pilar, bella_vista);
		g.agregarArista(pilar, moreno);
		LinkedList<Localidad> vecinosPilar = g.vecinos(pilar);

		for (Localidad vecinoJoseCPaz : vecinosJoseCPaz) {
			assertTrue(vecinosPilar.contains(vecinoJoseCPaz));
		}

	}

	@Test
	public void existeAristaNoEsta() {
		assertFalse(g.existeArista(jose_c_paz, bella_vista));
	}

	@Test
	public void existeAristaYsiEsta() {
		g.agregarArista(jose_c_paz, bella_vista);
		assertTrue(g.existeArista(jose_c_paz, bella_vista));
	}

	@Test
	public void existeAristaNULL() {
		g.agregarArista(null, bella_vista);
		assertFalse(g.existeArista(null, bella_vista));
	}

	@Test
	public void tamanioDos() {
		g = new GrafoLocalidad(jose_c_paz, san_miguel);
		assertTrue(g.tamanio() == 2);

	}
	
	@Test
	public void tamanioTres() {
		g = new GrafoLocalidad(jose_c_paz, san_miguel, villa_del_parque);
		assertTrue(g.tamanio() == 3);
	}
	
	@Test 
	public void eliminarVerticeNull() {
		g = new GrafoLocalidad(jose_c_paz, san_miguel, villa_del_parque, moreno, bella_vista);
		assertTrue(g.tamanio() == 5);
		g.quitarVertice(null);
		assertTrue(g.tamanio() == 5);
	}
	
	@Test
	public void eliminarVerticeNoExiste() {
		g = new GrafoLocalidad();
		g.quitarVertice(bella_vista);
		assertTrue(g.tamanio() == 0);
	}
	
	@Test
	public void eliminarUnVertice() {
		g = new GrafoLocalidad(jose_c_paz, san_miguel, villa_del_parque, moreno, bella_vista);
		assertTrue(g.tamanio() == 5);
		g.quitarVertice(bella_vista);
		assertTrue(g.tamanio() == 4);
	}
	
	@Test
	public void eliminarDosVertices() {
		g = new GrafoLocalidad(jose_c_paz, san_miguel, villa_del_parque, moreno, bella_vista);
		assertTrue(g.tamanio() == 5);
		g.quitarVertice(bella_vista);
		g.quitarVertice(jose_c_paz);
		assertTrue(g.tamanio() == 3);
	}
	
	@Test
	public void eliminarDosVerticesYquedaVacioElGrafo() {
		g = new GrafoLocalidad(jose_c_paz, san_miguel);
		assertTrue(g.tamanio() == 2);
		g.quitarVertice(jose_c_paz);
		g.quitarVertice(san_miguel);
		assertTrue(g.tamanio() == 0);
	}

}
