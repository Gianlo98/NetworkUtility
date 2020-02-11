package co.occhipinti.network.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class NetworkUtils {
	
	

	public static boolean checkAvaiablePort(String host, int port) {
		boolean cs = false;
		
		try (Socket s = new Socket()){
			InetAddress ia = InetAddress.getByName(host);
			InetSocketAddress sia = new InetSocketAddress(ia, port);
			s.connect(sia);
			cs = s.isConnected();
		} catch(ConnectException e) {
			System.out.println("Port " + port + " on device " + host + " closed");
			cs = false;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return cs;
	}
	
	public static boolean isReachable(String host) {
		Process p1;
		boolean reachable = false;
		try {
			p1 = java.lang.Runtime.getRuntime().exec("ping -n 1" + host);
		    int returnVal = p1.waitFor();
		    reachable = (returnVal==0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reachable;
	}
	
	

	
	public static Map<String, String> getDevicesList(){
		Map<String, String> hosts = new HashMap<>();
				
		try {
			Process cmd = Runtime.getRuntime().exec("arp -a");
			
			BufferedReader stdInput = new BufferedReader(new 
				     InputStreamReader(cmd.getInputStream()));
				
			String s = null;
			while ((s = stdInput.readLine()) != null) {
			    if(s.startsWith("192.168", 2)) { //OR 172. OR 10.
			    	String host = s.substring(2, 15); //ERRORE  192.168.[1-9]{3}.[1-9]{3}
			    	if(host.lastIndexOf("") == -1)
			    		host = host.substring(0, 11);
			    	String mac = s.substring(24, 41);
			    	hosts.put(mac, host);
			    }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return hosts;
	}
	
}
