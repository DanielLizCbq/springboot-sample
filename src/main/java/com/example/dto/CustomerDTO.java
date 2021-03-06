/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dto;

import com.example.domain.Customer;
import com.example.services.validation.CustomerUpdate;
import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author daniel
 */
@CustomerUpdate
public class CustomerDTO implements Serializable {

    private Integer id;
    
    @NotEmpty(message = "Name can't be empty")
    @Length(min = 5, max = 120, message = "Name must be contain a minimum of {min} and a maximum of {max} characters")
    private String name;
    
    @NotEmpty(message = "Email can't be empty")
    @Email(message = "Invalid email")
    private String email;

    public CustomerDTO() {
    }

    public CustomerDTO(Customer obj) {
        id = obj.getId();
        name = obj.getName();
        email = obj.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
        
}
