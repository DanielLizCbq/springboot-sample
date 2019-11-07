/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dto;

import com.example.domain.Category;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author daniel
 */
public class CategoryDTO implements Serializable {
    
    private Integer id;
    
    @NotEmpty(message = "Name can't be empty")
    @Length(min = 5, max = 80, message = "Name must be contain a minimum of {min} and a maximum of {max} characters")
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(Category obj) {
        id = obj.getId();
        name = obj.getName();
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
