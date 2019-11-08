/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.services.validation;

import com.example.domain.Customer;
import com.example.dto.CustomerDTO;
import com.example.repositories.CustomerRepository;
import com.example.resources.exceptions.FieldMessage;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
/**
 *
 * @author daniel
 */

public class CustomerUpdateValidator implements ConstraintValidator<CustomerUpdate, CustomerDTO> {

    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public void initialize(CustomerUpdate ann) {
    }

    @Override
    public boolean isValid(CustomerDTO objDto, ConstraintValidatorContext context) {
        
        Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        
        Integer uriId = Integer.parseInt(map.get("id"));
        
        List<FieldMessage> list = new ArrayList<>();

        Customer aux = customerRepository.findByEmail(objDto.getEmail());
        if (aux != null && !aux.getId().equals(uriId)) {
            list.add(new FieldMessage("email", "Email already registered"));
        }
        
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
