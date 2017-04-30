package com.spring.demo.util;

public class ApiError {

	private int status;
	private String message;
	private String developerMessage;
	
	public ApiError(){
		super();
	}
	
	public ApiError(int status, String message, String developerMessage) {
		super();
		this.status = status;
		this.message = message;
		this.developerMessage = developerMessage;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getDeveloperMessage() {
		return developerMessage;
	}
	
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
	
    @Override
    public final String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ApiError [status=").append(status).append(", message=").append(message).append(", developerMessage=").append(developerMessage).append("]");
        return builder.toString();
}
}
