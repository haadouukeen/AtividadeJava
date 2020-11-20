/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ativijava.controllers;

import com.ativijava.models.Carro;
import com.ativijava.models.Estacionamento;
import com.ativijava.models.Moto;
import com.ativijava.repository.CarroRepository;
import com.ativijava.repository.EstacionamentoRepository;
import com.ativijava.repository.MotoRepository;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Igor
 */
@Controller
public class IndexController {
    
    @Autowired
    private CarroRepository cr;
    
    @Autowired
    private MotoRepository mr;
    
    @Autowired
    private EstacionamentoRepository er;
    
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
    
    @RequestMapping(value="/endpoint2",method=RequestMethod.GET)
    public ModelAndView endpoint2(){
        ModelAndView mv = new ModelAndView("endpoint2");
        Fatorial f = new Fatorial();
        f.setFatorial("");
        mv.addObject("resultado", f);
        
        return mv;
    }
    
    @RequestMapping(value="/endpoint2",method=RequestMethod.POST)
    public ModelAndView endpoint2Post(Fatorial fato){
        ModelAndView mv = new ModelAndView("endpoint2");
        double resultado = 1;
        
        for(int i = fato.getVal();i > 0;i--){
            resultado = resultado * i;
        }
        
        fato.setFatorial(String.valueOf(resultado));
        mv.addObject("resultado", fato);
        
        return mv;
    }
    
    @RequestMapping(value="/endpoint3",method=RequestMethod.GET)
    public ModelAndView endpoint3(){
        ModelAndView mv = new ModelAndView("endpoint3");
        Vals v  = new Vals();
        v.setSoma("");
        mv.addObject("resultado", v);
        
        return mv;
    }
    
    @RequestMapping(value="/endpoint3",method=RequestMethod.POST)
    public ModelAndView endpoint3Post(Vals vals){
        ModelAndView mv = new ModelAndView("endpoint3");
        Random rn = new Random();
        
        int m[][] = new int[vals.getVal1()][vals.getVal2()];
        
        for(int i1=0;i1<vals.getVal1();i1++){
            for(int i2=0;i2<vals.getVal2();i2++){
                m[i1][i2] = rn.nextInt();
            }
        }
        
        int resultado = 0;
        
        for(int i1=0;i1<vals.getVal1();i1++){
            for(int i2=0;i2<vals.getVal2();i2++){
                resultado = resultado + m[i1][i2];
            }
        }
        
        vals.setSoma(String.valueOf(resultado));
        mv.addObject("resultado", vals);
        
        return mv;
    }
    
    @RequestMapping(value="/endpoint4_1",method=RequestMethod.GET)
    public ModelAndView endpoint4_1(){
        ModelAndView mv = new ModelAndView("endpoint4_1");
        
        return mv;
    }
    
    @RequestMapping(value="/endpoint4_1",method=RequestMethod.POST)
    public String endpoint4_1Post(@RequestParam("tipo") String tipo,@RequestParam("nome") String nome,
            @RequestParam("placa") String placa,RedirectAttributes attributes){
        
        Estacionamento e = er.findById(Long.valueOf(1)).get();
        int tamanho = 0;
        
        if(e.getLivres() <= 0){
            attributes.addFlashAttribute("mensagem", "Não temos vagas disponiveis.");
            return "redirect:/endpoint4_1";
        }
        
        if(tipo.equals("C")){
            Carro c = new Carro();
            c.setNome(nome);
            c.setPlaca(placa);
            
            cr.save(c);
            tamanho = 4;
        }else{
            Moto m = new Moto();
            m.setNome(nome);
            m.setPlaca(placa);
            
            mr.save(m);
            tamanho = 2;
        }
        
        e.setLivres(e.getLivres()-tamanho);
        e.setOcupados(e.getOcupados()+tamanho);
        er.save(e);
        
        attributes.addFlashAttribute("mensagem", "Cadastrado com sucesso.");
        return "redirect:/endpoint4_1";
    }
    
    @RequestMapping(value="/endpoint4_2",method=RequestMethod.GET)
    public ModelAndView endpoint4_2(){
        ModelAndView mv = new ModelAndView("endpoint4_2");
        
        return mv;
    }
    
    @RequestMapping(value="/endpoint4_2",method=RequestMethod.POST)
    public ModelAndView endpoint4_2Post(Vals vals){
        ModelAndView mv = new ModelAndView("endpoint4_2");
      
        return mv;
    }
    
    @RequestMapping(value="/endpoint4_3",method=RequestMethod.GET)
    public ModelAndView endpoint4_3(){
        ModelAndView mv = new ModelAndView("endpoint4_3");
        
        return mv;
    }
    
    @RequestMapping(value="/endpoint4_3",method=RequestMethod.POST)
    public ModelAndView endpoint4_3Post(Vals vals){
        ModelAndView mv = new ModelAndView("endpoint4_3");
      
        return mv;
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

class Fatorial{
    private int val;
    private String fatorial;

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public String getFatorial() {
        return fatorial;
    }

    public void setFatorial(String fatorial) {
        this.fatorial = fatorial;
    }
    
    
}
