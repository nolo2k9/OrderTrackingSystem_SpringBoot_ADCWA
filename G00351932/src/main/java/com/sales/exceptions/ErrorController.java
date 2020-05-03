package com.sales.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class ErrorController {

	// Variables for errors
	private String header;
	private String error;
	private Long productId;
	private Long customerId;
	private int qty;

	// Getters and setters
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

}
