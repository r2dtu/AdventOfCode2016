/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2016;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author David
 */
public class Day3_SquaresWithThreeSides {

    private static final int DECIMAL = 10;

    /**
     *
     * @param args
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        InputStream is = new FileInputStream(new File("C:\\Users\\David\\Documents\\NetBeansProjects\\AdventOfCode2016\\src\\adventofcode2016\\Day3_input.txt"));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            StringBuilder out = new StringBuilder();
            String line;
            
            int numTriangles = 0;
            
            while ((line = reader.readLine()) != null) {
                out.append(line);
                out.append('\n');

                List<String> input = Arrays.asList(line.split("\\s* \\s*"));
                int[] sides = new int[3];
                int index = 0;
                for (String number : input) {
                    if (!number.isEmpty()) {
                        int sideLength = 0;
                        for (int i = 0; i < number.length(); i++) {
                            char digit = number.charAt(i);
                            sideLength += (int) ((digit - '0')
                                    * Math.pow(DECIMAL, (number.length() - 1 - i)));
                        }
                        sides[index++] = sideLength;
                    }
                }
                
                if (sides[0] + sides[1] > sides[2] &&
                        sides[0] + sides[2] > sides[1] &&
                        sides[1] + sides[0] > sides[2] &&
                        sides[1] + sides[2] > sides[0]) {
                    numTriangles += 1;
                }
                
            }
            
            System.out.println(numTriangles);
        }

    }
}
