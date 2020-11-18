/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ativijava.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Igor
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    
    @RequestMapping(value="/endpoint1",method=RequestMethod.GET)
    public ModelAndView endpoint1(){
        ModelAndView mv = new ModelAndView("endpoint1");
        Vals v  = new Vals();
        v.setSoma("");
        mv.addObject("resultado", v);
        
        return mv;
    }
    
    @RequestMapping(value="/endpoint1",method=RequestMethod.POST)
    public ModelAndView endpoint1Post(Vals vals){
        ModelAndView mv = new ModelAndView("endpoint1");
        vals.setSoma(String.valueOf(vals.getVal1() + vals.getVal2()));
        mv.addObject("resultado", vals);
        
        return mv;
    }
    
    @RequestMapping("/endpoint2")
    public String endpoint2(){
        return "endpoint2";
    }
    
    @RequestMapping("/endpoint3")
    public String endpoint3(){
        return "endpoint3";
    }
}

class Vals{
    private int val1;
    private int val2;
    private String soma;

    public int getVal1() {
        return val1;
    }

    public void setVal1(int val1) {
        this.val1 = val1;
    }

    public int getVal2() {
        return val2;
    }

    public void setVal2(int val2) {
        this.val2 = val2;
    }

    public String getSoma() {
        return soma;
    }

    public void setSoma(String soma) {
        this.soma = soma;
    }
    
    
}
