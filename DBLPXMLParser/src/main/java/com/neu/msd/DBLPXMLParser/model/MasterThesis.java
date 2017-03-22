package com.neu.msd.DBLPXMLParser.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="mastersthesis")
public class MasterThesis extends Thesis {

	final String type = "master";

	public String getType() {
		return type;
	}
}
