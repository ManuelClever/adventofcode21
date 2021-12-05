package de.manuelclever;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyFileReader {
    private Path path = null;

    public MyFileReader(int day, int part) {
        String dayWithZero = day < 10 ? "0" + day : String.valueOf(day);
        path = FileSystems.getDefault().getPath(
                "src", "main", "java", "de", "manuelclever", "dec" + dayWithZero, "input_" + part + ".txt")
                .toAbsolutePath();
    }

    public List<String> getStringList() {
        List<String> list = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path.toString()));
            reader.lines().forEach(list::add);
        } catch (IOException e) {
            return null;
        }
        return list;
    }

    public Scanner createScanner() {
        try {
            return new Scanner(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public BufferedReader createBufferedReader() {
        try {
            return new BufferedReader(new FileReader(path.toString()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
