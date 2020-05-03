package com.sales.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.taglibs.standard.lang.jstl.LessThanOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sales.exceptions.ErrorController;
import com.sales.models.Customer;
import com.sales.models.Order;
import com.sales.models.Product;
import com.sales.services.CustomerService;
import com.sales.services.OrderService;
import com.sales.services.ProductService;

@Controller
@SessionAttributes({ "customers", "products" })
public class OrderController {
	// Auto wiring services for use in controller
	@Autowired
	ProductService ps;
	@Autowired
	CustomerService cs;
	@Autowired
	OrderService os;
	
	// Customer, Product, ErrorController objects
	private Product prod;
	private Customer cust;
	private Customer custs;

	ErrorController ec;

	// Mapping to show orders
	@RequestMapping(value = "/showOrders.html")
	public String listOrders(Model model) {
		// arraylist of orders implemented using findall()
		ArrayList<Order> orders = os.findAll();
		// adding vales to model
		model.addAttribute("allOrders", orders);
		// arraylist of customers implemented using findall()
		ArrayList<Customer> cust = cs.findAll();
		// adding to model
		model.addAttribute("ordrs", cust);
		// return jsp
		return "allOrders";

	}// listorders

	// Get request mapping to newOrder.html
	@RequestMapping(value = "/newOrder.html", method = RequestMethod.GET)
	public String addPerson(Model model) {
		// Arraylist of customers and products
		ArrayList<Customer> customer = cs.findAll();
		ArrayList<Product> product = ps.findAll();
		
		// Mapping customers to a new linkedHashmap
		// Pulling out cId and cName
		Map<Long, String> customers = new LinkedHashMap<Long, String>();
		for (Customer c : customer) {
			customers.put(c.getcId(), c.getcName());
			// adding to model
			model.addAttribute("customers", customers);
			
			// Mapping products to a new linkedHashmap
			// Pulling out and adding pId and pDesc
			Map<Long, String> products = new LinkedHashMap<Long, String>();
			for (Product p : product) {
				products.put(p.getpId(), p.getpDesc());
				// adding to model
				model.addAttribute("products", products);
			}

		}
		// New order object
		Order o = new Order();

		// adding to model
		model.addAttribute("orderList", o);

		// return jsp
		return "addOrder";

	}// addPerson

	// Post request mapping to newOrder.html
	@RequestMapping(value = "/newOrder.html", method = RequestMethod.POST)
	public String addOrderPost(@Valid @ModelAttribute("orderList") Order o, BindingResult result, Model model) {
		
		// if errors are contained in Binding result, reload addOrder
		if (result.hasErrors()) {
			
			return "addOrder";
		}
		
		/*
		 * assigning a a variable to product service.findOne and applying it to
		 * getProd,getpId it will then use this method to find the relevant product and
		 * id same done for customer
		 */

		prod = ps.findOne(o.getProd().getpId());
		cust = cs.findOne(o.getCust().getcId());
		
		// using the order object to set the product
		o.setProd(prod);
		
		/*
		 * Error validation if the order qty is bigger than whats in stock create a new
		 * error controller pass in the error message and header along with the customer
		 * id, product id and qty ordered redirect them to the associated error page
		 */
		
		if ((o.getQty() > prod.getQtyInStock())) {

			ec = new ErrorController();

			ec.setHeader("Error creating the following order");
			ec.setError("Quantity to large: " + "Product stock = " + prod.getQtyInStock());
			ec.setProductId(prod.getpId());
			ec.setCustomerId(cust.getcId());
			ec.setQty(o.getQty());

			return "redirect:errorPage";
		}
		
		/*
		 * Error validation (*****Not working******) if the customer id cannot be found
		 * create a new error controller pass in the error message and header along with
		 * the customer id, product id and qty ordered redirect them to the associated
		 * error page
		 */
		
		if ((o.getCust().getcName() != cust.getcName())) {
			ec = new ErrorController();

			ec.setHeader("Error creating the following order");
			ec.setError("Customer " + cust.getcId() + "and/or Product: " + prod.getpId() + "does not exist");
			ec.setProductId(prod.getpId());
			ec.setCustomerId(cust.getcId());
			ec.setQty(o.getQty());

			return "redirect:errorPage";

		} else if ((prod.getpId() == null)) {
			ec = new ErrorController();
			ec.setHeader("Error creating the following order");
			ec.setError("Customer " + cust.getcId() + "and/or Product: " + prod.getpId() + "does not exist");
			ec.setProductId(prod.getpId());
			ec.setCustomerId(cust.getcId());
			ec.setQty(o.getQty());

			return "redirect:errorPage";
		}
		
		/*
		 * if the if conditions are passed take the order qty from the stock save the
		 * object redirect to the showOrders page
		 */
		prod.setQtyInStock(prod.getQtyInStock() - (o.getQty()));
		os.save(o);

		return "redirect:showOrders.html";

	}//addOrderPost

	/*
	 * Mapping for error page print get the headers that have been set Create the
	 * model to display on the error page return the relevant error page
	 */
	
	@RequestMapping(value = "/errorPage")
	public String errorPage(Model model) {

		System.out.println(ec.getHeader());
		System.out.println(ec.getError());
		System.out.println(ec.getProductId());
		System.out.println(ec.getCustomerId());
		System.out.println(ec.getQty());

		model.addAttribute("exception", ec);

		return "errorPage";
		
	}//errorPage

	/*
	 * Programmatically logging out I had to include this method, because when I
	 * downgraded my spring the normal .logout() method wouldn't work in the
	 * security controller
	 * 
	 * Reference https://stackoverflow.com/questions/18957445/how-to-perform-logout-
	 * programmatically-in-spring-3
	 */
	
	@RequestMapping(value = "/logout1.html", method = RequestMethod.GET)
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

}// OrderController
