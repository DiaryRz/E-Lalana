/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import elements.*;
import java.util.List;

/**
 *
 * @author Diary
 */
public class Main {
    public static void main(String[] args) throws Exception{
        try{
         Pourcentage appel = new Pourcentage();
                Pourcentage prt = appel.AvoirPourcentage("1","1");
                System.out.println(prt.getNombrePrive());
         
            
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    
}
