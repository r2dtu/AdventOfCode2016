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

/**
 *
 * @author David
 */
public class Day9_ExplosivesInCyberspace {

    private static final String INPUT_FILE = "C:\\Users\\David\\Documents\\NetBeansProjects\\AdventOfCode2016\\src\\adventofcode2016\\Day9_input.txt";
    private static final int DECIMAL = 10;

    /**
     * Runs the Day 9, Part 1 solutions.
     * @param args
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Read in from file
        InputStream is = new FileInputStream(new File(INPUT_FILE));
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        // Decompressed length
        long length = 0;
        
        // Read in byte by byte
        int ch;
        while ((ch = reader.read()) != -1) {
            
            // Regular characters
            if (ch != '(') {
                length += 1;
                continue;
            }
            
            // We've found a marker!
            List<Character> marker = new ArrayList<>();
            marker.add((char) ch);
            while ((ch = reader.read()) != -1) {
                marker.add((char) ch);
                if (ch == ')') {
                    
                    // Get the index of the 'x' character
                    int x = marker.indexOf('x');
                    
                    // Convert the first number, which is number of subsequent
                    // characters to repeat
                    int numCharRepeat = 0;
                    for (int i = 1; i < x; i++) {
                        numCharRepeat += (marker.get(i) - '0') * Math.pow(DECIMAL, (x - (i + 1)));
                    }

                    // Convert the second number, which is how many times to
                    // repeat the above sequence
                    int repeatTimes = 0;
                    for (int i = x + 1; i < marker.indexOf(')'); i++) {
                        repeatTimes += (marker.get(i) - '0') * Math.pow(DECIMAL, (marker.indexOf(')') - (i + 1)));
                    }

                    // So now we need to read in the number of characters to
                    // repeat, so we can continue reading afterwards
                    for (int i = 0; i < numCharRepeat; i++) {
                        reader.read();
                    }
                    
                    // Then we just multiply # char repeated with the number of
                    // times to repeat
                    length += numCharRepeat * repeatTimes;
                    
                    // Break out of this current loop, b/c it's for markers only
                    break;
                }
            }
        }

        System.out.println("Decompressed length: " + length);
    }
}
