package backend;

import java.util.Objects;

public class Localidad {
	private String nombre;
	private String provincia;
	private Float latitud;
	private Float longitud;

	public Localidad(String _nombreLocalidad, String _provinciaLocalidad, double _latitud, double _longitud) 
	{
		this.nombre = _nombreLocalidad;
		this.provincia = _provinciaLocalidad;
		this.latitud = (float) _latitud;
		this.longitud = (float) _longitud;
	};

	public String getNombre() 
	{
		return nombre;
	}

	public String getProvincia() 
	{
		return provincia;
	}

	public Float getLatitud() 
	{
		return latitud;
	}

	public Float getLongitud() 
	{
		return longitud;
	}
	
	@Override
	public boolean equals(Object otraLocalidad) {
		if(otraLocalidad == null || otraLocalidad.getClass() != this.getClass()) 
		{
			return false;
		}
		
		Localidad otra = (Localidad) otraLocalidad;
		
		return this.provincia.equals(otra.getProvincia()) 
					&&	this.getNombre().equals(otra.nombre);
	}
	
	@Override
	public String toString() {
		String localidad = "LOCALIDAD: " + this.nombre;
		String provincia = " PROVINCIA: " + this.provincia;
		String latitud 	 = " LATITUD: "   + this.latitud;
		String longitud  = " LONGITUD: "  + this.longitud;
		
		return localidad + provincia + latitud + longitud;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nombre, provincia);
	}

}
