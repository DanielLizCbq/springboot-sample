/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.services;

import com.example.domain.Category;
import com.example.dto.CategoryDTO;
import com.example.repositories.CategoryRepository;
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
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Category find(Integer id) {
        Optional<Category> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + ", Type: " + Category.class.getName()));
    }
    
    public List<Category> findAll() {
        return repo.findAll();
    }
    
    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }
    
    public Category insert(Category obj) {
        obj.setId(null);
        return repo.save(obj);
    }
    
    public Category update(Category obj) {
        Category newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }
    
    public void delete(Integer id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch(DataIntegrityViolationException e) {
            throw new DataIntegrityException("Cannot delete a category that has products.");
        }
    }
    
    public Category fromDTO(CategoryDTO objDto) {
        return new Category(objDto.getId(), objDto.getName());
    }
    
    private void updateData(Category newObj, Category obj) {
        newObj.setName(obj.getName());
    }

}
