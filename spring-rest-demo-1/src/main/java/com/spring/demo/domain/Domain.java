package com.spring.demo.domain;

import javax.persistence.Transient;

public interface Domain {

	@Transient
	public Object getIdDomain();
	
	@Transient
	public String getNameDomain();
}
