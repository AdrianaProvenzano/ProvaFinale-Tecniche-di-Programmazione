package it.polito.tdp.model;

public class Referente {
	
	private int codice; 
	private String nome_cognome; 
	private String telefono; 
	private String email; 
	private String lingua; 
	private String fiera;
	
	public Referente(int codice, String nome_cognome, String telefono, String email, String lingua, String fiera) {
		this.codice = codice;
		this.nome_cognome = nome_cognome;
		this.telefono = telefono;
		this.email = email;
		this.lingua = lingua;
		this.fiera = fiera;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getNome_cognome() {
		return nome_cognome;
	}

	public void setNome_cognome(String nome_cognome) {
		this.nome_cognome = nome_cognome;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLingua() {
		return lingua;
	}

	public void setLingua(String lingua) {
		this.lingua = lingua;
	}

	public String getFiera() {
		return fiera;
	}

	public void setFiera(String fiera) {
		this.fiera = fiera;
	}

	public Referente() {
	} 
	
	
	

}
