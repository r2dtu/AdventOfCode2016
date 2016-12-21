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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Rank 266 & 201! Best so far xD
 *
 * @author David
 */
public class Day20 {

    private static final String INPUT_FILE = "C:\\Users\\David\\Documents\\NetBeansProjects\\AdventOfCode2016\\src\\adventofcode2016\\Day20_input.txt";

    /**
     * Runs the Day 20 solutions.
     *
     * @param args
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Read the input from the file
        InputStream is1 = new FileInputStream(new File(INPUT_FILE));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is1))) {

            // Read in strings to a list
            String line;
            List<String> input = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                input.add(line);
            }

            // Sort all of the bounds
            Collections.sort(input, compare);

            // Get high bound of one string, add 1, see if it's in next one
            // if it's not, that's the lowest one!
            List<Long> allowedIPs = new ArrayList<>();
            String bound = input.get(0);
            long allowedIP = Long.parseLong(bound.substring(bound.indexOf('-') + 1, bound.length()));
            for (int index = 0; index < input.size(); index++) {
                bound = input.get(index);
                long lowerBound = Long.parseLong(bound.substring(0, bound.indexOf('-')));
                long upperBound = Long.parseLong(bound.substring(bound.indexOf('-') + 1, bound.length()));

                // If in banned range, set allowedIP to upperBound + 1, since
                // all banned ranges are sorted
                if (allowedIP >= lowerBound && allowedIP <= upperBound) {
                    allowedIP = upperBound + 1;
                } 
                
                // For Part 2, find number of allowed IPs (we really only needed
                // to know the size (i.e. an integer value), but it was cool to
                // see all of them)
                else if (allowedIP < lowerBound) {
                    
                    // The logic here is add one to the allowedIP, and see if
                    // the value is still not in range after adding 1 to it - 
                    // if it's banned, we continue through the loop as usual
                    allowedIPs.add(allowedIP);
                    allowedIP += 1;
                    index--;
                }
            }

            // Print out solutions
            System.out.println("Lowest allowed IP: " + allowedIPs.get(0));
            System.out.println("Number of allowed IPs: " + allowedIPs.size());
        }

    }

    /**
     * Compares 2 sets of bounds (used to sort bounds list).
     */
    public static Comparator<String> compare = (String o1, String o2) -> {
        long num1 = Long.parseLong(o1.substring(0, o1.indexOf('-')));
        long num2 = Long.parseLong(o2.substring(0, o2.indexOf('-')));
        return (num1 < num2 ? -1 : (num1 > num2 ? 1 : 0));
    };
}
