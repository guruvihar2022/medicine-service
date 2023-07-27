package com.verizon.medicine.service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.medicine.service.pojo.MedicineEntity;
import com.verizon.medicine.service.pojo.MedicinePOJO;
import com.verizon.medicine.service.pojo.MedicineWithInvoice;
import com.verizon.medicine.service.service.MedicineService;

@RestController
@RequestMapping("/medicine")
public class MedicineController {
	
	@Autowired
	MedicineService medService;
	
	@RequestMapping("/message")
	public String getMessage() {		
		return "Hello , wellcome to medplus";
	}
//=============================JPA========================================	
	
	@RequestMapping("/saveMedicalDetails")
	public MedicineEntity saveMedicalDetails(@RequestBody MedicinePOJO medPOJO) {
		MedicineEntity medEntity = new MedicineEntity();
		copyPOJOToEntity(medPOJO,medEntity);
		medEntity = medService.saveMedicalDetails(medEntity);
		return medEntity;
		
	}
	
//	=============================JDBC==========================================
	
	@RequestMapping("saveMedicalDetailsJdbc")
	public int saveMedicalDetailsJdbc (@RequestBody MedicinePOJO medPOJO) {
		int value = medService.saveMedicalDetailsJdbc(medPOJO);
		return value;
	}
	
//	===============================JPA==========================================
	
	@RequestMapping("/getMedicalDetailsByPrice")
	public List<MedicineEntity> getMedicalDetailsByPrice(@RequestBody MedicinePOJO medPOJO){
		List<MedicineEntity> medicineList = new ArrayList<MedicineEntity>();
		medicineList = medService.getMedicalDetailsByPrice(medPOJO.getPrice());
		return medicineList;
	}
//	===============================JDBC======================================
	
	@RequestMapping("getMedicalDetailsByPriceJdbc")
	public MedicinePOJO getMedicalDetailsByPriceJdbc (@RequestBody MedicinePOJO medPOJO) {
		MedicinePOJO value = medService.getMedicalDetailsByPriceJdbc(medPOJO);
		return value;
	}
//	=================================JPA========================================
	
	@RequestMapping("/getMedicalDetailsByPriceAndQuantity")
	public List<MedicineEntity> getMedicalDetailsByPriceAndQuantity(@RequestBody MedicinePOJO medPOJO){
		List<MedicineEntity> medicineList = new ArrayList<MedicineEntity>();
		medicineList = medService.getMedicalDetailsByPriceAndQuantity(medPOJO.getPrice(),medPOJO.getQuantity());
		return medicineList;
	}
	
	@RequestMapping("/getMedicalDetailsByPriceAndPaymentMode")
	public List<MedicineEntity> getMedicalDetailsByPriceAndPaymentMode(@RequestBody MedicinePOJO medPOJO){
		List<MedicineEntity> medicineList1 = new ArrayList<MedicineEntity>();
		medicineList1 = medService.getMedicalDetailsByPriceAndPaymentMode(medPOJO.getPrice(),medPOJO.getPaymentMode());
		return medicineList1;
	}
	
	@RequestMapping("/deleteMedicineByPrice")
	public String deleteMedicineByPrice(@RequestBody MedicinePOJO medPOJO){
		medService.deleteMedicineByPrice(medPOJO.getPrice());
		return "deleted successfully";
	}
	
	
	
	@RequestMapping("/updateMedicalDetails")
	public MedicineEntity updateMedicalDetails(@RequestBody MedicinePOJO medPOJO) {
		MedicineEntity medEntity = new MedicineEntity();
		copyPOJOToEntity(medPOJO,medEntity);
		medEntity = medService.updateMedicalDetails(medEntity);
		return medEntity;
	}
	
	@RequestMapping("updateMedicalDetailsByPaymentModeJdbc")
	public int updateMedicalDetailsByPaymentModeJdbc (@RequestBody MedicinePOJO medPOJO) {
		int value = medService.updateMedicalDetailsByPaymentModeJdbc(medPOJO);
		return value;
	}
	
	@RequestMapping("/deleteMedicineByPriceJdbc")
	public int deleteMedicineByPrice1(@RequestBody MedicinePOJO medPOJO) {
		int value = medService.deleteMedicineByPrice1(medPOJO);
		return value;
	}
	
	@GetMapping("/medicineWithInvoice/{id}")
	public MedicineWithInvoice medicineWithInvoiceByMedicineId(@PathVariable int id) {
		return medService.getMedicineWithInvoiceByMedicineId(id);
	}

	
	
	
	private void copyPOJOToEntity(MedicinePOJO medPOJO, MedicineEntity medEntity) {
		medEntity.setProductId(medPOJO.getProductId());
		medEntity.setProductName(medPOJO.getProductName());
		medEntity.setCustomerName(medPOJO.getCustomerName());
		medEntity.setQuantity(medPOJO.getQuantity());
		medEntity.setPaymentMode(medPOJO.getPaymentMode());
		medEntity.setInvoiceNo(medPOJO.getInvoiceNo());
		medEntity.setPrice(medPOJO.getPrice());				
	}
}