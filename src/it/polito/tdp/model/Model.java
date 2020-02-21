package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import it.polito.tdp.db.AgenzieDAO;

public class Model {
	
	AgenzieDAO daoAg; 
	TreeMap <Integer, Agenzia> IdMapAg=new TreeMap <Integer, Agenzia> (); 
	TreeMap <Integer, Referente> IdMapRe=new TreeMap <Integer, Referente> (); 
	List <Agenzia> best1; 
	List <Agenzia> best2; 
	float best_guadagno1; 
	float best_guadagno2; 

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

	public LinkedList<Agenzia> cercaAgenziaPreventivi(boolean b, LinkedList<Agenzia> risultatoAgenzie) {
		LinkedList <Agenzia> agenzie=new LinkedList <Agenzia> (); 
		agenzie=daoAg.cercaPreventivi(IdMapAg, b); 
		if(!risultatoAgenzie.isEmpty()) {
			return intersezioneListe(risultatoAgenzie, agenzie); 
			}
		else
			return agenzie;
	}

	public List<Agenzia> getInvestimenti(int spesa, int perc) {
		float p=(float) ((float)perc/100.0); 
		float sommaNuove=p*spesa; 
		float sommaVecchie=spesa-sommaNuove; 
		
		List <Agenzia> nuove=daoAg.cercaPreventivi(IdMapAg, false); 
		List <Agenzia> vecchie=daoAg.cercaPreventivi(IdMapAg, true); 
		
		//Calcola punteggio per ogni agenzia
		for(int i=0; i<nuove.size(); i++) {
			int num_interessi=calcolaInteressi(nuove.get(i)); 
			float punteggio=(float) (num_interessi*0.1); 
			nuove.get(i).setPunteggio(punteggio);
		}
		for(int i=0; i<vecchie.size(); i++) {
			float punteggio=0; 
			if(vecchie.get(i).isPreventivi())
				punteggio+=0.5;
			punteggio+=3*vecchie.get(i).getNum_prenotazioni()+vecchie.get(i).getFatturato()/100; 
			int num_interessi=calcolaInteressi(vecchie.get(i)); 
			punteggio+=(float) (num_interessi*0.1); 
			vecchie.get(i).setPunteggio(punteggio);
		}
		
		//Avvio ricorsione
		best1=new ArrayList <Agenzia>(); 
		best2=new ArrayList <Agenzia>(); 
		ArrayList<Agenzia> parziale = new ArrayList<Agenzia>(); 
		cercaN(parziale, 0, sommaNuove, nuove);
		parziale = new ArrayList<Agenzia>() ;
		cercaV(parziale, 0, sommaVecchie, vecchie); 
		List<Agenzia> best=new LinkedList<Agenzia> (best1); 
		best.addAll(best2); 
		return best;
	}
	
	private void cercaN(List<Agenzia> parziale, int i, float sommaNuove, List <Agenzia> nuove) {
		int prezzo=calcolaPrezzo(parziale);
		if(prezzo>sommaNuove)
			return; 
		if(i==nuove.size())
			return ;
	
		float guadagno=calcolaGuadagno(parziale);
		if(guadagno>best_guadagno1) {
			best1=new ArrayList<Agenzia> (parziale); 
			best_guadagno1=(float) guadagno; 
		}
		
		List <Agenzia> agenzie=daoAg.getPrezzo(IdMapAg, ((int)sommaNuove-calcolaPrezzo(parziale))); 
		if(agenzie.contains(nuove.get(i))) {
			parziale.add(nuove.get(i)); 
			cercaN(parziale, i+1, sommaNuove, nuove); 
			parziale.remove(nuove.get(i));	
		}
		cercaN(parziale, i+1, sommaNuove, nuove); 
		}
	
	
	private void cercaV(List<Agenzia> parziale, int i, float sommaVecchie, List <Agenzia> vecchie) {
		//se il prezzo che si spenderebbe con il parziale è superiore al budget da dedicare alle
		//agenzie "vecchie" -> esco
		int prezzo=calcolaPrezzo(parziale);
		if(prezzo>sommaVecchie)
			return; 
		
		//se sono arrivato alla fine della lista delle agenzie "vecchie" -> esco
		if(i==vecchie.size())
			return ;
		
		//calcolo il guadagno che si otterrebbe con il parziale (inteso come somma dei punteggi), 
		//se è quello migliore trovato finora lo salvo
		float guadagno=calcolaGuadagno(parziale);
		if(guadagno>best_guadagno2) {
			best2=new ArrayList<Agenzia> (parziale); 
			best_guadagno2=(float) guadagno; 
		}
		
		//dato il parziale corrente, ottengo una lista che contiene solo le agenzie che, dato il loro prezzo, 
		//sono ancora accessibili. Se l'agenzia in questione fa parte di questa lista provo ad aggiungerla al
		//parziale. In ogni caso, mando anche avanti la ricorsione senza quell'agenzia.
		List <Agenzia> agenzie=daoAg.getPrezzo(IdMapAg, ((int)sommaVecchie-calcolaPrezzo(parziale))); 
		if(agenzie.contains(vecchie.get(i))) {
			parziale.add(vecchie.get(i)); 
			cercaV(parziale, i+1, sommaVecchie, vecchie); 
			//System.out.println(parziale.size()); 
			parziale.remove(vecchie.get(i));	
		}
		cercaV(parziale, i+1, sommaVecchie, vecchie); 
				
		}

	private float calcolaGuadagno(List<Agenzia> parziale) {
		float guadagno=(float) 0.0; 
		for(int i=0; i<parziale.size(); i++) 
			guadagno+=parziale.get(i).getPunteggio(); 
		return guadagno;
	}

	private int calcolaPrezzo(List<Agenzia> parziale) {
		int prezzo=0; 
		for(int i=0; i<parziale.size(); i++)
			prezzo+=parziale.get(i).getPrezzo(); 
		return prezzo;
	}

	public int calcolaInteressi(Agenzia a) {
		int p=0; 
		if(a.isArte_cultura())
			p++; 
		if(a.isBenessere_bellezza())
			p++; 
		if(a.isCicloturismo())
			p++; 
		if(a.isEnogastronomia())
			p++; 
		if(a.isHoney_moon())
			p++; 
		if(a.isLgbt())
			p++; 
		if(a.isMice())
			p++; 
		if(a.isPet_friendly())
			p++;
		if(a.isPiccoli_gruppi())
			p++; 
		return p; 
	}

}
