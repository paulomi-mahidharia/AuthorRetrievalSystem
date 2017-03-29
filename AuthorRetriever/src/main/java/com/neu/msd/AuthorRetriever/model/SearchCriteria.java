package com.neu.msd.AuthorRetriever.model;

public class SearchCriteria {
	private Paper paperInfo;
	private ServiceInfo serviceInfo;
	private boolean union;

	public SearchCriteria(Paper paperInfo, ServiceInfo serviceInfo, boolean union) {
		this.paperInfo = paperInfo;
		this.serviceInfo = serviceInfo;
		this.union = union;
	}
	
	public SearchCriteria() {
		// TODO Auto-generated constructor stub
	}

	public Paper getPaperInfo() {
		return paperInfo;
	}

	public void setPaperInfo(Paper paperInfo) {
		this.paperInfo = paperInfo;
	}

	public ServiceInfo getServiceInfo() {
		return serviceInfo;
	}

	public void setServiceInfo(ServiceInfo serviceInfo) {
		this.serviceInfo = serviceInfo;
	}

	public boolean isUnion() {
		return union;
	}

	public void setUnion(boolean union) {
		this.union = union;
	}
}
