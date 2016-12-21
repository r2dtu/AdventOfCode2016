/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventofcode2016;

import java.util.Arrays;
import java.util.List;

/**
 * Comments: I actually had to go to the bathroom when I started this.
 *
 * @author David
 */
public class Day2_BathroomSecurity {

    private static int vPos = 1, hPos = 1;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Test input
//        String input = "ULL\n"
//                + "RRDDD\n"
//                + "LURDL\n"
//                + "UUUUD";

        // Day 2 input
        String input = "RDLRUUULRRDLRLLRLDDUDLULULDDULUDRRUURLRLLUULDURRULLRULDRRDLLULLRLLDRLDDRRRRLLRLURRRDRDULRDUULDDDULURUDDRRRUULUDRLLUUURLUDRUUUDRDUULLRLLUDDRURRDDDRDLUUURLRLLUDRURDUDUULDDLLRDURULLLURLDURLUUULDULDDULULLLRRUDLRUURDRDLLURLUDULDUUUURRLDLUDRULUDLDLLDRLDDDRRLLDUDLLRRDDDRLUDURLLLDRUDDLDDRRLUDRRDUDLRRLULDULURULDULUULDRLLDRUUDDRLLUDRULLRRRLRDLRLUDLRULDRDLRDRLRULUDUURRUUULLDDDDUDDLDDDDRRULRDLRDDULLDLDLLDLLDLLDRRDDDRDDLRRDDDRLLLLURRDLRRLDRURDDURDULDDRUURUDUDDDRDRDDRLRRLRULLDRLDLURLRLRUDURRRDLLLUDRLRDLLDDDLLUDRLDRRUUDUUDULDULLRDLUDUURLDDRUDR\n"
                + "URULDDLDDUDLLURLUUUUUULUDRRRDDUDURDRUURLLDRURLUULUDRDRLLDRLDULRULUURUURRLRRDRUUUDLLLLRUDDLRDLLDUDLLRRURURRRUDLRLRLLRULRLRLRDLRLLRRUDDRLRUDULDURDLDLLLRDRURURRULLLDLLRRDRLLDUUDLRUUDDURLLLDUUDLRDDURRDRRULLDRLRDULRRLLRLLLLUDDDRDRULRRULLRRUUDULRRRUDLLUUURDUDLLLURRDDUDLDLRLURDDRRRULRRUDRDRDULURULRUDULRRRLRUDLDDDDRUULURDRRDUDLULLRUDDRRRLUDLRURUURDLDURRDUUULUURRDULLURLRUUUUULULLDRURULDURDDRRUDLRLRRLLLLDDUURRULLURURRLLDRRDDUUDLLUURRDRLLLLRLUDUUUDLRLRRLDURDRURLRLRULURLDULLLRRUUUDLLRRDUUULULDLLDLRRRDUDDLRULLULLULLULRU\n"
                + "DURUUDULRRLULLLDDUDDLRRDURURRRDDRRURDRURDRLULDUDUDUULULDDUURDDULRDUDUDRRURDRDDRLDRDRLDULDDULRULLDULURLUUDUDULRDDRRLURLLRRDLLDLDURULUDUDULDRLLRRRUDRRDDDRRDRUUURLDLURDLRLLDUULLRULLDDDDRULRRLRDLDLRLUURUUULRDUURURLRUDRDDDRRLLRLLDLRULUULULRUDLUDULDLRDDDDDRURDRLRDULRRULRDURDDRRUDRUDLUDLDLRUDLDDRUUULULUULUUUDUULDRRLDUDRRDDLRUULURLRLULRURDDLLULLURLUDLULRLRRDDDDDRLURURURDRURRLLLLURLDDURLLURDULURUUDLURUURDLUUULLLLLRRDUDLLDLUUDURRRURRUUUDRULDDLURUDDRRRDRDULURURLLDULLRDDDRRLLRRRDRLUDURRDLLLLDDDDLUUURDDDDDDLURRURLLLUURRUDLRLRRRURULDRRLULD\n"
                + "LLUUURRDUUDRRLDLRUDUDRLRDLLRDLLDRUULLURLRRLLUDRLDDDLLLRRRUDULDLLLDRLURDRLRRLURUDULLRULLLURRRRRDDDLULURUDLDUDULRRLUDDURRLULRRRDDUULRURRUULUURDRLLLDDRDDLRRULRDRDRLRURULDULRRDRLDRLLDRDURUUULDLLLRDRRRLRDLLUDRDRLURUURDLRDURRLUDRUDLURDRURLRDLULDURDDURUUDRLULLRLRLDDUDLLUUUURLRLRDRLRRRURLRULDULLLLDLRRRULLUUDLDURUUUDLULULRUDDLLDLDLRLDDUDURDRLLRRLRRDDUDRRRURDLRLUUURDULDLURULUDULRRLDUDLDDDUUDRDUULLDDRLRLLRLLLLURDDRURLDDULLULURLRDUDRDDURLLLUDLLLLLUDRDRDLURRDLUDDLDLLDDLUDRRDDLULRUURDRULDDDLLRLDRULURLRURRDDDRLUUDUDRLRRUDDLRDLDULULDDUDURRRURULRDDDUUDULLULDDRDUDRRDRDRDLRRDURURRRRURULLLRRLR\n"
                + "URLULLLDRDDULRRLRLUULDRUUULDRRLLDDDLDUULLDRLULRRDRRDDDRRDLRRLLDDRDULLRRLLUDUDDLDRDRLRDLRDRDDUUDRLLRLULLULRDRDDLDDDRLURRLRRDLUDLDDDLRDLDLLULDDRRDRRRULRUUDUULDLRRURRLLDRDRRDDDURUDRURLUDDDDDDLLRLURULURUURDDUDRLDRDRLUUUULURRRRDRDULRDDDDRDLLULRURLLRDULLUUDULULLLLRDRLLRRRLLRUDUUUULDDRULUDDDRRRULUDURRLLDURRDULUDRUDDRUURURURLRDULURDDDLURRDLDDLRUDUUDULLURURDLDURRDRDDDLRRDLLULUDDDRDLDRDRRDRURRDUDRUURLRDDUUDLURRLDRRDLUDRDLURUDLLRRDUURDUDLUDRRL";
        List<String> instructions = Arrays.asList(input.split("\\s*\n\\s*"));

        // Print out the code, Part 1
        System.out.println("Part 1: The code is: ");
        int[] code = new int[instructions.size()];
        for (int index = 0; index < code.length; index++) {
            code[index] = getCodeNumber_Part1(instructions.get(index));
            System.out.print(code[index]);
        }
        System.out.println(".");

        hPos = 0;
        vPos = 2;

        // Print out the code, Part 2
        System.out.println("Part 2: The code is: ");
        char[] code2 = new char[instructions.size()];
        for (int index = 0; index < code2.length; index++) {
            code2[index] = getCodeNumber_Part2(instructions.get(index));
            System.out.print(code2[index]);
        }
        System.out.println(".");
    }

    /**
     * Finds the code number given a sequence of directional instructions,
     * starting at 5, given conditions for Part 1 (the padlock).
     *
     * @param sequence - string of U, D, L, R characters
     * @return single digit code number
     */
    private static int getCodeNumber_Part1(String sequence) {
        int[][] padLock = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        // If we hit an edge, we can't go any further; i.e. if the current
        // digit is 3 and we need to go right, we stay on 3
        for (char instruction : sequence.toCharArray()) {
            switch (instruction) {
                case 'U':
                    if (vPos < 1) {
                        vPos = 0;
                    } else {
                        vPos--;
                    }
                    break;
                case 'D':
                    if (vPos > 1) {
                        vPos = 2;
                    } else {
                        vPos++;
                    }
                    break;
                case 'L':
                    if (hPos < 1) {
                        hPos = 0;
                    } else {
                        hPos--;
                    }
                    break;
                case 'R':
                    if (hPos > 1) {
                        hPos = 2;
                    } else {
                        hPos++;
                    }
                    break;
            }
        }
        return padLock[vPos][hPos];
    }

    /**
     * Finds the code number given a sequence of directional instructions,
     * starting at 5, given the conditions for part 2 (different padlock).
     *
     * @param sequence - string of U, D, L, R characters
     * @return single digit code number
     */
    private static char getCodeNumber_Part2(String sequence) {
        char[][] padLock = {
            {'-', '-', '1', '-', '-'},
            {'-', '2', '3', '4', '-'},
            {'5', '6', '7', '8', '9'},
            {'-', 'A', 'B', 'C', '-'},
            {'-', '-', 'D', '-', '-'}
        };

        // If we hit an edge, we can't go any further; i.e. if the current
        // digit is 3 and we need to go right, we stay on 3
        for (char instruction : sequence.toCharArray()) {
            switch (instruction) {
                case 'U':
                    // (row, column) : (0, 2), (1, 1/3), (2, 0/4)
                    if (vPos == 0) {
                        vPos = 0;
                        break;
                    }
                    if (vPos == 1) {
                        if (hPos != 2) {
                            vPos = 1;
                            break;
                        }
                    }
                    if (vPos == 2) {
                        if (hPos == 0 || hPos == 4) {
                            vPos = 2;
                            break;
                        }
                    }
                    vPos--;
                    break;
                case 'D':
                    // (row, column) : (4, 2), (3, 1/3), (2, 0/4)
                    if (vPos == 4) {
                        vPos = 4;
                        break;
                    }
                    if (vPos == 3) {
                        if (hPos != 2) {
                            vPos = 3;
                            break;
                        }
                    }
                    if (vPos == 2) {
                        if (hPos == 0 || hPos == 4) {
                            vPos = 2;
                            break;
                        }
                    }
                    vPos++;
                    break;
                case 'L':
                    // (row, column) : (2, 0), (1/3, 1), (0/4, 2)
                    if (hPos == 0) {
                        hPos = 0;
                        break;
                    }
                    if (hPos == 1) {
                        if (vPos != 2) {
                            hPos = 1;
                            break;
                        }
                    }
                    if (hPos == 2) {
                        if (vPos == 0 || vPos == 4) {
                            hPos = 2;
                            break;
                        }
                    }
                    hPos--;
                    break;
                case 'R':
                    // (row, column) : (4, 2), (1/3, 3), (0/4, 2)
                    if (hPos == 4) {
                        hPos = 4;
                        break;
                    }
                    if (hPos == 3) {
                        if (vPos != 2) {
                            hPos = 3;
                            break;
                        }
                    }
                    if (hPos == 2) {
                        if (vPos == 0 || vPos == 4) {
                            hPos = 2;
                            break;
                        }
                    }
                    hPos++;
                    break;
            }
        }
        return padLock[vPos][hPos];
    }
}
