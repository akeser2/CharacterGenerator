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
        }return array;
    }
    public String[] items() {
        // 0-3 weapon 1
        // 4-6 packs
        // 7-10 food
        // 11-14 combat
        // 15-18 misc
        String[] output = new String[5];
        String[] options = {"Dagger", "Short Sword", "War Axe", "Bow",
                            "Thief Pack", "Explorers Pack", "Smith's Tools",
                            "Bread Loaf", "Cheese Wheel", "2 Apples", "Wine Pitcher",
                            "Caltrops", "Pimple Poison", "Healing Potion", "Tinder Box",
                            "Magic Beans", "Heirloom Necklace", "Dust of Sneezing & Choking", "Potion of Animal Friendship"
        };
        output[0] = options[getRandom(0,3)];
        output[1] = options[getRandom(4,6)];
        output[2] = options[getRandom(7,10)];
        output[3] = options[getRandom(11,14)];
        output[4] = options[getRandom(15,18)];
        return output;
    }
}

