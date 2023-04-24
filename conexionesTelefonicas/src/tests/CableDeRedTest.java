package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import backend.CableDeRed;
import backend.Localidad;

/* ----si los argumentos no son validos
 * nombre != ""
 * provincia != ""
 * -91<latitud<91 
 * -181<longitud<181
 */

public class CableDeRedTest {
	private Localidad san_miguel;
	private Localidad _nombreNull;
	private Localidad _provinciaNull;
	private Localidad _latitudMenorNull;
	private Localidad _latitudMayorNull;
	private Localidad _longitudMenorNull;
	private Localidad _longitudMayorNull;
	private Localidad jc_paz;
	private Localidad muniz;
	private Localidad morris;

	@Before
	public void setup() {
		san_miguel = new Localidad("San Miguel", "Buenos Aires", 50, 30);

		jc_paz = new Localidad("Jc.Paz", "Buenos Aires", 50, 29);
		muniz = new Localidad("Muñiz", "Buenos Aires", 10, 30);
		morris= new Localidad("Morris", "Cordoba", 10, 30);
		_nombreNull = new Localidad("", "Buenos Aires", 20, 30);

		_provinciaNull = new Localidad("Prueba", "", 20, 30);

		_latitudMenorNull = new Localidad("Prueba", "Buenos Aires", -91, 30);
		_latitudMayorNull = new Localidad("Prueba", "Buenos Aires", 91, 30);

		_longitudMenorNull = new Localidad("Prueba", "Buenos Aires", 20, -181);
		_longitudMayorNull = new Localidad("Prueba", "Buenos Aires", 20, 181);
	}

	// ----------si el nombre es invalido de la localidad.
	@Test(expected = IllegalArgumentException.class)
	public void nombreInvalidoTest() {
		CableDeRed.distanciaEnKm(_nombreNull, san_miguel);
	}

	// ----------si la provincia es invalida de la localidad.
	@Test(expected = IllegalArgumentException.class)
	public void provinciaInvalidaTest() {
		CableDeRed.distanciaEnKm(_provinciaNull, san_miguel);
	}

	// si la latitud es menor a la valida de la localidad.
	@Test(expected = IllegalArgumentException.class)
	public void latitudMenorInvalidaTest() {
		CableDeRed.distanciaEnKm(_latitudMenorNull, san_miguel);
	}

	// si la latitud es invalida de la localidad.
	@Test(expected = IllegalArgumentException.class)
	public void latitudMayorInvalidaTest() {
		CableDeRed.distanciaEnKm(_latitudMayorNull, san_miguel);
	}

	// si la longitud es invalida de la localidad.
	@Test(expected = IllegalArgumentException.class)
	public void longitudMenorInvalidaTest() {
		CableDeRed.distanciaEnKm(_longitudMenorNull, san_miguel);
	}

	// si la longitud es invalida de la localidad.
	@Test(expected = IllegalArgumentException.class)
	public void longitudMayorInvalidaTest() {
		CableDeRed.distanciaEnKm(_longitudMayorNull, san_miguel);
	}

	// ----------si los km estan en la misma provincia y no supera 300km
	@Test
	public void costoSinAgregadoTest() {
		CableDeRed red = new CableDeRed(jc_paz, san_miguel);

		Double distancia = red.getDistancia();
		int costo = CableDeRed.getCostoCable();
		
		assertTrue(red.getCosto().equals((float) (distancia * costo)));
	}

	// ----------si los km estan en la misma provincia y supera 300km
	@Test
	public void costoConKmAgregadoTest() {
		CableDeRed red = new CableDeRed(jc_paz, muniz);

		Double distancia = red.getDistancia();
		float costo = CableDeRed.getCostoCable();
		double bruto=distancia* costo ;
		double porcentajeAdicional = bruto * CableDeRed.getcostoDistanciaSuperaKm();
		
		assertTrue(red.getCosto().equals((float) (distancia * costo + porcentajeAdicional)));
	}
	// ----------si los km No estan en la misma provincia y supera 300km
	@Test
	public void costoConKmAgregadoYOtraProvinciaTest() {
		CableDeRed red = new CableDeRed(jc_paz, morris);

		Double distancia = red.getDistancia();
		float costo = CableDeRed.getCostoCable();
		double bruto=distancia* costo ;
		double porcentajeAdicional = bruto * CableDeRed.getcostoDistanciaSuperaKm();
		
		assertTrue(red.getCosto().equals((float) (distancia * costo + porcentajeAdicional)+20));
	}
	
	@Test
	public void costoConOtraProvinciaTest() {
		san_miguel= new Localidad("San Miguel", "Cordoba", 50, 30);
		CableDeRed red = new CableDeRed(jc_paz, san_miguel);

		Double distancia = red.getDistancia();
		int costo = CableDeRed.getCostoCable();
		
		assertTrue(red.getCosto().equals((float) (distancia * costo+20)));
	}
}
