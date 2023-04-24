package backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class GrafoLocalidad {
	
	
//	LinkedList<Localidad> vertices;
	LinkedList<CableDeRed> aristas;
	Map<Localidad, LinkedList<Localidad>> vecinos;

	
	public GrafoLocalidad() 
	{
		vecinos = new HashMap<Localidad, LinkedList<Localidad>>();
		aristas = new LinkedList<CableDeRed>();
	}
	
	public GrafoLocalidad(LinkedList<Localidad> listaVertices) 
	{
		vecinos = new HashMap<Localidad, LinkedList<Localidad>>();
		aristas = new LinkedList<CableDeRed>();
		
		// Agrego los vertices
		for(Localidad vertice: listaVertices) 
			vecinos.put(vertice, new LinkedList<Localidad>());
	}
	
	public GrafoLocalidad(Localidad... localidades) 
	{
		vecinos = new HashMap<Localidad, LinkedList<Localidad>>();
		aristas = new LinkedList<CableDeRed>();
		
		for(Localidad localidad: localidades) 
			vecinos.put(localidad, new LinkedList<Localidad>());
		
	}
	
	public void agregarVertice(Localidad v) 
	{
		if (v != null && !vecinos.containsKey(v))
			vecinos.put(v, new LinkedList<Localidad>());

	}

	public void quitarVertice(Localidad verticeAquitar) 
	{
		// Remuevo la key asociada al vertice
		for(Localidad vertice: vecinos.keySet())
			// Remuevo el valor en cada lista de vecinos
			vecinos.get(vertice).remove(verticeAquitar);
		
		// Lo remuevo de las aristas del grafo
		Iterator<CableDeRed> arista = aristas.iterator();
		while(arista.hasNext())
			if(arista.next().tieneEstaLocalidad(verticeAquitar))
				arista.remove();
		
		vecinos.remove(verticeAquitar);
	}

	public boolean existeVertice(Localidad v) 
	{
		return vecinos.keySet().contains(v);
	}

	public void agregarArista(Localidad v1, Localidad v2) 
	{
		if(verificarVertices(v1, v2)) return;
		
		CableDeRed nuevoCable = new CableDeRed(v1, v2);
		if(!aristas.contains(nuevoCable)){
			aristas.add(nuevoCable);
			vecinos.get(v1).add(v2);
			vecinos.get(v2).add(v1);
		}
	}
	

	public void quitarArista(Localidad v1, Localidad v2) 
	{
		if(verificarVertices(v1, v2)) return;

		CableDeRed cable = new CableDeRed( v1, v2);
		if(aristas.contains(cable)) {
			aristas.remove(cable);
			vecinos.get(v1).remove(v2);
			vecinos.get(v2).remove(v1);
		}
	}

	public boolean existeArista(Localidad v1, Localidad v2) 
	{
		if(v1 == null || v2 == null) {
			return false;
		}
		return aristas.contains(new CableDeRed( v1, v2));
	}

	public LinkedList<Localidad> vecinos(Localidad v) 
	{
		return vecinos.get(v);
	}
	
	public int tamanio() 
	{
		return vecinos.size();
	}
	
	public void imprimirLocalidades() 
	{
		for(Localidad localidad: vecinos.keySet())
			System.out.println(localidad.toString());
		
	}

	private boolean verificarVertices(Localidad v1, Localidad v2) {
		return v1 == null || v2 == null || !vecinos.keySet().contains(v1) || !vecinos.keySet().contains(v2);
	}

	public ArrayList<Localidad> getLocalidades() {
		return new ArrayList<Localidad>(vecinos.keySet());
	}

	public LinkedList<CableDeRed> getAristas() {
		return aristas;
	}

}
