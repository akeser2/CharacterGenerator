package com.example.charactergenerator;;

import java.util.Arrays;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

public class AddInfo {

    public int getRandom(int start, int end) {
        return start + (int)(Math.random() * ((end - start) + 1));
    }

    public int[] abilityScores() {
        int[] array = { 8, 10, 12, 13, 14, 15 };

        Random rand = new Random();

        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = rand.nextInt(array.length);
            int temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
        return array;

    }
}

