package co.occhipinti.network.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configuration {

	private List<Device> devices;

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
	
	public Map<String, Device> getDevicesMap(){
		Map<String, Device> dm = new HashMap<>();
		devices.forEach(device -> {
			dm.put(device.getDevice(), device);
		});
		
		return dm;
	}
}
