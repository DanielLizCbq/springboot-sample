/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.repositories;

import com.example.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author daniel
 */
@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    
}
