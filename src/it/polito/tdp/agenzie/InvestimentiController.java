package it.polito.tdp.agenzie;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.model.Agenzia;
import it.polito.tdp.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class InvestimentiController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtSpesa;

    @FXML
    private Slider percNuove;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnRicerca;

    @FXML
    private TextArea txtResult;

    @FXML
    private Label NumResults;
    
    @FXML
    private Model model;
    
    @FXML
    private Button btnAgenzie;

    @FXML
    void doAgenzie(ActionEvent event) throws IOException {
    	Stage stage = null;
    	BorderPane root = null;
        stage = (Stage) btnAgenzie.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AgenzieController.fxml"));
        root = loader.load();
        AgenzieController controller=loader.getController();
        Model model=new Model(); 
        controller.setModel(model);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    	percNuove.setValue(0);
    	txtSpesa.clear();
    	NumResults.setText(""); 
    }

    @FXML
    void doRicerca(ActionEvent event) {
    	txtResult.clear();
    	List <Agenzia> result=new ArrayList<Agenzia>(); 
    	if(txtSpesa!=null) {
    		String spesa=txtSpesa.getText(); 
    		int perc=(int) percNuove.getValue();
    		boolean numeric=true; 
    		try {
    			 int s=Integer.parseInt(spesa); 
    			 result=model.getInvestimenti(s, perc); 
    			 for(int i=0; i<result.size(); i++)
    		    	txtResult.appendText(result.get(i).toString());
    			 NumResults.setText("Numero risultati: "+result.size());
    			}
    		catch(NumberFormatException e) {
    			txtResult.appendText("Inserire un numero valido!"); 
    		}
    	}
    	
    }

    @FXML
    void initialize() {
        assert txtSpesa != null : "fx:id=\"txtSpesa\" was not injected: check your FXML file 'InvestimentiController.fxml'.";
        assert percNuove != null : "fx:id=\"percNuove\" was not injected: check your FXML file 'InvestimentiController.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'InvestimentiController.fxml'.";
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'InvestimentiController.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'InvestimentiController.fxml'.";
        assert NumResults != null : "fx:id=\"NumResults\" was not injected: check your FXML file 'InvestimentiController.fxml'.";
        assert btnAgenzie != null : "fx:id=\"btnAgenzie\" was not injected: check your FXML file 'InvestimentiController.fxml'.";
        NumResults.setFont(Font.font ("System", FontPosture.ITALIC, 12));

    }

	public void setModel(Model model) {
		this.model=model; 
		
	}
}
