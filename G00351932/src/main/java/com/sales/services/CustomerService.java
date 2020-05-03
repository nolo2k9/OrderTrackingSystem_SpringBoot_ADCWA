package com.sales.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.models.Customer;
import com.sales.models.Product;
import com.sales.repositories.CustomerRepositroy;

@Service
public class CustomerService {
	// AutoWiring customer repository
	@Autowired
	CustomerRepositroy cr;

	// Arraylist of customers with findAll method to find them all
	public ArrayList<Customer> findAll() {
		// return the arraylist of customers
		return (ArrayList<Customer>) cr.findAll();

	}

	// Save customer
	public void save(Customer c) {
		cr.save(c);

	}

	// Find one ciD
	public Customer findOne(Long cId) {

		return cr.findOne(cId);
	}

}//CustomerService
