package it.polito.tdp.itunes.model;

public class classeAdiacenze implements Comparable<classeAdiacenze>{
	private Album vertice;
	private Double bilancio;
	public classeAdiacenze(Album vertice, Double bilancio) {
		super();
		this.vertice = vertice;
		this.bilancio = bilancio;
	}
	public Album getVertice() {
		return vertice;
	}
	public void setVertice(Album vertice) {
		this.vertice = vertice;
	}
	public Double getBilancio() {
		return bilancio;
	}
	public void setBilancio(Double bilancio) {
		this.bilancio = bilancio;
	}
	@Override
	public int compareTo(classeAdiacenze o) {
		// TODO Auto-generated method stub
		return o.getBilancio().compareTo(this.getBilancio());
	}
	
	
}
