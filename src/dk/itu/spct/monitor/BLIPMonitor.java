package dk.itu.spct.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import dk.itu.spct.model.Location;
import dk.pervasive.jcaf.relationship.Located;
import dk.pervasive.jcaf.util.AbstractMonitor;

public class BLIPMonitor extends AbstractMonitor {

	private static final long serialVersionUID = 1L;
	private List<String> btAddresses;
	private boolean isRunning = false;
	private int t = 2000;
	private String blipUrl = "http://pit.itu.dk:7331/location-of/";
	private Located located;

	public BLIPMonitor(String service_uri) throws RemoteException {
		super(service_uri);
		btAddresses = new ArrayList<String>();
	}

	public void run() {

	}

	public void monitor(String arg0) throws RemoteException {
		// TODO Auto-generated method stub

	}

	public void addAddress(String btAddress) {
		btAddresses.add(btAddress);
		if (btAddresses.size() == 1)
			start();
	}

	public void setLocated(Located located) {
		this.located = located;
	}

	public void start() {
		if (!isRunning && btAddresses.size() != 0) {
			isRunning = true;
			//TEST
			int i = 0;
			while (isRunning) {
				for (String btAddress : btAddresses) {
					System.out.println("Checking for " + btAddress);
					try {
						URL url = new URL(blipUrl + btAddress);
						HttpURLConnection conn = (HttpURLConnection) url
								.openConnection();
						InputStream is = conn.getInputStream();
						BufferedReader rd = new BufferedReader(
								new InputStreamReader(is));
						String line;
						StringBuffer response = new StringBuffer();
						while ((line = rd.readLine()) != null) {
							response.append(line);
							response.append('\r');
						}
						rd.close();
						System.out.println(response.toString());

						JSONObject o = new JSONObject(response.toString());
						String location = o.optString("location");

						System.out.println("Adding " + btAddress
								+ " located at " + location + i);
						getContextService().addContextItem(btAddress,
								new Located("testlocated"),
								new Location(location + i));
						i++;
					} catch (Exception e) {
						System.out.println("Error" + e.toString());
					}
				}

				try {
					Thread.sleep(t);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void stop() {
		if (isRunning)
			isRunning = !isRunning;
	}
}
