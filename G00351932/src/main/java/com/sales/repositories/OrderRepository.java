package com.sales.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sales.models.Customer;
import com.sales.models.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

List<Order> findByCust(Customer cust);

	
}
