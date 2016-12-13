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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author David
 */
public class Day4_SecurityThruObscurity {

    private static final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase().toCharArray();
    private static final String input_file = "C:\\Users\\David\\Documents\\NetBeansProjects\\AdventOfCode2016\\src\\adventofcode2016\\Day4_input.txt";
    private static final int DECIMAL = 10;

    /**
     * Runs the Day 4 solutions.
     *
     * @param args
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Read from the input file
        InputStream is = new FileInputStream(new File(input_file));
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        // Sum of valid sector IDs
        int sumOfSectorIDs = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            List<String> input = Arrays.asList(line.split("-|\\["));
            HashMap<Character, Integer> common_letters = new HashMap<>();
            int sectorID = 0;

            // Parse through each part of the name
            for (String crypt : input) {

                // Sector ID
                if (crypt.matches("[0-9]+")) {
                    sectorID = convertStrToInt(crypt, DECIMAL);
                    break;
                }

                // Otherwise, it is an encrypted name, and so we need to count
                // the number of times each letter appears
                char[] letters = crypt.toLowerCase().toCharArray();
                for (char letter : letters) {
                    if (common_letters.containsKey(letter)) {
                        int o = common_letters.get(letter) + 1;
                        common_letters.replace(letter, o);
                    } else {
                        common_letters.put(letter, 1);
                    }
                }
            }

            // Now that we've broken out of the loop, get the checksum sequence
            char[] checksum = input.get(input.size() - 1).toCharArray();

            // Find the top 5 letters of the HashMap
            // If there is a tie, then they must be in alphabetical order; 
            // thankfully, creating a TreeMap sorts the keys for us! How
            // convenient (alphabetical order).
            Map<Character, Integer> alphaSortedLetters = new TreeMap<>(common_letters);
            
            // Sort the values in descending order, so the maximum is first.
            List<Character> keys = new ArrayList<>(alphaSortedLetters.keySet());
            Collections.sort(keys, descendingValueComparator(alphaSortedLetters));
            
            // Now we can compare the key values to the checksum array 1 by 1.
            int counter = 0;
            for (Character key : keys) {
                
                // If we've matched all 5 characters, we're done!
                if (counter > 4) {
                    break;
                }
                if (checksum[counter++] == key) {
                    continue;
                }
                break;
            }

            // Do those 5 letters (in order) match the checksum? If so, let's 
            // add the sector ID to the total.
            if (counter > 4) {
                sumOfSectorIDs += sectorID;
            }
        }
        System.out.println("Sum of valid sector IDs: " + sumOfSectorIDs);
    }

    /**
     * Used to sort in descending order.
     *
     * @param <K>
     * @param <V>
     * @param map
     * @return
     */
    public static <K, V extends Comparable<? super V>>
            Comparator<K> descendingValueComparator(final Map<K, V> map) {
        return new Comparator<K>() {
            public int compare(K key1, K key2) {
                return map.get(key2).compareTo(map.get(key1));
            }
        };
    }

    /**
     * Converts a string number into an integer.
     *
     * @param str string number input
     * @param base what number system we're using
     * @return integer
     */
    public static int convertStrToInt(String str, int base) {
        int number = 0;
        for (int i = 0; i < str.length(); i++) {
            char digit = str.charAt(i);
            number += (int) ((digit - '0')
                    * Math.pow(base, (str.length() - 1 - i)));
        }
        return number;
    }
}
