package de.manuelclever;

import de.manuelclever.dec01.Calculate_01;
import de.manuelclever.dec02.Calculate_02;
import de.manuelclever.dec03.Calculate_03;
import de.manuelclever.dec04.Calculate_04;
import de.manuelclever.dec05.Calculate_05;

public class Factory {
    Calculator calculatePart;

    public static Calculator createCalculator(int day) throws Exception {

        switch (day) {
            case 1 -> {return new Calculate_01();}
            case 2 -> {return new Calculate_02();}
            case 3 -> {return new Calculate_03();}
            case 4 -> {return new Calculate_04();}
            case 5 -> {return new Calculate_05();}
            default -> {throw new Exception("No such class");}
        }

    }
}
