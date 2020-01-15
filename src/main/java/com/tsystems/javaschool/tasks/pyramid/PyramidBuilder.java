package com.tsystems.javaschool.tasks.pyramid;

import java.util.Collections;
import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        // TODO : Implement your solution here
        if (inputNumbers == null || inputNumbers.contains(null)) throw new CannotBuildPyramidException();
        if (inputNumbers.size() == Integer.MAX_VALUE-1) throw new CannotBuildPyramidException();
        Collections.sort(inputNumbers);
        int row = 1;
        int column = 1;
        int i;

        for (i =1; i<inputNumbers.size();){
            row++;
            i+=row;
            column+=2;
        }

        if (i!=inputNumbers.size()) throw new CannotBuildPyramidException();

        int [][] pyramid = new int [row][column];
        int numberFromList =0;
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column ; y++) {
                if (y> row-2-x && y<row+x){
                    pyramid[x][y] = inputNumbers.get(numberFromList);
                    y++;
                    numberFromList++;
                }

            }
        }

        return pyramid;
    }
}
