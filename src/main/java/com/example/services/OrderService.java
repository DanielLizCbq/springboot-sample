/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.services;

import com.example.domain.Order;
import com.example.repositories.OrderRepository;
import com.example.services.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author daniel
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    public Order search(Integer id) {
        Optional<Order> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + ", Type: " + Order.class.getName()));
    }

}
