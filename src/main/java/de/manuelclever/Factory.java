package de.manuelclever;

import de.manuelclever.dec01.Calculate_01;
import de.manuelclever.dec02.Calculate_02;
import de.manuelclever.dec03.Calculate_03;
import de.manuelclever.dec04.Calculate_04;
import de.manuelclever.dec05.Calculate_05;
import de.manuelclever.dec06.Calculate_06;
import de.manuelclever.dec07.Calculate_07;
import de.manuelclever.dec08.Calculate_08;
import de.manuelclever.dec09.Calculate_09;
import de.manuelclever.dec10.Calculate_10;
import de.manuelclever.dec11.Calculate_11;

public class Factory {
    Calculator calculatePart;

    public static Calculator createCalculator(int day) throws Exception {

        switch (day) {
            case 1 -> {return new Calculate_01();}
            case 2 -> {return new Calculate_02();}
            case 3 -> {return new Calculate_03();}
            case 4 -> {return new Calculate_04();}
            case 5 -> {return new Calculate_05();}
            case 6 -> {return new Calculate_06();}
            case 7 -> {return new Calculate_07();}
            case 8 -> {return new Calculate_08();}
            case 9 -> {return new Calculate_09();}
            case 10 -> {return new Calculate_10();}
            case 11 -> {return new Calculate_11();}
            default -> throw new Exception("No such class");
        }

    }
}
