package backend;

//arista: calcula la distancia entre 2 localidades.
public class CableDeRed {

	private static int costoCable=20;
	private static Float costoDistanciaSuperaKm=(float) 0.2;
	private static int kmMaximo=300;
	private static int costoFijoDosProvincias=20;
	
	private Double distancia;
	private Localidad primerLocalidad;
	private Localidad segundaLocalidad;
	private Float costo;
	private static int radioTierraKm = 6371;

	public CableDeRed(Localidad v1, Localidad v2) {
		distancia = distanciaEnKm(v1, v2);
		primerLocalidad = v1;
		segundaLocalidad = v2;
		asignarCosto(); // Esto lo hago para poder hacer casos de tests
	}

	private void asignarCosto() {
		costo=(float) (distancia*CableDeRed.costoCable);
		
		if(kmMaximo<distancia) {
			costo=costo+costo*costoDistanciaSuperaKm;
		}
		
		if(!primerLocalidad.getProvincia().toString().equals(segundaLocalidad.getProvincia())) {
			costo=costo+costoFijoDosProvincias;
		}
		
	}

	public CableDeRed() {
	}

	public boolean tieneEstaLocalidad(Localidad localidad) {
		if (!localidadValida(localidad))
			throw new IllegalArgumentException();

		return primerLocalidad.equals(localidad) || segundaLocalidad.equals(localidad);
	}

	// calcula la distancia entre localidades y las pone en kilometros con la
	// formula de haversine
	public static double distanciaEnKm(Localidad localidadUno, Localidad localidadDos) {
		if (!localidadValida(localidadUno) || !localidadValida(localidadDos))
			throw new IllegalArgumentException();

		double x1 = gradosARadianes(localidadUno.getLatitud());
		double y1 = gradosARadianes(localidadUno.getLongitud());
		double x2 = gradosARadianes(localidadDos.getLatitud());
		double y2 = gradosARadianes(localidadDos.getLongitud());

		double diferenciaEntreLongitudes = (y2 - y1);
		double diferenciaEntreLatitudes = (x2 - x1);

		double Haversine = Math.pow(Math.sin(diferenciaEntreLatitudes / 2.0), 2)
				+ Math.cos(x1) * Math.cos(x2) * Math.pow(Math.sin(diferenciaEntreLongitudes / 2.0), 2);

		double kmDistancia = 2 * Math.atan2(Math.sqrt(Haversine), Math.sqrt(1 - Haversine));

		return radioTierraKm * kmDistancia;
	}

	private static double gradosARadianes(float grados) {
		return grados * Math.PI / 180;
	}

	private static boolean localidadValida(Localidad localidad) {
		if (localidad == null)
			return false;
		if (localidad.getLatitud() < 91 && localidad.getLongitud() < 181 && localidad.getLatitud() > -90
				&& localidad.getLongitud() > -181 && localidad.getNombre() != "" && localidad.getProvincia() != "")
			return true;

		return false;
	}

	@Override
	public boolean equals(Object otroCableDeRed) {
		if (otroCableDeRed == null || otroCableDeRed.getClass() != this.getClass())
			return false;

		CableDeRed otroCable = (CableDeRed) otroCableDeRed;

		return (this.primerLocalidad.equals(otroCable.primerLocalidad)
				&& this.segundaLocalidad.equals(otroCable.segundaLocalidad))
				|| (this.segundaLocalidad.equals(otroCable.primerLocalidad))
						&& this.primerLocalidad.equals(otroCable.segundaLocalidad);
	}

	public void setCosto(float costo) {
		if (costo > 0) {
			this.costo = costo;
		}
	}

	public Float getCosto() {
		return costo;
	}

	public void setDistancia(double distancia) {
		if (distancia > 0)
			this.distancia = distancia;
	}

	public Double getDistancia() {
		return distancia;
	}

	public Localidad getVertice1() {
		return primerLocalidad;
	}

	public Localidad getVertice2() {
		return segundaLocalidad;
	}
	
	public void setCostoCable(int costo) {
		CableDeRed.costoCable=costo;
	}
	public void setCostoDistanciaSuperaKm(Float costo) {
		CableDeRed.costoDistanciaSuperaKm=costo;
		
	}
	public void setCostoFijoDosProvincias(int costo) {
		CableDeRed.costoFijoDosProvincias=costo;
	}
	public void setKmMaximo(int kmMaximo) {
		CableDeRed.kmMaximo=kmMaximo;
	}
	public static int getCostoCable() {
		return CableDeRed.costoCable;
	}
	public static Float getcostoDistanciaSuperaKm() {
		return CableDeRed.costoDistanciaSuperaKm;
	}


}
