package com.verizon.medicine.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.verizon.medicine.service.pojo.MedicineEntity;

public interface MedicineRepository extends JpaRepository<MedicineEntity, Integer>{

	@Query(value="SELECT * from tmg.tmg_medshop  WHERE price =:price", nativeQuery=true)
    public List<MedicineEntity> getMedicalDetailsByPrice(@Param("price") int price);
	
	

	@Query(value="SELECT * from tmg.tmg_medshop  WHERE price =:price and quantity=:quantity", nativeQuery=true)
	public List<MedicineEntity> getMedicalDetailsByPriceAndQuantity(@Param("price")int price,@Param("quantity") int quantity);


	@Query(value="SELECT * from tmg.tmg_medshop  WHERE price =:price and payment_mode=:paymentMode", nativeQuery=true)
	public List<MedicineEntity> getMedicalDetailsByPriceAndPaymentMode(@Param("price")int price,@Param("paymentMode") String paymentMode);
	
	
	@Modifying
	@Query(value="delete from tmg.tmg_medshop where price=:price", nativeQuery=true)
    public void deleteMedicibneByPrice(@Param("price") int price);
	
}
