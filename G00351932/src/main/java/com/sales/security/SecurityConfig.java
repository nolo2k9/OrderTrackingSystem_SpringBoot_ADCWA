package com.sales.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
 * When i changed my version of spring to the current version,
 * The login and logout werent working as shown in the labs.
 * 
 * This is a reference to the question I asked on stack overflow which gave me the answer
 * https://stackoverflow.com/questions/61363348/error-the-method-withdefaultpasswordencoder-is-undefined-for-the-type-user-s/61363621#61363621
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{ 
  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception{
    httpSecurity.authorizeRequests()
     .antMatchers("/showProducts.html", "/addProduct.html", "/showCustomers.html", 
    		 "/addCustomer.html", "/showOrders.html", "/newOrder.html")
     .hasRole("USER")
     .and()
     .formLogin();
    
   
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) 
						throws Exception {
    auth.inMemoryAuthentication()
      .withUser("user").password("user").roles("USER");
  }
  
}//SecurityConfig