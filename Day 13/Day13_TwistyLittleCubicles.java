/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2016;

/**
 *
 * @author David
 */
public class Day13_TwistyLittleCubicles {

    private static final int destX = 31, destY = 39;
    private static final int FAVORITE_NUMBER = 1362;
    private static int xPos = 1, yPos = 1;
    private static final char[][] grid = new char[destX+10][destY+10];

    public static void main(String[] args) {

        // Make the maze
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (isWall(x, y)) {
                    grid[x][y] = '#';
                } else {
                    grid[x][y] = '-';
                }

                
                System.out.print(grid[x][y]);
            }
            System.out.println();
        }

        if (isWall(destX, destY)) {
            System.out.println("Destination is a wall");
            return;
        }

        int minSteps = 0;
        //int prevX = xPos, prevY = yPos;
        while (xPos != destX || yPos != destY) {

            System.out.println("X: " + xPos + ", Y: " + yPos);

            // If yPos < destY, we want to go down
            if (yPos < destY) {

                if (!isWall(xPos, yPos + 1) /*&& (yPos + 1 != prevY)*/) {
                    //            prevY = yPos;
                    yPos += 1;
                    minSteps += 1;
                }
            }
            // If xPos < destX, we want to go right
            if (xPos < destX) {
                if (!isWall(xPos + 1, yPos) /*&& (xPos + 1 != prevX)*/) {
                    //            prevX = xPos;
                    xPos += 1;
                    minSteps += 1;
                }
            }

            // If we still haven't moved, then we gotta try up or left
            if (!isWall(xPos, yPos - 1) /*&& (yPos - 1 != prevY)*/) {
                //        prevY = yPos;
                yPos -= 1;
                minSteps += 1;
                //        continue;
            }
            if (!isWall(xPos - 1, yPos) /*&& (xPos - 1 != prevX)*/) {
                //        prevX = xPos;
                xPos -= 1;
                minSteps += 1;
                //        continue;
            }

            System.out.println("Dead end! Steps taken: " + minSteps);
            break;
        }
    }

    /**
     *
     * @param x
     * @param y
     * @return true if result is odd (wall), false if even (open space)
     */
    private static boolean isWall(int x, int y) {
        int result = (x * x) + (3 * x) + (2 * x * y) + y + (y * y);
        result += FAVORITE_NUMBER;
//        System.out.println(result);
        int numOnes = Integer.bitCount(result);
        return (numOnes % 2) != 0;
    }

}
