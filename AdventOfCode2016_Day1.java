/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2016;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author David
 */
public class AdventOfCode2016_Day1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String input = "R4, R3, L3, L2, L1, R1, L1, R2, R3, L5, L5, R4, L4, R2, R4, L3, R3, L3, R3, R4, R2, L1, R2, L3, L2, L1, R3, R5, L1, L4, R2, L4, R3, R1, R2, L5, R2, L189, R5, L5, R52, R3, L1, R4, R5, R1, R4, L1, L3, R2, L2, L3, R4, R3, L2, L5, R4, R5, L2, R2, L1, L3, R3, L4, R4, R5, L1, L1, R3, L5, L2, R76, R2, R2, L1, L3, R189, L3, L4, L1, L3, R5, R4, L1, R1, L1, L1, R2, L4, R2, L5, L5, L5, R2, L4, L5, R4, R4, R5, L5, R3, L1, L3, L1, L1, L3, L4, R5, L3, R5, R3, R3, L5, L5, R3, R4, L3, R3, R1, R3, R2, R2, L1, R1, L3, L3, L3, L1, R2, L1, R4, R4, L1, L1, R3, R3, R4, R1, L5, L2, R2, R3, R2, L3, R4, L5, R1, R4, R5, R4, L4, R1, L3, R1, R3, L2, L3, R1, L2, R3, L3, L1, L3, R4, L4, L5, R3, R5, R4, R1, L2, R3, R5, L5, L4, L1, L1";
        List<String> items = Arrays.asList(input.split("\\s*,\\s*"));

        int direction = 0; // index of direction array
        int numBlocksH = 0;
        int numBlocksV = 0;
        for (String step : items) {
            char turn = step.charAt(0);
            int blocksToMove = 0;
            for (int digitIndex = 1; digitIndex < step.length(); digitIndex++) {
                char digit = step.charAt(digitIndex);
                blocksToMove += (int) ((digit - '0') * Math.pow(10, (step.length() - 1 - digitIndex)));
            }

            direction = getDirection(direction, turn) % 4;
            switch (direction) {
                case 0: // North
                    numBlocksV += blocksToMove;
                    break;
                case 1: // East
                    numBlocksH += blocksToMove;
                    break;
                case 2: // South
                    numBlocksV -= blocksToMove;
                    break;
                case 3: // West
                    numBlocksH -= blocksToMove;
                    break;
            }
            
        }
        
        System.out.println("Total blocks away: " + (Math.abs(numBlocksV) + Math.abs(numBlocksH)));

    }

    private static int getDirection(int curDir, char turn) {
        if (turn == 'R') {
            return curDir + 1;
        }
        if (curDir < 1) {
            return 3;
        }
        return curDir - 1;
    }

}
