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
import java.util.List;

/**
 * IPv7 addresses, b/c why not!
 *
 * @author David
 */
public class Day7_InternetProtocolVer7 {

    private static final String INPUT_FILE = "C:\\Users\\David\\Documents\\NetBeansProjects\\AdventOfCode2016\\src\\adventofcode2016\\Day7_input.txt";
    private static List<String> ABAStrings = new ArrayList<>();
    private static List<String> BABStrings = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException, IOException {

        int numTLSsupport = 0, numSLSsupport = 0;
        InputStream is1 = new FileInputStream(new File(INPUT_FILE));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ABAStrings.clear();
                BABStrings.clear();
                boolean supportsTLS = false;
                boolean supportsSLS = false;
                List<String> input = Arrays.asList(line.toLowerCase().split("\\[|\\]"));
                System.out.println(input);
                int index = 0;
                List<String> aba = new ArrayList<>();
                List<String> bab = new ArrayList<>();
                for (String crypt : input) {
                    // Square brackets
                    if (index % 2 != 0) {
                        if (containsABBA(crypt)) {
                            supportsTLS = false;
                            break;
                        }
                    }
                    // Outside square brackets
                    if (index % 2 == 0) {
                        if (containsABBA(crypt)) {
                            supportsTLS = true;
                        }
                    }
                    index++;
                }

                for (index = 0; index < input.size();) {
                    // Outside square brackets - ABA
                    if (index % 2 == 0) {
                        List<String> str = containsABA(input.get(index), true);
                        for (String seq : str) {
                            if (!aba.contains(seq)) {
                                aba.add(seq);
                            }
                        }
                        index += 2;
                        if (index >= input.size()) {
                            index = 1;
                        }
                    }

                    // Square brackets - BAB
                    if (index % 2 != 0) {
                        List<String> str = containsABA(input.get(index), false);
                        for (String seq : str) {
                            if (!bab.contains(seq)) {
                                bab.add(seq);
                            }
                        }
                        index += 2;
                    }
                }

                // Not necessary, but this is kinda cool!
                Collections.sort(aba, (String o1, String o2) -> o1.compareTo(o2));
                Collections.sort(bab, (String o1, String o2) -> o1.compareTo(o2));

                // Need to check for ABA - BAB relationships for SLS
                if (equalsABA(aba, bab)) {
                    supportsSLS = true;
                }

                // Add 1 to TLS or SLS if the IP supports it
                if (supportsTLS) {
                    numTLSsupport += 1;
                }
                if (supportsSLS) {
                    numSLSsupport += 1;
                }
            }
        }

        System.out.println(
                "Number of IPs that support TLS: " + numTLSsupport);
        System.out.println(
                "Number of IPs that support SLS: " + numSLSsupport);

    }

    /**
     * Determines if a given string contains an ABBA sequence.
     *
     * @param input string to find possibly contained ABBA sequence
     * @return true if ABBA sequence occurs
     */
    private static boolean containsABBA(String input) {
        if (input.length() < 4) {
            return false;
        }
        if (input.charAt(0) == input.charAt(3)
                && input.charAt(1) == input.charAt(2)
                && input.charAt(0) != input.charAt(1)) {
            return true;
        }
        return containsABBA(input.substring(1, input.length()));
    }

    /**
     * Determines if a given string contains an ABA sequence, and returns that
     * ABA sequence.
     *
     * @param input string to find possibly contained ABA sequence
     * @param aba - true if aba, false if bab
     * @return ABA sequence, if occurs; otherwise, null
     */
    private static List<String> containsABA(String input, boolean aba) {
        if (input.length() < 3) {
            if (aba) {
                return ABAStrings;
            }
            return BABStrings;
        }
        if (input.charAt(0) == input.charAt(2)
                && input.charAt(0) != input.charAt(1)) {
            if (aba) {
                ABAStrings.add(input.substring(0, 3));
            } else {
                BABStrings.add(input.substring(0, 3));
            }
        }
        return containsABA(input.substring(1, input.length()), aba);
    }

    /**
     * Determines if two strings have an ABA - BAB relationship with each other.
     *
     * @param aba
     * @param bab
     * @return true if one string is ABA and the other is BAB
     */
    private static boolean equalsABA(String aba, String bab) {
        return aba.charAt(0) == bab.charAt(1)
                && aba.charAt(1) == bab.charAt(0);
    }

    /**
     * Determines if two entire lists have some strings that have ABA - BAB
     * relationships. Calls the equalsABA(String, String) function.
     *
     * @param aba
     * @param bab
     * @return true if one string in the list appears as its equal ABA - BAB in
     * the other list
     */
    private static boolean equalsABA(List<String> aba, List<String> bab) {
        boolean equalsABA = false;
        for (String s : aba) {
            for (String t : bab) {
                if (equalsABA(s, t)) {
                    equalsABA = true;
                }
            }
        }
        return equalsABA;
    }

}
