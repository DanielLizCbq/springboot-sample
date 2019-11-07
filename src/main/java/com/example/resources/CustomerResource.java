/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.resources;

import com.example.domain.Customer;
import com.example.dto.CustomerDTO;
import com.example.dto.CustomerNewDTO;
import com.example.services.CustomerService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author daniel
 */
@RestController
@RequestMapping(value = "/customers")
public class CustomerResource {

    @Autowired
    private CustomerService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> find(@PathVariable Integer id) {
        Customer obj = service.find(id);
        return ResponseEntity.ok(obj);
    }
    
    @GetMapping()
    public ResponseEntity<List<CustomerDTO>> findAll() {
        List<Customer> list = service.findAll();
        List<CustomerDTO> listDTO = list.stream().map(obj -> new CustomerDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok(listDTO);
    }
    
    @GetMapping(value = "/page")
    public ResponseEntity<Page<CustomerDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Customer> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<CustomerDTO> listDTO = list.map(obj -> new CustomerDTO(obj));
        return ResponseEntity.ok(listDTO);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CustomerNewDTO objDto) {
        Customer obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody CustomerDTO objDto) {
        Customer obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }
    

}
