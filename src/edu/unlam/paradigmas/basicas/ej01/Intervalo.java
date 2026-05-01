package edu.unlam.paradigmas.basicas.ej01;

public enum Intervalo {
	ABIERTO(false, false),
	ABIERTO_DER(true, false),
	ABIERTO_IZQ(false, true),
	CERRADO(true, true);
	
	private final boolean incluyeIzq;
	private final boolean incluyeDer;
	
	Intervalo(boolean incluyeIzq, boolean incluyeDer) {
		this.incluyeIzq = incluyeIzq;
		this.incluyeDer = incluyeDer;
	}
	
	public boolean getIzq() {
		return this.incluyeIzq;
	}
	
	public boolean getDer() {
		return this.incluyeDer;
	}
	
	public char toCharIzq() {
		return this.incluyeIzq ? '[' : '(';
	}
	
	public char toCharDer() {
		return this.incluyeDer ? ']' : ')';
	}
}
