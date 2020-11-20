/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ativijava.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Igor
 */
@Entity
public class Estacionamento implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @NotNull
    private int totalLugares;
    @NotNull
    private int ocupados;
    @NotNull
    private int livres;

    public int getTotalLugares() {
        return totalLugares;
    }

    public void setTotalLugares(int totalLugares) {
        this.totalLugares = totalLugares;
    }

    public int getOcupados() {
        return ocupados;
    }

    public void setOcupados(int ocupados) {
        this.ocupados = ocupados;
    }

    public int getLivres() {
        return livres;
    }

    public void setLivres(int livres) {
        this.livres = livres;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
    
}
