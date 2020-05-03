package com.sales.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.models.Customer;
import com.sales.models.Order;
import com.sales.models.Product;
import com.sales.repositories.CustomerRepositroy;
import com.sales.repositories.OrderRepository;
import com.sales.repositories.ProductRepository;

@Service
public class OrderService {
	//Autowiring repositorys
	@Autowired
	OrderRepository or;
	@Autowired
	CustomerRepositroy cr;
	@Autowired
	ProductRepository pr;
	
	//customer Service
	CustomerService cs;
	ProductService ps;
	
	//Customer and Product Objects 
	private Product product;
	private Customer customer;

	// Arraylist of customers with findAll method to find them all
	public ArrayList<Order> findAll() {
		// return the arraylist of customers
		return (ArrayList<Order>) or.findAll();

	}

	public Order save(Order o) {
		// Formatting the output of the date
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		// current time
		LocalDate ld = LocalDate.now();
		// setting time and date to current
		o.setOrderDate(date.format(ld));
		// saving this to the object
		return or.save(o);
	}// save
	
	
	public void exists(Order o) {
		
		cs.findOne(o.getCust().getcId());
		ps.findOne(o.getProd().getpId());
		
	}
	
		
}//OrderService
