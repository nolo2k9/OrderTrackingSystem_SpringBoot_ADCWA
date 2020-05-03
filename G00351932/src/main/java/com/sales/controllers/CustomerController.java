package com.sales.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sales.models.Customer;
import com.sales.models.Product;
import com.sales.services.CustomerService;

@Controller
public class CustomerController {
	// Autowiring customer service for use
	@Autowired
	CustomerService cs;

	// mapping for showCustomers.html
	@RequestMapping(value = "/showCustomers.html")
	public String listCustomers(Model model) {
		// Finding customers in an arraylist of customers
		ArrayList<Customer> customers = cs.findAll();
		// adding to model
		model.addAttribute("custs", customers);
		// return jsp
		return "allCustomers";
		
	}//listCustomers
	
	//Get request for addCustomer.html mapping
	@RequestMapping(value = "addCustomer.html", method = RequestMethod.GET)
	public String addCustomer(Model model) {
		// Finding customers in an arraylist of customers
		ArrayList<Customer> customers = cs.findAll();
		//Mapping String cName, Long cId to a hashmap.
		//Looping through and adding the name and id
		Map<String, Long> customerList = new LinkedHashMap<String, Long>();
		for (Customer c : customers) {
			customerList.put(c.getcName(), c.getcId());
		}
		// adding to model
		model.addAttribute("customerList", customerList);
		//Create new customer object
		Customer c = new Customer();
		// adding to model
		model.addAttribute("newCust", c);
		//return jsp
		return "addCustomer";

	}//addCustomer

	//Post request for addCustomer.html mapping
	@RequestMapping(value = "/addCustomer.html", method = RequestMethod.POST)
	public String addCustomerPost(@ModelAttribute("newCust") @Valid Customer c, BindingResult result) {
		//if errors occuer return the page
		if (result.hasErrors()) {

			return "addCustomer";
		}
		
		//otherwise save object
		cs.save(c);
		
		//redirect to showCustomers.html
		return "redirect:showCustomers.html";

	}//addCustomerPost

	/* Programmatically logging out
	 * I had to include this method, because when I downgraded my spring the normal 
	 * .logout() method wouldn't work in the security controller 
	 * 
	 * Reference
	 * https://stackoverflow.com/questions/18957445/how-to-perform-logout-programmatically-in-spring-3
	 */
	
	@RequestMapping(value = "/logout.html", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		session = request.getSession(false);
		
		//invalidate session
		if (session != null) {
			session.invalidate();
		}
		
		//cear cookies
		for (Cookie cookie : request.getCookies()) {
			cookie.setMaxAge(0);
		}

		return "redirect:login";
		
	}//logout

}//CustomerController
