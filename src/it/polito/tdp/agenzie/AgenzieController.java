package it.polito.tdp.agenzie;

import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.model.Agenzia;
import it.polito.tdp.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

public class AgenzieController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private ComboBox<String> tndAgenzie;

    @FXML
    private ComboBox<String> tndLingua;

    @FXML
    private CheckBox chkCicloturismo;

    @FXML
    private CheckBox chkEnogastronomia;

    @FXML
    private CheckBox chkLGBT;

    @FXML
    private CheckBox chkPetFriendly;

    @FXML
    private CheckBox chkPiccoliGruppi;

    @FXML
    private CheckBox chkHoneyMoon;

    @FXML
    private CheckBox chkArteCultura;

    @FXML
    private CheckBox chkBenessereBellezza;

    @FXML
    private CheckBox chkMICE;

    @FXML
    private ComboBox<String> tndFiera;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnRicerca;

    @FXML
    private TextArea txtResult;
    
    @FXML
    private RadioButton rdSI;

    @FXML
    private RadioButton rdNo;

    @FXML
    private Slider numPrenotazioni;

   
    private Model model;

    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    	setComboItems(); 
    }

    @FXML
    void doRicerca(ActionEvent event) {
    	txtResult.clear(); 
    	LinkedList <Agenzia> risultatoAgenzie=new LinkedList <Agenzia> (); 
    	
    	//RICERCA INFORMAZIONI AGENZIA
    	if(tndAgenzie.getValue()!=null) {
    		String nomeAgenzia=tndAgenzie.getValue(); 
    		risultatoAgenzie=model.cercaInfoAgenzia(nomeAgenzia); 
    	}
    	
    	//RICERCA AGENZIE PER FIERA
    	if(tndFiera.getValue()!=null) {
    		String nomeFiera=tndFiera.getValue(); 
    		risultatoAgenzie=model.cercaAgenzieDaFiera(nomeFiera, risultatoAgenzie); 
    	}
    	
    	//RICERCA AGENZIE PER LINGUA
    	if(tndLingua.getValue()!=null) {
    		String lingua=tndLingua.getValue(); 
    		risultatoAgenzie=model.cercaAgenzieDaLingua(lingua, risultatoAgenzie); 
    	}
    	
    	//RICERCA AGENZIA PER NUM PRENOTAZIONI
    	if(numPrenotazioni.getValue()>0){
    		int num= (int)numPrenotazioni.getValue(); 
    		risultatoAgenzie=model.cercaAgenzieDaNumPren(num, risultatoAgenzie); 
    	}
    	
    	//RICERCA AGENZIA PER INTERESSI 
    	boolean [] interessi=new boolean [9]; 
    	interessi[0]=chkCicloturismo.isSelected(); 
    	interessi[1]=chkEnogastronomia.isSelected(); 
    	interessi[2]=chkLGBT.isSelected(); 
    	interessi[3]=chkPetFriendly.isSelected(); 
    	interessi[4]=chkPiccoliGruppi.isSelected(); 
    	interessi[5]=chkHoneyMoon.isSelected(); 
    	interessi[6]=chkArteCultura.isSelected(); 
    	interessi[7]=chkBenessereBellezza.isSelected(); 
    	interessi[8]=chkMICE.isSelected(); 
    	boolean almenoUno=false; 
    	for(int i=0; i<interessi.length; i++)
    		if(interessi[i]==true) {
    			almenoUno=true; 
    			break; 
    		}
    	if(almenoUno)
    		risultatoAgenzie=model.cercaAgenzieDaInteressi(interessi, risultatoAgenzie); 
    	
    	//STAMPA RISULTATO
    	for(int i=0; i<risultatoAgenzie.size(); i++)
    		txtResult.appendText(risultatoAgenzie.get(i).toString());
    }
	
	void initialize() {
        assert tndAgenzie != null : "fx:id=\"tndAgenzie\" was not injected: check your FXML file 'AgenzieController.fxml'.";
        assert tndLingua != null : "fx:id=\"tndLingua\" was not injected: check your FXML file 'AgenzieController.fxml'.";
        assert chkCicloturismo != null : "fx:id=\"chkCicloturismo\" was not injected: check your FXML file 'AgenzieController.fxml'.";
        assert chkEnogastronomia != null : "fx:id=\"chkEnogastronomia\" was not injected: check your FXML file 'AgenzieController.fxml'.";
        assert chkLGBT != null : "fx:id=\"chkLGBT\" was not injected: check your FXML file 'AgenzieController.fxml'.";
        assert chkPetFriendly != null : "fx:id=\"chkPetFriendly\" was not injected: check your FXML file 'AgenzieController.fxml'.";
        assert chkPiccoliGruppi != null : "fx:id=\"chkPiccoliGruppi\" was not injected: check your FXML file 'AgenzieController.fxml'.";
        assert chkHoneyMoon != null : "fx:id=\"ChkHoneyMoon\" was not injected: check your FXML file 'AgenzieController.fxml'.";
        assert chkArteCultura != null : "fx:id=\"chkArteCultura\" was not injected: check your FXML file 'AgenzieController.fxml'.";
        assert chkBenessereBellezza != null : "fx:id=\"chkBenessereBellezza\" was not injected: check your FXML file 'AgenzieController.fxml'.";
        assert chkMICE != null : "fx:id=\"chkMICE\" was not injected: check your FXML file 'AgenzieController.fxml'.";
        assert tndFiera != null : "fx:id=\"tndFiera\" was not injected: check your FXML file 'AgenzieController.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'AgenzieController.fxml'.";
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'AgenzieController.fxml'.";
        assert txtResult != null : "fx:id=\"txtResul\" was not injected: check your FXML file 'AgenzieController.fxml'.";
        assert rdSI != null : "fx:id=\"rdSI\" was not injected: check your FXML file 'AgenzieController.fxml'.";
        assert rdNo != null : "fx:id=\"rdNo\" was not injected: check your FXML file 'AgenzieController.fxml'.";
        assert numPrenotazioni != null : "fx:id=\"numPrenotazioni\" was not injected: check your FXML file 'AgenzieController.fxml'.";
	}



	public void setModel(Model model2) {
		this.model=model2;
		setComboItems(); 
	}
	
	public void setComboItems() {
		tndLingua.setValue(null); 
		tndAgenzie.setValue(null);
		tndFiera.setValue(null);
		tndLingua.getItems().addAll(model.getLingue());  //ORDINARE + SELEZIONARE PRIMO
		tndAgenzie.getItems().addAll(model.getAgenzie());
		tndFiera.getItems().addAll(model.getFiere());
		chkCicloturismo.setSelected(false);
		chkEnogastronomia.setSelected(false);
    	chkLGBT.setSelected(false);
    	chkPetFriendly.setSelected(false);
    	chkPiccoliGruppi.setSelected(false);
    	chkHoneyMoon.setSelected(false);
    	chkArteCultura.setSelected(false);
    	chkBenessereBellezza.setSelected(false); 
	}
	

}
