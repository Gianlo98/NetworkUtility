package co.occhipinti.network.controller;

import java.io.InputStream;

import co.occhipinti.network.NetworkUtility;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public abstract class NetworkUtilsContoller {
	
	private Pane view;
	
	protected abstract String getViewFileName();
	
	public NetworkUtilsContoller() {
		try (InputStream element = NetworkUtility.class.getResourceAsStream("/" + getViewFileName())) {
			FXMLLoader loader = new FXMLLoader();
			loader.setController(this);
			view = loader.load(element);
		}catch(Exception e) {
			view = new Pane();
		}
	}
	
	public Pane getPane() {
		return view;
	}
	
}
