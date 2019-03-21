package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Customer> findAll() {
        return jdbcTemplate.query("SELECT id, first_name, last_name FROM customers1",
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name")));
    }

    public void update(Customer customer) {
        jdbcTemplate.update("UPDATE customers1 SET first_name=?, last_name=? WHERE id=?",
                customer.getFirstName(), customer.getLastName(), customer.getId());
    }
    
  /*  public void insert(Customer customer) {
    	jdbcTemplate.insert("INSERT INTO customers (first_name, last_name) VALUES ('Cardinal', 'Stavanger')")
    }*/
}
