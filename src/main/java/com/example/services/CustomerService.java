/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.services;

import com.example.domain.Customer;
import com.example.dto.CustomerDTO;
import com.example.repositories.CustomerRepository;
import com.example.services.exceptions.DataIntegrityException;
import com.example.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author daniel
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;

    public Customer find(Integer id) {
        Optional<Customer> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + ", Type: " + Customer.class.getName()));
    }
    
    public List<Customer> findAll() {
        return repo.findAll();
    }
    
    public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }
    
    public Customer update(Customer obj) {
        Customer newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }
    
    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch(DataIntegrityViolationException e) {
            throw new DataIntegrityException("Unable to delete because there are related entities");
        }
    }
    
    public Customer fromDTO(CustomerDTO objDto) {
        return new Customer(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
    }

    private void updateData(Customer newObj, Customer obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }
    
}
