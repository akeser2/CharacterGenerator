package com.example.charactergenerator;

public class Logic {
    /**
     * Takes in the index of the desired category outputs a random number
     * @param start - start index
     * @param end - end index
     * @return - returns random int [start, end]
     */
    public int getRandom(int start, int end) {
        return start + (int)(Math.random() * ((end - start) + 1));
        }
    }

