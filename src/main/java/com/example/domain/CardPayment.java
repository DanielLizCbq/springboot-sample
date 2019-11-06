/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.domain;

import com.example.domain.enums.PaymentStatus;
import javax.persistence.Entity;

/**
 *
 * @author daniel
 */
@Entity
public class CardPayment extends Payment {

    private Integer numberInstallments;

    public CardPayment() {
    }

    public CardPayment(Integer id, PaymentStatus status, Order order, Integer numberInstallments) {
        super(id, status, order);
        this.numberInstallments = numberInstallments;
    }

    public Integer getNumberInstallments() {
        return numberInstallments;
    }

    public void setNumberInstallments(Integer numberInstallments) {
        this.numberInstallments = numberInstallments;
    }
    
    
    
}
