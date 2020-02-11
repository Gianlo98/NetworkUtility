package co.occhipinti.network.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ElementController extends NetworkUtilsContoller{

    @FXML
    private ImageView image;

    @FXML
    private Label uri;

    @FXML
    private Label name;

    @FXML
    private Button button;
	
    public ElementController(String host) {
    	this("", host, "", true);
    }
    
    public ElementController(String imageURL, String host, String mac, boolean disabled) {
    	super();
    	this.name.setText(host);
    	this.image.setImage(new Image(imageURL));
    	this.uri.setText(mac);
    	this.button.setDisable(disabled);
    }
    
    @FXML
    void click(MouseEvent event) {
    	Desktop desktop = Desktop.getDesktop();
        try {
			desktop.browse(new URI("http://" + this.uri.getText()));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
    }
    
	@Override
	protected String getViewFileName() {
		return "element.fxml";
	}

}
