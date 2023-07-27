package com.verizon.medicine.service.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.verizon.medicine.service.pojo.MedicinePOJO;

@Component
public class MedicineDAO {
	
	public int saveMedicalDetailsJdbc (MedicinePOJO medPOJO) throws ClassNotFoundException,SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://Guru Vihar@localhost:3306/tmg", "root", "admin");
		System.out.println("connection establishished for saved");
		PreparedStatement pstmt = con.prepareStatement("insert into tmg_medshop (product_id,product_name ,customer_name,quantity,payment_mode,invoice_no,price) values (?, ?, ?, ?, ?, ?, ?)");
		pstmt.setInt(1, medPOJO.getProductId());
		pstmt.setString(2, medPOJO.getProductName());
		pstmt.setString(3, medPOJO.getCustomerName());
		pstmt.setInt(4, medPOJO.getQuantity());
		pstmt.setString(5, medPOJO.getPaymentMode());
		pstmt.setInt(6, medPOJO.getInvoiceNo());
		pstmt.setInt(7, medPOJO.getPrice());
		int value =pstmt.executeUpdate();
		return value;
	}

	public MedicinePOJO getMedicalDetailsByPriceJdbc(MedicinePOJO medPOJO) throws ClassNotFoundException,SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MedicinePOJO mdPOJO = new MedicinePOJO();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://Guru Vihar@localhost:3306/tmg", "root", "admin");
			System.out.println("connection establishished for medicine details by price");
			pstmt = con.prepareStatement("SELECT * from tmg.tmg_medshop  WHERE price =?");
			pstmt.setInt(1, medPOJO.getPrice());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int productId =rs.getInt("product_id");
				mdPOJO.setProductId(productId);
				String productName =rs.getString("product_name");
				mdPOJO.setProductName(productName);
				String customerName =rs.getString("customer_name");
				mdPOJO.setCustomerName(customerName);
				int quantity = rs.getInt("quantity");
				mdPOJO.setQuantity(quantity);
				String paymentMode =rs.getString("payment_mode");
				mdPOJO.setPaymentMode(paymentMode);
				int invoiceNo =rs.getInt("invoice_no");
				mdPOJO.setInvoiceNo(invoiceNo);
				int price =rs.getInt("price");
				mdPOJO.setPrice(price);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(rs,pstmt,con);
		}
		return mdPOJO;
	
	
	}

	private void closeConnection(ResultSet rs, PreparedStatement pstmt, Connection con) {
		
		
	}

	public int updateMedicalDetailsByPaymentModeJdbc(MedicinePOJO medicinePOJO) throws ClassNotFoundException,SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://Guru Vihar@localhost:3306/tmg", "root", "admin");
		System.out.println("connection establishished for update payment mode");
		PreparedStatement pstmt = con.prepareStatement("update tmg_medshop set payment_mode=? where customer_name =?");
		pstmt.setString(1, medicinePOJO.getPaymentMode());
		pstmt.setString(2, medicinePOJO.getCustomerName());
		int value = pstmt.executeUpdate();
		System.out.println("value is "+value);
		pstmt.close();
		con.close();
		return value;
	}

	public int deleteMedicineByPrice1(MedicinePOJO medPOJO)throws ClassNotFoundException,SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://Guru Vihar@localhost:3306/tmg", "root", "admin");
		System.out.println("connection establishished for delete");
		PreparedStatement pstmt = con.prepareStatement("delete from tmg_medshop where price =?");
		pstmt.setInt(1, medPOJO.getPrice());
		int value = pstmt.executeUpdate();
		System.out.println("value is "+value);
		pstmt.close();
		con.close(); {
		
		return value;
		}
	}
	
}

