package com.tgsbhadohi.TGS.classes;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ResponseModel {
	private String message;
	private String status;
	private boolean alert;
	//private List<?> data;
	private Object data;
	
	public ResponseModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseModel(String message, String status, boolean alert, Object data) {
		super();
		this.message = message;
		this.status = status;
		this.alert = alert;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}
		
	public boolean isAlert() {
		return alert;
	}

	public void setAlert(boolean alert) {
		this.alert = alert;
	}

}
