/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ativijava.repository;

import com.ativijava.models.Carro;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Igor
 */
public interface CarroRepository extends CrudRepository<Carro, Long>{
    
}
