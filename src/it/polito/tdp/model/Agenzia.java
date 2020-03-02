package it.polito.tdp.model;

public class Agenzia {
	
	private int cod; 
	private String nome; 
	private Referente referente; 
	private boolean preventivi; 
	private int num_prenotazioni; 
	private float fatturato; 
	private boolean cicloturismo; 
	private boolean enogastronomia;
	private boolean lgbt;
	private boolean pet_friendly;
	private boolean piccoli_gruppi;
	private boolean honey_moon;
	private boolean arte_cultura;
	private boolean benessere_bellezza;
	private boolean mice;
	private int prezzo; 
	private float punteggio; 
	
	

	public Agenzia(int cod, String nome, Referente referente, boolean preventivi, int num_prenotazioni, float fatturato,
			boolean cicloturismo, boolean enogastronomia, boolean lgbt, boolean pet_friendly, boolean piccoli_gruppi,
			boolean honey_moon, boolean arte_cultura, boolean benessere_bellezza, boolean mice, int prezzo) {
		super();
		this.cod = cod;
		this.nome = nome;
		this.referente = referente;
		this.preventivi = preventivi;
		this.num_prenotazioni = num_prenotazioni;
		this.fatturato = fatturato;
		this.cicloturismo = cicloturismo;
		this.enogastronomia = enogastronomia;
		this.lgbt = lgbt;
		this.pet_friendly = pet_friendly;
		this.piccoli_gruppi = piccoli_gruppi;
		this.honey_moon = honey_moon;
		this.arte_cultura = arte_cultura;
		this.benessere_bellezza = benessere_bellezza;
		this.mice = mice;
		this.prezzo = prezzo;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Referente getReferente() {
		return referente;
	}

	public void setReferente(Referente referente) {
		this.referente = referente;
	}

	public boolean isPreventivi() {
		return preventivi;
	}

	public void setPreventivi(boolean preventivi) {
		this.preventivi = preventivi;
	}

	public int getNum_prenotazioni() {
		return num_prenotazioni;
	}

	public void setNum_prenotazioni(int num_prenotazioni) {
		this.num_prenotazioni = num_prenotazioni;
	}

	public float getFatturato() {
		return fatturato;
	}

	public void setFatturato(float fatturato) {
		this.fatturato = fatturato;
	}

	public boolean isCicloturismo() {
		return cicloturismo;
	}

	public void setCicloturismo(boolean cicloturismo) {
		this.cicloturismo = cicloturismo;
	}

	public boolean isEnogastronomia() {
		return enogastronomia;
	}

	public void setEnogastronomia(boolean enogastronomia) {
		this.enogastronomia = enogastronomia;
	}

	public boolean isLgbt() {
		return lgbt;
	}

	public void setLgbt(boolean lgbt) {
		this.lgbt = lgbt;
	}

	public boolean isPet_friendly() {
		return pet_friendly;
	}

	public void setPet_friendly(boolean pet_friendly) {
		this.pet_friendly = pet_friendly;
	}

	public boolean isPiccoli_gruppi() {
		return piccoli_gruppi;
	}

	public void setPiccoli_gruppi(boolean piccoli_gruppi) {
		this.piccoli_gruppi = piccoli_gruppi;
	}

	public boolean isHoney_moon() {
		return honey_moon;
	}

	public void setHoney_moon(boolean honey_moon) {
		this.honey_moon = honey_moon;
	}

	public boolean isArte_cultura() {
		return arte_cultura;
	}

	public void setArte_cultura(boolean arte_cultura) {
		this.arte_cultura = arte_cultura;
	}

	public boolean isBenessere_bellezza() {
		return benessere_bellezza;
	}

	public void setBenessere_bellezza(boolean benessere_bellezza) {
		this.benessere_bellezza = benessere_bellezza;
	}

	public boolean isMice() {
		return mice;
	}

	public void setMice(boolean mice) {
		this.mice = mice;
	}

	public Agenzia() {
	}

	@Override
	public String toString() {
		String s=""; 
		s+="Agenzia: " + nome+"\n";
		s+="Referente: " + referente.getNome_cognome()+" (email: "+referente.getEmail()+", tel.: "+referente.getTelefono()+", lingua: "+referente.getLingua()+", fiera: "+referente.getFiera() +")\n";
		if(preventivi==true) 
			s+="L'agenzia ha richiesto preventivi"; 
		if(num_prenotazioni>0)
			s+= " e ha effettutato "+num_prenotazioni+" prenotazioni, facendo ottenere un fatturato netto di "+fatturato+" €.\n"; 
		s+="Interessi ["; 
		if(this.isArte_cultura())
			s+="Arte e Cultura, "; 
		if(this.isBenessere_bellezza())
			s+="Benessere e Bellezza, "; 
		if(this.isCicloturismo())
			s+="Cicloturismo, "; 
		if(this.isEnogastronomia())
			s+="Enogastronomia, ";  
		if(this.isHoney_moon())
			s+="Luna di Miele, "; 
		if(this.isLgbt())
			s+="LGBT, "; 
		if(this.isMice())
			s+="MICE, ";  
		if(this.isPet_friendly())
			s+="Pet friendly, "; 
		if(this.isPiccoli_gruppi())
			s+="Piccoli gruppi"; 
		s+="]\n"; 
		s+="Prezzo: "+prezzo+"€\n\n"; 
		return s; 
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public float getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(float punteggio) {
		this.punteggio = punteggio;
	} 
	
	
	
	

}
