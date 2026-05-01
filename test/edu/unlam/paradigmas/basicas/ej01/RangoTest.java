package edu.unlam.paradigmas.basicas.ej01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RangoTest {
	
	@Test
	void testConstructorAbierto() {
		Double limInf = 2.5;
		Double limSup = 5.6;
		Rango rangoAbierto = Rango.intervaloAbierto(limInf, limSup);
		
		assertEquals(false, rangoAbierto.contiene(limInf), "el rango no debe contener el límite izquierdo"); 
		assertEquals(false, rangoAbierto.contiene(limSup), "el rango no debe contener el límite derecho");
		
		assertEquals(true, rangoAbierto.equals(Rango.intervaloAbierto(limInf, limSup)), "el rango debe ser igual a sí mismo");
	}
	
	@Test
	void testConstructorCerrado() {
		Double limInf = 2.5;
		Double limSup = 5.6;
		Rango rangoCerrado = Rango.intervaloCerrado(limInf, limSup);
		
		assertEquals(true, rangoCerrado.contiene(limInf), "el rango DEBE contener el límite izquierdo");
		assertEquals(true, rangoCerrado.contiene(limSup), "el rango DEBE contener el límite derecho");
		
		assertEquals(true, rangoCerrado.equals(Rango.intervaloCerrado(limInf, limSup)), "el rango debe ser igual a sí mismo");
		
		limInf = Double.NEGATIVE_INFINITY;
		rangoCerrado = Rango.intervaloCerrado(limInf, limSup);
		assertEquals(false, rangoCerrado.contiene(limInf), "el rango cerrado no puede contener infinito por izquierda");
		
		limInf = 2.5;
		limSup = Double.POSITIVE_INFINITY;
		rangoCerrado = Rango.intervaloCerrado(limInf, limSup);
		assertEquals(false, rangoCerrado.contiene(limSup), "el rango cerrado no puede contener infinito por derecha");
	}
	
	@Test
	void constructorAbiertoIzq() {
		Double limInf = 2.5;
		Double limSup = 5.6;
		Rango rangoAbiertoIzq = Rango.intervaloAbiertoIzq(limInf, limSup);
		
		assertFalse(rangoAbiertoIzq.contiene(limInf));
		assertTrue(rangoAbiertoIzq.contiene(limSup));
		
		limInf = Double.NEGATIVE_INFINITY;
		rangoAbiertoIzq = Rango.intervaloAbiertoIzq(limInf, limSup);
		
		assertFalse(rangoAbiertoIzq.contiene(limInf), "el rango abierto a la izquierda no puede contener infinito por izquierda"); 
	}
	
	@Test
	void constructorAbiertoDer() {
		Double limInf = 2.5;
		Double limSup = 5.6;
		Rango rangoAbiertoIzq = Rango.intervaloAbiertoDer(limInf, limSup);
		
		assertTrue(rangoAbiertoIzq.contiene(limInf));
		assertFalse(rangoAbiertoIzq.contiene(limSup));
		
		limSup = Double.POSITIVE_INFINITY;
		rangoAbiertoIzq = Rango.intervaloAbiertoDer(limInf, limSup);
		
		assertFalse(rangoAbiertoIzq.contiene(limSup), "el rango abierto a la izquierda no puede contener infinito por izquierda"); 
	}
	
	@Test
	void testCompareTo() {
		Double limIzq1 = 2.5;
		Double limDer1 = 5.6;
		Rango rango1 = Rango.intervaloCerrado(limIzq1, limDer1);
		
		Double limIzq2 = 3.0;
		Double limDer2 = 3.2;
		Rango rango2 = Rango.intervaloAbierto(limIzq2, limDer2);
		
		assertEquals(0, rango1.compareTo(rango1));	
		
		assertTrue(rango1.compareTo(rango2) < 0);
		assertTrue(rango1.compareTo(Rango.intervaloCerrado(2.5, 5.4)) > 0);
		
		// Comparaciones de rangos con límites idénticos, pero de distinto tipo. 
		// Los cerrados siempre van antes: 
		
		assertTrue(rango1.compareTo(Rango.intervaloAbiertoIzq(limIzq1, limDer1)) > 0);
		assertTrue(rango1.compareTo(Rango.intervaloAbiertoDer(limIzq1, limDer1)) > 0);
		assertTrue(rango1.compareTo(Rango.intervaloAbierto(limIzq1, limDer1)) > 0);
		
		rango1 = Rango.intervaloAbiertoIzq(limIzq1, limDer1);
		
		assertTrue(rango1.compareTo(Rango.intervaloCerrado(limIzq1, limDer1)) < 0);
		assertTrue(rango1.compareTo(Rango.intervaloAbiertoDer(limIzq1, limDer1)) > 0);
		assertTrue(rango1.compareTo(Rango.intervaloAbierto(limIzq1, limDer1)) > 0);
		
		rango1 = Rango.intervaloAbiertoDer(limIzq1, limDer1);
		
		assertTrue(rango1.compareTo(Rango.intervaloCerrado(limIzq1, limDer1)) < 0);
		assertTrue(rango1.compareTo(Rango.intervaloAbiertoIzq(limIzq1, limDer1)) < 0);
		assertTrue(rango1.compareTo(Rango.intervaloAbierto(limIzq1, limDer1)) > 0);
		
		// Comparación de un rango enorme con infinito
		
		rango1 = Rango.intervaloCerrado(limIzq1, Double.MAX_VALUE);
		assertTrue(rango1.compareTo(Rango.intervaloAbiertoDer(limIzq1, Double.POSITIVE_INFINITY)) < 0);
	}
	
	@Test
	void testContieneRango() {
		Rango rango1 = Rango.intervaloCerrado(Double.MIN_VALUE, Double.MAX_VALUE);
		
		assertTrue(Rango.supraRango().contiene(rango1)); // el suprarango contiene a todos los rangos
		
		Double limIzq = 10.5;
		Double limDer = 20.6;
		rango1 = Rango.intervaloCerrado(limIzq, limDer);
		
		assertTrue(rango1.contiene(Rango.intervaloCerrado(11.1, 19.3)));
		
		Rango rango2 = Rango.intervaloAbierto(limIzq, limDer);
		
		assertTrue(rango1.contiene(rango1));
		assertTrue(rango1.contiene(rango2));
		assertFalse(rango2.contiene(rango1)); // un rango abierto no puede contener a un rango cerrado con los mismos valores
	}
	
	@Test
	void testInterseccion() {
		Rango rango1 = Rango.intervaloCerrado(20.0, 80.0);
		Rango rango2 = Rango.intervaloCerrado(60.0, 120.0);
		Rango interseccionEsperada = Rango.intervaloCerrado(60.0, 80.0);
		
		assertTrue(rango1.hayInterseccion(rango2));
		assertTrue(rango2.hayInterseccion(rango1));
		
		assertEquals(interseccionEsperada, rango1.interseccion(rango2));
		
		rango2 = Rango.intervaloCerrado(90.0, 120.0);
		interseccionEsperada = Rango.intervaloAbierto(0.0, 0.0);
		assertEquals(interseccionEsperada, rango1.interseccion(rango2));
		assertEquals(interseccionEsperada, rango2.interseccion(rango1));
		
		rango1 = Rango.intervaloAbiertoDer(20.0, 100.0);
		interseccionEsperada = Rango.intervaloAbiertoDer(90.0, 100.0);
		assertEquals(interseccionEsperada, rango1.interseccion(rango2));
		
		rango2 = Rango.intervaloAbiertoIzq(90.0, 120.0);
		interseccionEsperada = Rango.intervaloAbierto(90.0, 100.0);
		assertEquals(interseccionEsperada, rango1.interseccion(rango2));
		
		rango1 = Rango.intervaloCerrado(20.0, 80.0);
		assertEquals(rango1, rango1.interseccion(Rango.supraRango()));
	}
	
	@Test
	void testSumar() {
		Double limIzq = 4.0;
		Double limDer = 5.0;
		Rango rango1 = Rango.intervaloCerrado(limIzq, limDer);
		Rango resultadoEsperado = Rango.intervaloAbiertoDer(limIzq, Double.POSITIVE_INFINITY);
		
		assertEquals(resultadoEsperado, rango1.sumar(Rango.supraRango()));
	}
}
