package edu.unlam.paradigmas.basicas.ej01;

import java.lang.Math;

public class Rango {
	
	private Double limIzq;
	private Double limDer;
	private Intervalo intervalo;
	
	private Rango(Double limIzq, Double limDer, Intervalo intervalo) {
		this.limIzq = limIzq;
		this.limDer = limDer;
		this.intervalo = intervalo;
	}
	
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
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(obj.getClass() != this.getClass()) {
			return false;
		}
		
		final Rango otro = (Rango)obj;
		
		if(this.intervalo == otro.intervalo && this.limIzq == otro.limIzq && this.limDer == otro.limDer) {
			return true;
		}
		
		return false;
	}
	
	@Override 
	public String toString() {
		return  this.intervalo.toCharIzq() + "" + 
				this.limIzq + ", " + this.limDer + "" + this.intervalo.toCharDer();
	}

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
	
	public boolean contiene(Rango otro) {
		
		if(this.equals(otro)) {
			return true;
		}
		
		if(this.contiene(otro.limIzq) && this.contiene(otro.limDer)) {
			return true;
		}
		
		return false;
	}
	
	public double comparar(Rango otro) {
		
		if(this.equals(otro)) {
			return 0;
		}
		
		if(this.limIzq != otro.limIzq) {
			return this.limIzq - otro.limIzq;
		}
		
		if(this.limDer != otro.limDer) {
			return this.limDer - otro.limDer;
		}
		
		if(this.intervalo.getIzq() != otro.intervalo.getIzq()) {
			if(this.intervalo.getIzq()) {
				return 1;
			}
			return -1;
		}
		
		if(this.intervalo.getDer()) {
			return 1;
		}
		
		return -1;
	}
	
	public static Rango supraRango() {
		return Rango.intervaloAbierto(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
	}
	
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
	
	public void desplazar(Double escalar) {
		this.limIzq += escalar;
		this.limDer += escalar;
	}
	
	public boolean hayInterseccion(Rango otro) {
		
		if(this.limDer > otro.limIzq) {
			return true;
		}
		return false;
	}
	
	public Rango interseccion(Rango otro) {
		if(!this.hayInterseccion(otro)) {
			return Rango.intervaloAbierto(0.0, 0.0); // No hay intersección
		}
		
		Double interLimIzq = Math.max(this.limIzq, otro.limIzq);
		Double interLimDer = Math.min(this.limDer, otro.limDer);
		
		boolean interIncluyeIzq = (this.limIzq > otro.limIzq) 				
				? this.intervalo.getIzq() : otro.intervalo.getIzq();
		boolean interIncluyeDer = (this.limDer > otro.limDer) 
				? this.intervalo.getDer() : otro.intervalo.getDer();
		
		if(interIncluyeIzq && interIncluyeDer) {
			return Rango.intervaloCerrado(interLimIzq, interLimDer);
		}
		if(interIncluyeIzq && !interIncluyeDer) {
			return Rango.intervaloAbiertoDer(interLimIzq, interLimDer);
		}
		if(!interIncluyeIzq && interIncluyeDer) {
			return Rango.intervaloAbiertoIzq(interLimIzq, interLimDer);
		}
		return Rango.intervaloAbierto(interLimIzq, interLimDer);
	}
}
