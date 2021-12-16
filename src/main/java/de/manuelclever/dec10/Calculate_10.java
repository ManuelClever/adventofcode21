package de.manuelclever.dec10;

import de.manuelclever.Calculator;
import de.manuelclever.MyFileReader;

import java.util.ArrayList;
import java.util.List;

public class Calculate_10 implements Calculator {
    List<String> rawInput;

    @Override
    public long calculatePart1() {
        setInput();

        List<Character> corruptBrackets = new ArrayList<>();
        for(String line : rawInput) {
            List<Character> brackets = new ArrayList<>();

            for (int i = 0; i < line.length(); i++) {
                char bracket = line.charAt(i);

                if(bracket == '(' || bracket == '[' || bracket == '{' || bracket == '<') {
                    brackets.add(bracket);
                } else if(isClosingBracket(bracket, brackets)) {
                    brackets.remove(brackets.size() - 1);
                } else { //corrupt
                    corruptBrackets.add(bracket);
                    break;
                }
            }
        }

        return calculateIllegal(corruptBrackets);
    }

    private void setInput() {
        MyFileReader fr = new MyFileReader(10,1);
        rawInput = fr.getStringList();
    }

    private boolean isClosingBracket(char c, List<Character> list) {
        char currBracket = list.get(list.size() - 1);

        if(currBracket == '(') {
            return c == ')';
        } else if(currBracket == '[') {
            return c == ']';
        } else if(currBracket == '{') {
            return c == '}';
        } else if(currBracket == '<') {
            return c == '>';
        }
        return false;
    }

    private long calculateIllegal(List<Character> corruptBrackets) {
        int c = 0;

        for(char bracket : corruptBrackets) {
            if(bracket == ')') {
                c += 3;
            } else if(bracket == ']') {
                c += 57;
            } else if(bracket == '}') {
                c += 1197;
            } else if(bracket == '>') {
                c += 25137;
            }
        }
        return c;
    }

    @Override
    public long calculatePart2() {
        return 0;
    }
}
