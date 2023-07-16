package it.polito.tdp.yelp.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.yelp.db.YelpDao;

public class Model {
	YelpDao dao;
	Graph<String, DefaultWeightedEdge> graph;
	List<Arco> archi;

	public Model() {
		this.dao = new YelpDao();
		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.archi = new ArrayList<>();
	}
	
	public List<String> getCities(){
		return this.dao.getCities();
	}
	
	public Graph creaGrafo(String città) {
		this.archi = new ArrayList<>();
		
		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.graph, this.dao.getVertices(città));
		
		for(String s : this.graph.vertexSet()) {
			for(String s2 : this.graph.vertexSet()) {
				if(s.compareTo(s2)!=0) {
					archi.add(new Arco(s,s2,getPeso(s,s2)));
					Graphs.addEdge(this.graph, s, s2, getPeso(s,s2));
				}
			}
		}
		
		
		return this.graph;
	}

	private double getPeso(String s, String s2) {
		Business trovato = this.dao.trovato(s);
		Business trovato1 = this.dao.trovato(s2);
		
		LatLng latlngS = new LatLng(trovato.getLatitude(),trovato.getLongitude());
		LatLng latlngS2 = new LatLng(trovato1.getLatitude(),trovato1.getLongitude());

		double peso = LatLngTool.distance(latlngS, latlngS2, LengthUnit.KILOMETER);
		
		
		return peso;
	}
	
	public List<Arco> getDistante(String locale){
		
		List<Arco> restituire = new ArrayList<>();
		double peso=0.0;
		
		for(Arco a: this.archi) {
			if(a.getLocale1().compareTo(locale)==0 || a.getLocale2().compareTo(locale)==0) {
				if(a.getPeso()>peso) {
					peso= a.getPeso();
				}
			}
		}
		
		for(Arco aa: this.archi) {
			if(aa.getLocale1().compareTo(locale)==0 || aa.getLocale2().compareTo(locale)==0) {
				if(aa.getPeso()==peso) {
					if(!restituire.contains(aa)) {
						restituire.add(aa);
					}
				}
			}
		}
		
		return restituire;
		
	}
	
}
