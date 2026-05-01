package edu.unlam.paradigmas.basicas.ej01;

import java.lang.Math;

public class Rango implements Comparable<Rango>{
	
	private final Double limIzq;
	private final Double limDer;
	private final Intervalo intervalo;
	
	// Ejercicio 3
	
	private Rango(Double limIzq, Double limDer, Intervalo intervalo) {
		
		if(limIzq.compareTo(limDer) > 0) {	// El límite izquierdo no puede ser superior al derecho.
			this.limIzq = limDer;			// En caso de que el parámetro izquierdo sea mayor al
			this.limDer = limIzq;			// derecho, invertimos los límites.
		}
		else {
			this.limIzq = limIzq;
			this.limDer = limDer;
		}
		this.intervalo = intervalo;
	}
	
	// Ejercicio 2
	
	public static Rango intervaloAbierto(Double limIzq, Double limDer) {
		return new Rango(limIzq, limDer, Intervalo.ABIERTO);
	}
	
	public static Rango intervaloAbiertoIzq(Double limIzq, Double limDer) {
		if(limDer == Double.POSITIVE_INFINITY) {							// Un intervalo no puede
			return new Rango(limIzq, limDer, Intervalo.ABIERTO);			// incluir un infinitésimo,
		}																	// por lo que se debe abrir
		return new Rango(limIzq, limDer, Intervalo.ABIERTO_IZQ);			// en el lugar donde se
	}																		// encuentra
	
	public static Rango intervaloAbiertoDer(Double limIzq, Double limDer) {
		if(limIzq == Double.NEGATIVE_INFINITY) {							
			return new Rango(limIzq, limDer, Intervalo.ABIERTO);			
		}																	
		return new Rango(limIzq, limDer, Intervalo.ABIERTO_DER);
	}
	
	public static Rango intervaloCerrado(Double limIzq, Double limDer) {
		if(limIzq == Double.NEGATIVE_INFINITY) {							
			return new Rango(limIzq, limDer, Intervalo.ABIERTO_IZQ);		
		}																	
		if(limDer == Double.POSITIVE_INFINITY) {							
			return new Rango(limIzq, limDer, Intervalo.ABIERTO_DER);		
		}
		return new Rango(limIzq, limDer, Intervalo.CERRADO);
	}
	
	// Ejercicio 7
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(obj.getClass() != this.getClass()) {
			return false;
		}
		
		final Rango otro = (Rango)obj;
		
		if(this.intervalo == otro.intervalo && this.limIzq.equals(otro.limIzq) && this.limDer.equals(otro.limDer)) {
			return true;
		}
		
		return false;
	}
	
	// Ejercicio 8
	
	@Override
	public int compareTo(Rango otro) {
		if(this.equals(otro)) {		// Verificamos si son iguales. Si lo son, retornamos 0 y evitamos hacer las siguientes comparaciones
			return 0;
		}
		
		if(!this.limIzq.equals(otro.limIzq)) { 	// Comparamos izquierda (valores)
			return this.limIzq.compareTo(otro.limIzq); 
		}
		
		if(!this.limDer.equals(otro.limDer)) {	// Empató izquierda, se decide por la derecha
			return this.limDer.compareTo(otro.limDer);
		}
		
		return this.intervalo.ordinal() - otro.intervalo.ordinal(); // Empataron izquierda y derecha, decidimos según la prioridad de los tipos de intervalo
	}
	
	// Ejercicio 9
	
	@Override 
	public String toString() {
		return  this.intervalo.toCharIzq() + "" + 
				this.limIzq + ", " + this.limDer + "" + this.intervalo.toCharDer();
	}
	
	// Ejercicio 4

	public boolean contiene(Double numero) {
		switch(this.intervalo) {
		case CERRADO:
			if(numero >= limIzq && numero <= limDer) {
				return true;
			}
			break;
		case ABIERTO:
			if(numero > limIzq && numero < limDer) {
				return true;
			}
			break;
		case ABIERTO_IZQ:
			if(numero > limIzq && numero <= limDer) {
				return true;
			}
			break;
		default:
			if(numero >= limIzq && numero < limDer) {
				return true;
			}
			break;
		}
		return false;
	}
	
	// Ejercicio 5
	
	public boolean contiene(Rango otro) {
		if(this.equals(otro)) {
			return true;
		}
		
		if(this.contiene(otro.limIzq) && this.contiene(otro.limDer)) {
			return true;
		}
		
		return false;
	}
	
	// Ejercicio 11
	
	public static Rango supraRango() {
		return Rango.intervaloAbierto(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);	// El intervalo (-∞; +∞) contiene a todos los rangos
	}
	
	// Ejercicio 12
	
	public Rango sumar(Rango otro) {
		boolean sumaIncluyeIzq = this.intervalo.getIzq();
		boolean sumaIncluyeDer = otro.intervalo.getDer();
		
		if(sumaIncluyeIzq && sumaIncluyeDer) {
			return Rango.intervaloCerrado(this.limIzq, otro.limDer);
		}
		if(sumaIncluyeIzq && !sumaIncluyeDer) {
			return Rango.intervaloAbiertoDer(this.limIzq, otro.limDer);
		}
		if(!sumaIncluyeIzq && sumaIncluyeDer) {
			return Rango.intervaloAbiertoIzq(this.limIzq, otro.limDer);
		}
		return Rango.intervaloAbierto(this.limIzq, otro.limDer);
	}
	
	// Ejercicio 6
	
	public boolean hayInterseccion(Rango otro) {
		
		Double limIzq = Math.max(this.limIzq, otro.limIzq);
		Double limDer = Math.min(this.limDer, otro.limDer);
		
		if(limDer.compareTo(limIzq) > 0) {
			return true;
		}
		
		return false;
	}
	
	// Ejercicio 13
	
	public Rango interseccion(Rango otro) {
		if(!this.hayInterseccion(otro)) {
			return Rango.intervaloAbierto(0.0, 0.0); // No hay intersección
		}
		
		Double interLimIzq = Math.max(this.limIzq, otro.limIzq);
		Double interLimDer = Math.min(this.limDer, otro.limDer);
		
		boolean interIncluyeIzq = (this.limIzq.compareTo(otro.limIzq) > 0) 						
				? this.intervalo.getIzq() : otro.intervalo.getIzq();	// del mayor límite izquierdo, guardamos si está incluído o no
		boolean interIncluyeDer = (this.limDer.compareTo(otro.limDer) < 0) 
				? this.intervalo.getDer() : otro.intervalo.getDer();	// del menor límite derecho, guardamos si está incluído o no
		
		if(interIncluyeIzq && interIncluyeDer) {						// Nos fijamos qué
			return Rango.intervaloCerrado(interLimIzq, interLimDer);	// tipo de intervalo es
		}																// según sus límites
		if(interIncluyeIzq && !interIncluyeDer) {						// y decidimos qué
			return Rango.intervaloAbiertoDer(interLimIzq, interLimDer);	// método usar en base
		}																// a eso
		if(!interIncluyeIzq && interIncluyeDer) {
			return Rango.intervaloAbiertoIzq(interLimIzq, interLimDer);
		}
		return Rango.intervaloAbierto(interLimIzq, interLimDer);
	}
	
	// Ejercicio 14
	
//	public void desplazar(Double escalar) {
//		this.limIzq += escalar;
//		this.limDer += escalar;
//	}
	
	// Ejercicio 14
	
	public Rango desplazar(Double escalar) {													// Como los rangos son inmutables,
		if(this.intervalo == Intervalo.ABIERTO) {												// al desplazarlo devolvemos un nuevo
			return Rango.intervaloAbierto(this.limIzq + escalar, this.limDer + escalar);		// rango
		}
		if(this.intervalo == Intervalo.ABIERTO_IZQ) {
			return Rango.intervaloAbiertoIzq(this.limIzq + escalar, this.limDer + escalar);
		}
		if(this.intervalo == Intervalo.ABIERTO_DER) {
			return Rango.intervaloAbiertoDer(this.limIzq + escalar, this.limDer + escalar);
		}
		return Rango.intervaloCerrado(this.limIzq + escalar, this.limDer + escalar);
	}
}
