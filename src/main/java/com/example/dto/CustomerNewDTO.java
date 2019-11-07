/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dto;

import com.example.services.validation.CustomerInsert;
import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author daniel
 */
@CustomerInsert
public class CustomerNewDTO implements Serializable {
    
    /* Customer data */
    @NotEmpty(message = "Name can't be empty")
    @Length(min = 5, max = 120, message = "Name must be contain a minimum of {min} and a maximum of {max} characters")
    private String name;
    
    @NotEmpty(message = "Email can't be empty")
    @Email(message = "Invalid email")
    private String email;
    
    @NotEmpty(message = "CPF or CNPJ can't be empty")
    private String cpfOrCnpj;
    private Integer type;
    
    /* Address data */
    @NotEmpty(message = "Street can't be empty")
    private String street;
    
    @NotEmpty(message = "Number can't be empty")
    private String number;
    private String complement;
    private String neighborhood;
    
    @NotEmpty(message = "Zip code can't be empty")
    private String zipCode;
    
    /* Phone data */
    @NotEmpty(message = "Phone can't be empty")
    private String phone; 
    private String phone2; 
    private String phone3; 
    
    /* City reference */
    private Integer cityId;

    public CustomerNewDTO() {
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

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone1(String phone) {
        this.phone = phone;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
            
}
