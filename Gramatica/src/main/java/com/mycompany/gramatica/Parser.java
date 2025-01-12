/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gramatica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Administrador
 */
public class Parser {

    Gramatica gramatica;
    String inicial;
    Map<String, List<String>> mapaProductions = new HashMap<>();
    Object[] reglasdeProduccion;

    public Parser(String[] productions, String grammar) throws Exception {
        /*this.productions = productions;
        this.grammar = grammar;

        grammar = grammar.replaceAll(" ", "");
        String[] cuadrupla = grammar.split(",(?![^()]*\\))");
        //System.out.println(Arrays.toString(cuadrupla));
        //System.out.println(cuadrupla.length);

        if (cuadrupla.length != 3) {
            throw new RuntimeException("Error en gramatica");
        }

        terminales = cuadrupla[0].replaceAll("[()]", "").split(",");
        noTerminales = cuadrupla[1].replaceAll("[()]", "").split(",");
        inicial = cuadrupla[2].replaceAll("[()]", "");

        //System.out.println(Arrays.toString(terminales));
        //System.out.println(Arrays.toString(noTerminales));
        //System.out.println(inicial);
        for (String prod : productions) {
            prod = prod.trim();
            //System.out.println("prod:"+prod);
            String[] parts = prod.split("=");
            //System.out.println("parts:"+Arrays.toString(parts));
            if (parts.length != 2) {
                throw new RuntimeException("Error en gramatica");
            }
            ArrayList formaciones = new ArrayList<String>(Arrays.asList(parts[1].trim().split(",")));
            mapaProductions.put(parts[0], formaciones);
        }
        for (Map.Entry<String, List<String>> entry : mapaProductions.entrySet()) {
            //System.out.println(entry.toString());
        }*/
        gramatica = new Gramatica(productions, grammar);
        this.mapaProductions=gramatica.mapaProductions;
        this.inicial = gramatica.inicial;
    }

    public boolean shiftReduce(String palabra) {
        boolean belongs = false;
        String stack = "";
        palabra = palabra.replaceAll(" ", "");
        String[] rule = reduceFinal(stack);
        
        while (!palabra.isBlank() ) {
            System.out.println("test1");
            System.out.println("palabra:" + palabra);
            stack += palabra.charAt(0);
            System.out.println("stack:" + stack);

            palabra = palabra.substring(1);
            System.out.println("palabra ahora:" + palabra);

            rule = reduceFinal(stack);
            
                while (!stack.equals(inicial)&&rule!=null){
                    stack = stack.replaceAll(Pattern.quote(rule[1]), rule[0]);
                    System.out.println("stack ahora:" + stack);
                    rule = reduceFinal(stack);
                }
                
            

        }
        
        String[] finalRule = reduceFinal(stack);
        
        while (!stack.equals(inicial)&&finalRule!=null) {
            System.out.println("string rule"+Arrays.toString(finalRule));
            stack = stack.replaceAll(Pattern.quote(finalRule[1]), finalRule[0]);
            finalRule = reduceFinal(stack);
        }
        System.out.println("stack final:" + stack);

        belongs = stack.equals(inicial);
        return belongs;
    }

    public String[] reduceFinal(String palabra) {
        String[] rule = null;
        boolean hayCoincidencia = true;

        for (Map.Entry<String, List<String>> regla : mapaProductions.entrySet()) {
            System.out.println("test reduce");
            System.out.println("key:" + regla.getKey() + " y value:" + regla.getValue().toString());
            for (String subString : regla.getValue()) {
                System.out.println("test3");
                System.out.println("substring:" + subString);
                hayCoincidencia = palabra.contains(subString);
                if (hayCoincidencia) {
                    System.out.println("contains funciona");
                    rule = new String[2];
                    rule[0] = regla.getKey();
                    rule[1] = subString;
                    return rule;
                    //palabra=palabra.replaceAll(Pattern.quote(subString), regla.getKey());

                }
            }
        }

        return rule;
    }

}
