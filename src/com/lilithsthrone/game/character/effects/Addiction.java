package com.lilithsthrone.game.character.effects;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.2.0
 * @version 0.2.0
 * @author Innoxia
 */
public class Addiction implements XMLSaving {

	private FluidType fluid;
	private long lastTimeSatisfied;
	private List<String> providerIDs;
	
	public Addiction(FluidType fluid, long lastTimeSatisfied) {
		this.fluid = fluid;
		this.lastTimeSatisfied = lastTimeSatisfied;
		this.providerIDs = new ArrayList<>();
	}
	
	public Addiction(FluidType fluid, long lastTimeSatisfied, String providerID) {
		this.fluid = fluid;
		this.lastTimeSatisfied = lastTimeSatisfied;
		this.providerIDs = new ArrayList<>();
		this.providerIDs.add(providerID);
	}
	
	public Addiction(FluidType fluid, long lastTimeSatisfied, List<String> providerIDs) {
		this.fluid = fluid;
		this.lastTimeSatisfied = lastTimeSatisfied;
		this.providerIDs = providerIDs;
	}

	@Override
	public boolean equals (Object o) {
		if(super.equals(o)) {
			return (o instanceof Addiction)
					&& ((Addiction)o).getFluid().equals(this.getFluid())
					&& ((Addiction)o).getLastTimeSatisfied() == this.getLastTimeSatisfied()
					&& ((Addiction)o).getProviderIDs().equals(this.getProviderIDs());
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + this.getFluid().hashCode();
		result = 31 * result + (int)this.getLastTimeSatisfied();
		result = 31 * result + this.getProviderIDs().hashCode();
		return result;
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("addiction");
		parentElement.appendChild(element);

		CharacterUtils.addAttribute(doc, element, "fluid", this.getFluid().toString());
		CharacterUtils.addAttribute(doc, element, "lastTimeSatisfied", String.valueOf(this.getLastTimeSatisfied()));
		
		Element innerElement = doc.createElement("providerIDs");
		element.appendChild(innerElement);
		for(String id : this.getProviderIDs()) {
			Element idElement = doc.createElement("id");
			innerElement.appendChild(idElement);
			CharacterUtils.addAttribute(doc, idElement, "value", id);
		}
		
		return element;
	}

	public static Addiction loadFromXML(StringBuilder log, Element parentElement, Document doc) {
		
		List<String> IDs = new ArrayList<>();
		Element element = (Element)parentElement.getElementsByTagName("providerIDs").item(0);
		for(int i=0; i<element.getElementsByTagName("id").getLength(); i++){
			Element e = ((Element)element.getElementsByTagName("id").item(i));
			IDs.add(e.getAttribute("value"));
		}
		
		return new Addiction(FluidType.valueOf(parentElement.getAttribute("fluid")),
				Long.valueOf(parentElement.getAttribute("lastTimeSatisfied")),
				IDs);
	}
	
	public FluidType getFluid() {
		return fluid;
	}
	
	public void setFluid(FluidType fluid) {
		this.fluid = fluid;
	}
	
	public long getLastTimeSatisfied() {
		return lastTimeSatisfied;
	}
	
	public void setLastTimeSatisfied(long lastTimeSatisfied) {
		this.lastTimeSatisfied = lastTimeSatisfied;
	}
	
	public List<String> getProviderIDs() {
		return providerIDs;
	}
	
	public void addProviderID(String providerID) {
		getProviderIDs().add(providerID);
	}
}
