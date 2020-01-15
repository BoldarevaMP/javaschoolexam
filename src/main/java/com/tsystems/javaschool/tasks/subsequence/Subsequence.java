package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
        // TODO: Implement the logic here
        if (x==null || y == null) throw new IllegalArgumentException();
        if (x.isEmpty()&& !y.isEmpty()) return true;
        int i = 0;
        for (int j = 0; j < y.size(); j++) {
            if (x.get(i).equals(y.get(j))){
                i++;
                if (i==x.size()) {
                    while(y.size()!=x.size()){
                        y.remove(x.size());
                    }
                    break;
                }
            } else {
                y.remove(j);
                j--;
            }
        }

        if (x.size() ==y.size()) return true;
        return false;
    }
}
