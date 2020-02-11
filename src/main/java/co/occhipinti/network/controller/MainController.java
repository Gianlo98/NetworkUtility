package co.occhipinti.network.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;

public class MainController extends NetworkUtilsContoller{

    @FXML
    private Label label;
    
    @FXML
    private Label counter;

    @FXML
    private ListView<Pane> list;
    
    @FXML
    private ProgressBar progress;
    
    @FXML
    private Label info;

    public void addElementToList(Pane a) {
    	list.getItems().add(a);
    }
    
    public void setProgressBar(double i) {
    	this.progress.setProgress(i);
    }
    
    public void setInfo(String i) {
    	info.setText(i);
    }
    
    public void setCounter(int i) {
    	counter.setText("Devices found: " + i);
    }
	
	@Override
	protected String getViewFileName() {
		return "main.fxml";
	}
	

}
