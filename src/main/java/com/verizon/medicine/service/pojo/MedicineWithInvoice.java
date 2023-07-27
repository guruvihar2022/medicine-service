package com.verizon.medicine.service.pojo;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MedicineWithInvoice {
	
	private MedicinePOJO medicinePOJO;
	private InvoiceVO invoicePOJO;
	public MedicinePOJO getMedicinePOJO() {
		return medicinePOJO;
	}
	public void setMedicinePOJO(MedicinePOJO medicinePOJO) {
		this.medicinePOJO = medicinePOJO;
	}
	public InvoiceVO getInvoicePOJO() {
		return invoicePOJO;
	}
	public void setInvoicePOJO(InvoiceVO invoicePOJO) {
		this.invoicePOJO = invoicePOJO;
	}
	
	

}
