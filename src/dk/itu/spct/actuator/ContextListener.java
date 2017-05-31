package dk.itu.spct.actuator;

import java.rmi.RemoteException;

import dk.itu.spct.model.BLIPTraceableEntity;
import dk.itu.spct.model.Location;
import dk.pervasive.jcaf.ContextEvent;
import dk.pervasive.jcaf.EntityListener;
import dk.pervasive.jcaf.EntityNotFoundException;
import dk.pervasive.jcaf.RemoteContextActuator;
import dk.pervasive.jcaf.impl.RemoteEntityListenerImpl;
import dk.pervasive.jcaf.util.AbstractContextClient;

public class ContextListener extends AbstractContextClient implements
		EntityListener {
	public ContextListener(String service_uri) {
		super(service_uri);
	}

	public void contextChanged(ContextEvent arg0) {
		System.out.println("Context changed");

	}

	public void run() {
		try {
			RemoteEntityListenerImpl listener = new RemoteEntityListenerImpl();
			listener.addEntityListener(this);
				getContextService().addEntityListener(listener,BLIPTraceableEntity.class);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}