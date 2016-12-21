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

/**
 *
 * @author David
 */
public class Day8_TwoFactorAuth {

    private static final String INPUT_FILE = "C:\\Users\\David\\Documents\\NetBeansProjects\\AdventOfCode2016\\src\\adventofcode2016\\Day8_input.txt";
    private static final int GRID_WIDTH = 50;
    private static final int GRID_HEIGHT = 6;
    private static final char[][] grid = new char[GRID_HEIGHT][GRID_WIDTH];

    /**
     * Runs the Day 8 solutions.
     *
     * @param args
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        InputStream is = new FileInputStream(new File(INPUT_FILE));
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null) {

            if (line.contains("rect")) {

                // Get the digits surrounding the X
                // Set the corresponding index values in the grid on
                int width = Integer.parseInt(
                        line.substring((line.indexOf(" ") + 1),
                                line.indexOf("x"))
                );
                int height = Integer.parseInt(
                        line.substring((line.indexOf("x") + 1),
                                line.length())
                );
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        grid[i][j] = '*';
                    }
                }

            } // Otherwise, it's rotate
            else {

                if (line.contains("rotate row")) { // y
                    int rowNumber = Integer.parseInt(
                            line.substring(line.indexOf("=") + 1,
                                    line.indexOf("b") - 1)
                    );
                    int rotateVal = Integer.parseInt(
                            line.substring(line.indexOf("by") + 3,
                                    line.length())
                    );

                    for (int rotated = 0; rotated < rotateVal; rotated++) {
                        char temp = grid[rowNumber][0];
                        for (int index = GRID_WIDTH; index > 0; index--) {
                            grid[rowNumber][index % GRID_WIDTH] = grid[rowNumber][index - 1];
                        }
                        grid[rowNumber][1] = temp;
                    }

                } else { // rotate column, x

                    int colNumber = Integer.parseInt(
                            line.substring(line.indexOf("=") + 1,
                                    line.indexOf("b") - 1)
                    );
                    int rotateVal = Integer.parseInt(
                            line.substring(line.indexOf("by") + 3),
                            line.length()
                    );

                    for (int rotated = 0; rotated < rotateVal; rotated++) {
                        char temp = grid[0][colNumber];
                        for (int index = GRID_HEIGHT; index > 0; index--) {
                            grid[index % GRID_HEIGHT][colNumber] = grid[index - 1][colNumber];
                        }
                        grid[1][colNumber] = temp;
                    }

                }
            }
        }

        int pixels = 0;
        for (char[] g : grid) {
            for (char c : g) {
                if (c == '*') {
                    pixels++;
                }
                System.out.print((c == '*') ? c : " ");
            }
            System.out.println();
        }
        System.out.println("Pixels Lit: " + pixels);
    }
}
