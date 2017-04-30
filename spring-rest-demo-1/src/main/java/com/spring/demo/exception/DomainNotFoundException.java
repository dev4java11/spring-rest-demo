package com.spring.demo.exception;

/**
 * Clase que verifica si existe el dominio en la base de datos
 * 
 * @author Administrador
 *
 */
public class DomainNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Class<?> domainType;
	private Object id;
	
	
	public DomainNotFoundException() {
		super();
	}
	
	public DomainNotFoundException(Class<?> domainType, Object id){
		super("Does not find the "+domainType.getName()+" by identifier: "+id);
		this.domainType = domainType;
		this.id = id;
	}

	
	public Class<?> getDomainType() {
		return domainType;
	}
	
	public void setDomainType(Class<?> domainType) {
		this.domainType = domainType;
	}
	
	public Object getId() {
		return id;
	}
	
	public void setId(Object id) {
		this.id = id;
	}
}
