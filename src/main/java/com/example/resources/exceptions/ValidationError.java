/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daniel
 */
public class ValidationError extends StandardError {

    private List<FieldMessage> list = new ArrayList<>();
    
    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return list;
    }

    public void addError(String fieldName, String message) {
        list.add(new FieldMessage(fieldName, message));
    }
    
    
      
}
