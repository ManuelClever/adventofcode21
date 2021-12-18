package de.manuelclever.dec14;

import de.manuelclever.Calculator;
import de.manuelclever.MyFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Calculate_14 implements Calculator {
    StringBuilder polymer;
    Map<String, String> insertionRules;

    @Override
    public long calculatePart1() {
        generatePolymerAndRules();
        step(10);

        return calculateSolution();
    }

    private void generatePolymerAndRules() {
        MyFileReader fr = new MyFileReader(14, 1);
        BufferedReader br = fr.createBufferedReader();

        try {
            polymer = new StringBuilder(br.readLine());
            br.readLine();

            insertionRules = new HashMap<>();
            while (true) {
                String[] rule = br.readLine().split(" -> ");
                insertionRules.put(rule[0], rule[1]);
            }

        } catch (IOException | NullPointerException ignore) {}
    }

    private void step(int steps) {

        for(int i = 0; i < steps; i++) {
            Map<Integer, String> toInsert = new HashMap<>();

            for(Map.Entry<String,String> entry : insertionRules.entrySet()) {
                String reg = entry.getKey();
                Pattern p = Pattern.compile(reg);
                Matcher m = p.matcher(polymer);

                int index = 0;
                while (m.find(index)) {
                    index = polymer.indexOf(reg, index) + 1; //+1 to move past the found reg
                    toInsert.put(index, entry.getValue());
                }
            }

            List<Map.Entry<Integer,String>> sortedToInsert = toInsert.entrySet().stream()
                    .sorted((o1,o2) -> {
                        if(o1.getKey() < o2.getKey()) {
                            return -1;
                        } else if(o1.getKey() > o2.getKey()) {
                            return 1;
                        } return 0;})
                    .collect(Collectors.toList());

            int offset = 0;
            for(Map.Entry<Integer,String> entry : sortedToInsert) {
                polymer.insert(entry.getKey() + offset, entry.getValue());
                offset++;
            }
        }
    }

    private long calculateSolution() {
        Map<Character, Integer> occurences = new HashMap<>();

        for(int i = 0; i < polymer.length(); i++) {
            char element = polymer.charAt(i);
            occurences.merge(element, 1, Integer::sum);
        }

        List<Map.Entry<Character,Integer>> sorted = occurences.entrySet().stream()
                .sorted((o1, o2) -> {
                    if (o1.getValue() > o2.getValue()) {
                        return -1;
                    } else if (o1.getValue() < o2.getValue()) {
                        return 1;
                    } return 0;})
                .collect(Collectors.toList());

        return sorted.get(0).getValue() - sorted.get(sorted.size()-1).getValue();

    }

        @Override
    public long calculatePart2() {
        return 0;
    }
}
