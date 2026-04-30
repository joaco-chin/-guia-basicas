package edu.unlam.paradigmas.basicas.ej01;

public class Main {

	public static void main(String[] args) {
		Rango[] vectorRangos = {
				Rango.intervaloAbierto(2.5, 80.0),
				Rango.intervaloAbiertoIzq(-40.5, 70.0),
				Rango.intervaloCerrado(-10.6, 10.0),
				Rango.intervaloAbiertoDer(-5.6, Double.POSITIVE_INFINITY),
				Rango.intervaloAbiertoIzq(Double.NEGATIVE_INFINITY, -10.1)
		};
		
		for(int i = 0; i < vectorRangos.length; i++) {
			System.out.println(vectorRangos[i].toString());
		}
		
		System.out.println(Rango.supraRango().toString());
	}

}
