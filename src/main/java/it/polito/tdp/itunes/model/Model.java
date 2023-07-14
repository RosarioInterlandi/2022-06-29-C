package it.polito.tdp.itunes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {
	private SimpleWeightedGraph<Album, DefaultWeightedEdge> grafo;
	private ItunesDAO dao;
	
	public Model() {
		this.dao = new ItunesDAO();
	}
	
	public void BuildGraph(Double n) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		//Creazione vertici
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(n));
		//Creazione archi
		for (Album a : this.grafo.vertexSet()) {
			for(Album b: this.grafo.vertexSet()) {
				if (!a.equals(b)) {
					double differenza = Math.abs(a.getCostoAlbum()-b.getCostoAlbum())   ;
					if (differenza!=0.00) {
						Graphs.addEdge(this.grafo, a, b, differenza);
					}
				}
			}
		}
		
	}
	public List<Album> getVertici(){
		List<Album > result = new ArrayList<>(this.grafo.vertexSet());
		Collections.sort(result);
		return result;
		
	}
	public Set<DefaultWeightedEdge> getEdges(){
		return this.grafo.edgeSet();
	}
	
	public Double getBilancio(Album vertice) {
		double bilancio = 0.0;
		for (DefaultWeightedEdge edge: this.grafo.edgesOf(vertice)) {
			bilancio += this.grafo.getEdgeWeight(edge);
		}
		bilancio = bilancio / this.grafo.edgesOf(vertice).size();
		return bilancio;
	}
	
	public List<classeAdiacenze> StampaAdiacenze(Album source){
		List<Album> adiacenze = Graphs.neighborListOf(this.grafo, source);
		List<classeAdiacenze> result = new ArrayList<>();
		for (Album a : adiacenze) {
			double bilancioVertice = this.getBilancio(a);
			classeAdiacenze c = new classeAdiacenze(a, bilancioVertice);
			result.add(c);
		}
		Collections.sort(result);
		return result;
	}
	
	
	
	
}
