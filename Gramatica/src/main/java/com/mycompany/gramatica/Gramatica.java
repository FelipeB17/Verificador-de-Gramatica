/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gramatica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Administrador
 */
public class Gramatica {

    String[] productions;
    String grammar;
    ArrayList<String> terminales;
    ArrayList<String> noTerminales;

    String inicial;
    Map<String, List<String>> mapaProductions = new HashMap<>();

    public Gramatica(String[] productions, String grammar) throws Exception {
        grammar = grammar.replaceAll(" ", "");
        String[] cuadrupla = grammar.split(",(?![^()]*\\))");
        //System.out.println(Arrays.toString(cuadrupla));
        //System.out.println(cuadrupla.length);

        if (cuadrupla.length != 3) {
            throw new RuntimeException("Error en gramatica");
        }

        terminales = new ArrayList(Arrays.asList(cuadrupla[0].replaceAll("[()]", "").split(",")));
        noTerminales = new ArrayList(Arrays.asList(cuadrupla[1].replaceAll("[()]", "").split(",")));
        inicial = cuadrupla[2].replaceAll("[()]", "");

        for (String prod : productions) {
            prod = prod.trim();
            //System.out.println("prod:"+prod);
            String[] parts = prod.split("=");
            //System.out.println("parts:"+Arrays.toString(parts));
            if (parts.length != 2) {
                throw new RuntimeException("Error en producciones");
            }
            ArrayList formaciones = new ArrayList<String>(Arrays.asList(parts[1].trim().split(",")));

            if (mapaProductions.containsKey(parts[0])) {
                formaciones.addAll(mapaProductions.get(parts[0]));
            }

            mapaProductions.put(parts[0], formaciones);

        }
        depurar();
        for (Entry<String, List<String>> entry : mapaProductions.entrySet()) {
            System.out.println("Mapa: key " + entry.getKey() + " value:" + entry.getValue().toString());
        }

    }

    private void depurar() {
        if (!mapaProductions.containsKey(inicial)) {
            throw new RuntimeException("Simbolo inicial no genera produccion");
        }
        
        //eliminar simbolos no definidos
        ArrayList<String> simbolos = new ArrayList();
        simbolos.addAll(terminales);
        simbolos.addAll(noTerminales);
        /*for (Entry<String, List<String>> entry : mapaProductions.entrySet()) {
            List<String> formaciones = entry.getValue();
            System.out.println("Entra a primer depurar");
            for (int i=0;i<formaciones.size();i++){
                String formacion = formaciones.get(i);
                List<String> noPertenecen = Stream.of(formacion.split(""))
                .map(String::trim)
                .collect(Collectors.toList());
                noPertenecen.removeAll(simbolos);
                for (String string : noPertenecen) {
                    formaciones.set(i, formacion.replaceAll(Pattern.quote(string), ""));
                }
            }
        }*/

        //eliminar simbolos inalcanzables
        for (int i = 0; i < noTerminales.size(); i++) {
            String noTerminal = noTerminales.get(i);
            boolean inalcanzable = mapaProductions.get(noTerminal) == null;
            if (inalcanzable) {
                System.out.println("inalcanzable:" + noTerminal);
                for (Entry<String, List<String>> entry : mapaProductions.entrySet()) {
                    List<String> formaciones = entry.getValue();
                    System.out.println("Lista antes:" + formaciones.toString());
                    formaciones.replaceAll(formacion
                            -> formacion.contains(noTerminal) ? formacion.replaceAll(Pattern.quote(noTerminal), "")
                            : formacion);
                    System.out.println("Lista ahora:" + formaciones.toString());

                }
            }
        }

    }
}
