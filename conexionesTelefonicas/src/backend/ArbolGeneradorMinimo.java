package backend;

import java.util.LinkedList;

public class ArbolGeneradorMinimo {

	static GrafoLocalidad grafoAGM;
	static LinkedList<Localidad> verticesMarcados;

	public static GrafoLocalidad prim(GrafoLocalidad grafo) {
		if (grafo == null || !BFS.esConexo(grafo)) 
			throw new IllegalArgumentException();

		// Agrego un vertice el cual sera el vertice origen
		Localidad origen = grafo.getLocalidades().get(0);
		grafoAGM = new GrafoLocalidad(origen);

		verticesMarcados = new LinkedList<Localidad>();
		verticesMarcados.add(origen);

		CableDeRed aristaMin = new CableDeRed();
		Localidad verticeNoMarcado = null;
		
		LinkedList<CableDeRed> aristas = grafo.getAristas();

		int cantAristas = 0;
		while (cantAristas < grafo.tamanio() - 1) { 

			aristaMin.setCosto(Float.POSITIVE_INFINITY);

			// Recorro la lista de aristas del grafo conexo pasado como parametro
			for (CableDeRed arista : aristas) 
				if (soloUnoMarcado(arista) && aristaEsMenorQueAristaMin(arista, aristaMin))
					aristaMin = arista;
			
			verticeNoMarcado = obtenerNoMarcado(aristaMin);
			
			// Agrego el vertice no marcado a la lista de marcados.
			grafoAGM.agregarVertice(verticeNoMarcado);
			verticesMarcados.add(verticeNoMarcado);

			// Agrego la arista al grafo AGM
			grafoAGM.agregarArista(aristaMin.getVertice1(), aristaMin.getVertice2());
			cantAristas++;
			
			aristas.remove(aristaMin);
		}

		return grafoAGM;
	}

	private static boolean aristaEsMenorQueAristaMin(CableDeRed arista, CableDeRed aristaMin) {
		return arista.getCosto() < aristaMin.getCosto();
	}

	private static boolean soloUnoMarcado(CableDeRed arista) {
		boolean primerCaso = verticesMarcados.contains(arista.getVertice1())
				&& !verticesMarcados.contains(arista.getVertice2());
		boolean segundoCaso = !verticesMarcados.contains(arista.getVertice1())
				&& verticesMarcados.contains(arista.getVertice2());
		return primerCaso || segundoCaso;
	}

	private static Localidad obtenerNoMarcado(CableDeRed arista) {
		if (verticesMarcados.contains(arista.getVertice1()))
			return arista.getVertice2();
		return arista.getVertice1();
	}

}
