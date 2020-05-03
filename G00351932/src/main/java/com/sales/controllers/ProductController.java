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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sales.models.Product;
import com.sales.services.ProductService;

@Controller
@SessionAttributes("product")
public class ProductController {
	// Autowiring for use in controller
	@Autowired
	ProductService ps;

	// request mapping for showProducts
	@RequestMapping(value = "/showProducts.html")
	public String allProducts(Model model) {
		// arraylist of products implemented using findall()
		ArrayList<Product> product = ps.findAll();
		// adding to model
		model.addAttribute("product", product);
		// return relevant jsp
		return "allProducts";

	}// allProducts

	// Get request mapping to addProduct.html
	@RequestMapping(value = "addProduct.html", method = RequestMethod.GET)
	public String addProduct(Model model) {
		ArrayList<Product> products = ps.findAll();
		// Mapping productList to a new linkedHashmap
		// Looping and Pulling out desc and qtyInstock
		Map<String, Integer> productList = new LinkedHashMap<String, Integer>();
		for (Product p : products) {
			productList.put(p.getpDesc(), p.getQtyInStock());
		}
		// add to model
		model.addAttribute("productList", productList);
		// new model object
		Product p = new Product();
		// add attribute
		model.addAttribute("nProd", p);
		// return jsp
		return "addProduct";

	}// addProduct

	// Post request mapping to addProduct.html
	@RequestMapping(value = "/addProduct.html", method = RequestMethod.POST)
	public String addPersonPost(@ModelAttribute("nProd") @Valid Product p, BindingResult result) {
		// if errors are contained in Binding result, reload addProduct
		if (result.hasErrors()) {

			return "addProduct";
		}
		// save object
		ps.save(p);

		// return page
		return "redirect:showProducts.html";

	}// addPersonPost

	/*
	 * Programmatically logging out I had to include this method, because when I
	 * downgraded my spring the normal .logout() method wouldn't work in the
	 * security controller
	 * 
	 * Reference https://stackoverflow.com/questions/18957445/how-to-perform-logout-
	 * programmatically-in-spring-3
	 */
	@RequestMapping(value = "/Logout.html", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		for (Cookie cookie : request.getCookies()) {
			cookie.setMaxAge(0);
		}

		return "redirect:login";
	}// logout

}// ProductController
