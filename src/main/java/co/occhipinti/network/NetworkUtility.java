package co.occhipinti.network;

import java.io.File;
import java.net.InetAddress;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import co.occhipinti.network.configuration.Configuration;
import co.occhipinti.network.configuration.Device;
import co.occhipinti.network.controller.ElementController;
import co.occhipinti.network.controller.MainController;
import co.occhipinti.network.utils.NetworkUtils;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NetworkUtility {
	static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
	static Configuration configuration = new Configuration();
	
    public static void main(String[] args) {

		try {
			configuration = mapper.readValue(new File("src/main/resources/configuration.yaml"), Configuration.class);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	
    	Platform.startup(() -> {
    		try {
	    		Stage stage = new Stage();
	    		
	    		MainController mc = new MainController();
	    		Pane pane = mc.getPane();
	    		Scene scene = new Scene(pane);
	    		stage.setTitle("Network Utility");
	    		stage.setScene(scene);
	    		stage.show();
	    		
	    		
	    		Thread t = new Thread(() -> {
	   			  
	    			Map<String, Device> dm = configuration.getDevicesMap();

	    			var w = new Object(){
	    				int i = 0;
	    			};
	    			
	    			Map<String, String> dl = NetworkUtils.getDevicesList();
	    			
	    			try {
	    				final int TIMEOUT = 100;
	    				
	    				dl.forEach((mac, host) -> {
	    					w.i++;
	    					
	    					Platform.runLater(() -> {
		  			   			mc.setProgressBar(w.i/dl.size());
		  			   			mc.setInfo(host);
		  			   			mc.setCounter(w.i);
			    			});
	    					
	    					boolean webserver = NetworkUtils.checkAvaiablePort(host, 80);
	  			   			String name = dm.containsKey(host) ? dm.get(host).getName() : "Device";
	  			   			String image = dm.containsKey(host) ? dm.get(host).getImage() : "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/Anonymous_emblem.svg/1200px-Anonymous_emblem.svg.png";
	  			   			
	  			   			ElementController ec = new ElementController(image, name, mac + " - " + host, !webserver);
		  			   		Platform.runLater(() -> {
									mc.addElementToList(ec.getPane());
			    			});
	    					
	    				});
	    				
			  			  Platform.runLater(() -> {
		  			   			mc.setInfo("Completed");
			    			});
	    			}catch(Exception e) {
	    				e.printStackTrace();
	    			}	
	    		});
	    		t.start();

	    		
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	});
    	
    }
    
    /*
     * IMAP 
     * SMTP
     */

}