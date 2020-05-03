package com.sales.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.models.Product;
import com.sales.repositories.ProductRepository;



@Service
public class ProductService {
	@Autowired
	ProductRepository pr;
	// Arraylist of customers with findAll method to find them all//saving this to the object
	public ArrayList<Product> findAll()
	{
		// return the arraylist of customers
		return (ArrayList<Product>) pr.findAll();
	}
	// Save customer
	public void save(Product p)
	{
		pr.save(p);
	}
	
	// Find one piD
	public Product findOne(Long pId)
	{
		
		return pr.findOne(pId);
	}
	
	
	
	
	
}


