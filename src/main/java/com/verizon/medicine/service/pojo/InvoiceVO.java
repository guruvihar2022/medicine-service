package com.verizon.medicine.service.pojo;

import java.util.Date;

public class InvoiceVO {
	private int invoiceNo;
	private Date invoiceDate;
	private String invoiceBy;
	public int getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getInvoiceBy() {
		return invoiceBy;
	}
	public void setInvoiceBy(String invoiceBy) {
		this.invoiceBy = invoiceBy;
	}
	
	

}
