/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.services;

import com.example.domain.Category;
import com.example.repositories.CategoryRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author daniel
 */
@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository repo;
    
    public Category search(Integer id) {
        Optional<Category> obj = repo.findById(id);
        return obj.orElse(null);
    }
    
}