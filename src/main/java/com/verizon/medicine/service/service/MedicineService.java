package com.verizon.medicine.service.service;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.verizon.medicine.service.dao.MedicineDAO;
import com.verizon.medicine.service.dao.MedicineRepository;
import com.verizon.medicine.service.pojo.InvoiceVO;
import com.verizon.medicine.service.pojo.MedicineEntity;
import com.verizon.medicine.service.pojo.MedicinePOJO;
import com.verizon.medicine.service.pojo.MedicineWithInvoice;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.modifier.MethodArguments;

@Service
@Slf4j
public class MedicineService {
	
	@Autowired
	MedicineDAO medDAO;
	
	@Autowired
	MedicineRepository medRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
//	================================JPA==========================================
	
	public MedicineEntity saveMedicalDetails( MedicineEntity medEntity){
		return medRepository.save(medEntity);
	}
	
//	=================================JDBC=======================================
	
	public int saveMedicalDetailsJdbc (MedicinePOJO medPOJO) {
		int value = 0;
		try {
			value = medDAO.saveMedicalDetailsJdbc(medPOJO);
			
		}catch(Exception e) {
			
		}
		return value;
	}
//	===================================JPA======================================

	public List<MedicineEntity> getMedicalDetailsByPrice(int price) {
		return medRepository.getMedicalDetailsByPrice(price);
	}
//	=================================JDBC======================================

	public MedicinePOJO getMedicalDetailsByPriceJdbc(MedicinePOJO medPOJO) {
		MedicinePOJO value=null;
		try {
			value=medDAO.getMedicalDetailsByPriceJdbc(medPOJO);
		}catch (Exception e) {
			
		}
		return value;
	}
//	================================JPA===========================================
	
	public List<MedicineEntity> getMedicalDetailsByPriceAndQuantity(int price, int quantity) {
		return medRepository.getMedicalDetailsByPriceAndQuantity(price,quantity);
	}


	public List<MedicineEntity> getMedicalDetailsByPriceAndPaymentMode(int price,String paymentMode) {
		return medRepository.getMedicalDetailsByPriceAndPaymentMode(price,paymentMode);
	}
	
	public MedicineEntity updateMedicalDetails( MedicineEntity medEntity){
		return medRepository.save(medEntity);
	}
	
	
	@Transactional
	public void deleteMedicineByPrice(int price) {
		medRepository.deleteMedicibneByPrice(price);
		
	}

	public int updateMedicalDetailsByPaymentModeJdbc(MedicinePOJO medPOJO) {
		int value =0;
		try {
			value= medDAO.updateMedicalDetailsByPaymentModeJdbc(medPOJO);
			
			}catch(Exception e) {
				
			}
			
		return value;
	}
	
	
	public int deleteMedicineByPrice1(MedicinePOJO medPOJO) {
		int value =0;
		try {
			value = medDAO.deleteMedicineByPrice1(medPOJO);
			
		}catch (Exception e) {
			System.out.println("deleted medicine");
		}
	
		return value;
		
	}

	public MedicineWithInvoice getMedicineWithInvoiceByMedicineId(int id) {
		MedicineWithInvoice medicineWithInvoice = new MedicineWithInvoice();
		 MedicineEntity entity = medRepository.getById(id);
		 MedicinePOJO pojo = new MedicinePOJO();
		 pojo.setCustomerName(entity.getCustomerName());
		 pojo.setInvoiceNo(entity.getInvoiceNo());
		 pojo.setPaymentMode(entity.isPaymentMode());
		 pojo.setPrice(entity.getPrice());
		 pojo.setProductId(entity.getProductId());
		 pojo.setProductName(entity.getProductName());
		 pojo.setQuantity(entity.getQuantity());
		 medicineWithInvoice.setMedicinePOJO(pojo);
//		 HttpHeaders headers = new HttpHeaders();
//	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//	      HttpEntity <InvoiceVO> entity1 = new HttpEntity<InvoiceVO>(headers);
		InvoiceVO invo =null; //restTemplate.exchange("http://localhost:9091/invoicedetails/1", HttpMethod.GET, InvoiceVO.class);
		//invo =restTemplate.postForObject("http://localhost:9091/invoicedetails/"+entity.getInvoiceNo(), HttpMethod.GET,InvoiceVO.class);
		 ResponseEntity<InvoiceVO> response
         = restTemplate.getForEntity("http://INVOICE-SERVICE/invoice/invoicedetails/"+entity.getInvoiceNo(), InvoiceVO.class);
		 invo = response.getBody();
		 medicineWithInvoice.setInvoicePOJO(invo);
		 return medicineWithInvoice;
	}



}
