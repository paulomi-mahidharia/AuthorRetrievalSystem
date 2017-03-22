package com.neu.msd.DBLPXMLParser.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="phdthesis")
public class PhdThesis extends Thesis {

	final String type = "phd";

	public String getType() {
		return type;
	}
}
