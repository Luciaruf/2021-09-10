package it.polito.tdp.yelp.model;

public class Arco {
	String locale1;
	String locale2;
	double peso;
	public Arco(String locale1, String locale2, double peso) {
		super();
		this.locale1 = locale1;
		this.locale2 = locale2;
		this.peso = peso;
	}
	public String getLocale1() {
		return locale1;
	}
	public void setLocale1(String locale1) {
		this.locale1 = locale1;
	}
	public String getLocale2() {
		return locale2;
	}
	public void setLocale2(String locale2) {
		this.locale2 = locale2;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	

}
