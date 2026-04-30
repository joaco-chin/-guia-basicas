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
		System.out.println(rangoAbierto.toString());
	}
	
	@Test
	void testConstructorCerrado() {
		Double limInf = 2.5;
		Double limSup = 5.6;
		Rango rangoCerrado = Rango.intervaloCerrado(limInf, limSup);
		
		assertEquals(true, rangoCerrado.contiene(limInf), "el rango DEBE contener el límite izquierdo");
		assertEquals(true, rangoCerrado.contiene(limSup), "el rango DEBE contener el límite derecho");
		
		assertEquals(true, rangoCerrado.equals(Rango.intervaloCerrado(limInf, limSup)), "el rango debe ser igual a sí mismo");
		System.out.println(rangoCerrado.toString());
		
		limInf = Double.NEGATIVE_INFINITY;
		rangoCerrado = Rango.intervaloCerrado(limInf, limSup);
		assertEquals(false, rangoCerrado.contiene(limInf), "el rango cerrado no puede contener infinito por izquierda");
		System.out.println(rangoCerrado.toString());
		
		limInf = 2.5;
		limSup = Double.POSITIVE_INFINITY;
		rangoCerrado = Rango.intervaloCerrado(limInf, limSup);
		assertEquals(false, rangoCerrado.contiene(limSup), "el rango cerrado no puede contener infinito por derecha");
		System.out.println(rangoCerrado.toString());
	}
}
