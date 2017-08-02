package com.concretepage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.concretepage.model.Student;


@ManagedBean(name = "order", eager = true)
@ViewScoped
@Component

public class OrderBean implements Serializable{
	
	
	@Autowired
	public StudentImpDAO studentImpDAO ;
	
	private List<Student> student ;
	
	
	public List<Student> getStudent() {
		return student;
	}


	public void setStudent(List<Student> student) {
		this.student = student;
	}


	public  List all(){
		return studentImpDAO.list() ; 
	}
	
	
	@PostConstruct
	public void init() {
	
		student = studentImpDAO.list() ; 
		
	}

	private static final long serialVersionUID = 1L;

	private static final Order[] orderList = new Order[] {

		new Order("A0001", "Intel CPU",
				new BigDecimal("700.00"), 1),
		new Order("A0002", "Harddisk 10TB",
				new BigDecimal("500.00"), 2),
		new Order("A0003", "Dell Laptop",
				new BigDecimal("11600.00"), 8),
		new Order("A0004", "Samsung LCD",
				new BigDecimal("5200.00"), 3),
		new Order("A0005", "A4Tech Mouse",
				new BigDecimal("100.00"), 10)
	};

	public Order[] getOrderList() {

		return orderList;

	}

	
	
	
	public static class Order{

		String orderNo;
		String productName;
		BigDecimal price;
		int qty;

		public Order(String orderNo, String productName,
                                BigDecimal price, int qty) {

			this.orderNo = orderNo;
			this.productName = productName;
			this.price = price;
			this.qty = qty;
		}

		public String getOrderNo() {
			return orderNo;
		}

		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public int getQty() {
			return qty;
		}

		public void setQty(int qty) {
			this.qty = qty;
		}

		

	}
}