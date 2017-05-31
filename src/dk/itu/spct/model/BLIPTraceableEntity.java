package dk.itu.spct.model;

import dk.pervasive.jcaf.entity.GenericEntity;

public class BLIPTraceableEntity extends GenericEntity {

	private static final long serialVersionUID = 1L;

	private String id;

	public BLIPTraceableEntity() {
		super();
	}

	public BLIPTraceableEntity(String id) {
		super(id);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getEntityInfo() {
		return "BLIPTraceable entity";
	}

	public String toString() {
		return "[" + getId() + "] ";
	}

	public String toXML() {
		String context = "";
		if (getContext() != null) {
			context = getContext().toXML();
		}
		return "<bliptraceableentity id=\"" + getId() + "\">" + context
				+ "</bliptraceableentity>";
	}

}
