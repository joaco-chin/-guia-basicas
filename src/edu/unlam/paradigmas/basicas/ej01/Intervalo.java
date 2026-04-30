package edu.unlam.paradigmas.basicas.ej01;

public enum Intervalo {
	CERRADO(true, true),
	ABIERTO(false, false),
	ABIERTO_IZQ(false, true),
	ABIERTO_DER(true, false);
	
	private boolean incluyeIzq;
	private boolean incluyeDer;
	
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
		return (this.incluyeIzq == true) ? '[' : '(';
	}
	
	public char toCharDer() {
		return (this.incluyeDer == true) ? ']' : ')';
	}
}
