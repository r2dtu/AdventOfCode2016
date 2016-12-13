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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David
 */
public class Day6_SignalsAndNoise {

    private static final String INPUT_FILE = "C:\\Users\\David\\Documents\\NetBeansProjects\\AdventOfCode2016\\src\\adventofcode2016\\Day6_input.txt";

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
                
                // Initialize the list with empty hash maps
                if (input.isEmpty()) {
                    for (index = 0; index < line.length(); index++) {
                        input.add(new HashMap<>());
                    }
                }
                
                // Go through each character in the line, adding 1 to the hash
                // map value that corresponds to the character key
                index = 0;
                for (Map<Character, Integer> column : input) {
                    if (index < line.length()) {
                        char letter = line.charAt(index);
                        if (column.containsKey(letter)) {
                            column.replace(letter, column.get(letter) + 1);
                        } else {
                            column.put(line.charAt(index), 1);
                        }
                    }
                    index++;
                }
            }

            System.out.print("Part 1 Message: ");
            for (Map<Character, Integer> column : input) {
                // Sort the values in descending order, so the maximum is first.
                List<Character> keys = new ArrayList<>(column.keySet());
                Collections.sort(keys, descendingValueComparator(column));
                System.out.print(keys.get(0));
            }
            System.out.println();

            System.out.print("Part 2 Message: ");
            for (Map<Character, Integer> column : input) {
                // Sort the values in ascending order, so the minimum is first.
                List<Character> keys = new ArrayList<>(column.keySet());
                Collections.sort(keys, ascendingValueComparator(column));
                System.out.print(keys.get(0));
            }
            System.out.println();
        }
    }

    /**
     * Used to sort a hash map in ascending value order.
     *
     * @param <K> key
     * @param <V> value
     * @param map hash map to sort
     * @return hash map sorted in ascending order by value
     */
    public static <K, V extends Comparable<? super V>>
            Comparator<K> ascendingValueComparator(final Map<K, V> map) {
        return (K key1, K key2) -> map.get(key1).compareTo(map.get(key2));
    }

    /**
     * Used to sort a hash map in descending value order.
     *
     * @param <K> key
     * @param <V> value
     * @param map hash map to sort
     * @return hash map sorted in descending order by value
     */
    public static <K, V extends Comparable<? super V>>
            Comparator<K> descendingValueComparator(final Map<K, V> map) {
        return (K key1, K key2) -> map.get(key2).compareTo(map.get(key1));
    }
}
