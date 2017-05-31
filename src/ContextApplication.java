import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import dk.itu.spct.actuator.ContextListener;
import dk.itu.spct.model.BLIPTraceableEntity;
import dk.itu.spct.monitor.BLIPMonitor;
import dk.pervasive.jcaf.util.AbstractContextClient;

public class ContextApplication extends AbstractContextClient {

	// Blip monitor
	private BLIPMonitor monitor;

	// Context listener
	private ContextListener listener;

	public ContextApplication(String service_uri) {
		// Create and install a security manager
		super(service_uri);
		//if (System.getSecurityManager() == null) {
		//	System.setSecurityManager(new RMISecurityManager());
		//}
		try {
			monitor = new BLIPMonitor(service_uri);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		// Set up monitor
		monitor.addAddress("000ea50050ec");

		// Register listener
		listener = new ContextListener(service_uri);

		// Add entity
		BLIPTraceableEntity bte = new BLIPTraceableEntity("000ea50050ec");

		try {
			getContextService().addEntity(bte);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		new Thread(monitor).start();
		new Thread(listener).start();
	}

	public static void main(String[] args) {
		ContextApplication app = new ContextApplication("rmi://10.27.240.143/spct");
		new Thread(app).start();
	}
}
