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
    private static final int NUM_SIDES_TRIANGLE = 3;
    private static final String INPUT_FILE = "C:\\Users\\David\\Documents\\NetBeansProjects\\AdventOfCode2016\\src\\adventofcode2016\\Day3_input.txt";

    /**
     * Runs the solution for Part 1 and 2 for Day 3.
     * @param args - command line arguments (none)
     * @throws FileNotFoundException - for input file
     * @throws IOException - for reading the lines of the file
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        InputStream is = new FileInputStream(new File(INPUT_FILE));

        /**
         * Part 1.
         */
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            int numTriangles = 0;
            while ((line = reader.readLine()) != null) {
                List<String> input = Arrays.asList(line.split("\\s* \\s*"));
                int[] sides = getSides(input);

                if (isTriangle(sides)) {
                    numTriangles += 1;
                }
            }

            System.out.println("Number of real triangles (Part 1): " + numTriangles);
        }

        InputStream is1 = new FileInputStream(new File(INPUT_FILE));

        /**
         * Part 2.
         */
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is1))) {
            String line1, line2, line3;
            int numTriangles = 0;
            while ((line1 = reader.readLine()) != null
                    && (line2 = reader.readLine()) != null
                    && (line3 = reader.readLine()) != null) {

                List<String> input1 = Arrays.asList(line1.split("\\s* \\s*"));
                List<String> input2 = Arrays.asList(line2.split("\\s* \\s*"));
                List<String> input3 = Arrays.asList(line3.split("\\s* \\s*"));
                int[] sides1 = getSides(input1);
                int[] sides2 = getSides(input2);
                int[] sides3 = getSides(input3);

                numTriangles += isTriangle(sides1, sides2, sides3);
            }

            System.out.println("Number of real triangles (Part 2): " + numTriangles);

        }
    }

    /**
     * Reads in the side lengths from 1 line of the input file. See input_file
     * for an example of the layout.
     * @param input - line read from file as a list of strings (separated by 
     *                spaces)
     * @return integer array of the sides of the triangle
     */
    private static int[] getSides(List<String> input) {
        int[] sides = new int[NUM_SIDES_TRIANGLE];
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
        return sides;
    }

    /**
     * Determines whether the triangle is real or not given the side lengths. 
     * The sum of any two sides must be larger than the last side.
     * @param sides - 3 side lengths of the "triangle"
     * @return true if real triangle, false if not
     */
    private static boolean isTriangle(int[] sides) {
        return (sides[0] + sides[1] > sides[2]
                && sides[0] + sides[2] > sides[1]
                && sides[1] + sides[0] > sides[2]
                && sides[1] + sides[2] > sides[0]);
    }

    /**
     * For Part 2. Columns of 3 are now the triangle side lengths instead of 
     * rows. We need 3 rows at a time, and we use the same index for every
     * array and use those as the triangle's sides.
     * @param sides1
     * @param sides2
     * @param sides3
     * @return 
     */
    private static int isTriangle(int[] sides1, int[] sides2, int[] sides3) {
        int numTriangles = 0;
        for (int i = 0; i < 3; i++) {
            int[] sides = {sides1[i], sides2[i], sides3[i]};
            if (isTriangle(sides)) {
                numTriangles += 1;
            }
        }
        return numTriangles;
    }
}
