/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.services;

import com.example.domain.Category;
import com.example.repositories.CategoryRepository;
import com.example.services.exceptions.DataIntegrityException;
import com.example.services.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 *
 * @author daniel
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Category find(Integer id) {
        Optional<Category> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + ", Type: " + Category.class.getName()));
    }
    
    public Category insert(Category obj) {
        obj.setId(null);
        return repo.save(obj);
    }
    
    public Category update(Category obj) {
        find(obj.getId());
        return repo.save(obj);
    }
    
    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch(DataIntegrityViolationException e) {
            throw new DataIntegrityException("Cannot delete a category that has products.");
        }
    }
    

}
