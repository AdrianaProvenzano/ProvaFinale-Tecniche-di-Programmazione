package it.polito.tdp.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.db.AgenzieDAO;

public class Model {
	
	AgenzieDAO daoAg; 
	TreeMap <Integer, Agenzia> IdMapAg=new TreeMap <Integer, Agenzia> (); 
	TreeMap <Integer, Referente> IdMapRe=new TreeMap <Integer, Referente> (); 
	

	public Model() {
		daoAg = new AgenzieDAO();
		IdMapRe=daoAg.loadReferenti(IdMapRe); 
		IdMapAg =daoAg.loadAgenzie(IdMapAg, IdMapRe); 
	}



	public LinkedList<String> getLingue() {
		LinkedList rs=new LinkedList <String> (); 
		rs=daoAg.loadLingue(); 
		Collections.sort(rs);
		return rs; 
	}

	public LinkedList<String> getAgenzie() {
		LinkedList <String> rs=new LinkedList <String> (); 
		LinkedList <Agenzia> ag=new LinkedList <Agenzia> (IdMapAg.values()); 
		for(int i=0; i<ag.size(); i++)
			rs.add(ag.get(i).getNome()); 
		Collections.sort(rs);
		return rs;
	}

	public LinkedList<String> getFiere() {
		LinkedList rs=new LinkedList <String> (); 
		rs=daoAg.loadFiere(); 
		Collections.sort(rs);
		return rs; 
	}

	public LinkedList<Agenzia> cercaInfoAgenzia(String nomeAgenzia) {
		boolean trovato=false; 
		int codAgenzia=0; 
		LinkedList <Agenzia> risultato=new LinkedList <Agenzia> (); 
		LinkedList <Agenzia> listaAg=new LinkedList <Agenzia> (IdMapAg.values()); 
		for(int i=0; i<listaAg.size(); i++)
			if(listaAg.get(i).getNome().compareTo(nomeAgenzia)==0) {
				trovato=true; 
				codAgenzia=listaAg.get(i).getCod(); 
			}
		if(trovato) 
			risultato.add(IdMapAg.get(codAgenzia)); 
		return risultato;
	}



	public LinkedList<Agenzia> cercaAgenzieDaFiera (String nomeFiera, LinkedList<Agenzia> risultatoAgenzie) {
		LinkedList <Agenzia> agenzie=new LinkedList <Agenzia> (); 
		agenzie=daoAg.getAgenzieDaFiera(IdMapAg, nomeFiera);
		if(!risultatoAgenzie.isEmpty()) {
			return intersezioneListe(risultatoAgenzie, agenzie); 
			}
		else
			return agenzie;
	}



	public LinkedList<Agenzia> cercaAgenzieDaLingua(String lingua, LinkedList<Agenzia> risultatoAgenzie) {
		LinkedList <Agenzia> agenzie=new LinkedList <Agenzia> (); 
		agenzie=daoAg.getAgenzieDaLingua(IdMapAg, lingua); 
		if(!risultatoAgenzie.isEmpty()) {
			return intersezioneListe(risultatoAgenzie, agenzie); 
			}
		else
			return agenzie;
	}



	public LinkedList<Agenzia> cercaAgenzieDaNumPren(int num, LinkedList<Agenzia> risultatoAgenzie) {
		LinkedList <Agenzia> agenzie=new LinkedList <Agenzia> (); 
		agenzie=daoAg.getAgenzieDaNumPren(IdMapAg, num); 
		if(!risultatoAgenzie.isEmpty()) {
			return intersezioneListe(risultatoAgenzie, agenzie); 
			}
		else
			return agenzie;
	}

	public LinkedList<Agenzia> cercaAgenzieDaInteressi(boolean[] interessi,  LinkedList<Agenzia> risultatoAgenzie) {
		LinkedList <Agenzia> agenzie=new LinkedList <Agenzia> (); 
		agenzie=daoAg.cercaInteressi(IdMapAg, interessi); 
		if(!risultatoAgenzie.isEmpty()) {
			return intersezioneListe(risultatoAgenzie, agenzie); 
			}
		else
			return agenzie;
	}
	
	public LinkedList <Agenzia> intersezioneListe(LinkedList <Agenzia> a, LinkedList <Agenzia> b){
		LinkedList <Agenzia> r=new LinkedList <Agenzia> (); 
		for(int i=0; i<a.size(); i++)
			for(int j=0; j<b.size(); j++)
				if(a.get(i)==b.get(j))
					r.add(a.get(i)); 
		return r; 
	
	}

	

}
