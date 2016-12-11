/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2016;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author David
 */
public class AdventOfCode2016_Day1 {

    private static final int NUM_OF_DIRECTIONS = 4;
    private static final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
    private static final int DECIMAL = 10;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Raw input string
        String input = "R4, R3, L3, L2, L1, R1, L1, R2, R3, L5, L5, R4, L4, R2, R4, L3, R3, L3, R3, R4, R2, L1, R2, L3, L2, L1, R3, R5, L1, L4, R2, L4, R3, R1, R2, L5, R2, L189, R5, L5, R52, R3, L1, R4, R5, R1, R4, L1, L3, R2, L2, L3, R4, R3, L2, L5, R4, R5, L2, R2, L1, L3, R3, L4, R4, R5, L1, L1, R3, L5, L2, R76, R2, R2, L1, L3, R189, L3, L4, L1, L3, R5, R4, L1, R1, L1, L1, R2, L4, R2, L5, L5, L5, R2, L4, L5, R4, R4, R5, L5, R3, L1, L3, L1, L1, L3, L4, R5, L3, R5, R3, R3, L5, L5, R3, R4, L3, R3, R1, R3, R2, R2, L1, R1, L3, L3, L3, L1, R2, L1, R4, R4, L1, L1, R3, R3, R4, R1, L5, L2, R2, R3, R2, L3, R4, L5, R1, R4, R5, R4, L4, R1, L3, R1, R3, L2, L3, R1, L2, R3, L3, L1, L3, R4, L4, L5, R3, R5, R4, R1, L2, R3, R5, L5, L4, L1, L1";

        // Convert to array of strings, so we can look at them one by one
        List<String> instructions = Arrays.asList(input.split("\\s*,\\s*"));

        // List of all locations we've hit, including start
        List<Pair<Integer, Integer>> coordinates = new ArrayList<>();
        coordinates.add(new Pair(0, 0));

        int direction = 0; // Index of direction array
        int numBlocksV = 0; // N/S blocks (vertical)
        int numBlocksH = 0; // W/E blocks (horizontal)

        // Go through each instruction
        for (String step : instructions) {

            // Either R or L
            char turn = step.charAt(0);

            // Get the # of blocks to move in given direction; we need to
            // convert the string number into an actual integer
            int blocksToMove = 0;
            for (int digitIndex = 1; digitIndex < step.length(); digitIndex++) {
                char digit = step.charAt(digitIndex);
                blocksToMove += (int) ((digit - '0') * 
                        Math.pow(DECIMAL, (step.length() - 1 - digitIndex)));
            }

            // For Part 2, need to add every coordinate we've traversed
            // Just for clarity, the incrementation is separate from the loop
            direction = getDirection(direction, turn) % NUM_OF_DIRECTIONS;
            switch (direction) {
                case 0: // North
                    for (int i = 1; i <= blocksToMove; i++) {
                        coordinates.add(new Pair(numBlocksH, numBlocksV + i));
                    }
                    numBlocksV += blocksToMove;
                    break;
                case 1: // East
                    for (int i = 1; i <= blocksToMove; i++) {
                        coordinates.add(new Pair(numBlocksH + i, numBlocksV));
                    }
                    numBlocksH += blocksToMove;
                    break;
                case 2: // South
                    for (int i = 1; i <= blocksToMove; i++) {
                        coordinates.add(new Pair(numBlocksH, numBlocksV - i));
                    }
                    numBlocksV -= blocksToMove;
                    break;
                case 3: // West
                    for (int i = 1; i <= blocksToMove; i++) {
                        coordinates.add(new Pair(numBlocksH - i, numBlocksV));
                    }
                    numBlocksH -= blocksToMove;
                    break;
            }
        }

        // Part 1 answer
        System.out.println("Total blocks away: " + 
                (Math.abs(numBlocksV) + Math.abs(numBlocksH)));

        // Part 2 answer, requires you to do some math
        // Do a linear search, O(n^2); if we find the same coordinate pair, 
        // we've hit the location twice, which means we have found the HQ!
        for (int i = 0; i < coordinates.size(); i++) {
            Pair coordinate = coordinates.get(i);
            for (int j = i + 1; j < coordinates.size(); j++) {
                if (coordinate.equals(coordinates.get(j))) {
                    System.out.println(coordinate);
                    break;
                }
            }
        }
    }

    /**
     * Get the new direction after the instruction.
     *
     * @param curDir - current facing direction
     * @param turn - R or L
     * @return an integer representation of the direction (N/S/E/W) you are to
     * travel
     */
    private static int getDirection(int curDir, char turn) {

        // If we're turning right, just add 1, since we mod later
        if (turn == 'R') {
            return curDir + 1;
        }

        // Going left - make sure if we're going negative (west), reset to west
        if (curDir == NORTH) {
            return WEST;
        }
        
        // Otherwise, we just subtract
        return curDir - 1;
    }

}
