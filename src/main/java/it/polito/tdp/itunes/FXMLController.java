/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.itunes;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.itunes.model.Album;
import it.polito.tdp.itunes.model.Model;
import it.polito.tdp.itunes.model.classeAdiacenze;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAdiacenze"
    private Button btnAdiacenze; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnPercorso"
    private Button btnPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="cmbA1"
    private ComboBox<Album> cmbA1; // Value injected by FXMLLoader

    @FXML // fx:id="cmbA2"
    private ComboBox<Album> cmbA2; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtX"
    private TextField txtX; // Value injected by FXMLLoader

    @FXML
    void doCalcolaAdiacenze(ActionEvent event) {
    	//Non faccio controlli per vedere se il grafo è stato creato perchè se viene scatenata
    	//questa funzione lo è di sicuro
    	
    	Album source = this.cmbA1.getValue();
    	if (source == null) {
    		txtResult.setText("Scegli un album a1");
    		return;
    	}
    	//Se sono qui ho tutti i valor corretti
    	List<classeAdiacenze> lista = this.model.StampaAdiacenze(source);
    	txtResult.clear();
    	for (classeAdiacenze c: lista) {
    		txtResult.appendText(c.getVertice().getTitle()+"="+c.getBilancio()+"\n");
    	}
    	this.btnPercorso.setDisable(false);
    	this.cmbA2.setDisable(false);
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	double prezzoMinimo = 0.0;
    	try {
    	  prezzoMinimo = Double.parseDouble(this.txtN.getText()) ;
    	}catch(NumberFormatException n) {
    		txtResult.setText("Inserisci un valore minimo di prezzo");
    		return;
    	}
    	//Se sono qui i valori sono corretti
    	this.model.BuildGraph(prezzoMinimo);
    	if (this.model.getVertici().size()!=0) {
    		txtResult.setText("Grafo creato con successo: "+ this.model.getVertici().size()+"---"+this.model.getEdges().size());
        	this.cmbA1.setDisable(false);
        	
        	this.btnAdiacenze.setDisable(false);
    		this.cmbA1.getItems().clear();
    		this.cmbA2.getItems().clear();
    		this.cmbA1.getItems().addAll(this.model.getVertici());
    		this.cmbA2.getItems().addAll(this.model.getVertici());
    		
    	}
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAdiacenze != null : "fx:id=\"btnAdiacenze\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbA1 != null : "fx:id=\"cmbA1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbA2 != null : "fx:id=\"cmbA2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtX != null : "fx:id=\"txtX\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    
    public void setModel(Model model) {
    	this.model = model;
    	this.cmbA1.setDisable(true);
    	this.cmbA2.setDisable(true);
    	this.btnAdiacenze.setDisable(true);
    	this.btnPercorso.setDisable(true);
    }
}
