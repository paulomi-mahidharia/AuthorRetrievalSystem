package com.neu.msd.AuthorRetriever.model;

public class SearchCriteria {
	public Paper paper_info;
	public ServiceInfo service_info;

	public SearchCriteria(Paper paper_info, ServiceInfo service_info) {
		this.paper_info = paper_info;
		this.service_info = service_info;
	}
}
