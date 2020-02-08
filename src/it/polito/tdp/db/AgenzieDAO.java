package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import it.polito.tdp.model.Agenzia;
import it.polito.tdp.model.Referente;

public class AgenzieDAO {
	
	public LinkedList<String> loadLingue(){
		String sql = "SELECT DISTINCT lingua from referenti";
		LinkedList<String> result = new LinkedList<String>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(rs.getString("lingua"));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
	}
	
	public TreeMap <Integer, Referente> loadReferenti(TreeMap <Integer,Referente> idR){
		String sql = "SELECT * from referenti";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(!idR.containsKey(rs.getInt("Codice"))) {
					Referente r=new Referente(); 
					r.setCodice(rs.getInt("Codice"));
					r.setNome_cognome(rs.getString("Nome_e_cognome"));
					r.setTelefono(rs.getString("telefono"));
					r.setEmail(rs.getString("email"));
					r.setLingua(rs.getString("lingua"));
					r.setFiera(rs.getString("fiera")); 
					idR.put(r.getCodice(), r); 
				}
			}

			conn.close();
			return idR;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		} 
	}
	
	public TreeMap <Integer, Agenzia> loadAgenzie(TreeMap <Integer, Agenzia> id, TreeMap <Integer,Referente> idR){
		String sql = "SELECT * from agenzia";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(!id.containsKey(rs.getInt("Codice"))) {
					int numRef=getCodReferente(rs.getString("Nome_Referente")); 
					if(numRef!=0)
						{
						Agenzia a=new Agenzia(); 
						a.setCod(rs.getInt("Codice"));
						a.setNome(rs.getString("Nome_Agenzia"));
						a.setReferente(idR.get(numRef));
						a.setPreventivi(rs.getBoolean("Preventivi_01"));
						a.setNum_prenotazioni(rs.getInt("Num_Prenotazioni")); 
						a.setFatturato(rs.getFloat("Fatturato_netto"));
						a.setCicloturismo(rs.getBoolean("Cicloturismo"));
						a.setEnogastronomia(rs.getBoolean("Enogastronomia"));
						a.setLgbt(rs.getBoolean("LGBT"));
						a.setPet_friendly(rs.getBoolean("Pet_friendly"));
						a.setPiccoli_gruppi(rs.getBoolean("Piccoli_gruppi"));
						a.setHoney_moon(rs.getBoolean("Lune_di_miele"));
						a.setArte_cultura(rs.getBoolean("ArteCultura"));
						a.setBenessere_bellezza(rs.getBoolean("BenessereBellezza"));
						a.setMice(rs.getBoolean("MICE"));
						
						id.put(a.getCod(), a);
						}
				}
			}

			conn.close();
			return id;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		} 
	}
	
	public int getCodReferente(String nomeR){
		String sql = "SELECT * from referenti where Nome_e_cognome=?";
		int ris=0; 
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nomeR);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				ris= rs.getInt("Codice"); 
			}

			conn.close();
			return ris;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		} 
	}

	public LinkedList <String>loadFiere() {
		String sql = "SELECT DISTINCT fiera from referenti";
		LinkedList<String> result = new LinkedList<String>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(rs.getString("fiera"));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
	}

	public LinkedList<Agenzia> getAgenzieDaFiera(TreeMap<Integer, Agenzia> idMapAg, String nomeFiera) {
		String sql = "SELECT a.Codice as cod FROM agenzia a, referenti r WHERE a.Nome_Referente=r.Nome_e_cognome and fiera=?";
		LinkedList<Agenzia> result = new LinkedList<Agenzia>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nomeFiera);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(idMapAg.containsKey(rs.getInt("cod")))
					result.add(idMapAg.get(rs.getInt("cod")));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public LinkedList<Agenzia> getAgenzieDaLingua(TreeMap<Integer, Agenzia> idMapAg, String lingua) {
		String sql = "SELECT a.Codice as cod FROM agenzia a, referenti r WHERE a.Nome_Referente=r.Nome_e_cognome and lingua=?";
		LinkedList<Agenzia> result = new LinkedList<Agenzia>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, lingua);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(idMapAg.containsKey(rs.getInt("cod")))
					result.add(idMapAg.get(rs.getInt("cod")));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}	}

	public LinkedList<Agenzia> getAgenzieDaNumPren(TreeMap<Integer, Agenzia> idMapAg, int num) {
		String sql = "SELECT a.Codice as cod FROM agenzia a WHERE Num_Prenotazioni>=?";
		LinkedList<Agenzia> result = new LinkedList<Agenzia>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, num);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(idMapAg.containsKey(rs.getInt("cod")))
					result.add(idMapAg.get(rs.getInt("cod")));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public LinkedList<Agenzia> cercaInteressi(TreeMap<Integer, Agenzia> idMapAg, boolean[] interessi) {
		String sql = "SELECT a.Codice as cod FROM agenzia a WHERE ";
		LinkedList<Agenzia> result = new LinkedList<Agenzia>();

		try {
			sql+=combinaInteressi(interessi); 
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(idMapAg.containsKey(rs.getInt("cod")))
					result.add(idMapAg.get(rs.getInt("cod")));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	private String combinaInteressi(boolean[] interessi) {
		String sql=""; 
		boolean primo=true; 
		if(interessi[0]==true) {
			sql+="Cicloturismo=1";
			primo=false; }
		if(interessi[1]==true && primo) {
			sql+=" Enogastronomia=1";
			primo=false; }
		else if(interessi[1]==true)
			sql+=" and Enogastronomia=1";
		if(interessi[2]==true && primo) {
			sql+=" LGBT=1";
			primo=false; }
		else if(interessi[2]==true)
			sql+=" and LGBT=1";
		if(interessi[3]==true && primo) {
			sql+=" Pet_friendly=1";
			primo=false; }
		else if(interessi[3]==true)
			sql+=" and Pet_friendly=1";
		if(interessi[4]==true && primo) {
			sql+=" Piccoli_gruppi=1";
			primo=false; }
		else if(interessi[4]==true)
			sql+=" and Piccoli_gruppi=1";
		if(interessi[5]==true && primo) {
			sql+=" Lune_di_miele=1";
			primo=false; }
		else if(interessi[5]==true)
			sql+=" and Lune_di_miele=1";
		if(interessi[6]==true && primo) {
			sql+=" ArteCultura=1";
			primo=false; }
		else if(interessi[6]==true)
			sql+=" and ArteCultura=1";
		if(interessi[7]==true && primo) {
			sql+=" BenessereBellezza=1";
			primo=false; }
		else if(interessi[7]==true)
			sql+=" and BenessereBellezza=1";
		if(interessi[8]==true && primo) {
			sql+=" MICE=1";
			primo=false; }
		else if(interessi[8]==true)
			sql+=" and MICE=1";
		
		return sql; 
	}

	public LinkedList<Agenzia> cercaPreventivi(TreeMap<Integer, Agenzia> idMapAg, boolean b) {
		String sql = "SELECT a.Codice as cod FROM agenzia a WHERE Preventivi_01=?";
		LinkedList<Agenzia> result = new LinkedList<Agenzia>();

		try { 
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			if(b)
				st.setInt(1, 1);
			else
				st.setInt(1, 0);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(idMapAg.containsKey(rs.getInt("cod")))
					result.add(idMapAg.get(rs.getInt("cod")));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	

}
