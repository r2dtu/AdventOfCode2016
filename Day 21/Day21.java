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
import java.util.List;
import java.util.Map;

/**
 *
 * @author David
 */
public class Day21 {
    
    private String start = "abcdefgh";
    private static final String INPUT_FILE = "C:\\Users\\David\\Documents\\NetBeansProjects\\AdventOfCode2016\\src\\adventofcode2016\\Day21_input.txt";

    /**
     * Runs the Day 6 Solutions.
     * @param args
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Read the input from the file
        InputStream is1 = new FileInputStream(new File(INPUT_FILE));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is1))) {
            String line;
            
            // We'll need a list of key/value pairs - for the character and the
            // number of times it comes up. The number of maps we need is equal
            // to just the line length, but ... oh well
            List<Map<Character, Integer>> input = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                int index;
            }
        }
    }
}
