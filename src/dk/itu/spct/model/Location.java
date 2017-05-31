package dk.itu.spct.model;

import dk.pervasive.jcaf.entity.Place;

public class Location extends Place {

	private static final long serialVersionUID = 1L;

	public Location() {
		super();
	}

	public Location(String id) {
		super(id);
	}

	public String getEntityInfo() {
		return "Location entity";
	}

	public String toString() {
		return "[" + getId() + "] ";
	}

	public String toXML() {
		String context = "";
		if (getContext() != null) {
			context = getContext().toXML();
		}
		return "<location id=\"" + getId() + "\">" + context + "</locations>";
	}
}
