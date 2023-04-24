package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import backend.ArbolGeneradorMinimo;
import backend.GrafoLocalidad;
import backend.Localidad;

public class ArbolGeneradorMinimoTest
{

	private GrafoLocalidad g;
	private Localidad jose_c_paz;
	private Localidad san_miguel;
	private Localidad pilar;
	private Localidad bella_vista;
	private Localidad moreno;
	private Localidad villa_del_parque;

	@Before
	public void setup() 
	{
		jose_c_paz = new Localidad("Jose.C.Paz", "Buenos Aires", 20, 30);
		san_miguel = new Localidad("San Miguel", "Buenos Aires", 50, 30);
		pilar = new Localidad("Pilar", "Buenos Aires", 10, 30);
		bella_vista = new Localidad("Bella Vista", "Buenos Aires", 70, 30);
		moreno = new Localidad("Moreno", "Buenos Aires", 80, 42);
		villa_del_parque = new Localidad("Villa Del Parque", "Buenos Aires", 48, 23);
		
		g = new GrafoLocalidad();
	}

	private GrafoLocalidad inicializarGrafoInconexo() 
	{
		GrafoLocalidad g = new GrafoLocalidad(jose_c_paz, pilar, san_miguel, villa_del_parque, bella_vista);
		
		g.agregarArista(jose_c_paz, moreno);
		g.agregarArista(jose_c_paz, pilar);
		g.agregarArista(villa_del_parque, san_miguel);
		
		return g;		
	}
	
	private GrafoLocalidad inicializarGrafoCompleto() 
	{
		GrafoLocalidad g = new GrafoLocalidad(jose_c_paz, pilar, san_miguel, villa_del_parque, bella_vista);
		
		g.agregarArista(jose_c_paz, pilar);
		g.agregarArista(jose_c_paz, san_miguel);
		g.agregarArista(jose_c_paz, villa_del_parque);
		g.agregarArista(jose_c_paz, bella_vista);
		g.agregarArista(pilar, san_miguel);
		g.agregarArista(pilar, villa_del_parque);
		g.agregarArista(pilar, bella_vista);
		g.agregarArista(san_miguel, villa_del_parque);
		g.agregarArista(san_miguel, bella_vista);
		g.agregarArista(villa_del_parque, bella_vista);

		return g;		
	}

	@Test
	public void agmHappyTest()
	{
		GrafoLocalidad grafoCompleto = inicializarGrafoCompleto();
		
		GrafoLocalidad agm = ArbolGeneradorMinimo.prim(grafoCompleto);
		
		
		assertTrue(agm.getAristas().size() == grafoCompleto.getLocalidades().size() - 1);
		assertTrue(agm.getLocalidades().size() == grafoCompleto.getLocalidades().size());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agmGrafoInconexo()
	{
		g = inicializarGrafoInconexo();
		
		@SuppressWarnings("unused")
		GrafoLocalidad agm = ArbolGeneradorMinimo.prim(g);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agmGrafoNULL()
	{
		@SuppressWarnings("unused")
		GrafoLocalidad agm = ArbolGeneradorMinimo.prim(null);
	}
	
	@Test
	public void agmGrafoConexoNoCompleto()
	{
		g = new GrafoLocalidad(jose_c_paz, san_miguel, pilar, bella_vista, villa_del_parque);
		
		g.agregarArista(jose_c_paz, bella_vista);
		g.agregarArista(bella_vista, san_miguel);
		g.agregarArista(san_miguel, pilar);
		g.agregarArista(jose_c_paz, villa_del_parque);
		g.agregarArista(villa_del_parque, bella_vista);
		g.agregarArista(san_miguel, jose_c_paz);
		
		GrafoLocalidad agm = ArbolGeneradorMinimo.prim(g);
		
		assertTrue(agm.getAristas().size() == g.tamanio() - 1);
		assertTrue(agm.tamanio() == g.tamanio());
	}

}
